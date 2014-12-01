package views;
 
import controller.OverallControllerCallback;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import model.ExerciseHolder;
import model.Exercises;
import model.Trainee;

/**
 *
 * @author Rawsome
 */
public class ProgressAndGoalView 
{
    private final JFrame swingFrame;
    private final OverallControllerCallback myController;
    private TableView<Exercises> tableView;
    private StackedBarChart<String,Number> stackedBarChart;
    private ObservableList<XYChart.Data> goalDataList;
    private ObservableList<XYChart.Data> recoDataList;
    
    public ProgressAndGoalView(OverallControllerCallback inController)
    {
        myController = inController;
        swingFrame =new JFrame("Progress and Goal View");
        final JFXPanel fxPanel = new JFXPanel();
        swingFrame.add(fxPanel);
        swingFrame.setSize(900,450);
        swingFrame.setVisible(false);
        swingFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            Platform.runLater(() -> {
                this.initialize(fxPanel);
            }); 
    }
    
    public void setVisibility(boolean input)
    {
        swingFrame.setVisible(input);
    }
    
    public boolean isVisible()
    {
        return swingFrame.isVisible();
    }
    
    private void initialize(JFXPanel inputPanel) {
        Group root = new Group();
        tableView = new TableView<>();
        tableView.setEditable(true);
        
        TableColumn<Exercises,String> exerNameCol = new TableColumn<>("Work Out Names");
        exerNameCol.setCellValueFactory(cellData -> cellData.getValue().exerNameProperty());
        TableColumn<Exercises,Number> actualRepCol = new TableColumn<>("Reps");
        actualRepCol.setCellValueFactory(cellData -> cellData.getValue().lastRepsProperty());
        TableColumn<Exercises,Number> actualSetCol = new TableColumn<>("Sets");
        actualSetCol.setCellValueFactory(cellData -> cellData.getValue().lastSetsProperty());
        TableColumn<Exercises,Number> goalRepCol = new TableColumn<>("Goal Reps");
        goalRepCol.setCellValueFactory(cellData -> cellData.getValue().goalRepsProperty());
        TableColumn<Exercises,Number> goalSetCol = new TableColumn<>("Goal Sets");
        goalSetCol.setCellValueFactory(cellData -> cellData.getValue().goalSetsProperty());
        
        Callback<TableColumn<Exercises, Number>, TableCell<Exercises, Number>> cellFactory =
            new Callback<TableColumn<Exercises, Number>, TableCell<Exercises, Number>>() {
                @Override
                public TableCell call(TableColumn p) {
                    EditingCell cellToReturn = new EditingCell();
                    cellToReturn.setAlignment(Pos.CENTER);
                    return cellToReturn;
                }
            };
        
        goalRepCol.setCellFactory(cellFactory);
        goalSetCol.setCellFactory(cellFactory);
        actualRepCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.10));
        actualSetCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.10));
        goalSetCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        goalRepCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        tableView.setPrefWidth(400);
        
        Trainee currentTrainee = myController.getCurTrainee();
        ExerciseHolder currentExerciseHolder = currentTrainee.getExerciseHolder();
        
        ObservableList<Exercises> exerciseList = FXCollections.observableArrayList(currentExerciseHolder.getAllExercises());
        tableView.setItems(exerciseList);
        tableView.getColumns().addAll(exerNameCol, actualRepCol, goalRepCol, actualSetCol, goalSetCol);
        tableView.getSelectionModel().select(0);
        
        //--- Prepare StackedBarChart
        Exercises currExer = tableView.getSelectionModel().getSelectedItem();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
                "Sets", 
                "Reps")));
        yAxis.setLabel("How Many");
        
        goalDataList = FXCollections.observableArrayList(
                        new XYChart.Data("Sets",0),
                        new XYChart.Data("Reps",0));

        XYChart.Series series1 = new XYChart.Series(goalDataList);
        series1.setName("Goal");
        
        recoDataList = FXCollections.observableArrayList(
                        new XYChart.Data("Sets",0),
                        new XYChart.Data("Reps",0));
        XYChart.Series series2 = new XYChart.Series(recoDataList);
        series2.setName("Achieved");
        
        stackedBarChart = new StackedBarChart<>(xAxis,yAxis);
        stackedBarChart.getData().addAll(series2,series1);
        stackedBarChart.setTitle("Exercise: " + currExer.getExerName());
        
        reCalcStackedBar();
        //--- Add change listener and rebind graph data to the table row selected.
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> reCalcStackedBar());
        
        HBox hBox = new HBox();
        hBox.setSpacing(8);
        hBox.getChildren().addAll(tableView,stackedBarChart);
        
        root.getChildren().add(hBox);
        inputPanel.setScene(new Scene(root,900,450));
    }
    
    public final void reCalcStackedBar()
    {
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() == null) 
        {
            tableView.getSelectionModel().select(0);
        }
            
        Exercises tmpExer = tableView.getSelectionModel().getSelectedItem();
        goalDataList.get(0).YValueProperty().bind(
                Bindings.max(tmpExer.goalSetsProperty().subtract(tmpExer.lastSetsProperty()), 0));
        goalDataList.get(1).YValueProperty().bind(
                Bindings.max(tmpExer.goalRepsProperty().subtract(tmpExer.lastRepsProperty()), 0));
        recoDataList.get(0).YValueProperty().bind(tmpExer.lastSetsProperty());
        recoDataList.get(1).YValueProperty().bind(tmpExer.lastRepsProperty());
        stackedBarChart.setTitle("Exercise: " + tmpExer.getExerName());
    }
    
    public void rePopulateData()
    {
        Trainee currentTrainee = myController.getCurTrainee();
        ExerciseHolder currentExerciseHolder = currentTrainee.getExerciseHolder();
        
        ObservableList<Exercises> exerciseList = FXCollections.observableArrayList(currentExerciseHolder.getAllExercises());
        tableView.setItems(exerciseList);
        
        reCalcStackedBar();
    }
    
    /*
    The following class of EditingCell is taken from website with slight modification.
    http://java-buddy.blogspot.com/2013/02/create-dynamic-stackedbarchart-of.html
    */
    class EditingCell extends TableCell<Exercises, Number> {
         
        private TextField textField;
         
        public EditingCell() {}
         
        @Override
        public void startEdit() {
             
            super.startEdit();
             
            if (textField == null) {
                createTextField();
            }
             
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }
         
        @Override
        public void cancelEdit() {
            super.cancelEdit();
             
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
         
        @Override
        public void updateItem(Number item, boolean empty) {
            super.updateItem(item, empty);
             
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
         
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                 
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        try
                        {
                            commitEdit(Integer.parseInt(textField.getText()));
                        }
                        catch(NumberFormatException e)
                        {
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.WINDOW_MODAL);
                            VBox box = new VBox();
                            box.setSpacing(10);
                            box.setPadding(new Insets(0,20,10,20));
                            box.getChildren().addAll(new Text("Integers Only Please!"));
                            dialogStage.setScene(new Scene(box,box.getPrefWidth(),box.getPrefHeight()));
                            dialogStage.show();
                        }
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
         
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
