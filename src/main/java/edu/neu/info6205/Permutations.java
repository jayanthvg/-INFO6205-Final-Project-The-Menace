package edu.neu.info6205;

import java.util.HashSet;
import java.util.Set;


public class Permutations {

    public Set<String> hash_Set = new HashSet<>();

    // Recursive function to generate permutations of the string
    public void allPermutations(String str, String ans)
    {
        // If string is empty
        if (str.length() == 0) {
            // Add the generated permutation to the set in order to avoid duplicates
        	Operations ref = new Operations();
        	if(ref.gameEndCheck(ref.stringToArray(ans)).compareTo("In Progress") == 0)
            hash_Set.add(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            // ith character of str
            char ch = str.charAt(i);
            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) + str.substring(i + 1);
            // Recursive call
            allPermutations(ros, ans + ch);
        }
    }
}