package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void dropDownList(By locator, String text) {
        WebElement dropdown = manager.driver.findElement(locator);
        dropdown.findElement(By.xpath(".//option[. = '" + text + "']")).click();
    }

    protected void selectAllElements() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    protected void attach(By locator, String file) {
        //manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
        File f = new File(file);
        if (f.exists()) {
            manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
        }
    }

}
