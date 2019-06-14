package at.javatraining;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("e2e")
public class IndexFormTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        setSystemPropertyIfUnset("webdriver.chrome.driver", "webdrivers/chromedriver.exe"); // key: von Chrome vorgegeben; value: relativer Pfad vom Root Ordner

        driver = new ChromeDriver(); // Browser öffnen
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // Werden Elemente im Browser nicht gefunden, bricht er nach 3 Sekunden ab
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit(); // Browser wieder schließen
    }

    @Test
    public void testShowActiveEmployees() {
        driver.get("http://localhost:8082/employee/index.xhtml");

        var checkboxShowActive = driver.findElement(By.id("form:showactive"));
        var checkboxShowNonActive = driver.findElement(By.id("form:shownonactive"));
        var buttonSubmit = driver.findElement(By.id("form:submit"));
        var hiddenViewState = driver.findElement(By.name("javax.faces.ViewState"));

        var viewStateValue = hiddenViewState.getAttribute("value");
        if (!checkboxShowActive.isSelected()) {
            checkboxShowActive.click();
        }
        if (checkboxShowNonActive.isSelected()) {
            checkboxShowNonActive.click();
        }
        buttonSubmit.submit();

        // waits for view state value to change
        var wait = new WebDriverWait(driver, 3);
        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.textToBePresentInElementValue(
                                By.name("javax.faces.ViewState"),
                                viewStateValue
                        )
                )
        );

        var liEmployeeTexts = driver.findElements(By.cssSelector("ul > li")).stream()
                .map(li -> li.getText())
                .collect(Collectors.toList());

        assertEquals(2, liEmployeeTexts.size());
    }

    private void setSystemPropertyIfUnset(String key, String value) {
        System.getProperties().putIfAbsent(key, value); // damit es beim Aufruf über Maven nicht erneut gesetzt wird
    }
}
