import java.util.ArrayList;


public class Consumer extends User {
	private ArrayList<Document> searchResults;

	public Consumer(ArrayList<Tag> taste) {
		super(taste);
		searchResults = new ArrayList<Document>();
	}
	
	@Override
	public void act(SocialNetwork sn, String strategy){
		if (strategy.equals("documentPopularity")){
			
		}
	}

}
