package CentreSportif;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Resultat {
    @Id
    @GeneratedValue
    private long m_id;

    private int scoreEquipeA;
    private int scoreEquipeB;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Equipe equipeA;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Equipe equipeB;

    public Resultat() {
    }

    public Resultat(String nomEquipeA, int scoreEquipeA, String nomEquipeB, int scoreEquipeB) {
        this.setScoreEquipeA(scoreEquipeA);
        this.setScoreEquipeB(scoreEquipeB);
        this.setEquipeA(equipeA);
        this.setEquipeB(equipeB);
    }

    public int getScoreEquipeA() {
        return scoreEquipeA;
    }

    public void setScoreEquipeA(int scoreEquipeA) {
        this.scoreEquipeA = scoreEquipeA;
    }

    public int getScoreEquipeB() {
        return scoreEquipeB;
    }

    public void setScoreEquipeB(int scoreEquipeB) {
        this.scoreEquipeB = scoreEquipeB;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    @Override
    public String toString() {
        return "Partie " + m_id +
                "\n  Equipe " + equipeA.getNomEquipe() + ": " + scoreEquipeA + " points\n" +
                "  Equipe " + equipeB.getNomEquipe() + ": " + scoreEquipeB + " points ";
    }
}
