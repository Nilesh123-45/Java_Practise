package org.neel;

public class DB_Details {

    public static final String URL="jdbc:mysql://localhost:3306/intellij_practise";
    public static final String name="root";
    public static final String password="12345";


    public static final String SELECT_QUERY="select * from account_Info";

    public static final String SELECT_BALANCE="select balance from account_Info where user_Id=?";
    public static final String UPDATE_BALANCE="update account_Info set balance=? where user_Id=?";
}
