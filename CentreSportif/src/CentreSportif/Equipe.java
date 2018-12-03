package CentreSportif;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Equipe {
    @Id
    @GeneratedValue
    private long m_id;

    private String nomEquipe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ligue e_ligue;
    private Participant capitaine;

    @OneToMany(mappedBy = "p_equipe")
    private List<Participant> participants;

    @OneToMany(mappedBy = "equipeA")
    private List<Resultat> resultats;

    public Equipe() {
        this.participants = new LinkedList<Participant>();
        this.resultats = new LinkedList<Resultat>();
    }

    public Equipe(Ligue ligue, String nomEquipe, Participant participant) {
        this.setE_ligue(ligue);
        this.e_ligue = ligue;
        this.nomEquipe = nomEquipe;
        this.capitaine = participant;
        this.participants = new LinkedList<Participant>();
        this.resultats = new LinkedList<Resultat>();
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Ligue getE_ligue() {
        return e_ligue;
    }

    public void setE_ligue(Ligue e_ligue) {
        this.e_ligue = e_ligue;
    }

    public Participant getCapitaine() {
        return capitaine;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setCapitaine(Participant capitaine) {
        this.capitaine = capitaine;
    }

    @Override
    public String toString() {
        return "Nom Ligue: '" + e_ligue.getNomLigue() +
                "' | Nom Equipe: '" + nomEquipe;
    }

    public void ajouterJoueur(Participant participant) {
        participants.add(participant);
    }

    public void supprimerJoueur(Participant participant) {
        participants.remove(participant);
    }
}