package my.domain.name.jpetstore.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class CreateAccountSteps {
    WebDriver driver;

    @Given("^I open Chrome browser$")
    public void i_open_chrome_browser() {
        System.setProperty("webdriver.chrome.driver", "chrome/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I go to Pet Store Create an Account Page")
    public void i_go_to_pet_store_create_an_account_page() {
        driver.get("http://35.224.152.84:8080/jpetstore/actions/Account.action?newAccountForm=");
    }

    @When("I enter a valid {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, and {string} combination and press Save Account Information")
    public void i_enter_valid_details_and_press_save(String userID, String password, String firstname, String lastname, String email, String phone, String address1, String city, String state, String zip, String country) {
        fillAccountDetails(userID, password, firstname, lastname, email, phone, address1, city, state, zip, country);
    }

    @Then("My account is made with the appropriate information assigned to it")
    public void my_account_is_created() {
        try {
            assertTrue(driver.getCurrentUrl().equals("http://35.224.152.84:8080/jpetstore/actions/Catalog.action"));
        } catch (Exception e) {
            fail();
        } finally {
            driver.quit();
        }
    }

    @When("I enter an invalid {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, and {string} combination and press Save Account Information")
    public void i_enter_invalid_details_and_press_save(String userID, String password, String firstname, String lastname, String email, String phone, String address1, String city, String state, String zip, String country) {
        fillAccountDetails(userID, password, firstname, lastname, email, phone, address1, city, state, zip, country);
    }

    @Then("I should see an error message indicating invalid input")
    public void i_should_see_an_error_message() {
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
        driver.findElement(By.name("username")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("repeatedPassword")).sendKeys(password);
        driver.findElement(By.name("account.firstName")).sendKeys(firstname);
        driver.findElement(By.name("account.lastName")).sendKeys(lastname);
        driver.findElement(By.name("account.email")).sendKeys(email);
        driver.findElement(By.name("account.phone")).sendKeys(phone);
        driver.findElement(By.name("account.address1")).sendKeys(address1);
        driver.findElement(By.name("account.city")).sendKeys(city);
        driver.findElement(By.name("account.state")).sendKeys(state);
        driver.findElement(By.name("account.zip")).sendKeys(zip);
        driver.findElement(By.name("account.country")).sendKeys(country);
        driver.findElement(By.name("newAccount")).click();
    }
}
