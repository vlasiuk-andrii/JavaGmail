package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class GmailPage {
    WebDriver driver;
    public GmailPage(WebDriver driver){
        this.driver = driver;
    }

    public void getpage(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void login(String email, String pass){

        driver.findElement(By.xpath("//div/div/input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//div/div/*[@id='next']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-display")));

        driver.findElement(By.xpath("//div/div/input[@id='Passwd']")).sendKeys(pass);
        driver.findElement(By.xpath("//div/div/*[@id='signIn']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/span[contains(text(),'Gmail')]")));
    }

    public void createNewMessage()  {
        driver.findElement(By.xpath("//div[@gh='cm']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Senden')]")));

    }

    public void fillNewMessage(String receiver, String topic, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath("//div/textarea[@name='to']")).clear();
        driver.findElement(By.xpath("//div/textarea[@name='to']")).sendKeys(receiver);

        driver.findElement(By.xpath("//div/input[@name='subjectbox']")).clear();
        driver.findElement(By.xpath("//div/input[@name='subjectbox']")).sendKeys(topic);

        driver.findElement(By.xpath("//div[@role='textbox']")).clear();
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(text);

        StringSelection ss = new StringSelection("c:\\Users\\andrey.prudnyk\\IdeaProjects\\Gmail\\145.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null); //Copy filePath to clipboard

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div/div[@aria-label='Dateien anhängen']")).click(); //Click button "Upload"

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/div[contains(text(),'145.jpg')]")));
    }
    public void sendMessage(){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath("//div/div[contains(text(),'Senden')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div[contains(text(),'Ihre Nachricht wurde gesendet.')]")));
    }
    public void verifyInSentBox(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//a[@title='Gesendet']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/span[@title='Nicht markiert']/img[@alt='Nicht markiert']")));
        driver.findElement(By.xpath("//div/span/b[contains(text(),'Topic of the message')]"));

    }




}
