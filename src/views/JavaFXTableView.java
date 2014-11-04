package views;
 
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
 

public class JavaFXTableView 
{
     
    private TableView<XYChart.Data> tableView = new TableView<>();
    private ObservableList<XYChart.Data> dataList =
            FXCollections.observableArrayList(
                new XYChart.Data("Sets", 7),
                new XYChart.Data("Reps", 9));

    public void initialize(JFXPanel inputPanel) {
        Group root = new Group();
         
        tableView.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
            new Callback<TableColumn, TableCell>() {
                @Override
                public TableCell call(TableColumn p) {
                    return new EditingCell();
                }
            };
         
        TableColumn columnWorkout = new TableColumn("WorkOut");
        columnWorkout.setCellValueFactory(
            new PropertyValueFactory<XYChart.Data,String>("XValue"));
         
        TableColumn columnSets = new TableColumn("Sets");
        columnSets.setCellValueFactory(
            new PropertyValueFactory<XYChart.Data,Number>("YValue"));
        
        TableColumn columnReps = new TableColumn("Reps");
        columnReps.setCellValueFactory(
            new PropertyValueFactory<XYChart.Data,Number>("YValue"));
         
        //--- Add for Editable Cell of Value field, in Number
        columnSets.setCellFactory(cellFactory);
         
        columnSets.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<XYChart.Data, Number>>() {
                 
                @Override public void handle(TableColumn.CellEditEvent<XYChart.Data, Number> t) {
                    ((XYChart.Data)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setYValue(t.getNewValue());
                }
            });
        columnReps.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<XYChart.Data, Number>>() {

                @Override public void handle(TableColumn.CellEditEvent<XYChart.Data, Number> t) {
                    ((XYChart.Data)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setYValue(t.getNewValue());
                }
            });
        
        
        //--- Prepare StackedBarChart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Type of Exercise");
        xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
                "Sets", 
                "Reps")));
        yAxis.setLabel("How Many");
        final StackedBarChart<String,Number> stackedBarChart = new StackedBarChart<>(xAxis,yAxis);
        stackedBarChart.setTitle("Your Progress and Goals");
        XYChart.Series series1 = new XYChart.Series(dataList);
        series1.setName("Actual");
         
        //Series 2
        XYChart.Series<String,Number> series2 = new XYChart.Series();
        series2.setName("Goals");
            
        series2.getData().add(new XYChart.Data("Sets", 15));
        series2.getData().add(new XYChart.Data("Reps", 15));
         
        stackedBarChart.getData().addAll(series1, series2);
         
        //--- Prepare TableView
        tableView.setItems(dataList);
        tableView.getColumns().addAll(columnWorkout, columnSets, columnReps);
         
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(tableView, stackedBarChart);
         
        root.getChildren().add(hBox);
         
        inputPanel.setScene(new Scene(root,750,400));
    }
    
    class EditingCell extends TableCell<XYChart.Data, Number> {
         
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
