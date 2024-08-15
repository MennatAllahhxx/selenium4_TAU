import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Chapter5_ElementPosition {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://testautomationu.applitools.com/learningpaths.html");
    }

    @Test
    public void getPositionDimention() {
        WebElement logoTAU = driver.findElement(By.className("logo"));
        Rectangle rectLogoTAU = logoTAU.getRect();

        System.out.println("X: "      + rectLogoTAU.getX());
        System.out.println("Y: "      + rectLogoTAU.getY());
        System.out.println("Width: "  + rectLogoTAU.getWidth());
        System.out.println("Height: " + rectLogoTAU.getHeight());
    }
}