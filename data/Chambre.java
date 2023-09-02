package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Chambre {
	private int numero;
	private String categorie; // Classe catégorie ?
	
	//private static HashMap<String, ArrayList<Integer>> numByCategorie = new HashMap<String, ArrayList<Integer>>();
	private static ArrayList<Integer> numChambres = new ArrayList<Integer>();
	public Chambre(int numero, String categorie) throws DoublonNumChambre {
		if (Chambre.numChambres.contains(numero)) {
			throw new DoublonNumChambre(numero);
		} else {
			Chambre.numChambres.add(numero);
			this.numero = numero;
			this.categorie = categorie;
		}
	}
}
