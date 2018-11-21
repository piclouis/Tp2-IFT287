package CentreSportif;

import org.bson.Document;


public class Resultat {

    private int idResultat; //ajouter au constructeur
    private int scoreEquipeA;
    private int scoreEquipeB;
    private Equipe equipeA; //not sure
    private Equipe equipeB; //not sure

    private int idEquipe;

    public Resultat() {
    }

    public Resultat(Document d)
    {
        idResultat = d.getInteger("idResultat");
        idEquipe = d.getInteger("idEquipe");
    }

    public Resultat(Equipe equipeA, int scoreEquipeA, Equipe equipeB, int scoreEquipeB) {
        this.setScoreEquipeA(scoreEquipeA);
        this.setScoreEquipeB(scoreEquipeB);
        this.setEquipeA(equipeA);
        this.setEquipeB(equipeB);
    }

    public int getIdResultat() { return idResultat; }

    public void setIdResultat(int idResultat) { this.idResultat = idResultat; }

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
// not sure
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
//
    public String toString() {    // à refaire
        return "Partie " + idResultat +
                "\n  Equipe " + equipeA.getNomEquipe() + ": " + scoreEquipeA + " points\n" +
                "  Equipe " + equipeB.getNomEquipe() + ": " + scoreEquipeB + " points ";
    }
    public Document toDocument()
    {
        return new Document().append("idResultat", idResultat) //*
                .append("EquipeA", equipeA)
                .append("scoreEquipeA", scoreEquipeA)
                .append("EquipeB", equipeB)
                .append("scoreEquipeB", scoreEquipeB);
    }
}
