package CentreSportif;

import java.sql.*;

public class GestionResultat {
    private TableResultats resultat;
    private TableEquipes equipe;
    private Connexion cx;

    //Creation d'une instance

    public GestionResultat(TableResultats resultat, TableEquipes equipe) throws IFT287Exception {
        this.cx = resultat.getConnexion();
        if (resultat.getConnexion() != equipe.getConnexion())
            throw new IFT287Exception("Les instances de resultat et de equipe n'utilisent pas la même connexion au serveur");
        this.resultat = resultat;
        this.equipe = equipe;
    }

    /**
     * Ajout d'un nouveau resultat dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void ajouterResultat(String nomEquipeA, int scoreEquipeA, String nomEquipeB, int scoreEquipeB)
            throws SQLException, IFT287Exception, Exception {
        try {
            // Vérifie si le resultat existe déja
            if (resultat.existe(nomEquipeA, nomEquipeB)) // a voir
                throw new IFT287Exception("Équipe A : " + nomEquipeA + " et équipe B : " + nomEquipeB + " déja existant.");

            // Ajout d'un resultat dans la table des livres
            resultat.ajouterResultat(nomEquipeA, scoreEquipeA, nomEquipeB, scoreEquipeB);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}
