package Controler;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CADao;
import Model.CourrierA;

@WebServlet("/ca")
public class CAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CADao caDao;
       
    public CAServlet() {
    	this.caDao = new CADao();
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
                insertCourrierA(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
		case "/delete":
			try {
				deleteCourrierA(request, response);
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
			listCourrierA(request, response);
			
			// case "/update":		
			if (request.getParameter("act") != null) { // à utiliser pour update et delete
				act = request.getParameter("act");
				System.out.println("act ===>>>> " + act);
				if (act.equals("update") ) {
					try {
						System.out.println("case update step1"); //<<<<<<<<<<<<<<<<<<<< pour le suivi de l'execution
						updateCourrierA(request, response);
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
	
	private void listCourrierA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<CourrierA> listCourrierA = caDao.selectallCourrierA();
		request.setAttribute("listCourrierA", listCourrierA);
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		System.out.println("size liste servlet " + listCourrierA.size());
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
		disp.forward(request, response);
		
	}
	
	private void insertCourrierA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
	    String Type = request.getParameter("Type");
	    String dateStr = request.getParameter("date");
        String Objet = request.getParameter("Objet");
        String Affectations = request.getParameter("Affectations");
        String Destinataires = request.getParameter("Destinataires");
        int Numero = Integer.parseInt(request.getParameter("Numero"));
        int Numero_BO = Integer.parseInt(request.getParameter("Numero_BO"));
        String date_BOStr = request.getParameter("date_BO");
        String Origine = request.getParameter("Origine");
        String Observation = request.getParameter("Observation");
        String Pieces_jointes = request.getParameter("Pieces_jointes");
        String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date(dateFormat.parse(dateStr).getTime());
	    Date date_BO = new Date(dateFormat.parse(date_BOStr).getTime());
	    
	    Timestamp createdAt = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedAt = Timestamp.valueOf(updatedAtStr);

	    CourrierA newCourrierA = new CourrierA(id, Type, date, Objet, Affectations, Destinataires,
                Numero, Numero_BO, date_BO, Origine, Observation, Pieces_jointes,
                createdAt, updatedAt);
	    caDao.insertCourrierA(newCourrierA);

	    response.sendRedirect("list");
	}
	
	private void deleteCourrierA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		caDao.deleteCourrierA(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    if (id != 0) {
	        CourrierA existingCourrierA = caDao.selectCourrierA(id);
	        if (existingCourrierA != null) {
	            request.setAttribute("CourrierA", existingCourrierA);
	        }
	    }
	    RequestDispatcher disp = request.getRequestDispatcher("Table.jsp");
	    disp.forward(request, response);
	}

	
	private void updateCourrierA(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException, ParseException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String Type = request.getParameter("Type");
	    String dateStr = request.getParameter("date");
        String Objet = request.getParameter("Objet");
        String Affectations = request.getParameter("Affectations");
        String Destinataires = request.getParameter("Destinataires");
        int Numero = Integer.parseInt(request.getParameter("Numero"));
        int Numero_BO = Integer.parseInt(request.getParameter("Numero_BO"));
        String date_BOStr = request.getParameter("date_BO");
        String Origine = request.getParameter("Origine");
        String Observation = request.getParameter("Observation");
        String Pieces_jointes = request.getParameter("Pieces_jointes");
        String createdAtStr = request.getParameter("created_at");
	    String updatedAtStr = request.getParameter("updated_at");

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date(dateFormat.parse(dateStr).getTime());
	    Date date_BO = new Date(dateFormat.parse(date_BOStr).getTime());
	    		
	    Timestamp createdAt = Timestamp.valueOf(createdAtStr);
	    Timestamp updatedAt = Timestamp.valueOf(updatedAtStr);

	    CourrierA updatedCourrierA = new CourrierA(id, Type, date, Objet, Affectations, Destinataires,
                Numero, Numero_BO, date_BO, Origine, Observation, Pieces_jointes,
                createdAt, updatedAt);

	    boolean rowUpdated = caDao.updateCourrierA(updatedCourrierA);

	    if (rowUpdated) {
	        System.out.println("Mail Arrived with ID " + id + " updated successfully.");
	    } else {
	        System.out.println("Failed to update Mail Arrived with ID " + id + ".");
	    }

	    response.sendRedirect("list");
	}


}

