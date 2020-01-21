/*
 * Main Question UI
 * Parsons Problems V0.3
 */
package parsonsproblems;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;


/**
 * @author UP815458
 */
public class ParsonsProblemsQUI extends javax.swing.JFrame {
    static String testName;
    static int qNo;
    static String[] qTextArray;
    static String[][] trueAnswers;
    static String[][] falseAnswers;
    static int[] marks;
    static boolean[] completedQs;
    static int[] currentPoints;
    boolean isCorrect = false;
    
    /**
     * Initialises the components and sets up the question
     * @param qNumber This indicates what question is being answered, and is used in all array references
     * @param qTxtList An Array of string containing the Question descriptions
     * @param inMarks 
     * @param trueAnswerList
     * @param falseAnswerList
     * @param completedQuestions
     */
    public ParsonsProblemsQUI(String inTestName, int qNumber, String[] qTxtList, int[] inMarks, String[][] trueAnswerList, String[][] falseAnswerList, boolean[] completedQuestions, int[] inCurrentPoints) {
        testName = inTestName;
        qNo = qNumber;
        qTextArray = qTxtList;
        marks = inMarks;
        trueAnswers = trueAnswerList;
        falseAnswers = falseAnswerList;
        completedQs = completedQuestions;
        if(completedQs[qNo] == true){
            isCorrect = true;
        }
        currentPoints = inCurrentPoints;
        initAnswers();
        initComponents();
        initQuestionDetails();
        // Set the text to be blank on startup
        feedbackText.setText("");
        //tells the user if points cannot be earned on startup
        System.out.println(currentPoints[qNo]);
        if(currentPoints[qNo] == 0){
            feedbackText.setText("<html>X <br/> Points can no longer <br/> be earned from this <br/> question");
        }
        setTextFields();
        //remove the "previous" button on question 1
        if(qNo == 0){
            previousButton.setVisible(false);
        }
        if(qNo == 9){
            nextButton.setText("Finish");
        }
    }
    
    private void initQuestionDetails(){
        int dispNo = qNo + 1;
        // Takes the length of qTextArray to determine the total no. of questions (redundancy for if max questions increased)
        int totalNoOfQuestions = qTextArray.length;
        qNoDisp.setText("Question " + dispNo + "/" + totalNoOfQuestions);
        qTextDisp.setText(qTextArray[qNo]);
    }
    
    //used to set text fields when buttons are pressed and on window start
    private void setTextFields(){
        posOneText.setText(randomAnswerArray[0]);
        posTwoText.setText(randomAnswerArray[1]);
        posThreeText.setText(randomAnswerArray[2]);
        posFourText.setText(randomAnswerArray[3]);
        posFiveText.setText(randomAnswerArray[4]);
        posSixText.setText(randomAnswerArray[5]);
        posSevenText.setText(randomAnswerArray[6]);
        posEightText.setText(randomAnswerArray[7]);
        marksScore.setText(marks[qNo]+"/5");
        totPointsAvailable.setText("Total Points Available : " + currentPoints[qNo] + "/5");
        setTotalMarksLabel();
    }
    
    private void setTotalMarksLabel(){
        int markTemp = 0;
        for(int u = 0; u < 10; u++){
            markTemp = markTemp + marks[u];
        }
        totalMarksScore.setText(markTemp + "/50");
    }
    
    //-----------------------------//
    //  BUTTON REARRANGEMENT CODE  //
    //-----------------------------//
        
    private void moveTextDown(int i){
        //takes the text from the inputTextField position (1. being 0, down to false being inputted as 3)
        //and shifts the text down a position, while bring the text previously there up
        String temp;
        
        temp = randomAnswerArray[i];
        randomAnswerArray[i] = randomAnswerArray[i+1];
        randomAnswerArray[i+1] = temp;
        setTextFields();
    }
    
    private void moveTextUp(int i){
        String temp;
        
        temp = randomAnswerArray[i-1];
        randomAnswerArray[i-1] = randomAnswerArray[i];
        randomAnswerArray[i] = temp;
        setTextFields();
    }
    
