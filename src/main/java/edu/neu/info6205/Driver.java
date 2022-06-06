package edu.neu.info6205;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws Exception {

		int alpha1 = 8;
		int alpha2 = 4;
		int alpha3 = 2;
		int alpha4 = 1;
		int beta = 2;
		int gamma = 1;
		int delta = 1;
		double p = 0.9;
		int paraChangeFlag = 0;

		Logger logger = LoggerFactory.getLogger(Driver.class);
		Scanner myObj = new Scanner(System.in);

		System.out.println("**********DEFAULT PARAMETER VALUES**********\n");
		System.out.println("*****Each alpha value below is count of each color bead in the match box\n");
		System.out.println("alpha: 8,4,2,1 (For 1st, 2nd, 3rd & 4th steps of Menace machine respectively)");
		System.out.println("beta : 2 (the number of ”beads” to add to the “matchbox” in the event of a win)");
		System.out.println("gamma: 1 (the number of ”beads” to take to the “matchbox” in the event of a loss)");
		System.out.println("delta: 1 (the number of ”beads” to add to the “matchbox” in the event of a draw)\n");

		System.out.print("Change default parameter values? (Y/N): ");
		String userInput = myObj.nextLine().toUpperCase();
		while (!userInput.equals("Y") && !userInput.equals("N")) {
			System.out.print("Enter valid input (Y/N): ");
			userInput = myObj.nextLine().toUpperCase();
		}


		if (userInput.equals("Y")) {
			paraChangeFlag = 1;
			System.out.println("Enter alpha value using four comma separated integers: ");
			userInput = myObj.nextLine().toUpperCase();

			try {
				String[] alpha = userInput.split(",");
				alpha1 = Integer.parseInt(alpha[0]);
				alpha2 = Integer.parseInt(alpha[1]);
				alpha3 = Integer.parseInt(alpha[2]);
				alpha4 = Integer.parseInt(alpha[3]);
				BeadsFile.demo(alpha1,alpha2,alpha3,alpha4);

			} catch (Exception e) {
				System.out.println("Enter valid input!");
				e.printStackTrace();
			}

			System.out.println("Enter beta value: ");
			userInput = myObj.nextLine().toUpperCase();

			try {
				beta = Integer.parseInt(userInput);

			} catch (Exception e) {
				System.out.println("Enter valid input!");
				e.printStackTrace();
			}

			System.out.println("Enter gamma value: ");
			userInput = myObj.nextLine().toUpperCase();

			try {
				gamma = Integer.parseInt(userInput);

			} catch (Exception e) {
				System.out.println("Enter valid input!");
				e.printStackTrace();
			}

			System.out.println("Enter delta value: ");
			userInput = myObj.nextLine().toUpperCase();

			try {
				delta = Integer.parseInt(userInput);

			} catch (Exception e) {
				System.out.println("Enter valid input!");
				e.printStackTrace();
			}

			System.out.println("Updating the parameters.......");
			try
			{
				// Delay for 2 seconds
				Thread.sleep(2000);
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}

			System.out.println("Successfully updated the parameters!");

			try
			{
				// Delay for 2 seconds
				Thread.sleep(2000);
			}
			catch(InterruptedException ex)
			{
				ex.printStackTrace();
			}

			CSVreadwrite.writeCSV("stats.csv", "Result", false);
		}

		System.out.println("\nCurrent value of p="+p);
		System.out.print("Change p value? (Y/N): ");
		userInput = myObj.nextLine().toUpperCase();
		while (!userInput.equals("Y") && !userInput.equals("N")) {
			System.out.print("Enter valid input (Y/N): ");
			userInput = myObj.nextLine().toUpperCase();
		}


		if (userInput.equals("Y")) {
			try {
				System.out.print("Enter p value: ");
				String userInput1 = myObj.nextLine().toUpperCase();
				p = Double.parseDouble(userInput1);
				System.out.println("Please re-train the menace machine now!!");

			} catch (Exception e) {
				System.out.println("Enter valid input!");
				e.printStackTrace();
			}
		}

		else if (paraChangeFlag == 1)
			System.out.println("\nPlease re-train the menace machine now!!");

		System.out.print("\nTrain Menace Machine/Watch Final Game? (T/W): ");
		userInput = myObj.nextLine().toUpperCase();
		while (!userInput.equals("T") && !userInput.equals("W")) {
			System.out.println("Enter valid input (T/W): " + userInput);
			userInput = myObj.nextLine().toUpperCase();
		}


		if (userInput.equals("T")) {
			System.out.print("Train Menace machine for how many times?: ");
			userInput = myObj.nextLine().toUpperCase();

			try {
				int matches = Integer.parseInt(userInput);
				System.out.println("Training the Menace Machine.... Please Wait!");
				for(int i = 0; i<matches; i++) {
					Game g = new Game();
					String result = g.train(p, beta, gamma, delta, false, logger);
					String loggingData = "p="+p;
					if(paraChangeFlag==1) loggingData = "alpha=["+alpha1+","+alpha2+","+alpha3+","+alpha4+"],beta="+beta+",gamma="+gamma+","+loggingData;
					loggingData = loggingData+","+result;
					logger.info(loggingData);

					try {
						CSVreadwrite.writeCSV("stats.csv", result, true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				try
				{
					// Delay for 2 seconds
					Thread.sleep(2000);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}

				System.out.println("Training completed!");

			} catch (Exception e) {
				System.out.println("Enter valid integer!");
				e.printStackTrace();
			}
		}

		else {
			System.out.print("Watch how many games?: ");
			userInput = myObj.nextLine().toUpperCase();

			try {
				int matches = Integer.parseInt(userInput);
				for (int i = 0; i < matches; i++) {
					String loggingData = "p="+p;
					if(paraChangeFlag==1) loggingData = "alpha=["+alpha1+","+alpha2+","+alpha3+","+alpha4+"],beta="+beta+",gamma="+gamma+","+loggingData;
					logger.info(loggingData);
					Game g = new Game();
					g.watch(p, beta, gamma, delta, true, logger);
				}

				System.out.println("\nMatch Simulations completed!");

			} catch (Exception e) {
				System.out.println("Enter valid integer!");
				e.printStackTrace();
			}
		}
	}
}