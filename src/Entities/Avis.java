package Entities;

/**
 * @author lenovo
 *
 */
public class Avis 
{
	private int id;
	private String description;
	private int note;
	private String satisfaction;
	private User user;

        public Avis(int id, String description, int note, String satisfaction, User user) {
            this.id = id;
            this.description = description;
            this.note = note;
            this.satisfaction = satisfaction;
            this.user = user;
        }

    public Avis(String description, int note, String satisfaction, User user) {
        this.description = description;
        this.note = note;
        this.satisfaction = satisfaction;
        this.user = user;
    }

    public Avis() {
    }
        
        
	
        
        
	
	public int getId()
	{
		return id;
	}
	
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	public String getDescription() 
	{
		return description;
	}
	
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
	public int getNote() 
	{
		return note;
	}
	
	
	public void setNote(int note) 
	{
		this.note = note;
	}
	
	
	public String getSatisfaction() 
	{
		return satisfaction;
	}
	
	
	public void setSatisfaction(String satisfaction) 
	{
		this.satisfaction = satisfaction;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	
	
	
}
