package com.jisr.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ImdbPage {

	
	WebDriver ldriver;
	public ImdbPage(WebDriver rdriver){
		
		ldriver= rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//table//tbody//td[@class=\"titleColumn\"]")
	@CacheLookup
	List <WebElement> TitleList;
	
	@FindBy(xpath="//table//tbody//td[@class=\"ratingColumn imdbRating\"]")
	@CacheLookup
	List <WebElement> RatingList;
	
	@FindBy(xpath="//table//tbody//td[@class=\"titleColumn\"]//span[@class=\"secondaryInfo\"]")
	@CacheLookup
	List <WebElement> YearList;
	
	
	@FindBy(xpath="//table//tbody//td[@class=\"ratingColumn imdbRating\"]")
	@CacheLookup
	WebElement Rating;
	
	@FindBy(xpath="//table//tbody//td[@class=\"titleColumn\"]//span[@class=\"secondaryInfo\"]")
	@CacheLookup
	WebElement Year;
	
	@FindBy(xpath="//div[@class=\"controls float-right lister-activated\"]//child::span")
	@CacheLookup
	WebElement sortIcon;
	
	@FindBy(name="sort")
	@CacheLookup
	WebElement metricdropdown;
	
	
	public void printElements() {
		for(int i =0; i<TitleList.size(); i++)
		{
			System.out.print("List having titles is " );
			System.out.println(TitleList.get(i).getText());
		}
		for(int i =0; i< RatingList.size();i++)
		{
			System.out.print("List with ratings is " );
			System.out.println(RatingList.get(i).getText());
		}
		for(int i =0; i< YearList.size();i++)
		{
			System.out.print("List with years of release is " );
			System.out.println(YearList.get(i).getText());
			
		}
	
	}
	
	public void select(String metric)
	{
		Select metric_dropdown= new Select(metricdropdown);
		metric_dropdown.selectByVisibleText(metric);
		
		
	}
	
	public List<WebElement> getTitleList(){
		
		return TitleList;
	}
	
	public List<WebElement> getRatingList(){
		return RatingList;
	}
	
	public List<WebElement> getYearList(){
		return YearList;
	}
	
	
	public void clickOnsortIcon() {
		
		sortIcon.click();
	}
}
	

