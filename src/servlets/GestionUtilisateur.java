package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.UtilisateurDao;
import forms.AjoutUtilisateurForm;
import forms.ModifierUtilisateurForm;

/**
 * Servlet implementation class GestionUtilisateur
 */
@WebServlet({ "/users/add", "/users/list","/users/update","/users/delete" })
public class GestionUtilisateur extends HttpServlet
{
	private static final long	serialVersionUID		= 1L;
	private static final String	VUE_AJOUT_UTILISATEUR	= "/WEB-INF/ajouterUtilisateur.jsp";
	private static final String	VUE_LIST_UTILISATEUR	= "/WEB-INF/listerUtilisateur.jsp";
	private static final String	VUE_MODIFIER_UTILISATEUR	= "/WEB-INF/modifierUtilisateur.jsp";
	


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
		String path = request.getServletPath();

	
			switch (path)
			{
				case "/users/add":
					getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
							.forward(request, response);
					break;
				case "/users/list":
					request.setAttribute("utilisateurs", UtilisateurDao.getList());
					getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
							.forward(request, response);
					break;
					
				case "/users/update":
					
					int id = Integer.parseInt(request.getParameter("id"));
					Utilisateur user = UtilisateurDao.getById(id);
					request.setAttribute("utilisateur", user);
					getServletContext().getRequestDispatcher(VUE_MODIFIER_UTILISATEUR)
							.forward(request, response);
					
					
					break;
					
				case "/users/delete":
					
					int id1 = Integer.parseInt(request.getParameter("id"));
					UtilisateurDao.supprimer(id1);
					request.setAttribute("utilisateur", UtilisateurDao.getList());
					getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
							.forward(request, response);
					break;	
			}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getServletPath();
		if(requestedUrl.equals("/users/update")) {
			ModifierUtilisateurForm form = new ModifierUtilisateurForm(request);
			form.modifier();
			response.sendRedirect(
					request.getContextPath() + "/users/list");

			
		}
		
		else {
		AjoutUtilisateurForm form = new AjoutUtilisateurForm(request);
		form.ajouter();
		request.setAttribute("form", form);
		request.setAttribute("utilisateurs", UtilisateurDao.getList());
		getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
				.forward(request, response);
		}
		
		
		
	}

}
