/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.UI.Template;

import Schoolware.Unit.Question;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author yukun
 */
public class QuestionPanel extends javax.swing.JPanel {

    /**
     * Creates new form QuestionPanel
     */
    JTextField answerText; //used when type is 3
    CheckboxGroup buttonGroup; //used when type is 1 or 2
    JPanel optionsArea;
    Checkbox[] boxGroup;
    TimerPanel timer;
    Question question;
    
    public QuestionPanel(Question question) {
        initComponents();
        this.question = question;
        timer = new TimerPanel(0, 100);
        jPanel4.add(timer);
        questionArea.setText(question.getContent());
        if (question.getType() == 3) {
            answerText = new JTextField();
            answerText.setSize(200, 200);
            answerArea.setViewportView(answerText);
        } else {
            optionsArea = new JPanel();
            optionsArea.setLayout(new GridLayout(question.getOptions().length, 1));
            boxGroup = new Checkbox[question.getOptions().length];
            answerArea.setViewportView(optionsArea);
            buttonGroup = new CheckboxGroup();
            for (int n = 0; n < question.getOptions().length; n++) {
                Checkbox button;
                if (question.getType() == 1) {
                    button = new Checkbox(question.getOptions()[n], false, buttonGroup);
                } else {
                    button = new Checkbox(question.getOptions()[n], false);
                }
                optionsArea.add(button);
                boxGroup[n] = button;
            }
        }
    }
    
    public QuestionPanel(Question question, String[] answer, long time) {
        initComponents();
        this.question = question;
        timer = new TimerPanel(time, 100);
        jPanel4.add(timer);
        questionArea.setText(question.getContent());
        if (question.getType() == 3) {
            answerText = new JTextField();
            answerText.setSize(200, 200);
            if (answer != null && answer[0] != null) {
                answerText.setText(answer[0]);
            }
            answerArea.setViewportView(answerText);
        } else {
            optionsArea = new JPanel();
            optionsArea.setLayout(new GridLayout(question.getOptions().length, 1));
            boxGroup = new Checkbox[question.getOptions().length];
            answerArea.setViewportView(optionsArea);
            buttonGroup = new CheckboxGroup();
            for (int n = 0; n < question.getOptions().length; n++) {
                Checkbox button;
                if (question.getType() == 1) {
                    button = new Checkbox(question.getOptions()[n], false, buttonGroup);
                } else {
                    button = new Checkbox(question.getOptions()[n], false);
                }
                optionsArea.add(button);
                boxGroup[n] = button;
            }
            if (answer != null) {
                for (int n = 0; n < answer.length; n++) {
                    try {
                        int intNum = Integer.parseInt(answer[n]);
                        boxGroup[intNum].setState(true);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            
        }
    }
    
    public String[] getAnswer() {
        ArrayList<String> answerList = new ArrayList<>();
        if (question.getType() == 3) {
            answerList.add(answerText.getText());
        } else {
            for (int n = 0; n < boxGroup.length; n++) {
                if (boxGroup[n].getState()) {
                    answerList.add("" + n);
                }
            }
        }
        String[] answer=new String[answerList.size()];
        for(int n=0;n<answerList.size();n++){
            answer[n]=answerList.get(n);
        }
        return answer;
    }
    
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        String testQuestion = "1+1=?";
        String[] testOptions;
        testOptions = new String[3];
        testOptions[0] = "zero 0";
        testOptions[1] = "one 1";
        testOptions[2] = "two 2";
        String[] correctAnswer = new String[1];
        correctAnswer[0] = "2";
        Question q = new Question(1, testQuestion, testOptions, 1.0, correctAnswer);
        QuestionPanel test = new QuestionPanel(q);
        frame.add(test);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        answerArea = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionArea = new javax.swing.JTextArea();

        jRadioButton1.setText("jRadioButton1");

        answerArea.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        answerArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Answer"));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Question"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        questionArea.setEditable(false);
        questionArea.setBackground(new java.awt.Color(238, 238, 238));
        questionArea.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        questionArea.setLineWrap(true);
        questionArea.setTabSize(3);
        questionArea.setWrapStyleWord(true);
        questionArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(questionArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(answerArea)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(answerArea, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane answerArea;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea questionArea;
    // End of variables declaration//GEN-END:variables
}
