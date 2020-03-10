/*
 * Load Screen
 * Multi-purpose loading file intended for use with both the functions of loading a test and loading a save file
 */
package parsonsProblems;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author UP815458
 */
public class LoadTestFile extends javax.swing.JFrame {
    
    static int mode;
    String path;
    
    //datasets
    static String testName;
    static int testLength;
    static String[] qTextArray = new String[10];
    static String[][] trueAnswers = new String[10][10];
    static String[][] falseAnswers = new String[10][10];
    //The 2 below will only be modified if loading a save file
    static boolean[] completedQs;
    static int[] marks;
    static int[] currentPoints;
    /**
     * Creates new form LoadTestFile
     * @param setMode the parameter passed in to say whether or not this instance is for loading tests or previous test sessions
     * 0 for loading tests to start, 1 for loading previous test sessions
     */
    public LoadTestFile(int setMode) {
        mode = setMode;
        initComponents();
        initializeTable();
        errorFeedbackText.setText("");
        if(mode == 0){
            titleLabel.setText("Load a new Test");
            descriptionLabel.setText("Select a test from the list to begin");
            path = "TESTDATA" + File.separator + "Tests" + File.separator;
            loadButton.setText("Load this test!");
            completedQs = new boolean[10];
            for (int i = 0; i < 10; i++) {
                completedQs[i] = true;
            }
            marks = new int[]{0,0,0,0,0,0,0,0,0,0};
            currentPoints = new int[]{5,5,5,5,5,5,5,5,5,5};
            getFileNamesOnTable();
        }
        if(mode == 1){
            titleLabel.setText("Load A Save");
            descriptionLabel.setText("Select a save from the list below");
            path = "TESTDATA" + File.separator + "Sessions" + File.separator;
            loadButton.setText("Load this save!");
            marks = new int[10];
            completedQs = new boolean[10];
            currentPoints = new int[10];
            getFileNamesOnTable();
        }
        if(mode == 2){
            //edit an already existing test
            titleLabel.setText("Load a Test");
            descriptionLabel.setText("Select a test from the list to begin editing");
            path = "TESTDATA" + File.separator + "Tests" + File.separator;
            loadButton.setText("Edit this test!");
            getFileNamesOnTable();
        }
        
    }
    
