package sort;

/**
 * Quick Sort // 快速排序 java
 * author：lzrrr
 * time complexity: O(n*log(n))
 * space complexity: O(log(n)) // use stack space
 * 特点：不稳定，最坏情况时间复杂度为 O(n^2)，且需要递归，用到栈空间
 */
public class QuickSort implements Sort{
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr,boolean increase){
        quicksort(arr,0,arr.length-1);
        if (!increase){
            for(int i=0;i<arr.length/2;i++)
                SortUtils.swap(arr,i,arr.length-1-i);
        }
        return arr;
    }

    public <T extends Comparable<T>> void quicksort(T[] arr,int i ,int j){
        if (i < j) {
            T x = arr[i];
            int i1=i,j1=j;
            while(i<j) {
                while (i < j && arr[j].compareTo(x) >= 0) j--;
                if (i < j)
                    arr[i++] = arr[j];
                while (i < j && arr[i].compareTo(x) <= 0) i++;
                if (i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = x;
            quicksort(arr,i1,i-1);
            quicksort(arr,i+1,j1);
        }


    }
    public static void main(String args[]){
        Integer[] arr = {4,23, 2, 78, 1, 54, 231, 9, 12};

        QuickSort QuickSort = new QuickSort();
        // increase
        SortUtils.print(arr);
        Integer[] sorted = QuickSort.sort(arr, true);

        // Output => 1	  4	 6	9	12	23	54	78	231

        SortUtils.print(sorted);

        // String Input ; decrease
        String[] strings = {"c", "a", "e", "b", "d"};
        String[] sortedStrings = QuickSort.sort(strings, false);

        //Output => a	b	 c  d	e
        SortUtils.print(sortedStrings); // use sortutils method
    }
}
