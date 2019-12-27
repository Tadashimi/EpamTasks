package main.java.Task6_TransportationWithRepoAndService.common.utils;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static void copyArray(Object[] src, Object[] dest) {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    public static void printArray(Object[] arr) {
        for (Object obj : arr) {
            if (obj != null) {
                System.out.println(obj);
            }
        }
    }

    public static void trimArray(Object[] arr) {
        int i = 0;
        while (arr[i] != null) {
            i++;
        }
        Object[] tempArray = new Object[i];
        System.arraycopy(arr, 0, tempArray, 0, i);
        arr = tempArray;
    }

    public static void removeElementFromArray(Object[] arr, int index) {
        int indexOfLatElement = arr.length - 1;
        System.arraycopy(arr, index + 1, arr, index, indexOfLatElement - index);
        arr[indexOfLatElement] = null;
        trimArray(arr);
    }
}
