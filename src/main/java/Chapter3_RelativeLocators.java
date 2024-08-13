import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Chapter3_RelativeLocators {
    WebDriver driver;

    @BeforeMethod
    public void setUP() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRelativeLocator() {
        WebElement loginPanel = driver.findElement(By.className("oxd-form"));
        WebElement credentials = driver.findElement(RelativeLocator.with(
                By.tagName("div")).
                above(loginPanel));

        System.out.println(credentials.getText());
    }

    @Test
    public void testListOFElements() {
        WebElement copyright = driver.findElement(By.className("orangehrm-copyright-wrapper"));
        System.out.println(copyright.getText());

        List<WebElement> allSocialMedia = driver.findElements(with(By.tagName("a")).near(copyright));
        allSocialMedia.forEach(socialMedia -> System.out.println(socialMedia.getAttribute("href")));

    }
}