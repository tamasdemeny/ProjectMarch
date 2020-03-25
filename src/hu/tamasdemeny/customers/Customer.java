package hu.tamasdemeny.customers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Customer {

    public static void main(String[] args) {
        List fullData = new ArrayList();
        fullData = reader();
        System.out.println(fullData);
    }

    static List reader() {
        BufferedReader br = null;
        List fullData = new ArrayList();

        try {
            br = new BufferedReader(new FileReader(new File("src/hu/tamasdemeny/customers/ugyfelek.csv")));
            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] newData = line.split(",");
                fullData.add(new OneCustomer(newData[0], newData[1],newData[2],newData[3],newData[4],newData[5],newData[6],newData[7]));
            }

        }   catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    return fullData;
    }



}


class OneCustomer implements Comparable {
    // nem lesz mint string, ez csak a próba
    private String name;
    private String motherName;
    private String dob;
    private String placeBirth;
    private String accountDate;
    private String accountNum;
    private String accountBalance;
    private String accountSaved;


    @Override
    public String toString() {
        return name + " (" + motherName + ") -- " + accountDate ;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public OneCustomer(String name, String motherName,String dob, String placeBirth, String accountDate,String accountNum,String accountBalance, String accountSaved  ) {
        this.name = name;
        this.motherName = motherName;
        this.dob = dob;
        this.placeBirth = placeBirth;
        this.accountDate = accountDate;
        this.accountNum = accountNum;
        this.accountBalance = accountBalance;
        this.accountSaved = accountSaved;
    }
}


