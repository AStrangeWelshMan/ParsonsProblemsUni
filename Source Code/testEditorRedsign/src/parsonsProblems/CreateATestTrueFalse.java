/*
 * True/False Question creation tool 
 */
package parsonsProblems;

import javax.swing.table.DefaultTableModel;

/**
 * @author UP815458
 */
public class CreateATestTrueFalse extends javax.swing.JFrame {
    
    int qNo;
    static String testTitle;
    static String[][] trueAnswers;
    static String[][] falseAnswers;
    static String[] qTextArray;
    
    /**
     * This is used to start a brand new, blank test
     */
    public CreateATestTrueFalse() {
        initComponents();
        qNo = 0;
        testTitle = "";
        qTextArray = new String[10];
        trueAnswers = new String[10][10];
        falseAnswers = new String[10][10];
        
        //initialize feedback texts to blank
        resetFeedbackMessages();
        DefaultTableModel tableModelTrue = new DefaultTableModel();
        trueAnswerTable.setModel(tableModelTrue);
        tableModelTrue.addColumn("True Answers");
        //
        tableModelTrue.addRow(new Object[]{"DEFAULT TRUE ANSWER 1"});
        tableModelTrue.addRow(new Object[]{"DEFAULT TRUE ANSWER 2"});
        noOfTrueAnswers = 2;
        DefaultTableModel tableModelFalse = new DefaultTableModel();
        falseAnswerTable.setModel(tableModelFalse);
        tableModelFalse.addColumn("False Answers");
        noOfFalseAnswers = 0;
        
        //run once to initialize arrays
        addValuesToArrays();
        setupTables();
    }
    
    /**
     * Used when loading from a file or when returning from the AreYouSure frame
     * @param inQNo Question Number Input
     * @param inTestTitle Test Title input
     * @param inQTextArray Question description
     * @param inTrueAnswers True Answers Array
     * @param inFalseAnswers False Answers Array
     */
    public CreateATestTrueFalse(int inQNo, String inTestTitle, String[] inQTextArray, String[][] inTrueAnswers, String[][] inFalseAnswers){
        initComponents();
        qNo = inQNo;
        testTitle = inTestTitle;
        qTextArray = inQTextArray;
        trueAnswers = inTrueAnswers;
        falseAnswers = inFalseAnswers;
        //Initialize feedback texts to blank
        resetFeedbackMessages();
        noOfTrueAnswers = 0;
        noOfFalseAnswers = 0;
        
        testNameTF.setText(testTitle);
        
        descTextField.setText(qTextArray[qNo]);
        
        DefaultTableModel tableModelTrue = new DefaultTableModel();
        trueAnswerTable.setModel(tableModelTrue);
        tableModelTrue.addColumn("True Answers");
        
        DefaultTableModel tableModelFalse = new DefaultTableModel();
        falseAnswerTable.setModel(tableModelFalse);
        tableModelFalse.addColumn("False Answers");
        
        setupTables();
    }
    
    private void addValuesToArrays(){
        //Makes sure that even if the test is incomplete that it still can be saved without issue
        //TITLE
        String testString = testNameTF.getText();
        if("".equals(testString)){
            testTitle = "UNTITLED";
        }
        else{
            testTitle = testString;
        }
        
        //QUESTION DESCRIPTION
        String desc = descTextField.getText();
        //System.out.println(desc);
        if("".equals(desc)){
            qTextArray[qNo] = "UNDEFINED QUESTION " + (qNo+1) + " DESCRIPTION";
        }
        else{
            qTextArray[qNo] = desc;
        }
        int tempDesc = 0;
        while(tempDesc < 10){
            if(qTextArray[tempDesc] == null){
                qTextArray[tempDesc] = "UNDEFINED QUESTION " + (tempDesc+1) + " DESCRIPTION";
            }
            tempDesc++;
        }
        
        //TRUE VALUES
        int tempNoT = 0;
        String tempValTrue = "";
        while(tempNoT < 10){
            if(tempNoT < noOfTrueAnswers){
                tempValTrue = (String) trueAnswerTable.getModel().getValueAt(tempNoT, 0);
            }
            else{
                tempValTrue = "INVALID";
            }
            //System.out.println(tempValTrue);
            trueAnswers[qNo][tempNoT] = tempValTrue;
            //System.out.println(trueAnswers[qNo][tempNoT]);
            tempNoT++;
        }
        //Make all other questions valid
        for(int i = 0; i < 10; i++){
            if(trueAnswers[i][0] == null && trueAnswers[i][1] == null){
                    trueAnswers[i][0] = "DEFAULT TRUE ANSWER 1"; 
                    trueAnswers[i][1] = "DEFAULT TRUE ANSWER 2";
            }
            for(int j = 0; j < 10; j++){
                if(trueAnswers[i][j] == null){
                    trueAnswers[i][j] = "INVALID";
                }
            }
        }
        
        //FALSE VALUES
        int tempNoF = 0;
        String tempValFalse = "";
        while(tempNoF < 10){
            if(tempNoF < noOfFalseAnswers){
                tempValFalse = (String) falseAnswerTable.getModel().getValueAt(tempNoF, 0);
                if(tempValFalse == ""){
                    tempValFalse = "INVALID";
                }
            }
            else{
                tempValFalse = "INVALID";
            }
            falseAnswers[qNo][tempNoF] = tempValFalse;
            //System.out.println(falseAnswers[qNo][tempNoF]);
            tempNoF++;
        }
        //make all other questions valid
        for(int i = 0; i < qTextArray.length; i++){
            for(int j = 0; j < 10; j++){
                if(falseAnswers[i][j] == null){
                    falseAnswers[i][j] = "INVALID";
                }
            }
        }
    }
    
