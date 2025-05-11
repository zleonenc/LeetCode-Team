function findMedianSortedArrays(nums1: number[], nums2: number[]): number {
    const total_len = nums1.length + nums2.length;

    if (total_len % 2 === 1) {
        return binarySearch(nums1, 0, nums2, 0, Math.floor(total_len / 2) + 1);
    } else {
        return (
            binarySearch(nums1, 0, nums2, 0, total_len / 2) +
            binarySearch(nums1, 0, nums2, 0, total_len / 2 + 1)
        ) / 2;
    }
}

function binarySearch(
    A: number[],
    A_start: number,
    B: number[],
    B_start: number,
    index: number
): number {
    if (A_start >= A.length) {
        return B[B_start + index - 1];
    }

    if (B_start >= B.length) {
        return A[A_start + index - 1];
    }

    if (index === 1) {
        return Math.min(A[A_start], B[B_start]);
    }

    const A_mid_index = A_start + Math.floor(index / 2) - 1;
    let A_mid_value = Number.MAX_VALUE;
    if (A_mid_index < A.length) {
        A_mid_value = A[A_mid_index];
    }

    const B_mid_index = B_start + Math.floor(index / 2) - 1;
    let B_mid_value = Number.MAX_VALUE;
    if (B_mid_index < B.length) {
        B_mid_value = B[B_mid_index];
    }

    if (A_mid_value < B_mid_value) {
        return binarySearch(A, A_start + Math.floor(index / 2), B, B_start, index - Math.floor(index / 2));
    } else {
        return binarySearch(A, A_start, B, B_start + Math.floor(index / 2), index - Math.floor(index / 2));
    }
}
