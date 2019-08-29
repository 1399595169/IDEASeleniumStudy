package com.selenium.testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class OpenBrowserTest {

    @Test
    public void openChrome(){
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
    }

    @Test
    public void openFirefox(){
        System.setProperty("webdriver.friefox.bin","D:\\IDEASeleniumStudy\\driver\\drivergeckodriver.exe");
        WebDriver webDriver = new FirefoxDriver();
    }

    @Test
    public void  openIE(){
        System.setProperty("webdriver.friefox.driver","D:\\IDEASeleniumStudy\\driver\\IEDriverServer.exe");
        WebDriver webDriver = new InternetExplorerDriver();
    }


}
