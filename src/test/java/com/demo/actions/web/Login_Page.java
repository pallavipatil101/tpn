package com.demo.actions.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class Login_Page extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();

	@FindBy(id = "input_0")
	private WebElement Email;

	@FindBy(id = "input_1")
	private WebElement Password;

	@FindBy(xpath = "//*[@class=\"text-left mt-10\"]/button")
	private WebElement Login_btn;

	@FindBy(xpath = "//*[@class = 'ng-isolate-scope' ]/div/input")
	private List<WebElement> otp;
	
	@FindBy(css = ".btn")
	private WebElement verifybtn;
	
	@FindBy(xpath = "//span[contains(text(),'Admin')]")
	private WebElement Login_Username;

	@FindBy(xpath = "//*[@id=\"scrollview\"]/div/div/div/div[1]/div/div[3]/div[1]/div[1]/span/div/i")
	private WebElement arrowicon;

	@FindBy(xpath = "//span[contains(text(),'Log Out')]")
	private WebElement logout_btn;

	@FindBy(xpath = "//span[contains(text(),'Log Out')]")
	private WebElement confirmation_logout_btn;

	@FindBy(xpath = "//div[contains(text(),'Recent logins')]")
	private WebElement recentLogin;

	@FindBy(xpath = "//*[@id=\"loginform\"]/div[1]/div[2]")
	private WebElement errormessage;

	@FindBy(css = ".md-icon-button.md-button.md-ink-ripple")
	private WebElement close_popup;
	
	public WebElement recent_login_disply() {
		return recentLogin;
	}

	public void click_arrowicon() {
		utilities.click(arrowicon);

	}

	public void click_logout_btn() {
		utilities.click(logout_btn);

	}

	public void click_conformation_Logout_btn() {
		utilities.click(confirmation_logout_btn);

	}

	public Login_Page(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public void enter_Invalid_Password(String password) {
		utilities.sendkeys(Password, password);
	}

	public void enter_Valid_Email(String username) throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
//		utilities.sendkeys(Email, properties.getProperty("user_name") );
		utilities.sendkeys(Email, username );
		//utilities.verify_true(Email.getText().equals(username));
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$Username: "+Email.getText());
	}

	public WebElement verify_user_name() {
		return Login_Username;
	}

	public String validate_Error_Message() {
		return utilities.getText(errormessage);
	}
	
	public void enter_Valid_Password(String password) throws Exception {
		properties = readPropertiesFile(System.getProperty("user.dir") + "/src/test/java/com/demo/properties/testdata.properties");
		utilities.sendkeys(Password, password);
		//utilities.verify_true(Password.getText().equals(password));
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$Username: "+Password.getText());

	}

	
	public void click_login_btn() {
		utilities.click(Login_btn);
		utilities.explicitwait(Email);
		utilities.click(close_popup);
	}

	public void enter_otp() {
		for (WebElement element : otp){
			utilities.sendkeys(element, element.getAttribute("Placeholder"));
		}
	}
	
	public void verify_otp() {
		utilities.click(verifybtn);
	}
	
	public void implicit_Wait() {
		utilities.implicitWait();
	}

}
