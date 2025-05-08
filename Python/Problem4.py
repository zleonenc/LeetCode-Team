class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        memo = [[None] * (len(p) + 1) for _ in range(len(s) + 1) ]
        def dp(i: int, j: int) -> bool:
            if memo[i][j] is not None:
                return memo[i][j]

            if j == len(p):
                result = i == len(s)
            elif i == len(s):
                if (len(p) - j) % 2 == 1:
                    result = False
                else:
                    result = all(p[k + 1] == '*' for k in range (j, len(p), 2))
            else:
                first_match = s[i] == p[j] or p[j] == '.'
                if j + 1 < len(p) and p[j + 1] == '*':
                    result = dp(i, j + 2) or (first_match and dp(i + 1, j))
                else:
                    result = first_match and dp(i + 1,j + 1)
            memo [i][j] = result
            return result
        return dp(0,  0)
        