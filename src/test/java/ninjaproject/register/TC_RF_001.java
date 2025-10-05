package ninjaproject.register;

//import java.sql.Date;
import java.time.Duration;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {
	@Test

	public  void verifyRegisterWithMandatoryFields() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(18));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("JawaD");
		driver.findElement(By.id("input-lastname")).sendKeys("Aziz");
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("01234556789");
		driver.findElement(By.id("input-password")).sendKeys("Mardan");
		driver.findElement(By.id("input-confirm")).sendKeys("Mardan");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), expectedHeading);
		String properDetailsOne = "Congratulations! Your new account has been successfully created!";
		String properDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetailsThree= "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetailsFour= "contact us";
		String expectedProperDetails = driver.findElement(By.id("content")).getText();
		Assert.assertTrue(expectedProperDetails.contains(properDetailsOne));
		Assert.assertTrue(expectedProperDetails.contains(properDetailsTwo));
		Assert.assertTrue(expectedProperDetails.contains(properDetailsThree));
		Assert.assertTrue(expectedProperDetails.contains(properDetailsFour));
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		driver.quit();
				

	}
	
	public String generateNewEmail() {
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+"@gmail.com";
	}

}
