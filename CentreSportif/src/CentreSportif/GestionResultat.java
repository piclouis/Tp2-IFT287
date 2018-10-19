package CentreSportif;

import java.sql.*;

public class GestionResultat {
    private TableResultats resultats;
    private TableEquipes equipes;
    private Connexion cx;

    //Creation d'une instance

    public GestionResultat(TableResultats resultats, TableEquipes equipes) throws IFT287Exception {
        this.cx = resultats.getConnexion();
        if (resultats.getConnexion() != equipes.getConnexion())
            throw new IFT287Exception("Les instances de resultat et de equipe n'utilisent pas la même connexion au serveur");
        this.resultats = resultats;
        this.equipes = equipes;
    }

    /**
     * Ajout d'un nouveau resultat dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void ajouterResultat(String nomEquipeA, int scoreEquipeA, String nomEquipeB, int scoreEquipeB)
            throws SQLException, IFT287Exception, Exception {
        try {
            // Verifie si les equipes existent
            TupleEquipe tupleEquipeA = getEquipe(nomEquipeA);
            if(tupleEquipeA == null)
                throw new IFT287Exception("Nom d'équipe A : " + nomEquipeA + "inexistant");
            TupleEquipe tupleEquipeB = getEquipe(nomEquipeB);
            if(tupleEquipeB == null)
                throw new IFT287Exception("Nom d'équipe B : " + nomEquipeB + "inexistant");
            // Vérifie si le resultat existe déja
            if (resultats.existe(nomEquipeA, nomEquipeB))
                throw new IFT287Exception("Équipe A : " + nomEquipeA + " et équipe B : " + nomEquipeB + " déja existant.");


            // Ajout d'un resultat dans la table des livres
            resultats.ajouterResultat(nomEquipeA, scoreEquipeA, nomEquipeB, scoreEquipeB);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}
