package CentreSportif;

import java.sql.*;

public class GestionLigue {

    private TableLigues ligues;
    private TableEquipes equipes;
    private TableResultats resultats;
    private Connexion cx;

    public GestionLigue(TableLigues ligues, TableEquipes equipes, TableResultats resultats) throws IFT287Exception {
        this.cx = ligues.getConnexion();
        if (ligues.getConnexion() != equipes.getConnexion())
            throw new IFT287Exception("Les instances de TableLigues et de TableEquipes n'utilisent pas la même connexion au serveur");
        if (ligues.getConnexion() != resultats.getConnexion())
            throw new IFT287Exception("Les instances de TableLigues et de TableResultats n'utilisent pas la même connexion au serveur");
        this.resultats = resultats;
        this.equipes = equipes;
        this.ligues = ligues;
    }

    public void ajouterLigue(String nomLigue,int nbJoueurMaxParEquipe) throws SQLException, IFT287Exception, Exception {
        try {


        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void supprimerLigue(String nomLigue) throws SQLException, IFT287Exception, Exception {
        try {


        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }


    public void afficherLigue(String nomLigue) throws SQLException, IFT287Exception, Exception {
        try {


        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }


}