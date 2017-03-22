import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class SocialNetwork {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Tag> tags = new ArrayList<Tag>();
	private ArrayList<Document> documents = new ArrayList<Document>();
	
	int nUsers = 0, nConsumers = 0, nProducers = 0, nTags = 0, nSimulations = 0;
	
	public void addDocument(Document d){
		documents.add(d);
	}
	
	public ArrayList<Document> getDocuments(){
		return documents;
	}
	
	public void simulate(){
		String tagsNames = "";
		
		
		
		String strategy = "";

		Scanner sCon = new Scanner(System.in);
		Scanner sProd = new Scanner(System.in);
		Scanner sTags = new Scanner(System.in);
		Scanner sSim = new Scanner(System.in);
		Scanner sStrat = new Scanner(System.in);
		Scanner sTagList = new Scanner(System.in);
		
		System.out.println("Hello! welcome to the social network simulation!");
		
		System.out.println("Please enter the # of consumers you would like to enter(minimum:1 and maximum:100): ");
		nConsumers = sCon.nextInt();
		
		System.out.println("Please enter the # of producers you would like to enter(minimum:1 and maximum:100): ");
		nProducers = sProd.nextInt();
		
		System.out.println("Please enter the strategy that you follow(documentPopularity for milestone 1) : ");
		
		while (!(sStrat.nextLine().equals("documentPopularity")) ){
			System.out.println("Your input is invalid. Please try again.");
			System.out.println("Please enter the strategy that you follow(documentPopularity for milestone 1) : ");
		}
		strategy = sStrat.nextLine();
		
		System.out.println("Please enter the # of different tags(with the minimum being 5 and the maximum being 100): ");
		nTags = sTags.nextInt();
		
		System.out.println("Please enter the # of iterations you want to take place: ");
		nSimulations = sSim.nextInt();
		
		System.out.println("Please enter all tags separated by a space: ");
		tagsNames = sTagList.nextLine();
		
		for (String s : tagsNames.split(" ")){
			tags.add(new Tag(s));
		}
		
		
		/*
		for (int i = 0; i < nProducers; i++){
			ArrayList<Tag> taste = new ArrayList<Tag>();
			Random randomTag = new Random();
			Random randomTaste = new Random();
			int tasteSize = randomTaste.nextInt(nTags)+1;
			for (int j = 0; i < tasteSize; i++){
				taste.add(tags.get(randomTag.nextInt(tags.size())));
			}
			producers.add(new Producer(taste));
		}
		
		for (int i = 0; i < nConsumers; i++){
			ArrayList<Tag> taste = new ArrayList<Tag>();
			Random randomTag = new Random();
			Random randomTaste = new Random();
			int tasteSize = randomTaste.nextInt(nTags)+1;
			for (int j = 0; i < tasteSize; i++){
				taste.add(tags.get(randomTag.nextInt(tags.size())));
			}
			consumers.add(new Consumer(taste));
		}
		
		*/
		
		for (int i = 0; i < nUsers; i++){
			ArrayList<Tag> taste = new ArrayList<Tag>();
			Random randomTag = new Random();
			Random randomTaste = new Random();
			int tasteSize = randomTaste.nextInt(nTags)+1;
			for (int j = 0; i < tasteSize; i++){
				taste.add(tags.get(randomTag.nextInt(tags.size())));
			}
			if (i<nProducers){
				users.add(new Producer(taste));
			} else {
				users.add(new Consumer(taste));
			}
		}
		
		Random rUser = new Random();
		
		
		for (int i = 0; i < nSimulations; i++){
			users.get(rUser.nextInt(users.size())).act(this, strategy);
		}
	
	}
	
	public static void main(String[] args){
		SocialNetwork sn = new SocialNetwork();
		sn.simulate();
	}

}
