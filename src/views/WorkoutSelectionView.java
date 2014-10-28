/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ExerciseHolder;
/**
 *
 * @author Ben
 */

/**
   Workout selection view provides user with exercises to pick from
*/
public class WorkoutSelectionView implements ActionListener
{
    
    //declare container variables
    private ExerciseHolder h;
    private final JFrame appFrame;
    private final JPanel topPanel;
     private final JPanel exercisePanel;
    private final JComboBox exercises;
    private final JLabel viewname;
  
   public WorkoutSelectionView()
   {
      //create components
      appFrame = new JFrame("Workout Selection View");
      appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      viewname = new JLabel("Select Exercise");
      topPanel = new JPanel();
      exercisePanel = new JPanel();
           

exercises = new JComboBox();

      //initialize menu and register action listeners
      //exercises.addEventHandler();
      exercises.setVisible(true);
      //add Components to the panel
      exercisePanel.setLayout(new BorderLayout());
      topPanel.add(viewname, BorderLayout.NORTH);  
      exercisePanel.add(exercises, BorderLayout.SOUTH);
          
       exercises.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Exercise", "Pushups", "Crunches", "Pullups", "Squats" }));
        exercises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //exercisesActionPerformed(evt);
            }
        });
        
      
      appFrame.add(topPanel, BorderLayout.NORTH);
      //appFrame.add(exercisePanel, BorderLayout.);
      appFrame.add(exercisePanel, BorderLayout.SOUTH);
      appFrame.pack();
      
      appFrame.setSize(300, 200);
     appFrame.setVisible(true);//Display the window
   }

   /**
      display the exercises in the ETracker application
      @param output the text that will be "spoken"
   */
   public void displayView(String output)
   {
       //
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /**
      Loops reading user input and passes the input to the
      Connection object's methods dial, record or hangup.
      @param c the connection that connects this phone to the
      voice mail system
   
   public void run(MailSystemModel c)
   {
     this.c = c;//
   }
   
   @Override
    public void actionPerformed(ActionEvent e) {
        
        String input = e.getActionCommand(); //get input from clicked button.
        System.out.print(input);//for debugging
        if (input == null) ;//ignore
         
            ;//c.record(input);
    }*/
}
