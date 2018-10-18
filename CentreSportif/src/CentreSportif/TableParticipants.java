package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableParticipants {
    private Connexion cx;

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;

    public TableParticipants(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().prepareStatement(
                "select matricule, nom, prenom, motDePasse, nomEquipe from participant where matricule = ?");
        stmtInsert = cx.getConnection()
                .prepareStatement("insert into participant (matricule, nom, prenom, motDePasse) "
                        + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from participant where matricule = ?");

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

    public void inscrire(String prenom, String nom, String motDePasse, int matricule) throws SQLException
    {
        stmtInsert.setString(1, prenom);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, motDePasse);
        stmtInsert.setInt(4, matricule);
        stmtInsert.executeUpdate();
    }

    //Lecture d'un participant

    public TupleParticipant getParticipant(int matricule) throws SQLException
    {
        stmtExiste.setInt(1, matricule);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
            TupleParticipant tupleParticipant = new TupleParticipant();
            tupleParticipant.setMatricule(matricule);
            tupleParticipant.setNom(rset.getString(1));
            tupleParticipant.setPrenom(rset.getString(2));
            tupleParticipant.setMotDePasse(rset.getString(3));
            tupleParticipant.setNomEquipe(rset.getString(4));

            rset.close();
            return tupleParticipant;
        }
        else
        {
            return null;
        }
    }
}
