package edu.neu.info6205;

import org.slf4j.Logger;

import java.util.*;


public class Operations {

	//Pick a position for next move among all the possibles
	public int pickBead(int[] beads) {
		int[] cum = {0,0,0,0,0,0,0,0,0};
		int sum = 0;
		for(int i=0; i<beads.length; i++){
			cum[i] = sum + beads[i];
			sum = cum[i];
		}

		int p = (int)Math.floor(Math.random()*101);

		int index = beads.length-1;
		while(sum*p/100.0<=cum[index]) {
			if(sum*p/100.0==cum[index]) {if(cum[index]==0)break;};
			index--;
			if(index<0) break;
		}
		return index+1;
	}

	//Create a string with zeros in existing positions and alpha value in next possible positions
	public String beadPositionNumbers(String s1, int beads) {
		String beadPositions = beadPositions(s1);
		String numberPositions = "";

		for(String s: beadPositions.split(""))
			if(s.equals("B")) numberPositions+=(beads+",");
			else numberPositions+="0,";

		return numberPositions;
	}

	//Find all the possible next positions of a state by ignoring similar positions
	public String beadPositions(String s1) {
		Set<String> strings = new HashSet<>();

		for(int i=0; i<s1.length(); i++)
		{
			StringBuilder string = new StringBuilder(s1);
			if(s1.charAt(i) == 'N') {
				string.setCharAt(i, 'B');
				strings.add(string.toString());
				}
		}

	    Iterator<String> iter = strings.iterator();

	    while (iter.hasNext()) {
	        String s = iter.next();
	        if(!similarArrays(s).isEmpty())
	        A:
		    	for(String t : similarArrays(s)) {
		    		if(strings.contains(t)) {
		    			iter.remove();
		    			break A;
		    		}
		    	}
	    }

	    StringBuilder string = new StringBuilder(s1);
		strings.forEach((n) -> string.setCharAt(n.indexOf("B"),'B'));
		return string.toString();
	}

	//Find all possible permutations of a string removing similar patterns
	public Set<String> finalArrays(String s1) {

		Permutations per = new Permutations();
		per.allPermutations(s1, "");

	    Iterator<String> iter = per.hash_Set.iterator();

	    while (iter.hasNext()) {
	        String s = iter.next();
	        if(!similarArrays(s).isEmpty())
	        A:
		    	for(String t : similarArrays(s)) {
		    		if(per.hash_Set.contains(t)) {
		    			iter.remove();
		    			break A;
		    		}
		    	}
	    }
	    return per.hash_Set;
	}

	//Find similar arrays to an array through mirror images and rotations
	public Set<String> similarArrays(String s1) {
		// TODO Auto-generated method stub
	    Set<String> strings = new HashSet<>();

		String[][] s2 = rotateArray(stringToArray(s1));
		if(s1.compareTo(arrayToString(s2)) != 0) strings.add(arrayToString(s2));

		String[][] s3 = rotateArray(s2);
		if(s1.compareTo(arrayToString(s3)) != 0) strings.add(arrayToString(s3));

		String[][] s4 = rotateArray(s3);
		if(s1.compareTo(arrayToString(s4)) != 0) strings.add(arrayToString(s4));

		String[][] s5 = mirrorImage(stringToArray(s1));
		if(s1.compareTo(arrayToString(s5)) != 0) strings.add(arrayToString(s5));

		String[][] s6 = rotateArray(s5);
		if(s1.compareTo(arrayToString(s6)) != 0) strings.add(arrayToString(s6));

		String[][] s7 = rotateArray(s6);
		if(s1.compareTo(arrayToString(s7)) != 0) strings.add(arrayToString(s7));

		String[][] s8 = rotateArray(s7);
		if(s1.compareTo(arrayToString(s8)) != 0) strings.add(arrayToString(s8));

		return strings;
	}

	//Convert an array to a String
	public String arrayToString(String[][] a) {
	String key = "";
	for(int i = 0; i<3; i++)
		for(int j = 0; j<3; j++)
			key = key + a[i][j];
	return key;
	}

	//Convert String to an Array
	public String[][] stringToArray(String a) {
		String[][] b = new String[3][3];
		String[] a1 = a.split("");
		int k = 0;

		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				b[i][j] = a1[k++];
		return b;
	}

	//Rotate an array clockwise by 90 degrees
	public String[][] rotateArray(String[][] a) {
		String[][] b = new String[3][3];

		for(int j = 0; j<3; j++)
			for(int i = 2; i>=0;i--)
				b[j][2-i] = a[i][j];
		return b;
	}

	//Mirror image of an array w.r.t vertical axis
	public String[][] mirrorImage(String[][] a) {
		String[][] b = new String[3][3];

		for(int j = 2; j>=0; j--)
			for(int i = 0; i<3;i++)
				b[i][2-j] = a[i][j];
		return b;
	}

