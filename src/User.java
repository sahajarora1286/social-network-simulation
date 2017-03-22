import java.util.ArrayList;


public abstract class User {
	
	private ArrayList<Tag> taste;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private ArrayList<Document> likedDocuments;
	
	public User(ArrayList<Tag> taste){
		taste = new ArrayList<Tag>();
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		likedDocuments = new ArrayList<Document>();
	}
	
	public abstract void act(SocialNetwork sn, String strategy);
	
	public void addToTaste(Tag tag){
		taste.add(tag);
	}
	
	public void like(Document document){
		document.like(this);
		likedDocuments.add(document);
	}
	
	public void addFollowing(User user){
		following.add(user);
	}
	
	public void addFollower(User user){
		followers.add(user);
	}
	
	public void follow(User user){
		addFollowing(user);
		user.addFollower(this);
	}


	public ArrayList<Tag> getTaste() {
		return taste;
	}

	public ArrayList<User> getFollowers(){
		return followers;
	}
	
	public ArrayList<User> getFollowing() {
		return following;
	}

	public ArrayList<Document> getLikedDocuments() {
		return likedDocuments;
	}
	
	

}
