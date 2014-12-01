/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Afik
 */
public class AllKomentar {

    private int PID;
    private ArrayList<Komentar> allKomentar;

    /**
     * Creates a new instance of AllKomentar
     */
    public AllKomentar() {
    }
    
    public int getPID(){
        return PID;
    }
    
    public ArrayList <Komentar> getAllKomentar() {
        allKomentar = new ArrayList<Komentar> (DAO.DAOFactory.getInstance("javabase.jdbc").getKomentarDAO().list(PID));
        System.out.println("flag : " + PID);
        return allKomentar;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }
    
    public void setAllKomentar(ArrayList <Komentar> allKomentar) {
        this.allKomentar = allKomentar;
    }
}
