package CentreSportif;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class Ligues {
    private Connexion cx;
    private TypedQuery<Ligue> stmtExiste;

    public Ligues(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select l from Ligue where l.nomLigue = :nomLigue", Ligue.class);
    }

    public boolean existe(String nomLigue) throws SQLException {
        stmtExiste.setParameter("nomLigue", nomLigue);
        return !stmtExiste.getResultList().isEmpty();
    }

    public boolean supprimer(Ligue ligue) throws SQLException {
        if(ligue != null) {
            cx.getConnection().remove(ligue);
            return true;
        }
        return false;
    }

    public Ligue ajouter(Ligue ligue) throws SQLException {
        cx.getConnection().persist(ligue);
        return ligue;
    }

    //Lecture d'une ligue

    public Ligue getLigue(String nomLigue) throws SQLException {
        stmtExiste.setParameter("nomLigue", nomLigue);
        List<Ligue> ligues = stmtExiste.getResultList();
        if (!ligues.isEmpty())
            return ligues.get(0);
        else
            return null;

    }

    public Connexion getConnexion() {
        return cx;
    }
}
