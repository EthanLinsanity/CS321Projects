/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import controller.OverallControllerCallback;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import model.Exercises;

/**
 *
 * @author imm0022
 */
public class ExerciseDescriptionView extends javax.swing.JFrame {
    /**
     * Creates new form ExerciseDescrptionView
     */
    private OverallControllerCallback myController;
    private Exercises exerToDisp;
    
    public ExerciseDescriptionView(OverallControllerCallback ctl, Exercises inExercise) {
        myController = ctl;
        exerToDisp = inExercise;
        initComponents();
        this.setSize(this.getPreferredSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        fillContents();
        txtExerciseDescription.setEditable(false);
        txtExerciseDescription.setLineWrap(true);
        txtExerciseDescription.setWrapStyleWord(true);
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                GoHomeButtonActionPerformed(null);
            }
        });
    }
    
    private void fillContents()
    {        
        lblExerciseName.setText(exerToDisp.getExerName());
        lblPrevReps.setText(Integer.toString(exerToDisp.getLastReps()));
        lblPrevSets.setText(Integer.toString(exerToDisp.getLastSets()));
        
        txtCurReps.setText(Integer.toString(exerToDisp.getActualReps()));
        txtCurSets.setText(Integer.toString(exerToDisp.getActualSets()));
        
        txtCurReps.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField text = (JTextField)input;
                String value = text.getText().trim();
                try {
                    Integer.parseInt(value);
                    txtCurReps.setText(value);
                } catch (NumberFormatException e) {
                   txtCurReps.setText("0");
                   return false;
                }
                return true;
            }
        });
        txtCurSets.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField text = (JTextField)input;
                String value = text.getText().trim();
                try {
                    Integer.parseInt(value);
                    txtCurSets.setText(value);
                } catch (NumberFormatException e) {
                   txtCurSets.setText("0");
                   return false;
                }
                return true;
            }
        });
        
        String imageFilePath = exerToDisp.getPicturePath();
        String descriptionPath = exerToDisp.getDesciption();
        try {
            ImageIcon ii=new ImageIcon(
                    scaleImage(lblExercisePic.getSize().height, 
                               lblExercisePic.getSize().width, 
                               ImageIO.read(
                                    ClassLoader.getSystemResource(imageFilePath)
                                )
                    )
            );
            lblExercisePic.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(descriptionPath)))) 
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            txtExerciseDescription.setText(everything);
            txtExerciseDescription.setCaretPosition(0);
        }
        catch(Exception e){
            System.out.println("bufferedReader in ExerciseDescription has crashed.... oh NOOOOOOOOOOO");   
        }
        
    }

    private BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
        BufferedImage bi;
        bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();
        return bi;
    }
    
    private void saveUserInputs()
    {
        int repNum = Integer.parseInt(txtCurReps.getText().trim());
        int setNum = Integer.parseInt(txtCurSets.getText().trim());
        exerToDisp.setActualReps(0);
        exerToDisp.setActualSets(0);
        exerToDisp.setLastReps(repNum);
        exerToDisp.setLastSets(setNum);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DescriptionLabel = new javax.swing.JLabel();
        PreviousWorkOutLabel = new javax.swing.JLabel();
        PreviousSetLabel = new javax.swing.JLabel();
        PreviousRepsLabel = new javax.swing.JLabel();
        CurrentRepsLabel1 = new javax.swing.JLabel();
        CurrentSetLabel = new javax.swing.JLabel();
        PreviousWorkOutLabel1 = new javax.swing.JLabel();
        txtCurSets = new javax.swing.JTextField();
        txtCurReps = new javax.swing.JTextField();
        GoHomeButton = new javax.swing.JButton();
        lblExercisePic = new javax.swing.JLabel();
        lblExerciseName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExerciseDescription = new javax.swing.JTextArea();
        lblPrevSets = new javax.swing.JLabel();
        lblPrevReps = new javax.swing.JLabel();
        RecommendNextButton = new javax.swing.JButton();

        setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        DescriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DescriptionLabel.setText("Description:");

        PreviousWorkOutLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PreviousWorkOutLabel.setText("Previous WorkOut");

        PreviousSetLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PreviousSetLabel.setText("SETS");

        PreviousRepsLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PreviousRepsLabel.setText("REPS");

        CurrentRepsLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CurrentRepsLabel1.setText("REPS");

        CurrentSetLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CurrentSetLabel.setText("SETS");

        PreviousWorkOutLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PreviousWorkOutLabel1.setText("Current WorkOut");

        txtCurSets.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCurSets.setText("3");
        txtCurSets.setPreferredSize(new java.awt.Dimension(25, 25));

        txtCurReps.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCurReps.setText("20");
        txtCurReps.setPreferredSize(new java.awt.Dimension(25, 25));

        GoHomeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GoHomeButton.setText("GO HOME");
        GoHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoHomeButtonActionPerformed(evt);
            }
        });

        lblExercisePic.setToolTipText("");

        lblExerciseName.setText("Exercise Name");

        txtExerciseDescription.setColumns(20);
        txtExerciseDescription.setRows(5);
        jScrollPane1.setViewportView(txtExerciseDescription);

        lblPrevSets.setText("jLabel1");
        lblPrevSets.setPreferredSize(new java.awt.Dimension(25, 25));

        lblPrevReps.setText("jLabel1");
        lblPrevReps.setPreferredSize(new java.awt.Dimension(45, 45));

        RecommendNextButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RecommendNextButton.setText("RECOMMEND NEXT");
        RecommendNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecommendNextButtonActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(DescriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblExerciseName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblExercisePic, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CurrentSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCurSets, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PreviousSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrevSets, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(96, 96, 96)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(PreviousWorkOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(PreviousRepsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblPrevReps, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(RecommendNextButton))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(PreviousWorkOutLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(245, 245, 245)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(CurrentRepsLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtCurReps, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(GoHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblExerciseName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblExercisePic, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreviousWorkOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreviousSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreviousRepsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrevReps, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrevSets, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RecommendNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PreviousWorkOutLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CurrentSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CurrentRepsLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCurSets, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurReps, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GoHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void GoHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoHomeButtonActionPerformed
        myController.showMainView();
        saveUserInputs();
        this.dispose();
    }//GEN-LAST:event_GoHomeButtonActionPerformed

    private void RecommendNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecommendNextButtonActionPerformed
        saveUserInputs();
        Exercises newExerToDisp = myController.recommendNext(exerToDisp.getExerName());
        exerToDisp = newExerToDisp;
        fillContents();
    }//GEN-LAST:event_RecommendNextButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CurrentRepsLabel1;
    private javax.swing.JLabel CurrentSetLabel;
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.JButton GoHomeButton;
    private javax.swing.JLabel PreviousRepsLabel;
    private javax.swing.JLabel PreviousSetLabel;
    private javax.swing.JLabel PreviousWorkOutLabel;
    private javax.swing.JLabel PreviousWorkOutLabel1;
    private javax.swing.JButton RecommendNextButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExerciseName;
    private javax.swing.JLabel lblExercisePic;
    private javax.swing.JLabel lblPrevReps;
    private javax.swing.JLabel lblPrevSets;
    private javax.swing.JTextField txtCurReps;
    private javax.swing.JTextField txtCurSets;
    private javax.swing.JTextArea txtExerciseDescription;
    // End of variables declaration//GEN-END:variables
}
