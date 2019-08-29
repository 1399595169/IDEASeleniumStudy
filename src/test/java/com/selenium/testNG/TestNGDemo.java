package com.selenium.testNG;

import org.testng.annotations.*;

public class TestNGDemo {

    @Test
    public void  case1(){
    System.out.println("这是case1的方法");

    }
    @Test
    public void case2(){
        System.out.println("这个是case2的方法");
    }

    @BeforeTest
    public void Beforcase(){
        System.out.println("这个是所有方法之前执行的方法");
    }

    @BeforeMethod
    public void BeforMethod(){
        System.out.println("这个是每个case方法执行之前执行的");
    }

    @AfterTest
    public void Aftercase(){
        System.out.println("这个是所有方法之后执行的方法");
    }

    @AfterMethod
    public void AfterMethod(){
        System.out.println("这个是每个case执行之后执行的方法");
    }

}
