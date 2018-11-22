package CentreSportif;

import static com.mongodb.client.model.Filters.*;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

public class Ligues {

    private Connexion cx;
    private MongoCollection<Document> liguesCollection;

    public Ligues(Connexion cx) {
        this.cx = cx;
        liguesCollection = cx.getDatabase().getCollection("Ligues");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String nomLigue) {
        return liguesCollection.find(eq("nomLigue", nomLigue)).first() != null;
    }

    public boolean supprimer(String nomLigue) {
        return liguesCollection.deleteOne(eq("nomLigue", nomLigue)).getDeletedCount() > 0;
    }

    public void ajouter(String nomLigue, int nbJoueurMaxParEquipe) {
        Ligue l = new Ligue(nomLigue, nbJoueurMaxParEquipe);
        liguesCollection.insertOne(l.toDocument());
    }

    //Lecture d'une ligue

    public Ligue getLigue(String nomLigue) {
        Document d = liguesCollection.find(eq("nomLigue", nomLigue)).first();
        if(d != null)
        {
            return new Ligue(d);
        }
        return null;

    }


}
