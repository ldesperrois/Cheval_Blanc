package ihm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import data.Reservation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FenCompte extends Stage {
	private VBox root = new VBox();
	
	private String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	private Button    	bnOk	  = new Button("OK");
	private Button	    bnCancel  = new Button("Annuler");
	
	private Label		title = new Label("Créez un compte");
	
	private CustomInput pseudo = new CustomInput(100, "Pseudo:", "Merci de saisir au moins 3 caractères",".{3,}");
	private CustomInput mail = new CustomInput(100, "Mail:","Format Incorrect. Saisir une email valid (exemple@gmail.com)", emailRegex);	
	private CustomInput password = new CustomInput(100, "Mot de passe:", "Au moins 5 caractères", ".{5,}");
	private CustomInput passwordRepeat = new CustomInput(100, "Mot de passe:", "Au moins 5 caractères", ".{3,}");

	
	private HBox 		buttonZone = new HBox();
	
	private FenPrincipale owner;

	
	public FenCompte(FenPrincipale owner){ 
		this.setTitle("Formulaire"); 
		this.setResizable(false);
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		this.setWidth(600);
		this.initModality(Modality.APPLICATION_MODAL);
		this.initOwner(owner);
		this.owner = owner;
	}
	
	private Parent creerContenu(){
		this.title.setStyle("-fx-font-size: 24px");
		bnOk.setDefaultButton(true);
		bnCancel.setCancelButton(true);
		
		this.buttonZone.getChildren().addAll(bnOk, bnCancel);
		this.buttonZone.setAlignment(Pos.BOTTOM_RIGHT);
		this.buttonZone.setPadding(new Insets(10));
		HBox.setMargin(bnCancel, new Insets(5));
		HBox.setMargin(bnOk, new Insets(5));
		
		root.setAlignment(Pos.CENTER);
		
		root.setSpacing(5);
		root.getChildren().addAll(title, pseudo, mail, password, passwordRepeat, buttonZone);
		
	
		bnOk.setOnAction((e) -> {
			new ConfirmationDialog("Confirmez l'ajout d'un abonnement", "Confirmez vous ?", () -> {
				this.submitForm();
			});
		});
		
		bnCancel.setOnAction(e -> this.close());
		
		return root;
	}
	
	private void retrieveCustomInputsWithError(Node node, List<CustomInput> customInputs) {
	    if (node instanceof CustomInput) {
	        CustomInput customInput = (CustomInput) node;
	        customInputs.add(customInput);
	    } else if (node instanceof Parent) {
	        Parent parent = (Parent) node;
	        for (Node child : parent.getChildrenUnmodifiable()) {
	            retrieveCustomInputsWithError(child, customInputs);
	        }
	    }
	}
	
	private void submitForm() {
		 List<CustomInput> customInputs = new ArrayList<>();
		 retrieveCustomInputsWithError(root, customInputs);
		 
		 boolean isValid = true;
		 
		 for (CustomInput customInput : customInputs) {
		    if (!customInput.check()) {
		    	isValid = false;
		    }
		 }
		 
		 if (isValid) {
			 Reservation c = new Reservation(this.pseudo.getText(), this.mail.getText(), this.password.getText());
			 this.owner.creeCompte(c);
			 this.close();
		 } else {
			 System.out.println("Submit cancelled;");
		 }
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo.setText(pseudo);
	}
	
	public void setEmail(String email) {
		this.mail.setText(email);
	}
}
