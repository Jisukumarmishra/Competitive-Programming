import java.util.*;
public class CoordinateCompression {
    public static void main(String[] args) {

        int[] arr = {100, 500, 100, 200};

        // step 1: copy + sort
        int[] temp = arr.clone();
        Arrays.sort(temp);

        // step 2: map unique values
        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;

        for (int x : temp) {
            if (!map.containsKey(x)) {
                map.put(x, idx++);
            }
        }

        // step 3: replace values
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

        // compressed array
        System.out.println(Arrays.toString(arr));
    }
}
