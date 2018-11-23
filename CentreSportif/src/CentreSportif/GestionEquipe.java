package CentreSportif;

import com.sun.org.apache.regexp.internal.RE;

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

            Equipe equipe = equipes.getEquipe(nomEquipe);

            if (equipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);

            if (!participants.existe(equipe.getCapitaine()))
                throw new IFT287Exception("Matricule inexistante: " + equipe.getCapitaine());

            Participant capitaine = participants.getParticipant(equipe.getCapitaine());

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
            List<Resultat> listResulats = resultats.getResultats(nomEquipe);
            System.out.println("Liste des parties");
            for (Resultat resultat : listResulats)
                System.out.println(resultat.toString());

        } catch (Exception e) {
            throw e;
        }
    }

    public void afficherEquipes() {
        try {
            System.out.println();
            for (Equipe equipe : equipes.getEquipes())
                System.out.println(equipe.toString());
        } catch (Exception e) {

            throw e;
        }

    }

    public void ajouterEquipe(String nomLigue, String nomEquipe, int matriculeCapitaine) throws IFT287Exception {
        try {

            // Vérifie si la ligue existe
            if (!ligues.existe(nomLigue))
                throw new IFT287Exception("Ligue inexistante: " + nomLigue);

            // Vérifie si l'equipe existe déja
            if (equipes.existe(nomEquipe))
                throw new IFT287Exception("Equipe existe déjà: " + nomEquipe);

            // Verifie si le capitaine existe
            Participant participant = participants.getParticipant(matriculeCapitaine);
            if (participant == null)
                throw new IFT287Exception("Participant inexistant: " + matriculeCapitaine);

            if (participant.getNomEquipe() != null)
                throw new IFT287Exception("Participant est deja dans une equipe.");

            // Ajout d'un equipe.
            equipes.ajouter(nomLigue, nomEquipe, matriculeCapitaine);

            participants.ajouterEquipe(nomEquipe, matriculeCapitaine);
            participants.accepterJoueur(nomEquipe, matriculeCapitaine);

        } catch (Exception e) {
            throw e;
        }
    }
}