package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import Model.CourrierA;

public class CADao {
	
	private String URL = "jdbc:mysql://localhost:3306/courrierbd";
	private String Username = "root";
	private String Password = "";

	private static final String INSERT = "INSERT INTO `courriera`(`id`, `Type`, `date`, `Objet`, `Affectations`, `Destinataires`, `Numero`, `Numero_BO`, `date_BO`, `Origine`, `Observation`, `Pieces_jointes`, `created_at`, `updated_at`)" + 
			" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_ID = "SELECT * FROM courriera WHERE id = ?;";
	private static final String SELECT_ALL = "SELECT * FROM courriera;";
	private static final String DELETE = "DELETE FROM courriera WHERE id = ?;";
	private static final String UPDATE = "UPDATE courriera SET `Type` = ?, `date` = ?, `Objet` = ?, `Affectations` = ?, `Destinataires` = ?, `Numero` = ?, `Numero_BO` = ?, `date_BO` = ?, `Origine` = ?, `Observation` = ?, `Pieces_jointes` = ?, `created_at` = ?, `updated_at` = ? WHERE id = ?;";

	protected Connection getConnection() {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, Username, Password);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// Insert
	public void insertCourrierA(CourrierA ca) throws SQLException {
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(INSERT)){
			String req = "SELECT MAX(id) as maxid from courriera";
			int lastid = 0;
			try(PreparedStatement lstm = conn.prepareStatement(req);
					ResultSet res = lstm.executeQuery()){
				if(res.next()) {
					lastid = res.getInt("maxid");
				}
			}
			
			int newid = lastid + 1;
			pstm.setInt(1, newid);
			pstm.setString(2, ca.getType());			
			pstm.setDate(3, ca.getDate());
			pstm.setString(4, ca.getObjet());
			pstm.setString(5, ca.getAffectations());
			pstm.setString(6, ca.getDestinataires());
			pstm.setInt(7, ca.getNum());
			pstm.setInt(8, ca.getNumBO());
			pstm.setDate(9, ca.getDateBO());
			pstm.setString(10, ca.getOrigine());
			pstm.setString(11, ca.getObservation());
			pstm.setString(12, ca.getPj());
			pstm.setTimestamp(13, ca.getCreatedAt());
			pstm.setTimestamp(14, ca.getUpdatedAt());
			
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Update
	public boolean updateCourrierA(CourrierA ca) throws SQLException {
	    boolean rowUpdated;
	    try (Connection conn = getConnection();
	            PreparedStatement pstm = conn.prepareStatement(UPDATE);) {
	    	pstm.setString(1, ca.getType());			
			pstm.setDate(2, ca.getDate());
			pstm.setString(3, ca.getObjet());
			pstm.setString(4, ca.getAffectations());
			pstm.setString(5, ca.getDestinataires());
			pstm.setInt(6, ca.getNum());
			pstm.setInt(7, ca.getNumBO());
			pstm.setDate(8, ca.getDateBO());
			pstm.setString(9, ca.getOrigine());
			pstm.setString(10, ca.getObservation());
			pstm.setString(11, ca.getPj());
			pstm.setTimestamp(12, ca.getCreatedAt());
			pstm.setTimestamp(13, ca.getUpdatedAt());
			pstm.setInt(14, ca.getId());

	        rowUpdated = pstm.executeUpdate() > 0;
	    }

	    return rowUpdated;
	}
	
	// Select by id
	public CourrierA selectCourrierA(int id) {
	    CourrierA ca = null;

	    try (Connection conn = getConnection();
	         PreparedStatement pstm = conn.prepareStatement(SELECT_ID);) {
	        pstm.setInt(1, id);
	        System.out.println(pstm);

	        ResultSet rs = pstm.executeQuery();

	        if (rs.next()) {
	            String Type = rs.getString("Type");
	            Date date = rs.getDate("date");
	            String Objet = rs.getString("Objet");
	            String Affectations = rs.getString("Affectations");
	            String Destinataires = rs.getString("Destinataires");
	            int Numero = rs.getInt("Numero");
	            int Numero_BO = rs.getInt("Numero_BO");
	            Date date_BO = rs.getDate("date_BO");
	            String Origine = rs.getString("Origine");
	            String Observation = rs.getString("Observation");
	            String Pieces_jointes = rs.getString("Pieces_jointes");
	            Timestamp created_at = rs.getTimestamp("created_at");
	            Timestamp updated_at = rs.getTimestamp("updated_at");

	            ca = new CourrierA(id, Type, date, Objet, Affectations, Destinataires,
	                    Numero, Numero_BO, date_BO, Origine, Observation, Pieces_jointes,
	                    created_at, updated_at);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ca;
	}

	// Select all
	public List<CourrierA> selectallCourrierA() {
		List<CourrierA> ca = new ArrayList<>();
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL);){
			System.out.println(pstm);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String Type = rs.getString("Type");
	            Date date = rs.getDate("date");
	            String Objet = rs.getString("Objet");
	            String Affectations = rs.getString("Affectations");
	            String Destinataires = rs.getString("Destinataires");
	            int Numero = rs.getInt("Numero");
	            int Numero_BO = rs.getInt("Numero_BO");
	            Date date_BO = rs.getDate("date_BO");
	            String Origine = rs.getString("Origine");
	            String Observation = rs.getString("Observation");
	            String Pieces_jointes = rs.getString("Pieces_jointes");
	            Timestamp created_at = rs.getTimestamp("created_at");
	            Timestamp updated_at = rs.getTimestamp("updated_at");

				ca.add(new CourrierA(id, Type, date, Objet, Affectations, Destinataires,
	                    Numero, Numero_BO, date_BO, Origine, Observation, Pieces_jointes,
	                    created_at, updated_at));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("nombre users " + ca.size());
		return ca;
	}
	
	// Delete
	public boolean deleteCourrierA(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(DELETE);) {
			pstm.setInt(1, id);
			rowDeleted = pstm.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}
