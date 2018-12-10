package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass extends Lib implements AutoConst
{
	public WebDriver driver;
	//set the driver executable path
	static
	{
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		System.setProperty(CHROME_KEY, CHROME_VALUE);
	}
	@BeforeMethod
	public void openApplication()
	{
		
		 driver=new ChromeDriver();
		String url = Lib.getPropertyValue("URL");
		driver.get(url);
		String implicateWait = Lib.getPropertyValue("ImplicateWait");
		//Convert String into Long
		long implicateWaitInLong = Long.parseLong(implicateWait);
		driver.manage().timeouts().implicitlyWait(implicateWaitInLong, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeApplication(ITestResult result)
	{

		if(ITestResult.FAILURE==result.getStatus())//(2==2)
		{
		Lib.captureScreeenShot(driver,result.getName());
		}
		
		driver.close();
	}

}



