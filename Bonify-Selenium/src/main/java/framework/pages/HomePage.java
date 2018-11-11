package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by FOX on 11/11/2018.
 */
public class HomePage extends PageObject{

    final String XPATH_LABEL_WELCOME = "//div[@class='message']";

    //User links
    final String XPATH_BUTTON_USERLINKS = "(//div[@class='dropdown nav-menu-dropdown'])[1]/button";
    final String XPATH_BUTTON_LOGOUT = "(//button[@class='dropdown-item-content'])[2]";

    //Side bar
    final String XPATH_SIDEBAR = "//nav";
    final String XPATH_BUTTON_DEALS = "//a[@href='/my-products']";


    public String getMessage(){ return readElement(XPATH_LABEL_WELCOME);}

    public void gotoDeals(){ clickLink(XPATH_BUTTON_DEALS);}

    public void moveToNavBar(String navBar, String option){

        String xPath;
        String xPath2;
        if (navBar.equals("user"))
            xPath = XPATH_BUTTON_USERLINKS;
        else
            xPath = XPATH_SIDEBAR;

        clickElement(xPath);
        WebElement admin = findElement("xpath", xPath);
        new Actions(getWebDriver()).moveToElement(admin).perform();

        if (option.equals("signout"))
            xPath2 = XPATH_BUTTON_LOGOUT;
        else if (option.equals("deals"))
            xPath2 = XPATH_BUTTON_DEALS;
        else xPath2 = "";

        WebElement logout = new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable(findElement("xpath",xPath2)));
        logout.click();

    }

//    public void signOut(){
//        WebElement logout = new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable(findElement("xpath",xPath2)));
//        logout.click();
//    }



}
