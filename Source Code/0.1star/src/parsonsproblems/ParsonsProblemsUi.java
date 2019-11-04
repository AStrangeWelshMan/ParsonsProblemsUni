/*
 * Parsons Problems Question UI V0.1*
 * * Denotes there is still work to be done with the randomisation section
 * (specifically with createRandomAnswerArray)
 */
package parsonsproblems;

import java.util.Arrays;

/**
 * @author UP815458
 */
public class ParsonsProblemsUi extends javax.swing.JFrame {
    
    /**
     * generates a random answer from the given list
     */
    public ParsonsProblemsUi() {
        System.out.println("Initialising question 1 answers\n...");
        initAnswers();
        System.out.println("...\nQuestion 1 answers Initialized");
        initComponents();
        setTextFields();
        System.out.println("Question UI Initialized");
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
        QNoDisp = new javax.swing.JLabel();
        QTextDisp = new javax.swing.JLabel();
        MarkingPanel = new javax.swing.JPanel();
        marksLabel = new javax.swing.JLabel();
        marksScore = new javax.swing.JLabel();
        increaseMarkDisp = new javax.swing.JLabel();
        AnswerPanel = new javax.swing.JPanel();
        ButtonPanel = new javax.swing.JPanel();
        quitButton = new javax.swing.JButton();
        submitAnswerButton = new javax.swing.JButton();
        answerBlock1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        posOneTextField = new javax.swing.JTextField();
        OneDownButton1 = new javax.swing.JButton();
        answerBlock2 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        posTwoTextField = new javax.swing.JTextField();
        OneDownButton2 = new javax.swing.JButton();
        OneUpButton1 = new javax.swing.JButton();
        answerBlock3 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        posThreeTextField = new javax.swing.JTextField();
        OneDownButton3 = new javax.swing.JButton();
        OneUpButton2 = new javax.swing.JButton();
        answerBlock4 = new javax.swing.JPanel();
        labelFalse = new javax.swing.JLabel();
        posFalseTextField = new javax.swing.JTextField();
        OneUpButton3 = new javax.swing.JButton();
        greenFeedbackText = new javax.swing.JLabel();
        redFeedbackText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        QuestionInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        QNoDisp.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        QNoDisp.setText("Question 1");

        QTextDisp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        QTextDisp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        QTextDisp.setText("<html> Using the buttons to the side <br/> of the textfields on the right, <br/> Arrange the code snippets so that it <br/> creates an if statement that returns <br/> true if the condition inside the <br/> paranthesis is met. <br/> <br/> in addition to this, one <br/> of these answers is <br/> false, put this false answer <br/> in the false answer combo box");

        MarkingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MarkingPanel.setPreferredSize(new java.awt.Dimension(76, 50));

        marksLabel.setText("Marks:");

        marksScore.setText("0/5\n");

        increaseMarkDisp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        increaseMarkDisp.setForeground(new java.awt.Color(51, 204, 0));

        javax.swing.GroupLayout MarkingPanelLayout = new javax.swing.GroupLayout(MarkingPanel);
        MarkingPanel.setLayout(MarkingPanelLayout);
        MarkingPanelLayout.setHorizontalGroup(
            MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MarkingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(marksLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(marksScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(increaseMarkDisp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MarkingPanelLayout.setVerticalGroup(
            MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MarkingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MarkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(increaseMarkDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(marksLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(marksScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout QuestionInfoPanelLayout = new javax.swing.GroupLayout(QuestionInfoPanel);
        QuestionInfoPanel.setLayout(QuestionInfoPanelLayout);
        QuestionInfoPanelLayout.setHorizontalGroup(
            QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QuestionInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(QTextDisp, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addComponent(QNoDisp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MarkingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                .addContainerGap())
        );
        QuestionInfoPanelLayout.setVerticalGroup(
            QuestionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestionInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(QNoDisp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTextDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MarkingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        QTextDisp.getAccessibleContext().setAccessibleName("Question 1:\nArrange the code snippets so that it creates an if statement that returns true if the ");

        AnswerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ButtonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ButtonPanel.setPreferredSize(new java.awt.Dimension(324, 50));

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        submitAnswerButton.setText("Submit Answer");
        submitAnswerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAnswerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonPanelLayout = new javax.swing.GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitAnswerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quitButton)
                .addContainerGap())
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitButton)
                    .addComponent(submitAnswerButton))
                .addContainerGap())
        );

        label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label1.setText("1.");

        posOneTextField.setEditable(false);
        posOneTextField.setBackground(new java.awt.Color(255, 255, 255));

        OneDownButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OneDownButton1.setText("▼");
        OneDownButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneDownButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout answerBlock1Layout = new javax.swing.GroupLayout(answerBlock1);
        answerBlock1.setLayout(answerBlock1Layout);
        answerBlock1Layout.setHorizontalGroup(
            answerBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(posOneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OneDownButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        answerBlock1Layout.setVerticalGroup(
            answerBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(answerBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OneDownButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(answerBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(posOneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label2.setText("2.");

        posTwoTextField.setEditable(false);
        posTwoTextField.setBackground(new java.awt.Color(255, 255, 255));

        OneDownButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OneDownButton2.setText("▼");
        OneDownButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneDownButton2ActionPerformed(evt);
            }
        });

        OneUpButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OneUpButton1.setText("▲");
        OneUpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneUpButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout answerBlock2Layout = new javax.swing.GroupLayout(answerBlock2);
        answerBlock2.setLayout(answerBlock2Layout);
        answerBlock2Layout.setHorizontalGroup(
            answerBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(posTwoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OneUpButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OneDownButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        answerBlock2Layout.setVerticalGroup(
            answerBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(answerBlock2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OneDownButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OneUpButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posTwoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        label3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label3.setText("3.");

        posThreeTextField.setEditable(false);
        posThreeTextField.setBackground(new java.awt.Color(255, 255, 255));

        OneDownButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OneDownButton3.setText("▼");
        OneDownButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneDownButton3ActionPerformed(evt);
            }
        });

        OneUpButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OneUpButton2.setText("▲");
        OneUpButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneUpButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout answerBlock3Layout = new javax.swing.GroupLayout(answerBlock3);
        answerBlock3.setLayout(answerBlock3Layout);
        answerBlock3Layout.setHorizontalGroup(
            answerBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(posThreeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OneUpButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OneDownButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        answerBlock3Layout.setVerticalGroup(
            answerBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(answerBlock3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OneDownButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OneUpButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posThreeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        labelFalse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelFalse.setText("<html> False <br/>answer");

        posFalseTextField.setEditable(false);
        posFalseTextField.setBackground(new java.awt.Color(255, 255, 255));

        OneUpButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OneUpButton3.setText("▲");
        OneUpButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneUpButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout answerBlock4Layout = new javax.swing.GroupLayout(answerBlock4);
        answerBlock4.setLayout(answerBlock4Layout);
        answerBlock4Layout.setHorizontalGroup(
            answerBlock4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFalse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posFalseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OneUpButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        answerBlock4Layout.setVerticalGroup(
            answerBlock4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerBlock4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(answerBlock4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFalse)
                    .addGroup(answerBlock4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(OneUpButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(posFalseTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );

        greenFeedbackText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        greenFeedbackText.setForeground(new java.awt.Color(102, 204, 0));

        redFeedbackText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        redFeedbackText.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout AnswerPanelLayout = new javax.swing.GroupLayout(AnswerPanel);
        AnswerPanel.setLayout(AnswerPanelLayout);
        AnswerPanelLayout.setHorizontalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnswerPanelLayout.createSequentialGroup()
                        .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AnswerPanelLayout.createSequentialGroup()
                        .addGroup(AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(answerBlock4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answerBlock3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answerBlock2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(answerBlock1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(AnswerPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(greenFeedbackText)
                .addGap(41, 41, 41)
                .addComponent(redFeedbackText)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AnswerPanelLayout.setVerticalGroup(
            AnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AnswerPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(answerBlock1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(answerBlock2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answerBlock3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(answerBlock4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(redFeedbackText, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenFeedbackText)
                .addGap(31, 31, 31)
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(QuestionInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(QuestionInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // To be changed to quitting to the test selection menu and save the current progress of the test, but for now just quits the application entirely
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    boolean isCorrect = false;
    int currentPoints = 5;
    private void submitAnswerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAnswerButtonActionPerformed
        //clear the feedback messages
        greenFeedbackText.setText("");
        redFeedbackText.setText("");
        //checks the answer and provides feedback accordingly (will be the bulk of the main code for this UI eventually)
        if(isCorrect == true){
            greenFeedbackText.setText("√, Marks Already earned");
        }
        //check the current state of the randomArray against the trueArray
        if(randomAnswerArray[0].equals(trueAnswerArray[0])
        && randomAnswerArray[1].equals(trueAnswerArray[1])
        && randomAnswerArray[2].equals(trueAnswerArray[2])
        && randomAnswerArray[3].equals(trueAnswerArray[3])  ){
            if(currentPoints == 5){
                greenFeedbackText.setText("√ Full marks, well done!");
            }
            else{
                greenFeedbackText.setText("√");
            }
            isCorrect = true;
            marksScore.setText(currentPoints + "/5");
            increaseMarkDisp.setText("+ " + currentPoints);
        }
        else if(currentPoints != 0){
            redFeedbackText.setText("<html>X <br/>1 point deducted");
            if(currentPoints != 0){
                currentPoints--;
            }
        }
        else{
            redFeedbackText.setText("<html>X <br/> Points can no longer <br/> be earned from this <br/> question");
        }
    }//GEN-LAST:event_submitAnswerButtonActionPerformed

    //REARRANGING BUTTONS SECTION
    
    //MOVE DOWN BUTTONS
    private void OneDownButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneDownButton1ActionPerformed
        moveTextDown(0);
    }//GEN-LAST:event_OneDownButton1ActionPerformed

    private void OneDownButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneDownButton2ActionPerformed
        moveTextDown(1);
    }//GEN-LAST:event_OneDownButton2ActionPerformed

    private void OneDownButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneDownButton3ActionPerformed
        moveTextDown(2);
    }//GEN-LAST:event_OneDownButton3ActionPerformed
    
    //MOVE UP BUTTONS
    private void OneUpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneUpButton1ActionPerformed
        moveTextUp(1);
    }//GEN-LAST:event_OneUpButton1ActionPerformed

    private void OneUpButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneUpButton2ActionPerformed
        moveTextUp(2);
    }//GEN-LAST:event_OneUpButton2ActionPerformed

    private void OneUpButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneUpButton3ActionPerformed
        moveTextUp(3);
    }//GEN-LAST:event_OneUpButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ParsonsProblemsUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParsonsProblemsUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParsonsProblemsUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParsonsProblemsUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParsonsProblemsUi().setVisible(true);
            }
        });
    }
    
    //used to set text fields when buttons are pressed and on window start
    private void setTextFields(){
        posOneTextField.setText(randomAnswerArray[0]);
        posTwoTextField.setText(randomAnswerArray[1]);
        posThreeTextField.setText(randomAnswerArray[2]);
        posFalseTextField.setText(randomAnswerArray[3]);
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
    
    //-----------------------------------------//
    //  ANSWER RANDOMISATION CODE (Draft)      //
    //-----------------------------------------//
    // will probably have to be moved to an intermediate jframe for when the test is loading/test is being initiallized
    
    String[] trueAnswerArray;
    String[] randomAnswerArray;
    
    private void initAnswers(){
        String[] trueAnswers = initTrueAnswers();
        String falseAnswer = initFalseAnswer();
        //the true answer array has the true order of the answer stored
        trueAnswerArray = new String[4];
        trueAnswerArray[0] = trueAnswers[0];
        trueAnswerArray[1] = trueAnswers[1];
        trueAnswerArray[2] = trueAnswers[2];
        trueAnswerArray[3] = falseAnswer;
        
        System.out.println(trueAnswerArray[0]);
        System.out.println(trueAnswerArray[1]);
        System.out.println(trueAnswerArray[2]);
        System.out.println(trueAnswerArray[3]);
        
        
        randomAnswerArray = new String[4];
        //randomAnswerArray = createRandomAnswerArray(trueAnswerArray);
        //temporary until randomisation is fixed
        randomAnswerArray[0] = trueAnswerArray[0];
        randomAnswerArray[1] = trueAnswerArray[2];
        randomAnswerArray[2] = trueAnswerArray[3];
        randomAnswerArray[3] = trueAnswerArray[1];
        
        System.out.println(randomAnswerArray[0]);
        System.out.println(randomAnswerArray[1]);
        System.out.println(randomAnswerArray[2]);
        System.out.println(randomAnswerArray[3]);
    }
    
    private String[] initTrueAnswers(){
        String[] trueAnswerList = new String[3];
        //creates the correct answer list
        trueAnswerList[0] = "... \n if(2 < 3){";
        trueAnswerList[1] = "   return true";
        trueAnswerList[2] = "}";
        
        return trueAnswerList;
    }
    
    private String initFalseAnswer(){
        String[] falseAnswerList = new String[6];
        
        falseAnswerList[0] = "... \n if (2 > 3){";
        falseAnswerList[1] = "banana";
        falseAnswerList[2] = "Strawberry";
        falseAnswerList[3] = "pineapple";
        falseAnswerList[4] = "Pie";
        falseAnswerList[5] = "Cake";
        
        String falseAnswer = getRandomFalseAnswer(falseAnswerList);
        return falseAnswer;
    }
    
    // Finds out how long the input list is, and gives a random false answer from the provided list
    private String getRandomFalseAnswer(String[] inFalseAnsList){
        //count the length of inFalseAnsList
        int length = inFalseAnsList.length;
        System.out.println("The length of the false answer array is : " + length);
        
        //picks a random number out of the list and put that into string and return
        int randomNumber = (int)(Math.random() * length);
        System.out.println("The random number picked is : " + randomNumber);
        String falseAnswer = inFalseAnsList[randomNumber];
        return falseAnswer;
    }
    
    //random code not working atm
    /*
    private String[] createRandomAnswerArray(String[] inTrueAnswerArray){
        //get the list length of the input true answer array
        int length = inTrueAnswerArray.length;
        int[] tempArray = new int[4];
    
        String[] randomAnswerTempArray = new String[4];
        
        //randomly place the randomAnswerArrayTemp strings in the 4 textfields 
        //by randomly selecting one of the four answers and placing them in the
        int i = 0;
        while(i < 4){
            System.out.println(i);
            int randomNumber = (int)(Math.random() * length);
            System.out.println(randomNumber);
            boolean result = Arrays.stream(tempArray).anyMatch(inTrueAnswerArray[randomNumber]::equals);
            if (result) {
		System.out.println(inTrueAnswerArray[randomNumber] + " taken by algorithm");
                randomAnswerTempArray[i] = inTrueAnswerArray[randomNumber];
            }
            
            System.out.println(inTrueAnswerArray[randomNumber]);
            i++;
        }
        return randomAnswerTempArray;
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AnswerPanel;
    private javax.swing.JPanel ButtonPanel;
    private javax.swing.JPanel MarkingPanel;
    private javax.swing.JButton OneDownButton1;
    private javax.swing.JButton OneDownButton2;
    private javax.swing.JButton OneDownButton3;
    private javax.swing.JButton OneUpButton1;
    private javax.swing.JButton OneUpButton2;
    private javax.swing.JButton OneUpButton3;
    private javax.swing.JLabel QNoDisp;
    private javax.swing.JLabel QTextDisp;
    private javax.swing.JPanel QuestionInfoPanel;
    private javax.swing.JPanel answerBlock1;
    private javax.swing.JPanel answerBlock2;
    private javax.swing.JPanel answerBlock3;
    private javax.swing.JPanel answerBlock4;
    private javax.swing.JLabel greenFeedbackText;
    private javax.swing.JLabel increaseMarkDisp;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel labelFalse;
    private javax.swing.JLabel marksLabel;
    private javax.swing.JLabel marksScore;
    private javax.swing.JTextField posFalseTextField;
    private javax.swing.JTextField posOneTextField;
    private javax.swing.JTextField posThreeTextField;
    private javax.swing.JTextField posTwoTextField;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel redFeedbackText;
    private javax.swing.JButton submitAnswerButton;
    // End of variables declaration//GEN-END:variables
}
