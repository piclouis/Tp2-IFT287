package CentreSportif;

import org.bson.Document;


public class Resultat {

    private int idResultat; //ajouter au constructeur
    private int scoreEquipeA;
    private int scoreEquipeB;
    private String nomEquipeA; //not sure
    private String nomEquipeB; //not sure

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
        this.setNomEquipeA(nomEquipeA);
        this.setNomEquipeB(nomEquipeB);
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

    public String getNomEquipeA() {
        return nomEquipeA;
    }

    public void setNomEquipeA(String nomEquipeA) {
        this.nomEquipeA = nomEquipeA;
    }

    public String getNomEquipeB() {
        return nomEquipeB;
    }

    public void setNomEquipeB(String nomEquipeB) {
        this.nomEquipeB = nomEquipeB;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String toString() {
        return "Partie " + idResultat +
                "\n  Equipe " + nomEquipeA + ": " + scoreEquipeA + " points\n" +
                "  Equipe " +nomEquipeB + ": " + scoreEquipeB + " points ";
    }
    public Document toDocument()
    {
        return new Document().append("idResultat", idResultat) //*
                .append("nomEquipeA", nomEquipeA)
                .append("scoreEquipeA", scoreEquipeA)
                .append("nomEquipeB", nomEquipeB)
                .append("scoreEquipeB", scoreEquipeB);
    }
}
