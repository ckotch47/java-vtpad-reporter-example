import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;


public class ConsultationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(DataForFilling.deployUrl);
    }
    @Test//6.1
    public void sayHelloInChatBySpecialist() throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();
        Thread.sleep(2000);
        mainPage.selectConsultationBySpecialist();
        Thread.sleep(3000);
        mainPage.fiilingHelloInChat(DataForFilling.messageForSendInConsultation);
        mainPage.pushSendTextInChat();
        mainPage.logout();
        Thread.sleep(7000);
        registrationPage.stepsOnAutorizationClient();
        mainPage.selectConsultationBySpecialist();
        Thread.sleep(3000);
        String actualText = mainPage.getFirstMessageInChat();
        String expectedText = DataForFilling.messageForSendInConsultation;
        Assert.assertEquals(expectedText, actualText);
        Thread.sleep(5000);
        driver.quit();
    }
    @Test//case 7.1
    public void sayHelloByClient() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        Thread.sleep(2000);
        mainPage.selectConsultationBySpecialist();
        Thread.sleep(2000);
        mainPage.fiilingHelloInChat(DataForFilling.messageForSendInConsultation);
        mainPage.pushSendTextInChat();
        mainPage.logout();
        Thread.sleep(7000);
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectConsultationBySpecialist();
        Thread.sleep(2000);
        String actualText = mainPage.getFirstMessageInChat();
        String expectedText = DataForFilling.messageForSendInConsultation;
        Assert.assertEquals(expectedText, actualText);
        driver.quit();
    }
    @Test
    public void checkUploadFileSpecialist() throws InterruptedException {//6.2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();
        Thread.sleep(3000);
        mainPage.selectConsultation();
        Thread.sleep(3000);
        mainPage.pushPaperClipButton();
        mainPage.uploadFile();
        Thread.sleep(3000);
        mainPage.addTextInModalMessageWithImage(DataForFilling.messageForSendConsultationWithImage);
        mainPage.submitUpload();
        mainPage.logout();
        registrationPage.stepsOnAutorizationClient();
        mainPage.selectConsultation();
        boolean a = mainPage.checkDisplayedImage();
        if (mainPage.getFirstMessageInChat().equals(DataForFilling.messageForSendConsultationWithImage) && a ){
            System.out.println("Добавлено изображение");
        }
        else System.out.println("Изображение не добавлено");
    }

    @Test
    public void checkUploadFileClient()throws InterruptedException {//7.2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        Thread.sleep(3000);
        mainPage.selectConsultation();
        Thread.sleep(3000);
        mainPage.pushPaperClipButton();
        mainPage.uploadFile();
        Thread.sleep(3000);
        mainPage.addTextInModalMessageWithImage(DataForFilling.messageForSendConsultationWithImage);
        mainPage.submitUpload();
        mainPage.logout();
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectConsultation();
        boolean a = mainPage.checkDisplayedImage();
        if (mainPage.getFirstMessageInChat().equals(DataForFilling.messageForSendConsultationWithImage) && a ){
            System.out.println("Добавлено изображение");
        }
        else System.out.println("Изображение не добавлено");
    }


}