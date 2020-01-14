package task8_transportation_update_and_compare.common.solutions.utils;

import java.util.Collection;

public class CollectionUtils {

    private CollectionUtils() {

    }

    public static void printCollection(Collection<?> collection) {
        for (Object obj : collection) {
            System.out.println(obj.toString());
        }
    }
}
