package org.shaaditest.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public static Logger logger;

	public WebDriver initDriver() throws IOException {

		logger = Logger.getLogger(Base.class.getName());
		PropertyConfigurator.configure("log4j.properties");

		prop = new Properties();
		FileInputStream fis = new FileInputStream("A:\\JAVA\\org.shaaditest.maven\\Configuration\\config.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.contains("chrome")) {
			// Execute Chrome Browser

			System.setProperty("webdriver.chrome.driver", "A:\\Chromedriver\\90\\chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			if (browserName.contains("headless")) {
//				options.addArguments("headless");
//			}
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {

			System.setProperty("webdriver.gecko.driver", "A:\\Drivers\\Gaekodriver\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("IE")) {

			System.setProperty("webdriver.edge.driver", "A:\\Drivers\\Microsoft Edge\\88.0.705.74\\msedgedriver.exe");
			driver = new EdgeDriver();

		}
		return driver;

	}

	public String getScreenshot(String testCasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\screenshots\\" + testCasename + ".png";
		// FileHandler.copy(source, new File(destinationFile));
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

}
