package com.selenium.testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTest {

    @Test
    public void  testChrome() throws MalformedURLException, InterruptedException {
        //创建一个DesiredCapabilities类型
        DesiredCapabilities dc =DesiredCapabilities.chrome();
        //实例化一个driver
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.110:7777/wd/hub"),dc);

        driver.get("http://www.baidu.com");
        Thread.sleep(3000);
        driver.quit();
    }

    //数据驱动
    @DataProvider(name="date4")
    public Object [][] test1(){
        return  new Object[][]{
                {"firefox","http://192.168.0.110:6666"},
                {"chrome","http://192.168.0.110:7777"}
        };
    }

    @Test(dataProvider = "date4")
    public void  testGrid2(String browser,String url) throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc=null;
        if (browser.equals("firefox")){
            dc=DesiredCapabilities.firefox();
        }else if (browser.equals("chrome")){
            dc=DesiredCapabilities.chrome();
        }else {
            System.out.println("error");
        }

        WebDriver driver = new RemoteWebDriver(new URL(url+"/wd/hub"),dc);

        driver.get("http://www.baidu.com");
        Thread.sleep(3000);
        driver.quit();
    }
}
