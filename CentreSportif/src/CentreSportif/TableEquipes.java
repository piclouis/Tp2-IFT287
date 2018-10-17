package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableEquipes {
    private Connexion cx;

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtUpdate;
    private PreparedStatement stmtDelete;
    private PreparedStatement stmtListeEquipesTriesLigue;    //  Liste des équipes trié par lignes
    private PreparedStatement stmtListeEquipesLigue;        // Liste des équipes d'une ligue

    public TableEquipes(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().prepareStatement(
                "select nomLigue, nomEquipe, matriculeCapitaine from equipe where nomEquipe = ?");
        stmtInsert = cx.getConnection()
                .prepareStatement("insert into equipe (nomLigue, nomEquipe, matriculeCapitaine) "
                        + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from equipe where nomEquipe = ?");

        stmtListeEquipesTriesLigue = cx.getConnection().prepareStatement(
                "select nomLigue, nomEquipe, matriculeCapitaine from equipe order by nomLigue");

        stmtListeEquipesTriesLigue = cx.getConnection().prepareStatement(
                "select nomLigue, nomEquipe, matriculeCapitaine from equipe where nomLigue = ?");

    }

    public boolean existe(String nomEquipe) throws SQLException {
        stmtExiste.setString(1, nomEquipe);
        ResultSet rs = stmtExiste.executeQuery();
        boolean equipeExiste = rs.next();
        rs.close();
        return equipeExiste;
    }

    public void ajouter(String nomLigue, String nomEquipe,int matriculeCapitaine) throws SQLException
    {
        stmtInsert.setString(1, nomLigue);
        stmtInsert.setString(2, nomEquipe);
        stmtInsert.setInt(3, matriculeCapitaine);
        stmtInsert.executeUpdate();
    }

}
