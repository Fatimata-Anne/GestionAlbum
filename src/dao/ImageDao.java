package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Image;

public class ImageDao
{

	private static final String	SELECT_ALL_IMAGES_SQL	= "SELECT * FROM image";
	private static final String	SELECT_USER_IMAGES_SQL	= "SELECT * FROM image WHERE idUser=?";
	private static final String	INSERT_IMAGE_PSQL		= "INSERT INTO image VALUES(0,?,?,?,?,?,?,?,?,?,?)";
	private static final String	UPDATE_IMAGE_PSQL		= "UPDATE image SET titre=?, description=?, hauteur=?,"
			+ " largeur=?, motCles=?, dateCreation=?, dateUpdate=?, imgLien=?, idAlbum=?, idUser=? WHERE id = ?";

	private static final String	SELECT_IMAGE			= "SELECT * FROM image WHERE id=?";
	private static final String	DELETE_IMAGE_PSQL		= "DELETE FROM image WHERE id=?";

	public static boolean ajouter(Image Image)
	{
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try
		{
			statement = connection.prepareStatement(INSERT_IMAGE_PSQL);
			statement.setInt(1, Image.getId());
			statement.setString(2, Image.getTitre());
			statement.setString(3, Image.getDescription());
			statement.setDouble(4, Image.getHauteur());
			statement.setDouble(5, Image.getLargeur());
			statement.setString(6, Image.getMotCles());
			statement.setString(7, Image.getDateCreation());
			statement.setString(9, Image.getDateUpdate());
			statement.setString(10, Image.getImgLien());
			statement.setInt(11, Image.getIdAlbum());
			statement.setInt(12, Image.getIdUser());

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
					"Erreur durant l'insertion du Image : " + e.getMessage());
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

	public static ArrayList<Image> getList()
	{
		Connection connection = ConnexionManager.getInstance();

		Statement statement = null;
		ResultSet reponse = null;

		ArrayList<Image> images = new ArrayList<Image>();

		try
		{
			statement = connection.createStatement();
			reponse = statement.executeQuery(SELECT_ALL_IMAGES_SQL);

			int id, idAlbum, idUser;
			double hauteur, largeur;
			String titre, description, motCles, dateCreation, dateUpdate,
					imgLien;

			while (reponse.next())
			{
				id = reponse.getInt("id");

				titre = reponse.getString("titre");
				description = reponse.getString("description");
				hauteur = reponse.getDouble("hauteur");
				largeur = reponse.getDouble("largeur");
				motCles = reponse.getString("motCles");
				dateCreation = reponse.getString("dateCreation");
				dateUpdate = reponse.getString("dateUpdate");
				imgLien = reponse.getString("imgLien");
				idAlbum = reponse.getInt("idALbum");
				idUser = reponse.getInt("idUser");

				images.add(new Image(id, titre, description, hauteur, largeur,
						motCles, dateCreation, dateUpdate, imgLien, idAlbum,
						idUser));
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

		return images;
	}

}
