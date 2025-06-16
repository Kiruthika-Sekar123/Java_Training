package com.infovision.sharemarket.trading.Interface;

import com.infovision.sharemarket.trading.exception.*;

public interface Tradeable {
    void buyStock(String stockId, int quantity)
        throws StockNotFoundException, InsufficientMarketSharesException,InsufficientFundsException;

    void sellStock(String stockId, int quantity)
        throws StockNotFoundException, InsufficientSharesException;
}


