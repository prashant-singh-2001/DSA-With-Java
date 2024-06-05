class Sort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int start, int end) {
        if (start < end) {
            int m = start + (end - start) / 2;
            mergeSortHelper(arr, start, m);
            mergeSortHelper(arr, m + 1, end);
            merge(arr, start, m, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; ++i) {
            left[i] = arr[start + i];
        }
        for (int j = 0; j < n2; ++j) {
            right[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = start;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] arr) {
        if (arr.length <= 1)
            return;
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);

            heapify(arr, n, largest);
        }
    }
}

public class SortingTechs {
    private static int[] arr;

    public static void main(String[] args) {
        double tEBS, tEIS, tEMS, tEQS, tEHS;
        int size = 2000;
        resetArray(size);

        // Bubble Sort
        long start = System.nanoTime();
        Sort.bubbleSort(arr);
        long end = System.nanoTime();
        tEBS = end - start;
        if (isSorted())
            System.out.println("Time taken by BubbleSort: " + tEBS + " nanoseconds \n");

        // Reset array for next sorting
        resetArray(size);
        // Insertion Sort
        start = System.nanoTime();
        Sort.insertionSort(arr);
        end = System.nanoTime();
        tEIS = end - start;
        if (isSorted())
            System.out.println("Time taken by Insertion Sort: " + tEIS + " nanoseconds \n");

        // Reset array for next sorting
        resetArray(size);

        // Merge Sort
        start = System.nanoTime();
        Sort.mergeSort(arr);
        end = System.nanoTime();
        tEMS = end - start;
        if (isSorted())
            System.out.println("Time taken by Merge Sort: " + tEMS + " nanoseconds \n");

        // Reset array for next sorting
        resetArray(size);

        start = System.nanoTime();
        Sort.quickSort(arr);
        end = System.nanoTime();
        tEQS = end - start;
        if (isSorted())
            System.out.println("Time taken by Merge Sort: " + tEQS + " nanoseconds \n");

        resetArray(size);

        start = System.nanoTime();
        Sort.quickSort(arr);
        end = System.nanoTime();
        tEQS = end - start;
        if (isSorted())
            System.out.println("Time taken by Merge Sort: " + tEQS + " nanoseconds \n");

        tEBS /= size;
        tEIS /= size;
        tEMS /= size;
        tEQS /= size;

        System.out.println("Mean Bubble Sort Time: " + tEBS);
        System.out.println("Mean Insertion Sort Time: " + tEIS);
        System.out.println("Mean Merge Sort Time: " + tEMS);
        System.out.println("Mean Quick Sort Time: " + tEQS);

    }

    private static boolean isSorted() {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > arr[i + 1])
                return false;
        return true;
    }

    private static void resetArray(int size) {
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = randomInt(1, 200);
        }
    }

    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}