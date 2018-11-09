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
    private Ligue ligue;
    private Participant capitaine;

    @OneToMany(mappedBy = "equipe")
    private List<Participant> participants;

    public Equipe() {
        this.participants = new LinkedList<Participant>();
    }

    public Equipe(Ligue ligue, String nomEquipe) {
        this.setLigue(ligue);

        this.ligue = null;
        this.capitaine = null;
        this.participants = new LinkedList<Participant>();
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Ligue getLigue() {
        return ligue;
    }

    public void setLigue(Ligue ligue) {
        this.ligue = ligue;
    }

    public Participant getCapitaine() {
        return capitaine;
    }

    public void setCapitaine(Participant capitaine) {
        this.capitaine = capitaine;
    }

    @Override
    public String toString() {
        return "Nom Ligue: '" + ligue.getNomLigue() +
                "' | Nom Equipe: '" + nomEquipe;
    }
}