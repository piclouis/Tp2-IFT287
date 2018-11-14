package CentreSportif;

import java.sql.*;

public class GestionParticipant {
    private Connexion cx;
    private TableParticipants participants;
    private TableEquipes equipes;

    /**
     * Création d'une instance
     */
    public GestionParticipant(TableParticipants participants, TableEquipes equipes) throws IFT287Exception {
        this.cx = participants.getConnexion();
        if (participants.getConnexion() != equipes.getConnexion())
            throw new IFT287Exception("Les instances de TableParticipants et de TableEquipes n'utilisent pas la même connexion au serveur");
        this.participants = participants;
        this.equipes = equipes;
    }

    /**
     * Ajout d'un nouveau participant dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void inscrireParticipant(String prenom, String nom, String motDePasse, int matricule)
            throws SQLException, IFT287Exception, Exception {
        try {
            // Vérifie si le particpant existe déja
            if (participants.existe(matricule)) // a voir
                throw new IFT287Exception("Participant existe déjà: " + matricule); //a voir

            // Ajout d'un particpant.
            participants.inscrire(prenom, nom, motDePasse, matricule);

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
            TupleParticipant tupleParticipant = participants.getParticipant(matricule);
            if (tupleParticipant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);
            if (tupleParticipant.getNomEquipe() != null)
                throw new IFT287Exception("Le participant avec la matricule " + matricule + " fait partie de l'équipe " + tupleParticipant.getNomEquipe());

            // Suppression d'un participant
            int nb = participants.supprimer(matricule);
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