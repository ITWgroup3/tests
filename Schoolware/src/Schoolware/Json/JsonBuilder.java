package Schoolware.Json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javax.xml.crypto.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonBuilder {

    static JsonBuilder singleton = null;
    static JSONArray jarray;
    JSONObject json;
    private static final String QuestionId = new String("questionid");
    private static final String Time = new String("time");
    private static final String ChangeTime = new String("changetime");
    private static final String Answer = new String("answer");
    private static final String TotalTime = new String("totaltime");
    private static final String Analytics = new String("analytics");
    private static final String FinalAnswer = new String("finalanswer");
    private static final String CorrectAnswer = new String("correctanswer");
    private static final String SelectedAnswers = new String("selectedanswers");
    private static final String Clicks = new String("clicks");
    private static final String AppId = new String("appid");
    private static final String TestId = new String("testid");
    private static final String OverallScore = new String("overallscoreobtained");
    private static final String QuesAttended = new String("quesattended");
    private static final String TotalQuestions = new String("totalquestions");
    private static final String Difficulty = new String("difficulty");
    private static final String QuestionMark = new String("questionmark");
    private static final String Mark = new String("mark");
    private static final String TotalMark = new String("totalmark");
    private static final String QuestionType = new String("questiontype");
    private static final String TestStartTime = new String("teststarttime");
    private static boolean finalreesultprinted = false;
    private static boolean quesattendedoverride = false;
    private static HashMap<Integer, String> previousanswer;

    /**
     * Constructor - Initialise JsonArray
     *
     * @throws JSONException
     */
    public JsonBuilder() {
    }

    /**
     * Get the instance of this class to access the methods under this library
     * class
     *
     * @return JSONBuilder_Instance
     * @throws JSONException
     */
    public static JsonBuilder getSingletonInstance() throws JSONException {

        synchronized (JsonBuilder.class) {
            if (singleton == null) {
                singleton = new JsonBuilder();
            }
        }
        return singleton;
    }

    /**
     * Call this method to Initialise the Library Functions. Do not call this
     * method anywhere else in the program because it refreshes everything Call
     * this method at the beginning of the test It initialises the
     * "TestStartTime" with current machine time It also initialises "TestId"
     *
     * @throws JSONException
     */
    public void initialiseScore(Date startTime) throws JSONException {
        previousanswer = new HashMap<>();
        jarray = new JSONArray();
        json = new JSONObject();
        //json.put(TotalTime, 0);
        //json.put(OverallScore, 0);
        //json.put(QuesAttended, 0);
        //json.put(Clicks, 0);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
        json.put(TestStartTime, sdfDate.format(startTime));
//        json.put(TestStartTime, startTime);
        Random randomGenerator = new Random();
        int randomInt = 0;
        for (int idx = 1; idx <= 100000; ++idx) {
            randomInt = randomGenerator.nextInt(100);
        }

        System.currentTimeMillis();
        json.put(TestId, System.currentTimeMillis() + randomInt);
    }

    /**
     * This method create new QuestionID if its not already present
     *
     * @param Questionid
     * @throws JSONException
     */
    private void createQuestionStructure(int Questionid) throws JSONException {
        JSONObject temp = new JSONObject();
        temp.put(QuestionId, Questionid);
        temp.put(Time, 0);
        jarray.put(temp);
        return;
    }

    /**
     * This methods takes all the inputs at one stroke to construct the score
     * for each question with more than one answercode
     *
     * @param Questionid
     * @param correctanswer
     * @param finalanswer
     * @param mark
     * @param time
     * @param noofclicks
     * @throws JSONException
     */
    public void putSingleEntry(int Questionid, String correctanswer, String finalanswer, double mark, long time, int noofclicks) throws JSONException {
        //JSONObject temp = new JSONObject();
        boolean quesnotfound = false;
        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                jarray.getJSONObject(i).put(FinalAnswer, finalanswer);
                jarray.getJSONObject(i).put(CorrectAnswer, correctanswer);
                jarray.getJSONObject(i).put(Mark, mark);
                jarray.getJSONObject(i).put(Time, time);
                jarray.getJSONObject(i).put(Clicks, noofclicks);
                quesnotfound = false;
                return;
            } else {
                quesnotfound = true;
            }
        }
        if (quesnotfound || jarray.length() == 0) {
            createQuestionStructure(Questionid);
            for (int i = 0; i < jarray.length(); i++) {
                if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                    jarray.getJSONObject(i).put(FinalAnswer, finalanswer);
                    jarray.getJSONObject(i).put(CorrectAnswer, correctanswer);
                    jarray.getJSONObject(i).put(Mark, mark);
                    jarray.getJSONObject(i).put(Time, time);
                    jarray.getJSONObject(i).put(Clicks, noofclicks);
                    return;
                }
            }
        }
    }

    /**
     * This methods takes all the inputs at one stroke to construct the score
     * for each question with more than one answers
     *
     * @param Questionid
     * @param correctanswer
     * @param finalanswer
     * @param mark
     * @param time
     * @param noofclicks
     * @throws JSONException
     */
    public void putSingleEntry(int Questionid, String correctanswer, String finalanswer[], String mark, long time, int noofclicks) throws JSONException {
        boolean quesnotfound = false;
        String finalans = "";

        for (int j = 0; j < finalanswer.length; j++) {
            finalans = finalans + "," + finalanswer[j];
        }

        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                jarray.getJSONObject(i).put(FinalAnswer, finalans);
                jarray.getJSONObject(i).put(CorrectAnswer, correctanswer);

                jarray.getJSONObject(i).put(Mark, mark);
                jarray.getJSONObject(i).put(Time, time);
                jarray.getJSONObject(i).put(Clicks, noofclicks);
                quesnotfound = false;
                return;
            } else {
                quesnotfound = true;
            }
        }
        if (quesnotfound || jarray.length() == 0) {
            createQuestionStructure(Questionid);
            for (int i = 0; i < jarray.length(); i++) {
                if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                    jarray.getJSONObject(i).put(FinalAnswer, finalans);
                    jarray.getJSONObject(i).put(CorrectAnswer, correctanswer);
                    jarray.getJSONObject(i).put(Mark, mark);
                    jarray.getJSONObject(i).put(Time, time);
                    jarray.getJSONObject(i).put(Clicks, noofclicks);
                    return;
                }
            }
        }
    }

    /**
     * This method add selected options if they are not already added or adds
     * the additional time value if the options have been already included, when
     * the record for question id is already created If the selected answercode
     * is alreaady there in the SelectedAnswers array, then the time value is
     * incremented If the question is not already created, then it add the
     * "QuestionId" and "SelectedAnswers" JSONArray alone. Not other fields. Use
     * this method to add the selected options whenever student selects an
     * option
     *
     * @param Questionid
     * @param answercode
     * @param time
     * @throws JSONException
     */
    //yes
    public void addSelectedAnswer(int Questionid, String answercode, long time) throws JSONException {
        //Traversing the JSONArray list
        boolean quesnotfound = false;
        for (int i = 0; i < jarray.length(); i++) {
            //Checking if the Question already exist in the JSONArray
            if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                addQuestionAnswers(Questionid, answercode, time);
                quesnotfound = false;

                return;
            } else {
                quesnotfound = true;
                //createQuestionStructure(Questionid);
                //addQuestionAnswers(Questionid, answercode, time);
                //return;

            }
        }
        if (quesnotfound || jarray.length() == 0) {
            createQuestionStructure(Questionid);
            addQuestionAnswers(Questionid, answercode, time);

        }
    }

    /**
     * This method adds new or updates existing "SelectedAnswers" for a given
     * QuestionId Use this method whenever a students selects an option along
     * with the spent on selecting that option
     *
     * @param Questionid
     * @param answercode
     * @param time
     * @throws JSONException
     */
    private void addQuestionAnswers(int Questionid, String answercode, long time) throws JSONException {
        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                if (jarray.getJSONObject(i).isNull(SelectedAnswers)) {
                    previousanswer.put(Questionid, answercode);
                    JSONArray jatemp = new JSONArray();
                    JSONArray jbtemp = new JSONArray();
                    JSONObject jotemp = new JSONObject();
                    jotemp.put(Answer, answercode);
                    jotemp.put(ChangeTime, time);
                    jbtemp.put(jotemp);
                    jatemp.put(jbtemp);
                    jarray.getJSONObject(i).put(SelectedAnswers, jatemp);
                    return;
                } else {
                    for (int j = 0; j < jarray.getJSONObject(i).getJSONArray(SelectedAnswers).length(); j++) {
                        if (jarray.getJSONObject(i).getJSONArray(SelectedAnswers).getJSONArray(j).get(0).equals(answercode) && previousanswer.get(QuestionId) == answercode) {
                            long prevtime = 0;
                            prevtime = jarray.getJSONObject(i).getJSONArray(SelectedAnswers).getJSONArray(j).getLong(1);
                            jarray.getJSONObject(i).getJSONArray(SelectedAnswers).getJSONArray(j).put(1, time + prevtime);
                            return;
                        } else {
                            JSONArray jbtemp = new JSONArray();
                            JSONObject jotemp = new JSONObject();
                            jotemp.put(Answer, answercode);
                            jotemp.put(ChangeTime, time);
                            jbtemp.put(jotemp);
                            jarray.getJSONObject(i).getJSONArray(SelectedAnswers).put(jbtemp);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method adds time to previous value of "Time" if time for Questionid
     * is already present or sets "Time" to time if not present for Questionid
     * Use this method to add time spent on a question when the student does not
     * select any option
     *
     * @param Questionid
     * @param time
     * @throws JSONException
     */
    public void addQuestionTime(int Questionid, long time) throws JSONException {
        boolean questionnotfound = false;
        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                if (jarray.getJSONObject(i).isNull(Time)) {
                    jarray.getJSONObject(i).put(Time, time);
                } else {
                    long prevtime = 0;
                    prevtime = jarray.getJSONObject(i).getLong(Time);
                    jarray.getJSONObject(i).put(Time, prevtime + time);
                    questionnotfound = false;
                    return;
                }
            } else {
                questionnotfound = true;

            }
        }
        if (questionnotfound || jarray.length() == 0) {
            createQuestionStructure(Questionid);
            for (int i = 0; i < jarray.length(); i++) {
                if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                    jarray.getJSONObject(i).put(Time, time);
                }
            }
        }
    }

    private <T> void duplicateputQuestionElements(String Element, int Questionid, T data) throws JSONException {
        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(JsonBuilder.QuestionId) == Questionid) {
                if (jarray.getJSONObject(i).isNull(Element)) {
                    jarray.getJSONObject(i).put(Element, data);
                    return;
                } else {
                    jarray.getJSONObject(i).put(Element, data);
                    return;
                }

            }
        }
    }

    private <T> void putQuestionElements(String Element, int Questionid, T data) throws JSONException {
        boolean questionnotfound = false;

        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(JsonBuilder.QuestionId) == Questionid) {
                if (jarray.getJSONObject(i).isNull(Element)) {
                    jarray.getJSONObject(i).put(Element, data);
                    questionnotfound = false;

                    return;
                } else {
                    jarray.getJSONObject(i).put(Element, data);
                    return;
                }

            } else {
                questionnotfound = true;

            }
        }
        if (questionnotfound || jarray.length() == 0) {
            createQuestionStructure(Questionid);
            duplicateputQuestionElements(Element, Questionid, data);
            return;
        }
    }

    /**
     * Sets the Final answer for a Question. Replace the old value with new
     * value.
     *
     * @param Questionid
     * @param answer
     * @throws JSONException
     */
    public void setQuestionFinalAnswer(int Questionid, String answer) throws JSONException {
        putQuestionElements(FinalAnswer, Questionid, answer);
    }

    /**
     * Sets the Final answer for a Question. Replace the old value with new
     * value. If the Final Answer is a String[]. Say {"a","a"}. Use this method.
     *
     * @param Questionid
     * @param answer
     * @throws JSONException
     */
    public void setQuestionFinalAnswer(int Questionid, String[] answer) throws JSONException {
        String finalans = "";
        for (int j = 0; j < answer.length; j++) {
            finalans = finalans + "," + answer[j];
        }
        putQuestionElements(FinalAnswer, Questionid, finalans.substring(1));
    }

    /**
     * Sets the Time for a Question. Replace the old value with new value.
     *
     * @param Questionid
     * @param time
     * @throws JSONException
     */
    public void setQuestionTime(int Questionid, long time) throws JSONException {
        putQuestionElements(Time, Questionid, time);
    }

    /**
     * Sets the CorrectAnswer for a Question. Replace the old value with new
     * value.
     *
     * @param Questionid
     * @param correctanswer
     * @throws JSONException
     */
    public void setQuestionCorrectAnswer(int Questionid, String correctanswer) throws JSONException {
        putQuestionElements(CorrectAnswer, Questionid, correctanswer);
    }

    /**
     * Sets the Correct answer for a Question. Replace the old value with new
     * value. If the Correct Answer is a String[]. Say {"a","a"}. Use this
     * method.
     *
     * * @param Questionid
     * @param correctanswer
     * @throws JSONException
     */
    public void setQuestionCorrectAnswer(int Questionid, String[] correctanswer) throws JSONException {
        String finalans = "";
        for (int j = 0; j < correctanswer.length; j++) {
            finalans = finalans + "," + correctanswer[j];
        }

        putQuestionElements(CorrectAnswer, Questionid, finalans.substring(1));
    }

    /**
     * Sets the User's Mark for a Question. Replace the old value with new
     * value.
     *
     * @param Questionid
     * @param mark
     * @throws JSONException
     */
    public void setQuestionMark(int Questionid, double mark) throws JSONException {
        putQuestionElements(Mark, Questionid, mark);
    }

    /**
     * Sets the Question Mark for a Question. Replace the old value with new
     * value.
     *
     * @param Questionid
     * @param mark
     * @throws JSONException
     */
    public void setQuestionTotalMark(int Questionid, double mark) throws JSONException {
        putQuestionElements(TotalMark, Questionid, mark);
    }

    /**
     * Sets the QuestionType for a Question. Replace the old value with new
     * value.
     *
     * @param Questionid
     * @param questiontype
     * @throws JSONException
     */
    public void setQuestionType(int Questionid, String questiontype) throws JSONException {
        putQuestionElements(QuestionType, Questionid, questiontype);
    }

    /**
     * Sets the Clicks for a Question. Replace the old value with new value.
     *
     * @param Questionid
     * @param clicks
     * @throws JSONException
     */
    public void setQuestionClicks(int Questionid, int clicks) throws JSONException {
        putQuestionElements(Clicks, Questionid, clicks);
    }

    /**
     * Increments the Clicks of a Question. If Clicks already present for a
     * question, increments 1 to the previous value If Clicks does not exist for
     * a question, initialises Clicks with 1.
     *
     * @param Questionid
     * @throws JSONException
     */
    public void incrementQuestionClicks(int Questionid) throws JSONException {
        boolean questionnotfound = false;

        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                if (jarray.getJSONObject(i).isNull(Clicks)) {
                    jarray.getJSONObject(i).put(Clicks, 1);
                    questionnotfound = false;
                    return;
                } else {
                    int prevclicks = jarray.getJSONObject(i).getInt(Clicks);
                    jarray.getJSONObject(i).put(Clicks, prevclicks + 1);
                    return;
                }
            } else {
                questionnotfound = true;
            }
        }
        if (questionnotfound || jarray.length() == 0) {
            createQuestionStructure(Questionid);
            for (int i = 0; i < jarray.length(); i++) {
                if (jarray.getJSONObject(i).getInt(QuestionId) == Questionid) {
                    jarray.getJSONObject(i).put(Clicks, 1);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    private <T> T getQuestionElements(int Questionid, String Element) throws JSONException {
        for (int i = 0; i < jarray.length(); i++) {
            if (jarray.getJSONObject(i).getInt(JsonBuilder.QuestionId) == Questionid) {
                if (jarray.getJSONObject(i).isNull(Element)) {
                    return null;
                } else {
                    return (T) jarray.getJSONObject(i).get(Element);
                }

            }
        }
        return null;
    }

    /**
     * Returns the String Value of the FinalAnswer of a Question
     *
     * @param Questionid
     * @return FinalAnswer - String
     * @throws JSONException
     */
    public String getQuestionFinalAnswer(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, FinalAnswer);
    }

    /**
     * Returns the String Value of the CorrectAnswer of a Question
     *
     * @param Questionid
     * @return CorrectAnswer
     * @throws JSONException
     */
    public String getQuestionCorrectAnswer(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, CorrectAnswer);
    }

    /**
     * Returns the long Value of Time of a Question
     *
     * @param Questionid
     * @return Time
     * @throws JSONException
     */
    public long getQuestionTime(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, Time);
    }

    /**
     * Returns the int Value of Clicks of a Question
     *
     * @param Questionid
     * @return Clicks
     * @throws JSONException
     */
    public int getQuestionClicks(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, Clicks);
    }

    /**
     * Returns the double Value of user's Mark of a Question
     *
     * @param Questionid
     * @return Mark
     * @throws JSONException
     */
    public double getQuestionMark(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, Mark);
    }

    /**
     * Returns the double Value of total Mark of a Question
     *
     * @param Questionid
     * @return Mark
     * @throws JSONException
     */
    public double getQuestionTotalMark(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, QuestionMark);
    }

    /**
     * Returns the String Value of QuestionType of a Question
     *
     * @param Questionid
     * @return QuestionType
     * @throws JSONException
     */
    public String getQuestionType(int Questionid) throws JSONException {
        return getQuestionElements(Questionid, QuestionType);
    }

    /**
     * Set AppId of the application. Replace the old value with new value.
     *
     * @param appid
     * @throws JSONException
     */
    public void setAppId(int appid) throws JSONException {
        json.put(AppId, appid);
    }

    /**
     * Set TestId. Replace the old value with new value.
     *
     * @param testid
     * @throws JSONException
     */
    public void setTestId(String testid) throws JSONException {
        json.put(TestId, testid);
    }

    /**
     * Set TotalTime of the test. Replace the old value with new value.
     *
     * @param totaltime
     * @throws JSONException
     */
    public void setTotalTime(long totaltime) throws JSONException {
        json.put(TotalTime, totaltime);
    }

    /**
     * Set TotalMark of the test. Replace the old value with new value.
     *
     * @param totalmark
     * @throws JSONException
     */
    public void setTotalMark(double totalmark) throws JSONException {
        json.put(TotalMark, totalmark);

    }

    /**
     * Set OverallScore of the test. Replace the old value with new value.
     *
     * @param overallscore
     * @throws JSONException
     */
    public void setOverallScoreObtained(double overallscore) throws JSONException {
        json.put(OverallScore, overallscore);
    }

    /**
     * Set QuestionsAttended of the test. Replace the old value with new value.
     * The library automatically calculates by looking at final answer of each
     * questions entered. Use this method to override the automatic
     * functionality and set OverallScore
     *
     * @param quesattended
     * @throws JSONException
     */
    public void setQuesAttended(long quesattended) throws JSONException {
        quesattendedoverride = true;
        json.put(QuesAttended, quesattended);
    }

    /**
     * Set TotalQuestions present in the test. Replace the old value with new
     * value.
     *
     * @param totalquestions
     * @throws JSONException
     */
    public void setTotalQuestions(long totalquestions) throws JSONException {
        json.put(TotalQuestions, totalquestions);
    }

    /**
     * Set Difficulty level of the test. Replace the old value with new value.
     *
     * @param difficulty
     * @throws JSONException
     */
    public void setDifficulty(String difficulty) throws JSONException {
        json.put(Difficulty, difficulty);
    }

    /**
     * Set Difficulty level of the test. Replace the old value with new value.
     *
     * @param date
     * @throws JSONException
     */
    /**
     * Returns the long Value of the TotalTime of the test
     *
     * @return TotalTime
     * @throws JSONException
     */
    public long getTotalTime() throws JSONException {
        if (!json.isNull(TotalTime)) {
            return json.getLong(TotalTime);
        } else {
            return 0;
        }
    }

    /**
     * all the questions' id in the instance
     *
     * @return
     * @throws JSONException
     */
    public int[] getQuestionIdsInserted() throws JSONException {
        int[] QuestionIds = {};
        int k = 0;
        for (int i = 0; i < jarray.length(); i++) {
            if (!jarray.getJSONObject(i).isNull(QuestionId)) {
                QuestionIds[k] = jarray.getJSONObject(i).getInt(QuestionId);
                k++;
            }
        }
        return QuestionIds;
    }

    /**
     * Returns the double Value of the OverallScore of the test
     *
     * @return OverallScore
     * @throws JSONException
     */
    public double getOverallScore() throws JSONException {
        if (!json.isNull(OverallScore)) {
            return json.getLong(OverallScore);
        } else {
            return 0;
        }
    }

    /**
     * Returns the long Value of the number of QuesAttended in the test
     *
     * @return FinalAnswer
     * @throws JSONException
     */
    public long getQuesAttended() throws JSONException {
        int quesattended = 0;
        for (int i = 0; i < jarray.length(); i++) {
            if (!jarray.getJSONObject(i).isNull(FinalAnswer)) {
                quesattended++;
            }
        }
        return quesattended;
    }

    /**
     * Returns the long Value of the Total number of Questions in the test
     *
     * @return TotalQuestions
     * @throws JSONException
     */
    public long getTotalQuestions() throws JSONException {
        if (!json.isNull(TotalQuestions)) {
            return json.getLong(TotalQuestions);
        } else {
            return 0;
        }
    }

    /**
     * Automatically calculates the no. of questions attended by reading the
     * "SelectedAnswers" JsonArray of each question
     *
     * @throws JSONException
     */
    private void calculateNoOfQuestionAttended() throws JSONException {
        int quesattended = 0;
        for (int i = 0; i < jarray.length(); i++) {
            if (!jarray.getJSONObject(i).isNull(FinalAnswer)) {
                quesattended++;
            }
        }
        json.put(QuesAttended, quesattended);
    }

    /**
     * Returns the missing elements/fields in the ScoreDetails for each question
     * and overall test This method can be called to check what are the field
     * that the developer has missed to set values for
     *
     * @return Missing_Fields_Message
     * @throws JSONException
     */
    public String analyseScore() throws JSONException {
        json.put(Analytics, jarray);
        JsonChecker obj = new JsonChecker(json);
        return obj.JsonChecking();
    }

    /**
     * This method is used to get the final Result Builded. It returns the final
     * JSON Score Object to be sent to the server This method should be called
     * only once when the test in completely over and when you want to save the
     * report
     *
     * @throws JSONException
     */
    public void finaliseScore(String path) throws JSONException {
        if (!finalreesultprinted) {
            finalreesultprinted = true;
            if (!quesattendedoverride) {
                calculateNoOfQuestionAttended();
            }
            json.put(Analytics, jarray);
            SaveScore sobj = new SaveScore();
            sobj.saveBuildedScore(path, json);
            jarray = new JSONArray();
            json = new JSONObject();
        }
    }
}