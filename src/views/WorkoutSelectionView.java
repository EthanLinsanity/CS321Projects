/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;
import controller.OverallControllerCallback;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ExerciseNames;
/**
 *
 * @author Ben
 */

/**
   Workout selection view provides user with exercises to pick from
*/
public class WorkoutSelectionView
{
    
    //declare container variables
    private final JFrame appFrame;
    private final JPanel topPanel;
     private final JPanel exercisePanel;
    private final JComboBox comBoxExercises;
    private final JLabel viewname;
    private final OverallControllerCallback myController;
  
   public WorkoutSelectionView(OverallControllerCallback inputController)
   {
      myController = inputController;
      appFrame = new JFrame("Workout Selection View");
      appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      viewname = new JLabel("Select Exercise");
      topPanel = new JPanel();
      exercisePanel = new JPanel();
      comBoxExercises = new JComboBox();
      initComboBox();
      comBoxExercises.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent selected) {
              myController.exerSelectionComplete(comBoxExercises.getSelectedItem().toString());
          }
      });
      exercisePanel.setLayout(new BorderLayout());
      topPanel.add(viewname, BorderLayout.NORTH);  
      exercisePanel.add(comBoxExercises, BorderLayout.SOUTH);
      
      appFrame.add(topPanel, BorderLayout.NORTH);
      //appFrame.add(exercisePanel, BorderLayout.);
      appFrame.add(exercisePanel, BorderLayout.SOUTH);
      appFrame.pack();
      
      appFrame.setSize(300, 200);
      appFrame.setLocationRelativeTo(null);
      appFrame.setVisible(true);//Display the window
      
      appFrame.addWindowListener(new WindowAdapter()
      {
          @Override
          public void windowClosing(WindowEvent windowEvent)
          {
              myController.showMainView();
          }
      });
   }
      
   private void initComboBox()
   {
       ArrayList<String> names = ExerciseNames.getAllNames();
       names.stream().forEach((n) -> {
            comBoxExercises.addItem(n);
        });
   }
   
   public void closeThisView()
   {
       appFrame.setVisible(false);
       appFrame.dispose();
   }
}
