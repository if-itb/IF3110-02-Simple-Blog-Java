/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author yafithekid
 */
public class PostTest {
    
    public PostTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class Post.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        int id = 1;
        Post instance = new Post();
        boolean result = instance.load(id);
        assertEquals("Hello World",instance.getJudul());
    }

    /**
     * Test of save method, of class Post.
     */
    //@Test
    public void testSave() {
        System.out.println("save");
        Post instance = new Post();
        boolean expResult = false;
        boolean result = instance.save();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Post.
     */
    //@Test
    public void testGetId() {
        System.out.println("getId");
        Post instance = new Post();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Post.
     */
    //@Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Post instance = new Post();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJudul method, of class Post.
     */
    //@Test
    public void testGetJudul() {
        System.out.println("getJudul");
        Post instance = new Post();
        String expResult = "";
        String result = instance.getJudul();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setJudul method, of class Post.
     */
    //@Test
    public void testSetJudul() {
        System.out.println("setJudul");
        String judul = "";
        Post instance = new Post();
        instance.setJudul(judul);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTanggal method, of class Post.
     */
    //@Test
    public void testGetTanggal() {
        System.out.println("getTanggal");
        Post instance = new Post();
        Calendar expResult = null;
        String result = instance.getTanggal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTanggal method, of class Post.
     */
    //@Test
    public void testSetTanggal() {
        System.out.println("setTanggal");
        String tanggal = null;
        Post instance = new Post();
        instance.setTanggal(tanggal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKonten method, of class Post.
     */
    //@Test
    public void testGetKonten() {
        System.out.println("getKonten");
        Post instance = new Post();
        String expResult = "";
        String result = instance.getKonten();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKonten method, of class Post.
     */
    //@Test
    public void testSetKonten() {
        System.out.println("setKonten");
        String konten = "";
        Post instance = new Post();
        instance.setKonten(konten);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPublished method, of class Post.
     */
    //@Test
    public void testIsPublished() {
        System.out.println("isPublished");
        Post instance = new Post();
        boolean expResult = false;
        boolean result = instance.isPublished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPublished method, of class Post.
     */
    //@Test
    public void testSetPublished() {
        System.out.println("setPublished");
        boolean published = false;
        Post instance = new Post();
        instance.setPublished(published);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class Post.
     */
    //@Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Post instance = new Post();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserId method, of class Post.
     */
    //@Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 0;
        Post instance = new Post();
        instance.setUserId(userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComments method, of class Post.
     */
    //@Test
    public void testGetComments() {
        System.out.println("getComments");
        Post instance = new Post();
        List<Comment> expResult = null;
        List<Comment> result = instance.getComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComments method, of class Post.
     */
    //@Test
    public void testSetComments() {
        System.out.println("setComments");
        ArrayList<Comment> comments = null;
        Post instance = new Post();
        instance.setComments(comments);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
