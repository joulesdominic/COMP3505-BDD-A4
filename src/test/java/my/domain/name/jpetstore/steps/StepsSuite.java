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

public class StepsSuite {
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
	
	@Given("I go to Pet Store Create an Account Page")
	public void i_go_to_pet_store_account_page() throws Throwable{
	    driver.get("http://35.224.152.84:8080/jpetstore/actions/Account.action?newAccountForm=");
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
            fail();
        } finally {
            driver.quit();
        }
	}
	
	@When("I logout")
	public void i_logout() throws Throwable{
		try {
            WebElement signOutLink = driver.findElement(By.cssSelector("div#MenuContent a[href*='signoff=']"));
            signOutLink.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            fail();
        }
	}
	
	@Then("I should see the Sign In button again")
	public void i_should_see_the_sign_in_button_again() throws Throwable{
	    try {
	    	WebElement signInButton = driver.findElement(By.cssSelector("div#MenuContent a[href*='signonForm=']"));
		    assertTrue(signInButton.isDisplayed());
	    } catch (Exception e) {
	    	fail();
	    }
	    finally {
	    	driver.quit();
	    }
	}
	
	@Then("I should see the Sign In button again for password")
	public void i_should_see_the_sign_in_button_again_for_password() throws Throwable{
	    	WebElement signInButton = driver.findElement(By.cssSelector("div#MenuContent a[href*='signonForm=']"));
		    assertTrue(signInButton.isDisplayed());
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
    public void i_should_see_an_error_message_indicating_invalid_input() throws InterruptedException {
        try {
            WebElement errorMessage = driver.findElement(By.className("messages"));
            assertTrue(errorMessage.isDisplayed());
        } catch (Exception e) {
            fail();
        } finally {
            driver.quit();
        }
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
	
	private void enterCredentials(String username, String password) throws Throwable{
		driver.findElement(By.name("username")).sendKeys(username);
		
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
		passwordField.sendKeys(password);
		
		driver.findElement(By.cssSelector("input[type='submit'][value='Login']")).click();
	}
}
