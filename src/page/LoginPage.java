package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	@FindBy(id="username")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="subbtn")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);		
	}
	public void SetUserName(String un)
	{
		//userName.clear();
		userName.sendKeys(un);
	}
	public void setPassWord(String pw)
	{
//		password.clear();
		password.sendKeys(pw);
	}
	public void clickOnLoginButton()
	{
		loginButton.click();
	}


}
