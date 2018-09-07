package com.stockexchange.stocks;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@XmlRootElement(name="StockByContinent")
public class StockByContinent extends Stocks{
    
    @XmlElement(name = "continentname")
    public String continent;
    
    @XmlElement(name = "stockmarketname")
    public String stockMarket;
    
    @XmlElement(name = "pricechange_weekly")
    public String weekly;
    
    @XmlElement(name = "pricechange_monthly")
    public String monthly;
    
    public String getContinent() {
        return continent;
    }
    
    public void setContinent(String continent) {
        this.continent = continent;
    }
    
    public String getStockMarket() {
        return stockMarket;
    }
    
    public void setStockMarket(String stockMarket) {
        this.stockMarket = stockMarket;
    }
    
    public String getWeekly() {
        return weekly;
    }
    
    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }
    
    public String getMonthly() {
        return monthly;
    }
    
    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }
    
    @Override
    public void updateValues(Element trElement, String continent) {
        
        Elements tdElements = trElement.getElementsByTag("td");
        String stockName = tdElements.get(1).getElementsByTag("b").get(0).childNode(0).toString().trim();
        String currentPrice = tdElements.get(2).childNode(0).toString().trim();
        String priceChange = tdElements.get(3).attr("data-value").trim();
        String day = tdElements.get(4).attr("data-value").trim();
        String week = tdElements.get(5).attr("data-value").trim();
        String month = tdElements.get(6).attr("data-value").trim();
        String year = tdElements.get(7).attr("data-value").trim();
        String date = tdElements.get(8).childNode(0).toString().trim();
        
        
        this.setContinent(continent);
        this.setStockMarket(stockName);
        this.setPrice(currentPrice);
        this.setPriceChange(priceChange);
        this.setDay(day);
        this.setWeekly(week);
        this.setMonthly(month);
        this.setYearly(year);
        this.setDate(date);
        
    }
}
