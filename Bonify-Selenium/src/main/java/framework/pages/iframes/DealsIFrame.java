package framework.pages.iframes;


import framework.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

/**
 * Created by FOX on 11/11/2018.
 */
public class DealsIFrame extends HomePage {

    //Loans xpaths
    final String XPATH_IFRAME_LOANS = "//section[@class='iframe-container']/iframe";
    final String XPATH_IFRAME_LOANS2 = "//iframe[@name='ffgembedframe']";

    final String XPATH_SEARCH_AMOUNT = "//input[@id='amount']";
    final String XPATH_SEARCH_TERM = "//select[@id='term']";
    final String XPATH_SEARCH_PURPOSE = "//select[@id='purpose']";
    final String XPATH_SEARCH_RELOAD = "//button[@id='reloadButton']";



    public void switchToIframe(String iFrame){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_IFRAME_LOANS)));
            if(iFrame.equals("LOANS")) {
                getWebDriver().switchTo().frame(findElement("xpath", XPATH_IFRAME_LOANS));
                getWebDriver().switchTo().frame(findElement("xpath", XPATH_IFRAME_LOANS2));
            }
        }
        catch (NoSuchElementException nSee){
            System.out.println(nSee.toString());
        }
    }

    public void switchToMain(){ getWebDriver().switchTo().defaultContent();}

    public void submitCreaditComparisonSearchParams(int kreditbetrag, int laufzeit, int verwendung){
        typeValues(XPATH_SEARCH_AMOUNT, kreditbetrag);
        selectDropdown(XPATH_SEARCH_TERM, laufzeit);
        selectDropdown(XPATH_SEARCH_PURPOSE, verwendung);

    }


}
