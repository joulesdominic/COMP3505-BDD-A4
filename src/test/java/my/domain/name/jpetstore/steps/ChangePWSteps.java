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

public class ChangePWSteps {
	
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
	
	@When("I navigate to the change password page")
	    public void i_navigate_to_the_change_password_page() throws Throwable {
	        driver.findElement(By.linkText("My Account")).click();
	    }
	
	@When("I enter {string} in the password field")
	public void i_enter_password(String newpassword) throws Throwable {
	    WebElement passwordField = driver.findElement(By.name("password")); // Use name instead of ID
	    passwordField.clear();
	    passwordField.sendKeys(newpassword);
	}

	@When("I confirm {string} in the repeat password field")
	public void i_confirm_repeat_password(String newpassword) throws Throwable {
	    WebElement confirmPasswordField = driver.findElement(By.name("repeatedPassword"));
	    confirmPasswordField.clear();
	    confirmPasswordField.sendKeys(newpassword);
	}

	@When("I submit the password change form")
	public void i_submit_the_password_change_form() throws Throwable {
	    driver.findElement(By.name("editAccount")).click();
	}
	
	@Then("I should be directed back to main page")
	public void i_should_be_directed_to_main_page() throws Throwable{
		try {
			String currentUrl = driver.getCurrentUrl();
		    assertTrue(currentUrl.equals("http://35.224.152.84:8080/jpetstore/actions/Catalog.action"));
		} catch (Exception e) {
			fail("Not redirected to main page");
		}

	}
	
	@When("I logout")
	public void i_logout() throws Throwable {
	    try {
	        WebElement signOutLink = driver.findElement(By.cssSelector("div#MenuContent a[href*='signoff=']"));
	        signOutLink.click();
	        Thread.sleep(1000); 
	    } catch (Exception e) {
	        fail("Logout failed.");
	    }
	}

	@Then("I should see the Sign In button again")
	public void i_should_see_the_sign_in_button_again() throws Throwable {
	    try {
	        WebElement signInButton = driver.findElement(By.cssSelector("div#MenuContent a[href*='signonForm=']"));
	        assertTrue(signInButton.isDisplayed());
	    } catch (Exception e) {
	        fail("Sign In button not found after logout.");
	    }
	}

	@Then("I should be able to login with {string} and {string}")
	public void i_should_be_able_to_login_with_and(String username, String newpassword) throws Throwable {
	    driver.get("http://35.224.152.84:8080/jpetstore/actions/Account.action?signonForm=");
	    enterCredentials(username, newpassword);
	    try {
	        String currentUrl = driver.getCurrentUrl();
	        assertTrue(currentUrl.equals("http://35.224.152.84:8080/jpetstore/actions/Catalog.action"));
	    } catch (Exception e) {
	        fail("Login with new password failed.");
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
