package com.netflix.vms.transformer.input.api.gen.videoAward;

import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.provider.HollowFactory;
import com.netflix.hollow.core.read.dataaccess.HollowTypeDataAccess;

@SuppressWarnings("all")
public class VideoAwardMappingHollowFactory<T extends VideoAwardMapping> extends HollowFactory<T> {

    @Override
    public T newHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T)new VideoAwardMapping(((VideoAwardMappingTypeAPI)typeAPI).getDelegateLookupImpl(), ordinal);
    }

    @Override
    public T newCachedHollowObject(HollowTypeDataAccess dataAccess, HollowTypeAPI typeAPI, int ordinal) {
        return (T)new VideoAwardMapping(new VideoAwardMappingDelegateCachedImpl((VideoAwardMappingTypeAPI)typeAPI, ordinal), ordinal);
    }

}