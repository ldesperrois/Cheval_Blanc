package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class Reservation {

	private Date dateReservation, dateDebutSejour, dateFinSejour;
	private boolean arrive;
	private int nbOccu;
	private Client client;
	private int numRes;
	private String status;
	
	private StringProperty nomRes = new SimpleStringProperty();
	
	private StringProperty dateDebutStr = new SimpleStringProperty();
	private StringProperty dateFinStr = new SimpleStringProperty();
	
	private ArrayList<Chambre> chambres = new ArrayList<Chambre>();
	
	private static int nbRes = 0;
	
	public Reservation(Client client, Chambre chambre, Date dateReservation, Date dateDebutSejour, Date dateFinSejour, int nbOccu) {
		this.numRes = nbRes++;
		this.client = client;
		this.dateReservation = dateReservation;
		this.dateDebutSejour = dateDebutSejour;
		this.dateFinSejour = dateFinSejour;
		this.nbOccu = nbOccu;
		this.chambres.add(chambre);
		this.status = "Attente";
		nomRes.set(client.getNom());
		this.arrive = false;
		dateDebutStr.set(dateDebutSejour.toString());
		dateFinStr.set(dateFinSejour.toString());
	}
	
	public Reservation(Client client, Chambre chambre, String dateReservation, String dateDebutSejour, String dateFinSejour, int nbNuit) {
		this(client, chambre, Reservation.stringToDate(dateReservation), Reservation.stringToDate(dateDebutSejour), Reservation.stringToDate(dateFinSejour), nbNuit);
	}

	// Constructeur avec String pour la date

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateDebutSejour() {
		return dateDebutSejour;
	}

	public void setDateDebutSejour(Date dateDebutSejour) {
		this.dateDebutSejour = dateDebutSejour;
	}

	public Date getDateFinSejour() {
		return dateFinSejour;
	}

	public void setDateFinSejour(Date dateFinSejour) {
		this.dateFinSejour = dateFinSejour;
	}

	public int getNbOccupant() {
		return nbOccu;
	}

	public void setNbOccupant(int nbNuit) {
		this.nbOccu = nbNuit;
	}
	
	public void setArrivee(boolean a) {
		this.arrive = a;
	}
	
	public boolean getArrive() {
		return this.arrive;
	}
	
	protected void ajouterChambre(Chambre c) {
		this.chambres.add(c);
	}
	
	protected void supprimerChambre(Chambre c) {
		this.chambres.remove(c);
	}
	
	public void enregistrerChambre(Chambre c) {
		if (c != null) {
			if (!this.chambres.contains(c)) {
				this.ajouterChambre(c);
			} else {
				System.err.println("Cette résérvation possède déjà cette chambre.");
			}
		} else {
			System.err.println("Une chambre d'une résérvation ne peux pas être null.");
		}
	}
	
	public void desenregistrerChambre(Chambre c) {
		if (c != null) {
			if (this.chambres.contains(c)) {
				if (this.chambres.size() > 1) {
					this.supprimerChambre(c);
				} else {
					System.err.println("Une résérvation doit oblgiatoirement avoir une chambre.");
				}
			} else {
				System.err.println("Cette résérvation ne possède pas cette chambre.");
			}
		} else {
			System.err.println("Une chambre d'une résérvation ne peux pas être null.");
		}
	}
	
	public Stage afficherReservation() {
		Stage root = new Stage();
		root.setTitle("Résérvations de " + this.client.getNom() + " (" + this.numRes + ")");
		
		return root;
	}
	
	public void setClient(Client c) {
		this.client = c;
		this.nomRes.set(c.getNom());
	}
	
	public Client getClient() {
		return client;
	}
	
	public int getNbOccu() {
		return nbOccu;
	}

	public void setNbOccu(int nbOccu) {
		this.nbOccu = nbOccu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResNom() {
		return nomRes.get();
	}

	public void getResNom(String str) {
		this.nomRes.setValue(str);
	}
	
	public int getNumRes() {
		return this.numRes;
	}
	
	private static  Date stringToDate(String str) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(str);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
}
