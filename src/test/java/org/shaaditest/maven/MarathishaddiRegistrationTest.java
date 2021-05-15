package org.shaaditest.maven;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shaaditest.pageobject.MarathishaadiRegistration;
import org.shaaditest.resources.Base;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class MarathishaddiRegistrationTest extends Base {

	public WebDriver driver;
	String CSV_PATH = "A:\\JAVA\\org.shaaditest.maven\\Configuration\\TestData.csv";

	private CSVReader csvReader;
	String[] csvCell;
	String baseURL;
	String ExpectedCommunityname = "Marathi";

	@BeforeTest
	public void initBrowser() throws IOException, CsvValidationException {
		driver = initDriver();
		logger.info("Driver Initialized:" + prop.getProperty("browser"));
		driver.manage().window().maximize();
		csvReader = new CSVReader(new FileReader(CSV_PATH));
		while ((csvCell = csvReader.readNext()) != null) {
			this.baseURL = csvCell[0];
			logger.info("Reading Base URL from Cell Value:" + this.baseURL);
		}
	}

	@Test()
	public void marathiRegistration() throws InterruptedException {

		driver.get(baseURL);
		logger.info("URL Opened by driver:" + driver.getCurrentUrl());
		MarathishaadiRegistration mr = new MarathishaadiRegistration(driver);
		logger.info("Test Class Object instantiated.");
		mr.letsbeginbtn().click(); // Click on Login Button
		logger.info("Clicked on Let's Begin Button.");
		Thread.sleep(3000);

		mr.emailId().sendKeys("marathi101@yopmail.com"); // Enter Email Id
		logger.info("Enetered Email ID");
		mr.passsword().sendKeys("Pass@123457"); // Enter Password
		logger.info("Enetered Password");
		mr.profile_dropdown().click(); // Click on Create Profile for drop down
		logger.info("Clicked on Profile Type Drop Down");

		// Select a drop down value from Profile Type List
		for (WebElement profiletye : mr.profilelist()) {
			if (profiletye.getText().equals("Self")) {
				profiletye.click();
				break;
			}
		}
		logger.info("Profiletype Selected");

		mr.gender().click(); // Select Gender
		logger.info("Selected Gender: Male");
		mr.nextbtn().click(); // Click on Nest Button
		logger.info("Clicked on Next Button");

		// Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h2[text()='Great! Now some basic details']")));

		// Verify community name and mother tongue
		String ActualCommunityname = mr.communityname().getAttribute("innerText");
		System.out.println("Community Name Displayed: " + ActualCommunityname);
		Assert.assertEquals(ExpectedCommunityname, ActualCommunityname);
		logger.info("Community on Web Page." + ActualCommunityname);
	}

	@AfterTest
	public void teardown() {
		driver.close();
		logger.info("Web Page Closed");
	}

}
