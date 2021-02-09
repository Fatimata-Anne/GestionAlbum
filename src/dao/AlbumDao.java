package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Album;

public class AlbumDao
{
	private static final String	SELECT_ALL_ALBUMS_SQL	= "SELECT * FROM album";
	private static final String	SELECT_USER_ALBUMS_SQL	= "SELECT * FROM album WHERE idUser=?";
	private static final String	INSERT_ALBUM_PSQL		= "INSERT INTO album VALUES(0, ?, ?,?)";
	private static final String	UPDATE_ALBUM_PSQL		= "UPDATE album SET nom=?, description=?, idUser=? WHERE id = ?";
	private static final String	SELECT_ALBUM			= "SELECT * FROM album WHERE id=?";
	private static final String	DELETE_ALBUM_PSQL		= "DELETE FROM album WHERE id=?";

	public static boolean ajouter(Album Album)
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try
		{
			statement = connection.prepareStatement(INSERT_ALBUM_PSQL);
			statement.setInt(1, Album.getId());
			statement.setString(2, Album.getTheme());
			statement.setString(3, Album.getVisibility());
			statement.setInt(4, Album.getIdUser());

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
			System.out.println(
					"Erreur durant l'insertion du Album : " + e.getMessage());
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

		}

		return false;
	}

	public static ArrayList<Album> getList()
	{
		Connection connection = ConnexionManager.getInstance();

		Statement statement = null;
		ResultSet reponse = null;

		ArrayList<Album> albums = new ArrayList<Album>();

		try
		{
			statement = connection.createStatement();
			reponse = statement.executeQuery(SELECT_ALL_ALBUMS_SQL);

			int id, userId;
			String nom, description;

			while (reponse.next())
			{
				id = reponse.getInt("id");

				nom = reponse.getString("nom");
				description = reponse.getString("description");
				userId = reponse.getInt("userId");

				albums.add(new Album(id, nom, description, userId));
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

		}

		return albums;
	}

}
