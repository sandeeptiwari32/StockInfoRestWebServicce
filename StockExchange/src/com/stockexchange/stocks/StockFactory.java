package com.stockexchange.stocks;

public class StockFactory {
    public static final String BY_CONTINENT = "CONTINENT";
    public static final String BY_COUNTRY   = "COUNTRY";
    
    public static Stocks getInstance(String type)
    {
        if(BY_CONTINENT.equalsIgnoreCase(type))
        {
            return new StockByContinent();
        }
        else if(BY_COUNTRY.equalsIgnoreCase(type))
        {
            return new StockByCountry();
        }
        return null; 
    }

    public static String getClassId(String stocksBy) {
        
        if(BY_CONTINENT.equalsIgnoreCase(stocksBy))
        {
            return "table table-hover table-striped";
        }
        else if(BY_COUNTRY.equalsIgnoreCase(stocksBy))
        {
            return "table table-hover sortable-theme-minimal table-heatmap";
        }
        return null;
    }
}
