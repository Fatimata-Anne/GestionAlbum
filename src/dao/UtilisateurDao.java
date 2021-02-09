package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Utilisateur;

public class UtilisateurDao
{
	private static final ArrayList<Utilisateur>	utilisateurs			= new ArrayList<Utilisateur>();

	private static final String					SELECT_ALL_USERS_SQL	= "SELECT * FROM Utilisateur";
	private static final String					INSERT_USER_PSQL		= "INSERT INTO Utilisateur VALUES(0, ?, ?, ?, ?, ?)";
	
	private static final String					UPDATE_USER_PSQL		="Update Utilisateur set nom=?, prenom=?, login=?, password=?, role= ? where id=?";
	private static final String 				USER_BY_ID_PSQL          = "Select * from Utilisateur where id=?";
	private static final String 				USER_BY_LOGIN_PSQL          = "Select * from Utilisateur where login=?";
	private static final String 				DELETE_BY_ID_PSQL          =  "DELETE from Utilisateur where id=?";

	public static boolean ajouter(Utilisateur utilisateur)
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try
		{
			statement = connection.prepareStatement(INSERT_USER_PSQL);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getLogin());
			statement.setString(4, utilisateur.getPassword());
			statement.setString(5, utilisateur.getRole());

			int status = statement.executeUpdate();

			if (status == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur durant l'insertion de l'utilisateur : "
					+ e.getMessage());
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
			}

			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
			}
		}

		return false;
	}

	/*public static boolean modifier(Utilisateur user)
	{
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getId() == user.getId())
			{
				utilisateur.setNom(user.getNom());
				utilisateur.setPrenom(user.getPrenom());
				utilisateur.setLogin(user.getLogin());
				utilisateur.setPassword(user.getPassword());

				return true;
			}
		}

		return false;
	}
*/
	public static boolean supprimer(int id)
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
	try
		{	
		statement = connection.prepareStatement(DELETE_BY_ID_PSQL );
		statement.setInt(1, id);
		
		int status = statement.executeUpdate();

		
		if (status == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur durant l'insertion de l'utilisateur : "
				+ e.getMessage());
		}
		finally
		{
		try
		{
			if (statement != null)
			{
				statement.close();
			}
		}
		catch (SQLException e)
		{
		}

		try
		{
			if (connection != null)
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{
		}
	}

	return false;
}
	

	public static ArrayList<Utilisateur> getList()
	{
		Connection connection = ConnexionManager.getInstance();

		Statement statement = null;
		ResultSet reponse = null;

		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		try
		{
			statement = connection.createStatement();
			reponse = statement.executeQuery(SELECT_ALL_USERS_SQL);

			int id;
			String nom, prenom, login, password,role;

			while (reponse.next())
			{
				id = reponse.getInt("id");
				nom = reponse.getString("nom");
				prenom = reponse.getString("prenom");
				login = reponse.getString("login");
				password = reponse.getString("password");
				role = reponse.getString("role");

				utilisateurs
						.add(new Utilisateur(id, nom, prenom, login, password,role));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (reponse != null)
				{
					reponse.close();
				}
			}
			catch (SQLException e)
			{
			}
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
			}

			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
			}
		}

		return utilisateurs;
	}

	public static Utilisateur getById(int id)
	{

		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		Utilisateur user = null;

		try
		{
			statement = connection.prepareStatement(USER_BY_ID_PSQL );
			statement.setInt(1, id);
			

			ResultSet reponse = statement.executeQuery();

			while(reponse.next()) {
				String  nom = reponse.getString("nom");
				String  prenom = reponse.getString("prenom");
				String  login = reponse.getString("login");
				String  password = reponse.getString("password");
				String  role = reponse.getString("role");
				
				user = new Utilisateur(id, nom, prenom, login, password,role);

				
			}
			return user;

		}
		catch (SQLException e)
		{
			System.out.println("Erreur durant la mise à jour de l'utilisateur : "
					+ e.getMessage());
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
			}

			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
			}
		}

		return null;
	}
	

	public static Utilisateur getByLogin(String login)
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		Utilisateur user = null;

		try
		{
			statement = connection.prepareStatement(USER_BY_LOGIN_PSQL );
			statement.setString(1, login);
			

			ResultSet reponse = statement.executeQuery();

			while(reponse.next()) {
				int id = reponse.getInt("id");
				String  nom = reponse.getString("nom");
				String  prenom = reponse.getString("prenom");
				String  password = reponse.getString("password");
				String  role = reponse.getString("role");
				
				
				user = new Utilisateur(id, nom, prenom, login, password,role);

				
			}
			return user;

		}
		catch (SQLException e)
		{
			System.out.println("Erreur durant la mise à jour de l'utilisateur : "
					+ e.getMessage());
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
			}

			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
			}
		}

		return null;
	}
		
		

	public static boolean modifier(Utilisateur user) {
			
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		

		try
		{
			statement = connection.prepareStatement(UPDATE_USER_PSQL);
			statement.setInt(6, user.getId());
			statement.setString(1, user.getNom());
			statement.setString(2, user.getPrenom());
			statement.setString(3,user.getLogin());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getRole());
			
			
			int status = statement.executeUpdate();

			

			if (status == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Erreur durant la mise à jour de l'utilisateur : "
					+ e.getMessage());
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
			}

			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
			}
		}

		return false;
	
		
	}
}