    //------------------------------//
    //  ANSWER RANDOMISATION CODE   //
    //------------------------------//
    
    String[] trueAnswerArray;
    String[] randomAnswerArray;
    int arrayLength = 8;                                         //
    String[] falseAnswersAlreadyInArray = new String[arrayLength - 5];
    
    private void initAnswers(){
        String falseAnswer1 = getRandomFalseAnswer(falseAnswers[qNo], falseAnswersAlreadyInArray);
        falseAnswersAlreadyInArray[0] = falseAnswer1;
        //METHOD BELOW IS A WORKAROUND FOR REQUIRING A RETURN STATEMENT IN "getRandomFalseAnswer" METHOD
        String falseAnswer2 = "INVALID";
        while("INVALID".equals(falseAnswer2)){
            falseAnswer2 = getRandomFalseAnswer(falseAnswers[qNo], falseAnswersAlreadyInArray);
        }
        falseAnswersAlreadyInArray[1] = falseAnswer2;
        String falseAnswer3 = "INVALID";
        while("INVALID".equals(falseAnswer3)){
            falseAnswer3 = getRandomFalseAnswer(falseAnswers[qNo], falseAnswersAlreadyInArray);
        }
        falseAnswersAlreadyInArray[2] = falseAnswer3;
        
        String falseAnswer4 = "INVALID";
        while("INVALID".equals(falseAnswer4)){
            falseAnswer4 = getRandomFalseAnswer(falseAnswers[qNo], falseAnswersAlreadyInArray);
        }
        //the true answer array has the true order of the answer stored
        trueAnswerArray = new String[arrayLength];
        for(int i = 0;i < 3; i++){
            trueAnswerArray[i] = trueAnswers[qNo][i];
        }
        trueAnswerArray[3] = falseAnswer1;
        trueAnswerArray[4] = falseAnswer2;
        trueAnswerArray[5] = falseAnswer3;
        trueAnswerArray[6] = falseAnswer4;
        
        randomAnswerArray = new String[arrayLength];
        randomAnswerArray[0] = "--END OF PROGRAM--";
        String [] randomAnswerArrayTemp = createRandomAnswerArray();
        for(int i = 1; i < 8; i++){
            randomAnswerArray[i] = randomAnswerArrayTemp[i-1];
        }
        boolean arrayValid = false;
        while(arrayValid == false){
            if(randomAnswerArray[0].equals(trueAnswerArray[0])){
                randomAnswerArray = createRandomAnswerArray();
            }
            else{
                arrayValid = true;
            }
        }
    }
    
        // Finds out how long the input list is, and gives a random false answer from the provided list
    /*
     * @param inAlreadyList contains the false answers already selected answers
    */
    private static String getRandomFalseAnswer(String[] inFalseAnsList, String[] inAlreadyList){
        //count the length of inFalseAnsList
        int length = inFalseAnsList.length;
        //picks a random number out of the list and put that into string and return
        int randomNumber = (int)(Math.random() * length);
        String falseAnswer = inFalseAnsList[randomNumber];
        for (int i = 0 ; i < inAlreadyList.length; i++) {
            if (falseAnswer.equals(inAlreadyList[i])) {
                //WORKAROUND FOR REQUIRING A RETURN STATEMENT
                falseAnswer = "INVALID";
            }
        }
        return falseAnswer;
    }
    
    // This functions creates a random answer array to initially display in the text boxes
    // source for shuffling the array using collections.shuffle:  https://www.geeksforgeeks.org/collections-shuffle-java-examples/
    private String[] createRandomAnswerArray(){
        String[] randomTempArray = new String[arrayLength];
        // copies trueAnswerArray to the random temp array
        // since collections.shuffle does not use String[] arrays, an array list is made instead
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < trueAnswerArray.length; i++){
            temp.add(trueAnswerArray[i]);
        }
        // randomise the array, and give the random answer array all the elements
        // of the temp arrayList
        Collections.shuffle(temp);
        for(int z = 0;z < randomTempArray.length;z++){
            randomTempArray[z] = temp.get(z);
        }
        
