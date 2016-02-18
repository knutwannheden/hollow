package com.netflix.vmsserver.videocollectionsdata;

import com.netflix.vms.transformer.hollowinput.EpisodeHollow;

import com.netflix.vms.transformer.hollowinput.SeasonHollow;
import com.netflix.vms.transformer.hollowinput.CountryVideoDisplaySetHollow;
import com.netflix.hollow.util.HashCodes;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowHierarchy {

    private final int topNodeId;
    private final int seasonIds[];
    private final int episodeIds[][];
    private final int hashCode;

    public ShowHierarchy(int topNodeId, CountryVideoDisplaySetHollow set, String countryCode, VideoCollectionsBuilder builder) {
        this.topNodeId = topNodeId;
        int hashCode = HashCodes.hashInt(topNodeId);

        List<SeasonHollow> seasons = set._getChildren();
        if(seasons != null) {
            seasons = new ArrayList<SeasonHollow>(seasons);
            Collections.sort(seasons, new Comparator<SeasonHollow>() {
                public int compare(SeasonHollow o1, SeasonHollow o2) {
                    return (int)o1._getSequenceNumber() - (int)o2._getSequenceNumber();
                }
            });

            int seasonIds[] = new int[seasons.size()];
            int episodeIds[][] = new int[seasons.size()][];

            int seasonCounter = 0;

            for(int i=0;i<seasons.size();i++) {
                SeasonHollow season = seasons.get(i);

                if(!builder.isChildNodeIncluded(season._getMovieId(), countryCode))
                    continue;


                seasonIds[seasonCounter] = (int)season._getMovieId();
                hashCode ^= seasonIds[i];
                hashCode = HashCodes.hashInt(hashCode);

                List<EpisodeHollow> episodes = new ArrayList<EpisodeHollow>(season._getChildren());
                Collections.sort(episodes, new Comparator<EpisodeHollow>() {
                    public int compare(EpisodeHollow o1, EpisodeHollow o2) {
                        return (int)o1._getSequenceNumber() - (int)o2._getSequenceNumber();
                    }
                });

                episodeIds[seasonCounter] = new int[episodes.size()];

                int episodeCounter = 0;

                for(int j=0;j<episodes.size();j++) {
                    EpisodeHollow episode = episodes.get(j);

                    if(!builder.isChildNodeIncluded(episode._getMovieId(), countryCode))
                        continue;

                    episodeIds[seasonCounter][episodeCounter] = (int)episode._getMovieId();
                    hashCode ^= episodeIds[seasonCounter][episodeCounter];
                    hashCode = HashCodes.hashInt(hashCode);
                    episodeCounter++;
                }

                if(episodeCounter != episodeIds[seasonCounter].length)
                    episodeIds[seasonCounter] = Arrays.copyOf(episodeIds[seasonCounter], episodeCounter);

                seasonCounter++;
            }

            if(seasonCounter != seasonIds.length) {
                seasonIds = Arrays.copyOf(seasonIds, seasonCounter);
                episodeIds = Arrays.copyOf(episodeIds, seasonCounter);
            }

            this.seasonIds = seasonIds;
            this.episodeIds = episodeIds;
        } else {
            this.seasonIds = new int[0];
            this.episodeIds = new int[0][];
        }

        this.hashCode = hashCode;
    }

    public int getTopNodeId() {
        return topNodeId;
    }

    public int[] getSeasonIds() {
        return seasonIds;
    }

    public int[][] getEpisodeIds() {
        return episodeIds;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ShowHierarchy))
            return false;
        ShowHierarchy other = (ShowHierarchy)obj;
        if(topNodeId != other.topNodeId)
            return false;
        if(!Arrays.equals(seasonIds, other.seasonIds))
            return false;
        for(int i=0;i<episodeIds.length;i++) {
            if(!Arrays.equals(episodeIds[i], other.episodeIds[i]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

}