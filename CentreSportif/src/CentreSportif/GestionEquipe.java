package CentreSportif;

import java.sql.*;
import java.util.ArrayList;

public class GestionEquipe {
    private TableEquipes equipes;
    private TableParticipants participants;
    private TableResultats resultats;
    private Connexion cx;

    public GestionEquipe(TableEquipes equipes, TableParticipants participants, TableResultats resultats) throws IFT287Exception {
        this.cx = equipes.getConnexion();
        if (equipes.getConnexion() != participants.getConnexion())
            throw new IFT287Exception("Les instances de TableEquipes et de TableParticipants n'utilisent pas la même connexion au serveur");
        if (equipes.getConnexion() != resultats.getConnexion())
            throw new IFT287Exception("Les instances de TableEquipes et de TableResultats n'utilisent pas la même connexion au serveur");
        this.resultats = resultats;
        this.participants = participants;
        this.equipes = equipes;
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
}