        return randomTempArray;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        QuestionInfoPanel = new javax.swing.JPanel();
        qNoDisp = new javax.swing.JLabel();
        qTextDisp = new javax.swing.JLabel();
        MarkingPanel = new javax.swing.JPanel();
        marksLabel = new javax.swing.JLabel();
        marksScore = new javax.swing.JLabel();
        increaseMarkDisp = new javax.swing.JLabel();
        totalMarksScore = new javax.swing.JLabel();
        totalMarksLabel = new javax.swing.JLabel();
        navPanel = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        feedbackText = new javax.swing.JLabel();
        AnswerPanel = new javax.swing.JPanel();
        posOneText = new javax.swing.JLabel();
        moveOneDownPos1 = new javax.swing.JButton();
        posTwoText = new javax.swing.JLabel();
        moveOneUpPos2 = new javax.swing.JButton();
        moveOneDownPos2 = new javax.swing.JButton();
        posThreeText = new javax.swing.JLabel();
        moveOneUpPos3 = new javax.swing.JButton();
        moveOneDownPos3 = new javax.swing.JButton();
        posFourText = new javax.swing.JLabel();
        moveOneUpPos4 = new javax.swing.JButton();
        moveOneDownPos4 = new javax.swing.JButton();
        moveOneUpPos5 = new javax.swing.JButton();
        moveOneDownPos5 = new javax.swing.JButton();
        posSixText = new javax.swing.JLabel();
        moveOneUpPos6 = new javax.swing.JButton();
        moveOneDownPos6 = new javax.swing.JButton();
        posSevenText = new javax.swing.JLabel();
        moveOneUpPos7 = new javax.swing.JButton();
        moveOneDownPos7 = new javax.swing.JButton();
        posEightText = new javax.swing.JLabel();
        ButtonPanel = new javax.swing.JPanel();
        submitAnswerButton = new javax.swing.JButton();
        totPointsAvailable = new javax.swing.JLabel();
        moveOneUpPos8 = new javax.swing.JButton();
        posFiveText = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Question UI");

        QuestionInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        qNoDisp.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        qNoDisp.setText("Question No.");

        qTextDisp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        qTextDisp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        qTextDisp.setText("Description");

        MarkingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MarkingPanel.setPreferredSize(new java.awt.Dimension(76, 50));

        marksLabel.setText("Marks:");

        marksScore.setText("0/5\n");

        increaseMarkDisp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        increaseMarkDisp.setForeground(new java.awt.Color(51, 204, 0));

        totalMarksScore.setText("0/50 ");

        totalMarksLabel.setText("Total:");

