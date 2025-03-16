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

public class LoginSteps {
	
	WebDriver driver;

	@Given("^I open Chrome browser$")
	public void i_open_Chrome_browser() throws Throwable {
	 System.setProperty("webdriver.chrome.driver", "chrome/chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();
	}
	
	@Given("I go to Pet Store Login page")
	public void i_go_to_pet_store_login_page() throws Throwable{
	    driver.get("http://35.224.152.84:8080/jpetstore/actions/Account.action;jsessionid=579C88EA64842A44C4BF5D3B9A561500?signonForm=");
	}
	
	@When("I enter valid {string} and {string} combination")
	public void i_enter_valid_and_combination(String string, String string2) throws Throwable{
		enterCredentials(string, string2);
	}
	
	@When("I enter invalid {string} and {string} combination")
	public void i_enter_invalid_and_combination(String string, String string2) throws Throwable{
	    enterCredentials(string, string2);
	}
	
	@Then("I should be able to login successfully")
	public void i_should_be_able_to_login_successfully() throws Throwable{
		try {
			String currentUrl = driver.getCurrentUrl();
		    assertTrue(currentUrl.equals("http://35.224.152.84:8080/jpetstore/actions/Catalog.action"));
		} catch (Exception e) {
			fail();
		} finally {
			driver.quit();
		}

	}
	@Then("I should see an error message")
	public void i_should_see_an_error_message() throws Throwable{
		try {
            WebElement errorMessage = driver.findElement(By.cssSelector("div#Content li"));
            String actualErrorMessage = errorMessage.getText();
            String expectedErrorMessage = "Invalid username or password. Signon failed.";

            assertTrue("Error message is not displayed or incorrect", 
                       errorMessage.isDisplayed() && actualErrorMessage.equals(expectedErrorMessage));
        } catch (Exception e) {
            fail("Error message not displayed or incorrect: " + e.getMessage());
        } finally {
            driver.quit();
        }
	}
	
	private void enterCredentials(String username, String password) throws Throwable{
		driver.findElement(By.name("username")).sendKeys(username);
		
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
		passwordField.sendKeys(password);
		
		driver.findElement(By.cssSelector("input[type='submit'][value='Login']")).click();
	}


}
