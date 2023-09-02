package data;

public class Client {
	
	private int id;
	private String nom, prenom, civilite, adresse1, adresse2, codePostale, ville, email, telephone;
	
	private static int nbClient = 0;
	
	public Client(String nom, String prenom, String civilite, String adresse1, String adresse2, String codePostale, String ville, String email, String telephone) {
		this.id = nbClient++;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostale = codePostale;
		this.ville = ville;
		this.email = email;
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	
	
}
