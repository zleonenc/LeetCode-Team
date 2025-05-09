function lengthOfLongestSubstring(s: string): number {
    let maxLen = 0;
    let left = 0;
    const set = new Set<string>();

    for (let right = 0; right < s.length; right++) {
        while (set.has(s[right])) {
            set.delete(s[left]);
            left++;
        }

        set.add(s[right]);
        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
};
