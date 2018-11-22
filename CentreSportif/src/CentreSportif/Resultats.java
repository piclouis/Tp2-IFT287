package CentreSportif;

import static com.mongodb.client.model.Filters.*;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.client.MongoCursor;
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

    public void ajouterResultat(Equipe equipeA, int scoreEquipeA, Equipe equipeB, int scoreEquipeB) {
        Resultat r = new Resultat(equipeA, scoreEquipeA, equipeB, scoreEquipeB);
        resultatsCollection.insertOne(r.toDocument());
    }

    public List<Resultat> getResultats(String nomEquipe) {
        MongoCursor<Document> resultats = resultatsCollection.find
                (and(eq("nomEquipeA", nomEquipe), eq("nomEquipeB", nomEquipe))).iterator();
        List<Resultat> liste = new LinkedList<>();
        try
        {
            while (resultats.hasNext())
            {
                liste.add(new Resultat(resultats.next()));
            }
        }
        finally
        {
            resultats.close();
        }

        return liste;
    }

    public int nbVictoires(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats) {
            if (resultat.getNomEquipeA().equals(nomEquipe) && resultat.getScoreEquipeA() > resultat.getScoreEquipeB())
                cpt++;

            if (resultat.getNomEquipeB().equals(nomEquipe) && resultat.getScoreEquipeB() > resultat.getScoreEquipeA())
                cpt++;
        }

        return cpt;
    }

    public int nbDefaites(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats) {
            if (resultat.getNomEquipeA().equals(nomEquipe) && resultat.getScoreEquipeA() < resultat.getScoreEquipeB())
                cpt++;

            if (resultat.getNomEquipeB().equals(nomEquipe) && resultat.getScoreEquipeB() < resultat.getScoreEquipeA())
                cpt++;
        }

        return cpt;
    }

    public int nbPartiesNulles(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats)
            if ((resultat.getNomEquipeA().equals(nomEquipe) || resultat.getNomEquipeB().equals(nomEquipe)) &&
                    resultat.getScoreEquipeA() == resultat.getScoreEquipeB())
                cpt++;

        return cpt;
    }

}
