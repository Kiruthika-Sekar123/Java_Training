package com.infovision.sharemarket.trading.Interface;

import java.util.List;

import com.infovision.sharemarket.trading.exception.*;
import com.infovision.sharemarket.trading.model.Stock;
import com.infovision.sharemarket.trading.service.ImplTradeableService;

public interface Market {
    void addStock(Stock stock);
    List<Stock> getAllStocks() throws StockNotFoundException;

    void executeBuyStock(ImplTradeableService trader, String stockId, int quantity)
        throws StockNotFoundException, InsufficientMarketSharesException, InsufficientFundsException;

    void executeSellStock(ImplTradeableService trader, String stockId, int quantity)
        throws StockNotFoundException, InsufficientSharesException;
}
