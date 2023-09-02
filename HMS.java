import ihm.FenPrincipale;
import javafx.application.Application;
import javafx.stage.Stage;

public class HMS extends Application {
	public void start(Stage fenetre) {
		fenetre = new FenPrincipale();
		fenetre.show();
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
}