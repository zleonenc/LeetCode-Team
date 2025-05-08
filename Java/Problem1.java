class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0;

        int total_len = nums1.length + nums2.length;

        if (total_len % 2 == 1){
            median = binary_search(nums1, 0, nums2, 0, total_len / 2 + 1);
        }else{
            median = (binary_search(nums1, 0, nums2, 0, total_len / 2) + binary_search(nums1, 0, nums2, 0, total_len / 2 + 1)) / 2;
        }

        return median;
    }

    static double binary_search(int[] A, int A_start, int[] B, int B_start, int index){
        
        if (A_start >= A.length){
            return B[B_start + index - 1];
        }

        if (B_start >= B.length){
            return A[A_start + index - 1];
        }

        if (index == 1){
            return Math.min(A[A_start], B[B_start]);
        }

        int A_mid_index = A_start + index / 2 - 1;
        int A_mid_value = Integer.MAX_VALUE;
        if (A_mid_index < A.length){
            A_mid_value = A[A_mid_index];
        }

        int B_mid_index = B_start + index / 2 - 1;
        int B_mid_value = Integer.MAX_VALUE;
        if (B_mid_index < B.length){
            B_mid_value = B[B_mid_index];
        }

        if (A_mid_value < B_mid_value){
            return Solution.binary_search(A, A_start + index / 2, B, B_start, index - index / 2);
        }else{
            return Solution.binary_search(A, A_start, B, B_start + index / 2, index - index / 2);
        }
    } 
}