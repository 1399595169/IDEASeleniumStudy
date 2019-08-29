package com.selenium.testNG;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Action {

    WebDriver driver;
    @BeforeMethod
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }


    /**
     * 打开百度，输入selenium点击百度一下，
     * 清空搜索框
     */
    @Test
    public void clikeTest() throws InterruptedException {
        driver.get("https://www.baidu.com");
      WebElement keys = driver.findElement(By.id("kw"));
      keys.sendKeys("selenium");
      WebElement btn = driver.findElement(By.id("su"));
      btn.click();
      Thread.sleep(3000);
    }

    @Test
    public void clearText() throws InterruptedException {
        driver.get("https://www.baidu.com");
        WebElement keys = driver.findElement(By.id("kw"));
        keys.sendKeys("selenium");
        Thread.sleep(3000);
        keys.clear();
        Thread.sleep(3000);
        keys.sendKeys("你是我今生的唯一");
        driver.findElement(By.id("su")).click();
        Thread.sleep(3000);
        String titl=driver.getTitle();
        Assert.assertEquals(titl,"你是我今生的唯一_百度搜索");

    }

    @Test
    public  void getText(){
        driver.get("https://www.baidu.com");
        WebElement element=driver.findElement(By.xpath(".//*[@id='u_sp']/a[1]"));
        String text =element.getText();
        Assert.assertEquals(text,"新闻");

    }

    /**
     * 打开百度页面
     * 判断按钮显示的文本值是否为 百度一下
     */
    @Test
    public void getAtest(){
        driver.get("https://www.baidu.com");
        String vlue= driver.findElement(By.id("su")).getAttribute("value");
       Assert.assertEquals(vlue,"百度一下");
    }

    /**
     * 打开百度页面
     * 判断是否显示百度一下按钮
     */
    @Test
    public void isDisplayedTest(){
        driver.get("https://www.baidu.com");
       Boolean A= driver.findElement(By.id("su")).isDisplayed();
       Assert.assertTrue(A);
    }

    /**
     * 截图百度首页
     */
    @Test
    public void shotTest(){
        driver.get("https://www.baidu.com");
        File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("D:\\test1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开测试页面，判断单选框是否被选中
     */
    @Test
    public void isSelectTest() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        WebElement element = driver.findElement(By.xpath(".//*[@id='radio']/label[1]"));
        //点击单选框
        element.click();
        Thread.sleep(3000);
        //判断是否点击
        boolean a= element.isSelected();
        Assert.assertTrue(a);
    }

    /**
     * 判断submit按钮是否激活（处于可点击状态）
     */
    @Test
    public void isEnabled(){
        driver.get("file:///C:/selenium_html/index.html");
        WebElement element =driver.findElement(By.name("buttonhtml"));
        boolean a=  element.isEnabled();
        Assert.assertTrue(a);
    }


    /**
     * Alert弹窗
     */

    @Test
    public void alertTest() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        driver.findElement(By.xpath("//*[@id=\"alert\"]/input")).click();
        //在移交控制前最好加个等待
        Thread.sleep(1000);
        //把控制权移交给alert
        Alert alert= driver.switchTo().alert();
        //获取弹窗的提示信息文本
        String text =alert.getText();
        Assert.assertEquals(text,"请点击确定");
        alert.accept();
    }


    /**
     * 1.打开测试主页
     * 2.点击Confirm按钮
     * 3.在confirm警告框点击取消按钮
     * 4.再次点击确定按钮
     */
     @Test
     public void confirmTest() throws InterruptedException {
         driver.get("file:///C:/selenium_html/index.html");
         driver.findElement(By.xpath(".//*[@id='confirm']/input")).click();
         Thread.sleep(3000);
         Alert alert=driver.switchTo().alert();
         //点击取消
         alert.dismiss();
         Thread.sleep(3000);
         //点击确定
         alert.accept();
     }

    /**
     * 1.打开测试页面
     * 2.点击prompt按钮
     * 3.在prompt弹框中输入这个是prompt
     * 4.点击确定
     * 5.再次点击确定
     */
     @Test
     public void promptTest() throws InterruptedException {
         driver.get("file:///C:/selenium_html/index.html");
         driver.findElement(By.className("prompt")).click();
         Thread.sleep(3000);
         Alert alert = driver.switchTo().alert();
         alert.sendKeys("这个是prompt");
         Thread.sleep(3000);
         alert.accept();
         Thread.sleep(3000);
         alert.accept();
     }

    /**
     * 1.打开测试页面
     * 2.点击iframe中的百度按钮
     *
     * @throws InterruptedException
     */

    @Test
    public void iframeTest() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        /**1.通过ifame的name或者id去转交控制权
         *   driver.switchTo().frame("aa");
         */
        // 2.通过Webelement方式
        WebElement element = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(element);
        driver.findElement(By.linkText("baidu")).click();
        Thread.sleep(3000);
        //把控制权转交回去
        driver.switchTo().defaultContent();
        driver.findElement(By.linkText("登陆界面")).click();

    }


    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
