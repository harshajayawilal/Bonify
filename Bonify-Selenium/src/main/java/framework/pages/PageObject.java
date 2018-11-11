package framework.pages;

import framework.utils.TestFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by FOX on 11/11/2018.
 */
public class PageObject {

    private WebDriver webDriver;


    public WebDriver getWebDriver(){ return webDriver; } //Getter

    public PageObject() {
        webDriver = TestFramework.getConnection().getWebDriver();
    }

    public WebElement findElement(String type, String value){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            if(type.equals("xpath")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
                return getWebDriver().findElement(By.xpath(value));
            }
            else if (type.equals("css")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(value)));
                return getWebDriver().findElement(By.cssSelector(value));
            }
            else if (type.equals("id")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.id(value)));
                return getWebDriver().findElement(By.id(value));
            }
            else return null;
        } catch (NoSuchElementException nSee) {
            System.out.println(nSee.toString());
            return null;
        }
    }

    public void clickElement(String xPath){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
            getWebDriver().findElement(By.xpath(xPath)).click();
        } catch (NoSuchElementException nSee) {
            System.out.println(nSee.toString());
        }
    } // Clicks on an element

    public void clickLink(String linkText){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
            getWebDriver().findElement(By.linkText(linkText)).click();

        } catch (NoSuchElementException nSee) {
            System.out.println(nSee.toString());
        }
    } // Clicks on an element

    public String readElement(String xPath){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
            return getWebDriver().findElement(By.xpath(xPath)).getText();
        }
        catch (NoSuchElementException nSee){
            System.out.println(nSee.toString());
            return "ERROR: No such element found.";
        }
    } //Gets the test value of an element

    public void clearElement(String xPath){
        try {
            getWebDriver().findElement(By.xpath(xPath)).clear();
        }
        catch (NoSuchElementException nSee){
            System.out.println(nSee.toString());
        }
    } //Clears an element

    public void typeValues(String xPath, Object value){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
            getWebDriver().findElement(By.xpath(xPath)).sendKeys(String.valueOf(value));
        } catch (NoSuchElementException nSee) {
            System.out.println(nSee.toString());
        }
    }

    public void selectDropdown(String xPath, int index){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
            new Select(getWebDriver().findElement(By.xpath(xPath))).selectByIndex(index);
        } catch (NoSuchElementException nSee) {
            System.out.println(nSee.toString());
        }


    }

    public static String replaceUmlaut(String input) {

        String output = input.replace("ü", "ue")
                .replace("ö", "oe")
                .replace("ä", "ae")
                .replace("ß", "ss");

        output = output.replace("Ü(?=[a-zäöüß ])", "Ue")
                .replace("Ö(?=[a-zäöüß ])", "Oe")
                .replace("Ä(?=[a-zäöüß ])", "Ae");

        output = output.replace("Ü", "UE")
                .replace("Ö", "OE")
                .replace("Ä", "AE");

        return output;
    }


}
