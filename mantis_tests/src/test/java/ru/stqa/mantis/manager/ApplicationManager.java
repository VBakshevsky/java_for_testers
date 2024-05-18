package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;

    private SessionHelper sessionHelper;
    private HttpSessionHelper HttpSessionHelper;
    private JamesCliHelper JamesCliHelper;
    private MailHelper MailHelper;
    private JamesApiHelper jamesApiHelper;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
//            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(2576, 1426));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (HttpSessionHelper == null) {
            HttpSessionHelper = new HttpSessionHelper(this);
        }
        return HttpSessionHelper;
    }
    public JamesCliHelper jamesCli() {
        if (JamesCliHelper == null) {
            JamesCliHelper = new JamesCliHelper(this);
        }
        return JamesCliHelper;
    }
    public JamesApiHelper jamesApi() {
        if (jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }
    public MailHelper mail() {
        if (MailHelper == null) {
            MailHelper = new MailHelper(this);
        }
        return MailHelper;
    }
    public String property(String name) {
        return properties.getProperty(name);
    }
}
