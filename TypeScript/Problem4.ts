function isMatch(s: string, p: string): boolean {
    const memo: Record<string, boolean> = {};

    function dp(i: number, j: number): boolean {
        const key = `${i},${j}`;
        if (key in memo) return memo[key];

        let result: boolean;

        if (j === p.length) {
            result = i === s.length;
        } else {
          const firstMatch = i < s.length  && (s[i] === p[j] || p[j] === '.');

          if  (j + 1 < p.length && p[j + 1]  ===  '*') {
            result = dp(i, j + 2) ||  (firstMatch && dp(i +  1, j));
          } else {
            result = firstMatch  &&  dp(i + 1, j +1);
          }
        }

        memo[key] = result;
        return result;
    }

    return dp(0,0);
};