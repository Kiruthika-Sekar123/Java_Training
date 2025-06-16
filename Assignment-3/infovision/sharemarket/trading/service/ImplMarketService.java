package com.infovision.sharemarket.trading.service;

import com.infovision.sharemarket.trading.Interface.Market;
import com.infovision.sharemarket.trading.exception.*;
import com.infovision.sharemarket.trading.model.*;

import java.util.ArrayList;
import java.util.List;

public class ImplMarketService implements Market {
	
	    private Trader trader;

	    public ImplMarketService(Trader trader) {
	        if (trader == null) {
	            throw new IllegalArgumentException("Trader cannot be null");
	        }
	        this.trader = trader;
	    }

    private List<Stock> stockInventory = new ArrayList<>();

    @Override
    public void addStock(Stock stock) {
        stockInventory.add(stock);
    }

    @Override
    public List<Stock> getAllStocks() throws StockNotFoundException {
        if (stockInventory.isEmpty()) {
            throw new StockNotFoundException("No stocks available in the market.");
        }
        return new ArrayList<>(stockInventory);
    }

    @Override
    public void executeBuyStock(ImplTradeableService traderService, String stockId, int quantity)
            throws StockNotFoundException, InsufficientMarketSharesException, InsufficientFundsException {
        Stock stock = findStockInMarket(stockId);

        synchronized (stock) { // lock stock while checking/modifying available shares
            if (stock.getAvailableShares() < quantity) {
                throw new InsufficientMarketSharesException("Not enough shares available in the market.");
            }
            double totalCost = stock.getPricePerShare() * quantity;

            synchronized (trader) { // lock trader balance update
                if (trader.getBalance() < totalCost) {
                    throw new InsufficientFundsException("Insufficient funds to complete the purchase.");
                }
                stock.setAvailableShares(stock.getAvailableShares() - quantity);
                trader.setBalance(trader.getBalance() - totalCost);
            }
        }
        traderService.addStockToPortfolio(stock, quantity);
    }

    @Override
    public void executeSellStock(ImplTradeableService trader, String stockId, int quantity)
            throws StockNotFoundException, InsufficientSharesException {
        Stock stock = findStockInMarket(stockId);

        synchronized (stock) {
            synchronized (trader) {
                int traderStockQuantity = trader.getStockQuantity(stockId);
                if (traderStockQuantity < quantity) {
                    throw new InsufficientSharesException("Not enough shares to sell.");
                }
                trader.removeStockFromPortfolio(stockId, quantity);
                stock.setAvailableShares(stock.getAvailableShares() + quantity);
            }
        }
    }


    private Stock findStockInMarket(String stockId) throws StockNotFoundException {
        for (Stock s : stockInventory) {
            if (s.getStockId().equals(stockId)) {
                return s;
            }
        }
        throw new StockNotFoundException("Stock not found: " + stockId);
    }
}
