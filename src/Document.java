import java.util.ArrayList;


public class Document {
	
	private int likes;
	private Tag tag;
	private Producer producer;
	private ArrayList<User> likingUsers; // try to come up with a better name for this variable

	public Document(Producer producer, Tag tag) {
		likes = 0;
		this.producer = producer;
		this.tag = tag;
		likingUsers = new ArrayList<User>();
	}
	
	public void like(User user){
		likes++;
		likingUsers.add(user);
	}
	
	public int getLikes(){
		return likes;
	}
	
	public Producer getProducer(){
		return producer;
	}
	
	public ArrayList<User> getLikingUsers(){
		return likingUsers;
	}
	
	public Tag getTag(){
		return tag;
	}
	

}
