package hu.tamasdemeny.customers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Customer {

    public static void main(String[] args) {
        List fullData = new ArrayList();
        fullData = reader();
        //System.out.println(fullData);

        //Set s = new TreeSet();
        //System.out.println("Név szerint ABC: ");
        Collections.sort(fullData, new OsszehasonlitasNevSzerint());
        //System.out.println(fullData);

        //System.out.println("Összeg szerint: ");
        Collections.sort(fullData, new OsszehasonlitasOsszegSzerint());
        //System.out.println(fullData);
        writer(fullData);
    }

    static List reader() {
        BufferedReader br = null;
        List fullData = new ArrayList();

        try {
            br = new BufferedReader(new FileReader(new File("src/hu/tamasdemeny/customers/ugyfelek.csv")));
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] newData = line.split(",");
                Integer balance = Integer.parseInt(newData[6]);
                Integer saved = Integer.parseInt(newData[7]);
                System.out.println("balance + saved: " + balance +  saved);
                fullData.add(new OneCustomer(newData[0], newData[1],newData[2],newData[3],newData[4],newData[5],balance, saved));
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

    static void writer(List fullData) {


        File f = new File("penzosszegek.csv");

        FileWriter fw = null;
        try {
            fw = new FileWriter(f);

            for (int i=1;i<fullData.size();i++) {
                String currentLine = fullData.get(i).toString();
                System.out.println("Line " + i + " " + currentLine);
                fw.append(currentLine);
                fw.append("\n");

                if (i % 10 == 0) {
                    fw.flush();
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





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
    private Integer accountBalance;
    private Integer accountSaved;


    @Override
    public String toString() {
        return name + " (" + motherName + ") -- " + accountDate ;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public OneCustomer(String name, String motherName,String dob, String placeBirth, String accountDate,String accountNum,Integer accountBalance, Integer accountSaved  ) {
        this.name = name;
        this.motherName = motherName;
        this.dob = dob;
        this.placeBirth = placeBirth;
        this.accountDate = accountDate;
        this.accountNum = accountNum;
        this.accountBalance = accountBalance;
        this.accountSaved = accountSaved;
    }

    public String getName() {
        return name;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public Integer getAccountSaved() {
        return accountSaved;
    }
}



class OsszehasonlitasNevSzerint implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        OneCustomer p1 = (OneCustomer) o1;
        OneCustomer p2 = (OneCustomer) o2;


        if (p1.getName() == p2.getName()) {
            return 0;
        } else {
            if (p1.getName().compareTo(p2.getName())> 0){
                return 1;
            } else {
                return -1;
            }
        }

    }


}

class OsszehasonlitasOsszegSzerint implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        OneCustomer p1 = (OneCustomer) o1;
        OneCustomer p2 = (OneCustomer) o2;
        Integer total1 =p1.getAccountBalance() + p1.getAccountSaved();
        Integer total2 =p2.getAccountBalance() + p2.getAccountSaved();

        if (total1 == total2) {
            return 0;
        } else {
            if (total1>total2){
                return 1;
            } else {
                return -1;
            }
        }

    }


}


