/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.UI.Template;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Schoolware.UI.Template.TimerPanel;

/**
 *
 * @author mengdiwu
 */
public class TimerPanelTest {

    Timer timer;
    long initTime;
    long start;
    int interval;
    TimerPanel instance;

    public TimerPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        start = 0;
        interval = 100;
        JFrame frame = new JFrame();
        frame.setVisible(true);
        instance = new TimerPanel(start, interval);
        initTime = System.currentTimeMillis();
        frame.add(instance);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTimePast method, of class TimerPanel.
     */
    @Test
    public void testGetTimePast1() {
        System.out.println("getTimePast1");
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        long expResult = System.currentTimeMillis() - initTime;
        long result = instance.getTimePast();
        assertEquals(expResult, result, 10);
    }

    /**
     * Test of getTimePast method, of class TimerPanel.
     */
    @Test
    public void testGetTimePast2() {
        System.out.println("getTimePast2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        long expResult = 1000;
        long result = instance.getTimePast();
        assertEquals(expResult, result, 10);
    }
    
    
}