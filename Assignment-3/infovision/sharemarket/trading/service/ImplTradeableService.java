package com.infovision.sharemarket.trading.service;

import com.infovision.sharemarket.trading.Interface.Market;
import com.infovision.sharemarket.trading.Interface.Tradeable;
import com.infovision.sharemarket.trading.exception.*;
import com.infovision.sharemarket.trading.model.*;
import java.util.List;

public class ImplTradeableService implements Tradeable {

    private Market market;
    private Trader trader;
   

    public ImplTradeableService(Market market, Trader trader) {
        this.market = market;
        this.trader = trader;
    }

   

	@Override
    public void buyStock(String stockId, int quantity)
            throws StockNotFoundException, InsufficientMarketSharesException, InsufficientFundsException {
		System.out.println("fxahv");
        market.executeBuyStock(this, stockId, quantity);
    }

    @Override
    public void sellStock(String stockId, int quantity)
            throws StockNotFoundException, InsufficientSharesException {
        market.executeSellStock(this, stockId, quantity);
    }

    // Portfolio management methods inside ImplTradeableService

    public synchronized  void addStockToPortfolio(Stock stock, int quantity) {
    	List<Stock> portfolio = trader.getPortfolio();
        for (Stock s : portfolio) {
            if (s.getStockId().equals(stock.getStockId())) {
                s.setAvailableShares(s.getAvailableShares() + quantity);
                System.out.println("Updated existing stock: " + s);
                return;
            }
        }
        portfolio.add(new Stock(stock.getStockId(), stock.getStockName(), stock.getPricePerShare(), quantity));
        System.out.println("Added new stock: " + stock);
        
    }

    public synchronized  void removeStockFromPortfolio(String stockId, int quantity)
            throws StockNotFoundException, InsufficientSharesException {
    	List<Stock> portfolio = trader.getPortfolio();
        Stock stock = null;
        for (Stock s : portfolio) {
            if (s.getStockId().equals(stockId)) {
                stock = s;
                break;
            }
        }
        if (stock == null) {
            throw new StockNotFoundException("Stock not found in portfolio.");
        }
        if (stock.getAvailableShares() < quantity) {
            throw new InsufficientSharesException("Not enough shares to sell.");
        }
        System.out.println("Before: " + stock.getAvailableShares());
        stock.setAvailableShares(stock.getAvailableShares() - quantity);
        System.out.println("After: " + stock.getAvailableShares());
        if (stock.getAvailableShares() == 0) {
            portfolio.remove(stock);
        }
    }

    public int getStockQuantity(String stockId) {
    	List<Stock> portfolio = trader.getPortfolio();
        for (Stock s : portfolio) {
            if (s.getStockId().equals(stockId)) {
                return s.getAvailableShares();
            }
        }
        return 0;
    }

}
