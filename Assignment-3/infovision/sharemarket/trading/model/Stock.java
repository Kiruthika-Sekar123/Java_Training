package com.infovision.sharemarket.trading.model;


public class Stock {
    private String stockId;
    private String stockName;
    private double pricePerShare;
    private int availableShares;

    public Stock(String stockId, String stockName, double pricePerShare, int availableShares) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
        this.availableShares = availableShares;
    }

    public String getStockId() {
        return stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

	public int getAvailableShares() {
        return availableShares;
    }

	 public void setAvailableShares(int availableShares) {
	        this.availableShares = availableShares;
	    }

    @Override
    public String toString() {
        return String.format("Stock{id='%s', name='%s', price=%.2f, availableShares=%d}",
                stockId, stockName, pricePerShare, availableShares);
    }
}
