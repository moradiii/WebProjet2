package Controler;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CDDao;
import Model.CourrierD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

@WebServlet("/cd")
public class CDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CDDao cdDao;
       
    public CDServlet() {
    	this.cdDao = new CDDao();
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
                insertCourrierD(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
		case "/delete":
			try {
				deleteCourrierD(request, response);
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
			listCourrierD(request, response);
			
			// case "/update":		
			if (request.getParameter("act") != null) { // à utiliser pour update et delete
				act = request.getParameter("act");
				System.out.println("act ===>>>> " + act);
				if (act.equals("update") ) {
					try {
						System.out.println("case update step1"); //<<<<<<<<<<<<<<<<<<<< pour le suivi de l'execution
						updateCourrierD(request, response);
					} catch (ServletException | IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Problem updating!!!");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			break;
		}
		
	}
	
	private void listCourrierD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<CourrierD> listCourrierD = cdDao.selectallCourrierD();
		request.setAttribute("listCourrierD", listCourrierD);
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		System.out.println("size liste servlet " + listCourrierD.size());
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		
	}
	
	private void insertCourrierD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String type = request.getParameter("Type");
	    String dateStr = request.getParameter("date");
	    String dateTraitStr = request.getParameter("date_trait");
	    String objet = request.getParameter("Objet");
	    String affectations = request.getParameter("Affectations");
	    String destinataires = request.getParameter("Destinataires");
	    int numero = Integer.parseInt(request.getParameter("Numero"));
	    String observation = request.getParameter("Observation");
	    String piecesJointes = request.getParameter("Pieces_jointes");
	    String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

	    // Parsing the date and timestamp strings to the correct formats
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Date date = new Date(dateFormat.parse(dateStr).getTime());
	    Date dateTrait = new Date(dateFormat.parse(dateTraitStr).getTime());

	    Timestamp createdAt = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedAt = Timestamp.valueOf(updatedAtStr);

	    CourrierD newCourrierD = new CourrierD(id, type, date, objet, affectations, destinataires,
	            numero, dateTrait, observation, piecesJointes, createdAt, updatedAt);

	    cdDao.insertCourrierD(newCourrierD);

	    response.sendRedirect("list");
	}
	
	private void deleteCourrierD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		cdDao.deleteCourrierD(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    if (id != 0) {
	    	CourrierD existingCourrierD = cdDao.selectCourrierD(id);
	        if (existingCourrierD != null) {
	            request.setAttribute("CourrierD", existingCourrierD);
	        }
	    }
	    RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
	    disp.forward(request, response);
	}

	
	private void updateCourrierD(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException, ParseException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String type = request.getParameter("Type");
	    String dateStr = request.getParameter("date");
	    String dateTraitStr = request.getParameter("date_trait");
	    String objet = request.getParameter("Objet");
	    String affectations = request.getParameter("Affectations");
	    String destinataires = request.getParameter("Destinataires");
	    int numero = Integer.parseInt(request.getParameter("Numero"));
	    String observation = request.getParameter("Observation");
	    String piecesJointes = request.getParameter("Pieces_jointes");
	    String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

	    // Parsing the date and timestamp strings to the correct formats
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date(dateFormat.parse(dateStr).getTime());
	    Date dateTrait = new Date(dateFormat.parse(dateTraitStr).getTime());

	    Timestamp createdAt = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedAt = Timestamp.valueOf(updatedAtStr);

	    CourrierD updatedCourrierD = new CourrierD(id, type, date, objet, affectations, destinataires,
	            numero, dateTrait, observation, piecesJointes, createdAt, updatedAt);

	    boolean rowUpdated = cdDao.updateCourrierD(updatedCourrierD);

	    if (rowUpdated) {
	        System.out.println("Mail Departed with ID " + id + " updated successfully.");
	    } else {
	        System.out.println("Failed to update Mail Departed with ID " + id + ".");
	    }

	    response.sendRedirect("list");
	}


}

