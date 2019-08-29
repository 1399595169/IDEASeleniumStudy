package com.selenium.testNG;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;


/**平时我们在做自动化过程中可能需要模拟鼠
 * 标、键盘等的一些行为，例如鼠标单击，双击，右键，
 * 而且很多web 应用可能存在快捷组合键等等。那么可以用WebDriver中提供了Actions类来处理这类需求，
 * 更复杂的键盘鼠标处理我们可以通过Java提供的Robot类解决。
 */
public class ActionsTest {

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
     * 鼠标右键，双击的操作
     * @throws InterruptedException
     */

    @Test
    public void rightkeyTest() throws InterruptedException {
        driver.get("https://www.baidu.com");
     WebElement element = driver.findElement(By.id("su"));
        Actions actions = new Actions(driver);
        //鼠标右键
        actions.contextClick(element).perform();
        Thread.sleep(3000);
        //双击
        actions.doubleClick(element).perform();
    }

    /**
     * 鼠标移动到某个元素上
     */

    @Test
    public void  testMoveToElement() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        WebElement element= driver.findElement(By.xpath("//*[@id=\"action\"]/input"));
       Actions actions = new Actions(driver);
       //将鼠标移动到某某元素上
       actions.moveToElement(element).perform();
       Thread.sleep(3000);

      String hello = driver.findElement(By.xpath(".//*[text()='Hello World!']")).getText();
        Assert.assertEquals(hello,"Hello World!");
    }


    /**
     * 元素拖拽
     */
    @Test
    public void dragAndDroptest(){
        driver.get("file:///C:/selenium_html/dragAndDrop.html");
       WebElement element= driver.findElement(By.id("drag"));
       Actions actions = new Actions(driver);
       //把元素移动到指定位置
       actions.dragAndDropBy(element,500,500).perform();
    }

    /**
     * 把元素拖拽到另一个元素之上
     */

    @Test
    public void  testClikeAndHold(){
        driver.get("file:///C:/selenium_html/dragAndDrop.html");
       WebElement element1 = driver.findElement(By.id("drag"));
       WebElement element2= driver.findElement(By.xpath("/html/body/h1"));
       Actions actions =new Actions(driver);
       actions.clickAndHold(element1)
               .moveToElement(element2)
               .release(element1)
               .perform();
    }


    /**
     * 下拉框多选
     */
    @Test
    public void test05() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        List<WebElement> list =   driver.findElements(By.xpath("//*[@id=\"selectWithMultipleEqualsMultiple\"]/option"));
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(list.get(2))  //有默认选择第一个
                .keyUp(Keys.CONTROL)
                .perform();
        Thread.sleep(3000);
    }


    /**
     * 多键盘按键操作 按钮组合（ctrl+s）
     * robot
     */
    @Test
    public  void saveHtmlTest() throws AWTException, InterruptedException {
        driver.get("https://www.baidu.com");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(3000);
       //释放按钮
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * 上传文件处理
     * 注：sendKeys 方法 必须是input标签才可以使用，否则报错
     */
    @Test
    public void uploadTest() throws InterruptedException {
        driver.get("file:///C:/selenium_html/index.html");
        driver.findElement(By.id("load"))
                .sendKeys("D:\\253.JPG");
        Thread.sleep(5000);
    }

    /**
     * 更改js
     * 操作js
     */
    @Test
    public void exJs() throws InterruptedException {
        driver.get("https://www.baidu.com");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeAsyncScript("document.getElementById(\"kw\").setAttribute(\"Value\",\"WebElement\");");
        Thread.sleep(5000);
    }

}
