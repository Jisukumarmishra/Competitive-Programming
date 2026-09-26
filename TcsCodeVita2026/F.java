package TcsCodeVita2026;
import java.util.Scanner;
import java.util.Arrays;

public class F {
    static final int[][] P_MAP = new int[18][24];

    // Precompute all 18 possible transformations using standard Vertical Cross Net layout
    static void initializeTransformations() {
        int[][] core = new int[6][24];
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 24; y++) {
                core[x][y] = y;
            }
        }

        // Face 0: Top (U)
        applyCycle(core[0], 0, 1, 3, 2);
        applyCycle(core[0], 16, 15, 20, 4);
        applyCycle(core[0], 17, 14, 21, 5);

        // Face 1: Front (F)
        applyCycle(core[1], 4, 5, 7, 6);
        applyCycle(core[1], 2, 20, 9, 19);
        applyCycle(core[1], 17, 3, 22, 8);

        // Face 2: Down (D)
        applyCycle(core[2], 8, 9, 11, 10);
        applyCycle(core[2], 6, 22, 13, 18);
        applyCycle(core[2], 19, 7, 23, 12);

        // Face 3: Back (B)
        applyCycle(core[3], 15, 14, 12, 13);
        applyCycle(core[3], 1, 16, 10, 23);
        applyCycle(core[3], 21, 0, 18, 11);

        // Face 4: Left (L)
        applyCycle(core[4], 16, 17, 19, 18);
        applyCycle(core[4], 0, 4, 8, 12);
        applyCycle(core[4], 14, 2, 6, 10);

        // Face 5: Right (R)
        applyCycle(core[5], 20, 21, 23, 22);
        applyCycle(core[5], 3, 15, 11, 7);
        applyCycle(core[5], 5, 1, 13, 9);

        // Expand to include 180 (double) and 270 (counter-clockwise) turns
        for (int f = 0; f < 6; f++) {
            System.arraycopy(core[f], 0, P_MAP[f], 0, 24);
            for (int s = 0; s < 24; s++) {
                P_MAP[f + 6][s] = core[f][core[f][s]];         // 180 degrees
                P_MAP[f + 12][s] = core[f][core[f][core[f][s]]]; // 270 degrees
            }
        }
    }

    static void applyCycle(int[] target, int p1, int p2, int p3, int p4) {
        target[p2] = p1;
        target[p3] = p2;
        target[p4] = p3;
        target[p1] = p4;
    }

    // Examines if the configuration matches a single corner twist signature
    static String identifyTwist(byte[] q, int offset, char[] colorMap) {
        StringBuilder anomalies = new StringBuilder();
        for (int f = 0; f < 6; f++) {
            int base = offset + f * 4;
            int[] freqs = new int[6];
            for (int a = 0; a < 4; a++) {
                freqs[q[base + a]]++;
            }
            int peak = 0;
            for (int c = 0; c < 6; c++) {
                if (freqs[c] > peak) peak = freqs[c];
            }
            
            // For a single corner twist, exactly 3 faces will have a 3-1 color split. Rest will be 4-0
            if (peak < 3) return null;
            
            for (int c = 0; c < 6; c++) {
                if (freqs[c] == 1) {
                    anomalies.append(colorMap[c]);
                }
            }
        }
        
        if (anomalies.length() == 3) {
            char[] finalColors = anomalies.toString().toCharArray();
            Arrays.sort(finalColors); // Enforces alphabetical order constraint requested by prompt
            return new String(finalColors);
        }
        return null;
    }

    // Custom Open-Addressing native set strictly limiting GC memory allocations
    static class LongHashSet {
        long[] keys;
        int capacity;
        int size;

        LongHashSet(int capacity) {
            this.capacity = capacity;
            keys = new long[capacity];
            Arrays.fill(keys, -1L);
        }

        boolean add(long key) {
            int idx = (int) (hash(key) & (capacity - 1));
            while (keys[idx] != -1L) {
                if (keys[idx] == key) return false;
                idx = (idx + 1) & (capacity - 1);
            }
            keys[idx] = key;
            size++;
            if (size * 2 > capacity) resize();
            return true;
        }

        long hash(long x) {
            x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L;
            x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL;
            return x ^ (x >>> 31);
        }

        void resize() {
            long[] oldKeys = keys;
            capacity <<= 1;
            keys = new long[capacity];
            Arrays.fill(keys, -1L);
            size = 0;
            for (long key : oldKeys) {
                if (key != -1L) add(key);
            }
        }
    }

    public static void main(String[] args) {
        initializeTransformations();
        Scanner sc = new Scanner(System.in);
        
        char[] colorMap = new char[6];
        int[] charToColor = new int[256];
        Arrays.fill(charToColor, -1);
        int colorsCount = 0;
        
        // Massive 1D flat Byte-array to act as the BFS Queue without initializing Object properties (very fast)
        byte[] queue = new byte[300000 * 24]; 
        
        long rootState = 0;
        for (int idx = 0; idx < 24; idx++) {
            if (!sc.hasNext()) return;
            char c = sc.next().charAt(0);
            if (charToColor[c] == -1) {
                charToColor[c] = colorsCount;
                colorMap[colorsCount] = c;
                colorsCount++;
            }
            queue[idx] = (byte) charToColor[c];
            rootState = rootState * 6 + queue[idx];
        }

        String directCheck = identifyTwist(queue, 0, colorMap);
        if (directCheck != null) {
            System.out.println(directCheck);
            return;
        }

        LongHashSet explored = new LongHashSet(1 << 18);
        explored.add(rootState);

        int head = 0, tail = 1;
        int currentLevel = 0;
        
        // Allowed limits are exactly strictly <= 4 configurations
        while (head < tail && currentLevel < 4) {
            int levelSize = tail - head;
            for (int i = 0; i < levelSize; i++) {
                int currOffset = head * 24;
                head++;

                for (int move = 0; move < 18; move++) {
                    long childState = 0;
                    for (int k = 0; k < 24; k++) {
                        childState = childState * 6 + queue[currOffset + P_MAP[move][k]];
                    }

                    if (explored.add(childState)) {
                        int childOffset = tail * 24;
                        for (int k = 0; k < 24; k++) {
                            queue[childOffset + k] = queue[currOffset + P_MAP[move][k]];
                        }
                        
                        String result = identifyTwist(queue, childOffset, colorMap);
                        if (result != null) {
                            System.out.println(result);
                            return;
                        }
                        
                        tail++;
                    }
                }
            }
            currentLevel++;
        }
    }
}