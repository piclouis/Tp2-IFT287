package CentreSportif;

import static com.mongodb.client.model.Filters.*;
import java.util.List;
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

    public void inscrire(int matricule, String nom, String prenom, String motDePasse) {
        Participant p = new Participant(prenom, nom, motDePasse, matricule);
        participantsCollection.insertOne(p.toDocument());

    }

/*

    public Participant ajouterEquipe(Participant participant, Equipe equipe) {
        participant.setP_equipe(equipe);
        return participant;
    }


    public Participant supprimerEquipe(Participant participant) {

        participant.setP_equipe(null);

        return participant;
    }

    public Participant accepterJoueur(Participant participant, Equipe equipe) {
        participant.setEstAccepte(1);
        equipe.ajouterJoueur(participant);

        return participant;
    }

    public Participant refuserJoueur(Participant participant, Equipe equipe) {
        participant.setEstAccepte(0);
        equipe.supprimerJoueur(participant);

        return participant;
    }

    //Lecture d'un participant

    public Participant getParticipant(int matricule) {
        stmtExiste.setParameter("matricule", matricule);
        List<Participant> participants = stmtExiste.getResultList();
        if (!participants.isEmpty()) {
            return participants.get(0);
        } else {
            return null;
        }
    }

    public List<Participant> getJoueursEquipe(String nomEquipe) {
        stmtExisteJoueursEquipe.setParameter("nomEquipe", nomEquipe);
        List<Participant> participants = stmtExisteJoueursEquipe.getResultList();
        return participants;
    }

    /**
     * Retourner la connexion associée.
     */

}