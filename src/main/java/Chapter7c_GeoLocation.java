import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Chapter7c_GeoLocation {
    ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    //@AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void mockGeoLocation_ExecuteCDPCommand() {
        Map coordinates = new HashMap() {{
            put("latitude", 32.746940);
            put("longitude", -97.092400);
            put("accuracy", 1);
        }};

        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        driver.get("https://where-am-i.org/");
    }

    @Test
    public void mockGeoLocation_DevTools() {
        DevTools devTools = driver.getDevTools();

        devTools.createSession();

        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));

        driver.get("https://my-location.org/");
    }
}