package catchPayment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Catch {

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\VENKATESHANH\\Documents\\Drivers_Java\\chromedriver_win32\\chromedriver.exe");
		
		//To disable password save notification
		ChromeOptions chOption = new ChromeOptions();
        chOption.addArguments("--disable-extensions");
        chOption.addArguments("test-type");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chOption.setExperimentalOption("prefs", prefs);
		driver =new ChromeDriver(chOption);
		baseUrl = "https://www.catch.com.au/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		driver.get(baseUrl + "/");
		
		
		WebElement pushNotificationNotNowBtn = explicitWaitElement(By.xpath("//*[@id='mainContentBlock']/div[1]/div/div[3]/a"));
		
		if(pushNotificationNotNowBtn.isDisplayed()==true)			
		{
			pushNotificationNotNowBtn.click();
		}			
		else			
		{
			System.out.println("Push Notifiation didn't appear.!!");
		}
		explicitWaitElement(By.id("search-input")).clear();
		explicitWaitElement(By.id("search-input")).sendKeys("iphone");
		//search button for iphone
		explicitWaitElement(By.cssSelector("button.btn.medium")).click();
		//link for iphone
		explicitWaitElement(By.cssSelector("a.js-product-link.product--title > div")).click();
        //for addtocart for iphone
		explicitWaitElement(By.xpath("//*[@id='form-cart-add']/section[3]/div/div[1]/button")).click();
		explicitWaitElement(By.xpath("/html/body/header/div[2]/div/nav/div[1]/span")).click();		
		//Mouse hover for pets
		WebElement pets = explicitWaitElement(By.xpath("/html/body/header/div[3]/div/div/nav/div[1]/nav/a[10]"));
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.moveToElement(pets).moveToElement(explicitWaitElement(By.xpath("/html/body/header/div[3]/div/div/nav/div[2]/div[10]/div/div[1]/div[3]/nav[1]/a[5]"))).click().build().perform();
		//pet item selection
		explicitWaitElement(By.xpath("//*[@id='379822']/div/a[2]/div")).click();
		//pet item add to cart button
		explicitWaitElement(By.xpath("//*[@id='form-cart-add']/section[3]/div/div[1]/button/span")).click();
		//cart button
		explicitWaitElement(By.xpath("//*[@id='mini-cart']/a/div[1]")).click();
		//pay securely now button
		explicitWaitElement(By.xpath("//*[@id='checkout']")).click();
		//username and password
		explicitWaitElement(By.id("login_email")).sendKeys("harini.venkateshan@accesshq.com");
		explicitWaitElement(By.id("login_password")).sendKeys("testing123");
		explicitWaitElement(By.id("button-login")).click();
		//credit card entry
		explicitWaitElement(By.id("new-card-name")).clear();
		explicitWaitElement(By.id("new-card-name")).sendKeys("test");
		explicitWaitElement(By.id("new-card-number")).clear();
		explicitWaitElement(By.id("new-card-number")).sendKeys("4111 1111 1111 1111");
		explicitWaitElement(By.id("new-card-month")).clear();
		explicitWaitElement(By.id("new-card-month")).sendKeys("07");
		explicitWaitElement(By.id("new-card-year")).clear();
		explicitWaitElement(By.id("new-card-year")).sendKeys("24");
		explicitWaitElement(By.id("new-card-cvv")).clear();
		explicitWaitElement(By.id("new-card-cvv")).sendKeys("045");
		explicitWaitElement(By.xpath("//*[@id='checkout']")).click();	
		
		//Adding assertion
		String ExpectedResult = "Your credit card details could not be validated, please use another card.";
		String ActualResult = explicitWaitElement(By.xpath("//*[@id='flash-messages']/div")).getText();
		String actualResultFinal = ActualResult.substring(2);
		Assert.assertEquals(actualResultFinal, ExpectedResult,  "The test case failed");
	}

	
	//Method to handle the pop up notification
	/*private void toclick(WebElement e) {

		List<WebElement> findElements = driver.findElements(By.className("js-not-now-button"));
		for (WebElement webElement : findElements) {
			try {
				webElement.click();
			} catch (ElementNotVisibleException enfe) {
				// do nothing and proceed to code
			}
		}
		try {
			e.click();
		} catch (ElementNotVisibleException ex) {
			// do nothing and proceed to code
		}
	}*/

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	// Synchronization issue
	public WebElement explicitWaitElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement webelement = wait.until(ExpectedConditions
				.presenceOfElementLocated(locator));

		return webelement;
	}

	// private boolean isElementPresent(By by) {
	// try {
	// driver.findElement(by);
	// return true;
	// } catch (NoSuchElementException e) {
	// return false;
	// }
	// }
	//
	// private boolean isAlertPresent() {
	// try {
	// driver.switchTo().alert();
	// return true;
	// } catch (NoAlertPresentException e) {
	// return false;
	// }
	// }
	//
	// private String closeAlertAndGetItsText() {
	// try {
	// Alert alert = driver.switchTo().alert();
	// String alertText = alert.getText();
	// if (acceptNextAlert) {
	// alert.accept();
	// } else {
	// alert.dismiss();
	// }
	// return alertText;
	// } finally {
	// acceptNextAlert = true;
	// }
	// }	
}
