/*
 * Parsons Problems Question UI V0.3
*/
package parsonsProblems;

import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author UP815458
 */
public class QuestionSelect extends javax.swing.JFrame {
    
    static String testName;
    static int testLength;
    static int[] marks;
    static String[] qTextArray;
    static String[][] trueAnswers;
    static String[][] falseAnswers;
    
    // The boolean array qCompleted will be used to say whether or not the question in that position of the array has been a completed
    boolean[] completedQs;
    // Stores the current points for each of the questions, to stop an exploit of moving to another question to reset the number of points earned
    int[] currentPoints;
    
    /**
     * Creates new form QuestionSelect
     */
    //will only be run if the inbuilt test has been selected
    public QuestionSelect() {
        testName = "Inbuilt";
        initComponents();
        testLength = 10;
        qTextArray = initInbuiltQText();
        trueAnswers = initInbuiltTrueList();
        falseAnswers = initInbuiltFalseList();
        marks = new int[]{0,0,0,0,0,0,0,0,0,0};
        
        completedQs = initBoolList();
        currentPoints = new int[] {5,5,5,5,5,5,5,5,5,5};
        //hides the testcompleted label and the "finish" button
        testCompleteLabel.setVisible(false);
        finishButton.setVisible(false);
    }
    
    // This will be run if the user either returns to the question select screen from answering a question, or
    // if the user has loaded in a premade test

    /**
     * @param inTestName The name of the test
     * @param inLength
     * @param inQTextArray The Question text for each of the questions for the test
     * @param inTrueAnswers
     * @param inFalseAnswers
     * @param updatedMarks The updated marks given to the Question Select UI by the question UI once the user quits the question
     * @param updatedBoolList The updated Boolean list that is used to display which of the questions has been completed
     * @param inCurrentPoints
    */
    public QuestionSelect(String inTestName, int inLength, String[] inQTextArray, String[][] inTrueAnswers, String[][] inFalseAnswers, int[] updatedMarks, boolean[] updatedBoolList, int[] inCurrentPoints) {
        initComponents();
        testName = inTestName;
        currentTopicDisp.setText(testName);
        testLength = inLength;
        setVisibleQuestions();
        qTextArray = inQTextArray;
        trueAnswers = inTrueAnswers;
        falseAnswers = inFalseAnswers;
        marks = updatedMarks;
        currentPoints = inCurrentPoints;
        completedQs = updatedBoolList;
        setCompletedLabels();
        setScoreLabels();
        
        //adds up the marks for the total marks of the test
        int i = 0;
        int marksTotal = 0;
        while(i < marks.length){
            marksTotal = marksTotal + marks[i];
            i++;
        }
        totalMarksDisp.setText(marksTotal + "/50");
    }

    //generic, will be used in the case of using the inbuilt test
    private static boolean[] initBoolList(){
        boolean[] boolTemp = new boolean[]{false, false, false,false,false,false,false,false,false, false};
        
        return boolTemp;
    }
    
    private void setScoreLabels(){
        scoreLabel1.setText(marks[0] + "/5");
        scoreLabel2.setText(marks[1] + "/5");
        scoreLabel3.setText(marks[2] + "/5");
        scoreLabel4.setText(marks[3] + "/5");
        scoreLabel5.setText(marks[4] + "/5");
        scoreLabel6.setText(marks[5] + "/5");
        scoreLabel7.setText(marks[6] + "/5");
        scoreLabel8.setText(marks[7] + "/5");
        scoreLabel9.setText(marks[8] + "/5");
        scoreLabel10.setText(marks[9] + "/5");
    }
    
    private void setVisibleQuestions(){
        if(testLength < 10){
            questionPanel2.setVisible(false);
            questionPanel3.setVisible(false);
            questionPanel4.setVisible(false);
            questionPanel5.setVisible(false);
            questionPanel6.setVisible(false);
            questionPanel7.setVisible(false);
            questionPanel8.setVisible(false);
            questionPanel9.setVisible(false);
            questionPanel10.setVisible(false);
            if(testLength >= 2){
                questionPanel2.setVisible(true);
            }
            if(testLength >= 3){
                questionPanel3.setVisible(true);
            }
            if(testLength >= 4){
                questionPanel4.setVisible(true);
            }
            if(testLength >= 5){
                questionPanel5.setVisible(true);
            }
            if(testLength >= 6){
                questionPanel6.setVisible(true);
            }
            if(testLength >= 7){
                questionPanel7.setVisible(true);
            }
            if(testLength >= 8){
                questionPanel8.setVisible(true);
            }
            if(testLength >= 9){
                questionPanel9.setVisible(true);
            }
        }
    }
    
