package CentreSportif;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Participants {
    private Connexion cx;

    private TypedQuery<Participant> stmtExiste;
    private TypedQuery<Participant> stmtUpdateNomEquipeNull;
    private TypedQuery<Participant> stmtUpdateNomEquipe;
    private TypedQuery<Participant> stmtUpdateAccepte;
    private TypedQuery<Participant> stmtUpdateRefuser;
    private TypedQuery<Participant> stmtgetJoueurEquipe;


    public Participants(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select matricule, nom, prenom, motDePasse, nomEquipe, estAccepte from participants where matricule = ?", Participant.class);

        stmtUpdateNomEquipe = cx.getConnection()
                .createQuery("update participants set nomEquipe = ? where matricule = ?", Participant.class);

        stmtUpdateNomEquipeNull = cx.getConnection()
                .createQuery("update participants set nomEquipe = ? where matricule = ?", Participant.class);

        stmtUpdateAccepte = cx.getConnection()
                .createQuery("update participants set estAccepte = 1 where matricule = ?", Participant.class);

        stmtUpdateRefuser = cx.getConnection()
                .createQuery("update participants set estAccepte = 0 where matricule = ?", Participant.class);

        stmtgetJoueurEquipe = cx.getConnection().createQuery(
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

    //TODO
    public void ajouterEquipe(String nomEquipe, int matricule)throws SQLException{
        stmtUpdateNomEquipe.setString(1, nomEquipe);
        stmtUpdateNomEquipe.setInt(2, matricule);

        stmtUpdateNomEquipe.executeUpdate();
    }
    //TODO
    public void supprimerEquipe(String nomEquipe,int matricule) throws SQLException {
        stmtUpdateNomEquipeNull.setNull(1, matricule, nomEquipe);
        stmtUpdateNomEquipeNull.setInt(2, matricule);

        stmtUpdateNomEquipeNull.executeUpdate();
    }
    //TODO
    public void accepterJoueur(String nomEquipe,int matricule) throws SQLException{
        //stmtUpdateAccepte.setString(1,nomEquipe);
        stmtUpdateAccepte.setInt(1, matricule);

        stmtUpdateAccepte.executeUpdate();
    }
    //TODO
    public void refuserJoueur(String nomEquipe,int matricule) throws SQLException{
        //stmtUpdateRefuser.setString(1,nomEquipe);
        stmtUpdateRefuser.setInt(1, matricule);

        stmtUpdateRefuser.executeUpdate();
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
        stmtgetJoueurEquipe.setParameter("nomEquipe", nomEquipe);
        List<Participant> participants = stmtgetJoueurEquipe.getResultList();
        return participants;
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }
}