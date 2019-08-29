package com.selenium.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SelectTest {

    WebDriver driver;
    @BeforeMethod
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //全局等待
       // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * 1.打开测试页面
     * 2.下拉选择vivo
     * 3.第二次选择华为
     * 4.第三次选择iphone
     */
    @Test
    public void selectTest() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        //定位下拉框
       WebElement element= driver.findElement(By.id("moreSelect"));
       //实例化一个select对象
        Select select =new Select(element);
        //通过索引选取
        select.selectByIndex(2);
        Thread.sleep(3000);
        //通过属性value值选取
        select.selectByValue("huawei");
        Thread.sleep(3000);
        //通过text选取
        select.selectByVisibleText("iphone");

    }

    /**
     * 多窗口的处理
     * 1.打开测试页面
     * 2.点击Open new window
     * 3.点击baidu
     */
   @Test
    public void  testWin() throws InterruptedException {
       driver.get("file:///C:/selenium_html/index.html");
       driver.findElement(By.linkText("Open new window")).click();
       //获取当前页面的句柄
      String handle = driver.getWindowHandle();
      //获取所有页面的句柄，如果不是当前页面的句柄，就switchto 移交控制权
       for (String handles:driver.getWindowHandles()) {
           if (handles.equals(handle))
              continue;
           driver.switchTo().window(handles);
       }
       Thread.sleep(3000);
       driver.findElement(By.linkText("baidu")).click();
       Thread.sleep(3000);
       driver.switchTo().window(handle);
       Thread.sleep(3000);
       driver.findElement(By.id("user")).sendKeys("你是我今生的唯一");
 
   }



    /**打开测试页面
     * 点击wait按钮
     * 获取文本，并判断是否为“wait for display”
      */
   @Test
    public void waitTest(){
       driver.get("file:///C:/selenium_html/index.html");
       driver.findElement(By.xpath("//*[@id=\"wait\"]/input")).click();
       //显示等待
       WebDriverWait wait = new WebDriverWait(driver,10);
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"display\"]/div")));

       String text=  driver.findElement(By.xpath("//*[@id=\"display\"]/div")).getText();

       Assert.assertEquals(text,"wait for display");
   }





}
