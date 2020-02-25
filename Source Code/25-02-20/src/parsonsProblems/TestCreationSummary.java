/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsonsProblems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author UP815458
 */
public class TestCreationSummary extends javax.swing.JFrame {
    
    static int qNo;
    //Fields for saving to the test string
    static String testTitle;
    static int testLength;
    static String[][] trueAnswers;
    static String[][] falseAnswers;
    static String[] qTextArray;
    
    static boolean[] defaultQuestions;
    boolean defaultAnswersDetected = false;
    
    /**
     * Creates new form testCreationSummary
     * @param inQNo Used to allow the user to return to editing the test where they last left off
     * @param inTestTitle A string carrying the title of the completed test
     * @param inQTextArray 1-dimensional String array containing the question descriptions
     * @param inTrueAnswers 2-dimensional String array containing all the true answers (up to a max of ten) for each of the ten questions
     * @param inFalseAnswers 2-dimensional String array containing all the false answers (up to a max of ten) for each of the ten questions
     */
    public TestCreationSummary(int inQNo, String inTestTitle, String[] inQTextArray, String[][] inTrueAnswers, String[][] inFalseAnswers) {
        initComponents();
        qNo = inQNo;
        testTitle = inTestTitle;
        testTitleDisp.setText("Test Title : " + testTitle);
        qTextArray = inQTextArray;
        trueAnswers = inTrueAnswers;
        falseAnswers = inFalseAnswers;
        defaultQuestions = new boolean[qTextArray.length];
        populateTable();
        optionA.setVisible(false);
        optionB.setVisible(false);
        setupFeedbackText();
    }
    
    private void setupFeedbackText(){
        //Change the feedback message beneath the table
        feedbackText.setVisible(false);
        for (int i = 0; i < defaultQuestions.length; i++) {
            if (defaultQuestions[i] == true) {
                defaultAnswersDetected = true;
            }
        }
        //System.out.println("result is : " + defaultAnswersDetected);
        //find out how many non-default answers there are, and which ones they are, and create the feedback text
        int defaultCount = 0;
        if (defaultAnswersDetected == true) {
            optionA.setVisible(true);
            optionB.setVisible(true);
        }
        String feedbackTextOutput = "";
        String[] feedbackList = new String[defaultQuestions.length];
        if (defaultAnswersDetected == true) {
            feedbackText.setVisible(true);
            allQsCompletedLabel.setText("All 10 questions written : No");
            for (int i = 0; i < defaultQuestions.length; i++) {
                if (defaultQuestions[i] == true) {
                    defaultCount++;
                    feedbackList[i] = "" + (i+1);
                    //System.out.print(feedbackList[i]);
                }
            }
            testLength = 10 - defaultCount;
            if (testLength == 0) {
                testLength = 1;
            }
            feedbackTextOutput = "There are " + defaultCount + " questions that are still at default values, \n these are : ";
            for (int i = 0; i < defaultQuestions.length; i++) {
                if(feedbackList[i] != null){
                    defaultCount--;
                    if(defaultCount == 0){
                        feedbackTextOutput = feedbackTextOutput + " and " + feedbackList[i];
                    }
                    else{
                        feedbackTextOutput = feedbackTextOutput + feedbackList[i] + ", ";
                    }
                    //System.out.println(feedbackTextOutput);
                }
            }
        }
        feedbackText.setText(feedbackTextOutput);
        noOptionSelectedLabel.setVisible(false);
    }
    
