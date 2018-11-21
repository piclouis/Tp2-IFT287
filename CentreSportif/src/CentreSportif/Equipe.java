package CentreSportif;


import org.bson.Document;

public class Equipe {

    private int idEquipe;
    private String nomEquipe;
    private int capitaine;

    private String nomLigue;
    private int matricule;
    private int idResultats;

    public Equipe() {
    }

    public Equipe(Document d)
    {
        nomEquipe = d.getString("nomEquipe");
        nomLigue = d.getString("nomLigue");
        capitaine = d.getInteger("capitaine");
        matricule = d.getInteger("matricule");
        idResultats = d.getInteger("idResultat");

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

    public int getMatricule() { return matricule; }

    public int getIdResultats() { return idResultats; }

    public String toString() {      // à refaire
        return "Nom Ligue: '" + nomLigue +
                "' | Nom Equipe: '" + nomEquipe;
    }

    public void ajouterJoueur() {
        matricule++;
    }

    public void supprimerJoueur() {
        matricule--;
    }

    public Document toDocument()
    {
        return new Document().append("nomLigue", nomLigue)
                .append("nomEquipe", nomEquipe)
                .append("capitaine", capitaine)
                .append("matricule", matricule)
                .append("idResultat", idResultats);
    }
}