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

import Dao.RoleDao;
import Model.Role;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RoleDao roleDao;
       
    public RoleServlet() {
    	this.roleDao = new RoleDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = ""; 
		String action = request.getServletPath();
		
		
		switch(action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
            try {
                insertRole(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
		case "/delete":
			try {
				deleteRole(request, response);
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
			listRole(request, response);
			
			// case "/update":		
			if (request.getParameter("act") != null) { // à utiliser pour update et delete
				act = request.getParameter("act");
				System.out.println("act ===>>>> " + act);
				if (act.equals("update") ) {
					try {
						System.out.println("case update step1"); //<<<<<<<<<<<<<<<<<<<< pour le suivi de l'execution
						updateRole(request, response);
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
	
	private void listRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Role> listRole = roleDao.selectallRole();
		request.setAttribute("listRole", listRole);
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		System.out.println("size liste servlet " + listRole.size());
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		
	}
	
	private void insertRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String Nom = request.getParameter("Nom");
		String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

	    Timestamp createdAt = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedAt = Timestamp.valueOf(updatedAtStr);

	    Role newRole = new Role(id,Nom, createdAt, updatedAt);
	    roleDao.insertRole(newRole);

	    response.sendRedirect("list");
	}
	
	private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		roleDao.deleteRole(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    if (id != 0) {
	        Role existingRole = roleDao.selectRole(id);
	        if (existingRole != null) {
	            request.setAttribute("utilist", existingRole);
	        }
	    }
	    RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
	    disp.forward(request, response);
	}

	
	private void updateRole(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String Nom = request.getParameter("Nom");
		String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

		Timestamp createdAt = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedAt = Timestamp.valueOf(updatedAtStr);

	    Role updatedRole = new Role(id, Nom, createdAt, updatedAt);

	    boolean rowUpdated = roleDao.updateRole(updatedRole);

	    if (rowUpdated) {
	        System.out.println("Role with ID " + id + " updated successfully.");
	    } else {
	        System.out.println("Failed to update Role with ID " + id + ".");
	    }

	    response.sendRedirect("list");
	}


}

