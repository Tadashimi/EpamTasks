package task20_transportation_data_base.common.solutions.utils;

import task20_transportation_data_base.common.business.domain.BaseEntity;

import java.util.function.Predicate;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static Predicate<BaseEntity> entitiesIdsAreEqualsPredicate(BaseEntity otherBaseEntity) {
        return baseEntity -> baseEntity.getId().equals(otherBaseEntity.getId());
    }
}
