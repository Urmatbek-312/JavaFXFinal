package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView frontImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File frontFile = new File("images/front.jpg");
        Image frontImage = new Image(frontFile.toURI().toString());
        frontImageView.setImage(frontImage);

        File logoFile = new File("images/logo.jpg");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }


    public void loginButtonOnAction(ActionEvent event) {

        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false ) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username or password");
        }
    }


    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM test.user_account WHERE username = '"+ usernameTextField.getText() + "' AND password ='" + enterPasswordField.getText() + "'";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1) {
                  //  loginMessageLabel.setText("Congratulations");
                    createAccountForm();
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again!");
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void createAccountForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 632));
            registerStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
