package task16_transportation_lambda.common.solutions.utils;

import task16_transportation_lambda.common.business.domain.BaseEntity;

import java.util.function.Predicate;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static Predicate<BaseEntity> entitiesIdsAreEqualsPredicate(BaseEntity otherBaseEntity) {
        return baseEntity -> baseEntity.getId().equals(otherBaseEntity.getId());
    }
}
