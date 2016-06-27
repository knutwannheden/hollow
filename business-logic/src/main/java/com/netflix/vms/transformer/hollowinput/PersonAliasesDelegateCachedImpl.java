package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.HollowObjectSchema;
import com.netflix.hollow.read.customapi.HollowTypeAPI;
import com.netflix.hollow.objects.delegate.HollowCachedDelegate;

@SuppressWarnings("all")
public class PersonAliasesDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, PersonAliasesDelegate {

    private final Long aliasId;
    private final int nameOrdinal;
   private PersonAliasesTypeAPI typeAPI;

    public PersonAliasesDelegateCachedImpl(PersonAliasesTypeAPI typeAPI, int ordinal) {
        this.aliasId = typeAPI.getAliasIdBoxed(ordinal);
        this.nameOrdinal = typeAPI.getNameOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public long getAliasId(int ordinal) {
        return aliasId.longValue();
    }

    public Long getAliasIdBoxed(int ordinal) {
        return aliasId;
    }

    public int getNameOrdinal(int ordinal) {
        return nameOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public PersonAliasesTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (PersonAliasesTypeAPI) typeAPI;
    }

}