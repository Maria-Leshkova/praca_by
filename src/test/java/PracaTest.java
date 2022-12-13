import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PracaTest {

    WebDriver driver;
    PracaStep page;

    public static String generateEncorrectPsw() {

        return Util.generateRandomString(10);
    }

    public static String generateEncorrectEmail() {
        String email = "mail" + RandomStringUtils.randomNumeric(5) + "@gmail.com";
        return email;
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = new PracaStep(driver);
        driver.get(page.URL);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void testOpenPracaWebSite() {
        String xpathFooter = "//html/body/footer/div/div[2]/div[1]/div[2]/p[3]";
        final String FOOTER_COPY = "Режим работы сайта — круглосуточно. E-mail ООО «ПРАЦА БАЙ» editor@praca.by";
        By byXpath = By.xpath(xpathFooter);
        WebElement element = driver.findElement(byXpath);
        Util.waiter(3);
        String actual = element.getText();
        Assert.assertEquals(FOOTER_COPY, actual);
    }

    @Test
    public void testOpenPracaLoginPage() {
        String xpathButtonGoToLoginPage = "//*[@class='ctrl__top-login login-modal btn btn_dark-red js-open-login-modal']";
        String xpathButtonLogin = "//*[@id='login-form']/div/dl/dd[5]/input";
        Util.waiter(1);
        By byXpathButtonGoToLoginPage = By.xpath(xpathButtonGoToLoginPage);
        WebElement elementButtonGoToLoginPage = driver.findElement(byXpathButtonGoToLoginPage);
        elementButtonGoToLoginPage.click();
        Util.waiter(1);
    }

    @Test
    public void testOpenPracaLoginPageEmpty() {
        String xpathButtonGoToLoginPage = "//*[@class='ctrl__top-login login-modal btn btn_dark-red js-open-login-modal']";
        String xpathButtonLogin = "//*[@id='login-form']/div/dl/dd[5]/input";
        String expectedErrorMessageUserName = "Поле обязательно для заполнения";
        String expectedErrorMessagePassword = "Поле обязательно для заполнения";
        String xpathErrorMessageUserName = "//*[@id='login-form']/div/dl/dd[1]/ul/li";
        String xpathErrorMessagePassword = "//*[@id='web-forms__collection_dd_login-form__password__item']/ul/li";
        Util.waiter(1);
        By byXpathButtonGoToLoginPage = By.xpath(xpathButtonGoToLoginPage);
        WebElement elementButtonGoToLoginPage = driver.findElement(byXpathButtonGoToLoginPage);
        elementButtonGoToLoginPage.click();
        Util.waiter(1);
        By byXpathButtonLogin = By.xpath(xpathButtonLogin);
        WebElement elementButtonLogin = driver.findElement(byXpathButtonLogin);
        elementButtonLogin.click();

        By byXpathErrorMessageUserName = By.xpath(xpathErrorMessageUserName);
        Duration duration = Duration.ofSeconds(3);
        WebElement elementErrorMessageUserName = (new WebDriverWait(driver, duration))
                .until(ExpectedConditions.elementToBeClickable(byXpathErrorMessageUserName));

        By byXpathErrorMessagePassword = By.xpath(xpathErrorMessagePassword);
        WebElement elementErrorMessagePassword = driver.findElement(byXpathErrorMessagePassword);

        String actualErrorMessageUserName = elementErrorMessageUserName.getText();
        String actualErrorMessagePassword = elementErrorMessagePassword.getText();

        Assert.assertEquals(expectedErrorMessageUserName, actualErrorMessageUserName);
        Assert.assertEquals(expectedErrorMessagePassword, actualErrorMessagePassword);
        Util.waiter(3);
    }

    @Test
    public void testOpenPracaLoginPasswordEmpty() {
        String expectedErrorMessagePassword = "Поле обязательно для заполнения";
        String xpathErrorMessagePassword = "//*[@id='web-forms__collection_dd_login-form__password__item']/ul/li";
        page.fillUserNameAndClickSubmit("wadserfg90@gmail.com");
        Util.waiter(1);
        By byXpathErrorMessagePassword = By.xpath(xpathErrorMessagePassword);
        WebElement elementErrorMessagePassword = driver.findElement(byXpathErrorMessagePassword);
        Util.waiter(1);
        String actualErrorMessagePassword = elementErrorMessagePassword.getText();

        Assert.assertEquals(expectedErrorMessagePassword, actualErrorMessagePassword);
        Util.waiter(1);
    }

    @Test
    public void testOpenPracaLoginUserNameEmpty() {
        String expectedErrorMessageUserName = "Поле обязательно для заполнения";
        String xpathErrorMessageUserName = "//*[@id='login-form']/div/dl/dd[1]/ul/li";
        page.fillPasswordAndClickSubmit("pHG7qVMvJQ7+jNZ");
        Util.waiter(1);
        By byXpathErrorMessageUserName = By.xpath(xpathErrorMessageUserName);
        WebElement elementErrorMessageUserName = driver.findElement(byXpathErrorMessageUserName);
        Util.waiter(1);
        String actualErrorMessageUserName = elementErrorMessageUserName.getText();

        Assert.assertEquals(expectedErrorMessageUserName, actualErrorMessageUserName);
        Util.waiter(1);
    }

    @Test
    public void testOpenPracaLoginPageCorrect() {
        String xpathCloseModal = "//*[@id='phone-modal']/div/div[2]/div[1]/button/i";
        page.fillFormAndClickSubmit("wadserfg90@gmail.com", "pHG7qVMvJQ7+jNZ");
        Util.waiter(1);
        By byXpathCloseModal = By.xpath(xpathCloseModal);
        WebElement elementCloseModal = driver.findElement(byXpathCloseModal);
        elementCloseModal.click();
        Util.waiter(1);

        String xpathHomePage = "/html/body/div[1]/header/div[2]/div/div/div[1]/a";
        By byXpathHomePage = By.xpath(xpathHomePage);
        WebElement elementHomePage = driver.findElement(byXpathHomePage);
        elementHomePage.click();
        Util.waiter(1);
    }

    @Test
    public void testOpenPracaLoginPageIncorrect() {
        Faker faker = new Faker();
        String psw=faker.internet().password();
        String gmail=faker.internet().emailAddress();
        //String psw = generateEncorrectPsw();
        //String gmail = generateEncorrectEmail();
        String expectedIncorrectUserName = "Указан неверный e-mail или пароль";
        String xpathIncorrectUserName = "/html/body/div[1]/div[5]/div/div/div/div";
        page.fillFormAndClickSubmit(gmail, psw);
        Util.waiter(1);

        By byXpathIncorrectUserName = By.xpath(xpathIncorrectUserName);
        Duration duration = Duration.ofSeconds(3);
        WebElement elementIncorrectUserName = (new WebDriverWait(driver, duration))
                .until(ExpectedConditions.elementToBeClickable(byXpathIncorrectUserName));

        String actualIncorrectUserName = elementIncorrectUserName.getText();

        Assert.assertEquals(expectedIncorrectUserName, actualIncorrectUserName);
        Util.waiter(1);
    }
}
