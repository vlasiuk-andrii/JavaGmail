package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.GmailPage;

import java.io.IOException;

public class testGmail {
    WebDriver driver;

    @BeforeTest
    public void shutUt(){
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void shutDown(){
        driver.close();
    }

    @Test
    public void gmailTest()  {
        GmailPage ongmailPage = new GmailPage(driver);
        ongmailPage.getpage("http://gmail.com");
        ongmailPage.login("vlasiuk.andrii93@gmail.com", "31415926535pi");
        ongmailPage.createNewMessage();
        ongmailPage.fillNewMessage("vlasiuk.andrii93@gmail.com", "Topic of the message", "TextTextText");
        ongmailPage.sendMessage();
        ongmailPage.verifyInSentBox();
    }

}
