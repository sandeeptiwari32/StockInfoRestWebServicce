package com.stockexchange.stocks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jsoup.nodes.Element;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Stock")

public abstract class Stocks {
    @XmlElement(name = "currentprice")
    private String price;
    
    @XmlElement(name = "pricechange")
    private String priceChange;

    @XmlElement(name = "pricechange_day")
    private String day;
    
    @XmlElement(name = "pricechange_yearly")
    private String yearly;
    
    @XmlElement(name = "date")
    private String date;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYearly() {
        return yearly;
    }

    public void setYearly(String yearly) {
        this.yearly = yearly;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public abstract void updateValues(Element trElement, String continent);
}
