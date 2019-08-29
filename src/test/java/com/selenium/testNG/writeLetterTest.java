package com.selenium.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class writeLetterTest {

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
     * 使用163邮箱写信
     */
    @Test
    public void writeLetterTest(){
        //调用登录方法
        RegisterTest.login(driver,"qq1399595169","gun!!!.123");
        driver.findElement(By.xpath(".//*[@id='dvNavTop']/ul/li[2]/span[2]")).click();
        driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys("qq1399595169@163.com");
        driver.findElement(By.xpath(".//*[@aria-label='邮件主题输入框，请输入邮件主题']/input")).sendKeys("测试数据");
        driver.findElement(By.xpath(".//*[@title='一次可发送2000张照片，600首MP3，一部高清电影']/input")).sendKeys("C:\\Use\\123.jpg");
        //转交控制权给iframe
        WebElement frame= driver.findElement(By.className("APP-editor-iframe"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("/html/body")).sendKeys("这个是内容");
        //转回控制权
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath(".//*[text()='发送']")).click();
        //校验页面有没有展示发送成功
        Boolean Bl = driver.findElement(By.xpath(".//*[text()='发送成功']")).isDisplayed();
        Assert.assertTrue(Bl);

    }

}
