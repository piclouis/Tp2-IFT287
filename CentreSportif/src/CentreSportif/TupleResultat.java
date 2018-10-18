package CentreSportif;

public class TupleResultat {

    private Date dateResultat;
    private String nomEquipeA;
    private String nomEquipeB;
    private int scoreEquipeA;
    private int scoreEquipeB;

    public TupleResultat() {
    }

    public TupleResultat(Date dateResultat, String nomEquipeA, String nomEquipeB, int scoreEquipeA, int scoreEquipeB) {
        this.setDateResultat(dateResultat);
        this.setNomEquipeA(nomEquipeA);
        this.setNomEquipeB(nomEquipeB);
        this.setScoreEquipeA(scoreEquipeA);
        this.setScoreEquipeB(scoreEquipeB);

    }

    public Date getDateResultat() {
        return dateResultat;
    }

    public void setDatePartie(Date dateResultat) {
        this.dateResultat = dateResultat;
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
}
