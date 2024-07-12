package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import Model.CourrierD;

public class CDDao {

    private String URL = "jdbc:mysql://localhost:3306/courrierbd";
    private String Username = "root";
    private String Password = "";

    private static final String INSERT = "INSERT INTO `courrierd`(`id`, `Type`, `date`, `Objet`, `Ampiliations`, `Destinataires`, `Numero`, `date_trait`, `Observation`, `Pieces_jointes`, `created_at`, `updated_at`)" + 
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_ID = "SELECT * FROM courrierd WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM courrierd;";
    private static final String DELETE = "DELETE FROM courrierd WHERE id = ?;";
    private static final String UPDATE = "UPDATE courrierd SET `Type` = ?, `date` = ?, `Objet` = ?, `Ampiliations` = ?, `Destinataires` = ?, `Numero` = ?, `date_trait` = ?, `Observation` = ?, `Pieces_jointes` = ?, `created_at` = ?, `updated_at` = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, Username, Password);
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Insert
    public void insertCourrierD(CourrierD cd) throws SQLException {
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(INSERT)){
            String req = "SELECT MAX(id) as maxid from courrierd";
            int lastid = 0;
            try(PreparedStatement lstm = conn.prepareStatement(req);
                ResultSet res = lstm.executeQuery()){
                if(res.next()) {
                    lastid = res.getInt("maxid");
                }
            }

            int newid = lastid + 1;
            pstm.setInt(1, newid);
            pstm.setString(2, cd.getType());
            pstm.setDate(3, cd.getDate());
            pstm.setString(4, cd.getObjet());
            pstm.setString(5, cd.getAmpiliations());
            pstm.setString(6, cd.getDestinataires());
            pstm.setInt(7, cd.getNum());
            pstm.setDate(8, cd.getDateTrait());
            pstm.setString(9, cd.getObservation());
            pstm.setString(10, cd.getPj());
            pstm.setTimestamp(11, cd.getCreatedAt());
            pstm.setTimestamp(12, cd.getUpdatedAt());

            pstm.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Update
    public boolean updateCourrierD(CourrierD cd) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(UPDATE);) {
            pstm.setString(1, cd.getType());
            pstm.setDate(2, cd.getDate());
            pstm.setString(3, cd.getObjet());
            pstm.setString(4, cd.getAmpiliations());
            pstm.setString(5, cd.getDestinataires());
            pstm.setInt(6, cd.getNum());
            pstm.setDate(7, cd.getDateTrait());
            pstm.setString(8, cd.getObservation());
            pstm.setString(9, cd.getPj());
            pstm.setTimestamp(10, cd.getCreatedAt());
            pstm.setTimestamp(11, cd.getUpdatedAt());
            pstm.setInt(12, cd.getId());

            rowUpdated = pstm.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    // Select by id
    public CourrierD selectCourrierD(int id) {
        CourrierD cd = null;

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(SELECT_ID);) {
            pstm.setInt(1, id);
            System.out.println(pstm);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String Type = rs.getString("Type");
                Date date = rs.getDate("date");
                String Objet = rs.getString("Objet");
                String Ampiliations = rs.getString("Ampiliations");
                String Destinataires = rs.getString("Destinataires");
                int Numero = rs.getInt("Numero");
                Date date_trait = rs.getDate("date_trait");
                String Observation = rs.getString("Observation");
                String Pieces_jointes = rs.getString("Pieces_jointes");
                Timestamp created_at = rs.getTimestamp("created_at");
                Timestamp updated_at = rs.getTimestamp("updated_at");

                cd = new CourrierD(id, Type, date, Objet, Ampiliations, Destinataires,
                        Numero, date_trait, Observation, Pieces_jointes,
                        created_at, updated_at);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cd;
    }

    // Select all
    public List<CourrierD> selectallCourrierD() {
        List<CourrierD> cd = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(SELECT_ALL);) {
            System.out.println(pstm);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String Type = rs.getString("Type");
                Date date = rs.getDate("date");
                String Objet = rs.getString("Objet");
                String Ampiliations = rs.getString("Ampiliations");
                String Destinataires = rs.getString("Destinataires");
                int Numero = rs.getInt("Numero");
                Date date_trait = rs.getDate("date_trait");
                String Observation = rs.getString("Observation");
                String Pieces_jointes = rs.getString("Pieces_jointes");
                Timestamp created_at = rs.getTimestamp("created_at");
                Timestamp updated_at = rs.getTimestamp("updated_at");

                cd.add(new CourrierD(id, Type, date, Objet, Ampiliations, Destinataires,
                        Numero, date_trait, Observation, Pieces_jointes,
                        created_at, updated_at));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("nombre courriers " + cd.size());
        return cd;
    }

    // Delete
    public boolean deleteCourrierD(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(DELETE);) {
            pstm.setInt(1, id);
            rowDeleted = pstm.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
