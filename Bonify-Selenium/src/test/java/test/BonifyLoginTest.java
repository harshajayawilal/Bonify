package test;

import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.iframes.DealsIFrame;
import framework.utils.TestFramework;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Created by FOX on 11/11/2018.
 */
public class BonifyLoginTest extends TestFramework {

    public String validEmail = "emofox21@gmail.com"; //Valid first name
    public String validPassword = "@bonify123"; //Valid password
    public String validFName = "Harsha"; //Valid first name

    final String ERROR_CODE_EMAIL_01 = "Trage bitte Deine Email-Adresse ein";
    final String ERROR_CODE_EMAIL_02 = "Bitte nutze eine gültige Email-Adresse";
    final String ERROR_CODE_PASSWORD_01 = "Trage bitte Dein Passwort ein";
    final String ERROR_CODE_GENERAL_01 = "Leider passen Benutzername und Passwort nicht zusammen.";

    final String MESSAGE_01 = ", schön Dich zu sehen!";
    final String MESSAGE_RESET_PASSWORD_01 = "Vielen Dank! Wenn diese Email-Adresse bei bonify hinterlegt ist, haben wir" +
            " eine Email mit einem Link zum Zurücksetzen des Passwortes verschickt. Diese sollte in den nächsten Sekunden" +
            " bei Dir eintreffen.";

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Test(enabled = true, priority = 1, groups = {"Automated", "MyBonify", "Login"})
    public void cannotLoginWithoutEnteringEmailOrPassword() {

        loginPage.clearLoginFields();
        loginPage.clickSubmit();
        assertEquals(ERROR_CODE_EMAIL_01, loginPage.getEmailError());

        loginPage.enterEmail(validEmail);
        loginPage.clickSubmit();
        assertEquals(ERROR_CODE_PASSWORD_01, loginPage.getPasswordError());

    }

    @Test(enabled = true, priority = 2, groups = {"Automated", "MyBonify", "Login"})
    public void cannotProvideIncorrectEmailAsUsername() {

        loginPage.clearLoginFields();
        loginPage.enterEmail("111");
        loginPage.clickSubmit();

        assertEquals(loginPage.replaceUmlaut(ERROR_CODE_EMAIL_02), loginPage.replaceUmlaut(loginPage.getEmailError()));
    }

    @Test(enabled = true, priority = 3, groups = {"Automated", "MyBonify", "Login"})
    public void cannotLoginUsingIncorrectCredentials() {

        loginPage.clearLoginFields();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword("incorrectpass11");
        loginPage.clickSubmit();

        assertEquals(ERROR_CODE_GENERAL_01, loginPage.getGeneralError());
    }

    @Test(enabled = true, priority = 4, groups = {"Automated", "MyBonify", "Login"})
    public void canLoginUsingCorrectCredentials() {

        loginPage.clearLoginFields();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSubmit();

        assertEquals(homePage.replaceUmlaut("Hey " + validFName + MESSAGE_01), homePage.replaceUmlaut(homePage.getMessage()));

        homePage.moveToNavBar("user", "signout");
//        homePage.signOut();
    }

    @Test(enabled = true, priority = 5, groups = {"Automated", "MyBonify", "Login"})
    public void canRequestToResetPasswordViaForgotPassword() {
        loginPage.clickForgotPassword();
        assertEquals("Email senden", loginPage.readElement("//form[@name='sendEmail']/h1"));

        loginPage.enterEmail(validEmail);
        loginPage.clickSubmit();

        assertEquals("Neues Passwort", loginPage.readElement("//div[@class='substep']/div/h1"));
        assertEquals(loginPage.replaceUmlaut(MESSAGE_RESET_PASSWORD_01), loginPage.replaceUmlaut(loginPage.readElement("//div[@class='substep']/div/p")));
    }

    @Test(enabled = true, priority = 6, groups = {"Automated", "MyBonify", "Login"})
    public void emailValidationsOnForgotPassword() {
        loginPage.clickForgotPassword();
        loginPage.clickSubmit();
        assertEquals(ERROR_CODE_EMAIL_01, loginPage.getEmailError());

        loginPage.clearLoginFields();
        loginPage.enterEmail("111");
        loginPage.clickSubmit();

        assertEquals(loginPage.replaceUmlaut(ERROR_CODE_EMAIL_02), loginPage.replaceUmlaut(loginPage.getEmailError()));
    }

    @Test(enabled = true, priority = 7, groups = {"Automated", "MyBonify", "Login"})
    public void userCanUseCreditCardComparison() {
        DealsIFrame dealsIFrame = new DealsIFrame();

        loginPage.clearLoginFields();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSubmit();

        homePage.moveToNavBar("sidebar", "deals");
        dealsIFrame.switchToIframe("LOANS");
        dealsIFrame.submitCreaditComparisonSearchParams(2000,2,3);

        assertNotNull(dealsIFrame.findElement("xpath", "(//div[@class='offer-content'])[1]/button"));

        dealsIFrame.switchToMain();
        homePage.moveToNavBar("user", "signout");
    }

    @Test(enabled = true, priority = 8, groups = {"Automated", "MyBonify", "Login"})
    public void canVisitFooterLinksFromLoginPage() {
        String url = loginPage.clickFooterLinks("Impressum");
        assertEquals("https://www.bonify.de/impressum", url);

        url = loginPage.clickFooterLinks("AGB");
        assertEquals("https://www.bonify.de/agb", url);

        url = loginPage.clickFooterLinks("Datenschutz");
        assertEquals("https://www.bonify.de/datenschutz", url);
    }




}
