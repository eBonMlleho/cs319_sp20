package lab07;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class hw7task2 {
	static WebDriver driver;

//	Change your selenium driver path here
	static String pathChromeDriver = "D:\\\\graduate school\\\\coms319\\\\lab\\\\chromedriver_win32\\\\chromedriver.exe";
	static String pathLoginPage = "file:///D:/graduate%20school/coms319/Wen_hw7/HW7-Files/HW7-Files/task2/viewCars.html";

	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}

	@Test
	public void YearSuccessTest() throws InterruptedException {
		driver.get(pathLoginPage);
		driver.manage().window().maximize();

		Thread.sleep(1000);
		driver.findElement(By.id("lblYearColumn")).click();

		String strMessage = driver.findElement(By.xpath("//table/tbody/tr[10]/td[3]")).getText();
		assertEquals(strMessage, "2008");
	}

	@Test
	public void ManufacturerSuccessTest() throws InterruptedException {
		driver.get(pathLoginPage);
		driver.manage().window().maximize();

		Thread.sleep(1000);
		Select manu = new Select(driver.findElement(By.name("selectManufacturer")));
		manu.selectByIndex(1);

		String strMessage = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).getText();
		assertEquals(strMessage, "Toyota");

	}
}
