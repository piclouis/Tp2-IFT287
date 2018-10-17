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

    public void inscrire(int matricule, String nom, String motDePasse, String nomEquipe) throws SQLException
    {
        stmtInsert.setInt(1, matricule);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, motDePasse);
        stmtInsert.setString(4, nomEquipe);
        stmtInsert.executeUpdate();
    }
}
