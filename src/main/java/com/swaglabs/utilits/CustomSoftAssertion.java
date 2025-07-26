package com.swaglabs.utilits;

import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {


    public static CustomSoftAssertion  softAssertion = new CustomSoftAssertion();

    public static void customAssertAll()
    {
        try {
            softAssertion.assertAll("Custom Soft Assertion");
        }catch (AssertionError e)
        {
            System.out.println("Custom Soft Assertion Failed");
        }

    }

}