        nextButton.setText("Next >>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        previousButton.setText("<< Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navPanelLayout = new javax.swing.GroupLayout(navPanel);
        navPanel.setLayout(navPanelLayout);
        navPanelLayout.setHorizontalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        navPanelLayout.setVerticalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previousButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout MarkingPanelLayout = new javax.swing.GroupLayout(MarkingPanel);
        MarkingPanel.setLayout(MarkingPanelLayout);
        MarkingPanelLayout.setHorizontalGroup(
            MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MarkingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalMarksLabel)
                    .addComponent(marksLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marksScore)
                    .addComponent(totalMarksScore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseMarkDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MarkingPanelLayout.setVerticalGroup(
            MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MarkingPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(increaseMarkDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MarkingPanelLayout.createSequentialGroup()
                        .addGroup(MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(marksLabel)
                            .addComponent(marksScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(totalMarksScore, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalMarksLabel, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(15, 15, 15))
            .addGroup(MarkingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        feedbackText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        feedbackText.setForeground(new java.awt.Color(255, 0, 51));
        feedbackText.setText("Feedback appears here");

        javax.swing.GroupLayout QuestionInfoPanelLayout = new javax.swing.GroupLayout(QuestionInfoPanel);
        QuestionInfoPanel.setLayout(QuestionInfoPanelLayout);
        QuestionInfoPanelLayout.setHorizontalGroup(
            QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestionInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MarkingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(QuestionInfoPanelLayout.createSequentialGroup()
                        .addGroup(QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qTextDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(feedbackText, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qNoDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 62, Short.MAX_VALUE)))
                .addContainerGap())
        );
        QuestionInfoPanelLayout.setVerticalGroup(
            QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestionInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qNoDisp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qTextDisp, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(feedbackText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MarkingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        qTextDisp.getAccessibleContext().setAccessibleName("Question 1:\nArrange the code snippets so that it creates an if statement that returns true if the ");

        AnswerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        posOneText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posOneText.setText("1.");

        moveOneDownPos1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos1.setText("▼");
        moveOneDownPos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos1ActionPerformed(evt);
            }
        });

        posTwoText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posTwoText.setText("2.");

        moveOneUpPos2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos2.setText("▲");
        moveOneUpPos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos2ActionPerformed(evt);
            }
        });

        moveOneDownPos2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos2.setText("▼");
        moveOneDownPos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos2ActionPerformed(evt);
            }
        });

        posThreeText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posThreeText.setText("3.");

        moveOneUpPos3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos3.setText("▲");
        moveOneUpPos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos3ActionPerformed(evt);
            }
        });

        moveOneDownPos3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos3.setText("▼");
        moveOneDownPos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos3ActionPerformed(evt);
            }
        });

        posFourText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posFourText.setText("4.");

        moveOneUpPos4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos4.setText("▲");
        moveOneUpPos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos4ActionPerformed(evt);
            }
        });

        moveOneDownPos4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos4.setText("▼");
        moveOneDownPos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos4ActionPerformed(evt);
            }
        });

        moveOneUpPos5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos5.setText("▲");
        moveOneUpPos5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos5ActionPerformed(evt);
            }
        });

        moveOneDownPos5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos5.setText("▼");
        moveOneDownPos5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos5ActionPerformed(evt);
            }
        });

        posSixText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posSixText.setText("6.");

        moveOneUpPos6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos6.setText("▲");
        moveOneUpPos6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos6ActionPerformed(evt);
            }
        });

        moveOneDownPos6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos6.setText("▼");
        moveOneDownPos6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos6ActionPerformed(evt);
            }
        });

        posSevenText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posSevenText.setText("7.");

        moveOneUpPos7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos7.setText("▲");
        moveOneUpPos7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos7ActionPerformed(evt);
            }
        });

        moveOneDownPos7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos7.setText("▼");
        moveOneDownPos7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos7ActionPerformed(evt);
            }
        });

        posEightText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posEightText.setText("8.");

        ButtonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ButtonPanel.setPreferredSize(new java.awt.Dimension(324, 50));

        submitAnswerButton.setText("Submit Answer");
        submitAnswerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAnswerButtonActionPerformed(evt);
            }
        });

        totPointsAvailable.setText("Total Points Available : 5/5");

        javax.swing.GroupLayout ButtonPanelLayout = new javax.swing.GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totPointsAvailable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitAnswerButton)
                .addContainerGap())
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitAnswerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(totPointsAvailable))
                .addContainerGap())
        );

        moveOneUpPos8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos8.setText("▲");
        moveOneUpPos8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos8ActionPerformed(evt);
            }
        });

        posFiveText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posFiveText.setText("5.");

        javax.swing.GroupLayout AnswerPanelLayout = new javax.swing.GroupLayout(AnswerPanel);
        AnswerPanel.setLayout(AnswerPanelLayout);
        AnswerPanelLayout.setHorizontalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnswerPanelLayout.createSequentialGroup()
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AnswerPanelLayout.createSequentialGroup()
                                .addComponent(posEightText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(moveOneUpPos8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(AnswerPanelLayout.createSequentialGroup()
                                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(posTwoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(posOneText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(posThreeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(posFourText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(posSixText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(posSevenText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(posFiveText, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(moveOneDownPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                            .addComponent(moveOneDownPos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)))
                .addContainerGap())
        );
        AnswerPanelLayout.setVerticalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AnswerPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(posOneText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moveOneDownPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(posTwoText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(moveOneDownPos2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                        .addComponent(moveOneUpPos2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(posThreeText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneUpPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moveOneUpPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(moveOneDownPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(posFourText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveOneUpPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posFiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posSixText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneUpPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posSevenText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneUpPos7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveOneUpPos8, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(posEightText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
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
                        .addComponent(QuestionInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AnswerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(quitButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(QuestionInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        //quits to the question selection menu and save the current progress of the test, but for now just quits the application entirely
        QuestionSelect QS = new QuestionSelect(testName, qTextArray, trueAnswers, falseAnswers, marks, completedQs, currentPoints);
        QS.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed
    
    Color correctGreen = new Color(51,204,0);
    private void submitAnswerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAnswerButtonActionPerformed
        //Clear the feedback messages
        feedbackText.setText("");
        //Checks the answer and provides feedback accordingly (will be the bulk of the main code for this UI eventually)
        if(isCorrect == true){
            feedbackText.setForeground(correctGreen);
            feedbackText.setText("Question Already Completed");
        }
        else{//check the current state of the randomArray against the trueArray
            if(randomAnswerArray[0].equals(trueAnswerArray[1])
            && randomAnswerArray[1].equals(trueAnswerArray[2])
            && randomAnswerArray[2].equals(trueAnswerArray[3])
            && randomAnswerArray[3].equals(trueAnswerArray[0])){
                if(currentPoints[qNo] == 5){
                    feedbackText.setForeground(correctGreen);
                    feedbackText.setText("√ Full marks, well done!");
                }
                else{
                    feedbackText.setForeground(correctGreen);
                    feedbackText.setText("√");
                }
                isCorrect = true;
                marksScore.setText(currentPoints[qNo] + "/5");
                increaseMarkDisp.setText("+ " + currentPoints[qNo]);
                //export mark list code
                marks[qNo] = currentPoints[qNo];
                //edit the total mark list
                setTotalMarksLabel();
                //label question as completed
                completedQs[qNo] = true;
            }
            else if(currentPoints[qNo] != 0){
                feedbackText.setForeground(Color.RED);
                feedbackText.setText("<html>X <br/>1 point deducted");
                currentPoints[qNo]--;
                totPointsAvailable.setText("Total Points Available : " + currentPoints[qNo] + "/5");
                if(currentPoints[qNo] == 0){
                    feedbackText.setText("<html>X <br/> Points can no longer <br/> be earned from this <br/> question");
                }
            }
            else{
                feedbackText.setForeground(Color.RED);
                feedbackText.setText("<html>X <br/> Points can no longer <br/> be earned from this <br/> question");
                totPointsAvailable.setText("Total Points Available : " + currentPoints[qNo] + "/5");
            }
        }
    }//GEN-LAST:event_submitAnswerButtonActionPerformed

    // Movement Buttons
    // down buttons
    private void moveOneDownPos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos1ActionPerformed
        moveTextDown(0);
    }//GEN-LAST:event_moveOneDownPos1ActionPerformed

    private void moveOneDownPos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos2ActionPerformed
        moveTextDown(1);
    }//GEN-LAST:event_moveOneDownPos2ActionPerformed

    private void moveOneDownPos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos3ActionPerformed
        moveTextDown(2);
    }//GEN-LAST:event_moveOneDownPos3ActionPerformed
    
    //Up buttons
    private void moveOneUpPos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos2ActionPerformed
        moveTextUp(1);
    }//GEN-LAST:event_moveOneUpPos2ActionPerformed

    private void moveOneUpPos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos3ActionPerformed
        moveTextUp(2);
    }//GEN-LAST:event_moveOneUpPos3ActionPerformed
    
    //Test navigation buttons
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        //clear the feedback messages
        feedbackText.setText("");
        if(qNo == 9){
            boolean allQsDone = false;
            for(int i = 0; i < 10; i++){
                if(completedQs[i] == true){
                    allQsDone = true;
                }
                if(completedQs[i] == false){
                    allQsDone = false;
                }
            }
            if(allQsDone == false){
                feedbackText.setForeground(Color.RED);
                feedbackText.setText("<html> Not all questions <br/> have been completed");
            }
            else{
                Summary Sum = new Summary(testName, qTextArray, trueAnswers, falseAnswers, marks);
                Sum.setVisible(true);
                Sum.setDefaultCloseOperation(Sum.DO_NOTHING_ON_CLOSE);
                this.setVisible(false);
                this.dispose();
            }
        }
        else{
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, qNo + 1, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
            QUI.setVisible(true);
            this.setVisible(false);
            this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
            this.dispose();
        }
    }//GEN-LAST:event_nextButtonActionPerformed
    //Error shouldn't be triggered by this because the prev button should be invisible on q1
    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, qNo - 1, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints);
        QUI.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_previousButtonActionPerformed

    private void moveOneUpPos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos4ActionPerformed
        moveTextUp(3);
    }//GEN-LAST:event_moveOneUpPos4ActionPerformed

    private void moveOneDownPos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos4ActionPerformed
        moveTextDown(3);
    }//GEN-LAST:event_moveOneDownPos4ActionPerformed

    private void moveOneUpPos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos5ActionPerformed
        moveTextUp(4);
    }//GEN-LAST:event_moveOneUpPos5ActionPerformed

    private void moveOneDownPos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos5ActionPerformed
        moveTextDown(4);
    }//GEN-LAST:event_moveOneDownPos5ActionPerformed

    private void moveOneUpPos6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos6ActionPerformed
        moveTextUp(5);
    }//GEN-LAST:event_moveOneUpPos6ActionPerformed

    private void moveOneDownPos6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos6ActionPerformed
        moveTextDown(5);
    }//GEN-LAST:event_moveOneDownPos6ActionPerformed

    private void moveOneUpPos7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos7ActionPerformed
        moveTextUp(6);
    }//GEN-LAST:event_moveOneUpPos7ActionPerformed

    private void moveOneDownPos7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos7ActionPerformed
        moveTextDown(6);
    }//GEN-LAST:event_moveOneDownPos7ActionPerformed

    private void moveOneUpPos8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos8ActionPerformed
        moveTextUp(7);
    }//GEN-LAST:event_moveOneUpPos8ActionPerformed
    
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
            java.util.logging.Logger.getLogger(ParsonsProblemsQUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParsonsProblemsQUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParsonsProblemsQUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParsonsProblemsQUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParsonsProblemsQUI(testName, qNo, qTextArray, marks, trueAnswers, falseAnswers, completedQs, currentPoints).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AnswerPanel;
    private javax.swing.JPanel ButtonPanel;
    private javax.swing.JPanel MarkingPanel;
    private javax.swing.JPanel QuestionInfoPanel;
    private javax.swing.JLabel feedbackText;
    private javax.swing.JLabel increaseMarkDisp;
    private javax.swing.JLabel marksLabel;
    private javax.swing.JLabel marksScore;
    private javax.swing.JButton moveOneDownPos1;
    private javax.swing.JButton moveOneDownPos2;
    private javax.swing.JButton moveOneDownPos3;
    private javax.swing.JButton moveOneDownPos4;
    private javax.swing.JButton moveOneDownPos5;
    private javax.swing.JButton moveOneDownPos6;
    private javax.swing.JButton moveOneDownPos7;
    private javax.swing.JButton moveOneUpPos2;
    private javax.swing.JButton moveOneUpPos3;
    private javax.swing.JButton moveOneUpPos4;
    private javax.swing.JButton moveOneUpPos5;
    private javax.swing.JButton moveOneUpPos6;
    private javax.swing.JButton moveOneUpPos7;
    private javax.swing.JButton moveOneUpPos8;
    private javax.swing.JPanel navPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel posEightText;
    private javax.swing.JLabel posFiveText;
    private javax.swing.JLabel posFourText;
    private javax.swing.JLabel posOneText;
    private javax.swing.JLabel posSevenText;
    private javax.swing.JLabel posSixText;
    private javax.swing.JLabel posThreeText;
    private javax.swing.JLabel posTwoText;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel qNoDisp;
    private javax.swing.JLabel qTextDisp;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton submitAnswerButton;
    private javax.swing.JLabel totPointsAvailable;
    private javax.swing.JLabel totalMarksLabel;
    private javax.swing.JLabel totalMarksScore;
    // End of variables declaration//GEN-END:variables
}
