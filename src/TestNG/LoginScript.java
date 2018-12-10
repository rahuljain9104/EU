package TestNG;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.BaseClass;
import generic.Lib;
import page.LoginPage;

@Listeners(generic.TestListener.class)
public class LoginScript extends BaseClass
{

	
	@Test
	public void loginTest() throws InterruptedException
	{
		LoginPage login =new LoginPage(driver);
		Thread.sleep(3000);
		String un=Lib.getPropertyValue("USERNAME");
		String pwd=Lib.getPropertyValue("PASSWARD");
		login.SetUserName(un);
		Thread.sleep(3000);
		login.setPassWord(pwd);
		Thread.sleep(10000);
		login.clickOnLoginButton();
		//Assert.fail();
		
		
	}
}
