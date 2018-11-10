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
    private int estAccepte;

    @ManyToOne(fetch = FetchType.LAZY)
    private Equipe p_equipe;

    public Participant() {
    }

    public Participant(String prenom, String nom, String motDePasse, int matricule) {
        this.setMatricule(matricule);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setMotDePasse(motDePasse);
        this.setEstAccepte(0);
    }

    public int getEstAccepte() { return estAccepte; }

    public void setEstAccepte(int estAccepte) { this.estAccepte = estAccepte; }

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

    public Equipe getP_equipe() {
        return p_equipe;
    }

    public void setP_equipe(Equipe p_equipe) {
        this.p_equipe = p_equipe;
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
