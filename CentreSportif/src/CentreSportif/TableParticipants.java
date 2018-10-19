package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableParticipants {
    private Connexion cx;

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    private PreparedStatement stmtgetJoueurEquipe;

    public TableParticipants(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().prepareStatement(
                "select matricule, nom, prenom, motDePasse, nomEquipe from participants where matricule = ?");
        stmtInsert = cx.getConnection()
                .prepareStatement("insert into participants (matricule, nom, prenom, motDePasse, nomEquipe) "
                        + "values (?,?,?,?, null)");
        stmtDelete = cx.getConnection().prepareStatement("delete from participants where matricule = ?");

        stmtgetJoueurEquipe = cx.getConnection().prepareStatement(
                "select matricule, nom, prenom, motDePasse, nomEquipe from participants where nomEquipe = ?");

    }

    public boolean existe(int matricule) throws SQLException {
        stmtExiste.setInt(1, matricule);
        ResultSet rs = stmtExiste.executeQuery();
        boolean participantExiste = rs.next();
        rs.close();
        return participantExiste;
    }

    public int supprimer(int matricule) throws SQLException {
        stmtDelete.setInt(1, matricule);
        return stmtDelete.executeUpdate();
    }

    public void inscrire(String prenom, String nom, String motDePasse, int matricule) throws SQLException {
        stmtInsert.setInt(1, matricule);
        stmtInsert.setString(2, prenom);
        stmtInsert.setString(3, nom);
        stmtInsert.setString(4, motDePasse);

        stmtInsert.executeUpdate();
    }

    //Lecture d'un participant

    public TupleParticipant getParticipant(int matricule) throws SQLException {
        stmtExiste.setInt(1, matricule);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleParticipant tupleParticipant = new TupleParticipant();
            tupleParticipant.setMatricule(matricule);
            tupleParticipant.setNom(rset.getString(2));
            tupleParticipant.setPrenom(rset.getString(3));
            tupleParticipant.setMotDePasse(rset.getString(4));
            tupleParticipant.setNomEquipe(rset.getString(5));

            rset.close();
            return tupleParticipant;
        } else {
            return null;
        }
    }

    public ArrayList<TupleParticipant> getJoueursEquipe(String nomEquipe) throws SQLException {
        ArrayList<TupleParticipant> participants = new ArrayList<>();
        stmtgetJoueurEquipe.setString(1, nomEquipe);

        ResultSet rset = stmtgetJoueurEquipe.executeQuery();
        while(rset.next()) {
            TupleParticipant tupleParticipant = new TupleParticipant();
            tupleParticipant.setMatricule(rset.getInt(1));
            tupleParticipant.setNom(rset.getString(2));
            tupleParticipant.setPrenom(rset.getString(3));
            tupleParticipant.setMotDePasse(rset.getString(4));
            tupleParticipant.setNomEquipe(rset.getString(5));

            participants.add(tupleParticipant);
        }
        return participants;
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }
}
