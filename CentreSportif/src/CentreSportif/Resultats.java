package CentreSportif;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Resultats {
    private Connexion cx;

    private TypedQuery<Resultat> stmtResultatEquipe;

    public Resultats() {
    }

    public Resultats(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtResultatEquipe = cx.getConnection()
                .createQuery("select r "
                        + "from Resultat r where nomEquipeA = :nomEquipe OR nomEquipeB = :nomEquipe ", Resultat.class);

    }

    public Resultat ajouterResultat(Resultat resultat) throws SQLException {
        cx.getConnection().persist(resultat);
        return resultat;
    }

    public List<Resultat> getResultats(String nomEquipe) throws SQLException {
        stmtResultatEquipe.setParameter("nomEquipe", nomEquipe);
        List<Resultat> resultats = stmtResultatEquipe.getResultList();
        return resultats;
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }

    public int nbVictoires(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats) {
            if (resultat.getEquipeA().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeA() > resultat.getScoreEquipeB())
                cpt++;

            if (resultat.getEquipeB().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeB() > resultat.getScoreEquipeA())
                cpt++;
        }

        return cpt;
    }

    public int nbDefaites(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats) {
            if (resultat.getEquipeA().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeA() < resultat.getScoreEquipeB())
                cpt++;

            if (resultat.getEquipeB().getNomEquipe().equals(nomEquipe) && resultat.getScoreEquipeB() < resultat.getScoreEquipeA())
                cpt++;
        }

        return cpt;
    }

    public int nbPartiesNulles(String nomEquipe, List<Resultat> listResultats) {
        int cpt = 0;

        for (Resultat resultat : listResultats)
            if ((resultat.getEquipeA().getNomEquipe().equals(nomEquipe) || resultat.getEquipeB().getNomEquipe().equals(nomEquipe)) &&
                    resultat.getScoreEquipeA() == resultat.getScoreEquipeB())
                cpt++;

        return cpt;
    }

}
