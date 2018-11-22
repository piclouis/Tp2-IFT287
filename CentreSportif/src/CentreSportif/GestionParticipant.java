package CentreSportif;

public class GestionParticipant {
    private Connexion cx;
    private Participants participants;
    private Equipes equipes;

    /**
     * Création d'une instance
     */
    public GestionParticipant(Participants participants, Equipes equipes) throws IFT287Exception {
        this.cx = participants.getConnexion();
        if (participants.getConnexion() != equipes.getConnexion())
            throw new IFT287Exception("Les instances de Participants et de Equipes n'utilisent pas la même connexion au serveur");
        this.participants = participants;
        this.equipes = equipes;
    }

    /**
     * Ajout d'un nouveau participant dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void inscrireParticipant(String prenom, String nom, String motDePasse, int matricule)
            throws IFT287Exception, Exception {
        try {
            // Vérifie si le particpant existe déja
            if (participants.existe(matricule)) // a voir
                throw new IFT287Exception("Participant existe déjà: " + matricule); //a voir

            // Ajout d'un particpant.
            participants.inscrire(prenom, nom, motDePasse, matricule);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Suppression d'un partcipant de la base de données.
     */
    public void supprimerParticipant(int matricule) throws IFT287Exception, Exception {
        try {
            // Vérifie si le participant existe et qu'il fait partie d'une equipe
            Participant participant = participants.getParticipant(matricule);
            if (participant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);
            if (participant.getNomEquipe() != null)
                throw new IFT287Exception
                        ("Le participant avec la matricule " + matricule + " fait partie de l'équipe " + participant.getNomEquipe());

            // Suppression d'un participant
            boolean estSupprime = participants.supprimer(matricule);
            if (!estSupprime)
                throw new IFT287Exception("Participant " + matricule + " inexistant");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Permettre à un particpant de s'inscrire dans une équipe.
     */
    public void ajouterJoueur(String nomEquipe, int matricule) throws IFT287Exception, Exception {
        try {
            if (equipes.existe(nomEquipe))
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);
            Participant participant = participants.getParticipant(matricule);
            if (participants.existe(matricule))
                throw new IFT287Exception("Participant inexistant: " + matricule);

            if (participant.getNomEquipe() != null)
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " fait partie de l'équipe: " + participant.getNomEquipe());

            // ajout d'un joueur

            participants.ajouterEquipe(nomEquipe, matricule);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Permettre à un particpant de ce deinscrire dans une équipe.
     */
    public void supprimerJoueur(String nomEquipe, int matricule) throws IFT287Exception, Exception {
        try {
            Equipe equipe = equipes.getEquipe(nomEquipe);
            if (equipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);
            Participant participant = participants.getParticipant(matricule);
            if (participant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);

            if (participant.getNomEquipe() == null)
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " ne fait partie de l'équipe: " + nomEquipe);
            if (participant.getMatricule() == equipe.getCapitaine())
                throw new IFT287Exception("le matricule est celui du capitaine: " + matricule);

            participants.supprimerEquipe(matricule);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Accepter un joueur dans une equipe
     * @param nomEquipe
     * @param matricule
     * @throws IFT287Exception
     * @throws Exception
     */
    public void accepterJoueur(String nomEquipe, int matricule) throws IFT287Exception, Exception {
        try {
            Equipe equipe = equipes.getEquipe(nomEquipe);
            if (equipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);
            Participant participant = participants.getParticipant(matricule);
            if (participant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);

            if (nomEquipe.equals(participant.getNomEquipe()))
                throw new IFT287Exception("Il faut ajouter le participant dans l'equipe avant de l'accepter");
            if (participant.getNomEquipe() == null)
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " ne fait partie de l'équipe: " + nomEquipe);

            if (participant.getEstAccepte() != 0)
                throw new IFT287Exception("Participant: " + matricule + " deja accepte");

            participants.accepterJoueur(nomEquipe, matricule);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Refuser un joueur dans une equipe
     * @param nomEquipe
     * @param matricule
     * @throws IFT287Exception
     * @throws Exception
     */
    public void refuserJoueur(String nomEquipe, int matricule) throws IFT287Exception, Exception {
        try {
            Equipe equipe = equipes.getEquipe(nomEquipe);
            if (equipe == null)
                throw new IFT287Exception("Nom d'équipe inexistant: " + nomEquipe);
            Participant participant = participants.getParticipant(matricule);
            if (participant == null)
                throw new IFT287Exception("Participant inexistant: " + matricule);

            if (participant.equals(equipe.getCapitaine()))
                throw new IFT287Exception("Le participant est capitaine: " + matricule);

            if (participant.getNomEquipe() == null)
                throw new IFT287Exception("Le participant avec la matricule: " + matricule + " ne fait partie de l'équipe: " + nomEquipe);

            if (participant.getMatricule() == equipe.getCapitaine())
                throw new IFT287Exception("le matricule est celui du capitaine: " + matricule);

            if (participant.getEstAccepte() == 0)
                throw new IFT287Exception("Participant: " + matricule + " deja refuse");

            participants.refuserJoueur(nomEquipe, matricule);

        } catch (Exception e) {
            throw e;
        }
    }
}