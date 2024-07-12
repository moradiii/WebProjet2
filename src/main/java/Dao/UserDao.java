package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.User;

public class UserDao {
	
	private String URL = "jdbc:mysql://localhost:3306/courrierbd";
	private String Username = "root";
	private String Password = "";

	private static final String INSERT = "INSERT INTO `user`(`id`, `Username`, `Email`, `PWD`, `Role_id`, `Service`, `Division`, `created_at`, `updated_at`)" + 
			" VALUES (?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_ID = "SELECT * FROM user WHERE id = ?;";
	private static final String SELECT_ALL = "SELECT * FROM user;";
	private static final String DELETE = "DELETE FROM user WHERE id = ?;";
	private static final String UPDATE = "UPDATE user SET `Username` = ?, `Email` = ?, `PWD` = ?, `Role_id` = ?, `Service` = ?, `Division` = ?, `created_at` = ?, `updated_at` = ?;";

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
	public void insertUser(User user) throws SQLException {
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(INSERT)){
			String req = "SELECT MAX(id) as maxid from user";
			int lastid = 0;
			try(PreparedStatement lstm = conn.prepareStatement(req);
					ResultSet res = lstm.executeQuery()){
				if(res.next()) {
					lastid = res.getInt("maxid");
				}
			}
			
			int newid = lastid + 1;
			pstm.setInt(1, newid);
			pstm.setString(2, user.getUsername());			
			pstm.setString(3, user.getEmail());
			pstm.setString(4, user.getPassword());
			pstm.setString(5, user.getRole());
			pstm.setString(6, user.getService());
			pstm.setString(7, user.getDivision());
			pstm.setTimestamp(8, user.getCreatedAt());
			pstm.setTimestamp(9, user.getUpdatedAt());
			
			
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//update
	public boolean updateUser(User user) throws SQLException {
	    boolean rowUpdated;
	    try (Connection conn = getConnection();
	            PreparedStatement pstm = conn.prepareStatement(UPDATE);) {
	    	pstm.setString(1, user.getUsername());			
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getRole());
			pstm.setString(5, user.getService());
			pstm.setString(6, user.getDivision());
			pstm.setTimestamp(7, user.getCreatedAt());
			pstm.setTimestamp(8, user.getUpdatedAt());
	        pstm.setInt(9, user.getId());

	        rowUpdated = pstm.executeUpdate() > 0;
	    }

	    return rowUpdated;
	}
	
	//select by id
	public User selectUser(int id) {
		User user = null;
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ID);){
			pstm.setInt(1, id);
			System.out.println(pstm);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				String Username = rs.getString("Username");
				String Email = rs.getString("Email");
				String Password = rs.getString("PWD");
				String Role = rs.getString("Role_id");
				String Service = rs.getString("Service");
				String Division = rs.getString("Division");
				Timestamp createdat = rs.getTimestamp("created_at");
				Timestamp updatedat = rs.getTimestamp("updated_at");
				
				user = new User(id, Username, Email, Password, Role, Service, Division, createdat, updatedat);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	//select all
	public List<User> selectallUser() {
		List<User> user = new ArrayList<>();
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL);){
			System.out.println(pstm);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("Username");
				String email = rs.getString("Email");
				String password = rs.getString("PWD");
				String role = rs.getString("Role_id");
				String service = rs.getString("Service");
				String division = rs.getString("Division");
				Timestamp createdat = rs.getTimestamp("created_at");
				Timestamp updatedat = rs.getTimestamp("updated_at");
				
				user.add(new User(id, username, email, password, role, service, division, createdat, updatedat));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("nombre users " + user.size());
		return user;
	}
	
	//delete
	public boolean deleteUser(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(DELETE);){
			pstm.setInt(1, id);
			rowDeleted = pstm.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}