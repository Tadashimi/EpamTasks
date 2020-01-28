package task17_transportation_optional.common.solutions.utils;

import task17_transportation_optional.common.business.domain.BaseEntity;

import java.util.function.Predicate;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static Predicate<BaseEntity> entitiesIdsAreEqualsPredicate(BaseEntity otherBaseEntity) {
        return baseEntity -> baseEntity.getId().equals(otherBaseEntity.getId());
    }
}
