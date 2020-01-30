package task18_transportation_local_date_and_streams.common.solutions.utils;

import task18_transportation_local_date_and_streams.common.business.domain.BaseEntity;

import java.util.function.Predicate;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static Predicate<BaseEntity> entitiesIdsAreEqualsPredicate(BaseEntity otherBaseEntity) {
        return baseEntity -> baseEntity.getId().equals(otherBaseEntity.getId());
    }
}
