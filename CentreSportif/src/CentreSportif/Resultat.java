package CentreSportif;

import org.bson.Document;


public class Resultat {
    private int scoreEquipeA;
    private int scoreEquipeB;
    private String nomEquipeA; //not sure
    private String nomEquipeB; //not sure

    public Resultat() {
    }

    public Resultat(Document d) {
        scoreEquipeA = d.getInteger("scoreEquipeA");
        scoreEquipeB = d.getInteger("scoreEquipeB");
        nomEquipeA = d.getString("nomEquipeA");
        nomEquipeB = d.getString("nomEquipeB");
    }

    public Resultat(String nomEquipeA, int scoreEquipeA, String nomEquipeB, int scoreEquipeB) {
        this.setScoreEquipeA(scoreEquipeA);
        this.setScoreEquipeB(scoreEquipeB);
        this.setNomEquipeA(nomEquipeA);
        this.setNomEquipeB(nomEquipeB);
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

    public String toString() {
        return "Partie " +
                "\n  Equipe " + nomEquipeA + ": " + scoreEquipeA + " points\n" +
                "  Equipe " + nomEquipeB + ": " + scoreEquipeB + " points ";
    }

    public Document toDocument() {
        return new Document() //*
                .append("nomEquipeA", nomEquipeA)
                .append("scoreEquipeA", scoreEquipeA)
                .append("nomEquipeB", nomEquipeB)
                .append("scoreEquipeB", scoreEquipeB);
    }
}
