/*
 * Create A Test UI
 * Parsons Problems
 */
package parsonsproblems;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A form to Create a test in a more controlled environment than a text editor
 * @author UP815458
 */
public class CreateATest extends javax.swing.JFrame {
    
    String desc = "";
    String [] qTextArray = new String [] {"1","2","3","4","5","6","7","8","9","10"};
    String [][] trueAnswers;
    String [][] falseAnswers;
    
    int currentQNo;
    int currentFalseNo;
    
    Color goodGreen = new Color(51,204,0);

    /**
     * Creates new form CreateATest
     */
    public CreateATest() {
        currentQNo = 0;
        currentFalseNo = 0;
        falseAnswers = initDefaultFalseValues();
        trueAnswers = initDefaultTrueValues();
        initComponents();
    }
    
    public CreateATest(String[] inQTextArray, String[][] inTrueAnswers, String[][] inFalseAnswers){
        initComponents();
        qTextArray = inQTextArray;
        trueAnswers = inTrueAnswers;
        falseAnswers = inFalseAnswers;
    }
    
    private void prevQuestion(){
        if(currentQNo == 0){
            qTextArray[currentQNo] = QTextArea.getText();
            saveAndCheckTrueValidity();
            saveAndCheckFalseValidity();
            setTextFields();
            falseAnswers[currentQNo][currentFalseNo] = falseTextField.getText();
        }
        else{
            qTextArray[currentQNo] = QTextArea.getText();
            saveAndCheckTrueValidity();
            saveAndCheckFalseValidity();
            falseAnswers[currentQNo][currentFalseNo] = falseTextField.getText();
            currentFalseNo = 0;
            
            currentQNo--;
            setTextFields();
            questionLabel.setText("Question : " + (currentQNo + 1) + "/10");
            questionLabelTop.setText("Question : " + (currentQNo + 1) + "/10");
            questionLabelMirror.setText("Question : " + (currentQNo + 1) + "/10");
        }
        //Still will save the contents of the currently on screen text fields
    }
    private void nextQuestion(){
        if(currentQNo == 9){
            qTextArray[currentQNo] = QTextArea.getText();
            saveAndCheckTrueValidity();
            saveAndCheckFalseValidity();
            setTextFields();
            falseAnswers[currentQNo][currentFalseNo] = falseTextField.getText();
        }
        else{
            qTextArray[currentQNo] = QTextArea.getText();
            saveAndCheckTrueValidity();
            saveAndCheckFalseValidity();
            falseAnswers[currentQNo][currentFalseNo] = falseTextField.getText();
            currentFalseNo = 0;
            currentQNo++;
            setTextFields();
            questionLabel.setText("Question : " + (currentQNo + 1) + "/10");
            questionLabelTop.setText("Question : " + (currentQNo + 1) + "/10");
            questionLabelMirror.setText("Question : " + (currentQNo + 1) + "/10");
        }
    }
    
