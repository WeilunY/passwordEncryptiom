/* File Header */
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.*;

/* Class Header */
public class ProjectInterface extends Application
{
    final int MIN_PASSWORD_LENGTH = 6;
    TextField accInput;
    TextField passInput;
    TextField passOutput;
    Text infoText;
    boolean hasUpper = true;
    boolean hasLower = true;
    boolean hasNumber = true;
    boolean hasSpecial = true;
    CheckBox checkUpper;
    CheckBox checkLower;
    CheckBox checkNumber;
    CheckBox checkSpecial;

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
        checkUpper = new CheckBox("Uppercase");
        checkLower = new CheckBox("Lowercase");
        checkNumber = new CheckBox("Number");
        checkSpecial = new CheckBox("Special Character");
        checkUpper.setOnAction(new CheckHandler());
        checkLower.setOnAction(new CheckHandler());
        checkNumber.setOnAction(new CheckHandler());
        checkSpecial.setOnAction(new CheckHandler());
        pane.add(checkUpper, 2, 6);
        pane.add(checkLower, 2, 7);
        pane.add(checkNumber, 2, 8);
        pane.add(checkSpecial, 2, 9);

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
                // EncryptionBot encrypter = new EncryptionBot(passInput.getText());
                Encryption encrypter = new Encryption(passInput.getText(), hasUpper, hasLower, hasNumber, hasSpecial);
                passOutput.setText(encrypter.getEncrypted());
                //passOutput.setText(encrypter.getEncryptedPassword());
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
            else if(checkRep(passInput.getText()) == false){
                passOutput.clear();
                infoText.setText("Please enter more than 4 different chars");
                return;

            }
            else
            {
                Encryption encrypter = new Encryption(passInput.getText(), hasUpper, hasLower, hasNumber, hasSpecial);
                passOutput.setText(encrypter.getEncrypted());
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

    // Class for handling check boxes.
    class CheckHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e)
        {
            if(checkUpper.isSelected())
            {
                hasUpper = true;
            }
            else
                hasUpper = false;
            if(checkLower.isSelected())
            {
                hasLower = true;
            }
            else
                hasLower = false;
            if(checkNumber.isSelected())
            {
                hasNumber = true;
            }
            else
                hasNumber = false;
            if(checkSpecial.isSelected())
            {
                hasSpecial = true;
            }
            else
                hasSpecial = false;
            if(!hasUpper && !hasLower && !hasNumber && !hasSpecial)
            {
              infoText.setText("Please select at least one box");
            }
        }
    }

    public static boolean checkRep(String o){
      int variation = 0;
      ArrayList<Character> v = new ArrayList<Character>();
      v.add(o.charAt(0));
      boolean check = true;
      for(int i = 0; i < o.length(); i++){
        for(int j = 0; j < v.size(); j++){
          if(v.get(j) == o.charAt(i))
            check = false;
        }
        if(check){
          variation++;
          v.add(o.charAt(i));
        }
      }
      return variation > 4;
    }
}
