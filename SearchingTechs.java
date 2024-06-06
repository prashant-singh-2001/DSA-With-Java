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
    public static Result binarySearch(int[] arr,int target){
        return binarySearchHelper(arr,target,0,arr.length-1);
    }
    private static Result binarySearchHelper(int[] arr,int target,int l,int r){
        if(l>r) return new Result();
        int m=l+(r-l)/2;
        if(arr[m]==target) return new Result();
        else if(arr[m]>target) return binarySearchHelper(arr,target,l,m-1);
        else return binarySearchHelper(arr,target,m+1,r);
    }
}

public class SearchingTechs {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 47, 96, 5, 6, 6, 3, 5, 2, 5, 2, 25, 23, 4, 52, 5, 6, 3, 487, 9, 3, 4, 92, 3, 129,
                825, 19, 846, 216, 549, 265, 195, 2, 65, 621, 549841, 198, 18654, 984, 31, 984, 6, 498, 46, 98, 46, 6,
                2984, 26, 2195, 26, 519, 1, 5198, 198, 498, 9, 98, 4, 98, 4598298, 498, 161, 298 };
            Result rs=Search.linearSearch(arr, 52);
            if(rs.found){
                System.out.println("Data found at "+rs.index+1+" index");
            }
            else{
                System.out.println("Data is not in the array");
            }
        
    }
}
