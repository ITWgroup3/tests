/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schoolware.UI.Template;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author yukun
 */
public class ContentPanel extends javax.swing.JPanel {

    /**
     * Creates new form IntroductionPanel
     */
    public ContentPanel(String content, String title) {
        initComponents();
        this.contentArea.setText(content);
        contentArea.setBorder(javax.swing.BorderFactory.createTitledBorder(title));
    }

    public ContentPanel(String content) {
        initComponents();
        this.contentArea.setText(content);
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        ContentPanel test = new ContentPanel("content with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with titlecontent with title", "title");
//        ContentPanel test = new ContentPanel("content only");
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout());
        frame.add(test);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        contentArea = new javax.swing.JTextArea();

        setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setViewportView(contentArea);

        contentArea.setEditable(false);
        contentArea.setBackground(new java.awt.Color(238, 238, 238));
        contentArea.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        contentArea.setLineWrap(true);
        contentArea.setTabSize(3);
        contentArea.setWrapStyleWord(true);
        contentArea.setDoubleBuffered(true);
        jScrollPane1.setViewportView(contentArea);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea contentArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}