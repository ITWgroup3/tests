/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.Unit;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author yukun
 */
public class QuestionTest {

    String[] options;
    String[] correctAnswers;
    String content;
    Question instance;
    double totalScore;
    int type;

    public QuestionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        options = new String[3];
        correctAnswers = new String[2];
        options[0] = "Stomach";
        options[1] = "Liver";
        options[2] = "Gall bladder";
        correctAnswers[0] = "1";
        correctAnswers[1] = "2";
        content = "Where is bile made?";
        totalScore = 1.0;
        type = Configuration.MULTI_ANSWERS;
        instance = new Question(type, content, options, totalScore, correctAnswers);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getContent method, of class Question.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        String expResult = content;
        String result = instance.getContent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOptions method, of class Question.
     */
    @Test
    public void testGetOptions() {
        System.out.println("getOptions");
        String[] expResult = options;
        String[] result = instance.getOptions();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Question.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        int expResult = type;
        int result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalScore method, of class Question.
     */
    @Test
    public void testGetTotalScore() {
        System.out.println("getTotalScore");
        double expResult = totalScore;
        double result = instance.getTotalScore();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCorrectAnswer method, of class Question.
     */
    @Test
    public void testGetCorrectAnswer() {
        System.out.println("getCorrectAnswer");
        String[] expResult = correctAnswers;
        String[] result = instance.getCorrectAnswer();
        assertArrayEquals(expResult, result);
    }

    /**
     * if the question type is SINGLE_ANSWER, but has more than one correct answers.
     * the question type should be automatically changed to MULTI_ANSWERS.
     */
    @Test
    public void testErrorHandling1() {
        System.out.println("SINGLE_ANSWER -> MULTI_ANSWERS");
        instance = new Question(Configuration.SINGLE_ANSWER, content, options, totalScore, correctAnswers);
        int expResult = Configuration.MULTI_ANSWERS;
        int result = instance.getType();
        assertEquals(expResult, result);
    }
    
    /**
     * if the question's correct answer is not int, but the type is SINGLE_ANSWER,
     * the question type should be automatically changed to STRING_ANSWER.
     */
    @Test
    public void testErrorHandling2() {
        System.out.println("SINGLE_ANSWER -> STRING_ANSWER");
        correctAnswers = new String[1];
        correctAnswers[0] = "liver";
        instance = new Question(Configuration.SINGLE_ANSWER, content, options, totalScore, correctAnswers);
        int expResult = Configuration.STRING_ANSWER;
        int result = instance.getType();
        assertEquals(expResult, result);
    }
    
    /**
     * if the question's correct answer is not int, but the type is MULTI_ANSWERS,
     * the question type should be automatically changed to STRING_ANSWER.
     */
    @Test
    public void testErrorHandling3() {
        System.out.println("MULTI_ANSWERS -> STRING_ANSWER");
        correctAnswers = new String[1];
        correctAnswers[0] = "";
        instance = new Question(Configuration.MULTI_ANSWERS, content, options, totalScore, correctAnswers);
        int expResult = Configuration.STRING_ANSWER;
        int result = instance.getType();
        assertEquals(expResult, result);
    }
    
    /**
     * if the correctAnswers is null, the question type should be automatically changed to STRING_ANSWER
     */
    @Test
    public void testErrorHandling4(){
        System.out.println("correctAnswers is null");
        correctAnswers = null;
        instance = new Question(Configuration.MULTI_ANSWERS, content, options, totalScore, correctAnswers);
        int expResult = Configuration.STRING_ANSWER;
        int result = instance.getType();
        assertEquals(expResult, result);
    }
    
     /**
     * if the options is null,
     * the question type should be automatically changed to STRING_ANSWER, 
     */
    @Test
    public void testErrorHandling5(){
        System.out.println("options is null");
        options = null;
        instance = new Question(Configuration.MULTI_ANSWERS, content, options, totalScore, correctAnswers);
        int expResult = Configuration.STRING_ANSWER;
        int result = instance.getType();
        assertEquals(expResult, result);
    }
    
     /**
     * if question type is not STRING_ANSWER, and the integer in correct answer is minus or bigger than the number of options
     * the question type should be automatically changed to STRING_ANSWER, 
     */
    @Test
    public void testErrorHandling6(){
        System.out.println("the correct answer is out of boundary");
        correctAnswers[0] = "-1";
        correctAnswers[1] = "10";
        instance = new Question(Configuration.MULTI_ANSWERS, content, options, totalScore, correctAnswers);
        int expResult = Configuration.STRING_ANSWER;
        int result = instance.getType();
        assertEquals(expResult, result);
    }    
}
