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

public class productSearchPriceTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\elvin\\Desktop\\учеба.работа\\мфти\\автоматизация\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://homebrandofficial.ru/wear");
    }

    @Test
    public void productSearchPriceTest() {
        String productName = "ФУТБОЛКА ПОЛО ЧЕРНАЯ (М)";
        driver.findElement(By.cssSelector(".t-store__filter__search .t-store__filter__input")).sendKeys(productName);
        driver.findElement(By.cssSelector(".t-store__search-icon")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By element = By.xpath("//*[contains(@class, \"t-store__card__title\")]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));

        String actualPrice = driver.findElement(By.xpath("//*[contains(@class, \"price-val t-store__card__price\")]")).getText();

        Assert.assertEquals("2 800", actualPrice);

    }


    @After
    public void afterTest() {
        driver.quit();
    }
}