    private void saveAndCheckTrueValidity(){
        String trueAns0 = trueTextField0.getText();
        String trueAns1 = trueTextField1.getText();
        String trueAns2 = trueTextField2.getText();
        boolean valid = true;
        
        if("True Answer 1".equals(trueAns0) ||
           "True Answer 2".equals(trueAns1) ||
           "True Answer 3".equals(trueAns2) ||
           trueAns0.equals(trueAns1) ||
           trueAns0.equals(trueAns2) ||
           trueAns1.equals(trueAns2)){
            valid = false;
        }
        
        trueAnswers[currentQNo][0] = trueAns0;
        trueAnswers[currentQNo][1] = trueAns1;
        trueAnswers[currentQNo][2] = trueAns2;
        
        //For more advanced feedback
        //VERY ROUGH AT THE MOMENT
        String invalidStr = "";
        if(valid == false){
            //Are any of the fields still the default?
            if("True Answer 1".equals(trueAns0) &&
               "True Answer 2".equals(trueAns1) &&
               "True Answer 3".equals(trueAns2)){
                invalidStr = "1, 2 & 3";
            }
            else if("True Answer 1".equals(trueAns0) &&
                    "True Answer 2".equals(trueAns1)){
                invalidStr = "1 & 2";
            }
            else if("True Answer 2".equals(trueAns1) &&
                    "True Answer 3".equals(trueAns2)){
                invalidStr = "2 & 3";
            }
            else if("True Answer 1".equals(trueAns0) &&
                    "True Answer 3".equals(trueAns2)){
                invalidStr = "1 & 3";
            }
            
            //Are all of the fields the same value?
            //if so, make usable and update trueAnswer table
            else if(trueAns0.equals(trueAns1) &&
                    trueAns0.equals(trueAns2)){
                invalidStr = "1, 2 & 3";
                String new1 = "1-" + trueAns0;
                String new2 = "2-" + trueAns1;
                String new3 = "3-" + trueAns2;
                trueAnswers[currentQNo][0] = new1;
                trueAnswers[currentQNo][1] = new2;
                trueAnswers[currentQNo][2] = new3;
            }
            //Are any of the fields equal to one another?
            else if(trueAns0.equals(trueAns1)){
                invalidStr = "1 & 2";
                String new1 = "1-" + trueAns0;
                String new2 = "2-" + trueAns1;
                trueAnswers[currentQNo][0] = new1;
                trueAnswers[currentQNo][1] = new2;
            }
            else if(trueAns1.equals(trueAns2)){
                invalidStr = "2 & 3";
                String new2 = "2-" + trueAns1;
                String new3 = "3-" + trueAns2;
                trueAnswers[currentQNo][1] = new2;
                trueAnswers[currentQNo][2] = new3;
            }
            else if(trueAns0.equals(trueAns2)){
                invalidStr = "1 & 3";
                String new1 = "1-" + trueAns0;
                String new3 = "3-" + trueAns2;
                trueAnswers[currentQNo][0] = new1;
                trueAnswers[currentQNo][1] = new3;
            }
            else if("True Answer 1".equals(trueAns0)){
                invalidStr = "1";
            }
            else if("True Answer 2".equals(trueAns1)){
                invalidStr = "2";
            }
            else if("True Answer 3".equals(trueAns2)){
                invalidStr = "3";
            }
        }
        
        
        if(valid == false){
            trueFeedback.setForeground(Color.RED);
            trueFeedback.setText("<html>True Answers Saved <br> although these answers:<br>" + invalidStr + " are not valid, as they are either<br> still the default or duplicates of one another");
        }
        else{
            trueFeedback.setForeground(goodGreen);
            trueFeedback.setText("True Answers Saved");
        }
    }
    
    private void saveAndCheckFalseValidity(){
        String [] ansAlreadyIn = new String [] {"","","","","",""};
        String[] tempList = falseAnswers[currentQNo];
        for(int i = 0;i < 6 ;i++){
            String tempString = tempList[i];
            if(ansAlreadyIn[i].contains(tempString)){
                falseAnswers[currentQNo][i] = tempString + "-";
                falseFeedback.setForeground(Color.RED);
                falseFeedback.setText("<html>Duplicates Found<br>& corrected");
            }
            else{
                ansAlreadyIn[i] = tempString;
            }
        }
    }
    // Set text fields
    private void setTextFields(){
        QTextArea.setText(qTextArray[currentQNo]);
        trueTextField0.setText(trueAnswers[currentQNo][0]);
        trueTextField1.setText(trueAnswers[currentQNo][1]);
        trueTextField2.setText(trueAnswers[currentQNo][2]);
        falseTextField.setText(falseAnswers[currentQNo][0]);
    }
    
