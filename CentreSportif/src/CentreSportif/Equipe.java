package CentreSportif;

import javax.persistence.*;

public class Equipe {
    @Id
    @GeneratedValue
    private long m_id;

    private String nomEquipe;
    private String nomLigue;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ligue ligue;
    @OneToOne (cascade=CascadeType.PERSIST)
    private Participant capitaine;

    public Equipe() {
    }

    public Equipe(String nomLigue, String nomEquipe) {
        this.setNomLigue(nomLigue);
        this.setNomEquipe(nomEquipe);
        this.ligue = null;
        this.capitaine = null;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
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
        return "Nom Ligue: '" + nomLigue +
                "' | Nom Equipe: '" + nomEquipe;
    }
}