package sort;

/**
 * bubble sort // 冒泡排序 java
 * author:lzrrr
 * time complexity: O(n^2)
 * space complexity: O(1)
 * 特点：当一次遍历没有做交换时，则说明已经有序，可以退出程序。
 */
public class BubbleSort implements Sort{
    @Override // override sort method
    public <T extends Comparable<T>> T[] sort(T[] arr, boolean increase){

        for(int i=arr.length-1;i>1;i--) {
            boolean swaped= false;
            for (int j = 0; j < i; j++) {
                if (increase) {
                    if (arr[j].compareTo(arr[j + 1]) > 0) {
                        SortUtils.swap(arr, j, j + 1); // increase
                        swaped = true;
                    }
                } else {
                    if (arr[j].compareTo(arr[j + 1]) < 0) {
                        SortUtils.swap(arr, j, j + 1); // decrease
                        swaped = true;
                    }
                }
            }
            if (!swaped) break; // 没有交换则退出
        }
        return arr;
    }
    public static void main(String args[]){
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        BubbleSort BubbleSort = new BubbleSort();
        // increase
        Integer[] sorted = BubbleSort.sort(arr, true);

        // Output => 1	  4	 6	9	12	23	54	78	231
        SortUtils.print(sorted);

        // String Input ; decrease
        String[] strings = {"c", "a", "e", "b", "d"};
        String[] sortedStrings = BubbleSort.sort(strings, false);

        //Output => a	b	 c  d	e
        SortUtils.print(sortedStrings); // use sortutils method
    }
}

