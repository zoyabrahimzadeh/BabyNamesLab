package com.example.babynameclass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the list of BabyNames as well as reading and writing to the files
 * with the BabyNames.
 * @author Zoya Brahimzadeh
 */
public class BabyNameDatabase {

    private String databaseFileName;
    private File fileRecord;
    private ArrayList<BabyName> records;

    public BabyNameDatabase(String dbFileName) {
        databaseFileName = dbFileName;
        fileRecord = new File(databaseFileName);
        records = new ArrayList<>();
    }


    public ArrayList<BabyName> getRecords() {
        return records;
    }


    /**
     * Reads the csv file that holds the baby name birth data and updates
     * the records variable.
     *
     * @param filename name of the file to read from
     * @throws 'IOException' could not find or close file
     */

    public void readRecordsFromBirthDataFile(String filename) throws IOException {
        // TODO 2: Write the code below this line.
        Scanner scannie = new Scanner(new File(filename));
        Scanner filenameParser = new Scanner(filename);
        int year = Integer.parseInt(filename.substring(9, 13));
        String nxLine;
        while (scannie.hasNextLine()) {
            nxLine = scannie.nextLine();
            if (Character.isDigit(nxLine.charAt(0))) {
                processLineFromBirthDataFile(nxLine, year);
            }

        }
    }

    /**
     * Processes one formatted line of the csv file into baby names and
     * adds/updates the records array.
     *
     * @param line the string holding the line from the csv file
     * @param year when the data is from
     */
//    public void processLineFromBirthDataFile(String line, int year) {
//        BabyName female;
//        BabyName male;
//        Scanner scannie = new Scanner(line);
//        scannie.useDelimiter(",");
//        String rankingParsed = "";
//        String nameParsed = "";
//        String intParsed = "";
//        String[] parsedValues = new String[5];
//
//        String token = "";
//        for (int i = 0; i  <5; i++) {
//            token = processValue(line);
//            System.out.println("token " + token);
//            parsedValues[i] = token;
//            line = modifyString(line, token);
//            System.out.println(line);
//        }
//
//        for (String tok : parsedValues) {
//            System.out.println(tok);
//        }
//
//
//    }


    public void processLineFromBirthDataFile(String line, int year) {
        Scanner scannie = new Scanner(line);
        scannie.useDelimiter(",");
        String[] parsedValues = new String[5];

        String token = "";
        for (int i = 0; i  <5; i++) {
            token = processValue(scannie);
            parsedValues[i] = token;
        }

//        for (String tok : parsedValues) {
//            System.out.print(tok +",");
//        }
//        System.out.println();

        BabyName parsedMale = new BabyName(parsedValues[1], GenderOfName.MALE);
        parsedMale.addData(Integer.parseInt(parsedValues[2]), year);

        BabyName parsedFemale = new BabyName(parsedValues[3], GenderOfName.FEMALE);
        parsedFemale.addData(Integer.parseInt(parsedValues[4]), year);

        for (int i =0; i< records.size(); i++) {
            BabyName ithRecord = records.get(i);
            if (ithRecord.getName().equals(parsedMale.getName()) ) {

                if (ithRecord.getGender() != GenderOfName.MALE) {
                    parsedMale.setGender(GenderOfName.NEUTRAL);
                }
                parsedMale.addData(ithRecord.getBirthCounts(), ithRecord.getYears());
                records.remove(i);
                i -=1;
            }
            else if (ithRecord.getName().equals(parsedFemale.getName()) ) {
                if (ithRecord.getGender() != GenderOfName.FEMALE) {
                    parsedFemale.setGender(GenderOfName.NEUTRAL);
                }
                parsedFemale.addData(ithRecord.getBirthCounts(), ithRecord.getYears());
                records.remove(i);
                i -=1;
            }
        }
        records.add(parsedMale);
        records.add(parsedFemale);

    }
//    public String modifyString(String inputLine, String parsedToken) {
//        return inputLine.substring(parsedToken.length() + 1);
//    }

//    public String processValue(String line) {
//        Scanner scannie = new Scanner(line);
//        scannie.useDelimiter("");
//        String valBeingParsed = "";
//        int count = 0;
//        while (scannie.hasNext()) {
//            String nextChar = scannie.next();
//            if (nextChar.equals("\"")) {
//                count++;
//                continue;
//            }
//            // checks
//            else if ( Character.isLetterOrDigit(nextChar.charAt(0)) ) {
//                valBeingParsed += nextChar;
//                // don't need to mark whether it's a digit or a letter?
//            }
//
//            // count will equal 2 or 0
//            if (nextChar.equals(",")) {
//                // quotations are closed
//                if (count % 2 == 0 ) {
//                    return valBeingParsed;
//                }
//                else {
//                    continue;
//                }
//            }
//        }
//        return valBeingParsed;
//    }

    public String processValue(Scanner scannie) {

        scannie.useDelimiter("");
        String valBeingParsed = "";
        int count = 0;
        while (scannie.hasNext()) {
            String nextChar = scannie.next();
            if (nextChar.equals("\"")) {
                count++;
                continue;
            }
            // checks
            else if ( Character.isLetterOrDigit(nextChar.charAt(0)) ) {
                valBeingParsed += nextChar;
                // don't need to mark whether it's a digit or a letter?
            }

            // count will equal 2 or 0
            if (nextChar.equals(",")) {
                // quotations are closed
                if (count % 2 == 0 ) {
                    return valBeingParsed;
                }
                else {
                    continue;
                }
            }
        }
        return valBeingParsed;
    }




    public static void main(String[] args) throws IOException {
        BabyNameDatabase bab = new BabyNameDatabase("BabyNames2005.csv");
        bab.readRecordsFromBirthDataFile("BabyNames2006.csv");
//        bab.processLineFromBirthDataFile("1,Jacob,\"25,838\",Emily,\"23,948\"", 2005);
        for (BabyName rec : bab.getRecords()) {
            System.out.println(rec.toString());
        }

    }
}