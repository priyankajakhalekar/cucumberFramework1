package com.vtiger.stepdefinations;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.common.Xls_Reader;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseStapDefinitions {

	public 	WebDriver driver;
	
	public Properties prop;
	public  Map<String,Map<String,String>> TestData;
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public Scenario scenario;
	public static String TCName;
	public static LoginPage lp;
	public static HomePage hp;
	public static LeadPage lpp;

	
	public void init()
	{
		prop=readProperties();
		 createReport();
		launchApp();
		TestData=readExcelData(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx","Sheet1");	
	}
	

	public void closeApp()
	{
		driver.quit();
	}
	
	
	public void launchApp()
	{
		if(prop.getProperty("Browser").equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		 driver=new  ChromeDriver();
		}
		else if(prop.getProperty("Browser").equals("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		 driver=new  FirefoxDriver();
		}
		else if(prop.getProperty("Browser").equals("edge"))
		{
		WebDriverManager.edgedriver().setup();
		 driver=new  EdgeDriver();
		}

		else
		{
		WebDriverManager.chromedriver().setup();
		 driver=new  ChromeDriver();
		}
		
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		int time=Integer.parseInt(prop.getProperty("GlobalTimeOut"));	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	
	public Properties readProperties()
	{
		Properties prop=new Properties();
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Config/setting.properties");
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public Map<String,Map<String,String>> readExcelData(String path,String sheetName)
	{
		Xls_Reader xr=new Xls_Reader(path);
		int rowcount=xr.getRowCount(sheetName);
		int colmcount=xr.getColumnCount(sheetName);
		
		Map<String,Map<String,String>> Data=new HashMap<String,Map<String,String>>();
		for(int i=2;i<=rowcount;i++)
		{
			Map<String,String> colData=new HashMap<String,String>();
			
			for(int j=1;j<=colmcount;j++)
			{
				colData.put(xr.getCellData(sheetName, j, 1).trim(), xr.getCellData(sheetName, j, i).trim());
			}
			Data.put(xr.getCellData(sheetName, 0, i).trim(), colData);
		}
		
		
		return Data;
		
		
	}
	
	
	public void createReport() 
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport"+str+".html");
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
	}
	
	
	
}
