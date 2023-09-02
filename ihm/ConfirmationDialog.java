package ihm;

import java.util.Optional;
import java.util.function.Function;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ConfirmationDialog extends Alert {

	private Runnable success;
	
	public ConfirmationDialog(String title, String content, Runnable success) {
		super(AlertType.CONFIRMATION);
		this.setTitle(title);
		this.setContentText(content);
		this.success = success;
		
		Optional<ButtonType> result = this.showAndWait();
		if (result.get() == ButtonType.OK){
		    this.success.run();
		}
	}
	
	public ConfirmationDialog(String title, String content, Runnable success, String header) {
		this(title, content, success);
		this.setHeaderText(header);
	}
	
	
	

}
