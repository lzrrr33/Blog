package sort;
/**
 * sort interface // 排序 java
 * author:lzrrr
 */
public interface Sort {
    /**
     * Main method arrays sorting algorithms
     *
     * @param unsorted - an array should be sorted
     * @param increase - increase or decrease
     * @return a sorted array
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted, boolean increase);
}
