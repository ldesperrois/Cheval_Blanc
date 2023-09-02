package ihm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomInput extends VBox {
	private Label inputLabel = new Label();
	private TextField inputField = new TextField();
	private boolean required = false;
	
	private HBox input = new HBox();

	private Label errorLabel = new Label();
	private String errorTxt;
	private String match;
		
	private final double TEXT_WIDTH = 150;

	
	public CustomInput(double width, String txt) {
		this.inputLabel.setText(txt);
		this.inputLabel.setMinWidth(TEXT_WIDTH);
		this.inputField.setMinWidth(width);
		this.getChildren().addAll(inputLabel, inputField);
		this.setMinWidth(width);
		this.setAlignment(Pos.BASELINE_CENTER);
		this.input.setAlignment(Pos.BASELINE_CENTER);
		VBox.setMargin(errorLabel, new Insets(0, 0, 10, 0));
		
		input.getChildren().addAll(this.inputLabel, this.inputField);
		this.errorLabel.setStyle("-fx-font-size:10; -fx-text-fill:RED;");
		this.errorLabel.setVisible(false);
		this.getChildren().addAll(this.input, this.errorLabel);
		
		this.inputField.setOnKeyReleased(e -> {
			this.check();
		});
	}
	
	public CustomInput(double width, String txt, boolean required) {
		this(width, txt);
		this.setObligatory();
	}
	
	public CustomInput(double width, String txt, String err, String match) {
		this(width, txt, true);
		this.errorLabel.setText(err);
		this.errorTxt = err;
		this.match = match;
	}
	
	public void setObligatory() {
		this.required = true;
	}
	

	public CustomInput setNotification(String string) {
		this.inputField.setTooltip(new Tooltip(string));
		return this;
	}
	
	public CustomInput addLimit(int size) {
		this.inputField.setOnKeyPressed(e -> {
			if (this.inputField.getText().length() >= size) {
				this.inputField.deletePreviousChar();
			}
		});
		return this;
	}
	
	public void setText(String text) {
		this.inputField.setText(text);
	}
	
	public CustomInput addLimitAlert(int size, String title, String text) {
		this.inputField.setOnKeyTyped(e -> {
			if (this.inputField.getText().length() >= size) {
				e.consume(); // CANCEL PAS LE TYPED ???
				this.inputField.deletePreviousChar();
				Alert erreur = new Alert(AlertType.ERROR, text, ButtonType.OK);
				erreur.setTitle(title);
				erreur.showAndWait();
			}
		});
		return this;
	}
	
	public boolean isRequired() {
		return this.required;
	}
	
	public boolean check() {
	    String inputText = this.inputField.getText();

	    if (inputText.isEmpty()) {
	        if (this.isRequired()) {
	            errorLabel.setText("Merci de saisir une valeur.");
	            errorLabel.setVisible(true);
	            return false;
	        } else {
	            errorLabel.setVisible(false);
	            return true;
	        }
	    } else if (match != null && !inputText.matches(match)) {
	        errorLabel.setText(errorTxt);
	        errorLabel.setVisible(true);
	        return false;
	    } else {
	        errorLabel.setVisible(false);
	        return true;
	    }
	}
	
	public String getText() {
		return this.inputField.getText();
	}
	
    protected void setInputField(TextField inputField) {
        this.input.getChildren().remove(this.inputField);
        this.inputField = inputField;
        this.input.getChildren().add(inputField);
		this.inputField.setOnKeyReleased(e -> {
			this.check();
		});
    }
    
    public TextField getInput() {
    	return this.inputField;
    }
}