	//Check if the current state is the end of game or not
	public String gameEndCheck(String[][] a) {
		String X = "Menace Wins";
		String O = "Human Wins";
		String D = "Draw";
		String P = "In Progress";

		if(a[0][0].compareTo(a[0][1]) == 0 && a[0][1].compareTo(a[0][2]) == 0) {
			if(a[0][0].equals("X")) return X;
			else if(a[0][0].equals("O")) return O;
		}

		if(a[1][0].compareTo(a[1][1]) == 0 && a[1][1].compareTo(a[1][2]) == 0) {
			if(a[1][0].equals("X")) return X;
			else if(a[1][0].equals("O")) return O;
		}

		if(a[2][0].compareTo(a[2][1]) == 0 && a[2][1].compareTo(a[2][2]) == 0) {
			if(a[2][0].equals("X")) return X;
			else if(a[2][0].equals("O")) return O;
		}

		if(a[0][0].compareTo(a[1][0]) == 0 && a[1][0].compareTo(a[2][0]) == 0) {
			if(a[0][0].equals("X")) return X;
			else if(a[0][0].equals("O")) return O;
		}

		if(a[0][1].compareTo(a[1][1]) == 0 && a[1][1].compareTo(a[2][1]) == 0) {
			if(a[0][1].equals("X")) return X;
			else if(a[0][1].equals("O")) return O;
		}

		if(a[0][2].compareTo(a[1][2]) == 0 && a[1][2].compareTo(a[2][2]) == 0) {
			if(a[0][2].equals("X")) return X;
			else if(a[0][2].equals("O")) return O;
		}

		if(a[0][0].compareTo(a[1][1]) == 0 && a[1][1].compareTo(a[2][2]) == 0) {
			if(a[0][0].equals("X")) return X;
			else if(a[0][0].equals("O")) return O;
		}

		if(a[0][2].compareTo(a[1][1]) == 0 && a[1][1].compareTo(a[2][0]) == 0) {
			if(a[0][2].equals("X")) return X;
			else if(a[0][2].equals("O")) return O;
		}

		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(a[i][j].equals("N")) return P;

		return D;
	}


	//Print an array and log the state
	public void printArray(String[][] a, Logger logger) {

		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(a[i][j].equals("N"))a[i][j]=" ";

		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++)
				{if(j<2)System.out.print(a[i][j] + " | ");else System.out.println(a[i][j]);}
			if(i<2)System.out.println("---------");;
		}
		System.out.println();

		for(int i = 0; i<3; i++) {
			String temp = " ";
			for (int j = 0; j < 3; j++) {

					if (j < 2)
						temp = temp + a[i][j] + " | ";
					else
						temp = temp + a[i][j];

			}
			logger.info(temp);
			if (i < 2) logger.info("---------");
		}
		logger.info("\n");

	}

	//Identify the next move to be followed by the Menace Machine
	public List<String> nextState(HashMap<String, int[]> patterns,
									   HashMap<String, Integer> picked,
									   String oldState,
									   List<String> states,
									   List<String> modifiedStates) {

		List<String> allStates = new ArrayList<>();
		String oldModifiedState = oldState;
		int rot = -1;
		int mir = 0;
		boolean found = false;

		while(rot<4) {
			rot++;
			if(patterns.containsKey(oldModifiedState)) {found = true; break;}
			oldModifiedState = arrayToString(rotateArray(stringToArray(oldModifiedState)));
		}

		if(!found) {
			rot=0;
			mir++;
			oldModifiedState = arrayToString(mirrorImage(stringToArray(oldState)));
			if(patterns.containsKey(oldModifiedState)) found = true;
		}

		while(!found && rot<4) {
			rot++;
			oldModifiedState = arrayToString(rotateArray(stringToArray(oldModifiedState)));
			if(patterns.containsKey(oldModifiedState)) {found = true; break;}
		}

		int modifiedPickedBead = pickBead(patterns.get(oldModifiedState));
		picked.put(oldModifiedState,modifiedPickedBead);
		StringBuilder sb = new StringBuilder(oldModifiedState);
		sb.setCharAt(modifiedPickedBead, 'X');
		allStates.add(sb.toString());
		modifiedStates.add(sb.toString());

		StringBuilder sb2 = new StringBuilder(oldModifiedState);
		sb2.setCharAt(modifiedPickedBead, 'B');
		String temp = sb2.toString();

		if(mir==1) {
			temp = arrayToString(mirrorImage(stringToArray(temp)));
		}

		int i = 4-rot;
		if(mir==1)i=rot;
		while(i>0) {
			temp = arrayToString(rotateArray(stringToArray(temp)));
			i--;
		}

		for(int j=0; j<temp.length(); j++)
		{
			StringBuilder sb3 = new StringBuilder(temp);
			if(sb3.charAt(j) == 'B') {
				sb3.setCharAt(j, 'X');
				states.add(sb3.toString());
				allStates.add(sb3.toString());
			}
		}
		return allStates;
	}
}
