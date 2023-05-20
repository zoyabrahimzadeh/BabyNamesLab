package com.example.babynameclass;

import java.util.ArrayList;

/**
 * Object that represents a name for a baby. Includes the sex of the name
 * and birth data for the number of babies born with that name in a
 * particular year.
 * @author Zoya Brahimzadeh
 */
public class BabyName {


    private String name;
    private GenderOfName gender;
    private ArrayList<Integer> birthCounts;
    private ArrayList<Integer> years;
    // TODO 3: Write the code below this line.
    public BabyName(String name, GenderOfName gender) {
        this.name = name;
        this.gender = gender;
        birthCounts = new ArrayList<Integer>();
        years = new ArrayList<Integer>();
    }
    // TODO 4: Write the code below this line.
    public static void main(String[] args) {
        BabyName bab = new BabyName("Lola", GenderOfName.FEMALE );
        ArrayList<Integer> birthCountsMain = new ArrayList<Integer>();
        birthCountsMain.add(1); birthCountsMain.add(5);
        ArrayList<Integer> yearsMain = new ArrayList<Integer>();
        yearsMain.add(2022); yearsMain.add(2021);
        bab.addData(birthCountsMain, yearsMain);
        System.out.println(bab.getYears().toString());
    }
    public String getName() {
        return this.name;
    }

    public GenderOfName getGender() {
        return this.gender;
    }

    public ArrayList<Integer> getBirthCounts(){
        return this.birthCounts;
    }

    public ArrayList<Integer> getYears() {
        return this.years;
    }

    public void setGender(GenderOfName gend) {
        this.gender = gend;
    }

    public void addData(int numBirths, int year) {
        int index = 0;
        int temp = 0;
        while (index < this.years.size()) {
            System.out.println(temp);
            temp = years.get(index);

            if (year == temp) {
                this.birthCounts.set(index, birthCounts.get(index) + numBirths);
                return;
            }
            if (year < temp ) {
                break;
            }
            index ++;
        }
        birthCounts.add(index, numBirths);
        this.years.add(index, year);
    }

    public void addData(ArrayList<Integer> birthsWithName, ArrayList<Integer> yearsBirths) {
        for (int i = 0; i < birthsWithName.size(); i++) {
            System.out.println("addData() call: " + yearsBirths.get(i) );
            this.addData(birthsWithName.get(i), yearsBirths.get(i));
        }
    }


    /**
     * Formats the object as a String.
     * @return formatted String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Name: " + name + "\nSex of Name: " + gender.toString().toLowerCase());
        for (int i = 0; i < years.size(); i++){
            if (i == 0){
                result.append("\nData: ");
            }

                result.append(String.format("(%d, %d) ", birthCounts.get(i), years.get(i)));

            if (i == years.size()-1){
                result.deleteCharAt(result.length()-1); // Remove extra space
            }
        }
        return result.toString();
    }
}
