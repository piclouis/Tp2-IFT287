package CentreSportif;

import javax.persistence.TypedQuery;
import java.util.List;

public class Equipes {
    private Connexion cx;

    private TypedQuery<Equipe> stmtExiste;
    private TypedQuery<Equipe> stmtListeEquipesTriesLigue;    //  Liste des équipes trié par lignes
    private TypedQuery<Equipe> stmtListeEquipesLigue;        // Liste des équipes d'une ligue

    public Equipes(Connexion cx) {
        this.cx = cx;

        stmtExiste = cx.getConnection().createQuery(
                "select e from Equipe e where e.nomEquipe = :nomEquipe", Equipe.class);

        stmtListeEquipesTriesLigue = cx.getConnection().createQuery(
                "select e from Equipe e order by e.nomLigue", Equipe.class);

        stmtListeEquipesLigue = cx.getConnection().createQuery(
                "select e from Equipe e where e.nomLigue = :nomLigue", Equipe.class);

    }

    public boolean existe(String nomEquipe) {
        stmtExiste.setParameter("nomEquipe", nomEquipe);
        return !stmtExiste.getResultList().isEmpty();
    }

    public Equipe ajouter(Equipe equipe) {
        cx.getConnection().persist(equipe);
        return equipe;
    }

    //Lecture d'une equipe
    public Equipe getEquipe(String nomEquipe) {

        stmtExiste.setParameter("nomEquipe", nomEquipe);
        List<Equipe> equipes = stmtExiste.getResultList();

        if (!equipes.isEmpty()) {
            return equipes.get(0);
        } else {
            return null;
        }
    }

    public List<Equipe> getEquipes(String nomLigue) {
        stmtListeEquipesLigue.setParameter("nomLigue", nomLigue);
        return stmtListeEquipesLigue.getResultList();
    }

    public List<Equipe> getEquipes() {
        return stmtListeEquipesTriesLigue.getResultList();
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }

}
