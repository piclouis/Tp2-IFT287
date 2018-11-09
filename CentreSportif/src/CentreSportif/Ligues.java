package CentreSportif;

import javax.persistence.TypedQuery;
import java.util.List;

public class Ligues {
    private Connexion cx;
    private TypedQuery<Ligue> stmtExiste;

    public Ligues(Connexion cx) {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select l from Ligue l where l.nomLigue = :nomLigue", Ligue.class);
    }

    public boolean existe(String nomLigue) {
        stmtExiste.setParameter("nomLigue", nomLigue);
        return !stmtExiste.getResultList().isEmpty();
    }

    public boolean supprimer(Ligue ligue) {
        if(ligue != null) {
            cx.getConnection().remove(ligue);
            return true;
        }
        return false;
    }

    public Ligue ajouter(Ligue ligue) {
        cx.getConnection().persist(ligue);
        return ligue;
    }

    //Lecture d'une ligue

    public Ligue getLigue(String nomLigue) {
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
