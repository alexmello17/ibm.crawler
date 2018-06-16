package com.ibm.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Controller
public class WelcomeController {

	@Value("${linkRef:test}")
	private String linkRef;
	
	@Value("${loopProp:test}")
	private int loopProp;
	
	private List<String> finalLink;
	private MongoClient mongo;
	private MongoDatabase database;
	private MongoCollection<Document> linksDbCollection;
	
	@PostConstruct
	void init(){
		
	}
	
	public List<String> firstSearch(WebDriver driver, String url, MongoCollection<Document> linksDbCollection){
		Document filter;
		int loop=1;
		List<String> list = new ArrayList<String>();
		while(loop<loopProp){
			int i=0;
			driver.get(url);
			List<WebElement> linksize = driver.findElements(By.tagName("a"));
			// print all the links from webpage
			for (WebElement e : linksize) {
				if(e.getAttribute("href") != null && e.getAttribute("href").contains(".ibm.")){
					filter = new Document("link"+i++, e.getAttribute("href"));
					list.add(e.getAttribute("href"));
					linksDbCollection.insertOne(filter);
				}
			}
			url = list.get(loop);
			loop++;
		}
		
		return list;
	}
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model, String url) throws IOException, ParseException {
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED);
		mongo = new MongoClient( "localhost" , 27017 );
		database = mongo.getDatabase("linksDb");
		linksDbCollection = database.getCollection("linksDbCollection");
		finalLink = firstSearch(driver, linkRef, linksDbCollection);
		model.put("url", linkRef);
		model.put("finalLink", finalLink);
		mongo.close();
		return "welcome";
	}
			
}