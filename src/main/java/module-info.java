module ucf.assignment {
    requires javafx.controls;
    requires javafx.fxml;
    opens ucf.assignment to javafx.fxml;
    exports ucf.assignment;
}