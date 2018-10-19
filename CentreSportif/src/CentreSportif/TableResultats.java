package CentreSportif;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableResultats {
    private Connexion cx;

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtExisteEquipe;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public TableResultats() {
    }

    public TableResultats(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().prepareStatement(
                "select nomEquipeA, nomEquipeB, scoreEquipeA, scoreEquipeB "
                        + "from resultat where nomEquipeA = ? and nomEquipeB = ?");
        stmtExisteEquipe = cx.getConnection()
                .prepareStatement("select idResultat, nomEquipeA, nomEquipeB, scoreEquipeA, scoreEquipeB "
                        + "from resultat where nomEquipeA = ? AND nomEquipeB = ? " + "order by dateResultat");
        stmtInsert = cx.getConnection()
                .prepareStatement("insert into resultat (idResultat, nomEquipeA, scoreEquipeA, nomEquipeB, scoreEquipeB) "
                        + "values (DEFAULT,?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from resultat where dateResultat = ?");

    }

    public int supprimer(int idResultat) throws SQLException {
        stmtExiste.setInt(1, idResultat);
        return stmtDelete.executeUpdate();
    }

    public void ajouterResultat(String nomEquipeA, int scoreEquipeA, String nomEquipeB, int scoreEquipeB) throws SQLException {
        stmtInsert.setString(2, nomEquipeA);
        stmtInsert.setInt(2, scoreEquipeA);
        stmtInsert.setString(3, nomEquipeB);
        stmtInsert.setInt(4, scoreEquipeB);
        stmtInsert.executeUpdate();
    }

    //Lecture d'un resultat

    public TupleResultat getResultat(int idResultat) throws SQLException {
        stmtExiste.setInt(1, idResultat);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next()) {
            TupleResultat tupleResultat = new TupleResultat();
            tupleResultat.setIdResultat(idResultat);
            tupleResultat.setNomEquipeA(rset.getString(1));
            tupleResultat.setScoreEquipeA(rset.getInt(2));
            tupleResultat.setNomEquipeA(rset.getString(3));
            tupleResultat.setScoreEquipeB(rset.getInt(4));

            rset.close();
            return tupleResultat;
        } else {
            return null;
        }
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }



}
