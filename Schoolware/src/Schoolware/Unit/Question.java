/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.Unit;

/**
 *
 * @author yukun
 */
public class Question {
    
    private String content;
    private String[] options;
    private int type;
    private double totalScore;
    private String[] correctAnswer;
    
    /**
     * Question constructor
     * @param type the type of this question. The types values are stored in Configuration class
     * @param content the question content
     * @param options the operations' content which are stored in a String array 
     * @param totalScore the total score of this question
     * @param correctAnswer the corrects; content which are in a String array.
     * If the question only has one correct answer, the length of array should be one.
     */
    public Question(int type, String content, String[] options, double totalScore, String[] correctAnswer) {
        this.content = content;
        this.type = type;
        this.totalScore = totalScore;
        if(options==null){
            options=new String[1];
            options[0]="";
            this.type=Configuration.STRING_ANSWER;
        }
        this.options = new String[options.length];
        System.arraycopy(options, 0, this.options, 0, options.length); 
        if(correctAnswer==null){
            correctAnswer=new String[1];
            correctAnswer[0]="";
            this.type=Configuration.STRING_ANSWER;
        }
        if(correctAnswer.length>1){
            this.type=Configuration.MULTI_ANSWERS;
        }
        this.correctAnswer = new String[correctAnswer.length];
        System.arraycopy(correctAnswer, 0, this.correctAnswer, 0, correctAnswer.length); 
    }
    
    /**
     * get the question's content
     * @return the question's content in String
     */
    public String getContent(){
        return content;
    }
    
    /**
     * get the question opentions' content
     * @return the String array which stores the options
     */
    public String[] getOptions(){
        return options;
    }
    
    /**
     * get the question type
     * @return the question type in int
     */
    public int getType(){
        return type;
    }

    /**
     * get the total score of the question
     * @return the question total score in double
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * get the correct operation
     * @return the String array stores correct operations
     */
    public String[] getCorrectAnswer() {
        return correctAnswer;
    }
    
    
    
}
