package hu.tamasdemeny.customers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Customer {
    public String firstLine;

    public static void main(String[] args) {
        List fullData = new ArrayList();
        String firstLine = readerFirst();
        fullData = reader();
        //System.out.println(fullData);


        //System.out.println("Név szerint ABC: ");
        Collections.sort(fullData, new OsszehasonlitasNevSzerint());
        //Collections.sort(fullData, new OsszehasonlitasAnyaSzerint());

        writer(fullData,"nevek.csv", firstLine);
        //System.out.println(fullData);

       // System.out.println("Dátum szerint ABC: ");
        Collections.sort(fullData, new OsszehasonlitasDatumSzerint());
        //System.out.println(fullData);
        writer(fullData,"szamlanyitas.csv", firstLine);

        //System.out.println("Összeg szerint: ");
        Collections.sort(fullData, new OsszehasonlitasOsszegSzerint());
        //System.out.println(fullData);
        writer(fullData,"penzosszegek.csv", firstLine);
    }

    static String readerFirst() {
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(new File("ugyfelek.csv")));

            line = br.readLine();


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

        return line;
    }

    static List reader() {
        BufferedReader br = null;
        List fullData = new ArrayList();

        try {
            br = new BufferedReader(new FileReader(new File("ugyfelek.csv")));
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] newData = line.split(",");
               // Integer balance = Integer.parseInt(newData[6]);
                //Integer saved = Integer.parseInt(newData[7]);
                //System.out.println("balance + saved: " + balance +  saved);
                fullData.add(new OneCustomer(newData[0], newData[1],newData[2],newData[3],newData[4],newData[5],newData[6], newData[7]));
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

    static void writer(List fullData, String fileName, String firstLine) {

        File f = new File(fileName);
        //File f = new File("penzosszegek.csv");

        FileWriter fw = null;
        try {
            fw = new FileWriter(f);

            fw.append(firstLine);
            fw.append("\n");
            for (int i=0;i<fullData.size();i++) {
                String currentLine = fullData.get(i).toString();
                //System.out.println("Line " + i + " " + currentLine);
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

    public String getFirstLine() {
        return firstLine;
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
        return name + "," + motherName + "," + dob + "," + placeBirth + "," +accountDate + "," + accountNum +"," + accountBalance + "," + accountSaved ;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public String getMotherName() {
        return motherName;
    }

    public OneCustomer(String name, String motherName, String dob, String placeBirth, String accountDate, String accountNum, String accountBalance, String accountSaved  ) {
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

    public String getAccountBalance() {
        return accountBalance;
    }

    public String getAccountSaved() {
        return accountSaved;
    }
}



class OsszehasonlitasNevSzerint implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        OneCustomer p1 = (OneCustomer) o1;
        OneCustomer p2 = (OneCustomer) o2;


        /*EZ AZ EREDETI VERZIÓ

        if (p1.getName() == p2.getName()) {
            return 0;
        } else {
            if (p1.getName().compareTo(p2.getName())> 0){
                return 1;
            } else {
                return -1;
            }
        }*/

        if (p1.getName().compareTo(p2.getName()) > 0) {
            return 1;
        } else if (p1.getName().compareTo(p2.getName()) < 0) {
            return -1;
        } else if (p1.getName() == p2.getName() && p1.getMotherName() == p2.getMotherName()) {
            return 0;
        } else if (p1.getName() == p2.getName() && p1.getMotherName().compareTo(p2.getMotherName()) > 0) {
            return 1;
        } else if (p1.getName() == p2.getName() && p1.getMotherName().compareTo(p2.getMotherName()) < 0) {
            return -1;
        } else {
            return -1;
        }


    }
}

class OsszehasonlitasAnyaSzerint implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        OneCustomer p1 = (OneCustomer) o1;
        OneCustomer p2 = (OneCustomer) o2;


        if (p1.getMotherName() == p2.getMotherName()) {
            return 0;
        } else {
            if (p1.getMotherName().compareTo(p2.getMotherName())> 0){
                return 1;
            } else {
                return -1;
            }
        }

    }
}


class OsszehasonlitasDatumSzerint implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        OneCustomer p1 = (OneCustomer) o1;
        OneCustomer p2 = (OneCustomer) o2;


        if (p1.getAccountDate() == p2.getAccountDate()) {
            return 0;
        } else {
            if (p1.getAccountDate().compareTo(p2.getAccountDate())> 0){
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


        Integer total1 =Integer.parseInt(p1.getAccountBalance()) + Integer.parseInt(p1.getAccountSaved());
        Integer total2 =Integer.parseInt(p2.getAccountBalance()) + Integer.parseInt(p2.getAccountSaved());

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


