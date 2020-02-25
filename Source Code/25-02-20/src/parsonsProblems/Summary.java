/*
 * Summary/Save results screen
 * ParsonsProblems V0.3
 */
package parsonsProblems;

import java.awt.Color;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author UP815458
 */
public class Summary extends javax.swing.JFrame {
    //The question data is required for the ability to quit to the previous screen
    static String testName;
    static int testLength;
    static String [] qTextArray;
    static String [][] trueAnswers;
    static String [][] falseAnswers;
    // All completedQs booleans set to true because all questions should be completed in order to get to this screen
    static boolean[] completedQs = new boolean[]{true, true, true, true, true, true, true, true, true, true};
    static int[] marks;
    static int totalMarks;
    // This will inherit the marks passed in, and is used for the quit button so that the user can go back to the questions
    static int[] outCurrentPoints;
    
    /**
     * Creates new form Summary
     * @param inTestName
     * @param inLength
     * @param inQTextArray
     * @param inTrueAnswers
     * @param inFalseAnswers
     * @param inMarks The marks passed in from either the question select screen or the question UI
     */
    public Summary(String inTestName, int inLength, String[] inQTextArray, String[][] inTrueAnswers, String[][] inFalseAnswers,int [] inMarks) {
        testName = inTestName;
        testLength = inLength;
        qTextArray = inQTextArray;
        trueAnswers = inTrueAnswers;
        falseAnswers = inFalseAnswers;
        marks = inMarks;
        totalMarks = workOutTotal();
        outCurrentPoints = marks;
        initComponents();
        showQuestionLabels();
        setMarkLabels();
        assignGrade();
    }
    
    private static int workOutTotal(){
        int totalTemp = 0;
        int i = 0;
        int temp = 0;
        while(i < marks.length){
            temp = temp + marks[i];
            i++;
        }
        System.out.println("total : " + totalTemp);
        return totalTemp;
    }
    
    private void showQuestionLabels(){
        if(testLength < 10){
            question2.setVisible(false);
            marksQ2.setVisible(false);
            question3.setVisible(false);
            marksQ3.setVisible(false);
            question4.setVisible(false);
            marksQ4.setVisible(false);
            question5.setVisible(false);
            marksQ5.setVisible(false);
            question6.setVisible(false);
            marksQ6.setVisible(false);
            question7.setVisible(false);
            marksQ7.setVisible(false);
            question8.setVisible(false);
            marksQ8.setVisible(false);
            question9.setVisible(false);
            marksQ9.setVisible(false);
            question10.setVisible(false);
            marksQ10.setVisible(false);
            if(testLength >= 2){
                question2.setVisible(true);
                marksQ2.setVisible(true);
            }
            if(testLength >= 3){
                question3.setVisible(true);
                marksQ3.setVisible(true);
            }
            if(testLength >= 4){
                question4.setVisible(true);
                marksQ4.setVisible(true);
            }
            if(testLength >= 5){
                question5.setVisible(true);
                marksQ5.setVisible(true);
            }
            if(testLength >= 6){
                question6.setVisible(true);
                marksQ6.setVisible(true);
            }
            if(testLength >= 7){
                question7.setVisible(true);
                marksQ7.setVisible(true);
            }
            if(testLength >= 8){
                question8.setVisible(true);
                marksQ8.setVisible(true);
            }
            if(testLength >= 9){
                question9.setVisible(true);
                marksQ9.setVisible(true);
            }
        }
    }
    
    private void setMarkLabels(){
        marksQ1.setText(marks[0] + "/5");
        marksQ2.setText(marks[1] + "/5");
        marksQ3.setText(marks[2] + "/5");
        marksQ4.setText(marks[3] + "/5");
        marksQ5.setText(marks[4] + "/5");
        marksQ6.setText(marks[5] + "/5");
        marksQ7.setText(marks[6] + "/5");
        marksQ8.setText(marks[7] + "/5");
        marksQ9.setText(marks[8] + "/5");
        marksQ10.setText(marks[9] + "/5");
    }
    
    Color goodGreen = new Color(51,204,0);
    Color averageOrange = new Color(255,153,0);
    private void assignGrade(){
        int marksPercent = 100*(totalMarks/50);
        System.out.println("Percentage marks : " + marksPercent);
        if(marksPercent <= 80){
            gradeDisp.setForeground(goodGreen);
            gradeDisp.setText("A");
        }
        else if(marksPercent <= 70){
            gradeDisp.setForeground(goodGreen);
            gradeDisp.setText("B");
        }
        else if(marksPercent <= 60){
            gradeDisp.setForeground(averageOrange);
            gradeDisp.setText("C");
        }
        else if(marksPercent <= 40){
            gradeDisp.setForeground(averageOrange);
            gradeDisp.setText("D");
        }
        else{
            gradeDisp.setForeground(Color.RED);
            gradeDisp.setText("F");
        }
    }
    
