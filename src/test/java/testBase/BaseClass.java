package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // Log4j2
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriver" })
	@Parameters({ "os", "browser" })

	public void setup(String os, String br) throws IOException {
		// Loading config.proeprties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("linux")) 
			{
				capabilities.setPlatform(Platform.LINUX);
			} 
			else if (os.equalsIgnoreCase("mac")) 
			{
				capabilities.setPlatform(Platform.MAC);
			} 
			else 
			{
				System.out.println("No matching os");
				return;
			}

			// browser
			switch (br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default:System.out.println("No matching Browser..");break;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
			//Local execution
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch (br.toLowerCase()) 
			{
			case "chrome":driver = new ChromeDriver();break;
			case "edge":driver = new EdgeDriver();break;
			case "firefox":driver = new FirefoxDriver();break;
			default:System.out.println("Invalid browser");return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.get("http://localhost/opencart/upload/index.php");
		driver.get(p.getProperty("appURL2")); // reading url from properties file.
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriver" })
	public void teardown() {
		driver.quit();
	}

	// generated random data for textarea
	public String randomeString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomeNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomeAlphaNumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
	}

	public String captureScreen(String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