    private void initializeTable(){
        //initialize the string that will be put into the column title
        String columnString;
        if(mode == 0){
            columnString = "Load test";
        }
        else{
            columnString = "Save name";
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        loadTable.setModel(tableModel);
        //Disables the ability for the user to edit the fields
        loadTable.setDefaultEditor(Object.class, null);
        tableModel.addColumn(columnString);
    }
    
    private void getFileNamesOnTable(){
        File dir = new File(path);

        Collection<String> files = new ArrayList<String>();

        if(dir.isDirectory()){
            File[] listOfFiles = dir.listFiles();
            
            for(File file : listOfFiles){
                if(file.isFile()) {
                    files.add(file.getName());
                }
            }
        }
        String[] fileNames = files.toArray(new String[]{});
        DefaultTableModel m = (DefaultTableModel)loadTable.getModel();
        
        for(int i = 0; i < fileNames.length; i++){
            m.addRow(new Object[]{fileNames[i]});
        }
    }
    
    private static void load(String inPath)throws IOException{
        String fileLoc = inPath;
        try{
            File f = new File(fileLoc);
           
            BufferedReader b = new BufferedReader(new FileReader(f));
            
            String readLine = "";
            
            System.out.println("Reading file...");
            String[] qTextTemp = new String[10];
            while((readLine = b.readLine()) != null){
                if(readLine.contains("//")){}
                else if(readLine.contains("/n/")){
                    readLine = b.readLine();
                    testName = readLine;
                    //System.out.println(testName);
                }
                //Takes the length number for shortening the test
                //will be for the question select screen if the test has been condensed
                else if(readLine.contains("/l/")){
                    readLine = b.readLine();
                    try{
                        testLength = Integer.parseInt(readLine);
                        System.out.println("THE LENGTH IS : " + testLength);
                    }
                    catch(NumberFormatException e){
                        System.out.println(e);
                        System.out.println("Error detected, proceeding with test length 10...");
                        testLength = 10;
                    }
                    
                    
                }
                // Description
                else if(readLine.contains("/d/")){
                    for(int i = 0; i < 10; i++){
                        readLine = b.readLine();
                        //System.out.println(readLine);
                        qTextArray[i] = readLine;
                        //System.out.println(qTextArray[i]);
                    }
                }
                // True answers
                else if(readLine.contains("/t/")){
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            readLine = b.readLine();
                            //System.out.println(readLine);
                            trueAnswers[i][j] = readLine;
                            //System.out.println(trueAnswers[i][j]);
                        }
                    }
                }
                // False Answers
                else if(readLine.contains("/f/")){
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            readLine = b.readLine();
                            //System.out.println(readLine);
                            falseAnswers[i][j] = readLine;
                            //System.out.println(falseAnswers[i][j]);
                        }
                    }
                }
                //System.out.print(testLength);
                if(testLength < 10){
                    //System.out.print("#TRIGGERED");
                    for (int i = 0; i < testLength; i++) {
                        completedQs[i] = false;
                        //System.out.print(completedQs[i]);
                    }
                }
                if(mode == 1){
                    // Completed booleans
                    if(readLine.contains("/c/")){
                        for(int i = 0;i < 10;i++){
                            readLine = b.readLine();
                            //System.out.println(readLine);
                            if(readLine.contains("true")){
                                completedQs[i] = true;
                            }
                            else{
                                completedQs[i] = false;
                            }
                        }
                    }
                    // Question marks
                    else if(readLine.contains("/m/")){
                        for(int i = 0;i < 10;i++){
                            readLine = b.readLine();
                            //System.out.println(readLine);
                            marks[i] = Integer.parseInt(readLine);
                            //System.out.println(marks[i]);
                        }
                    }
                    // Current Points
                    else if(readLine.contains("/currentPoints/")){
                        for(int i = 0; i < 10; i++){
                            readLine = b.readLine();
                            //System.out.println(readLine);
                            currentPoints[i] = Integer.parseInt(readLine);
                            //System.out.println(currentPoints[i]);
                        }
                    }
                }
            }
            
                
        } catch (IOException e) {
            e.printStackTrace();
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
        descriptionLabel = new javax.swing.JLabel();
        loadScrollPane = new javax.swing.JScrollPane();
        loadTable = new javax.swing.JTable();
        loadButton = new javax.swing.JButton();
        errorFeedbackText = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Load a test file");

        descriptionLabel.setText("Select a test from the list to begin");

        loadTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        loadTable.setColumnSelectionAllowed(true);
        loadScrollPane.setViewportView(loadTable);
        loadTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        loadButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loadButton.setText("load Text goes here");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        errorFeedbackText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        errorFeedbackText.setForeground(new java.awt.Color(255, 0, 0));
        errorFeedbackText.setText("Error Feedback here");

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
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel)
                            .addComponent(quitButton))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(errorFeedbackText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addGap(7, 7, 7)
                .addComponent(loadScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorFeedbackText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(quitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        //gets the string from the selected row and attaches it to the path string for use in load(inPath); function
        int row = loadTable.getSelectedRow();
        String filename = "INVALID";
        if(row != -1){
            filename = (String) loadTable.getModel().getValueAt(row, 0);
            System.out.println("Filename is : " + filename);
        }
        else{
            errorFeedbackText.setText("You need to select something from the table!");
        }
        
        //the if statement is only here so that it doesnt try loading a blank filename (if no file has been selected)
        if(!"INVALID".equals(filename)){
            String filePath = path + filename;
            System.out.println("Loading...\n" + filePath);
            
            try {
                load(filePath);
            } catch (IOException ex) {
                Logger.getLogger(LoadTestFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("loading successful! Launching test...");
            if (mode == 0 || mode == 1) {
                QuestionSelect qs = new QuestionSelect(testName, testLength, qTextArray, trueAnswers, falseAnswers,  marks, completedQs, currentPoints);
                qs.setVisible(true);
            }
            if (mode == 2) {
                CreateATestParson ct = new CreateATestParson(0, testName, qTextArray, trueAnswers, falseAnswers);
                ct.setVisible(true);
            }
            this.setVisible(false);
            this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
            this.dispose();
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoadTestFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadTestFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadTestFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadTestFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoadTestFile(mode).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel errorFeedbackText;
    private javax.swing.JButton loadButton;
    private javax.swing.JScrollPane loadScrollPane;
    private javax.swing.JTable loadTable;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
