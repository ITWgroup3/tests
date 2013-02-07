/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.Unit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mengdiwu
 */
public class QuestionDataTest {
    int questionID;
    int clicks;
    long time;
    QuestionData instance;
    
    public QuestionDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         questionID=1;
         clicks=5;
         time=60;
         instance = new QuestionData(questionID);
         instance.setClicks(clicks);
         instance.setTime(time);
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getQuestionID method, of class QuestionData.
     */
    @Test
    public void testGetQuestionID() {
        System.out.println("getQuestionID");
        int expResult = questionID;
        int result = instance.getQuestionID();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getClicks method, of class QuestionData.
     */
    @Test
    public void testGetClicks() {
        System.out.println("getClicks");
        int expResult = clicks;
        int result = instance.getClicks();
        assertEquals(expResult, result);
       
    }


    /**
     * Test of getTime method, of class QuestionData.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        long expResult = time;
        long result = instance.getTime();
        assertEquals(expResult, result);
        
    }


    /**
     * Test of toString method, of class QuestionData.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "QuestionData{" + "questionID=" + questionID + 
                ", clicks=" + clicks + ", time=" + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
}
