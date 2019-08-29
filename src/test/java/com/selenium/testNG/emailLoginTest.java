package com.selenium.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class emailLoginTest {

    WebDriver driver;
    @BeforeMethod
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //全局等待
        //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }


    @Test
    public void loginTest(){
        driver.get("https://mail.163.com/");
        driver.findElement(By.id("switchAccountLogin")).click();
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        driver.findElement(By.name("email")).sendKeys("qq1399595169");
        driver.findElement(By.name("password")).sendKeys("gun!!!.123");
        driver.findElement(By.id("dologin")).click();



    }
}
