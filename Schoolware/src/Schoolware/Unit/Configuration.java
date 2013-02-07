/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.Unit;

/**
 *
 * @author yukun
 */
public class Configuration {

    public static int SINGLE_ANSWER = 1;
    public static int MULTI_ANSWERS = 2;
    public static int STRING_ANSWER = 3;
    public static int EASY_LEVEL = 1;
    public static int MIDDLE_LEVEL = 2;
    public static int DIFFICULT_LEVEL = 3;

    public static String getAnswerType(int n) {
        if (n == 1) {
            return "Single answer";
        } else if (n == 2) {
            return "Multi answers";
        } else if (n == 3) {
            return "String answer";
        } else {
            return "unknow";
        }
    }

    public static String getDifficultyLevel(int n) {
        if (n == 1) {
            return "Easy";
        } else if (n == 2) {
            return "Middle";
        } else if (n == 3) {
            return "Difficult";
        } else {
            return "unknow";
        }
    }
}
