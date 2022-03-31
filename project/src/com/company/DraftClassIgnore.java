package com.company;

import com.company.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DraftClassIgnore {


    public void methd(){
        ArrayList<Transaction> x = new ArrayList<>();
//        List<Transaction> y = new List<Transaction>();
        Transaction t = new Transaction(1, "yes", 2);
        x.add(t);
        x.add(t);
        x.add(t);
        Collections.sort(x, new Comparator<Transaction>(){
            @Override
            public int compare(Transaction o1, Transaction o2){

//                HashMap<String, Integer> months = new HashMap<>();
//                months.put("JAN", 1);
//                months.put("FEB", 2);
//                months.put("MAR", 3);
//                months.put("APR", 4);
//                months.put("MAY", 5);
//                months.put("JUN", 6);
//                months.put("JUL", 7);
//                months.put("AUG", 8);
//                months.put("SEP", 9);
//                months.put("OCT", 10);
//                months.put("NOV", 11);
//                months.put("DEC", 12);

                String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
                int result = 0;

                String[] firstDate = o1.getTransactionDate().split(" ");
                String[] secondDate = o2.getTransactionDate().split(" ");
//                System.out.println(firstDate);

                return result;
            }
        });
    }
//        CurrentAccount y = new CurrentAccount();


//        System.out.println(x);

    // Main

    /*
    crntacc.methd();

        HashMap<String, Integer> months = new HashMap<>();
        months.put("JAN", 1);
        months.put("FEB", 2);
        months.put("MAR", 3);
        months.put("APR", 4);
        months.put("MAY", 5);
        months.put("JUN", 6);
        months.put("JUL", 7);
        months.put("AUG", 8);
        months.put("SEP", 9);
        months.put("OCT", 10);
        months.put("NOV", 11);
        months.put("DEC", 12);

        String ex = "25 MAR 2022";
//        String firstDate = Arrays.toString(ex.split(" "));
//        String[] firstDate = (String []) ex.split(" ");
//        String[] firstDate = new String[];
//        assertArrayEquals(firstdate, ex.split(" "));


        ArrayList<String> firstDate = new ArrayList(ex.split(" "));

        firstDate[0] = (int) firstDate[0];


        System.out.println(firstDate[0] + 10);

     */

}