    private void writeFile(String inContent, String inFilename) throws IOException{
        FileWriter writer = new FileWriter("TESTDATA" + File.separator +"Results" + File.separator + "RESULTS--" + inFilename);
        writer.write(inContent);
        writer.close();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleLabel = new javax.swing.JLabel();
        marksPanel = new javax.swing.JPanel();
        marksTitle = new javax.swing.JLabel();
        question1 = new javax.swing.JLabel();
        marksQ1 = new javax.swing.JLabel();
        question2 = new javax.swing.JLabel();
        marksQ2 = new javax.swing.JLabel();
        question3 = new javax.swing.JLabel();
        marksQ3 = new javax.swing.JLabel();
        question4 = new javax.swing.JLabel();
        marksQ4 = new javax.swing.JLabel();
        question5 = new javax.swing.JLabel();
        marksQ5 = new javax.swing.JLabel();
        question6 = new javax.swing.JLabel();
        marksQ6 = new javax.swing.JLabel();
        question7 = new javax.swing.JLabel();
        marksQ7 = new javax.swing.JLabel();
        question8 = new javax.swing.JLabel();
        marksQ8 = new javax.swing.JLabel();
        question9 = new javax.swing.JLabel();
        marksQ9 = new javax.swing.JLabel();
        question10 = new javax.swing.JLabel();
        marksQ10 = new javax.swing.JLabel();
        gradePanel = new javax.swing.JPanel();
        gradeTitle = new javax.swing.JLabel();
        gradeDisp = new javax.swing.JLabel();
        savePanel = new javax.swing.JPanel();
        saveTestLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        fileNameLabel = new javax.swing.JLabel();
        fileDestTextField = new javax.swing.JTextField();
        feedbackText = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        Quit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        TitleLabel.setText("Test Summary");

        marksPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        marksTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        marksTitle.setText("Marks");

        question1.setText("Question 1 :");

        marksQ1.setText("0/5");

        question2.setText("Question 2 :");

        marksQ2.setText("0/5");

        question3.setText("Question 3 :");

        marksQ3.setText("0/5");

        question4.setText("Question 4 :");

        marksQ4.setText("0/5");

        question5.setText("Question 5 :");

        marksQ5.setText("0/5");

        question6.setText("Question 6 :");

        marksQ6.setText("0/5");

        question7.setText("Question 7 :");

        marksQ7.setText("0/5");

        question8.setText("Question 8 :");

        marksQ8.setText("0/5");

        question9.setText("Question 9 :");

        marksQ9.setText("0/5");

        question10.setText("Question 10 :");

        marksQ10.setText("0/5");

        javax.swing.GroupLayout marksPanelLayout = new javax.swing.GroupLayout(marksPanel);
        marksPanel.setLayout(marksPanelLayout);
        marksPanelLayout.setHorizontalGroup(
            marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(marksPanelLayout.createSequentialGroup()
                        .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(question2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(question1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(marksQ1)
                            .addComponent(marksQ2)
                            .addComponent(marksQ3)
                            .addComponent(marksQ4)
                            .addComponent(marksQ5)
                            .addComponent(marksQ6)
                            .addComponent(marksQ7)
                            .addComponent(marksQ8)
                            .addComponent(marksQ9)
                            .addComponent(marksQ10)))
                    .addComponent(marksTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        marksPanelLayout.setVerticalGroup(
            marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, marksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question1)
                    .addComponent(marksQ1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question2)
                    .addComponent(marksQ2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question3)
                    .addComponent(marksQ3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question4)
                    .addComponent(marksQ4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question5)
                    .addComponent(marksQ5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question6)
                    .addComponent(marksQ6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question7)
                    .addComponent(marksQ7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question8)
                    .addComponent(marksQ8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question9)
                    .addComponent(marksQ9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(question10)
                    .addComponent(marksQ10))
                .addContainerGap())
        );

        gradePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        gradeTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gradeTitle.setText("Grade:");

        gradeDisp.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        gradeDisp.setForeground(new java.awt.Color(255, 153, 0));
        gradeDisp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gradeDisp.setText("Grade");

        javax.swing.GroupLayout gradePanelLayout = new javax.swing.GroupLayout(gradePanel);
        gradePanel.setLayout(gradePanelLayout);
        gradePanelLayout.setHorizontalGroup(
            gradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gradeDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gradePanelLayout.createSequentialGroup()
                        .addComponent(gradeTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        gradePanelLayout.setVerticalGroup(
            gradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gradeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gradeDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        savePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        saveTestLabel.setText("Save test results:");

        NameLabel.setText("Name:");

        fileNameLabel.setText("Filename : ");

        feedbackText.setForeground(new java.awt.Color(255, 0, 0));

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout savePanelLayout = new javax.swing.GroupLayout(savePanel);
        savePanel.setLayout(savePanelLayout);
        savePanelLayout.setHorizontalGroup(
            savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(savePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(savePanelLayout.createSequentialGroup()
                        .addComponent(fileNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileDestTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(savePanelLayout.createSequentialGroup()
                        .addGroup(savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(savePanelLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(feedbackText))
                            .addComponent(saveTestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(savePanelLayout.createSequentialGroup()
                        .addComponent(NameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField)))
                .addContainerGap())
        );
        savePanelLayout.setVerticalGroup(
            savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, savePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveTestLabel)
                .addGap(11, 11, 11)
                .addGroup(savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameLabel)
                    .addComponent(fileDestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton))
                .addGap(12, 12, 12)
                .addComponent(feedbackText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Quit.setText("Quit");
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Quit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(savePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(marksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(marksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gradePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Quit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String fileDest = fileDestTextField.getText();
        if("".equals(fileDest)){
            feedbackText.setForeground(Color.RED);
            feedbackText.setText("The filename cannot be empty");
        }
        // checks if any of the individual characters "\/:*?"<> or |"are used in the filename text box and throws an error message if found
        else if( fileDest.contains("\\") == true ||
            fileDest.contains("/") == true ||
            fileDest.contains(":") == true ||
            fileDest.contains("*") == true ||
            fileDest.contains("?") == true ||
            fileDest.contains("\"") == true ||
            fileDest.contains("<") == true ||
            fileDest.contains(">") == true){
            feedbackText.setForeground(Color.RED);
            feedbackText.setText("<html>A file name cannot contain<br> any of the following characters : <br> \\ / : * ? \" < >");
        }
        else{
            String name = nameTextField.getText();
            String grade = gradeDisp.getText();
            feedbackText.setText("");
            String fileName = fileDest + ".txt";
            String fileContent = "RESULTS"
                               + "Test Name : " + testName
                               + "Name : " + name + "\n"
                               + "Marks : \n"
                               + "Question 1 : " + marks[0] + "/5 \n"
                               + "Question 2 : " + marks[1] + "/5 \n"
                               + "Question 3 : " + marks[2] + "/5 \n"
                               + "Question 4 : " + marks[3] + "/5 \n"
                               + "Question 5 : " + marks[4] + "/5 \n"
                               + "Question 6 : " + marks[5] + "/5 \n"
                               + "Question 7 : " + marks[6] + "/5 \n"
                               + "Question 8 : " + marks[7] + "/5 \n"
                               + "Question 9 : " + marks[8] + "/5 \n"
                               + "Question 10 : " + marks[9] + "/5 \n \n"
                               + "Total: \n" 
                               + "Final grade : " + grade;
            
            try {
                writeFile(fileContent, fileName);
            } catch (IOException ex) {
                Logger.getLogger(Summary.class.getName()).log(Level.SEVERE, null, ex);
            }
            feedbackText.setForeground(goodGreen);
            feedbackText.setText("File saved successfully");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitActionPerformed
        QuestionSelect QS = new QuestionSelect(testName, testLength, qTextArray, trueAnswers, falseAnswers, marks, completedQs, outCurrentPoints);
        QS.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_QuitActionPerformed

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
            java.util.logging.Logger.getLogger(Summary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Summary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Summary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Summary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Summary(testName, testLength, qTextArray, trueAnswers, falseAnswers, marks).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameLabel;
    private javax.swing.JButton Quit;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JLabel feedbackText;
    private javax.swing.JTextField fileDestTextField;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JLabel gradeDisp;
    private javax.swing.JPanel gradePanel;
    private javax.swing.JLabel gradeTitle;
    private javax.swing.JPanel marksPanel;
    private javax.swing.JLabel marksQ1;
    private javax.swing.JLabel marksQ10;
    private javax.swing.JLabel marksQ2;
    private javax.swing.JLabel marksQ3;
    private javax.swing.JLabel marksQ4;
    private javax.swing.JLabel marksQ5;
    private javax.swing.JLabel marksQ6;
    private javax.swing.JLabel marksQ7;
    private javax.swing.JLabel marksQ8;
    private javax.swing.JLabel marksQ9;
    private javax.swing.JLabel marksTitle;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel question1;
    private javax.swing.JLabel question10;
    private javax.swing.JLabel question2;
    private javax.swing.JLabel question3;
    private javax.swing.JLabel question4;
    private javax.swing.JLabel question5;
    private javax.swing.JLabel question6;
    private javax.swing.JLabel question7;
    private javax.swing.JLabel question8;
    private javax.swing.JLabel question9;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel savePanel;
    private javax.swing.JLabel saveTestLabel;
    // End of variables declaration//GEN-END:variables
}
