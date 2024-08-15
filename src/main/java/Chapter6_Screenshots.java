import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Chapter6_Screenshots {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://applitools.com/");
    }

    @Test
    public void takeWebElementScreenshot() throws IOException {
        WebElement header = driver.findElement(By.id("h-maximum-coverage-minimum-time"));

        File src = header.getScreenshotAs(OutputType.FILE);
        File dest = new File("header.png");

        FileHandler.copy(src, dest);
    }

    @Test
    public void takeWebElementPageSectionScreenshot() throws IOException {
        WebElement pageSection = driver.findElement(By.cssSelector("#post-8>div>div"));

        File src = pageSection.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("pageSection.png"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}