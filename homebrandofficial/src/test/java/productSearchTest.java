import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class productSearchTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://homebrandofficial.ru/wear");
    }

    @Test
    public void productSearchCountTest() throws InterruptedException {
        String productName = "ФУТБОЛКА ПОЛО ЧЕРНАЯ (М)";
        driver.findElement(By.cssSelector(".t-store__filter__search .t-store__filter__input")).sendKeys(productName);
        sleep(2000);
        driver.findElement(By.cssSelector(".t-store__search-icon")).click();
        sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By element = By.xpath("//*[contains(@class, \"t-store__card__title\")]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
        sleep(2000);

        String count = driver.findElement(By.xpath("//*[contains(@class, \"t-store__filter__prods-number\")]")).getText();
        String actualProductName = driver.findElement(element).getText();
        String actualPrice = driver.findElement(By.xpath("//*[contains(@class, \"price-val t-store__card__price\")]")).getText();


        Assert.assertEquals("Найдено: 1", count);
        Assert.assertEquals(productName, actualProductName);
        Assert.assertEquals("2 800", actualPrice);
    }


    @After
    public void afterTest() {
        driver.quit();
    }
}
