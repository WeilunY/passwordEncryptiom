/* File Header */
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/* Class Header */
public class ProjectInterface extends Application
{
    final int MIN_PASSWORD_LENGTH = 6;
    TextField accInput;
    TextField passInput;
    TextField passOutput;
    Text infoText;

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
        infoText = new Text("");
        infoText.setTextAlignment(TextAlignment.RIGHT);
        infoText.setVisible(true);
        pane.add(infoText, 2, 5);

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
                infoText.setText("Enter Account/Website");
                return;
            }
            else if(passInput.getLength() < MIN_PASSWORD_LENGTH)
            {
                passOutput.clear();
                passInput.requestFocus();
                infoText.setText("Enter 6+ char password");
            }
            else
            {
                EncryptionBot encrypter = new EncryptionBot(passInput.getText());
                passOutput.setText(encrypter.getEncryptedPassword());
                passOutput.requestFocus();
                passOutput.selectAll();
                infoText.setText("Encryption successful!");
            }
        }
    }
      
    // Class for handling password input.
    class PassHandler implements EventHandler<ActionEvent> 
    {
        public void handle(ActionEvent e) 
        {
            if(passInput.getLength() < MIN_PASSWORD_LENGTH)
            {
                passOutput.clear();
                infoText.setText("Enter 6+ char password");
                return;
            }
            else if(accInput.getLength() == 0)
            {
                passOutput.clear();
                accInput.requestFocus();
                infoText.setText("Enter Account/Website");
            }
            else
            {
                EncryptionBot encrypter = new EncryptionBot(passInput.getText());
                passOutput.setText(encrypter.getEncryptedPassword());
                passOutput.requestFocus();
                passOutput.selectAll();
                infoText.setText("Encryption successful!");
            }
        }
    }  
    
    // Class for handling info button press.
    class InfoHandler implements EventHandler<ActionEvent> 
    {
        public void handle(ActionEvent e) 
        {
            if(infoText.getText().equals("Information"))
            {
                infoText.setText("");
                return;
            }
            else
                infoText.setText("Information");
        }
    }
}