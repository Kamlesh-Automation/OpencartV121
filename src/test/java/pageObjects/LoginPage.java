package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
		public LoginPage(WebDriver driver)
		{
			super(driver);
		}
		
		@FindBy(xpath="//input[@id='input-email']")
		WebElement userEmail;
		
		@FindBy(xpath="//input[@id='input-password']")
		WebElement userPassword;
		
		@FindBy(xpath="//input[@value='Login']")
		WebElement loginBtn;
		
		@FindBy(xpath="//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
		WebElement forgotpasslink;
		
		public void setUserEmail(String email)
		{
			userEmail.sendKeys(email);
		}
		public void setUserPassword(String pwd)
		{
			userPassword.sendKeys(pwd);
		}
		public void loginButton()
		{
			loginBtn.click();
		}
		
		public void forgotLink()
		{
			forgotpasslink.click();
		}
}