    private void writeFile(String inContent, String inFilename) throws IOException{
        FileWriter writer = new FileWriter("./TESTDATA/Tests/TEST--" + inFilename);
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

        infoPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        instructions = new javax.swing.JLabel();
        saveFilePanel = new javax.swing.JPanel();
        Sdescription = new javax.swing.JLabel();
        saveFileTitle = new javax.swing.JLabel();
        fileNameLabel = new javax.swing.JLabel();
        fileNameTextField = new javax.swing.JTextField();
        saveFile = new javax.swing.JButton();
        saveFeedback = new javax.swing.JLabel();
        qTextPanel = new javax.swing.JPanel();
        qTextScrollPane = new javax.swing.JScrollPane();
        QTextArea = new javax.swing.JTextArea();
        qTextTitle = new javax.swing.JLabel();
        questionLabel = new javax.swing.JLabel();
        QTextDescription = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        nextQText = new javax.swing.JButton();
        prevQText = new javax.swing.JButton();
        createAnswersPanel = new javax.swing.JPanel();
        questionLabelTop = new javax.swing.JLabel();
        trueAnswersPanel = new javax.swing.JPanel();
        trueAnswersTitle = new javax.swing.JLabel();
        Tdescription = new javax.swing.JLabel();
        trueAnswerPanel1 = new javax.swing.JPanel();
        trueAnswerLabel1 = new javax.swing.JLabel();
        trueTextField0 = new javax.swing.JTextField();
        trueAnswerPanel2 = new javax.swing.JPanel();
        trueAnswerLabel2 = new javax.swing.JLabel();
        trueTextField1 = new javax.swing.JTextField();
        trueAnswerPanel3 = new javax.swing.JPanel();
        trueAnswerLabel3 = new javax.swing.JLabel();
        trueTextField2 = new javax.swing.JTextField();
        saveTrue = new javax.swing.JButton();
        trueFeedback = new javax.swing.JLabel();
        falseAnswerPanel = new javax.swing.JPanel();
        Fdescription = new javax.swing.JLabel();
        inputPanelFalse = new javax.swing.JPanel();
        falseAnswerNo = new javax.swing.JLabel();
        falseTextField = new javax.swing.JTextField();
        prevFalseButton = new javax.swing.JButton();
        nextFalseButton = new javax.swing.JButton();
        falseAnswersTitle = new javax.swing.JLabel();
        saveFalse = new javax.swing.JButton();
        falseFeedback = new javax.swing.JLabel();
        questionLabelMirror = new javax.swing.JLabel();
        answerPanelTitle = new javax.swing.JLabel();
        prevQTop = new javax.swing.JButton();
        nextQTop = new javax.swing.JButton();
        prevQBottom = new javax.swing.JButton();
        nextQBottom = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titleLabel.setText("Create A Test");

        instructions.setText("<html>Use the panels on the right side of the screen to create <br> your test");

        saveFilePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Sdescription.setText("Here you can save the file by providing a valid file name");

        saveFileTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        saveFileTitle.setText("Save File");

        fileNameLabel.setText("Filename :");

        saveFile.setText("Save File");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });

        saveFeedback.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveFeedback.setForeground(new java.awt.Color(51, 204, 0));
        saveFeedback.setText("Feedback displays here");

        javax.swing.GroupLayout saveFilePanelLayout = new javax.swing.GroupLayout(saveFilePanel);
        saveFilePanel.setLayout(saveFilePanelLayout);
        saveFilePanelLayout.setHorizontalGroup(
            saveFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(saveFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(saveFilePanelLayout.createSequentialGroup()
                        .addComponent(fileNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameTextField))
                    .addGroup(saveFilePanelLayout.createSequentialGroup()
                        .addGroup(saveFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveFileTitle)
                            .addComponent(Sdescription))
                        .addGap(0, 117, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveFilePanelLayout.createSequentialGroup()
                        .addComponent(saveFeedback)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveFile)))
                .addContainerGap())
        );
        saveFilePanelLayout.setVerticalGroup(
            saveFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveFileTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sdescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(saveFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(saveFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveFile)
                    .addComponent(saveFeedback))
                .addContainerGap())
        );

        qTextPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        QTextArea.setColumns(20);
        QTextArea.setRows(5);
        qTextScrollPane.setViewportView(QTextArea);

        qTextTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        qTextTitle.setText("Question Text Editing");

        questionLabel.setText("Question : 1/10");

        QTextDescription.setText("Description");

        jButton1.setText("Save Description");

        nextQText.setText("Next Question >>");
        nextQText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQTextActionPerformed(evt);
            }
        });

        prevQText.setText("<< Previous Question");
        prevQText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevQTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout qTextPanelLayout = new javax.swing.GroupLayout(qTextPanel);
        qTextPanel.setLayout(qTextPanelLayout);
        qTextPanelLayout.setHorizontalGroup(
            qTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qTextScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qTextPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(qTextPanelLayout.createSequentialGroup()
                        .addGroup(qTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qTextTitle)
                            .addComponent(QTextDescription)
                            .addGroup(qTextPanelLayout.createSequentialGroup()
                                .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prevQText, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextQText)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        qTextPanelLayout.setVerticalGroup(
            qTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qTextTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTextDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabel)
                    .addComponent(nextQText)
                    .addComponent(prevQText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qTextPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(instructions, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructions, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        createAnswersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        questionLabelTop.setText("Question : 1/10");

        trueAnswersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        trueAnswersTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        trueAnswersTitle.setText("True Answers");

        Tdescription.setText("Here is where you can create the true answers for question");

        trueAnswerPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        trueAnswerLabel1.setText("True Answer 1 :");

        trueTextField0.setText("True Answer 1");

        javax.swing.GroupLayout trueAnswerPanel1Layout = new javax.swing.GroupLayout(trueAnswerPanel1);
        trueAnswerPanel1.setLayout(trueAnswerPanel1Layout);
        trueAnswerPanel1Layout.setHorizontalGroup(
            trueAnswerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trueAnswerPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(trueAnswerLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(trueTextField0, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        trueAnswerPanel1Layout.setVerticalGroup(
            trueAnswerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trueAnswerPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(trueAnswerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trueTextField0, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trueAnswerLabel1))
                .addContainerGap())
        );

        trueAnswerPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        trueAnswerLabel2.setText("True Answer 2 :");

        trueTextField1.setText("True Answer 2");

        javax.swing.GroupLayout trueAnswerPanel2Layout = new javax.swing.GroupLayout(trueAnswerPanel2);
        trueAnswerPanel2.setLayout(trueAnswerPanel2Layout);
        trueAnswerPanel2Layout.setHorizontalGroup(
            trueAnswerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trueAnswerPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(trueAnswerLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(trueTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        trueAnswerPanel2Layout.setVerticalGroup(
            trueAnswerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trueAnswerPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(trueAnswerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trueTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trueAnswerLabel2))
                .addContainerGap())
        );

        trueAnswerPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        trueAnswerLabel3.setText("True Answer 3 :");

        trueTextField2.setText("True Answer 3");

        javax.swing.GroupLayout trueAnswerPanel3Layout = new javax.swing.GroupLayout(trueAnswerPanel3);
        trueAnswerPanel3.setLayout(trueAnswerPanel3Layout);
        trueAnswerPanel3Layout.setHorizontalGroup(
            trueAnswerPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trueAnswerPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trueAnswerLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(trueTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trueAnswerPanel3Layout.setVerticalGroup(
            trueAnswerPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueAnswerPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(trueAnswerPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trueAnswerLabel3)
                    .addComponent(trueTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        saveTrue.setText("Save True Answers");
        saveTrue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTrueActionPerformed(evt);
            }
        });

        trueFeedback.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        trueFeedback.setForeground(new java.awt.Color(51, 204, 0));
        trueFeedback.setText("Feedback displays here");

        javax.swing.GroupLayout trueAnswersPanelLayout = new javax.swing.GroupLayout(trueAnswersPanel);
        trueAnswersPanel.setLayout(trueAnswersPanelLayout);
        trueAnswersPanelLayout.setHorizontalGroup(
            trueAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueAnswersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trueAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trueAnswersPanelLayout.createSequentialGroup()
                        .addGroup(trueAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(trueAnswersTitle)
                            .addComponent(Tdescription)
                            .addComponent(trueAnswerPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trueAnswerPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trueAnswerPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(trueAnswersPanelLayout.createSequentialGroup()
                        .addComponent(trueFeedback)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveTrue)
                        .addGap(27, 27, 27))))
        );
        trueAnswersPanelLayout.setVerticalGroup(
            trueAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueAnswersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trueAnswersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tdescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(trueAnswerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trueAnswerPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trueAnswerPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(trueAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveTrue)
                    .addComponent(trueFeedback))
                .addContainerGap())
        );

        falseAnswerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Fdescription.setText("<html>Here is where you can create your false answers,  the arrows will allow you<br>to set up to 6 false answers, the arrows will save your progress as you go");

        inputPanelFalse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        falseAnswerNo.setText("False answer 1/6 :");

        falseTextField.setText("False Answer 1");

        prevFalseButton.setText("<");
        prevFalseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevFalseButtonActionPerformed(evt);
            }
        });

        nextFalseButton.setText(">");
        nextFalseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextFalseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelFalseLayout = new javax.swing.GroupLayout(inputPanelFalse);
        inputPanelFalse.setLayout(inputPanelFalseLayout);
        inputPanelFalseLayout.setHorizontalGroup(
            inputPanelFalseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelFalseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(falseAnswerNo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(falseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prevFalseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextFalseButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        inputPanelFalseLayout.setVerticalGroup(
            inputPanelFalseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelFalseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputPanelFalseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(falseTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inputPanelFalseLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(falseAnswerNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputPanelFalseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(prevFalseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nextFalseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        falseAnswersTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        falseAnswersTitle.setText("False Answers");

        saveFalse.setText("Save False Answer");
        saveFalse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFalseActionPerformed(evt);
            }
        });

        falseFeedback.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        falseFeedback.setForeground(new java.awt.Color(51, 204, 0));
        falseFeedback.setText("Feedback displays here");

        javax.swing.GroupLayout falseAnswerPanelLayout = new javax.swing.GroupLayout(falseAnswerPanel);
        falseAnswerPanel.setLayout(falseAnswerPanelLayout);
        falseAnswerPanelLayout.setHorizontalGroup(
            falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                        .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(falseAnswersTitle)
                            .addComponent(Fdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, falseAnswerPanelLayout.createSequentialGroup()
                        .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                                .addComponent(falseFeedback)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveFalse))
                            .addComponent(inputPanelFalse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))))
        );
        falseAnswerPanelLayout.setVerticalGroup(
            falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(falseAnswersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPanelFalse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveFalse)
                    .addComponent(falseFeedback))
                .addContainerGap())
        );

        questionLabelMirror.setText("Question 1/10");

        answerPanelTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        answerPanelTitle.setText("Answer Editing");

        prevQTop.setText("<< Previous Question");
        prevQTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevQTopActionPerformed(evt);
            }
        });

        nextQTop.setText("Next Question >>");
        nextQTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQTopActionPerformed(evt);
            }
        });

        prevQBottom.setText("<< Previous Question");
        prevQBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevQBottomActionPerformed(evt);
            }
        });

        nextQBottom.setText("Next Question >>");
        nextQBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQBottomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createAnswersPanelLayout = new javax.swing.GroupLayout(createAnswersPanel);
        createAnswersPanel.setLayout(createAnswersPanelLayout);
        createAnswersPanelLayout.setHorizontalGroup(
            createAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createAnswersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(trueAnswersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(falseAnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(createAnswersPanelLayout.createSequentialGroup()
                        .addGroup(createAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(answerPanelTitle)
                            .addGroup(createAnswersPanelLayout.createSequentialGroup()
                                .addComponent(questionLabelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prevQTop, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextQTop))
                            .addGroup(createAnswersPanelLayout.createSequentialGroup()
                                .addComponent(questionLabelMirror)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prevQBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextQBottom)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        createAnswersPanelLayout.setVerticalGroup(
            createAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createAnswersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(answerPanelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabelTop)
                    .addComponent(prevQTop)
                    .addComponent(nextQTop))
                .addGap(18, 18, 18)
                .addComponent(trueAnswersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(falseAnswerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createAnswersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabelMirror)
                    .addComponent(prevQBottom)
                    .addComponent(nextQBottom))
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
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(createAnswersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(quitButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createAnswersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveTrueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTrueActionPerformed
        saveAndCheckTrueValidity();
        setTextFields();
    }//GEN-LAST:event_saveTrueActionPerformed
    //False Answer Panel
    //Previous/Next buttons
    private void prevFalseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevFalseButtonActionPerformed
        if(currentFalseNo == 0){}
        else{
            saveAndCheckFalseValidity();
            currentFalseNo--;
            setTextFields();
            falseAnswerNo.setText("False answer " + (currentFalseNo + 1) + "/6");
            
        }
    }//GEN-LAST:event_prevFalseButtonActionPerformed

    private void nextFalseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextFalseButtonActionPerformed
        if(currentFalseNo == 5){}
        else{
            saveAndCheckFalseValidity();
            currentFalseNo++;
            setTextFields();
            falseAnswerNo.setText("False answer " + (currentFalseNo + 1) + "/6");
            
        }
    }//GEN-LAST:event_nextFalseButtonActionPerformed

    private void saveFalseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFalseActionPerformed
        saveAndCheckFalseValidity();
    }//GEN-LAST:event_saveFalseActionPerformed
    //PREVIOUS/NEXT QUESTION BUTTONS
    //TOP OF RIGHT PANEL
    private void prevQTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevQTopActionPerformed
        prevQuestion();
    }//GEN-LAST:event_prevQTopActionPerformed

    private void nextQTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQTopActionPerformed
        nextQuestion();
    }//GEN-LAST:event_nextQTopActionPerformed
    //BOTTOM OF RIGHT PANEL
    private void prevQBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevQBottomActionPerformed
        prevQuestion();
    }//GEN-LAST:event_prevQBottomActionPerformed

    private void nextQTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQTextActionPerformed
        nextQuestion();
    }//GEN-LAST:event_nextQTextActionPerformed
    //LEFT PANEL
    private void prevQTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevQTextActionPerformed
        prevQuestion();
    }//GEN-LAST:event_prevQTextActionPerformed

    private void nextQBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQBottomActionPerformed
        nextQuestion();
    }//GEN-LAST:event_nextQBottomActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        AreYouSure As = new AreYouSure(qTextArray, trueAnswers, falseAnswers);
        As.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileActionPerformed
        String fileDest = fileNameTextField.getText();
        if("".equals(fileDest)){
            saveFeedback.setForeground(Color.RED);
            saveFeedback.setText("The filename cannot be empty");
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
            saveFeedback.setForeground(Color.RED);
            saveFeedback.setText("<html>A file name cannot contain<br> any of the following characters : <br> \\ / : * ? \" < >");
        }
        else{
            saveFeedback.setText("");
            String fileName = fileDest + ".txt";
            String fileContent = "//-----------------------------\n"
                               + "//-----------------------------\n"
                               + "//    DO NOT EDIT THIS FILE\n \n"
                               + "//THIS FILE WILL BE USED TO LOAD THE STORED TEST \n"
                               + "//-----------------------------\n"
                               + "//-----------------------------\n"
                               + "/* proof that this works"
                               + "/*";
            
            try {
                writeFile(fileContent, fileName);
            } catch (IOException ex) {
                Logger.getLogger(Summary.class.getName()).log(Level.SEVERE, null, ex);
            }
            saveFeedback.setForeground(goodGreen);
            saveFeedback.setText("File saved successfully");
        }
    }//GEN-LAST:event_saveFileActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(CreateATest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateATest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateATest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateATest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateATest().setVisible(true);
            }
        });
    }
    //Creates dummy temp data so that the file is usable even if not fully completed
    private static String[][] initDefaultTrueValues(){
        String[][] trueTemp = new String[10][3];
        
        for(int i = 0;i < 10;i++){
            trueTemp[i][0] = "True Answer 1";
            trueTemp[i][1] = "True Answer 2";
            trueTemp[i][2] = "True Answer 3";
        }
        return trueTemp;
    }
    //Creates dummy temp data so that the file is usable even if not fully completed
    private static String[][] initDefaultFalseValues(){
        //Now a hard limit of 6 has been introduced for false answers
        //note: maybe if theres less than 6, for loop the rest, putting random answers in the rest of the slots
        String[][] falseTemp = new String[10][6];
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
        return falseTemp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fdescription;
    private javax.swing.JTextArea QTextArea;
    private javax.swing.JLabel QTextDescription;
    private javax.swing.JLabel Sdescription;
    private javax.swing.JLabel Tdescription;
    private javax.swing.JLabel answerPanelTitle;
    private javax.swing.JPanel createAnswersPanel;
    private javax.swing.JLabel falseAnswerNo;
    private javax.swing.JPanel falseAnswerPanel;
    private javax.swing.JLabel falseAnswersTitle;
    private javax.swing.JLabel falseFeedback;
    private javax.swing.JTextField falseTextField;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel inputPanelFalse;
    private javax.swing.JLabel instructions;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton nextFalseButton;
    private javax.swing.JButton nextQBottom;
    private javax.swing.JButton nextQText;
    private javax.swing.JButton nextQTop;
    private javax.swing.JButton prevFalseButton;
    private javax.swing.JButton prevQBottom;
    private javax.swing.JButton prevQText;
    private javax.swing.JButton prevQTop;
    private javax.swing.JPanel qTextPanel;
    private javax.swing.JScrollPane qTextScrollPane;
    private javax.swing.JLabel qTextTitle;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel questionLabelMirror;
    private javax.swing.JLabel questionLabelTop;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton saveFalse;
    private javax.swing.JLabel saveFeedback;
    private javax.swing.JButton saveFile;
    private javax.swing.JPanel saveFilePanel;
    private javax.swing.JLabel saveFileTitle;
    private javax.swing.JButton saveTrue;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel trueAnswerLabel1;
    private javax.swing.JLabel trueAnswerLabel2;
    private javax.swing.JLabel trueAnswerLabel3;
    private javax.swing.JPanel trueAnswerPanel1;
    private javax.swing.JPanel trueAnswerPanel2;
    private javax.swing.JPanel trueAnswerPanel3;
    private javax.swing.JPanel trueAnswersPanel;
    private javax.swing.JLabel trueAnswersTitle;
    private javax.swing.JLabel trueFeedback;
    private javax.swing.JTextField trueTextField0;
    private javax.swing.JTextField trueTextField1;
    private javax.swing.JTextField trueTextField2;
    // End of variables declaration//GEN-END:variables
}
