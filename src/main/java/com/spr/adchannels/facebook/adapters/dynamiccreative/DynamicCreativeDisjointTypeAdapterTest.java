package com.spr.adchannels.facebook.adapters.dynamiccreative;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DynamicCreativeDisjointTypeAdapterTest {

    @Test
    public void testSegmentDynamicCreativeToFBAdAsset(){
        Assertions.assertEquals(3,4);
    }

    @Test
    public void testSegmentPlacementDynamicCreativeToFBAdAsset(){
        Assertions.assertEquals(10,100);
    }
}
