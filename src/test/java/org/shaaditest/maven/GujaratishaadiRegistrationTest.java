package org.shaaditest.maven;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shaaditest.pageobject.GujaratishaddiRegistration;
import org.shaaditest.resources.Base;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class GujaratishaadiRegistrationTest extends Base {
	public WebDriver driver;
	String CSV_PATH = "A:\\JAVA\\org.shaaditest.maven\\Configuration\\TestData.csv";
	private CSVReader csvReader;
	String[] csvCell;
	String baseURL;
	String ExpectedCommunityname = "Gujarati";

	@BeforeTest
	public void initBrowser() throws IOException, CsvValidationException {
		driver = initDriver();
		logger.info("Driver Initialized:" + prop.getProperty("browser"));
		driver.manage().window().maximize();
		csvReader = new CSVReader(new FileReader(CSV_PATH));
		while ((csvCell = csvReader.readNext()) != null) {
			this.baseURL = csvCell[1];
			logger.info("Reading Base URL from Cell Value:" + this.baseURL);
		}

	}

	@Test
	public void gujaratiRegistration() throws InterruptedException {
		driver.get(baseURL);
		logger.info("URL Opened by driver:" + driver.getCurrentUrl());
		GujaratishaddiRegistration gr = new GujaratishaddiRegistration(driver);
		logger.info("Test Class Object instantiated.");
		gr.letsbeginbtn().click(); // Click on Login Button
		logger.info("Clicked on Let's Begin Button.");
		Thread.sleep(3000);

		gr.emailId().sendKeys("gujaratiqa002@yopmail.com"); // Enter Email Id
		logger.info("Enetered Email ID");
		gr.passsword().sendKeys("Pass@123457"); // Enter Password
		logger.info("Enetered Password");
		gr.profile_dropdown().click(); // Click on Create Profile for drop down
		logger.info("Clicked on Profile Type Drop Down");

		// Select a drop down value from Profile Type List
		for (WebElement profiletye : gr.profilelist()) {
			if (profiletye.getText().equals("Self")) {
				profiletye.click();
				break;
			}
		}
		logger.info("Profiletype Selected");

		gr.gender().click();
		logger.info("Selected Gender: Male");
		gr.nextbtn().click();
		logger.info("Clicked on Next Button");

		// Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h2[text()='Great! Now some basic details']")));

		// Verify community name and mother tongue
		String ActualCommunityname = gr.communityname().getAttribute("innerText");
		System.out.println("Community Name Displayed: " + ActualCommunityname);
		AssertJUnit.assertEquals(ExpectedCommunityname, ActualCommunityname);
		logger.info("Community on Web Page." + ActualCommunityname);
	}

	@AfterTest
	public void teardown() {
		driver.close();
		logger.info("Web Page Closed");
	}
}
