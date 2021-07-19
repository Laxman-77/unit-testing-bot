package com.example.demo.test;

import com.spr.adchannels.facebook.adapters.dynamiccreative.DynamicCreativeDisjointTypeAdapterTest;
import com.spr.paid.adchannels.facebook.adapters.FBPlacementBuilderPostEngagementTest;
import com.spr.paid.util.PaidUtilServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest1.class,
        CalculatorTest2.class,
        DynamicCreativeDisjointTypeAdapterTest.class,
        FBPlacementBuilderPostEngagementTest.class,
        PaidUtilServiceTest.class
})
public class CalculatorTestSuite2 {
}

