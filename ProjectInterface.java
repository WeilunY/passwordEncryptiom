/* File Header */
import javax.lang.model.util.ElementScanner6;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/* Class Header */
public class ProjectInterface extends Application
{
    TextField accInput;
    TextField passInput;
    TextField passOutput;

    /* Method Header */
    public void start(Stage primaryStage)
    {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.add(new Label("Encryption Tool"), 2, 0);
        pane.add(new Label("Account/Website"), 0, 1);
        accInput = new TextField();
        accInput.setOnAction(new AccHandler());
        pane.add(accInput, 0, 2);
        pane.add(new Label("Simple Password"), 3, 1); 
        passInput = new TextField();
        passInput.setOnAction(new PassHandler());
        pane.add(passInput, 3, 2);
        pane.add(new Label("Encrypted Password"), 2, 3);
        Button infoBT = new Button("?");
        infoBT.setOnAction(new InfoHandler());
        pane.add(infoBT, 3, 4);
        passOutput = new TextField();
        passOutput.setEditable(false);
        pane.add(passOutput, 2, 4);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Encryption Tool"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }

    // Class for handling Account/Website input
    class AccHandler implements EventHandler<ActionEvent> 
    {
        public void handle(ActionEvent e) 
        {
            if(accInput.getLength() == 0)
            {
                passOutput.clear();
                return;
            }
            else if(passInput.getLength() == 0)
            {
                passOutput.clear();
                passInput.requestFocus();
            }
            else
            {
                passOutput.setText("Encrypted Password");
            }
        }
    }
      
    // Class for handling password input.
    class PassHandler implements EventHandler<ActionEvent> 
    {
        public void handle(ActionEvent e) 
        {
            if(passInput.getLength() == 0)
            {
                passOutput.clear();
                return;
            }
            else if(accInput.getLength() == 0)
            {
                passOutput.clear();
                accInput.requestFocus();
            }
            else
            {
                passOutput.setText("Encrypted Password");
            }
        }
    }  
    
    // Class for handling info button press.
    class InfoHandler implements EventHandler<ActionEvent> 
    {
        public void handle(ActionEvent e) 
        {
            passOutput.setText("info");
        }
    }
}