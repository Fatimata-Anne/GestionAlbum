package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class ModifierUtilisateurForm {
	
	private static final String	CHAMP_ID			= "id";
	private static final String	CHAMP_NOM			= "nom";
	private static final String	CHAMP_PRENOM		= "prenom";
	private static final String	CHAMP_LOGIN			= "login";
	private static final String	CHAMP_PASSWORD		= "password";
	private static final String	CHAMP_PASSWORD_BIS	= "passwordBis";
	private static final String	CHAMP_ROLE	= "role";

	private Utilisateur			utilisateur;
	private HttpServletRequest	request;
	private Map<String, String>	erreurs;
	private String				statusMessage;

	public ModifierUtilisateurForm(HttpServletRequest request)
	{
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	}

	public boolean modifier()
	{
		int id = Integer.parseInt(getParameter(CHAMP_ID));
		String nom = getParameter(CHAMP_NOM);
		String prenom = getParameter(CHAMP_PRENOM);
		String login = getParameter(CHAMP_LOGIN);
		String password = getParameter(CHAMP_PASSWORD);
		String role = getParameter(CHAMP_ROLE);
		validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD,
				CHAMP_PASSWORD_BIS);

		validerPasswords(CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);

		if (erreurs.isEmpty())
		{
			utilisateur = new Utilisateur(id,nom, prenom, login, password,role);

			if (UtilisateurDao.modifier(utilisateur))
			{
				utilisateur = null;
				statusMessage = "Modification effectu� avec succ�s !";

				return true;
			}
			else
			{
				statusMessage = "Une erreur inattendue s'est produite !";

				return false;
			}
		}
		else
		{
			utilisateur = new Utilisateur(nom, prenom, login, password,role);
			statusMessage = "Echec de la modification";

			return false;
		}
	}

	private String getParameter(String champ)
	{
		String valeur = request.getParameter(champ);

		if (valeur == null || valeur.trim().isEmpty())
		{
			return null;
		}
		else
		{
			return valeur.trim();
		}
	}

	private void validerChamps(String... champs)
	{
		for (String champ : champs)
		{
			if (getParameter(champ) == null)
			{
				erreurs.put(champ, "Vous devez renseigner ce champ");
			}
		}
	}

	private void validerPasswords(String champPassword, String champPasswordBis)
	{
		String password = getParameter(champPassword);
		String passwordBis = getParameter(champPasswordBis);

		if (password != null && !password.equals(passwordBis))
		{
			erreurs.put(champPassword,
					"Les mots de passe ne sont pas conformes");
			erreurs.put(champPasswordBis,
					"Les mots de passe ne sont pas conformes");
		}
	}

	public Utilisateur getUtilisateur()
	{
		return utilisateur;
	}

	public Map<String, String> getErreurs()
	{
		return erreurs;
	}

	public String getStatusMessage()
	{
		return statusMessage;
	}

	public void modifier(int id) {
		// TODO Auto-generated method stub
		
	}
}
