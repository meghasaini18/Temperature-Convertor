package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;

	@FXML
	public ChoiceBox choiceBox;

	@FXML
	public TextField userInputField;

	@FXML
	public Button convertButton;

	private static final String C_to_F_Text = "celsius to Fahrenheit";
	private static final String F_to_C_Text = "Fahrenheit to celsius";

	private boolean isC_to_F_Text = true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.getItems().add(C_to_F_Text);
		choiceBox.getItems().add(F_to_C_Text);

		choiceBox.setValue(C_to_F_Text);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals(C_to_F_Text)) {
				isC_to_F_Text = true;
			}else{
				isC_to_F_Text = false;
			}
		});
		convertButton.setOnAction(event ->
				convert());
	}

	private void convert() {
		String input = userInputField.getText();

		float enteredTemperature = 0.0f;
		try {
			 enteredTemperature = Float.parseFloat(input);
		}catch (Exception exception){
			warnUser();
			return;
			//No code excuted...
		}
		float newTemperature = 0.0f;
		if(isC_to_F_Text){
			newTemperature = (enteredTemperature * 9/5) + 32;
		}else{
			newTemperature = (enteredTemperature - 32) * 5/9;
		}
		display(newTemperature);
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid temperature entered");
		alert.setContentText("Please enter a valid temperature");
		alert.show();
	}

	private void display(float newTemperature) {
		String unit = isC_to_F_Text? "F" : "C";           //Ternary Operator
		System.out.println("The new temperature is " + newTemperature + unit);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new temperature is " + newTemperature + unit);
		alert.show();
	}
}
