package com.selenium.testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGDemo2 {

    @Test
    public void  AssertEqueals(){
        String a ="asd";
        String b = "asd";
        Assert.assertEquals(a,b,"相等");
    }

    @Test
    public void AssertNotEqueals(){
        String  a= "123";
        String b ="123";
        Assert.assertNotEquals(a,b,"不相等");
    }

    @Test
    public void AssertNull(){
       String a =null;
       Assert.assertNull(a);
    }

    @Test
    public void AssertNotNull(){
        String a ="asd";
        Assert.assertNotNull(a);
    }
}
