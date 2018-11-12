package CentreSportif;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Ligue {
    @Id
    @GeneratedValue
    private long m_id;

    private String nomLigue;
    private int nbJoueurMaxParEquipe;

    @OneToMany(mappedBy = "e_ligue")
    private List<Equipe> equipes;

    public Ligue() {
        this.equipes = new LinkedList<Equipe>();
    }

    public Ligue(String nomLigue, int nbJoueurMaxParEquipe) {
        this.setNomLigue(nomLigue);
        this.setNbJoueurMaxParEquipe(nbJoueurMaxParEquipe);
        this.equipes = new LinkedList<Equipe>();
    }

    public String getNomLigue() {
        return nomLigue;
    }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }

    public void setNbJoueurMaxParEquipe(int nbJoueurMaxParEquipe) {
        this.nbJoueurMaxParEquipe = nbJoueurMaxParEquipe;
    }
}
