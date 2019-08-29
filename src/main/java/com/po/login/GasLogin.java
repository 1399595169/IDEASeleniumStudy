package com.po.login;

import com.po.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class GasLogin {

    public static void login(WebDriver driver, String userName, String password) throws InterruptedException {
        driver.get("https://lhgas.oksales.net");
        driver.findElement(LoginPage.userNameInput).sendKeys(userName);
        driver.findElement(LoginPage.pwdInput).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(LoginPage.loginButton).click();
    }

}