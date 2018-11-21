package CentreSportif;

import org.bson.Document;

public class Ligue {

    private int idLigue;
    private String nomLigue;
    private int nbJoueurMaxParEquipe;

    private String nomEquipe;

    public Ligue() {
    }

    public Ligue(Document d)
    {
        nomLigue = d.getString("nomLigue");
        nbJoueurMaxParEquipe = d.getInteger("nbJoueurMaxParEquipe");
        nomEquipe = d.getString("nomEquipe");
    }

    public Ligue(String nomLigue, int nbJoueurMaxParEquipe) {
        this.setNomLigue(nomLigue);
        this.setNbJoueurMaxParEquipe(nbJoueurMaxParEquipe);
    }

    public String getNomLigue() { return nomLigue; }

    public void setNomLigue(String nomLigue) {
        this.nomLigue = nomLigue;
    }

    public void setNbJoueurMaxParEquipe(int nbJoueurMaxParEquipe) {
        this.nbJoueurMaxParEquipe = nbJoueurMaxParEquipe;
    }

    public int getNbJoueurMaxParEquipe() { return nbJoueurMaxParEquipe; }

    public String getNomEquipe() { return nomEquipe; }

    public Document toDocument()
    {
        return new Document().append("nomLigue", nomLigue)
                .append("nbJoueurMaxParEquipe", nbJoueurMaxParEquipe)
                .append("nomEquipe", nomEquipe);

    }
}
