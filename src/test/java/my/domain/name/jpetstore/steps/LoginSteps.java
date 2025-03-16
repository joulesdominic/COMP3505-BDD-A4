package my.domain.name.jpetstore.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	WebDriver driver;

	@Given("^I open Chrome browser$")
	public void i_open_Chrome_browser() throws Throwable {
	 System.setProperty("webdriver.chrome.driver", "chrome/chromedriver");
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();
	}
	@Given("I go to Pet Store Login page")
	public void i_go_to_pet_store_login_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I enter valid {string} and {string} combination")
	public void i_enter_valid_and_combination(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I should be able to login successfully")
	public void i_should_be_able_to_login_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
