package com.stockexchange.stocks;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Path("/info")
public class StockInfoExtractorImpl {
    
    @Path("/stockinfo/continent")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public Response getInfoByContinent(@QueryParam("markets") final String  market) throws IOException, JAXBException
    {
        String params[]={market};
        ArrayList<Stocks> result = this.getStockInfo(StockFactory.getURL(params),StockFactory.BY_CONTINENT); 
        return Response.ok(200).entity(this.getFromatedData(result,StockFactory.BY_CONTINENT)).build();
    }
    
    @Path("/stockinfo/country/{country}")
    @Produces(MediaType.APPLICATION_XML)
    @GET
    public Response getStockInfoByCountry(@PathParam("country") String country) throws IOException, JAXBException
    {
        String param[]={country,"stock-market"};
        ArrayList<Stocks> result =  this.getStockInfo(StockFactory.getURL(param),StockFactory.BY_COUNTRY);
        return Response.ok(200).entity(this.getFromatedData(result,StockFactory.BY_COUNTRY)).build();
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
    
    private String getFromatedData(ArrayList<Stocks> results, String type) throws JAXBException {
        JAXBContext context = StockFactory.getJAXBContext(type);
        
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        
        StringWriter stringWriter = new StringWriter();
        for(Stocks result:results)
        {
            m.marshal(result, stringWriter);
        }
        return stringWriter.toString();   
    }
    
}
