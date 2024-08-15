import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Chapter7b_ConsoleLogs {
    EdgeDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void viewBrowserConsoleLogs() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Log.enable());

        devTools.addListener(Log.entryAdded(), LogEntry -> {
            System.out.println("----------------------");
            System.out.println("Level: " + LogEntry.getLevel());
            System.out.println("Text: " + LogEntry.getText());
            System.out.println("Broken URL: " + LogEntry.getUrl());
        });

        driver.get("https://the-internet.herokuapp.com/broken_images");
    }
}