    private void resetFeedbackMessages(){
        feedbackTitle.setText("");
        feedbackNavText.setText("");
        feedbackQText.setText("");
        feedbackTrue.setText("");
        feedbackFalse.setText("");
    }
    
    private void setupTables(){
        testNameTF.setText(testTitle);
        descTextField.setText(qTextArray[qNo]);
        //Take away all the answers currently still in the tables
        DefaultTableModel t = (DefaultTableModel) trueAnswerTable.getModel();
        trueAnswerTable.getSelectionModel().clearSelection();
        //This next line terminates editing and saves the currently editing cell
        trueAnswerTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        while(noOfTrueAnswers != 0){
            t.removeRow(0);
            noOfTrueAnswers--;
        }
        DefaultTableModel f = (DefaultTableModel) falseAnswerTable.getModel();
        falseAnswerTable.getSelectionModel().clearSelection();
        //This next line terminates editing and saves the currently editing cell
        falseAnswerTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        while(noOfFalseAnswers != 0){
            f.removeRow(0);
            noOfFalseAnswers--;
        }
        //Then add the new values from the new question index
        for(int i = 0; i < 10; i++){
            if(!"INVALID".equals(trueAnswers[qNo][i])){
                t.addRow(new Object[]{trueAnswers[qNo][i]});
                noOfTrueAnswers++;
            }
            if(!"INVALID".equals(falseAnswers[qNo][i])){
                f.addRow(new Object[]{falseAnswers[qNo][i]});
                noOfFalseAnswers++;
            }
        }
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
        testTitleLabel = new javax.swing.JLabel();
        testNameTF = new javax.swing.JTextField();
        feedbackTitle = new javax.swing.JLabel();
        qNoDisp = new javax.swing.JLabel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        feedbackNavText = new javax.swing.JLabel();
        basicInfoPanel = new javax.swing.JPanel();
        descScrollPane = new javax.swing.JScrollPane();
        descTextField = new javax.swing.JTextArea();
        qDescLabel = new javax.swing.JLabel();
        feedbackQText = new javax.swing.JLabel();
        trueAnswerPanel = new javax.swing.JPanel();
        trueScrollPane = new javax.swing.JScrollPane();
        trueAnswerTable = new javax.swing.JTable();
        addRowTrue = new javax.swing.JButton();
        maxRowsLabel = new javax.swing.JLabel();
        delRowTrue = new javax.swing.JButton();
        feedbackTrue = new javax.swing.JLabel();
        falseAnswerPanel = new javax.swing.JPanel();
        falseScrollPane = new javax.swing.JScrollPane();
        falseAnswerTable = new javax.swing.JTable();
        addRowFalse = new javax.swing.JButton();
        delRowFalse = new javax.swing.JButton();
        maxRowsFalseLabel = new javax.swing.JLabel();
        feedbackFalse = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        titleLabel.setText("Create A Test");

        testTitleLabel.setText("Test Title :");

        feedbackTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        feedbackTitle.setForeground(new java.awt.Color(204, 0, 0));
        feedbackTitle.setText("feedback goes here");

        qNoDisp.setText("Question 1/10");

        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        feedbackNavText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        feedbackNavText.setForeground(new java.awt.Color(204, 0, 0));
        feedbackNavText.setText("Feedback Text goes here");

        basicInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descTextField.setColumns(20);
        descTextField.setRows(5);
        descScrollPane.setViewportView(descTextField);

        qDescLabel.setText("Question Description :");

        feedbackQText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        feedbackQText.setForeground(new java.awt.Color(204, 0, 0));
        feedbackQText.setText("Feedback goes here");

        javax.swing.GroupLayout basicInfoPanelLayout = new javax.swing.GroupLayout(basicInfoPanel);
        basicInfoPanel.setLayout(basicInfoPanelLayout);
        basicInfoPanelLayout.setHorizontalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qDescLabel)
                            .addComponent(feedbackQText))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        basicInfoPanelLayout.setVerticalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qDescLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedbackQText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        trueAnswerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        trueAnswerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "True Answers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        trueAnswerTable.setColumnSelectionAllowed(true);
        trueScrollPane.setViewportView(trueAnswerTable);
        trueAnswerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (trueAnswerTable.getColumnModel().getColumnCount() > 0) {
            trueAnswerTable.getColumnModel().getColumn(0).setResizable(false);
        }

        addRowTrue.setText("Add Answer");
        addRowTrue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowTrueActionPerformed(evt);
            }
        });

        maxRowsLabel.setText("<html>Maximum number <br> of true answers is 10");

        delRowTrue.setText("Delete Row");
        delRowTrue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delRowTrueActionPerformed(evt);
            }
        });

        feedbackTrue.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        feedbackTrue.setForeground(new java.awt.Color(204, 0, 0));
        feedbackTrue.setText("feedback goes here");

        javax.swing.GroupLayout trueAnswerPanelLayout = new javax.swing.GroupLayout(trueAnswerPanel);
        trueAnswerPanel.setLayout(trueAnswerPanelLayout);
        trueAnswerPanelLayout.setHorizontalGroup(
            trueAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueAnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trueAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trueAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(trueScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(trueAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(trueAnswerPanelLayout.createSequentialGroup()
                                .addComponent(addRowTrue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxRowsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(delRowTrue)))
                    .addComponent(feedbackTrue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trueAnswerPanelLayout.setVerticalGroup(
            trueAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueAnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trueAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(trueAnswerPanelLayout.createSequentialGroup()
                        .addComponent(trueScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addRowTrue))
                    .addComponent(maxRowsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delRowTrue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedbackTrue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        falseAnswerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        falseAnswerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "False Answers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        falseAnswerTable.setColumnSelectionAllowed(true);
        falseScrollPane.setViewportView(falseAnswerTable);
        falseAnswerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (falseAnswerTable.getColumnModel().getColumnCount() > 0) {
            falseAnswerTable.getColumnModel().getColumn(0).setResizable(false);
        }

        addRowFalse.setText("Add Row");
        addRowFalse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowFalseActionPerformed(evt);
            }
        });

        delRowFalse.setText("Delete Row");
        delRowFalse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delRowFalseActionPerformed(evt);
            }
        });

        maxRowsFalseLabel.setText("<html>You can have<br> up to 10 false<br>answers");

        feedbackFalse.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        feedbackFalse.setForeground(new java.awt.Color(204, 0, 0));
        feedbackFalse.setText("feedback goes here");

        javax.swing.GroupLayout falseAnswerPanelLayout = new javax.swing.GroupLayout(falseAnswerPanel);
        falseAnswerPanel.setLayout(falseAnswerPanelLayout);
        falseAnswerPanelLayout.setHorizontalGroup(
            falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(falseScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                        .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                                .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(delRowFalse)
                                    .addComponent(addRowFalse))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxRowsFalseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(feedbackFalse))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        falseAnswerPanelLayout.setVerticalGroup(
            falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(falseScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(falseAnswerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(falseAnswerPanelLayout.createSequentialGroup()
                        .addComponent(addRowFalse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delRowFalse))
                    .addComponent(maxRowsFalseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedbackFalse, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save & Finish");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
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
                        .addComponent(quitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(testTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(testNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(titleLabel)
                            .addComponent(feedbackTitle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(qNoDisp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(previousButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(feedbackNavText, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(basicInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trueAnswerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(falseAnswerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(feedbackNavText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(qNoDisp)
                                .addComponent(previousButton)
                                .addComponent(nextButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(testNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(testTitleLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(feedbackTitle)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(falseAnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trueAnswerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(basicInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(quitButton))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // BUTTONS
    // DELETE ROW BUTTONS
    private void delRowTrueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delRowTrueActionPerformed
        resetFeedbackMessages();
        DefaultTableModel m = (DefaultTableModel) trueAnswerTable.getModel();
        int row = trueAnswerTable.getSelectedRow();
        if(row != -1){
            if(noOfTrueAnswers > 2){
                m.removeRow(row);
                noOfTrueAnswers--;
            }
            else{
                feedbackTrue.setText("<html>There cannot be less<br> than 2 true answers");
            }
            //System.out.println(noOfTrueAnswers);
        }
        else{
            feedbackTrue.setText("<html>No row selected,<br>nothing has been deleted");
        }
        addValuesToArrays();
    }//GEN-LAST:event_delRowTrueActionPerformed

    private void delRowFalseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delRowFalseActionPerformed
        resetFeedbackMessages();
        DefaultTableModel m = (DefaultTableModel) falseAnswerTable.getModel();
        int row = falseAnswerTable.getSelectedRow();
        if(row != -1){
            m.removeRow(row);
            noOfFalseAnswers--;
            //System.out.println(noOfFalseAnswers);
        }
        else{
            feedbackFalse.setText("<html>No row selected,<br>nothing has been deleted");
        }
        addValuesToArrays();
    }//GEN-LAST:event_delRowFalseActionPerformed
    // ADD ROWS
    int noOfTrueAnswers;
    private void addRowTrueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowTrueActionPerformed
        resetFeedbackMessages();
        DefaultTableModel m = (DefaultTableModel) trueAnswerTable.getModel();
        if(noOfTrueAnswers != 10){
            m.addRow(new Object[]{""});
            noOfTrueAnswers++;
        }
        else{
            feedbackTrue.setText("<html>There can only be<br>a maximum of 10 true lines");
        }
    }//GEN-LAST:event_addRowTrueActionPerformed

    int noOfFalseAnswers;
    private void addRowFalseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowFalseActionPerformed
        resetFeedbackMessages();
        DefaultTableModel m = (DefaultTableModel) falseAnswerTable.getModel();
        if(noOfFalseAnswers != 10){
            m.addRow(new Object[]{""});
            noOfFalseAnswers++;
        }
        else{
            feedbackFalse.setText("<html>There can only be<br>a maximum of 10 false lines");
        }
    }//GEN-LAST:event_addRowFalseActionPerformed
    // TEST NAVIGATION BUTTONS
    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        resetFeedbackMessages();
        addValuesToArrays();
        if(qNo != 0){
            addValuesToArrays();
            qNo--;
            qNoDisp.setText("Question: " + (qNo+1) + "/10");
            setupTables();
        }
        else{
            feedbackNavText.setText("Cannot go any further back");
        }
    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        resetFeedbackMessages();
        addValuesToArrays();
        if(qNo != 9){
            addValuesToArrays();
            qNo++;
            qNoDisp.setText("Question: " + (qNo+1) + "/10");
            setupTables();
        }
        else{
            feedbackNavText.setText("Cannot go any further forward");
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        addValuesToArrays();
        AreYouSure ys = new AreYouSure(qNo, testTitle, qTextArray, trueAnswers, falseAnswers);
        ys.setVisible(true);
        ys.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        addValuesToArrays();
        TestCreationSummary tc = new TestCreationSummary(qNo, testTitle, qTextArray, trueAnswers, falseAnswers);
        tc.setVisible(true);
        tc.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CreateATestTrueFalse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateATestTrueFalse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateATestTrueFalse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateATestTrueFalse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateATestTrueFalse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRowFalse;
    private javax.swing.JButton addRowTrue;
    private javax.swing.JPanel basicInfoPanel;
    private javax.swing.JButton delRowFalse;
    private javax.swing.JButton delRowTrue;
    private javax.swing.JScrollPane descScrollPane;
    private javax.swing.JTextArea descTextField;
    private javax.swing.JPanel falseAnswerPanel;
    private javax.swing.JTable falseAnswerTable;
    private javax.swing.JScrollPane falseScrollPane;
    private javax.swing.JLabel feedbackFalse;
    private javax.swing.JLabel feedbackNavText;
    private javax.swing.JLabel feedbackQText;
    private javax.swing.JLabel feedbackTitle;
    private javax.swing.JLabel feedbackTrue;
    private javax.swing.JLabel maxRowsFalseLabel;
    private javax.swing.JLabel maxRowsLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel qDescLabel;
    private javax.swing.JLabel qNoDisp;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField testNameTF;
    private javax.swing.JLabel testTitleLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel trueAnswerPanel;
    private javax.swing.JTable trueAnswerTable;
    private javax.swing.JScrollPane trueScrollPane;
    // End of variables declaration//GEN-END:variables
}
