# Small fixed range -> Counting Sort More Optimized O(n+k) // freq Array bnate hai

# General case -> Arrays.sort() More Optimized O(nlogn)

# Max Value In an Array

int max = 0;

for(int cost : costs){
max = Math.max(max, cost);
}

# Frequency Array or OccurenceAray

--calulate the maxime element of the array
--ushi ki size ka freqArray Bnao
int[] freq = new int [max+1]; // freq[i] = Value i kitni baar aayi hai.

for(int cost : costs) {
freq[cost]++;
}

char ch = s.charAt(i)l

# Character.isLetterOrDigit(ch)

this check at ch there is Letteror digit / operands


# Jab tumhare paas multiple elements ko order/sort karna ho aur ek operation mein sirf nearby/adjacent elements ko compare-swap kar rahe ho, to:

Outer loop  → multiple passes
Inner loop  → current pass mein adjacent elements check

for(int pass = 0; pass < n; pass++) {

    for(int j = 0; j < n - 1; j++) {

        if(arr[j] > arr[j + 1]) {
            // swap
        }
    }
}


. Kya ek pass mein poora array guaranteed sorted ho jayega?
→ Agar nahi, outer loop chahiye.


