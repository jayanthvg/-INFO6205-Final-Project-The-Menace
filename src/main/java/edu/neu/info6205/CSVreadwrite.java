package edu.neu.info6205;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVreadwrite {

    public static void readCSV(String filePath) throws Exception {

        Logger logger = LoggerFactory.getLogger(CSVreadwrite.class);
        CSVReader reader = new CSVReader(new FileReader(filePath), ',' , '"' , 1);

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                System.out.println(nextLine[0]+","+nextLine[1]+","+nextLine[2]);
                logger.info(nextLine[0]+","+nextLine[1]+","+nextLine[2]);
            }
        }
    }

    public static void writeCSV(String filePath, String content, boolean append) throws IOException {
        String csv = filePath;
        CSVWriter writer = new CSVWriter(new FileWriter(csv,append));

        //Create record
        String [] record = content.split(",");
        //Write the record to file
        writer.writeNext(record);
        //close the writer
        writer.close();
    }


    public static HashMap<String, int[]> readCSVline(String filePath) throws Exception {

        HashMap<String, int[]> patterns = new  HashMap<String, int[]>();
        CSVReader reader = new CSVReader(new FileReader(filePath), ',' , '"' , 1);

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                int[] beads = new int[9];
                for(int i = 0; i < 9; i++)
                    beads[i] = Integer.parseInt(nextLine[i+1].strip());
                patterns.put(nextLine[0],beads);
            }
        }
        return patterns;
    }
}
