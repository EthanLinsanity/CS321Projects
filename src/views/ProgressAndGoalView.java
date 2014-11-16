package views;
 
import controller.OverallControllerCallback;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javax.swing.JFrame;
import model.ExerciseHolder;
import model.Exercises;
import model.Trainee;

 

public class ProgressAndGoalView 
{
    int i_barbell = 8;
    private final JFrame swingFrame;
    private final OverallControllerCallback myController;
    private TableView<Exercises> tableView;
    
    public ProgressAndGoalView(OverallControllerCallback inController)
    {
        myController = inController;
        swingFrame =new JFrame("Progress and Goal View");
        final JFXPanel fxPanel = new JFXPanel();
        swingFrame.add(fxPanel);
        swingFrame.setSize(760,450);
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
    
    private void initialize(JFXPanel inputPanel) {
        Group root = new Group();
        tableView = new TableView<>();
        tableView.setEditable(true);
        
        TableColumn<Exercises,String> exerNameCol = new TableColumn<>("Work Out Names");
        exerNameCol.setCellValueFactory(cellData -> cellData.getValue().pExerNameProperty());
        
        TableColumn<Exercises,Number> goalRepCol = new TableColumn<>("Goal Reps");
        goalRepCol.setCellValueFactory(cellData -> cellData.getValue().pGoalRepsProperty());
        
        TableColumn<Exercises,Number> goalSetCol = new TableColumn<>("Goal Sets");
        goalSetCol.setCellValueFactory(cellData -> cellData.getValue().pGoalSetsProperty());
        
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
        
        
        populateData();
        
        tableView.getColumns().addAll(exerNameCol, goalRepCol, goalSetCol);
        
        HBox hBox = new HBox();
        hBox.setSpacing(8);
        hBox.getChildren().addAll(tableView);
        
        root.getChildren().add(hBox);
        inputPanel.setScene(new Scene(root,750,400));
        
        
//        TableView<XYChart.Data> tableView = new TableView<>();  
//        ObservableList<XYChart.Data> dataList =
//            FXCollections.observableArrayList(
////                new XYChart.Data("BARBELLCURL", i_barbell),         //exerToDisp.getActualSet()  Changes the Sets ROW in the table
////                new XYChart.Data("BARBELL DEADLIFT", i_barbell),
////                new XYChart.Data("BARBELL ROW", i_barbell),
////                new XYChart.Data("CALF RAISES", i_barbell),
////                new XYChart.Data("CRUNCHES", i_barbell),
////                new XYChart.Data("DUMBELL RAISES", i_barbell),
////                new XYChart.Data("FULL SQUAT", i_barbell),
////                new XYChart.Data("TRICEPS", i_barbell),
////                new XYChart.Data("CALF RAISES", i_barbell),
////                new XYChart.Data("SHOULDER PRESS", i_barbell),
////                new XYChart.Data("SITUPS", i_barbell),
//                new XYChart.Data("LUNGES", 15),
//                new XYChart.Data("Sets",5),
//                new XYChart.Data("Reps",7));                    //Changs the Reps ROW in the table
//
//        tableView.setEditable(true);
////        tableView.setFixedCellSize(40);     //sets the size of the cell downward
//        Callback<TableColumn, TableCell> cellFactory =
//            new Callback<TableColumn, TableCell>() {
//                @Override
//                public TableCell call(TableColumn p) {
//                    return new EditingCell();
//                }
//            };
//         
//        TableColumn columnWorkout = new TableColumn("WorkOut");
//        columnWorkout.setCellValueFactory(
//            new PropertyValueFactory<XYChart.Data,String>("XValue"));
//         
//        TableColumn columnSets = new TableColumn("Sets");
//        columnSets.setCellValueFactory(
//            new PropertyValueFactory<XYChart.Data,Number>("YValue"));
//        
//        TableColumn columnReps = new TableColumn("Reps");
//        columnReps.setCellValueFactory(
//            new PropertyValueFactory<XYChart.Data,Number>("YValue"));
//         
//        //--- Add for Editable Cell of Value field, in Number
////        columnSets.setCellFactory(cellFactory);
//        columnReps.setCellFactory(cellFactory);
//         
////        columnSets.setOnEditCommit(
////            new EventHandler<TableColumn.CellEditEvent<XYChart.Data, Number>>() {
////                 
////                @Override public void handle(TableColumn.CellEditEvent<XYChart.Data, Number> t) {
////                    ((XYChart.Data)t.getTableView().getItems().get(
////                            t.getTablePosition().getRow())).setYValue(t.getNewValue());
////                }
////            });
//        columnReps.setOnEditCommit(
//            new EventHandler<TableColumn.CellEditEvent<XYChart.Data, Number>>() {
//
//                @Override public void handle(TableColumn.CellEditEvent<XYChart.Data, Number> t) {
//                    ((XYChart.Data)t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())).setYValue(t.getNewValue());
//                }
//            });
//        
//        
//        //--- Prepare StackedBarChart
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("A Screen Like This For Every Type of Exercise");
//        xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
//                "Sets", 
//                "Reps")));
//        yAxis.setLabel("How Many");
//        final StackedBarChart<String,Number> stackedBarChart = new StackedBarChart<>(xAxis,yAxis);
//        stackedBarChart.setTitle("Exercise: Ab Crunches");
//        XYChart.Series series1 = new XYChart.Series(dataList);
//        series1.setName("Actual");
//         
//        //Series 2--the goals of the workout; the bar graph in the back
//        int i_barbellGoal = (15-i_barbell);
//        
//        XYChart.Series<String,Number> series2 = new XYChart.Series();
//        series2.setName("Goals");
//        series2.getData().add(new XYChart.Data("Sets", i_barbellGoal));  //Changes the goal graph CONSTANT
//        series2.getData().add(new XYChart.Data("Reps", 30));
//         
//        stackedBarChart.getData().addAll(series1, series2);
//         
//        //--- Prepare TableView
//        tableView.setItems(dataList);
//        tableView.getColumns().addAll(columnWorkout, columnSets, columnReps);
//         
//        HBox hBox = new HBox();
//        hBox.setSpacing(8);
//        hBox.getChildren().addAll(tableView, stackedBarChart);
//         
//        root.getChildren().add(hBox);
         
//        inputPanel.setScene(new Scene(root,750,400));
    }
    
    public void populateData()
    {
        Trainee currentTrainee = myController.getCurTrainee();
        ExerciseHolder currentExerciseHolder = currentTrainee.getExerciseHolder();
        
        ObservableList<Exercises> dataList = FXCollections.observableArrayList(currentExerciseHolder.getAllExercises());
        tableView.setItems(dataList);   
    }
    
    
    
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
                        commitEdit(Double.parseDouble(textField.getText()));
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
