package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.objects.HollowList;
import com.netflix.hollow.HollowListSchema;
import com.netflix.hollow.objects.delegate.HollowListDelegate;
import com.netflix.hollow.objects.generic.GenericHollowRecordHelper;

public class ConsolidatedVideoRatingListHollow extends HollowList<ConsolidatedVideoRatingHollow> {

    public ConsolidatedVideoRatingListHollow(HollowListDelegate delegate, int ordinal) {
        super(delegate, ordinal);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ConsolidatedVideoRatingHollow instantiateElement(int ordinal) {
        return (ConsolidatedVideoRatingHollow) api().getConsolidatedVideoRatingHollow(ordinal);
    }

    @Override
    public boolean equalsElement(int elementOrdinal, Object testObject) {
        return GenericHollowRecordHelper.equalObject(getSchema().getElementType(), elementOrdinal, testObject);
    }

    public VMSHollowVideoInputAPI api() {
        return typeApi().getAPI();
    }

    public ConsolidatedVideoRatingListTypeAPI typeApi() {
        return (ConsolidatedVideoRatingListTypeAPI) delegate.getTypeAPI();
    }

}