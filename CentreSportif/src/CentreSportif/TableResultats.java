package CentreSportif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableResultats {
    private Connexion cx;

    private PreparedStatement stmtExiste;
    private PreparedStatement stmtExisteEquipe;
    private PreparedStatement stmtInsert;
    private PreparedStatement stmtDelete;

    public TableResultats(Connexion cx) throws SQLException {
        this.cx = cx;

        stmtExiste = cx.getConnection().prepareStatement(
                "select dateResultat, nomEquipeA, nomEquipeB, scoreEquipeA, scoreEquipeB "
                        +"from resultat where dateResultat = ?");
        stmtExisteEquipe = cx.getConnection()
                .prepareStatement("select dateResultat, nomEquipeA, nomEquipeB, scoreEquipeA, scoreEquipeB "
                        + "from resultat where nomEquipeA = ? AND nomEquipeB = ? " + "order by dateResultat");
        stmtInsert = cx.getConnection()
                .prepareStatement("insert into resultat (dateResultat, nomEquipeA, scoreEquipeA, nomEquipeB, scoreEquipeB) "
                        + "values (?,?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from resultat where dateResultat = ?");

    }

    public boolean existe(Date dateResultat) throws SQLException {
        stmtExiste.setDate(1, Date.valueOf(dateResultat));
        ResultSet rs = stmtExiste.executeQuery();
        boolean resultatExiste = rs.next();
        rs.close();
        return resultatExiste;
    }

    public int supprimer(Date dateResultat) throws SQLException {
        stmtDelete.setDate(1, Date.valueOf(dateResultat));
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

    public TupleParticipant getResultat(Date dateResultat) throws SQLException
    {
        stmtExiste.setDate(1, Date.valueOf(dateResultat);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
            TupleResultat tupleResultat = new TupleResultat();
            tupleResultat.setDateResultat(dateResultat);
            tupleResultat.setNomEquipeA(rset.getString(1));
            tupleResultat.setScoreEquipeA(rset.getInt(2));
            tupleResultat.setNomEquipeA(rset.getString(3));
            tupleResultat.setScoreEquipeB(rset.getInt(4));

            rset.close();
            return tupleResultat;
        }
        else
        {
            return null;
        }
    }


}
