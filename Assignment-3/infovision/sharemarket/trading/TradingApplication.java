package com.infovision.sharemarket.trading;

import com.infovision.sharemarket.trading.model.*;
import com.infovision.sharemarket.trading.service.*;
import com.infovision.sharemarket.trading.Interface.*;
import java.util.*;
import java.util.concurrent.*;


public class TradingApplication {
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("📈 Welcome to the Share Market Trading System (Automated Simulation)!");

        // Initialize the market
        List<Stock> stockInventory = new ArrayList<>();
        stockInventory.add(new Stock("TCS", "Tata Consultancy Services", 3500.0, 1000));
        stockInventory.add(new Stock("INFY", "Infosys Ltd", 1500.0, 1500));
        stockInventory.add(new Stock("RELI", "Reliance Industries", 2500.0, 1200));
        
       

        // Create traders with preset balances
        List<Trader> traders = new ArrayList<>();
        traders.add(new Trader("Alice", 100_000));
        traders.add(new Trader("Bob", 150_000));
        traders.add(new Trader("Charlie", 100_000));
        
        for (Trader trader : traders) {
            trader.login();
            trader.performRole();
        }

        ExecutorService executor = Executors.newFixedThreadPool(traders.size());
        CountDownLatch latch = new CountDownLatch(traders.size());

        for (Trader trader : traders) {
            executor.submit(() -> {
                Market marketService = new ImplMarketService(trader);
                stockInventory.forEach(marketService::addStock);
                Tradeable trade = new ImplTradeableService(marketService, trader);

                // Perform a single random action
                boolean buy = random.nextBoolean();
                Stock stock = stockInventory.get(random.nextInt(stockInventory.size()));
                int quantity = random.nextInt(10) + 1; // Buy/sell 1-10 shares

                try {
                    if (buy) {
                        trade.buyStock(stock.getStockId(), quantity);
                        System.out.println(trader.getName() + " bought " + quantity + " shares of " + stock.getStockId());
                    } else {
                        trade.sellStock(stock.getStockId(), quantity);
                        System.out.println(trader.getName() + " sold " + quantity + " shares of " + stock.getStockId());
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Error for " + trader.getName() + ": " + e.getMessage());
                }

                System.out.println("✅ " + trader.getName() + " finished trading. Final Portfolio:");
                System.out.println(trader);

                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();

        System.out.println("\n📊 Final Portfolio Summary:");
        traders.forEach(System.out::println);
    }
}
