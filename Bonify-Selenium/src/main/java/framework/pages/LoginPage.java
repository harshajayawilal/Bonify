package framework.pages;

/**
 * Created by FOX on 11/11/2018.
 */
public class LoginPage extends PageObject{

    final String XPATH_BUTTON_LOGIN = "//button[@type='submit']";
    final String XPATH_BUTTON_REGISTER = "//button[@type='button']";
    final String XPATH_BUTTON_FOORGOTPASSWORD = "//*[contains(text(),'Passwort vergessen?')]";


    final String XPATH_TESTBOX_EMAIL = "//input[@name='email']";
    final String XPATH_TESTBOX_PASSWORD = "//input[@name='password']";

    final String XPATH_LABEL_EMAIL_ERROR = "(//div[@class='error'])[1]//span";
    final String XPATH_LABEL_PASSWORD_ERROR = "(//div[@class='error'])[2]//span";
    final String XPATH_LABEL_GENERAL_ERROR = "//p[@class='general-error']";


    public void clickSubmit(){ clickElement(XPATH_BUTTON_LOGIN);}
    public void clickRegister(){ clickElement(XPATH_BUTTON_REGISTER);}

    public String clickFooterLinks(String link){
        String currentURL = "";
        String tabHandle = getWebDriver().getWindowHandle();
        clickLink(link);

        for (String handle : getWebDriver().getWindowHandles()) {
            if (!handle.equals(tabHandle))
            {
                getWebDriver().switchTo().window(handle);
                currentURL = getWebDriver().getCurrentUrl();
            }
            getWebDriver().navigate().refresh();

        }
        getWebDriver().close();
        getWebDriver().switchTo().window(tabHandle);
        return currentURL;
    }

    public void clickForgotPassword(){ clickElement(XPATH_BUTTON_FOORGOTPASSWORD);}

    public void clearLoginFields(){
        clearElement(XPATH_TESTBOX_EMAIL);
//        clearElement(XPATH_TESTBOX_PASSWORD);
    } //Clears fields on the login page

    public String getEmailError(){ return readElement(XPATH_LABEL_EMAIL_ERROR);}

    public String getPasswordError(){ return readElement(XPATH_LABEL_PASSWORD_ERROR);}

    public String getGeneralError(){ return readElement(XPATH_LABEL_GENERAL_ERROR);}

    public void enterEmail(String email){ typeValues(XPATH_TESTBOX_EMAIL, email); }

    public void enterPassword(String password){ typeValues(XPATH_TESTBOX_PASSWORD, password); }


}