    private void populateTable(){
        DefaultTableModel t = new DefaultTableModel();
        statTable.setModel(t);
        t.addColumn("Question Number");
        t.addColumn("Description");
        t.addColumn("No. Of True Answers");
        t.addColumn("No. of False Answers");
        //Question Desc
        String[] qTextOutput = new String[qTextArray.length];
        for(int i = 0; i < qTextArray.length; i++){
            String defaultDesc = "UNDEFINED QUESTION " + (i+1) + " DESCRIPTION";
            if(qTextArray[i].equals(defaultDesc)){
                qTextOutput[i] = "Default";
            }
            else{
                qTextOutput[i] = qTextArray[i];
            }
        }
        //True
        String[] trueOutput = new String[trueAnswers.length];
        int trueCount = 0;
        for(int i = 0; i < trueAnswers.length; i++){
            boolean defaultValues = false;
            if(trueAnswers[i][0] == "DEFAULT TRUE ANSWER 1" && trueAnswers[i][1] == "DEFAULT TRUE ANSWER 2"){
                int j = 2;
                while(j < 10){
                    if("INVALID".equals(trueAnswers[i][j])){
                        defaultValues = true;
                        defaultQuestions[i] = true;
                    }
                    else{
                        defaultValues = false;
                        defaultQuestions[i] = false;
                    }
                    j++;
                }
            }
            if(defaultValues == false){
                for(int k = 0;k < trueAnswers[i].length;k++){
                    if(!"INVALID".equals(trueAnswers[i][k])){
                        trueCount++;
                    }
                }
            }
            if(defaultValues == true){
                trueOutput[i] = "DEFAULT";
            }
            else{
                trueOutput[i] = "" + trueCount;
            }
            //System.out.println(trueOutput[i]);
        }
        //False
        String[] falseOutput = new String[falseAnswers.length];
        
        for(int i = 0; i < falseAnswers.length; i++){
            int falseCount = 0;
            for(int k = 0;k < falseAnswers[i].length;k++){
                if(falseAnswers[i][k] != "INVALID"){
                    falseCount++;
                }
            }
            falseOutput[i] = "" + falseCount;
            //System.out.println(falseOutput[i]);
        }
        //Add rows
        for(int k = 0; k < 10; k++){
            t.addRow(new Object[]{"" + (k + 1), qTextOutput[k], trueOutput[k], falseOutput[k]});
        }
        //Disable editing
        statTable.setDefaultEditor(Object.class, null);
    }
    
    private String createSaveString(String AOrB){
        //TITLE
        String outputName = "/n/ NAME\n" + testTitle + "\n";
        //LENGTH
        String outputLength = "/l/ LENGTH/ \n";
        //if A is selected, set the length as it was calculated
        if("A".equals(AOrB)){
            outputLength = outputLength + testLength + "\n";
        }
        //if B is selected, or if there were no default answers detected, set the test length to the default 10. 
        else{
            testLength = 10;
            outputLength = outputLength + testLength + "\n";
        }
        //DESCRIPTION
        String outputDesc = "/d/ DESCRIPTIONS\n";
        int iD = 0;
        String descTemp = "";
        while(iD < 10){
            descTemp = descTemp + qTextArray[iD] + "\n";
            iD++;
        }
        outputDesc = outputDesc + descTemp;
        //TRUE
        String tempTrue = "";
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                tempTrue = tempTrue + trueAnswers[i][j] + "\n";
            }
        }
        String outputTrue = "/t/ TRUE ANSWERS\n" + tempTrue;
        //FALSE
        String tempFalse = "";
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10; j++){
                tempFalse = tempFalse + falseAnswers[i][j] + "\n";
            }
        }
        String outputFalse = "/f/ FALSE ANSWERS\n" + tempFalse;
        //FINISH
        String output = outputName + outputLength + outputDesc + outputTrue + outputFalse;
        return output;
    }
    
    private void writeFile(String inContent) throws IOException{
        FileWriter writer = new FileWriter("TESTDATA" + File.separator + "Tests" + File.separator + testTitle);
        writer.write(inContent);
        writer.close();
    }
    
    //String output = createSaveString();
