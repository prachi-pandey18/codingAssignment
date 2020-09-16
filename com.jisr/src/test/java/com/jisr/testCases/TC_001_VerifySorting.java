package com.jisr.testCases;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jisr.pageObjects.ImdbPage;
import com.jisr.utilities.readConfig;

public class TC_001_VerifySorting  {
	
	
	readConfig config= new readConfig();
	public String baseUrl= config.getApplicationUrl();
	
	public static WebDriver driver;
	
	public static Logger logger;
	
	ImdbPage testPage = new ImdbPage(driver);
	
	

	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger=Logger.getLogger("jisr");
		PropertyConfigurator.configure("log4j.properties");
		

		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",config.getChromePath() );
			driver= new ChromeDriver();
		}
		if (br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",config.getFireFoxPath() );
			driver = new FirefoxDriver();
		}
		
		driver.get(baseUrl);
		
		
	}
	
	@Test
	public void fetchLists() {
		
		testPage = new ImdbPage(driver);
		
		testPage.printElements();
		
	}
	
	@Test
	public void sortedMovieLists() {
		

		List<WebElement> defaultList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
		testPage.select("Ranking");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> sortedRankingList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
		Assert.assertTrue(defaultList.equals(sortedRankingList));
		
		testPage.select("IMDb Rating");
		wait=new WebDriverWait(driver, 30);
		List<WebElement> sortedRatingList=driver.findElements(By.xpath("//table//tbody//td[@class=\"ratingColumn imdbRating\"]"));
		Assert.assertFalse(defaultList.equals(sortedRatingList));
		
		
		testPage.select("Release Date");
		wait=new WebDriverWait(driver, 30);
		List<WebElement> sortedYearList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]//span[@class=\"secondaryInfo\"]"));
		Assert.assertFalse(defaultList.equals(sortedYearList));
		
		testPage.select("Number of Ratings");
		wait=new WebDriverWait(driver, 30);
		List<WebElement> sortedNumOfRatingList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
		Assert.assertFalse(defaultList.equals(sortedNumOfRatingList));

		testPage.select("Your Rating");
		wait=new WebDriverWait(driver, 30);
		List<WebElement> sortedYourRatingList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
		Assert.assertTrue(defaultList.equals(sortedYourRatingList));
		
		
	}
	@Test
	public void verifySort() {
		
		testPage.select("Release Date");
		WebDriverWait wait=new WebDriverWait(driver, 30);
		List<WebElement> sortedYearDescList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]//span[@class=\"secondaryInfo\"]"));
		testPage.clickOnsortIcon();
		wait = new WebDriverWait(driver, 30);
		List<WebElement> sortedYearAscList=driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]//span[@class=\"secondaryInfo\"]"));
		Assert.assertFalse(sortedYearDescList.equals(sortedYearAscList));
	}
	
	
