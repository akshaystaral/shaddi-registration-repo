package org.shaaditest.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarathishaadiRegistration {

	WebDriver driver;

	public MarathishaadiRegistration(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[normalize-space()=\"Let's Begin\"]")
	private WebElement letsbeginbtn;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailId;

	@FindBy(xpath = "//input[@name='password1']")
	private WebElement passsword;

	@FindBy(xpath = "//div[@class='Dropdown-placeholder']")
	private WebElement profile_dropdown;

	@FindBy(xpath = "//div[@class='Dropdown-option' and @role='option']")
	private List<WebElement> profilelist;

	@FindBy(xpath = "//input[@id='gender_male']")
	private WebElement gender;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement nextbtn;

	@FindBy(xpath = "//div[@class='Dropdown-placeholder is-selected'][normalize-space()='Marathi']")
	private WebElement communityname;

	public WebElement letsbeginbtn() {
		return letsbeginbtn;
	}

	public WebElement emailId() {
		return emailId;
	}

	public WebElement passsword() {
		return passsword;
	}

	public WebElement profile_dropdown() {
		return profile_dropdown;
	}

	public List<WebElement> profilelist() {
		return profilelist;
	}

	public WebElement gender() {
		return gender;
	}

	public WebElement nextbtn() {
		return nextbtn;
	}

	public WebElement communityname() {
		return communityname;
	}

}