    // if a question has already been completed, remove the button and tell the user it has been completed
    private void setCompletedLabels(){
        if(completedQs[0] == true){
            greenCompText1.setText("Question Completed");
            startQuestion1.setVisible(false);
        }
        if(completedQs[1] == true){
            greenCompText2.setText("Question Completed");
            startQuestion2.setVisible(false);
        }
        if(completedQs[2] == true){
            greenCompText3.setText("Question Completed");
            startQuestion3.setVisible(false);
        }
        if(completedQs[3] == true){
            greenCompText4.setText("Question Completed");
            startQuestion4.setVisible(false);
        }
        if(completedQs[4] == true){
            greenCompText5.setText("Question Completed");
            startQuestion5.setVisible(false);
        }
        if(completedQs[5] == true){
            greenCompText6.setText("Question Completed");
            startQuestion6.setVisible(false);
        }
        if(completedQs[6] == true){
            greenCompText7.setText("Question Completed");
            startQuestion7.setVisible(false);
        }
        if(completedQs[7] == true){
            greenCompText8.setText("Question Completed");
            startQuestion8.setVisible(false);
        }
        if(completedQs[8] == true){
            greenCompText9.setText("Question Completed");
            startQuestion9.setVisible(false);
        }
        if(completedQs[9] == true){
            greenCompText10.setText("Question Completed");
            startQuestion10.setVisible(false);
        }
        
        //Checks if all questions have been done before showing the finish test button and test completed label
        boolean allQsDone = true;
        for(int j = 0; j < marks.length; j++){
            if(completedQs[j] == false){
                allQsDone = false;
            }
        }
        if(allQsDone == false){
            testCompleteLabel.setVisible(false);
            finishButton.setVisible(false);
        }
    }
    
