package Task6_TransportationWithRepoAndService.common.utils;

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

    public static void shiftElementsLeftInArray(Object[] arr, int beginIndex, int shift) {
        boolean beginIndexIsCorrect = (beginIndex > 0) && (beginIndex < arr.length);
        boolean shiftIsCorrect = (shift > 0) && (shift < arr.length);
        if (arr != null && beginIndexIsCorrect && shiftIsCorrect) {
            for (int i = beginIndex; i < arr.length - shift; i++) {
                arr[i] = arr[i + shift];
            }
            for (int i = arr.length - shift; i < arr.length; i++) {
                arr[i] = null;
            }
        }
        trimArray(arr);
    }
}
