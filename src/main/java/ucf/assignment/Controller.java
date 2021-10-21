package ucf.assignment;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Reese Spector
 */

public class Controller implements Initializable {

    //creates variables for the program to use
    int trackID = 0; //track id's for each new task

    //the main list used to create and store tasks
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    //using javaFX FilteredList function to create temporary lists
    //to store filtered tasks of type 'Complete' or 'Incomplete'
    FilteredList<Task> items = new FilteredList<>(tasks);

    //FXML buttons and fields delceration
    @FXML private TableColumn<Task, Integer> id;
    @FXML private TableColumn<Task, String> date;
    @FXML private TableColumn<Task, String> desc;
    @FXML private TableColumn<Task, String> name;
    @FXML private TableColumn<Task, String> status;
    @FXML private TableView<Task> tasktable;
    @FXML private TextField addDate;
    @FXML private TextField addDesc;
    @FXML private TextField addStatus;
    @FXML private TextField addTitle;

    public Controller() {}

    //this method closes the program
    //by being called from the file > close
    //on the top menu bar
    public void closeProgram() {
        Platform.exit();
        System.out.println("Program Terminated\n");
    }

    //initialize table, and start the program blank
    //when no tasks are availible, the table shows blank
    public ObservableList<Task> getTasks() {
        tasks.add(new Task(0, " ", " ", " ", " ")); //initialize table
        tasks.remove(0);                                                  //starts/reset table blank
        return tasks;
    }

    //clears the task table of all entries
    //simply removes all tasks from list
    //and resets id tracker to 0
    public void clearTable(){
        tasks.removeAll(tasks);
        trackID = 0;
    }

    //this method is called when the 'add task' button is pressed.
    //it gets the data from the input fields
    //and populates the task fields
    //once all fields of the task are filled, create a task
    @FXML
    public void addTask() {
        tasks.add(new Task(trackID(), addTitle.getText(), addDesc.getText(), addDate.getText(), addStatus.getText()));
        addTitle.clear();
        addDesc.clear();
        addStatus.clear();
        addDate.clear();
        System.out.println("Task Added");
    }

    //removes selected task on the table, and clicks 'delete task'
    //user simply selects task .getSelectedItem() and calls
    //remove() to delete from list
    public void removeTask() {
        tasktable.getItems().removeAll(tasktable.getSelectionModel().getSelectedItem());
        System.out.println("Task Removed");
    }

    //this method simply iterates the id of a
    //task to give it a menu #
    public int trackID() {
        return trackID++;
    }

    //filter Tasks with the 'Complete' Status
    //use sub predicate list to temporarily
    //display a list with 'Complete' as its status
    public void filterListComplete() {
        tasktable.setItems(items);
        Predicate<Task> filterComplete = task -> task.getStatus().contains("Complete");
        items.setPredicate(filterComplete);
        System.out.println("Filter Complete");
    }

    //filter Tasks with the 'Incomplete' Status
    //use sub predicate list to temporarily
    //display a list with 'Incomplete' as its status
    public void filterListIncomplete() {
        tasktable.setItems(items);
        Predicate<Task> filterComplete = task -> task.getStatus().contains("Incomplete");
        items.setPredicate(filterComplete);
        System.out.println("Filter Incomplete");
    }

    //resets filters back to the origional list of tasks
    //prior to filtering
    public void filterReset() {
        items.setPredicate(null);
        System.out.println("Filter Reset");
    }

    //import and read task list
    //file new
    //open browser
    //try {
    //  while(){
    //      -take in task line by line, and seperate
    //      values into a tring using a delimiter ,
    //      tasks new (field, field field etc)...
    //      -then add to array list
    //      tasks.add(data);
    //  }
    //  catch()
    //      error message
    //}
    public void readCSV(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        String fieldDelimiter = ",";
        BufferedReader csvReader;
        try {
            String taskLine;
            csvReader = new BufferedReader(new FileReader(file));
            while ((taskLine = csvReader.readLine()) != null && !taskLine.isEmpty()) {
                String[] taskImport = taskLine.split(fieldDelimiter, -1);
                Task tasksCSV = new Task (trackID(), taskImport[1], taskImport[2], taskImport[3], taskImport[4]);
                tasks.add(tasksCSV);
                System.out.println("Tasks Added");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("ERROR -- CANNOT FIND FILE");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("ERROR -- FILE ERROR");
        }
    }

    //this method gathers the tasks on the list and exports it
    //to a comma seperated format (CSV)
    //file new
    //try {
    //  while(){
    //      take in task line by line, and seperate
    //      values into a tring using a delimiter ,
    //      tasks printf(field, field field etc)...
    //      close
    //  }
    //  catch()
    //      error message
    //}
    public void exportCSV() throws IOException {
        File outputCSV = new File("src/main/java/ucf/assignment/exported.csv");
        PrintWriter newCSV = new PrintWriter(outputCSV);
        for(Task tasks : tasks){
            newCSV.printf("%s, %s, %s, %s",tasks.getTitle(),tasks.getDescription(),tasks.getDate(),tasks.getStatus());
        }
        newCSV.close();
        System.out.println("Tasks Exported to Desktop: ExportedTasks.csv");
    }

    //this function initializes the table using list values that are imported via getTasks();
    //the values at the beginning are the column id's where the value is passed
    //the values at the end in "" are the column id's names
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Task,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Task,String>("title"));
        desc.setCellValueFactory(new PropertyValueFactory<Task,String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Task,String>("date"));
        status.setCellValueFactory(new PropertyValueFactory<Task,String>("status"));
        setEditable();
        tasktable.setItems(getTasks());
    }

    //this method sets each row cell of the table view as editable
    //users can double click a cell and change / save its value via enter key
    //enable the table to be editable, and save new input values
    //tasktable.setEditable(true);
    //task.getCell()
    //task.onCommit save task to task list
    //repeat for each field
    public void setEditable(){
        tasktable.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
             @Override
             public void handle(TableColumn.CellEditEvent<Task, String> event) {
                 Task task = event.getRowValue();
                 task.setTitle(event.getNewValue());
             }
         });
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        desc.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, String> event) {
                Task task = event.getRowValue();
                task.setDescription(event.getNewValue());
            }
        });
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, String> event) {
                Task task = event.getRowValue();
                task.setDate(event.getNewValue());
            }
        });
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, String> event) {
                Task task = event.getRowValue();
                task.setStatus(event.getNewValue());
            }
        });
    }
}