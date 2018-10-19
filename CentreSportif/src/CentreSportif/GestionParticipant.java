package CentreSportif;

import java.sql.*;

public class GestionParticipant {
    private Connexion cx;
    private TableParticipants participant;
    private TableEquipes equipe;

    /**
     * Création d'une instance
     */
    public GestionParticipant(TableParticipants participant, TableEquipes equipe) throws IFT287Exception {
        this.cx = participant.getConnexion();
        if (participant.getConnexion() != equipe.getConnexion())
            throw new IFT287Exception("Les instances de TableParticipants et de TableEquipes n'utilisent pas la même connexion au serveur");
        this.participant = participant;
        this.equipe = equipe;
    }

    /**
     * Ajout d'un nouveau participant dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void inscrireParticipant(String prenom, String nom, String motDePasse, int matricule)
            throws SQLException, IFT287Exception, Exception {
        try {
            // Vérifie si le particpant existe déja
            if (participant.existe(matricule)) // a voir
                throw new IFT287Exception("Participant existe déjà: " + matricule); //a voir

            // Ajout d'un particpant.
            participant.inscrire(prenom, nom, motDePasse, matricule);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Suppression d'un partcipant de la base de données.
     */
    public void supprimerParticipant(int matricule) throws SQLException, IFT287Exception, Exception {
        try {
            // Vérifie si le participant existe et qu'il fait partie d'une equipe
            TupleParticipant tupleParticipant = participant.getParticipant(matricule);
            if (tupleParticipant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);
            if (tupleParticipant.getNomEquipe() == null)
                throw new IFT287Exception("Participant " + matricule + " ne fait pas partie d'une equipe");

            // Suppression d'un participant
            int nb = participant.supprimer(matricule);
            if (nb == 0)
                throw new IFT287Exception("Participant " + matricule + " inexistant");

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}