package beans;


public class Album
{

	private int		id, idUser;
	private String	theme, visibility;

	public Album()
	{
	}

	public Album(String theme, String visibility, int idUser)
	{
		this(0, theme, visibility, idUser);
	}

	public Album(int id, String theme, String visibility, int idUser)
	{
		this.id = id;
		this.theme = theme;
		this.visibility = visibility;
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

	public int getIdUser()
	{
		return idUser;
	}

	public void setIdUser(int idUser)
	{
		this.idUser = idUser;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public String getVisibility()
	{
		return visibility;
	}

	public void setVisibility(String visibility)
	{
		this.visibility = visibility;
	}

	@Override
	public String toString()
	{
		return "Album [id=" + id + ",idUser=" + idUser + ", theme=" + theme
				+ ", visibility=" + visibility + "]";
	}

}
