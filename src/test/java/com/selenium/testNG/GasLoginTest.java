package com.selenium.testNG;

import com.po.login.GasLogin;
import com.po.page.HomePage;
import com.po.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GasLoginTest {


    WebDriver driver;
    @BeforeMethod
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //全局等待
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * 正常登录龙华燃气
     */
    @Test
    public void loginGas() throws InterruptedException {
        String userName = "0000";
        String password ="posun@2018";
        GasLogin.login(driver,userName,password);
        driver.findElement(HomePage.userAction).click();
        String text = driver.findElement(By.xpath(".//*[@id='navbar']/header/div[5]/ul/li/ul/li[5]/a")).getText();
        Assert.assertEquals(text,"注销");
    }

    /**
     * 异常登录龙华燃气
     */
    @DataProvider(name = "userList")
    public Object[][] userList(){
        return  new Object[][]{
                {"!@#@!%#","1231231231"},
                {"djqiwjdoqiwjdqoidjqjwdiqo","ajsiojaiosjiosajoiaofijajfaisjfoijfajfoaijfoi"},
                {"0000","123456123"}
        };
    }

    @Test(dataProvider ="userList")
    public void loginGasError(String userName,String password) throws InterruptedException {
        GasLogin.login(driver,userName,password);
        String result=   driver.findElement(LoginPage.loginResult).getText();
        System.out.println(result);

    }
}
