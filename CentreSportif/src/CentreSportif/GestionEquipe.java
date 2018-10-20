package CentreSportif;

import java.sql.*;
import java.util.ArrayList;

public class GestionEquipe {
    private TableLigues ligues;
    private TableEquipes equipes;
    private TableParticipants participants;
    private TableResultats resultats;
    private Connexion cx;

    public GestionEquipe(TableLigues ligues, TableEquipes equipes, TableParticipants participants, TableResultats resultats) throws IFT287Exception {
        this.cx = equipes.getConnexion();
        if (equipes.getConnexion() != participants.getConnexion())
            throw new IFT287Exception("Les instances de TableEquipes et de TableParticipants n'utilisent pas la même connexion au serveur");
        if (equipes.getConnexion() != resultats.getConnexion())
            throw new IFT287Exception("Les instances de TableEquipes et de TableResultats n'utilisent pas la même connexion au serveur");
        this.resultats = resultats;
        this.participants = participants;
        this.equipes = equipes;
        this.ligues = ligues;
    }

    public void afficherEquipe(String nomEquipe) throws SQLException, IFT287Exception {
        try {
            TupleEquipe tupleEquipe = equipes.getEquipe(nomEquipe);

            if (tupleEquipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);

            if(!participants.existe(tupleEquipe.getMatriculeCapitaine()))
                throw new IFT287Exception("Matricule inexistante: " + tupleEquipe.getMatriculeCapitaine());

            TupleParticipant capitaine = participants.getParticipant(tupleEquipe.getMatriculeCapitaine());

            System.out.printf("Nom d'equipe :" + tupleEquipe.getNomEquipe() +
                    "\nNom de ligue : " + tupleEquipe.getNomLigue() +
                    "\nCapitaine : " + capitaine.getPrenom() + " " + capitaine.getNom());

            ArrayList<TupleParticipant> listParticipants = participants.getJoueursEquipe(nomEquipe);

            if (listParticipants.isEmpty())
                System.out.printf("Aucun joueur");

            else {
                System.out.printf("Liste des joueurs");
                for (TupleParticipant participant : listParticipants)
                    System.out.printf(participant.toString());
            }

            System.out.printf("Liste des parties");

        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void ajouterEquipe(String nomLigue, String nomEquipe, int matriculeCapitaine) throws IFT287Exception, SQLException {
        try {
            /*// Vérifie si l'equipe existe déja
            if (equipes.existe(nomEquipe))
                throw new IFT287Exception("Equipe existe déjà: " + nomEquipe);

            // Vérifie si la ligue existe
            if (!ligues.existe(nomLigue))
                throw new IFT287Exception("Ligue inexistante: " + nomEquipe);

            // Verifie si le capitaine existe
            if (participants.existe(matriculeCapitaine))
                throw new IFT287Exception("Participant inexistant: " + nomEquipe);*/

            // Ajout d'un particpant.
            equipes.ajouter(nomLigue, nomEquipe, matriculeCapitaine);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}