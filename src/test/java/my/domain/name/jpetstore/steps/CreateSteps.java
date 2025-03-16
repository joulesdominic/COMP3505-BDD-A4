package my.domain.name.jpetstore.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateSteps {
	WebDriver driver;

	@Given("^I open Chrome browser$")
	public void i_open_Chrome_browser() throws Throwable {
	 System.setProperty("webdriver.chrome.driver", "chrome/chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();
	}
	
	@Given("I go to Pet Store Create an Account Page")
	public void i_go_to_pet_store_login_page() throws Throwable{
	    driver.get("http://35.224.152.84:8080/jpetstore/actions/Account.action?newAccountForm=");
	}
	
	@When("I enter a valid {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, and {string} combination and press Save Account Information")
    public void i_enter_valid_details_and_press_save(String userID, String password, String firstname, String lastname, String email, String phone, String address1, String city, String state, String zip, String country) {
        fillAccountDetails(userID, password, firstname, lastname, email, phone, address1, city, state, zip, country);
    }
	 @When("I enter an invalid {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, and {string} combination and press Save Account Information")
	    public void i_enter_invalid_details_and_press_save(String userID, String password, String firstname, String lastname, String email, String phone, String address1, String city, String state, String zip, String country) {
	        fillAccountDetails(userID, password, firstname, lastname, email, phone, address1, city, state, zip, country);
	    }
	
	@Then("My account is made with the appropriate information assigned to it")
    public void my_account_is_created() {
		try {
            WebElement errorMessage = driver.findElement(By.cssSelector("div#Content li"));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "One or more required filed does not have a value.";

            assertTrue("Error message is not displayed or incorrect", 
                       errorMessage.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage));
        } catch (Exception e) {
            fail();
        } finally {
            driver.quit();
        }
    }
	@Then("I should see an error message indicating invalid input")
    public void i_should_see_an_error_message() throws InterruptedException {
        try {
            WebElement errorMessage = driver.findElement(By.className("messages"));
            assertTrue(errorMessage.isDisplayed());
        } catch (Exception e) {
            fail();
        } finally {
            driver.quit();
        }
    }
	
	private void fillAccountDetails(String userID, String password, String firstname, String lastname, String email, String phone, String address1, String city, String state, String zip, String country) {
		driver.findElement(By.xpath("//td[text()='User ID:']/following-sibling::td/input")).sendKeys(userID);
		driver.findElement(By.xpath("//td[text()='New password:']/following-sibling::td/input")).sendKeys(password);
		driver.findElement(By.xpath("//td[text()='Repeat password:']/following-sibling::td/input")).sendKeys(password);
		driver.findElement(By.xpath("//td[text()='First name:']/following-sibling::td/input")).sendKeys(firstname);
		driver.findElement(By.xpath("//td[text()='Last name:']/following-sibling::td/input")).sendKeys(lastname);
		driver.findElement(By.xpath("//td[text()='Email:']/following-sibling::td/input")).sendKeys(email);
		driver.findElement(By.xpath("//td[text()='Phone:']/following-sibling::td/input")).sendKeys(phone);
		driver.findElement(By.xpath("//td[text()='Address 1:']/following-sibling::td/input")).sendKeys(address1);
		driver.findElement(By.xpath("//td[text()='City:']/following-sibling::td/input")).sendKeys(city);
		driver.findElement(By.xpath("//td[text()='State:']/following-sibling::td/input")).sendKeys(state);
		driver.findElement(By.xpath("//td[text()='Zip:']/following-sibling::td/input")).sendKeys(zip);
		driver.findElement(By.xpath("//td[text()='Country:']/following-sibling::td/input")).sendKeys(country);
		WebElement saveAccountButton = driver.findElement(By.cssSelector("div#Catalog input[type='submit'][value='Save Account Information']"));
        saveAccountButton.click();
    }
}
