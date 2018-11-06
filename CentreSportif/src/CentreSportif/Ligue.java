package CentreSportif;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class Ligue {
    @Id
    @GeneratedValue
    private long m_id;

    private String nomLigue;
    private int nbJoueurMaxParEquipe;

    @OneToMany(mappedBy = "ligue")
    private List<Equipe> equipes;

    public Ligue() {
    }

    public Ligue(String nomLigue, int nbJoueurMaxParEquipe) {
        this.setNomLigue(nomLigue);
        this.setNbJoueurMaxParEquipe(nbJoueurMaxParEquipe);
    }

    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }

    public int getNbJoueurMaxParEquipe() {
        return nbJoueurMaxParEquipe;
    }

    public void setNbJoueurMaxParEquipe(int nbJoueurMaxParEquipe) {
        this.nbJoueurMaxParEquipe = nbJoueurMaxParEquipe;
    }
}
