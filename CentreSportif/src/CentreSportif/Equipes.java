package CentreSportif;

import static com.mongodb.client.model.Filters.*;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.client.MongoCursor;
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

    public List<Equipe> getEquipes(String nomLigue) {
        MongoCursor<Document> equipes = equipesCollection.find(eq("nomLigue", nomLigue)).iterator();
        List<Equipe> liste = new LinkedList<>();
        try
        {
            while (equipes.hasNext())
            {
                liste.add(new Equipe(equipes.next()));
            }
        }
        finally
        {
            equipes.close();
        }

        return liste;
    }

    public List<Equipe> getEquipes() {
        MongoCursor<Document> equipes = equipesCollection.find().iterator();
        List<Equipe> liste = new LinkedList<>();
        try
        {
            while (equipes.hasNext())
            {
                liste.add(new Equipe(equipes.next()));
            }
        }
        finally
        {
            equipes.close();
        }

        return liste;
    }
}