    private void writeSaveFile(String inContent) throws IOException{
        FileWriter writer = new FileWriter("TESTDATA" + File.separator +"Sessions" + File.separator + "Save--" + testName);
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

        titleLabel = new javax.swing.JLabel();
        questionPanel = new javax.swing.JPanel();
        currentTopicLabel = new javax.swing.JLabel();
        currentTopicDisp = new javax.swing.JLabel();
        questionPanel1 = new javax.swing.JPanel();
        startQuestion1 = new javax.swing.JButton();
        questionLabel1 = new javax.swing.JLabel();
        marksLabel1 = new javax.swing.JLabel();
        scoreLabel1 = new javax.swing.JLabel();
        greenCompText1 = new javax.swing.JLabel();
        questionPanel2 = new javax.swing.JPanel();
        startQuestion2 = new javax.swing.JButton();
        questionLabel2 = new javax.swing.JLabel();
        marksLabel2 = new javax.swing.JLabel();
        scoreLabel2 = new javax.swing.JLabel();
        greenCompText2 = new javax.swing.JLabel();
        questionPanel3 = new javax.swing.JPanel();
        startQuestion3 = new javax.swing.JButton();
        questionLabel3 = new javax.swing.JLabel();
        marksLabel3 = new javax.swing.JLabel();
        scoreLabel3 = new javax.swing.JLabel();
        greenCompText3 = new javax.swing.JLabel();
        questionPanel4 = new javax.swing.JPanel();
        startQuestion4 = new javax.swing.JButton();
        questionLabel4 = new javax.swing.JLabel();
        marksLabel4 = new javax.swing.JLabel();
        scoreLabel4 = new javax.swing.JLabel();
        greenCompText4 = new javax.swing.JLabel();
        questionPanel5 = new javax.swing.JPanel();
        startQuestion5 = new javax.swing.JButton();
        questionLabel5 = new javax.swing.JLabel();
        marksLabel5 = new javax.swing.JLabel();
        scoreLabel5 = new javax.swing.JLabel();
        greenCompText5 = new javax.swing.JLabel();
        questionPanel6 = new javax.swing.JPanel();
        startQuestion6 = new javax.swing.JButton();
        questionLabel6 = new javax.swing.JLabel();
        marksLabel6 = new javax.swing.JLabel();
        scoreLabel6 = new javax.swing.JLabel();
        greenCompText6 = new javax.swing.JLabel();
        questionPanel7 = new javax.swing.JPanel();
        startQuestion7 = new javax.swing.JButton();
        questionLabel7 = new javax.swing.JLabel();
        marksLabel7 = new javax.swing.JLabel();
        scoreLabel7 = new javax.swing.JLabel();
        greenCompText7 = new javax.swing.JLabel();
        questionPanel8 = new javax.swing.JPanel();
        startQuestion8 = new javax.swing.JButton();
        questionLabel8 = new javax.swing.JLabel();
        marksLabel8 = new javax.swing.JLabel();
        scoreLabel8 = new javax.swing.JLabel();
        greenCompText8 = new javax.swing.JLabel();
        questionPanel9 = new javax.swing.JPanel();
        startQuestion9 = new javax.swing.JButton();
        questionLabel9 = new javax.swing.JLabel();
        marksLabel9 = new javax.swing.JLabel();
        scoreLabel9 = new javax.swing.JLabel();
        greenCompText9 = new javax.swing.JLabel();
        questionPanel10 = new javax.swing.JPanel();
        startQuestion10 = new javax.swing.JButton();
        questionLabel10 = new javax.swing.JLabel();
        marksLabel10 = new javax.swing.JLabel();
        scoreLabel10 = new javax.swing.JLabel();
        greenCompText10 = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();
        testCompleteLabel = new javax.swing.JLabel();
        saveAndQuitButton = new javax.swing.JButton();
        TotalMarksLabel = new javax.swing.JLabel();
        totalMarksDisp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titleLabel.setText("Question Select");

        currentTopicLabel.setText("Current topic:");

        currentTopicDisp.setText("Inbuilt");

        questionPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion1.setText("Start >>");
        startQuestion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion1ActionPerformed(evt);
            }
        });

        questionLabel1.setText("Question 1");

        marksLabel1.setText("Marks :");

        scoreLabel1.setText("0/5");

        greenCompText1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText1.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel1Layout = new javax.swing.GroupLayout(questionPanel1);
        questionPanel1.setLayout(questionPanel1Layout);
        questionPanel1Layout.setHorizontalGroup(
            questionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(greenCompText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel1)
                .addGap(18, 18, 18)
                .addComponent(startQuestion1)
                .addContainerGap())
        );
        questionPanel1Layout.setVerticalGroup(
            questionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion1)
                    .addComponent(questionLabel1)
                    .addComponent(marksLabel1)
                    .addComponent(scoreLabel1)
                    .addComponent(greenCompText1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion2.setText("Start >>");
        startQuestion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion2ActionPerformed(evt);
            }
        });

        questionLabel2.setText("Question 2");

        marksLabel2.setText("Marks :");

        scoreLabel2.setText("0/5");

        greenCompText2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText2.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel2Layout = new javax.swing.GroupLayout(questionPanel2);
        questionPanel2.setLayout(questionPanel2Layout);
        questionPanel2Layout.setHorizontalGroup(
            questionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel2)
                .addGap(18, 18, 18)
                .addComponent(startQuestion2)
                .addContainerGap())
        );
        questionPanel2Layout.setVerticalGroup(
            questionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion2)
                    .addComponent(questionLabel2)
                    .addComponent(marksLabel2)
                    .addComponent(scoreLabel2)
                    .addComponent(greenCompText2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion3.setText("Start >>");
        startQuestion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion3ActionPerformed(evt);
            }
        });

        questionLabel3.setText("Question 3");

        marksLabel3.setText("Marks :");

        scoreLabel3.setText("0/5");

        greenCompText3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText3.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel3Layout = new javax.swing.GroupLayout(questionPanel3);
        questionPanel3.setLayout(questionPanel3Layout);
        questionPanel3Layout.setHorizontalGroup(
            questionPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel3)
                .addGap(18, 18, 18)
                .addComponent(startQuestion3)
                .addContainerGap())
        );
        questionPanel3Layout.setVerticalGroup(
            questionPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion3)
                    .addComponent(questionLabel3)
                    .addComponent(marksLabel3)
                    .addComponent(scoreLabel3)
                    .addComponent(greenCompText3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion4.setText("Start >>");
        startQuestion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion4ActionPerformed(evt);
            }
        });

        questionLabel4.setText("Question 4");

        marksLabel4.setText("Marks :");

        scoreLabel4.setText("0/5");

        greenCompText4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText4.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel4Layout = new javax.swing.GroupLayout(questionPanel4);
        questionPanel4.setLayout(questionPanel4Layout);
        questionPanel4Layout.setHorizontalGroup(
            questionPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel4)
                .addGap(18, 18, 18)
                .addComponent(startQuestion4)
                .addContainerGap())
        );
        questionPanel4Layout.setVerticalGroup(
            questionPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion4)
                    .addComponent(questionLabel4)
                    .addComponent(marksLabel4)
                    .addComponent(scoreLabel4)
                    .addComponent(greenCompText4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion5.setText("Start >>");
        startQuestion5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion5ActionPerformed(evt);
            }
        });

        questionLabel5.setText("Question 5");

        marksLabel5.setText("Marks :");

        scoreLabel5.setText("0/5");

        greenCompText5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText5.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel5Layout = new javax.swing.GroupLayout(questionPanel5);
        questionPanel5.setLayout(questionPanel5Layout);
        questionPanel5Layout.setHorizontalGroup(
            questionPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel5)
                .addGap(18, 18, 18)
                .addComponent(startQuestion5)
                .addContainerGap())
        );
        questionPanel5Layout.setVerticalGroup(
            questionPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion5)
                    .addComponent(questionLabel5)
                    .addComponent(marksLabel5)
                    .addComponent(scoreLabel5)
                    .addComponent(greenCompText5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion6.setText("Start >>");
        startQuestion6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion6ActionPerformed(evt);
            }
        });

        questionLabel6.setText("Question 6");

        marksLabel6.setText("Marks :");

        scoreLabel6.setText("0/5");

        greenCompText6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText6.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel6Layout = new javax.swing.GroupLayout(questionPanel6);
        questionPanel6.setLayout(questionPanel6Layout);
        questionPanel6Layout.setHorizontalGroup(
            questionPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel6)
                .addGap(18, 18, 18)
                .addComponent(startQuestion6)
                .addContainerGap())
        );
        questionPanel6Layout.setVerticalGroup(
            questionPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion6)
                    .addComponent(questionLabel6)
                    .addComponent(marksLabel6)
                    .addComponent(scoreLabel6)
                    .addComponent(greenCompText6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion7.setText("Start >>");
        startQuestion7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion7ActionPerformed(evt);
            }
        });

        questionLabel7.setText("Question 7");

        marksLabel7.setText("Marks :");

        scoreLabel7.setText("0/5");

        greenCompText7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText7.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel7Layout = new javax.swing.GroupLayout(questionPanel7);
        questionPanel7.setLayout(questionPanel7Layout);
        questionPanel7Layout.setHorizontalGroup(
            questionPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel7)
                .addGap(18, 18, 18)
                .addComponent(startQuestion7)
                .addContainerGap())
        );
        questionPanel7Layout.setVerticalGroup(
            questionPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion7)
                    .addComponent(questionLabel7)
                    .addComponent(marksLabel7)
                    .addComponent(scoreLabel7)
                    .addComponent(greenCompText7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion8.setText("Start >>");
        startQuestion8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion8ActionPerformed(evt);
            }
        });

        questionLabel8.setText("Question 8");

        marksLabel8.setText("Marks :");

        scoreLabel8.setText("0/5");

        greenCompText8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText8.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel8Layout = new javax.swing.GroupLayout(questionPanel8);
        questionPanel8.setLayout(questionPanel8Layout);
        questionPanel8Layout.setHorizontalGroup(
            questionPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel8)
                .addGap(18, 18, 18)
                .addComponent(startQuestion8)
                .addContainerGap())
        );
        questionPanel8Layout.setVerticalGroup(
            questionPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion8)
                    .addComponent(questionLabel8)
                    .addComponent(marksLabel8)
                    .addComponent(scoreLabel8)
                    .addComponent(greenCompText8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion9.setText("Start >>");
        startQuestion9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion9ActionPerformed(evt);
            }
        });

        questionLabel9.setText("Question 9");

        marksLabel9.setText("Marks :");

        scoreLabel9.setText("0/5");

        greenCompText9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText9.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel9Layout = new javax.swing.GroupLayout(questionPanel9);
        questionPanel9.setLayout(questionPanel9Layout);
        questionPanel9Layout.setHorizontalGroup(
            questionPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel9)
                .addGap(18, 18, 18)
                .addComponent(startQuestion9)
                .addContainerGap())
        );
        questionPanel9Layout.setVerticalGroup(
            questionPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion9)
                    .addComponent(questionLabel9)
                    .addComponent(marksLabel9)
                    .addComponent(scoreLabel9)
                    .addComponent(greenCompText9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        questionPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        startQuestion10.setText("Start >>");
        startQuestion10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startQuestion10ActionPerformed(evt);
            }
        });

        questionLabel10.setText("Question 10");

        marksLabel10.setText("Marks :");

        scoreLabel10.setText("0/5");

        greenCompText10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        greenCompText10.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout questionPanel10Layout = new javax.swing.GroupLayout(questionPanel10);
        questionPanel10.setLayout(questionPanel10Layout);
        questionPanel10Layout.setHorizontalGroup(
            questionPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenCompText10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(questionLabel10)
                .addGap(18, 18, 18)
                .addComponent(startQuestion10)
                .addContainerGap())
        );
        questionPanel10Layout.setVerticalGroup(
            questionPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startQuestion10)
                    .addComponent(questionLabel10)
                    .addComponent(marksLabel10)
                    .addComponent(scoreLabel10)
                    .addComponent(greenCompText10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        finishButton.setText("Finish Test");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        testCompleteLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        testCompleteLabel.setForeground(new java.awt.Color(51, 153, 0));
        testCompleteLabel.setText("Test completed!");

        saveAndQuitButton.setText("Save & Quit");
        saveAndQuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndQuitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout questionPanelLayout = new javax.swing.GroupLayout(questionPanel);
        questionPanel.setLayout(questionPanelLayout);
        questionPanelLayout.setHorizontalGroup(
            questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questionPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(questionPanelLayout.createSequentialGroup()
                        .addComponent(currentTopicLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentTopicDisp)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanelLayout.createSequentialGroup()
                        .addComponent(quitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveAndQuitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(testCompleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(finishButton)))
                .addContainerGap())
        );
        questionPanelLayout.setVerticalGroup(
            questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentTopicLabel)
                    .addComponent(currentTopicDisp))
                .addGap(8, 8, 8)
                .addComponent(questionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitButton)
                    .addComponent(finishButton)
                    .addComponent(testCompleteLabel)
                    .addComponent(saveAndQuitButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TotalMarksLabel.setText("Total Marks : ");

        totalMarksDisp.setText("0/50");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(30, 30, 30)
                        .addComponent(TotalMarksLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalMarksDisp)
                        .addGap(0, 118, Short.MAX_VALUE))
                    .addComponent(questionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(TotalMarksLabel)
                    .addComponent(totalMarksDisp))
                .addGap(18, 18, 18)
                .addComponent(questionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("static-access")
    private void startQuestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion1ActionPerformed
        if(completedQs[0] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 0, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion1ActionPerformed
    //Quit Button
    @SuppressWarnings("static-access")
    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        AreYouSure youSure = new AreYouSure(testName, testLength, qTextArray, trueAnswers, falseAnswers, marks, completedQs, currentPoints);
        youSure.setVisible(true);
        youSure.setDefaultCloseOperation(youSure.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void startQuestion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion2ActionPerformed
        if(completedQs[1] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 1, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion2ActionPerformed

    private void startQuestion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion3ActionPerformed
        if(completedQs[2] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 2, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion3ActionPerformed

    private void startQuestion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion4ActionPerformed
        if(completedQs[3] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 3, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion4ActionPerformed

    private void startQuestion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion5ActionPerformed
        if(completedQs[4] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 4, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion5ActionPerformed

    private void startQuestion6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion6ActionPerformed
        if(completedQs[5] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 5, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion6ActionPerformed

    private void startQuestion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion7ActionPerformed
        if(completedQs[6] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 6, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion7ActionPerformed

    private void startQuestion8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion8ActionPerformed
        if(completedQs[7] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 7, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion8ActionPerformed

    private void startQuestion9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion9ActionPerformed
        if(completedQs[8] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 8, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion9ActionPerformed

    private void startQuestion10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startQuestion10ActionPerformed
        if(completedQs[9] == false){
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, 9, testLength, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            //disable the close (x) button for the question UI
            QUI.setDefaultCloseOperation(QUI.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_startQuestion10ActionPerformed
    //Finish button
    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        Summary Sum = new Summary(testName, testLength, qTextArray, trueAnswers, falseAnswers, marks);
        Sum.setVisible(true);
        Sum.setDefaultCloseOperation(Sum.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_finishButtonActionPerformed

    private void saveAndQuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndQuitButtonActionPerformed
        boolean saveValid = false;
        Date date = java.util.Calendar.getInstance().getTime();
        String descString = "";
        for (int i = 0;i < qTextArray.length;i++) {
            descString = descString + qTextArray[i] + "\n";
        }
        String trueString = "";
        //trueAnswer string creation
        for(int i =0; i < 10;i++){
            for(int j =0; j < trueAnswers[i].length;j++){
                trueString = trueString + trueAnswers[i][j] + "\n";
            }
            trueString = trueString + "";
        }
        //false answer string creation
        String falseString = "";
        for(int i =0; i < 10;i++){
            for(int j =0; j < falseAnswers[i].length;j++){
                falseString = falseString + falseAnswers[i][j] + "\n";
            }
        }
        //list completed questions
        String completedString = "";
        for(int i = 0; i < completedQs.length; i++){
            completedString = completedString + completedQs[i] + "\n";
        }
        //completedString = completedString + "c/\n";
        String marksString = "";
        for(int i = 0; i < marks.length; i++){
            marksString = marksString + marks[i] + "\n";
        }
        String currentPString = "";
        for(int i = 0; i < currentPoints.length; i++){
            currentPString = currentPString + currentPoints[i] + "\n";
        }
        
        String fileContent = "//Test name \n" + "/n/ Name/\n" + testName + "\n" + "//" + testName + " previous session saved at " + date + "\n" + "/l/ Length/\n" + testLength + "\n" +
                              "/d/Descriptions \n" + descString + "/t/ true answers\n" + trueString + "/f/ false answers \n" + falseString + "/c/ completed booleans \n" + completedString + "/m/ current marks \n" + marksString + "/currentPoints/\n" + currentPString;
        try {
            writeSaveFile(fileContent);
            saveValid = true;
            System.out.println("Save successful");
        } catch (IOException ex) {
            Logger.getLogger(QuestionSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(saveValid == true){
            MainMenu mm = new MainMenu();
            mm.setVisible(true);
            mm.setDefaultCloseOperation(mm.DO_NOTHING_ON_CLOSE);
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_saveAndQuitButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Lambda")
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
            java.util.logging.Logger.getLogger(QuestionSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                new QuestionSelect().setVisible(true);
            }
        });
    }
    
    //---------------------------------//
    //   INBUILT TEST INITIALIZATION   //
    //---------------------------------//
    private static String[] initInbuiltQText(){
        String q1Text = "<html> Arrange the code snippets on the right <br/> so that it creates an if statement that returns <br/> true if the condition inside the <br/> paranthesis is met. <br/> <br/> In addition to this, one <br/> of these answers is <br/> false, put this false answer <br/> in the false answer box";
        String q2Text = "<html> Question 2 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q3Text = "<html> Question 3 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q4Text = "<html> Question 4 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q5Text = "<html> Question 5 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q6Text = "<html> Question 6 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q7Text = "<html> Question 7 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q8Text = "<html> Question 8 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q9Text = "<html> Question 9 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
        String q10Text = "<html> Question 10 Order these numbers in order <br/> <br/> one of the answers,<br>is not a number, place this in the false answer box";
                
        String [] qTextTemp = new String[] {q1Text, q2Text, q3Text, q4Text, q5Text, q6Text, q7Text, q8Text, q9Text, q10Text};
        return qTextTemp;
    }
    
    private static String[][] initInbuiltTrueList(){
        String[][] trueTemp = new String[10][10];
        //creates the correct answer list
        //Question 1
        trueTemp[0][0] = "if(2 < 3){";
        trueTemp[0][1] = "   return true";
        trueTemp[0][2] = "}";
        //Question 2
        trueTemp[1][0] = "1";
        trueTemp[1][1] = "2";
        trueTemp[1][2] = "3";
        //Question 3
        trueTemp[2][0] = "1";
        trueTemp[2][1] = "2";
        trueTemp[2][2] = "3";
        //Question 4
        trueTemp[3][0] = "1";
        trueTemp[3][1] = "2";
        trueTemp[3][2] = "3";
        //Question 5
        trueTemp[4][0] = "1";
        trueTemp[4][1] = "2";
        trueTemp[4][2] = "3";
        //Question 6
        trueTemp[5][0] = "1";
        trueTemp[5][1] = "2";
        trueTemp[5][2] = "3";
        //Question 7
        trueTemp[6][0] = "1";
        trueTemp[6][1] = "2";
        trueTemp[6][2] = "3";
        //Question 8
        trueTemp[7][0] = "1";
        trueTemp[7][1] = "2";
        trueTemp[7][2] = "3";
        //Question 9
        trueTemp[8][0] = "1";
        trueTemp[8][1] = "2";
        trueTemp[8][2] = "3";
        //Question 10
        trueTemp[9][0] = "1";
        trueTemp[9][1] = "2";
        trueTemp[9][2] = "3";
        for(int i = 0; i < 10; i++){
            for(int j = 3; j < 10; j++){
                trueTemp[i][j] = "INVALID";
            }
        }
        return trueTemp;
    }
    
    private static String[][] initInbuiltFalseList(){
        //Now a hard limit of 6 has been introduced for false answers
        //note: maybe if theres less than 6, for loop the rest, putting random answers in the rest of the slots
        String[][] falseTemp = new String[10][10];
        //Question 1
        falseTemp[0][0] = "if (2 > 3){";
        falseTemp[0][1] = "banana";
        falseTemp[0][2] = "Strawberry";
        falseTemp[0][3] = "pineapple";
        falseTemp[0][4] = "Pie";
        falseTemp[0][5] = "Cake";
        //Question 2
        falseTemp[1][0] = "a";
        falseTemp[1][1] = "b";
        falseTemp[1][2] = "c";
        falseTemp[1][3] = "d";
        falseTemp[1][4] = "e";
        falseTemp[1][5] = "f";
        //Question 3
        falseTemp[2][0] = "a";
        falseTemp[2][1] = "b";
        falseTemp[2][2] = "c";
        falseTemp[2][3] = "d";
        falseTemp[2][4] = "e";
        falseTemp[2][5] = "f";
        //Question 4
        falseTemp[3][0] = "a";
        falseTemp[3][1] = "b";
        falseTemp[3][2] = "c";
        falseTemp[3][3] = "d";
        falseTemp[3][4] = "e";
        falseTemp[3][5] = "f";
        //Question 5
        falseTemp[4][0] = "a";
        falseTemp[4][1] = "b";
        falseTemp[4][2] = "c";
        falseTemp[4][3] = "d";
        falseTemp[4][4] = "e";
        falseTemp[4][5] = "f";
        //Question 6
        falseTemp[5][0] = "a";
        falseTemp[5][1] = "b";
        falseTemp[5][2] = "c";
        falseTemp[5][3] = "d";
        falseTemp[5][4] = "e";
        falseTemp[5][5] = "f";
        //Question 7
        falseTemp[6][0] = "a";
        falseTemp[6][1] = "n";
        falseTemp[6][2] = "m";
        falseTemp[6][3] = "k";
        falseTemp[6][4] = "j";
        falseTemp[6][5] = "p";
        //Question 8
        falseTemp[7][0] = "o";
        falseTemp[7][1] = "p";
        falseTemp[7][2] = "y";
        falseTemp[7][3] = "t";
        falseTemp[7][4] = "r";
        falseTemp[7][5] = "e";
        //Question 9
        falseTemp[8][0] = "w";
        falseTemp[8][1] = "q";
        falseTemp[8][2] = "w";
        falseTemp[8][3] = "e";
        falseTemp[8][4] = "r";
        falseTemp[8][5] = "r";
        //Question 10
        falseTemp[9][0] = "t";
        falseTemp[9][1] = "y";
        falseTemp[9][2] = "u";
        falseTemp[9][3] = "u";
        falseTemp[9][4] = "i";
        falseTemp[9][5] = "o";
        for(int i = 0; i < 10; i++){
            for(int j = 6; j < 10; j++){
                falseTemp[i][j] = "INVALID";
            }
        }
        return falseTemp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalMarksLabel;
    private javax.swing.JLabel currentTopicDisp;
    private javax.swing.JLabel currentTopicLabel;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel greenCompText1;
    private javax.swing.JLabel greenCompText10;
    private javax.swing.JLabel greenCompText2;
    private javax.swing.JLabel greenCompText3;
    private javax.swing.JLabel greenCompText4;
    private javax.swing.JLabel greenCompText5;
    private javax.swing.JLabel greenCompText6;
    private javax.swing.JLabel greenCompText7;
    private javax.swing.JLabel greenCompText8;
    private javax.swing.JLabel greenCompText9;
    private javax.swing.JLabel marksLabel1;
    private javax.swing.JLabel marksLabel10;
    private javax.swing.JLabel marksLabel2;
    private javax.swing.JLabel marksLabel3;
    private javax.swing.JLabel marksLabel4;
    private javax.swing.JLabel marksLabel5;
    private javax.swing.JLabel marksLabel6;
    private javax.swing.JLabel marksLabel7;
    private javax.swing.JLabel marksLabel8;
    private javax.swing.JLabel marksLabel9;
    private javax.swing.JLabel questionLabel1;
    private javax.swing.JLabel questionLabel10;
    private javax.swing.JLabel questionLabel2;
    private javax.swing.JLabel questionLabel3;
    private javax.swing.JLabel questionLabel4;
    private javax.swing.JLabel questionLabel5;
    private javax.swing.JLabel questionLabel6;
    private javax.swing.JLabel questionLabel7;
    private javax.swing.JLabel questionLabel8;
    private javax.swing.JLabel questionLabel9;
    private javax.swing.JPanel questionPanel;
    private javax.swing.JPanel questionPanel1;
    private javax.swing.JPanel questionPanel10;
    private javax.swing.JPanel questionPanel2;
    private javax.swing.JPanel questionPanel3;
    private javax.swing.JPanel questionPanel4;
    private javax.swing.JPanel questionPanel5;
    private javax.swing.JPanel questionPanel6;
    private javax.swing.JPanel questionPanel7;
    private javax.swing.JPanel questionPanel8;
    private javax.swing.JPanel questionPanel9;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton saveAndQuitButton;
    private javax.swing.JLabel scoreLabel1;
    private javax.swing.JLabel scoreLabel10;
    private javax.swing.JLabel scoreLabel2;
    private javax.swing.JLabel scoreLabel3;
    private javax.swing.JLabel scoreLabel4;
    private javax.swing.JLabel scoreLabel5;
    private javax.swing.JLabel scoreLabel6;
    private javax.swing.JLabel scoreLabel7;
    private javax.swing.JLabel scoreLabel8;
    private javax.swing.JLabel scoreLabel9;
    private javax.swing.JButton startQuestion1;
    private javax.swing.JButton startQuestion10;
    private javax.swing.JButton startQuestion2;
    private javax.swing.JButton startQuestion3;
    private javax.swing.JButton startQuestion4;
    private javax.swing.JButton startQuestion5;
    private javax.swing.JButton startQuestion6;
    private javax.swing.JButton startQuestion7;
    private javax.swing.JButton startQuestion8;
    private javax.swing.JButton startQuestion9;
    private javax.swing.JLabel testCompleteLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalMarksDisp;
    // End of variables declaration//GEN-END:variables
}
