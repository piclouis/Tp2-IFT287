package CentreSportif;

import static com.mongodb.client.model.Filters.*;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

public class Equipes {
    private Connexion cx;
    private MongoCollection<Document> equipesCollection;

    public Equipes(Connexion cx) {
        this.cx = cx;
        equipesCollection = cx.getDatabase().getCollection("Equipes");
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String nomEquipe) {
        return equipesCollection.find(eq("nomEquipe", nomEquipe)).first() != null;
    }

    public void ajouter(String nomLigue, String nomEquipe, int capitaine) {
        Equipe e = new Equipe(nomEquipe, capitaine, nomLigue);
        equipesCollection.insertOne(e.toDocument());
    }

    //Lecture d'une equipe
    public Equipe getEquipe(String nomEquipe) {

        Document d = equipesCollection.find(eq("nomEquipe", nomEquipe)).first();
        if(d != null)
        {
            return new Equipe(d);
        }
        return null;
    }
/*
    public List<Equipe> getEquipes(String nomLigue) {
        stmtListeEquipesLigue.setParameter("nomLigue", nomLigue);
        return stmtListeEquipesLigue.getResultList();
    }

    public List<Equipe> getEquipes() {
        return stmtListeEquipesTriesLigue.getResultList();
    }
*/
}
