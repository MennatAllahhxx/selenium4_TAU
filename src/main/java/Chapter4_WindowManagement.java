import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class Chapter4_WindowManagement {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().minimize();

        driver.get("https://magento.softwaretestingboard.com/");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        driver.manage().window().minimize();
        newWindow.get("https://magento.softwaretestingboard.com/what-is-new.html");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowTabs() {
        driver.manage().window().minimize();
        driver.switchTo().newWindow(WindowType.TAB).get("https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html");
        System.out.println("Title: " + driver.getTitle());

        driver.findElement(By.id("search")).sendKeys("zip");
        driver.findElement(By.cssSelector(".action.search")).click();

        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterator = allWindowTabs.iterator();
        String mainFirstWindow = iterator.next();

        driver.switchTo().window(mainFirstWindow);
        driver.manage().window().minimize();

        driver.findElement(By.className("home-main")).click();
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}