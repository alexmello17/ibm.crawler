package com.logponto;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Controller
public class WelcomeController {

	@Value("${pathLog:test}")
	private String pathLog;
	
	@Value("${diasLog:test}")
	private int diasLog;
	
	private Set<String> finalLink;
	
	@PostConstruct
	void init(){
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.BEST_SUPPORTED);
		List<String> links2 = firstSearch(driver, "http://www.ibm.com.br");
		List<String> links3 = seccndSearch(driver, links2);
		List<String> links4 = ListUtils.union(links2, links3);
		finalLink = new HashSet<String>(links4);
		for(String s : finalLink){
			System.out.println(s);
		}
	}
	
	public static List<String> firstSearch(WebDriver driver, String url){
		List<String> links2 = new ArrayList<String>();	
		links2.add(url);
			driver.get(url);
			List<WebElement> linksize = driver.findElements(By.tagName("a"));
			// print all the links from webpage
			for (WebElement e : linksize) {
				if(e.getAttribute("href") != null && e.getAttribute("href").contains(".ibm."))
					links2.add(e.getAttribute("href"));
			}
			
			return links2;
	}
	
	public static List<String> seccndSearch(WebDriver driver, List<String> urls){
		List<String> links3 = new ArrayList<String>();
		for(String url : urls){
			links3.add(url);
			driver.get(url);
			List<WebElement> linksize = driver.findElements(By.tagName("a"));
			// print all the links from webpage
			for (WebElement e : linksize) {
				if(e.getAttribute("href") != null && e.getAttribute("href").contains(".ibm."))
					links3.add(e.getAttribute("href"));
			}
		}
		
		return links3;
	}
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model, String url) throws IOException, ParseException {

		return "welcome";
	}
			
}