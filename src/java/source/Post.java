/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Try Ajitiono
 */
public class Post {
    
    /**
     * Mengecek apakah pengguna adalah admin
     * @return
     */
    public boolean isAdmin() {
        return true;
    }
    
    /**
     * Mengecek apakah pengguna adalah editor
     * @return
     */
    public boolean isEditor() {
        return true;
    }
    
    /**
     * Mengecek apakah pengguna adalah owner
     * @return
     */
    public boolean isOwner() {
        return true;
    }
    
    /**
     * Menampilkan semua post yang statusnya published
     */
    public void listPosts() {
        
    }
    
    /**
     * Menambahkan suatu post ke dalam database
     */
    public void addPost() {
        
    }
    
    /**
     * Mengubah status post di database dari unpublished menjadi published
     * @param post_ID post id di database
     */
    public void publishPost(int post_ID) {
        
    }
    
    /**
     * Mengubah aspek dari post di database
     * @param post_ID post id di database
     */
    public void editPost(int post_ID) {
        
    }
    
    /**
     * Menghapus post dari database
     * @param post_ID post id di database
     */
    public void deletePost(int post_ID) {
        
    }
}
