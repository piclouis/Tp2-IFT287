package CentreSportif;

import java.util.List;

public class GestionLigue {
    private Connexion cx;

    private Ligues ligues;
    private Equipes equipes;
    private Resultats resultats;

    public GestionLigue(Ligues ligues, Equipes equipes, Resultats resultats) throws IFT287Exception {
        this.cx = ligues.getConnexion();
        if (ligues.getConnexion() != equipes.getConnexion())
            throw new IFT287Exception("Les instances de Ligues et de Equipes n'utilisent pas la même connexion au serveur");
        if (ligues.getConnexion() != resultats.getConnexion())
            throw new IFT287Exception("Les instances de Ligues et de Resultats n'utilisent pas la même connexion au serveur");
        this.resultats = resultats;
        this.equipes = equipes;
        this.ligues = ligues;
    }

    public void ajouterLigue(String nomLigue, int nbJoueurMaxParEquipe) throws IFT287Exception, Exception {
        try {
            cx.demarreTransaction();

            if (ligues.existe(nomLigue))
                throw new IFT287Exception("Ligue " + nomLigue + " déjà existante");

            Ligue ligue = new Ligue(nomLigue, nbJoueurMaxParEquipe);
            ligues.ajouter(ligue);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void supprimerLigue(String nomLigue) throws IFT287Exception, Exception {
        try {
            cx.demarreTransaction();

            // Vérifier si la ligue existe
            Ligue ligue = ligues.getLigue(nomLigue);
            if (ligue == null)
                throw new IFT287Exception("Ligue inexistant: " + nomLigue);
            // Vérifier si des équipes sont inscrite dans la ligue
            if (!equipes.getEquipes(nomLigue).isEmpty())
                throw new IFT287Exception("Il existe une ou plusieurs equipes faisant parties de cette ligue.");

            ligues.supprimer(ligue);
            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void afficherLigue(String nomLigue) throws IFT287Exception, Exception {
        try {
            cx.demarreTransaction();

            if (!ligues.existe(nomLigue))
                throw new IFT287Exception("Ligue inexistant: " + nomLigue);

            System.out.println("Ligue : " + nomLigue);
            List<Equipe> listeEquipes = equipes.getEquipes(nomLigue);

            for (Equipe equipe : listeEquipes) {
                List<Resultat> listResultats = resultats.getResultats(equipe.getNomEquipe());
                listResultats.addAll(equipes.getEquipe(equipe.getNomEquipe()).getResultats());

                int nbVictoires = resultats.nbVictoires(equipe.getNomEquipe(), listResultats);
                int nbDefaites = resultats.nbDefaites(equipe.getNomEquipe(), listResultats);
                int nbPartieNulles = resultats.nbPartiesNulles(equipe.getNomEquipe(), listResultats);

                System.out.println("Nom d'équipe: " + equipe.getNomEquipe());
                System.out.println("  Nombre de victoires: " + nbVictoires);
                System.out.println("  Nombre de defaites: " + nbDefaites);
                System.out.println("  Nombre de parties nulles: " + nbPartieNulles);
            }

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}