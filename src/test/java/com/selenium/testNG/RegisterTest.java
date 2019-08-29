package com.selenium.testNG;

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

public class RegisterTest {


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

    /**
     * 163邮箱注册
     */
    @Test
    public void registerTest(){
        driver.get("https://mail.163.com");
        driver.findElement(By.xpath(".//*[@id='commonOperation']/a[2]")).click();
        String handle1 =  driver.getWindowHandle();
        for (String handles:driver.getWindowHandles()) {
            if (handles.equals(handle1))
                continue;
            driver.switchTo().window(handles);
        }
        String time= String.valueOf(System.currentTimeMillis()/100);
        driver.findElement(By.id("nameIpt")).sendKeys("email"+time);
        driver.findElement(By.id("mainPwdIpt")).sendKeys("qwe123456");
        driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("qwe123456");
        driver.findElement(By.id("vcodeIpt")).sendKeys("prspp");
        driver.findElement(By.id("mainMobileIpt")).sendKeys(time);
        driver.findElement(By.id("mainAcceptIpt")).click();
        driver.findElement(By.id("mainRegA")).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='m_vcode']/span")));

        String err=   driver.findElement(By.xpath(".//*[@id='m_vcode']/span")).getText();

        Assert.assertEquals(err,"  验证码不正确，请重新填写");
    }


    /**
     * 163登录(正常登录)
     */
    @Test
    public void loginSucessTest() throws InterruptedException {
//        driver.get("https://mail.163.com");
//        driver.findElement(By.id("switchAccountLogin")).click();
//        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
//        driver.findElement(By.name("email")).sendKeys("qq1399595169");
//        driver.findElement(By.name("password")).sendKeys("gun!!!.123");
//        driver.findElement(By.id("dologin")).click();
        RegisterTest.login(driver,"qq1399595169","gun!!!.123");

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='_mail_component_118_118']/a")));

       String loginout= driver.findElement(By.xpath(".//*[@id='_mail_component_118_118']/a")).getText();
       Assert.assertEquals(loginout,"退出");

    }


    /**
     * 163登录(异常登录)
     */

    //数据驱动 设置多份错误数据
    @DataProvider(name="userList")
    public Object [][] test1(){
        return  new Object[][]{
                //正确账号密码
                {"user1","12312312"},
                //错误账号密码
                {"user2","sadasdsada"}
        };
    }
    @Test(dataProvider = "userList")
    public  void loginErrorTest(String username,String password){
//        driver.get("https://mail.163.com");
//        driver.findElement(By.id("switchAccountLogin")).click();
//        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
//
//        driver.findElement(By.name("email")).sendKeys("qq1399595169");
//        driver.findElement(By.name("password")).sendKeys("gun!!!.123213");
//        driver.findElement(By.id("dologin")).click();
        RegisterTest.login(driver,username,password);

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nerror\"]/div[2]")));

        String text= driver.findElement(By.xpath("//*[@id=\"nerror\"]/div[2]")).getText();
        Assert.assertEquals(text,"帐号或密码错误");

    }

    /**
     * 登录方法
     * @param driver
     * @param email
     * @param password
     */

    public static void login(WebDriver driver,String email,String password){
        driver.get("https://mail.163.com");
        driver.findElement(By.id("switchAccountLogin")).click();
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("dologin")).click();

    }


}
