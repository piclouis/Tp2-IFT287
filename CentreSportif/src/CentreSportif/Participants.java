package CentreSportif;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Participants {
    private Connexion cx;

    private TypedQuery<Participant> stmtExiste;

    /*
    private TypedQuery<Participant> stmtUpdateNomEquipeNull;
    private TypedQuery<Participant> stmtUpdateNomEquipe;
    private TypedQuery<Participant> stmtUpdateAccepte;
    */

    private TypedQuery<Participant> stmtExisteEquipe;
    private TypedQuery<Participant> stmtExisteJoueursEquipe;


    public Participants(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select p from Participant p where p.matricule = :matricule", Participant.class);

        stmtExisteEquipe = cx.getConnection()
                .createQuery("select p from Participant p where nomEquipe = :nomEquipe", Participant.class);
/*
        stmtUpdateNomEquipeNull = cx.getConnection()
                .createQuery("update participants set nomEquipe = ? where matricule = ?", Participant.class);

        stmtUpdateAccepte = cx.getConnection()
                .createQuery("update participants set estAccepte = 1 where matricule = ?", Participant.class);
*/
        stmtExisteJoueursEquipe = cx.getConnection().createQuery(
                "select p from Participant p where nomEquipe = :nomEquipe and estAccepte = 1", Participant.class);


    }

    public boolean existe(int matricule) throws SQLException {
        stmtExiste.setParameter("matricule", matricule);
        return !stmtExiste.getResultList().isEmpty();
    }

    public boolean supprimer(Participant participant) throws SQLException {
        if(participant != null) {
            cx.getConnection().remove(participant);
            return true;
        }
        return false;
    }

    public Participant inscrire(Participant participant) throws SQLException {
        cx.getConnection().persist(participant);
        return participant;
    }


    public Participant ajouterEquipe(Participant participant, String nomEquipe)throws SQLException{
        if(participant.getNomEquipe() == null){
            participant.setNomEquipe(nomEquipe); // ca ou setNomEquipe(participant.getNomEquipe)
        }
        return participant;
    }


    public Participant supprimerEquipe(Participant participant) throws SQLException {
        if(participant.getNomEquipe() != null) {
            participant.setNomEquipe(null);
        }
        return participant;
    }

    public Participant accepterJoueur(Participant participant) throws SQLException{
        if(participant.getEstAccepter() == 0){
            participant.setEstAccepter(1);
        }
        return participant;
    }

    public Participant refuserJoueur(Participant participant) throws SQLException{
        if(participant.getEstAccepter() == 1){
            participant.setEstAccepter(0);
        }
        return participant;
    }

    //Lecture d'un participant

    public Participant getParticipant(int matricule) throws SQLException {
        stmtExiste.setParameter("matricule", matricule);
        List<Participant> participants = stmtExiste.getResultList();
        if (!participants.isEmpty()) {
            return participants.get(0);
        } else {
            return null;
        }
    }

    public List<Participant> getJoueursEquipe(String nomEquipe) throws SQLException {
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