///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// *
// * @author Afik
// */
//public class AddKomentar {
//   
//    Komentar komentar;
//   
//    
//    /**
//     * Creates a new instance of AddKomentar
//     */
//    public AddKomentar(){
//        komentar = new Komentar();
//    }
//    public void AddComment(int PID) throws SQLException{
//    try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_blog_2", "root", "");
//            Statement sta = conn.createStatement();
//            String Tanggal = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String Sql = "INSERT INTO `komentar`(`PID`, `NAMA`, `EMAIL`, `TANGGAL`, `KOMENTAR`) "
//                    + "VALUES ("+PID+",\""+komentar.getNama()+"\",\""+komentar.getEmail()+"\",\""+komentar.getTanggal()+"\",\""+komentar.getKomentar()+"\")";
//            sta.executeUpdate(Sql);
//            conn.close();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Komentar.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public boolean ValidateEmail(String email){
//       String regex = "/^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/";
//       Pattern pat = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//       Matcher mat = pat.matcher(email);
//       return mat.find();
//    }
//    
//}
