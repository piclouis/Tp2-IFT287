package CentreSportif;

import java.util.List;

public class GestionEquipe {
    private Ligues ligues;
    private Equipes equipes;
    private Participants participants;
    private Resultats resultats;
    private Connexion cx;

    public GestionEquipe(Ligues ligues, Equipes equipes, Participants participants, Resultats resultats) throws IFT287Exception {
        this.cx = equipes.getConnexion();
        if (equipes.getConnexion() != participants.getConnexion())
            throw new IFT287Exception("Les instances de Equipes et de Participants n'utilisent pas la même connexion au serveur");
        if (equipes.getConnexion() != resultats.getConnexion())
            throw new IFT287Exception("Les instances de Equipes et de Resultats n'utilisent pas la même connexion au serveur");
        this.resultats = resultats;
        this.participants = participants;
        this.equipes = equipes;
        this.ligues = ligues;
    }

    public void afficherEquipe(String nomEquipe) throws IFT287Exception {
        try {
            cx.demarreTransaction();

            Equipe equipe = equipes.getEquipe(nomEquipe);

            if (equipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);

            if (!participants.existe(equipe.getCapitaine().getMatricule()))
                throw new IFT287Exception("Matricule inexistante: " + equipe.getCapitaine().getMatricule());

            Participant capitaine = participants.getParticipant(equipe.getCapitaine().getMatricule());

            System.out.println("\nNom d'equipe : " + equipe.getNomEquipe() +
                    "\nNom de ligue : " + equipe.getNomLigue() +
                    "\nCapitaine : " + capitaine.getPrenom() + " " + capitaine.getNom());
            System.out.println();
            List<Participant> listParticipants = participants.getJoueursEquipe(nomEquipe);

            if (listParticipants.isEmpty())
                System.out.println("Aucun joueur");

            else {
                System.out.println("Liste des joueurs");
                for (Participant participant : listParticipants) {
                    System.out.println(participant.toString());
                }
            }

            System.out.println();

            List<Resultat> listResultats = resultats.getResultats(nomEquipe);

            System.out.println("Liste des parties");
            for (Resultat resultat : listResultats)
                System.out.println(resultat.toString());

        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void afficherEquipes() {
        List<Equipe> listEquipes = equipes.getEquipes();
        System.out.println("");
        for (Equipe equipe : listEquipes)
            System.out.println(equipe.toString());
    }

    //TODO
    public void ajouterEquipe(String nomLigue, String nomEquipe, int matriculeCapitaine) throws IFT287Exception {
        try {
            cx.demarreTransaction();

            // Vérifie si l'equipe existe déja
            if (equipes.existe(nomEquipe))
                throw new IFT287Exception("Equipe existe déjà: " + nomEquipe);

            // Vérifie si la ligue existe
            if (!ligues.existe(nomLigue))
                throw new IFT287Exception("Ligue inexistante: " + nomEquipe);

            // Verifie si le capitaine existe
            if (!participants.existe(matriculeCapitaine))
                throw new IFT287Exception("Participant inexistant: " + matriculeCapitaine);

            // Ajout d'un equipe.
            Equipe equipe = new Equipe(nomLigue, nomEquipe);
            Participant participant = participants.getParticipant(matriculeCapitaine);
            equipes.ajouter(equipe);
            participants.ajouterEquipe(nomEquipe);
            participants.accepterJoueur(participant);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}