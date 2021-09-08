package StepDefinitions;

import BaseClass.BaseClass;
import Managers.ConfigFileReader;
import PageObjectModel.DemoApp_LoginPage;
import Utilities.ExcelOperations;
import Utilities.GenericFunctions;
import Utilities.Screenshot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class DemoApplication {
    WebDriver driver;
    private DemoApp_LoginPage demoApp_LoginPage;
    private ExcelOperations exlObj;

    @When("Dummy")
    public void dummy() {

    }

    @Given("Demo Application is Launched")
    public void demo_application_is_launched() throws Exception{
        driver = BaseClass.driver;
        driver.get(ConfigFileReader.properties.getProperty("DemoApplicationURL"));
        Thread.sleep(6000);
        demoApp_LoginPage = new DemoApp_LoginPage();
        demoApp_LoginPage.close_Lauch_Popup();
        Screenshot.WebCapture(driver,"HomePage");
    }

    @When("Traverse to Simple Form Demo")
    public void traverse_to_simple_form_demo() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("Check Single Input Field Functionality {string}")
    public void check_single_input_field_functionality(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("Check Two Input Fields Functionality {int} {int}")
    public void check_two_input_fields_functionality(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("Close The Application")
    public void close_the_application() {
        // Write code here that turns the phrase above into concrete actions

    }

}

