import java.util.*;

static int lisOptimal(int[] arr) {
    ArrayList<Integer> list = new ArrayList<>();

    for (int num : arr) {
        int idx = Collections.binarySearch(list, num);

        if (idx < 0) idx = -(idx + 1);

        if (idx == list.size()) {
            list.add(num);
        } else {
            list.set(idx, num);
        }
    }

    return list.size();
}