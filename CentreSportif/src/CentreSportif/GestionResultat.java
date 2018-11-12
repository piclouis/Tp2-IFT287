package CentreSportif;

public class GestionResultat {
    private Resultats resultats;
    private Equipes equipes;
    private Connexion cx;

    //Creation d'une instance

    public GestionResultat(Resultats resultats, Equipes equipes) throws IFT287Exception {
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
            throws IFT287Exception, Exception {
        try {
            cx.demarreTransaction();

            // Verifie si les equipes existent
            Equipe equipeA = equipes.getEquipe(nomEquipeA);
            if (equipeA == null)
                throw new IFT287Exception("Nom d'équipe A : " + nomEquipeA + " inexistant");
            Equipe equipeB = equipes.getEquipe(nomEquipeB);
            if (equipeB == null)
                throw new IFT287Exception("Nom d'équipe B : " + nomEquipeB + " inexistant");

            if (!equipeA.getE_ligue().equals(equipeB.getE_ligue()))
                throw new IFT287Exception("Les deux equipes ne font pas partie de la même ligue.");

            // Ajout d'un resultat dans la table des livres
            Resultat resultat = new Resultat(equipeA, scoreEquipeA, equipeB, scoreEquipeB);
            resultats.ajouterResultat(resultat);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}
