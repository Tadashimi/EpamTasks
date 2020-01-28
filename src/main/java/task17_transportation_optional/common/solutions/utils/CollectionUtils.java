package task17_transportation_optional.common.solutions.utils;

import java.util.Collection;
import java.util.function.Predicate;

public final class CollectionUtils {

    private CollectionUtils() {

    }

    public static void printCollection(Collection<?> collection) {
        collection.forEach(System.out::println);

    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static Predicate<Collection<?>> collectionIsEmptyPredicate(boolean mustBeEmpty) {
        if (mustBeEmpty) {
            return collection -> collection == null || collection.isEmpty();
        }
        return collection -> collection != null && !collection.isEmpty();
    }

}
