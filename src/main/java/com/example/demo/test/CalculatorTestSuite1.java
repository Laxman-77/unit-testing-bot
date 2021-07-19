package com.example.demo.test;

import com.spr.adchannels.facebook.adapters.dynamiccreative.DynamicCreativeDisjointTypeAdapterTest;
import com.spr.audience.audienceImportExport.AudienceImportUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest1.class,
        CalculatorTest2.class,
        DynamicCreativeDisjointTypeAdapterTest.class,
        AudienceImportUnitTest.class
})
public class CalculatorTestSuite1 {

}
