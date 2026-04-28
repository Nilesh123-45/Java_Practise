package org.neel;

import java.sql.*;

public class DebitCredit {

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


    public void doOperation() throws SQLException {
        //1. display the account info

        //2.show the balance of senders
        //3.update the balance of the sender

        //4.show the balance of receiver
        //5.update the balance of receiver

        Connection connection= DriverManager.getConnection(DB_Details.URL,DB_Details.name,DB_Details.password);


        try(
             PreparedStatement ps1=connection.prepareStatement(DB_Details.SELECT_QUERY);
             PreparedStatement ps2=connection.prepareStatement(DB_Details.SELECT_BALANCE);
             PreparedStatement ps3=connection.prepareStatement(DB_Details.UPDATE_BALANCE);
             PreparedStatement ps4=connection.prepareStatement(DB_Details.UPDATE_BALANCE);
                ){
            //1.
            ResultSet rs=ps1.executeQuery();
            System.out.println("      =====User details=====");
            while(rs.next()){
                int id=rs.getInt("id");
               String name= rs.getString("name");
              String uId=  rs.getString("user_Id");
              int bal=  rs.getInt("balance");
                System.out.println("Id: "+id+" "+"Name: "+name+" "+"User id: "+uId+" "+"Balance: "+bal);
                System.out.println();
            }

            //2. show the balance of the sender
            int senderBalance=0;

            int transferAmount=5000;

            connection.setAutoCommit(false);
            System.out.println("======Sender balance=====");
            ps2.setInt(1,123);
            ResultSet rs1=ps2.executeQuery();
            while(rs1.next()){
                senderBalance=rs1.getInt("balance");
                System.out.println("Sender balance is checking...");
            }
            System.out.println("Sender Balance: "+senderBalance);


            //3.
            senderBalance=senderBalance-transferAmount;
            ps3.setInt(1,senderBalance);
            ps3.setInt(2,123);
            ps3.executeUpdate();
            System.out.println("Sender updated Balance: "+senderBalance);

            System.out.println();


            //4.show balance of receiver
            System.out.println("======Receiver balance=====");
            System.out.println();
            int receiverBalance=0;
            ps2.setInt(1,456);
            ResultSet rs2=ps2.executeQuery();
            while(rs2.next()){
                receiverBalance=rs2.getInt("balance");
                System.out.println("Receiver balance is checking...");
            }
            System.out.println("Receiver Balance: "+receiverBalance);


            //5. update the receiver balance
            receiverBalance=receiverBalance+transferAmount;
            ps4.setInt(1,receiverBalance);
            ps4.setInt(2,456);
            ps4.executeUpdate();
            System.out.println("Receiver updated Balance: "+receiverBalance);
            connection.commit();

        }catch (Exception e){
            connection.rollback();
            System.out.println(e.getLocalizedMessage());
        }

    }
}
