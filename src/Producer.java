import java.util.ArrayList;
import java.util.Random;


public class Producer extends User {
	
	private ArrayList<Tag> taste;
	private Random r;
	public Producer(ArrayList<Tag> taste) {
		super(taste);
		this.taste = taste;
		r = new Random();
	}
	
	@Override
	public void act(SocialNetwork sn, String strategy){
		if (strategy.equals("documentPopularity")){
			Document d = new Document(this, taste.get(r.nextInt(taste.size())));
			sn.addDocument(d);
			like(d);
		
			for (Document doc : sn.getDocuments()){
				if (taste.contains(doc.getTag())){
					like(doc);
					if (doc.getLikingUsers().size()>0){
						for (User user : doc.getLikingUsers()){
							follow(user);
						}
					}
				}
			}
		}
	}
	
}
