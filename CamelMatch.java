// Problem 1023. Camelcase Matching
// Time Complexity : O(nq)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            boolean flag = true;
            int i = 0; // pointer for query
            int j = 0; // pointer for pattern

            while (i < query.length()) {
                char qChar = query.charAt(i);
                if (j < pattern.length() && qChar == pattern.charAt(j)) {
                    i++;
                    j++;
                } else if (Character.isUpperCase(qChar)) {
                    flag = false;
                    break;
                } else {
                    i++;
                }
            }

            if (j != pattern.length()) {
                flag = false;
            }

            result.add(flag);
        }
        return result;
    }
}