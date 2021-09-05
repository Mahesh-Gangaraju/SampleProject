package PageObjectModel;

import BaseClass.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DemoApp_LoginPage {

    public DemoApp_LoginPage() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    @FindBy( how = How.XPATH , using = "//a[text()='No, thanks!']")
    WebElement launch_PopUp;

    public void close_Lauch_Popup() throws Exception {
        launch_PopUp.click();
    }

}
