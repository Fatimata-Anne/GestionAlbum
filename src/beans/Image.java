package beans;


public class Image
{
	private int		id, idAlbum, idUser;
	private double	largeur, hauteur;
	private String	titre, description, motCles, dateCreation, dateUpdate,
			imgLien;

	public Image()
	{
	}

	public Image(String titre, String description, double hauteur,
			double largeur, String motCles, String dateCreation,
			String dateUpdate, String imgLien, int idAlbum, int idUser)
	{
		this(0, titre, description, hauteur, largeur, motCles, dateCreation,
				dateUpdate, imgLien, idAlbum, idUser);
	}

	public Image(int id, String titre, String description, double hauteur,
			double largeur, String motCles, String dateCreation,
			String dateUpdate, String imgLien, int idAlbum, int idUser)
	{
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.motCles = motCles;
		this.dateCreation = dateCreation;
		this.dateUpdate = dateUpdate;
		this.imgLien = imgLien;
		this.idAlbum = idAlbum;
		this.idUser = idUser;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdAlbum()
	{
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum)
	{
		this.idAlbum = idAlbum;
	}

	public int getIdUser()
	{
		return idUser;
	}

	public void setIdUser(int idUser)
	{
		this.idUser = idUser;
	}

	public double getLargeur()
	{
		return largeur;
	}

	public void setLargeur(double largeur)
	{
		this.largeur = largeur;
	}

	public double getHauteur()
	{
		return hauteur;
	}

	public void setHauteur(double hauteur)
	{
		this.hauteur = hauteur;
	}

	public String getTitre()
	{
		return titre;
	}

	public void setTitre(String titre)
	{
		this.titre = titre;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getMotCles()
	{
		return motCles;
	}

	public void setMotCles(String motCles)
	{
		this.motCles = motCles;
	}

	public String getDateCreation()
	{
		return dateCreation;
	}

	public void setDateCreation(String dateCreation)
	{
		this.dateCreation = dateCreation;
	}

	public String getDateUpdate()
	{
		return dateUpdate;
	}

	public void setDateUpdate(String dateUpdate)
	{
		this.dateUpdate = dateUpdate;
	}

	public String getImgLien()
	{
		return imgLien;
	}

	public void setImgLien(String imgLien)
	{
		this.imgLien = imgLien;
	}

	@Override
	public String toString()
	{
		return "Image [id=" + id + ", idAlbum=" + idAlbum + ", idUser=" + idUser
				+ ", largeur=" + largeur + ", hauteur=" + hauteur + ", titre="
				+ titre + ", description=" + description + ", motCles="
				+ motCles + ", dateCreation=" + dateCreation + ", dateUpdate="
				+ dateUpdate + ", imgLien=" + imgLien + "]";
	}

}

