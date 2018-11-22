package CentreSportif;


import org.bson.Document;

public class Equipe {

    private int idEquipe;
    private String nomEquipe;
    private int capitaine;

    private String nomLigue;

    public Equipe() {
    }

    public Equipe(Document d)
    {
        nomEquipe = d.getString("nomEquipe");
        nomLigue = d.getString("nomLigue");
        capitaine = d.getInteger("capitaine");
    }

    public Equipe(String nomEquipe, int capitaine, String nomLigue) {

        this.nomLigue = nomLigue;
        this.nomEquipe = nomEquipe;
        this.capitaine = capitaine;
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

    public void setNomLigue(String nomLigue) { this.nomLigue = nomLigue; }

    public int getCapitaine() {
        return capitaine;
    }


    public String toString() {      // à refaire
        return "Nom Ligue: '" + nomLigue +
                "' | Nom Equipe: '" + nomEquipe;
    }

    public Document toDocument()
    {
        return new Document().append("nomLigue", nomLigue)
                .append("nomEquipe", nomEquipe)
                .append("capitaine", capitaine);
    }
}