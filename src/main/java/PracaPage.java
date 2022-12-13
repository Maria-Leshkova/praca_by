import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PracaPage {

    protected WebDriver driver;
    public final String URL = "https://praca.by";
    public String xpathFooter = "//html/body/footer/div/div[2]/div[1]/div[2]/p[3]";
    public final String FOOTER_COPY = "Режим работы сайта — круглосуточно. E-mail ООО «ПРАЦА БАЙ» editor@praca.by";
    public String xpathButtonGoToLoginPage = "//*[@class='ctrl__top-login login-modal btn btn_dark-red js-open-login-modal']";
    private String xpathInputName = "//*[@id='login-form']/div/dl/dd[1]/input";
    private String xpathInputPassword = "//*[@id='login-form__password']";
    private String xpathButtonSubmit = "//*[@id='login-form']/div/dl/dd[5]/input";

    public PracaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeName(String name) {
        By byInputName = By.xpath(xpathInputName);
        WebElement elementInputName = driver.findElement(byInputName);
        elementInputName.sendKeys(name);
    }

    public void typePassword(String password) {
        By byInputHeight = By.xpath(xpathInputPassword);
        WebElement elementInputHeight = driver.findElement(byInputHeight);
        elementInputHeight.sendKeys(password);
    }


    public void selectButtonSubmit() {
        By byButtonSubmit = By.xpath(xpathButtonSubmit);
        WebElement elementButtonSubmit = driver.findElement(byButtonSubmit);
        elementButtonSubmit.click();
    }


}
