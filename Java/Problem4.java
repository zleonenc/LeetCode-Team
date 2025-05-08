public class Solution{
    public boolean isMatch(String s, String p){
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];

        return dp(0, 0, s, p, memo);
    }

    private boolean dp(int i, int j,String s, String p, Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean result;

        if (j == p.length()){
            result = (i == s.length());
        } else if (i == s.length()) {

            if ((p.length() - j) % 2 != 0){
                result = false;
            } else {
                result =  true;
                for (int k = j; k + 1 < p.length(); k += 2) {
                    if (p.charAt(k + 1) != '*') {
                        result = false;
                        break;
                    }
                }
            }
        } else {
            boolean firstMatch = (s.charAt(i) == p.charAt(j)  || p.charAt(j)  == '.');

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                result = dp(i, j + 2, s, p, memo) || (firstMatch && dp(i + 1, j, s, p, memo));
            } else {
                result = firstMatch && dp(i + 1, j +1, s, p, memo);
            }
        }
        memo[i][j] =  result;
    return result;
    } 
}