package com.stockexchange.stocks;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Path("/info")
public class StockInfoExtractorImpl {
    private static final String URL = "https://tradingeconomics.com/"; 
    
    @Path("/stockinfo/continent")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public ArrayList<Stocks> getStockInfoByContinent() throws IOException
    {
        StringBuffer buffURL = new StringBuffer(URL);
        buffURL.append("stocks");
        return this.getStockInfo(buffURL.toString(),StockFactory.BY_CONTINENT);
    }
    
    @Path("/stockinfo/country/{country}")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public ArrayList<Stocks> getStockInfoByCountry(@PathParam("country") String country) throws IOException
    {
        StringBuffer buffURL = new StringBuffer(URL);
        buffURL.append(country).append("stock-market");
        return this.getStockInfo(buffURL.toString(),StockFactory.BY_COUNTRY);
    }
    
    public ArrayList<Stocks> getStockInfo(String url, String stocksBy) throws IOException
    {
        ArrayList<Stocks> stocks = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements elements = null;
        elements = doc.getElementsByClass(StockFactory.getClassId(stocksBy));
        for(Element element: elements)
        {
            String continent = null; 
            if(StockFactory.BY_CONTINENT.equalsIgnoreCase(stocksBy))        
                continent =  element.getElementsByTag("th").get(1).childNode(0).toString().trim();
            Elements trElements = element.getElementsByTag("tr");
            for(Element trElement:trElements)
            {
                if(trElement.getElementsByTag("td").size()<=0)
                    continue;
                
                Stocks stock = StockFactory.getInstance(stocksBy);
                stock.updateValues(trElement,continent);
                stocks.add(stock);
            }
            if(StockFactory.BY_COUNTRY.equalsIgnoreCase(stocksBy))
                break;
        }
        return stocks;
    }
    
}
