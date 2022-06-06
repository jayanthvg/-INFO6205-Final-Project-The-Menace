package edu.neu.info6205;

import java.util.HashMap;

public class Rewards {

    public void rewards(String result, HashMap<String, int[]> patterns, HashMap<String, Integer> picked, int w, int d, int l) {
        if(result.equals("X wins"))
            picked.forEach((k, v) -> patterns.get(k)[v] = patterns.get(k)[v] + w);

        else if(result.equals("Draw"))
            picked.forEach((k, v) -> patterns.get(k)[v] = patterns.get(k)[v] + d);

        else if(result.equals("O wins"))
            picked.forEach((k, v) -> patterns.get(k)[v] = patterns.get(k)[v] - l);
    }
}
