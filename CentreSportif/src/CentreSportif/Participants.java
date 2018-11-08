package CentreSportif;

import javax.persistence.TypedQuery;
import java.util.List;

public class Participants {
    private Connexion cx;

    private TypedQuery<Participant> stmtExiste;
    private TypedQuery<Participant> stmtExisteEquipe;
    private TypedQuery<Participant> stmtExisteJoueursEquipe;

    public Participants(Connexion cx) {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select p from Participant p where p.matricule = :matricule", Participant.class);

        stmtExisteEquipe = cx.getConnection()
                .createQuery("select p from Participant p where nomEquipe = :nomEquipe", Participant.class);

        stmtExisteJoueursEquipe = cx.getConnection().createQuery(
                "select p from Participant p where nomEquipe = :nomEquipe and estAccepte = 1", Participant.class);
    }

    public boolean existe(int matricule) {
        stmtExiste.setParameter("matricule", matricule);
        return !stmtExiste.getResultList().isEmpty();
    }

    public boolean supprimer(Participant participant) {
        if (participant != null) {
            cx.getConnection().remove(participant);
            return true;
        }
        return false;
    }

    public Participant inscrire(Participant participant) {
        cx.getConnection().persist(participant);
        return participant;
    }


    public Participant ajouterEquipe(String nomEquipe) {
        stmtExisteEquipe.setParameter("nomEquipe", nomEquipe);
        return stmtExisteEquipe.getSingleResult();
    }


    public Participant supprimerEquipe(Participant participant) {
        if (participant.getNomEquipe() != null) {
            participant.setNomEquipe(null);
        }
        return participant;
    }

    public Participant accepterJoueur(Participant participant) {
        if (participant.getEstAccepter() == 0) {
            participant.setEstAccepter(1);
        }
        return participant;
    }

    public Participant refuserJoueur(Participant participant) {
        if (participant.getEstAccepter() == 1) {
            participant.setEstAccepter(0);
        }
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
    public Connexion getConnexion() {
        return cx;
    }
}