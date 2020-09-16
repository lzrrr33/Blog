package sort;

/**
 * selection sort // 选择排序 java
 * author：lzrrr
 * time complexity: O(n^2)
 * space complexity: O(1)
 * 特点：每一次迭代不改变其他元素的顺序
 */

public class SelectionSort  implements Sort{
    /**
    override the sort method
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr, boolean increase) {
        for(int i=arr.length-1 ;i>0 ;i--){
            int min_index = 0;
            for(int j=0 ;j<=i ;j++){
                if(increase) {
                    if (arr[j].compareTo(arr[min_index]) > 0) min_index = j;

                }else{
                    if (arr[j].compareTo(arr[min_index]) < 0) min_index = j;
                }
            }
            //System.out.println(arr[min_index]);
            if(min_index!=i)
                SortUtils.swap(arr, min_index, i); // use the sortutils swap method
        }
        return arr;
    }

    // test
    public static void main(String[] args) {

        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        SelectionSort selectionSort = new SelectionSort();
        // increase
        Integer[] sorted = selectionSort.sort(arr, true);

        // Output => 1	  4	 6	9	12	23	54	78	231
        SortUtils.print(sorted);

        // String Input ; decrease
        String[] strings = {"c", "a", "e", "b", "d"};
        String[] sortedStrings = selectionSort.sort(strings, false);

        //Output => a	b	 c  d	e
        SortUtils.print(sortedStrings); // use sortutils method
    }
}