//        
//        try{
//            writeSaveFile(output);
//        } catch (IOException ex) {
//            Logger.getLogger(CreateATestTrueFalse.class.getName()).log(Level.SEVERE, null, ex);
//        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        SummaryTitle = new javax.swing.JLabel();
        testTitleDisp = new javax.swing.JLabel();
        allQsCompletedLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        questionStatsPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        statTableScrollPane = new javax.swing.JScrollPane();
        statTable = new javax.swing.JTable();
        feedbackText = new javax.swing.JLabel();
        finishSaveButton = new javax.swing.JButton();
        whatWouldYouText = new javax.swing.JLabel();
        optionA = new javax.swing.JRadioButton();
        optionB = new javax.swing.JRadioButton();
        noOptionSelectedLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SummaryTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        SummaryTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SummaryTitle.setText("Summary");

        testTitleDisp.setText("Test Title : ");

        allQsCompletedLabel.setText("All 10 questions written : ");

        returnButton.setText("<< Return to editing");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        questionStatsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        title.setText("Question stats at a glance");

        statTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Question No.", "Description", "No. of true answers", "No. of false answers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        statTableScrollPane.setViewportView(statTable);

        feedbackText.setText("<html>The non-default questions in this edited test are: <br>");

        finishSaveButton.setText("Save & finish");
        finishSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishSaveButtonActionPerformed(evt);
            }
        });

        whatWouldYouText.setText("What would you like to do with this test when saving?");

        buttonGroup1.add(optionA);
        optionA.setText("A. trim down the questions to only the non-default questions");

        buttonGroup1.add(optionB);
        optionB.setText("B. keep all questions and use as if it were a normal test");

        noOptionSelectedLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        noOptionSelectedLabel.setForeground(new java.awt.Color(204, 0, 0));
        noOptionSelectedLabel.setText("No option was selected");

        javax.swing.GroupLayout questionStatsPanelLayout = new javax.swing.GroupLayout(questionStatsPanel);
        questionStatsPanel.setLayout(questionStatsPanelLayout);
        questionStatsPanelLayout.setHorizontalGroup(
            questionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finishSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statTableScrollPane)
                    .addGroup(questionStatsPanelLayout.createSequentialGroup()
                        .addGroup(questionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title)
                            .addComponent(whatWouldYouText)
                            .addGroup(questionStatsPanelLayout.createSequentialGroup()
                                .addGroup(questionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionA)
                                    .addComponent(optionB))
                                .addGap(40, 40, 40)
                                .addComponent(noOptionSelectedLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(feedbackText))
                .addContainerGap())
        );
        questionStatsPanelLayout.setVerticalGroup(
            questionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(feedbackText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whatWouldYouText)
                .addGroup(questionStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(questionStatsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optionA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optionB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finishSaveButton)
                        .addContainerGap())
                    .addGroup(questionStatsPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(noOptionSelectedLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SummaryTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(returnButton)
                            .addComponent(testTitleDisp)
                            .addComponent(allQsCompletedLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(questionStatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SummaryTitle)
                .addGap(10, 10, 10)
                .addComponent(testTitleDisp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allQsCompletedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionStatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(returnButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        CreateATestParson ct = new CreateATestParson(qNo, testTitle, qTextArray, trueAnswers, falseAnswers);
        ct.setVisible(true);
        ct.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_returnButtonActionPerformed

    private void finishSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishSaveButtonActionPerformed
        noOptionSelectedLabel.setVisible(false);
        String AOrB = "";
        if(defaultAnswersDetected == true){
            if(optionA.isSelected()){
                System.out.println("option A selected");
                AOrB = "A";
            }
            else if(optionB.isSelected()){
                System.out.println("option B selected");
                AOrB = "B";
            }
            else{
                noOptionSelectedLabel.setVisible(true);
            }
        }
        String output = createSaveString(AOrB);
        try {
            writeFile(output);
        } catch (IOException ex) {
            Logger.getLogger(TestCreationSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_finishSaveButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestCreationSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestCreationSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestCreationSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestCreationSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestCreationSummary(qNo, testTitle, qTextArray, trueAnswers, falseAnswers).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SummaryTitle;
    private javax.swing.JLabel allQsCompletedLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel feedbackText;
    private javax.swing.JButton finishSaveButton;
    private javax.swing.JLabel noOptionSelectedLabel;
    private javax.swing.JRadioButton optionA;
    private javax.swing.JRadioButton optionB;
    private javax.swing.JPanel questionStatsPanel;
    private javax.swing.JButton returnButton;
    private javax.swing.JTable statTable;
    private javax.swing.JScrollPane statTableScrollPane;
    private javax.swing.JLabel testTitleDisp;
    private javax.swing.JLabel title;
    private javax.swing.JLabel whatWouldYouText;
    // End of variables declaration//GEN-END:variables
}
