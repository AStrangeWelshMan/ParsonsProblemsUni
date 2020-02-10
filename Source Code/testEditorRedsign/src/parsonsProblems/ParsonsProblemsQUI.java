/*
 * Main Question UI
 * Parsons Problems V0.3
 */
package parsonsProblems;

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
    //The true answer 
    static String[][] trueAnswerSet;
    static String[] trueAnswers;
    static String[][] falseAnswerSet;
    static int maxArrayLength;
    static boolean[] activeAnswers;
    static int[] marks;
    static boolean[] completedQs;
    static int[] currentPoints;
    boolean isCorrect = false;
    
    /**
     * Initialises the components and sets up the question
     * @param inTestName 
     * @param qNumber This indicates what question is being answered, and is used in all array references
     * @param qTxtList An Array of string containing the Question descriptions
     * @param inMarks 
     * @param trueAnswerList 
     * @param falseAnswerList 
     * @param completedQuestions 
     * @param inCurrentPoints 
     */
    public ParsonsProblemsQUI(String inTestName, int qNumber, String[] qTxtList, int[] inMarks, String[][] trueAnswerList, String[][] falseAnswerList, boolean[] completedQuestions, int[] inCurrentPoints) {
        testName = inTestName;
        qNo = qNumber;
        qTextArray = qTxtList;
        trueAnswerSet = trueAnswerList;
        falseAnswerSet = falseAnswerList;
        //set default values for the active answers block
        maxArrayLength = 0;
        activeAnswers = new boolean[]{false, false, false, false, false, false, false, false, false, false, false};
        marks = inMarks;
        
        completedQs = completedQuestions;
        if(completedQs[qNo] == true){
            isCorrect = true;
        }
        currentPoints = inCurrentPoints;
        initAnswers();
        initComponents();
        setupAnswerBlocks();
        initQuestionDetails();
        // Set the text to be blank on startup
        feedbackText.setText("");
        //tells the user if points cannot be earned on startup
        //System.out.println(currentPoints[qNo]);
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
        //Since there must be at least two true answers, no check is needed for the first three setTexts (since the third in that case will have to be "-- END OF PROGRAM --")
        posOneText.setText(randomAnswerArray[0]);
        posTwoText.setText(randomAnswerArray[1]);
        posThreeText.setText(randomAnswerArray[2]);
        if(maxArrayLength > 3){
            posFourText.setText(randomAnswerArray[3]);
        }
        if(maxArrayLength > 4){
            posFiveText.setText(randomAnswerArray[4]);
        }
        if(maxArrayLength > 5){
            posSixText.setText(randomAnswerArray[5]);
        }
        if(maxArrayLength > 6){
            posSevenText.setText(randomAnswerArray[6]);
        }
        if(maxArrayLength > 7){
            posEightText.setText(randomAnswerArray[7]);
        }
        if(maxArrayLength > 8){
            posNineText.setText(randomAnswerArray[8]);
        }
        if(maxArrayLength > 9){
            posTenText.setText(randomAnswerArray[9]);
        }
        if(maxArrayLength > 10){
            posElevenText.setText(randomAnswerArray[10]);
        }
        marksScore.setText(marks[qNo]+"/5");
        totPointsAvailable.setText("Total Points Available : " + currentPoints[qNo] + "/5");
        setTotalMarksLabel();
    }
    
    private void setTotalMarksLabel(){
        int markTemp = 0;
        for(int u = 0; u < qTextArray.length; u++){
            markTemp = markTemp + marks[u];
        }
        totalMarksScore.setText(markTemp + "/50");
    }
    
    private void setupAnswerBlocks(){
        for (int i = 0; i < maxArrayLength; i++) {
            activeAnswers[i] = true;
        }
        posFourText.setVisible(activeAnswers[3]);
        moveOneUpPos4.setVisible(activeAnswers[3]);
        moveOneDownPos4.setVisible(activeAnswers[3]);
        if (maxArrayLength < 4) {
            moveOneDownPos4.setVisible(false);
        }
        posFiveText.setVisible(activeAnswers[4]);
        moveOneUpPos5.setVisible(activeAnswers[4]);
        moveOneDownPos5.setVisible(activeAnswers[4]);
        if (maxArrayLength < 5) {
            moveOneDownPos5.setVisible(false);
        }
        posSixText.setVisible(activeAnswers[5]);
        moveOneUpPos6.setVisible(activeAnswers[5]);
        moveOneDownPos6.setVisible(activeAnswers[5]);
        if (maxArrayLength < 6) {
            moveOneDownPos5.setVisible(false);
        }
        posSevenText.setVisible(activeAnswers[6]);
        moveOneUpPos7.setVisible(activeAnswers[6]);
        moveOneDownPos7.setVisible(activeAnswers[6]);
        if (maxArrayLength < 7) {
            moveOneDownPos7.setVisible(false);
        }
        posEightText.setVisible(activeAnswers[7]);
        moveOneUpPos8.setVisible(activeAnswers[7]);
        moveOneDownPos8.setVisible(activeAnswers[7]);
        if (maxArrayLength < 8) {
            moveOneDownPos8.setVisible(false);
        }
        posNineText.setVisible(activeAnswers[8]);
        moveOneUpPos9.setVisible(activeAnswers[8]);
        moveOneDownPos9.setVisible(activeAnswers[8]);
        if(maxArrayLength < 9){
            moveOneDownPos9.setVisible(false);
        }
        posTenText.setVisible(activeAnswers[9]);
        moveOneUpPos10.setVisible(activeAnswers[9]);
        moveOneDownPos10.setVisible(activeAnswers[9]);
        if (maxArrayLength < 10) {
            moveOneDownPos10.setVisible(false);
        }
        posElevenText.setVisible(activeAnswers[10]);
        moveOneUpPos11.setVisible(activeAnswers[10]);
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
        //Does the same as moving the text down, but in the opposite order
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
    String[] falseAnswersAlreadyInArray = new String[maxArrayLength];
    
    private void initAnswers(){
        trueAnswerArray = setupTrueAnswerArray();
        randomAnswerArray = createRandomAnswerArray();
        for (int i = 0; i < randomAnswerArray.length; i++) {
            System.out.println(randomAnswerArray[i]);
        }
    }
    
    private String[] setupTrueAnswerArray(){
        String[] trueAnswerArrayTemp;
        //TRUECOUNT
        int trueAnswerCount = 0;
        for(int i = 0; i < 10; i++){
            //System.out.println(trueAnswerSet[qNo][i]);
            if(!trueAnswerSet[qNo][i].contains("INVALID")){
                //System.out.println("trueAnswer set" + trueAnswerSet[qNo][i]);
                trueAnswerCount++;
            }
        }
        trueAnswers = new String[trueAnswerCount+1];
        for (int k = 0; k < trueAnswers.length-1; k++) {
            trueAnswers[k] = trueAnswerSet[qNo][k];
            //System.out.println("true answer array " + trueAnswers[k]);
        }
        trueAnswers[trueAnswerCount] = "-- END OF PROGRAM --";
        //System.out.println(trueAnswers.length);
        //FALSE COUNT
        int falseAnswerCount = 0;
        for (int j = 0; j < 10; j++) {
            //System.out.println(falseAnswerSet[qNo][j]);
            if(!falseAnswerSet[qNo][j].contains("INVALID")){
                //System.out.println("false answer set" + falseAnswerSet[qNo][j]);
                falseAnswerCount++;
                //System.out.println(falseAnswerCount);
            }
        }
        //Skips creating the false array entirely if the false answer count is zero
        if (falseAnswerCount != 0) {
            String[] falseAnswers = new String[falseAnswerCount];
            for (int fa = 0; fa < falseAnswerCount; fa++) {
                falseAnswers[fa] = falseAnswerSet[qNo][fa];
            }
        }
        //ASSIGN FINAL ARRAY WITH BOTH TRUE AND FALSE VALUES
        
        //work out max array length
        if(trueAnswerCount + falseAnswerCount > 10){
            while(trueAnswerCount + falseAnswerCount > 10){
                //lowers the false answer count to dictate how many false answers will be in the array
                falseAnswerCount--;
            }
            maxArrayLength = trueAnswerCount + falseAnswerCount + 1; //the +1 being for the "-- END OF PROGRAM --" divider
        }
        else{
            maxArrayLength = trueAnswerCount + falseAnswerCount + 1;
        }
        trueAnswerArrayTemp = new String[maxArrayLength];
        //arrange the true answer array so that the true answers come first, then end of program string, then the rest are the false answers
        int trueNo = 0;
        while(trueNo < trueAnswers.length){
            trueAnswerArrayTemp[trueNo] = trueAnswers[trueNo];
            trueNo++;
        }
        //System.out.println(trueNo);
        //trueAnswerArrayTemp[trueAnswerCount] = "-- END OF PROGRAM --";
        int falseNo = 0;
        while(falseNo < falseAnswerCount){
            trueAnswerArrayTemp[trueNo+falseNo] = falseAnswerSet[qNo][falseNo];
            falseNo++;
        }
        
        //System.out.println(trueAnswerCount);
        
//        for (int i = 0; i < trueAnswerArrayTemp.length; i++) {
//            System.out.println(trueAnswerArrayTemp[i]);
//        }
        //System.out.println(trueAnswerArrayTemp.length);
        return trueAnswerArrayTemp;
    }

//old code    
//    private void initAnswers(){
//        String falseAnswer1 = getRandomFalseAnswer(falseAnswerSet[qNo], falseAnswersAlreadyInArray);
//        falseAnswersAlreadyInArray[0] = falseAnswer1;
//        //METHOD BELOW IS A WORKAROUND FOR REQUIRING A RETURN STATEMENT IN "getRandomFalseAnswer" METHOD
//        String falseAnswer2 = "INVALID";
//        while("INVALID".equals(falseAnswer2)){
//            falseAnswer2 = getRandomFalseAnswer(falseAnswerSet[qNo], falseAnswersAlreadyInArray);
//        }
//        falseAnswersAlreadyInArray[1] = falseAnswer2;
//        String falseAnswer3 = "INVALID";
//        while("INVALID".equals(falseAnswer3)){
//            falseAnswer3 = getRandomFalseAnswer(falseAnswerSet[qNo], falseAnswersAlreadyInArray);
//        }
//        falseAnswersAlreadyInArray[2] = falseAnswer3;
//        
//        String falseAnswer4 = "INVALID";
//        while("INVALID".equals(falseAnswer4)){
//            falseAnswer4 = getRandomFalseAnswer(falseAnswerSet[qNo], falseAnswersAlreadyInArray);
//        }
//        //the true answer array has the true order of the answer stored
//        trueAnswerArray = new String[maxArrayLength];
//        for(int i = 0;i < 3; i++){
//            trueAnswerArray[i] = trueAnswerSet[qNo][i];
//        }
//        trueAnswerArray[3] = falseAnswer1;
//        trueAnswerArray[4] = falseAnswer2;
//        trueAnswerArray[5] = falseAnswer3;
//        trueAnswerArray[6] = falseAnswer4;
//        
//        randomAnswerArray = new String[maxArrayLength];
//        randomAnswerArray[0] = "--END OF PROGRAM--";
//        String [] randomAnswerArrayTemp = createRandomAnswerArray();
//        for(int i = 1; i < 8; i++){
//            randomAnswerArray[i] = randomAnswerArrayTemp[i-1];
//        }
//        boolean arrayValid = false;
//        while(arrayValid == false){
//            if(randomAnswerArray[0].equals(trueAnswerArray[0])){
//                randomAnswerArray = createRandomAnswerArray();
//            }
//            else{
//                arrayValid = true;
//            }
//        }
//    }
    
        // Finds out how long the input list is, and gives a random false answer from the provided list
    /*
     * @param inAlreadyList contains the false answers already selected answers
    */
    static String [] inAlreadyList = new String[10];
    private static String getRandomFalseAnswer(String[] inFalseAnsList){
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
        String[] randomTempArray = new String[maxArrayLength];
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
        posFiveText = new javax.swing.JLabel();
        moveOneUpPos5 = new javax.swing.JButton();
        moveOneDownPos5 = new javax.swing.JButton();
        posSixText = new javax.swing.JLabel();
        moveOneUpPos6 = new javax.swing.JButton();
        moveOneDownPos6 = new javax.swing.JButton();
        posSevenText = new javax.swing.JLabel();
        moveOneUpPos7 = new javax.swing.JButton();
        moveOneDownPos7 = new javax.swing.JButton();
        posEightText = new javax.swing.JLabel();
        moveOneUpPos8 = new javax.swing.JButton();
        moveOneDownPos8 = new javax.swing.JButton();
        posNineText = new javax.swing.JLabel();
        moveOneUpPos9 = new javax.swing.JButton();
        moveOneDownPos9 = new javax.swing.JButton();
        posTenText = new javax.swing.JLabel();
        moveOneUpPos10 = new javax.swing.JButton();
        moveOneDownPos10 = new javax.swing.JButton();
        posElevenText = new javax.swing.JLabel();
        moveOneUpPos11 = new javax.swing.JButton();
        ButtonPanel = new javax.swing.JPanel();
        submitAnswerButton = new javax.swing.JButton();
        totPointsAvailable = new javax.swing.JLabel();
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
                .addComponent(qTextDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        posFiveText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posFiveText.setText("5.");

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

        moveOneUpPos8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos8.setText("▲");
        moveOneUpPos8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos8ActionPerformed(evt);
            }
        });

        moveOneDownPos8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos8.setText("▼");
        moveOneDownPos8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos8ActionPerformed(evt);
            }
        });

        posNineText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posNineText.setText("9.");

        moveOneUpPos9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos9.setText("▲");
        moveOneUpPos9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos9ActionPerformed(evt);
            }
        });

        moveOneDownPos9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos9.setText("▼");
        moveOneDownPos9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos9ActionPerformed(evt);
            }
        });

        posTenText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posTenText.setText("10.");

        moveOneUpPos10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos10.setText("▲");
        moveOneUpPos10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos10ActionPerformed(evt);
            }
        });

        moveOneDownPos10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneDownPos10.setText("▼");
        moveOneDownPos10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneDownPos10ActionPerformed(evt);
            }
        });

        posElevenText.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        posElevenText.setText("11.");

        moveOneUpPos11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moveOneUpPos11.setText("▲");
        moveOneUpPos11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneUpPos11ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout AnswerPanelLayout = new javax.swing.GroupLayout(AnswerPanel);
        AnswerPanel.setLayout(AnswerPanelLayout);
        AnswerPanelLayout.setHorizontalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnswerPanelLayout.createSequentialGroup()
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AnswerPanelLayout.createSequentialGroup()
                                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(posFourText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(posSixText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(posSevenText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(posEightText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(posFiveText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(posOneText, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(posTwoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(posThreeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(moveOneDownPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(moveOneDownPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                        .addComponent(moveOneDownPos7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(moveOneUpPos9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8))
                            .addGroup(AnswerPanelLayout.createSequentialGroup()
                                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addComponent(posElevenText, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(moveOneUpPos11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(posTenText, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                            .addComponent(posNineText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(moveOneUpPos10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moveOneDownPos10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        AnswerPanelLayout.setVerticalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AnswerPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(posOneText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moveOneDownPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(posTwoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moveOneDownPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneUpPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(posThreeText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneUpPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moveOneUpPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(moveOneUpPos8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posEightText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveOneUpPos9, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(posNineText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveOneUpPos10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posTenText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveOneDownPos10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveOneUpPos11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posElevenText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(QuestionInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        //quits to the question selection menu and save the current progress of the test, but for now just quits the application entirely
        QuestionSelect QS = new QuestionSelect(testName, qTextArray, trueAnswerSet, falseAnswerSet, marks, completedQs, currentPoints);
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
            int index = 0;
            while(index < trueAnswers.length){
                if(randomAnswerArray[index].equals(trueAnswers[index])){
                    isCorrect = true;
                }
                else{
                    isCorrect = false;
                }
                index++;
            }
            if(isCorrect == true){
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
                Summary Sum = new Summary(testName, qTextArray, trueAnswerSet, falseAnswerSet, marks);
                Sum.setVisible(true);
                Sum.setDefaultCloseOperation(Sum.DO_NOTHING_ON_CLOSE);
                this.setVisible(false);
                this.dispose();
            }
        }
        else{
            ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, qNo + 1, qTextArray, marks, trueAnswerSet, falseAnswerSet, completedQs, currentPoints);
            QUI.setVisible(true);
            this.setVisible(false);
            this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
            this.dispose();
        }
    }//GEN-LAST:event_nextButtonActionPerformed
    //Error shouldn't be triggered by this because the prev button should be invisible on q1
    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        ParsonsProblemsQUI QUI = new ParsonsProblemsQUI(testName, qNo - 1, qTextArray, marks, trueAnswerSet, falseAnswerSet, completedQs, currentPoints);
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

    private void moveOneUpPos9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos9ActionPerformed
        moveTextUp(8);
    }//GEN-LAST:event_moveOneUpPos9ActionPerformed

    private void moveOneUpPos8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos8ActionPerformed
        moveTextUp(7);
    }//GEN-LAST:event_moveOneUpPos8ActionPerformed

    private void moveOneDownPos8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos8ActionPerformed
        moveTextDown(7);
    }//GEN-LAST:event_moveOneDownPos8ActionPerformed

    private void moveOneDownPos9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos9ActionPerformed
        moveTextDown(8);
    }//GEN-LAST:event_moveOneDownPos9ActionPerformed

    private void moveOneUpPos10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos10ActionPerformed
        moveTextUp(9);
    }//GEN-LAST:event_moveOneUpPos10ActionPerformed

    private void moveOneDownPos10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneDownPos10ActionPerformed
        moveTextDown(9);
    }//GEN-LAST:event_moveOneDownPos10ActionPerformed

    private void moveOneUpPos11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneUpPos11ActionPerformed
        moveTextUp(10);
    }//GEN-LAST:event_moveOneUpPos11ActionPerformed
    
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
                new ParsonsProblemsQUI(testName, qNo, qTextArray, marks, trueAnswerSet, falseAnswerSet, completedQs, currentPoints).setVisible(true);
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
    private javax.swing.JButton moveOneDownPos10;
    private javax.swing.JButton moveOneDownPos2;
    private javax.swing.JButton moveOneDownPos3;
    private javax.swing.JButton moveOneDownPos4;
    private javax.swing.JButton moveOneDownPos5;
    private javax.swing.JButton moveOneDownPos6;
    private javax.swing.JButton moveOneDownPos7;
    private javax.swing.JButton moveOneDownPos8;
    private javax.swing.JButton moveOneDownPos9;
    private javax.swing.JButton moveOneUpPos10;
    private javax.swing.JButton moveOneUpPos11;
    private javax.swing.JButton moveOneUpPos2;
    private javax.swing.JButton moveOneUpPos3;
    private javax.swing.JButton moveOneUpPos4;
    private javax.swing.JButton moveOneUpPos5;
    private javax.swing.JButton moveOneUpPos6;
    private javax.swing.JButton moveOneUpPos7;
    private javax.swing.JButton moveOneUpPos8;
    private javax.swing.JButton moveOneUpPos9;
    private javax.swing.JPanel navPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel posEightText;
    private javax.swing.JLabel posElevenText;
    private javax.swing.JLabel posFiveText;
    private javax.swing.JLabel posFourText;
    private javax.swing.JLabel posNineText;
    private javax.swing.JLabel posOneText;
    private javax.swing.JLabel posSevenText;
    private javax.swing.JLabel posSixText;
    private javax.swing.JLabel posTenText;
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
