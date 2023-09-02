package data;

public class DoublonNumChambre extends Exception {
	
	private int numero;
	
	public DoublonNumChambre(int numero) {
		super("Chaque chambre doit avoir un numéro unique !");
		this.numero = numero;
	}
	
	public String toString() {
		return "La chambre n°" + this.numero + " existe déjà !";
	}
}
