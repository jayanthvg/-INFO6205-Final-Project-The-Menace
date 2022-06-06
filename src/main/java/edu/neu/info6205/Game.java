package edu.neu.info6205;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Game {

    public String state = "NNNNNNNNN";
    public String modifiedState = "NNNNNNNNN";
    public List<String> states = new ArrayList<String>(){{add("NNNNNNNNN");}};
    public List<String> modifiedStates = new ArrayList<String>(){{add("NNNNNNNNN");}};
    public HashMap<String, int[]> patterns = new  HashMap<String, int[]>();
    public HashMap<String, Integer> picked = new HashMap<String, Integer>();

    public void watch(double p, int w, int d, int l, boolean watch, Logger logger) throws Exception {
        String result = train(p, w, d, l, watch, logger);
        System.out.println(result);
        logger.info(result+"\n");
    }

    public String train(double p, int w, int d, int l, boolean watch, Logger logger) throws Exception {

        Operations op = new Operations();

        if(watch) {
            String temp = "*****Initial state*****\n";
            System.out.println("\n"+temp);
            logger.info(temp);
            op.printArray(op.stringToArray(state), logger);
        }
        patterns = CSVreadwrite.readCSVline("beads_data.csv");

        int step=0;
        while(op.gameEndCheck(op.stringToArray(state)).equals("In Progress")) {

            //Menace gameplay
            step++;
            if(step == 9) {
                StringBuilder sb = new StringBuilder(state);
                for(int i=0; i<state.length(); i++)
                {
                    if(sb.charAt(i) == 'N') {
                        sb.setCharAt(i, 'X');
                        state = sb.toString();
                        modifiedState = state;
                        states.add(state);
                        modifiedStates.add(modifiedState);
                    }
                }
            }
            else {
                List<String> allStates = new ArrayList<>();
                allStates = op.nextState(patterns, picked, state, states, modifiedStates);
                state = allStates.get(1);
                modifiedState = allStates.get(0);
            }

            if(watch) {
                String temp = "*****Menace Machine Move-" + step + "*****\n";
                System.out.println(temp);
                logger.info(temp);
                op.printArray(op.stringToArray(state), logger);
            }
            if(!op.gameEndCheck(op.stringToArray(state)).equals("In Progress")) break;


            //Human gameplay
            step++;
            Human h = new Human();
            StringBuilder string = new StringBuilder(state);
            string.setCharAt(h.humanMove(state,p), 'O');
            state = string.toString();
            modifiedState = state;
            states.add(state);
            modifiedStates.add(modifiedState);

            if(watch) {
                String temp = "*****Human Move-" + step + "*****\n";
                System.out.println(temp);
                logger.info(temp);
                op.printArray(op.stringToArray(state), logger);
            }
            if(!op.gameEndCheck(op.stringToArray(state)).equals("In Progress")) break;
        }

        //Rewarding
        Rewards r = new Rewards();
        r.rewards(op.gameEndCheck(op.stringToArray(state)), patterns, picked, w, d, l);
//      System.out.println(op.gameEndCheck(op.stringToArray(state)));


        //Updating patterns and beads count file
        CSVreadwrite.writeCSV("beads_data.csv",
                                "Pattern,Blue,Red,Yellow,Black,White,Orange,Pink,Green,Brown", false);
        patterns.forEach((k, v)
                -> {
            try {
                CSVreadwrite.writeCSV("beads_data.csv",
                        k+","+ Arrays.toString(v).substring(1,Arrays.toString(v).length()-1), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return op.gameEndCheck(op.stringToArray(state));
    }
}
