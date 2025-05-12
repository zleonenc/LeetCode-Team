class Solution:
    @staticmethod
    def binary_search(A, A_start, B, B_start, index):
        if A_start >= len(A):
            return B[B_start + index - 1]

        if B_start >= len(B):
            return A[A_start + index - 1]

        if index == 1:
            return min(A[A_start], B[B_start])

        A_mid_index = A_start + index // 2 - 1
        A_mid_value = float('inf')

        if A_mid_index < len(A):
            A_mid_value = A[A_mid_index]

        B_mid_index = B_start + index // 2 - 1
        B_mid_value = float('inf')

        if B_mid_index < len(B):
            B_mid_value = B[B_mid_index]

        if A_mid_value < B_mid_value:
            return Solution.binary_search(A, A_start + index // 2, B, B_start, index - index // 2)
        else:
            return Solution.binary_search(A, A_start, B, B_start + index // 2, index - index // 2)

    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        total_len = len(nums1) + len(nums2)
        if total_len % 2 == 1:
            return self.binary_search(nums1, 0, nums2, 0, total_len // 2 + 1)
        else:
            left = self.binary_search(nums1, 0, nums2, 0, total_len // 2)
            right = self.binary_search(nums1, 0, nums2, 0, total_len // 2 + 1)
            return (left + right) / 2.0