package ihm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import data.Chambre;
import data.Client;
import data.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FenPrincipale extends Stage {
	private HBox root = new HBox();

	private VBox buttonsBox = new VBox();
	private VBox resBox = new VBox();
		
	private TableView<Reservation> tableRes = new TableView();
	
	private ObservableList<Reservation> resObservable = FXCollections.observableArrayList();
	
	private FilteredList<Reservation> filteredComptes = new FilteredList<Reservation>(resObservable);

	private Button bnClose = new Button("Fermer");
	private Button bnModify = new Button("Modifier");
	private Button bnAdd = new Button("Ajouter");
	private Button bnDelete = new Button("Supprimer");
	
    private ColumnConstraints column1 = new ColumnConstraints();
    private ColumnConstraints column2 = new ColumnConstraints();
	
	private Scene scene;
	
	public FenPrincipale(){ 
		this.setTitle("Liste Reservation"); 
		this.setResizable(true);
		this.scene = new Scene(creerContenu());
		this.setScene(scene);
		this.setMinWidth(600);
		this.initList();
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double thirdWidth = newWidth.doubleValue() / 4;
            column1.setPrefWidth(thirdWidth);
            column2.setPrefWidth(newWidth.doubleValue() - thirdWidth);
        });
	}
	
	private Parent creerContenu(){
		
        VBox buttonPane = new VBox(20);
        
        buttonPane.setPadding(new Insets(20));
        buttonPane.setAlignment(Pos.CENTER);
        
        HBox.setHgrow(bnAdd, Priority.ALWAYS);
        HBox.setHgrow(bnModify, Priority.ALWAYS);
        HBox.setHgrow(bnDelete, Priority.ALWAYS);
        HBox.setHgrow(bnClose, Priority.ALWAYS);
        
        VBox.setVgrow(bnAdd, Priority.ALWAYS);
        VBox.setVgrow(bnModify, Priority.ALWAYS);
        VBox.setVgrow(bnDelete, Priority.ALWAYS);
        VBox.setVgrow(bnClose, Priority.ALWAYS);
        
        bnAdd.setMaxSize(250, 50);
        bnModify.setMaxSize(250, 50);
        bnDelete.setMaxSize(250, 50);
        bnClose.setMaxSize(250, 50);
        
        
        // Création du conteneur pour les boutons
        buttonPane.getChildren().addAll( bnAdd, bnModify, bnDelete, bnClose);
        
        TableColumn<Reservation, String> numResColumn = new TableColumn<>("Numéro Réservation");
        TableColumn<Reservation, String> nomClientColumn = new TableColumn<>("Nom");
        TableColumn<Reservation, String> statusColumn = new TableColumn<>("status");
        TableColumn<Reservation, String> arriveColumn = new TableColumn<>("Arrivée");
        TableColumn<Reservation, String> departColumn = new TableColumn<>("Départ");
        TableColumn<Reservation, String> nbOccuColumn = new TableColumn<>("Occupant(s)");
        
        numResColumn.setCellValueFactory(new PropertyValueFactory<>("numRes"));
        nomClientColumn.setCellValueFactory(new PropertyValueFactory<>("resNom"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        arriveColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebutSejour"));
        departColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinSejour"));
        nbOccuColumn.setCellValueFactory(new PropertyValueFactory<>("nbOccu"));
        
        tableRes.getColumns().addAll(numResColumn, nomClientColumn, statusColumn, arriveColumn, departColumn, nbOccuColumn);
        tableRes.setItems(filteredComptes);
        
        tableRes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        resBox.getChildren().add(tableRes);
        
        VBox.setVgrow(tableRes, Priority.ALWAYS);
        
        resBox.setPadding(new Insets(10));
        
        CustomInput search = new CustomInput(250, "Search");
        search.setBackground(new Background(new BackgroundFill(
				Color.ALICEBLUE, //couleur du fond
				new CornerRadii(0), //taille d’arrondi des angles
				new Insets(0) // taille du padding du fond
        )));          
        search.setMaxHeight(40);
        
        GridPane root = new GridPane();
        
        root.add(buttonPane, 0, 0);
        GridPane.setRowSpan(buttonPane, 2);
        root.add(search, 1, 0);
        root.add(resBox, 1, 1);
        
        column1.setPercentWidth(25);
        column2.setPercentWidth(75);
        
        root.getColumnConstraints().addAll(column1, column2);
        
        GridPane.setHgrow(tableRes, Priority.ALWAYS);
        GridPane.setVgrow(resBox, Priority.ALWAYS);
        GridPane.setVgrow(search, Priority.SOMETIMES);
        
        buttonPane.setBackground(new Background(new BackgroundFill(
				Color.BISQUE, //couleur du fond
				new CornerRadii(0), //taille d’arrondi des angles
				new Insets(0) // taille du padding du fond
        )));  
        
        bnAdd.setOnAction((e) -> {
        	new FenCompte(this).show();
        });
        
        bnModify.setOnAction((e) -> {
            Reservation selectedCompte = tableRes.getSelectionModel().getSelectedItem();
            if (selectedCompte != null) {
            }
        });
        
        bnDelete.setOnAction((e) -> {
        	Reservation selectedCompte = tableRes.getSelectionModel().getSelectedItem();
            if (selectedCompte != null) {
            	new ConfirmationDialog("Supprimer Compte", "Êtes vous sur de vouloir supprimer le compte de " + selectedCompte.getResNom(), () -> {
            		resObservable.remove(selectedCompte);
            	});
            }
        });
        
        bnClose.setOnAction((e) -> this.close());
        
        search.getInput().textProperty().addListener((obs, oldValue, newValue) -> {
        	filteredComptes.setPredicate(p -> p.getResNom().toLowerCase().startsWith(newValue.toLowerCase()));
        });
        
		return root;
	}
	
	private void initList() {
		Client c1 = new Client("Collin", "Ethan", "Mme", "2 rue du 19 mars 1962.", "aucune", "22100", "Dinan", "collin@gmail.com", "0644444444");
		Client c2 = new Client("Ponthou", "Yanis", "Mr", "27 rue du chateau", "aucune", "29190", "Chateaulin", "ponthou@gmail.com", "0699545876");
		Client c3 = new Client("Desperrois", "Lucas", "Mr", "9 rue Hervé de Portzmoguer.", "aucune", "29217", "Plougonvelin", "desperrois@gmail.com", "07667867");
		Client c4 = new Client("Levacher", "Ethan", "Mr", "3 impasse du Guillec", "aucune", "29400", "Plougourvest", "levacher@gmail.com", "0771662401");
		Chambre ch1 = null;
		try {
			ch1 = new Chambre(1, "Villa");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			Chambre ch2 = new Chambre(2, "Villa");
		} catch (Exception e) {
			System.out.println(e);
		}		
		this.resObservable.add(new Reservation(c4, ch1, "19/06/2023", "24/06/2023", "26/06/2023", 5));
	}
}
