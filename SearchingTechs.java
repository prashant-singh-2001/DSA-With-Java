import java.util.Arrays;

class Result {
    int data;
    int index;
    boolean found;

    Result() {
        found = false;
    }

    Result(int data, int index) {
        this.data = data;
        this.index = index;
        found = true;
    }

}

class Search {
    public static Result linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return new Result(target, i);
            }
        }
        return new Result();
    }

    public static Result binarySearch(int[] arr, int target) {

        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                return new Result(target, m);
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return new Result();
    }

    public static Result interporlationSearch(int[] arr, int target) {

        int l = 0, r = arr.length - 1;
        while (l <= r && arr[l] <= target && arr[r] >= target) {
            int pos = l + (((target - arr[l]) * (r - l)) / (arr[r] - arr[l]));
            if (arr[pos] == target)
                return new Result(target, pos);
            else if (arr[pos] > target)
                l = pos - 1;
            else
                r = pos + 1;
        }

        return new Result();
    }
}

public class SearchingTechs {
    public static void main(String[] args) {
        int[] arr = randomArray(100);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Result rs = Search.linearSearch(arr, 52);
        if (rs.found) {
            System.out.println("Data found at " + rs.index  + " index");
        } else {
            System.out.println("Data is not in the array");
        }
        rs = Search.binarySearch(arr, 52);
        if (rs.found) {
            System.out.println("Binary: Data found at " + rs.index  + " index");
        } else {
            System.out.println("Data is not in the array");
        }
        rs = Search.interporlationSearch(arr, 52);
        if (rs.found) {
            System.out.println("Interpolation : Data found at " + rs.index + " index");
        } else {
            System.out.println("Data is not in the array");
        }
    }

    private static int[] randomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = randomInt(1, 200);
        }
        return arr;
    }

    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
