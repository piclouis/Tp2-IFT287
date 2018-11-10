package CentreSportif;

import javax.persistence.TypedQuery;
import java.util.List;

public class Participants {
    private Connexion cx;

    private TypedQuery<Participant> stmtExiste;
    private TypedQuery<Participant> stmtExisteJoueursEquipe;

    public Participants(Connexion cx) {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select p from Participant p where p.matricule = :matricule", Participant.class);

<<<<<<< HEAD
=======
        stmtExisteEquipe = cx.getConnection()
                .createQuery("select p from Participant p where p.p_equipe.nomEquipe = :nomEquipe", Participant.class);

>>>>>>> 0a12707180480491953473a2394775fa28e724cb
        stmtExisteJoueursEquipe = cx.getConnection().createQuery(
                "select p from Participant p where p.p_equipe.nomEquipe = :nomEquipe and p.estAccepte = 1", Participant.class);
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


<<<<<<< HEAD
    public Participant ajouterEquipe(Equipe equipe, Participant participant){
        if(participant.getEquipe() == null){
            participant.setEquipe(equipe);
        }
        return participant;

=======
    public Participant ajouterEquipe(Participant participant, Equipe equipe) {
        participant.setP_equipe(equipe);
        return participant;
>>>>>>> 0a12707180480491953473a2394775fa28e724cb
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
    public Connexion getConnexion() {
        return cx;
    }
}