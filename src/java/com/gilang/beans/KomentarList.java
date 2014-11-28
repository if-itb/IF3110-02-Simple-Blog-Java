/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilang.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniar heri
 */
public class KomentarList {
    private List<Komentar> komentarList;
	
	public KomentarList(){
		komentarList = new ArrayList<>();
	}
	
	public void add(Komentar komentar){
		komentarList.add(komentar);
	}
	
	public List<Komentar> getKomentarList(){
		return komentarList;
	}
}
