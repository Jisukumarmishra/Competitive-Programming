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
