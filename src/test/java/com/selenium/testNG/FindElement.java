package com.selenium.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

public class FindElement {
    WebDriver webDriver;

    @BeforeMethod
    public  void openBrowser(){
        System.setProperty("webdriver.chrome.driver","D:\\IDEASeleniumStudy\\driver\\chromedriver.exe");
         webDriver = new ChromeDriver();
    }

    @Test
    public void  findElementById(){
        webDriver.get("https://www.baidu.com");
        WebElement element = webDriver.findElement(By.id("kw"));
    }

    @Test
    public void findElementByName(){
        webDriver.get("https://www.baidu.com");
        WebElement element=webDriver.findElement(By.name("wd"));
    }

    @Test
    public void findElementByclassName(){
        webDriver.get("https://www.baidu.com");
        WebElement element= webDriver.findElement(By.className("s_ipt"));
    }

    @Test
    public void findElenmentByLinkTest(){
        webDriver.get("https://www.baidu.com");
       WebElement element= webDriver.findElement(By.linkText("新闻"));
    }

    @Test
    public void findElementBypartialLinkText(){
        webDriver.get("https://www.baidu.com");
        WebElement element= webDriver.findElement(By.partialLinkText("吧"));
    }

    @Test
    public  void findElementBycssSelector(){
        webDriver.get("https://www.baidu.com");
        WebElement element=  webDriver.findElement(By.cssSelector("#su"));
    }
    @Test
    public void findElementByXpth(){
        webDriver.get("https://www.baidu.com");
        List<WebElement> list= webDriver.findElements(By.xpath("//*[@id='u_sp']/a"));
      for (int i=0;i<list.size();i++){
         String text= list.get(i).getText();
         System.out.println(text);
      }
    }


    @AfterMethod
    public void  closeBrowser(){
        webDriver.quit();
    }
}
