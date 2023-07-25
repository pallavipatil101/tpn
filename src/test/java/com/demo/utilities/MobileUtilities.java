package com.demo.utilities;

import org.openqa.selenium.WebElement;

public class MobileUtilities {
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void sendkeys(WebElement element, String value) {
		element.sendKeys(value);

	}

}
