package edu.neu.info6205;

import java.io.IOException;
import java.util.Set;

public class BeadsFile {
    public static void demo(int alpha1, int alpha2, int alpha3, int alpha4) throws IOException {
        String s1 = "NNNNNNNNN";
        String s3 = "NNNNNNNXO";
        String s5 = "NNNNNXOXO";
        String s7 = "NNNXOXOXO";

        Operations op = new Operations();
        CSVreadwrite.writeCSV("beads_data.csv",
                "Pattern,Blue,Red,Yellow,Black,White,Orange,Pink,Green,Brown", false);

        Set<String> hashSet1 = op.finalArrays(s1);
//        System.out.println(hashSet1.size());
        hashSet1.forEach((n) -> {

            try {
                CSVreadwrite.writeCSV("beads_data.csv",
                                        n + "," + op.beadPositionNumbers(n,alpha1),true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Set<String> hashSet3 = op.finalArrays(s3);
//        System.out.println(hashSet3.size());
        hashSet3.forEach((n) -> {
            try {
                CSVreadwrite.writeCSV("beads_data.csv",
                                        n + "," + op.beadPositionNumbers(n,alpha2),true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Set<String> hashSet5 = op.finalArrays(s5);
//        System.out.println(hashSet5.size());
        hashSet5.forEach((n) -> {
            try {
                CSVreadwrite.writeCSV("beads_data.csv",
                        n + "," + op.beadPositionNumbers(n,alpha3),true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Set<String> hashSet7 = op.finalArrays(s7);
//        System.out.println(hashSet7.size());
        hashSet7.forEach((n) -> {
            try {
                CSVreadwrite.writeCSV("beads_data.csv",
                        n + "," + op.beadPositionNumbers(n,alpha4),true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
