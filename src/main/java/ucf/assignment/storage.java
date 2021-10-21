package ucf.assignment;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
public class storage {
    GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

    Scene scene = new Scene(grid, 300, 275);

    Text scenetitle = new Text("Login Biiitch: ");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    Label userName = new Label("Username: ");
    TextField userTextField = new TextField();
    Label pw = new Label("Password: ");
    PasswordField peBox = new PasswordField();

        grid.add(scenetitle,0,0,2,1);
        grid.add(userName,1,1,1,1);
        grid.add(userTextField,2,1,1,1);
        grid.add(pw,1,2,1,1);
        grid.add(peBox,2,2,1,1);

    //grid.setGridLinesVisible(true);

    Button btn = new Button("Sign in");
    HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);

    final Text actiontarget = new Text();
        grid.add(actiontarget,1,6);

        btn.setOnAction(event -> {
        actiontarget.setFill(Color.FIREBRICK);
        actiontarget.setText("Sign in button pressed biiiitch");
    });
}
*/