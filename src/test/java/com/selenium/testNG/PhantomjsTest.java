package com.selenium.testNG;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;

public class PhantomjsTest {

    @Test
    public void openBrowser(){
        //设置phantomjs路径
        System.setProperty("phantomjs.binary.path","D:\\IDEASeleniumStudy\\driver\\phantomjs.exe");
        //打开phantomjs浏览器
        PhantomJSDriver driver = new PhantomJSDriver();
        driver.get("https://www.baidu.com");
         String title = driver.getTitle();
         System.out.println(title);
         driver.quit();

    }

}
