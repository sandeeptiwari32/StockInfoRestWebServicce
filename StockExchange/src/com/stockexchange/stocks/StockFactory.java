package com.stockexchange.stocks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class StockFactory {
    public static final String BY_CONTINENT = "CONTINENT";
    public static final String BY_COUNTRY   = "COUNTRY";
    private static final String URL = "https://tradingeconomics.com/"; 
    
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

    public static JAXBContext getJAXBContext(String type) throws JAXBException {
        
        if(BY_CONTINENT.equalsIgnoreCase(type))
        {
            return JAXBContext.newInstance(StockByContinent.class);
        }
        else if(BY_COUNTRY.equalsIgnoreCase(type))
        {
            return JAXBContext.newInstance(StockByCountry.class);
        }
        return null;
    }

    public static String getURL(String vals[]) {
        StringBuffer buffURL = new StringBuffer(URL);
        createURL(buffURL,vals);
        return buffURL.toString();
    }

    private static void createURL(StringBuffer buffURL, String[] vals) {
        for(String val:vals)
        {
            buffURL.append(val+"/");
        }   
    }
}
