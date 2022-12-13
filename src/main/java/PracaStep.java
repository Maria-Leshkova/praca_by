import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PracaStep extends PracaPage {

    public PracaStep(WebDriver driver) {
        super(driver);
    }
    public void fillFormAndClickSubmit(String name, String password){
        Util.waiter(3);
        By byXpathButtonGoToLoginPage = By.xpath(xpathButtonGoToLoginPage);
        WebElement elementButtonGoToLoginPage = driver.findElement(byXpathButtonGoToLoginPage);
        elementButtonGoToLoginPage.click();
        Util.waiter(3);
        typeName(name);
        Util.waiter(3);
        typePassword(password);
        selectButtonSubmit();
    }

    public void fillUserNameAndClickSubmit(String name){
        Util.waiter(3);
        By byXpathButtonGoToLoginPage = By.xpath(xpathButtonGoToLoginPage);
        WebElement elementButtonGoToLoginPage = driver.findElement(byXpathButtonGoToLoginPage);
        elementButtonGoToLoginPage.click();
        Util.waiter(3);
        typeName(name);
        selectButtonSubmit();
    }

    public void fillPasswordAndClickSubmit(String password){
        Util.waiter(3);
        By byXpathButtonGoToLoginPage = By.xpath(xpathButtonGoToLoginPage);
        WebElement elementButtonGoToLoginPage = driver.findElement(byXpathButtonGoToLoginPage);
        elementButtonGoToLoginPage.click();
        Util.waiter(3);
        typePassword(password);
        selectButtonSubmit();
    }
}
