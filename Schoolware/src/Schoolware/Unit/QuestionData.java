/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.Unit;

/**
 *
 * @author yukun
 */
public class QuestionData {

    private int questionID;
    private int clicks;
    private long time;

    public QuestionData(int questionID) {
        this.questionID = questionID;
        clicks = 0;
        time = 0l;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    

    @Override
    public String toString() {
        return "QuestionData{" + "questionID=" + questionID + 
                ", clicks=" + clicks + ", time=" + '}';
    }
    
    
}
