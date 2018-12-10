package generic;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Lib implements AutoConst
{
	private static final int FILE = 0;
	public static Workbook wb;
	
	public static String getCellValue(String mysheet,int rowNumber,int columnNumber)
	{
		String cellValue="";
		try 
		{
			
				 wb=WorkbookFactory.create(new FileInputStream(INPUT_PATH));
				 cellValue = wb.getSheet(mysheet).getRow(rowNumber).getCell(columnNumber).toString();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return cellValue;
		
	}
	
	public static void captureScreeenShot(WebDriver driver,String fileName )
	{
		try
		{
			Date d=new Date();
			String d1 = d.toString();
			String currentDate = d1.replaceAll(":", "_");
			TakesScreenshot ts=(TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			File destFile=new File(SNAP_PATH+fileName+"_ _"+currentDate+".png");
			FileUtils.copyFile(srcFile, destFile);
		} 
	catch (IOException e)
		{
			
		}
}
	
	public static int getRowCount(String sheetName)
	{
		int rowCount=0;
		try
		{
			wb=WorkbookFactory.create(new FileInputStream(INPUT_PATH));
			rowCount = wb.getSheet(sheetName).getLastRowNum();
		}
		catch (Exception e)
		{
			
		}
		
		return rowCount;
	}
	
	
	public static String getFormatedDateTime()
	{
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return simpleDate.format(new Date());
	}
	
	public static String getScreenShot(String imageFolderPath)
	{
		String imagePath=imageFolderPath+"/"+getFormatedDateTime()+".png";
		
		try
		{
			Dimension desktopSize=Toolkit.getDefaultToolkit().getScreenSize();
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(desktopSize));
			ImageIO.write(image,"png",new File(imagePath));
		}
		catch(Exception e)
		{
		}
		return imagePath;	
	}
	
	
	public static String getPropertyValue(String filePath,String key)
	{
		String value="";
		Properties ppt=new Properties();
		try{
			ppt.load(new FileInputStream(filePath));
			value=ppt.getProperty(key);
		}
		catch(Exception e)
		{
		}
		return value;
	}
	
	
	public static String getPropertyValue(String propertyName)
	{
		String propertyValue="";
		Properties prop=new Properties();
		try {
			
			prop.load(new FileInputStream(CONFIG_PATH));
			 propertyValue =prop.getProperty(propertyName);
			} 
		catch (Exception e) 
			{
				
			} 
		
		return propertyValue;
	}
	
	
	public static boolean verifyElementIsNotPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,5);
		try
		{
			wait.until(ExpectedConditions.visibilityOf(element));
			return false;
		}
		catch(Exception e)
		{
			return true;
		}
	}
	
	

}






