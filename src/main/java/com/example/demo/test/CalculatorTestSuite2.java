package com.example.demo.test;

import com.spr.MongoPersistentPropertyCacheTest;
import com.spr.adchannels.facebook.adapters.dynamiccreative.DynamicCreativeDisjointTypeAdapterTest;
import com.spr.audience.MongoIndicesReportServiceTest;
import com.spr.audience.SalesforceReachTest;
import com.spr.audience.UIActivatedAudienceDecorationTest;
import com.spr.core.modelvalidation.ModelValidationFilterServiceTest;
import com.spr.core.modelvalidation.ModelValidationServiceTest;
import com.spr.core.modelvalidation.TextClassifierModelTest;
import com.spr.modules.advocacy.user.AdvocacyCommunityUserMangagementServiceTest;
import com.spr.paid.adchannels.facebook.adapters.FBPlacementBuilderPostEngagementTest;
import com.spr.paid.util.PaidUtilServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest1.class,
        CalculatorTest2.class,
        DynamicCreativeDisjointTypeAdapterTest.class,
        MongoIndicesReportServiceTest.class,
        SalesforceReachTest.class,
        UIActivatedAudienceDecorationTest.class,
        ModelValidationFilterServiceTest.class,
        TextClassifierModelTest.class,
        ModelValidationServiceTest.class,
        AdvocacyCommunityUserMangagementServiceTest.class,
        MongoPersistentPropertyCacheTest.class
})
public class CalculatorTestSuite2 {
}

