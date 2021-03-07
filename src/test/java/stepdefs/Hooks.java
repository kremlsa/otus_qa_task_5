package stepdefs;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import common.BaseClass;
import factory.BrowserName;
import factory.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks extends BaseClass{

    BaseClass base;

    public Hooks(BaseClass base) {
        this.base = base;
    }


    @Before (value="@TestUI")
    public void Setup() {
        System.out.println("Setup");

        //Получаем имя браузера из параметра -Dbrowser командной строки, если не указан то по умолчанию chrome
        String name = Optional.ofNullable(System.getProperty("browser")).orElse("chrome");

        //Получаем имя драйвера из класса Enum
        BrowserName browserName = BrowserName.findByName(name);

        //Если имя браузера не было распознано корректно, то логируем предупреждение
        if (browserName == BrowserName.DEFAULT) {
            base.logger.warn("WebDriver name from the cmdline is not recognized %" + name
                    + "% use Chrome");
        }

        //Создаём вебдрайвер через статический метод класса WebDriverFactory
        base.driver = WebDriverFactory.create(browserName);
        //Устанавливаем максимальный размер окна для браузера
        base.driver.manage().window().maximize();
        base.logger.info("Start WebDriver " + browserName.getBrowserName());

        base.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After (value="@TestUI")
    public void setDown() {
        if (base.driver != null) {
            base.driver.close();
            base.logger.info("Shutdown WebDriver");
        } else {
            base.logger.error("Error WebDriver not found");
        }
    }
}