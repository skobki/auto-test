import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Locale;

public class AutoTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(AutoTest.class);
    //Читаем передаваемый параметр browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");

    @BeforeEach
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.getDriver(env.toLowerCase());
        logger.info("Драйвер запущен");
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен");
        }
    }

    @Test
    public void openPage() {
        //Открытие страницы
        driver.get("https://yandex.ru/");
        logger.info("Открыта страница Yandex - " + "https://yandex.ru/");

        //Вывод заголовка страницы
        String title = driver.getTitle();
        logger.info("title - " + title.toString());

        //Вывод текущего URL
        String currentUrl = driver.getCurrentUrl();
        logger.info("current URL - " + currentUrl.toString());

        //Ввод текста для поиска
        String searchInputXpath = ".//input[@class='input__control input__input mini-suggest__input']";
        WebElement searchInput = driver.findElement(By.xpath(searchInputXpath));
        String searchText = "Java";
        searchInput.sendKeys(searchText);
        logger.info("Введено " + searchText + " в поле поиска");

        //Нажатие кнопки "Найти"
        String searchButtonXpath = ".//button[@class='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']";
        WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
        searchButton.click();
        logger.info("Нажатие кнопки поиска");

        //Задержка sleep для просмотра результата
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
