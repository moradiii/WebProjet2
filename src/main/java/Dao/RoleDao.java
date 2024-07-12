package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Role;

public class RoleDao {
	
	private String URL = "jdbc:mysql://localhost:3306/courrierbd";
	private String Username = "root";
	private String Password = "";

	private static final String INSERT = "INSERT INTO `role`(`id`, `Nom`, `created_at`, `updated_at`)" + 
			" VALUES (?,?,?,?);";
	private static final String SELECT_ID = "SELECT * FROM role WHERE id = ?;";
	private static final String SELECT_ALL = "SELECT * FROM role;";
	private static final String DELETE = "DELETE FROM role WHERE id = ?;";
	private static final String UPDATE = "UPDATE role SET `Nom` = ?, `created_at` = ?, `updated_at` = ?";

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
	
	//insert
	public void insertRole(Role role) throws SQLException {
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(INSERT)){
			String req = "SELECT MAX(id) as maxid from role";
			int lastid = 0;
			try(PreparedStatement lstm = conn.prepareStatement(req);
					ResultSet res = lstm.executeQuery()){
				if(res.next()) {
					lastid = res.getInt("maxid");
				}
			}
			
			int newid = lastid + 1;
			pstm.setInt(1, newid);
			pstm.setString(2, role.getNom());			
			pstm.setTimestamp(3, role.getCreatedAt());
	        pstm.setTimestamp(4, role.getUpdatedAt());
						
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//update
	public boolean updateRole(Role role) throws SQLException {
	    boolean rowUpdated;
	    try (Connection conn = getConnection();
	            PreparedStatement pstm = conn.prepareStatement(UPDATE);) {
	    	pstm.setString(1, role.getNom());			
			pstm.setTimestamp(2, role.getCreatedAt());
	        pstm.setTimestamp(3, role.getUpdatedAt());
	        pstm.setInt(4, role.getId());

	        rowUpdated = pstm.executeUpdate() > 0;
	    }

	    return rowUpdated;
	}
	
	//select by id
	public Role selectRole(int id) {
		Role role = null;
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ID);){
			pstm.setInt(1, id);
			System.out.println(pstm);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				String Nom = rs.getString("Nom");
				Timestamp createdAt = rs.getTimestamp("created_at");
				Timestamp updatedAt= rs.getTimestamp("updated_at");
				
				
				role = new Role(id, Nom, createdAt, updatedAt);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return role;
	}
	
	//select all
	public List<Role> selectallRole() {
		List<Role> role = new ArrayList<>();
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL);){
			System.out.println(pstm);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String Nom = rs.getString("Nom");
				Timestamp createdAt = rs.getTimestamp("created_at");
				Timestamp updatedAt= rs.getTimestamp("updated_at");
				
				role.add(new Role(id, Nom, createdAt, updatedAt));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("nombre roles " + role.size());
		return role;
	}
	
	//delete
	public boolean deleteRole(String id) throws SQLException{
		boolean rowDeleted;
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(DELETE);){
			pstm.setString(1, id);
			rowDeleted = pstm.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}