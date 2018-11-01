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
            if (participants.existe(matricule))
                throw new IFT287Exception("Participant existe déjà: " + matricule);

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
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " fait partie de l'équipe " + tupleParticipant.getNomEquipe());

            // Suppression d'un participant
            int nb = participants.supprimer(matricule);
            if (nb == 0)
                throw new IFT287Exception("Participant: " + matricule + " inexistant");

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
    /**
     * Permettre à un particpant de s'inscrire dans une équipe.
     */
    public void ajouterJoueur(String nomEquipe, int matricule ) throws SQLException, IFT287Exception, Exception {
        try {
            TupleEquipe tupleEquipe = equipes.getEquipe(nomEquipe);
            if (tupleEquipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);
            TupleParticipant tupleParticipant = participants.getParticipant(matricule);
            if (tupleParticipant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);

            if (tupleParticipant.getNomEquipe() != null)
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " fait partie de l'équipe: " + tupleParticipant.getNomEquipe());

            // ajout d'un joueur
            participants.ajouterEquipe(nomEquipe, matricule);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
    /**
     * Permettre à un particpant de s'inscrire dans une équipe.
     */
    public void supprimerJoueur(String nomEquipe, int matricule ) throws SQLException, IFT287Exception, Exception {
        try {
            TupleEquipe tupleEquipe = equipes.getEquipe(nomEquipe);
            if (tupleEquipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);
            TupleParticipant tupleParticipant = participants.getParticipant(matricule);
            if (tupleParticipant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);

            if (tupleParticipant.getNomEquipe()== null )
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " ne fait partie de l'équipe: " + nomEquipe);

            int nb = participants.supprimerEquipe(nomEquipe, matricule);
            if (nb == 0)
                throw new IFT287Exception("Participant: " + matricule + " inexistant");

            participants.supprimerEquipe(nomEquipe, matricule);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void accepterJoueur(String nomEquipe, int matricule ) throws SQLException, IFT287Exception, Exception {
        try {
            boolean accepter = true;
            if (accepter)
                throw new IFT287Exception("Participant refuse: " + matricule);

            //participants.accepterJoueur(nomEquipe, matricule);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

}