package CentreSportif;

import static com.mongodb.client.model.Filters.*;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

public class Resultats {

    private Connexion cx;
    private MongoCollection<Document> resultatsCollection;

    public Resultats(Connexion cx) {
        this.cx = cx;
        resultatsCollection = cx.getDatabase().getCollection("Resultats");
    }
    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }
    public boolean ajouterResultat(Resultat idResultat) {
        return resultatsCollection.find(eq("idResultat", idResultat)).first() != null;
    }

    public Resultat getResultats(int idResultat) {
        Document d = resultatsCollection.find(eq("idResultat", idResultat)).first();
        if(d != null)
        {
            return new Resultat(d);
        }
        return null;
    }
//todo
    public int nbVictoires(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats) {
            if (resultat.getEquipeA().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeA() > resultat.getScoreEquipeB())
                cpt++;

            if (resultat.getEquipeB().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeB() > resultat.getScoreEquipeA())
                cpt++;
        }

        return cpt;
    }

    public int nbDefaites(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats) {
            if (resultat.getEquipeA().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeA() < resultat.getScoreEquipeB())
                cpt++;

            if (resultat.getEquipeB().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeB() < resultat.getScoreEquipeA())
                cpt++;
        }

        return cpt;
    }

    public int nbPartiesNulles(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats)
            if ((resultat.getEquipeA().getNomEquipe().equals(nomEquipe) || resultat.getEquipeB().getNomEquipe().equals(nomEquipe)) &&
                    resultat.getScoreEquipeA() == resultat.getScoreEquipeB())
                cpt++;

        return cpt;
    }

}
