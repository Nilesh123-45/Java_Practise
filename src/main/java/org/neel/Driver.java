package org.neel;

public class Driver {
    public static void main(String[] args) {
        DebitCredit dc=new DebitCredit();
        try {
            dc.doOperation();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