//	@Test
//	public void creatingList() {
//		
//		List<WebElement> titleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		
//		for(int i =0; i<titleElements.size(); i++) {
//			System.out.print("Name of elements is " );
//			System.out.println(titleElements.get(i).getText());
//		}
//		 
//		List<WebElement> ratingElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"ratingColumn imdbRating\"]"));
//		
//		for(int i =0; i<ratingElements.size(); i++) {
//			System.out.print("Rating of elements is ");
//			System.out.println(ratingElements.get(i).getText());
//		}
//		
//		List<WebElement> yearElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]//span[@class=\"secondaryInfo\"]"));
//		
//		for(int i =0; i<ratingElements.size(); i++) {
//			System.out.print("Year of elements is ");
//			System.out.println(yearElements.get(i).getText());
//		}
//		
//	}
//	
//	@Test
//	public void movieListInSortingMetricRanking() throws InterruptedException {
//		
//		
//		
//		boolean isEqual;
//		List<WebElement> defaulttitleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		Select dropdown = new Select(driver.findElement(By.name("sort")));
//		dropdown.selectByVisibleText("Ranking");
//		
//		WebElement sortIcon =driver.findElement(By.xpath("//div[@class=\"controls float-right lister-activated\"]//child::span"));
//		sortIcon.click();
//		
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		
//		List<WebElement> sortRanking = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		for(int i =0; i<sortRanking.size(); i++) {
//			System.out.print("Name of elements is " );
//			System.out.println(sortRanking.get(i).getText());
//		}
//		
//		isEqual = sortRanking.equals(defaulttitleElements);
//		
//		System.out.println(isEqual);
//		
//		
//	}
//	@Test
//	public void movieListInSortingMetricImdBRanking() throws InterruptedException {
//	
//		boolean isEqual;
//		List<WebElement> defaulttitleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		Select dropdown = new Select(driver.findElement(By.name("sort")));
//		dropdown.selectByVisibleText("IMDb Rating");
//		
//		WebElement sortIcon =driver.findElement(By.xpath("//div[@class=\"controls float-right lister-activated\"]//child::span"));
//		sortIcon.click();
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		
//		List<WebElement> sortImdBRating = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		for(int i =0; i<sortImdBRating.size(); i++) {
//			System.out.print("Name of elements is " );
//			System.out.println(sortImdBRating.get(i).getText());
//		}
//	
//		isEqual = sortImdBRating.equals(defaulttitleElements);
//		System.out.println(isEqual);
//	
//	}
//	
//	@Test
//	public void movieListInSortingMetricReleaseDate() throws InterruptedException {
//		
//		boolean isEqual;
//		List<WebElement> defaulttitleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		Select dropdown = new Select(driver.findElement(By.name("sort")));
//		dropdown.selectByVisibleText("Release Date");
//		
//		WebElement sortIcon =driver.findElement(By.xpath("//div[@class=\"controls float-right lister-activated\"]//child::span"));
//		sortIcon.click();
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		
//		List<WebElement> sortReleaseDate = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		for(int i =0; i<sortReleaseDate.size(); i++) {
//			System.out.print("Name of elements is " );
//			System.out.println(sortReleaseDate.get(i).getText());
//		}
//		
//		isEqual = sortReleaseDate.equals(defaulttitleElements);
//		System.out.println(isEqual);
//		
//	}
//	
//	@Test
//	public void movieListInSortingMetricNumberOfRatings() throws InterruptedException {
//		
//		boolean isEqual;
//		List<WebElement> defaulttitleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		Select dropdown = new Select(driver.findElement(By.name("sort")));
//		dropdown.selectByVisibleText("Release Date");
//		
//		WebElement sortIcon =driver.findElement(By.xpath("//div[@class=\"controls float-right lister-activated\"]//child::span"));
//		sortIcon.click();
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		
//		List<WebElement> sortRatingNumber = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		for(int i =0; i<sortRatingNumber.size(); i++) {
//			System.out.print("Name of elements is " );
//			System.out.println(sortRatingNumber.get(i).getText());
//		}
//		
//		isEqual = sortRatingNumber.equals(defaulttitleElements);
//		System.out.println(isEqual);
//		
//	}
//	
//	@Test
//	public void movieListInSortingMetricYourRating() throws InterruptedException {
//		
//		boolean isEqual;
//		List<WebElement> defaulttitleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		Select dropdown = new Select(driver.findElement(By.name("sort")));
//		dropdown.selectByVisibleText("Release Date");
//		
//		WebElement sortIcon =driver.findElement(By.xpath("//div[@class=\"controls float-right lister-activated\"]//child::span"));
//		sortIcon.click();
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		
//		List<WebElement> sortYourRating = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		for(int i =0; i<sortYourRating.size(); i++) {
//			System.out.print("Name of elements is " );
//			System.out.println(sortYourRating.get(i).getText());
//		}
//		
//		isEqual = sortYourRating.equals(defaulttitleElements);
//		System.out.println(isEqual);
//		
//	}
//	@Test
//	public void SortAscendingDesc() {
//		boolean isEqual;
//		
//		//List<WebElement> defaulttitleElements = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		Select dropdown = new Select(driver.findElement(By.name("sort")));
//		dropdown.selectByVisibleText("Release Date");
//		WebElement sortIcon =driver.findElement(By.xpath("//div[@class=\"controls float-right lister-activated\"]//child::span"));
//		sortIcon.click();
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		List<WebElement> ascElementsImdb = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		sortIcon.click();
//		wait = new WebDriverWait(driver, 30);
//		List<WebElement> descElementsImdb = driver.findElements(By.xpath("//table//tbody//td[@class=\"titleColumn\"]"));
//		
//		isEqual = ascElementsImdb.equals(descElementsImdb);
//		System.out.println(isEqual);
//		
//	
//		
//		
//		
//	}
//	
//	
//	
	
	
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	
	
	
	
	
	
	
	

}
