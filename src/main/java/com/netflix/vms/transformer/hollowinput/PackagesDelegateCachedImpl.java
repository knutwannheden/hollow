package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.HollowObjectSchema;
import com.netflix.hollow.read.customapi.HollowTypeAPI;
import com.netflix.hollow.objects.delegate.HollowCachedDelegate;

public class PackagesDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, PackagesDelegate {

    private final Long packageId;
    private final Long movieId;
    private final int momentsOrdinal;
    private final int drmInfoOrdinal;
    private final int downloadablesOrdinal;
    private final int defaultS3PathComponentOrdinal;
   private PackagesTypeAPI typeAPI;

    public PackagesDelegateCachedImpl(PackagesTypeAPI typeAPI, int ordinal) {
        this.packageId = typeAPI.getPackageIdBoxed(ordinal);
        this.movieId = typeAPI.getMovieIdBoxed(ordinal);
        this.momentsOrdinal = typeAPI.getMomentsOrdinal(ordinal);
        this.drmInfoOrdinal = typeAPI.getDrmInfoOrdinal(ordinal);
        this.downloadablesOrdinal = typeAPI.getDownloadablesOrdinal(ordinal);
        this.defaultS3PathComponentOrdinal = typeAPI.getDefaultS3PathComponentOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public long getPackageId(int ordinal) {
        return packageId.longValue();
    }

    public Long getPackageIdBoxed(int ordinal) {
        return packageId;
    }

    public long getMovieId(int ordinal) {
        return movieId.longValue();
    }

    public Long getMovieIdBoxed(int ordinal) {
        return movieId;
    }

    public int getMomentsOrdinal(int ordinal) {
        return momentsOrdinal;
    }

    public int getDrmInfoOrdinal(int ordinal) {
        return drmInfoOrdinal;
    }

    public int getDownloadablesOrdinal(int ordinal) {
        return downloadablesOrdinal;
    }

    public int getDefaultS3PathComponentOrdinal(int ordinal) {
        return defaultS3PathComponentOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public PackagesTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (PackagesTypeAPI) typeAPI;
    }

}