package com.po.page;

import org.openqa.selenium.By;

public class LoginPage {
    //用户名输入框
    public static By userNameInput = By.id("username");
    //密码输入框
    public static By pwdInput = By.id("kick");
    //登录按钮
    public static By loginButton = By.id("submitForm");
    //记住密码
    public static By jizhumima = By.xpath("//*[@id=\"loginForm\"]/div[5]/div/label");
    //异常登录结果
    public static By loginResult = By.xpath(".//*[@id='loginForm']/div[1]");

}
