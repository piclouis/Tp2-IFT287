package CentreSportif;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.MongoCollection;


public class Participants {

    private MongoCollection<Document> participantsCollection;
    private Connexion cx;

    public Participants(Connexion cx) {
        this.cx = cx;
        participantsCollection = cx.getDatabase().getCollection("Participants");

    }
    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(int matricule) {
        return participantsCollection.find(eq("matricule", matricule)).first() != null;
    }


    public boolean supprimer(int matricule) {
        return participantsCollection.deleteOne(eq("matricule", matricule)).getDeletedCount() > 0;
    }

    public void inscrire(String prenom, String nom, String motDePasse, int matricule) {
        Participant p = new Participant(prenom, nom, motDePasse, matricule);
        participantsCollection.insertOne(p.toDocument());

    }

    public void ajouterEquipe(String nomEquipe, int matriculeCapitaine) {
        participantsCollection.updateOne(eq("matricule", matriculeCapitaine), set("nomEquipe", nomEquipe));
    }

    public void supprimerEquipe(int matriculeCapitaine) {
        participantsCollection.updateOne
                (eq("matricule", matriculeCapitaine), combine(set("nomEquipe", null), set("estAccepte", 0)));

    }

    public void accepterJoueur(String nomEquipe, int matriculeCapitaine) {
        participantsCollection.updateOne(eq("matricule", matriculeCapitaine), set("estAccepte", 1));
    }

    public void refuserJoueur(String nomEquipe, int matriculeCapitaine) {
        participantsCollection.updateOne(eq("matricule", matriculeCapitaine), set("estAccepte", 0));
    }

    //Lecture d'un participant

    public Participant getParticipant(int matricule) {
        Document d = participantsCollection.find(eq("matricule", matricule)).first();
        if(d != null)
        {
            return new Participant(d);
        }
        return null;
    }

    public List<Participant> getJoueursEquipe(String nomEquipe) {
        MongoCursor<Document> participants = participantsCollection.find
                (and(eq("nomEquipe", nomEquipe), eq("estAccepte" , 1) )).iterator();
        List<Participant> liste = new LinkedList<>();
        try
        {
            while (participants.hasNext())
            {
                liste.add(new Participant(participants.next()));
            }
        }
        finally
        {
            participants.close();
        }

        return liste;
    }

}