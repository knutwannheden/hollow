package com.netflix.vms.transformer.input.api.gen.oscar;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.api.consumer.index.HollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

/**
 * @deprecated see {@link com.netflix.hollow.api.consumer.index.UniqueKeyIndex} which can be built as follows:
 * <pre>{@code
 *     UniqueKeyIndex<RolloutName, K> uki = UniqueKeyIndex.from(consumer, RolloutName.class)
 *         .usingBean(k);
 *     RolloutName m = uki.findMatch(k);
 * }</pre>
 * where {@code K} is a class declaring key field paths members, annotated with
 * {@link com.netflix.hollow.api.consumer.index.FieldPath}, and {@code k} is an instance of
 * {@code K} that is the key to find the unique {@code RolloutName} object.
 */
@Deprecated
@SuppressWarnings("all")
public class RolloutNamePrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<OscarAPI, RolloutName> implements HollowUniqueKeyIndex<RolloutName> {

    public RolloutNamePrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);
    }

    public RolloutNamePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getNonNullSchema("RolloutName")).getPrimaryKey().getFieldPaths());
    }

    public RolloutNamePrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public RolloutNamePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "RolloutName", isListenToDataRefresh, fieldPaths);
    }

    @Override
    public RolloutName findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getRolloutName(ordinal);
    }

}