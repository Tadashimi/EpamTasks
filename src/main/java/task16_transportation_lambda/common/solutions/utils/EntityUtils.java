package task16_transportation_lambda.common.solutions.utils;

import task16_transportation_lambda.common.business.domain.BaseEntity;

import java.util.Objects;
import java.util.function.Predicate;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static Predicate<Object> entityIsNullPredicate(boolean mustBeNull){
        if (mustBeNull) {
            return Objects::isNull;
        }
        return Objects::nonNull;
    }

    public static Predicate<BaseEntity> entitiesIdsAreEqualsPredicate(BaseEntity otherBaseEntity) {
        return baseEntity -> baseEntity.getId().equals(otherBaseEntity.getId());
    }
}
