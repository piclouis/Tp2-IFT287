package CentreSportif;

import org.bson.Document;


public class Participant {
    private int matricule;
    private String nom;
    private String prenom;
    private String motDePasse;
    private int estAccepte;
    private String nomEquipe;

    public Participant() {
    }

    public Participant(Document d)
    {
        matricule = d.getInteger("matricule");
        nom = d.getString("nom");
        prenom = d.getString("prenom");
        motDePasse = d.getString("motDePasse");
        estAccepte = d.getInteger("estAccepte");
        nomEquipe = d.getString("nomEquipe");
    }

    public Participant(String prenom, String nom, String motDePasse, int matricule) {
        this.setMatricule(matricule);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setMotDePasse(motDePasse);
        this.setEstAccepte(0);
    }

    public String getNomEquipe() { return nomEquipe; }

    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }

    public int getEstAccepte() {
        return estAccepte;
    }

    public void setEstAccepte(int estAccepte) {
        this.estAccepte = estAccepte;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public String toString() {     //à refaire
        return "  Matricule: " + matricule +
                "\n  Nom: " + nom +
                "\n  Prenom: " + prenom;
    }

    public Document toDocument() // dois je inclure le idParticipant
    {
        return new Document().append("matricule", matricule)
                .append("nom", nom)
                .append("prenom", prenom)
                .append("motDePasse", motDePasse)
                .append("estAccepte", estAccepte)
                .append("nomEquipe", nomEquipe);
    }
}
