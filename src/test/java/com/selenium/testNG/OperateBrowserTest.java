package com.selenium.testNG;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OperateBrowserTest {

    WebDriver webDriver;

    @BeforeMethod
    public void openBrowser(){
        //定位浏览器的驱动
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
        //打开浏览器
        webDriver=new ChromeDriver();
    }

    @Test
    public void BrowserTest() throws InterruptedException {
        //打开百度
        webDriver.get("https://www.baidu.com");
        //等待三秒
        Thread.sleep(3000);
        //后退
        webDriver.navigate().back();
        //等待三秒
        Thread.sleep(3000);
        //前进
        webDriver.navigate().forward();
        //等待三秒
        Thread.sleep(3000);
        //关闭浏览器
    }

    @Test
    public void  BrowserTest2() throws InterruptedException {
        //打开百度
        webDriver.get("https://www.baidu.com");
        //设置浏览器窗口大小
        Dimension dimension =new Dimension(300,300);
        webDriver.manage().window().setSize(dimension);
        Thread.sleep(1000);
        //刷新
        webDriver.navigate().refresh();
        Thread.sleep(1000);
        //浏览器最大化
        webDriver.manage().window().maximize();
        Thread.sleep(1000);
        //获取页当前页面的url
      String url =  webDriver.getCurrentUrl();
      //判断是否和我想的url一致
        Assert.assertEquals(url,"https://www.baidu.com/");
    }

    @Test
    public void BrowserTest3() throws InterruptedException {
        //打开百度
        webDriver.get("https://www.baidu.com");
        Thread.sleep(1000);
        //获取title
      String title=  webDriver.getTitle();
      Assert.assertEquals(title,"百度一下，你就知道");
    }



    @AfterMethod
    public void closeBrowser(){
        webDriver.close();
    }


}
