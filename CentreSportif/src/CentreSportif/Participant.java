package CentreSportif;

import javax.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    private long m_id;

    private int matricule;
    private String nom;
    private String prenom;
    private String motDePasse;
    private String nomEquipe;
    private int estAccepter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Equipe equipe;

    public Participant() {
    }

    public Participant(String prenom, String nom, String motDePasse, int matricule) {
        this.setMatricule(matricule);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setMotDePasse(motDePasse);
        this.setNomEquipe(null);
        this.setEstAccepter(0);
    }

    public int getEstAccepter() { return estAccepter; }

    public void setEstAccepter(int estAccepter) { this.estAccepter = estAccepter; }

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

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "  Matricule: " + matricule +
                "\n  Nom: " + nom +
                "\n  Prenom: " + prenom;
    }
}
