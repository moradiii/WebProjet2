package Controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDao;
import Model.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
       
    public UserServlet() {
    	this.userDao = new UserDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		this.doGet(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String act = ""; 
		String action = request.getServletPath();
		
		
		switch(action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
            try {
                insertUser(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;	
		
		default:
			
			// tjrs actualisé l'affichage lister apres modif et delete
			listUser(request, response);
			
			// case "/update":		
			if (request.getParameter("act") != null) { // à utiliser pour update et delete
				act = request.getParameter("act");
				System.out.println("act ===>>>> " + act);
				if (act.equals("update") ) {
					try {
						System.out.println("case update step1"); //<<<<<<<<<<<<<<<<<<<< pour le suivi de l'execution
						updateUser(request, response);
					} catch (ServletException | IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Problem updating!!!");
					}
				}
			}
			
			
			break;
		}
		
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		List<User> listUtilist = userDao.selectallUser();
		request.setAttribute("listUtilist", listUtilist);
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		System.out.println("size liste servlet " + listUtilist.size());
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String Username = request.getParameter("Username");
		String Email = request.getParameter("Email");
		String Password = request.getParameter("PWD");
		String Role = request.getParameter("Role_id");
		String Service = request.getParameter("Service");
		String Division = request.getParameter("Division");
		String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

	    Timestamp createdat = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedat = Timestamp.valueOf(updatedAtStr);

	    User newUser = new User(id, Username, Email, Password, Role, Service, Division, 
	    		createdat, updatedat);
	    userDao.insertUser(newUser);

	    response.sendRedirect("list");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		if (id != 0) {
	    	User existingUser = userDao.selectUser(id);
	        if (existingUser != null) {
	            request.setAttribute("utilist", existingUser);
	        }
	    }
	    RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
	    disp.forward(request, response);
	}

	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String Username = request.getParameter("Username");
		String Email = request.getParameter("Email");
		String Password = request.getParameter("PWD");
		String Role = request.getParameter("Role_id");
		String Service = request.getParameter("Service");
		String Division = request.getParameter("Division");
		String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

	    Timestamp createdat = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedat = Timestamp.valueOf(updatedAtStr);

	    User updatedUtilist = new User(id, Username, Email, Password, Role, Service, 
	    		Division, createdat, updatedat);

	    boolean rowUpdated = userDao.updateUser(updatedUtilist);

	    if (rowUpdated) {
	        System.out.println("User with ID " + id + " updated successfully.");
	    } else {
	        System.out.println("Failed to update User with ID " + id + ".");
	    }

	    response.sendRedirect("list");
	}


}

