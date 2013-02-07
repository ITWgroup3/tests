package Schoolware.UI;

import Schoolware.Json.JsonBuilder;
import Schoolware.Unit.QuestionData;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import org.json.JSONException;

public abstract class MouseEventCollectionPanel extends javax.swing.JPanel {

    private long initTime;
    private Hashtable<Integer, QuestionData> data;
    Date startTime;

    /**
     * Creates new form DataCollectPanel
     */
    public MouseEventCollectionPanel() {

        startTime = new Date();

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                record((java.awt.event.MouseEvent) event);

            }
        }, AWTEvent.MOUSE_EVENT_MASK);
        data = new Hashtable<>();
        initTime = System.currentTimeMillis();
    }

    private long getTimePast() {
        long time = System.currentTimeMillis() - initTime;
        initTime = System.currentTimeMillis();
        return time;
    }

    private void record(java.awt.event.MouseEvent evt) {
        int id = getCurrentQuestionID();
        if (id != -1) {
            QuestionData temp = data.get(id);
            if (temp == null) {
                temp = new QuestionData(id);
            }
            if (evt.getID() == MouseEvent.MOUSE_RELEASED) {
                int click = temp.getClicks();
                temp.setClicks(click + 1);
                long time = temp.getTime();
                temp.setTime(time + getTimePast());
                data.put(id, temp);
            }
        }
    }

    /**
     * get the record data
     *
     * @return
     */
    public Hashtable<Integer, QuestionData> getRecord() {
        return data;
    }

    /**
     * get the total time which the users spent on the whole test
     *
     * @return the test total time in long
     */
    public long getTotalTime() {
        long time = 0;
        Object[] keys = new ArrayList<Integer>(data.keySet()).toArray();
        for (int n = 0; n < keys.length; n++) {
            int id = (int) keys[n];
            QuestionData temp = data.get(id);
            time += temp.getTime();
        }
        return time / 1000;
    }

    /**
     * get the question's ID which is presented in the current frame If there is
     * no question in the current frame, return -1
     *
     * @return the current quesion's ID. -1 if there is no question in the frame
     */
    public abstract int getCurrentQuestionID();

    /**
     * get the question's type
     *
     * @param questionID the question's ID
     * @return the question's type
     */
    public abstract int getQuestionType(int questionID);

    /**
     * get the user's answer of a specific question
     *
     * @param questionID the question's iD
     * @return the String array stores the user's answer
     */
    public abstract String[] getUserAnswer(int questionID);

    /**
     * get the correct answer of a specific question
     *
     * @param questionID the question's iD
     * @return the String array stores the correct answer
     */
    public abstract String[] getCorrectAnswer(int questionID);

    /**
     * get the total mark of a specific question
     *
     * @param questionID the question's iD
     * @return the total mark of the question in double
     */
    public abstract double getTotalMark(int questionID);

    /**
     * get the user's mark of a specific question
     *
     * @param questionID the question's iD
     * @return the user's mark of the question in double
     */
    public abstract double getUserMark(int questionID);

    /**
     * get the application ID
     *
     * @return the application ID in String
     */
    public abstract int getApplicationID();

    private int generateQuestionID(int appID, int QuestionID) {
        String id = "1";
        int temp = 4 - (appID + "").length();
        for (int n = 0; n <= temp; n++) {
            id += "0";
        }
        id += appID;
        temp = 3 - (QuestionID + "").length();
        for (int n = 0; n <= temp; n++) {
            id += "0";
        }
        id += QuestionID;
        return Integer.parseInt(id);
    }

    /**
     * submit build the json file and submit it
     *
     * @param path the String path where the json file will be stored
     * @return true if the submit suceess, else return false
     */
    public boolean submit(String path) {
        try {
            Object[] keys = new ArrayList<Integer>(data.keySet()).toArray();
            Arrays.sort(keys);
            JsonBuilder jbuilder = JsonBuilder.getSingletonInstance();
            jbuilder.initialiseScore(startTime);
            jbuilder.setTotalQuestions(data.size());
            jbuilder.setTotalTime(getTotalTime());
            double finalMark = 0;
            double TotalMark = 0;
            for (int n = 0; n < keys.length; n++) {
                int id = (int) keys[n];
                int qID = generateQuestionID(getApplicationID(), id);
                QuestionData temp = data.get(id);
                jbuilder.setQuestionType(qID, getQuestionType(id) + "");
                jbuilder.setQuestionClicks(qID, temp.getClicks());
                jbuilder.setQuestionTime(qID, temp.getTime() / 1000);
                jbuilder.setQuestionCorrectAnswer(qID, getCorrectAnswer(id));
                jbuilder.setQuestionFinalAnswer(qID, getUserAnswer(id));
                jbuilder.setQuestionMark(qID, getUserMark(id));
                jbuilder.setQuestionTotalMark(qID, getTotalMark(id));
                jbuilder.setAppId(getApplicationID());
                finalMark += getUserMark(id);
                TotalMark += getTotalMark(id);
            }
            jbuilder.setTotalMark(TotalMark);
            jbuilder.setOverallScoreObtained(finalMark);
            jbuilder.finaliseScore(path);
            return true;
        } catch (JSONException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
