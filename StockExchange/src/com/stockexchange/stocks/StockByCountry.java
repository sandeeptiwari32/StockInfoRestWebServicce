package com.stockexchange.stocks;

import javax.xml.bind.annotation.XmlElement;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StockByCountry extends Stocks{
    
    @XmlElement(name = "companyname")
    private String company;
    
    @XmlElement(name = "capital")
    private String capital;
    
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCompany() {
        return company;
    }

    public void setCompanyt(String company) {
        this.company = company;
    }

    @Override
    public void updateValues(Element trElement, String continent) {
        
        Elements tdElements = trElement.getElementsByTag("td");
        String companyName = tdElements.get(1).getElementsByTag("a").get(0).childNode(0).toString().trim();
        String currentPrice = tdElements.get(2).childNode(0).toString().trim();
        String priceChange = tdElements.get(4).childNode(0).toString().trim();
        String day = tdElements.get(5).childNode(0).toString().trim();
        String year = tdElements.get(6).childNode(0).toString().trim();
        String capital = tdElements.get(7).childNode(0).toString().trim();
        String date = tdElements.get(8).childNode(0).toString().trim();
        
        
        this.setCompanyt(companyName);
        this.setPrice(currentPrice);
        this.setPriceChange(priceChange);
        this.setDay(day);
        this.setYearly(year);
        this.setCapital(capital);
        this.setDate(date);
        
    }
}
