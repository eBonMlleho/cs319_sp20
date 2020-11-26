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

public class hw7task1 {
	static WebDriver driver;

//	Change your selenium driver path here
	static String pathChromeDriver = "D:\\\\graduate school\\\\coms319\\\\lab\\\\chromedriver_win32\\\\chromedriver.exe";
	static String pathLoginPage = "D:\\graduate school\\coms319\\Wen_hw7\\HW7-Files\\HW7-Files\\task1\\validation.html";

	String txtFirstName = "txtFirstName";
	String txtLastName = "txtLastName";
//	String Gender = "Gender";
//	String State = "State";

	String Email = "txtEmail";
	String Phone = "txtPhone";
	String Address = "txtAddress";
	String txtFinalResult = "labelNotifytxtFinalResult";

	String btnLogin = "btnValidate";

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
	public void loginSuccessTest() throws InterruptedException {
		driver.get(pathLoginPage);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='" + txtFirstName + "']")).sendKeys("Zhanghao");
		driver.findElement(By.xpath("//input[@id='" + txtLastName + "']")).sendKeys("Wen");

		Select gender = new Select(driver.findElement(By.name("selectGender")));
		gender.selectByIndex(1);
		Select state = new Select(driver.findElement(By.name("selectState")));
		state.selectByIndex(1);
		driver.findElement(By.xpath("//input[@id='" + Email + "']")).sendKeys("Wen@iastate.edu");
		driver.findElement(By.xpath("//input[@id='" + Phone + "']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='" + Address + "']")).sendKeys("Wen,IA");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='" + btnLogin + "']")).click();

		String strMessage = driver.findElement(By.xpath("//label[@id='" + txtFinalResult + "']")).getText();
		assertEquals(strMessage, "OK");
	}

	@Test
	public void loginFailedTest() throws InterruptedException {
		driver.get(pathLoginPage);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='" + txtFirstName + "']")).sendKeys("Zhanghao");
		driver.findElement(By.xpath("//input[@id='" + txtLastName + "']")).sendKeys("Wen");

		Select gender = new Select(driver.findElement(By.name("selectGender")));
		gender.selectByIndex(1);
		Select state = new Select(driver.findElement(By.name("selectState")));
		state.selectByIndex(1);
		driver.findElement(By.xpath("//input[@id='" + Email + "']")).sendKeys("Wen@iastate.edu");
		driver.findElement(By.xpath("//input[@id='" + Phone + "']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='" + Address + "']")).sendKeys("Wen,IA");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='" + btnLogin + "']")).click();

		String strMessage = driver.findElement(By.xpath("//label[@id='" + txtFinalResult + "']")).getText();
		assertEquals(strMessage, "Error");
	}
}
