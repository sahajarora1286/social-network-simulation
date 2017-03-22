package com.sahajarora.socialnetwork;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * This class holds the state of the social network at all times. It takes in user input and sets the parameters accordingly
 * for the simulation, and then provides the possibility to run the simulation.
 *  @author Sahajnoor Arora
 */
public class SocialNetwork implements Serializable {
	private static final long serialVersionUID = 3L;
	private ArrayList<User> users;
	private ArrayList<Tag> tags;
	private ArrayList<Document> documents;
	private Strategy strat;
	private int topK;

	private int actCount = 0;

	private ArrayList<User> chosenUsersForIterations;
	private ArrayList<Document> initialDocuments;
	private int nUsers = 0, nConsumers = 0, nProducers = 0, nTags = 0, nSimulations = 0, nInitConsumers = 0, 
			nInitProducers = 0, nInitUsers = 0;
	private String stratProducers;
	private ArrayList<User> tempUsers;
	private User currentUser;
	private int decrement=0;

	public SocialNetwork(){
		users = new ArrayList<User>(); //Users in the social network
		tags = new ArrayList<Tag>();	//Tags in the social network
		documents = new ArrayList<Document>();	//Documents in the social network
		chosenUsersForIterations = new ArrayList<User>();
		initialDocuments = new ArrayList<>();
		topK=0;
		strat = new DocumentPopularity();
		stratProducers = "A";
		tempUsers = new ArrayList<>();


	}



	/**
	 * Adds Document d to the social network.
	 * @param d The document to be added to the network.
	 */
	public void addDocument(Document d){
		documents.add(d);
	}

	/**
	 * Returns an Array List of documents present in the social network.
	 * @return Array List of documents present in the network.
	 */
	public ArrayList<Document> getDocuments(){
		return documents;
	}

	/** Sets the number of tags
	 * @param nTags
	 */
	public void setTotalTags(int nTags){
		this.nTags = nTags;

		for (int i = 0; i < nTags; i++){
			tags.add(new Tag(i));
		}
	}

	/**
	 * @return number of tags
	 */
	public int getTagsCount(){
		return tags.size();
	}

	
	public int getTopK(){
		return topK;
	}
	
	public void setTopK(int topK){
		this.topK = topK;
	}
	
	public void setStratProducers(String stratProducers){
		this.stratProducers = stratProducers;
		
	}
	
	public String getStratProducers(){
		return stratProducers;
	}


	/** Sets initial number of producers and consumers
	 * @param nInitProducers
	 * @param nInitConsumers
	 */

	public void setInitialPopulation(int nInitProducers, int nInitConsumers){
		this.nInitProducers = nInitProducers;
		this.nInitConsumers = nInitConsumers;
		nInitUsers = nInitConsumers + nInitProducers;
	}


	/**
	 * @return Sum of initial number of consumers and producers entered
	 */
	public int getInitialPopulation(){
		return nInitUsers;
	}

	/**
	 * Adds initial number of consumers and producer to the list of users
	 * Producers producer documents
	 * consumers like documents according to their taste
	 */
	public void setInitialConfiguration(){

		for (int i = 0; i < nInitUsers; i++){
			ArrayList<Tag> taste = generateRandomTaste();
			if (i < nInitProducers){
				users.add(new Producer(taste, "Init User "+i));
			} else {
				users.add(new Consumer(taste, "Init User "+i));
			}
		}

		//First let all initial Producers produce documents and like them
		for (User u : users){
			if (u.getType().equals("Producer")){
				u.act(this, strat, topK, stratProducers,false);
				actCount++;
			}
		}

		// Now let all initial consumers 'like' documents according to their taste
		for (User u : users){
			if (u.getType().equals("Consumer")){
				u.act(this, strat, topK, stratProducers,false);
				actCount++;
			}

		}
	}





	/** Sets simulation number of consumers and producers
	 * @param nProducers
	 * @param nConsumers
	 */
	public void setSimilutionPopulation(int nProducers, int nConsumers){
		this.nProducers = nProducers;
		this.nConsumers = nConsumers;
		nUsers = nConsumers + nProducers;
	}

	/**
	 * @return total number of users
	 */
	public int getSimulationPopulation(){
		return nUsers;
	}


	/**
	 * creates and adds simulation producers and consumers
	 */
	public void setSimulationConfiguration(){
		// Generate nProducers and nConsumers with random tastes.
		for (int i = 0; i < nUsers; i++){
			ArrayList<Tag> taste = generateRandomTaste();
			if (i < nProducers){
				users.add(new Producer(taste, "New User "+i));
			} else {
				users.add(new Consumer(taste, "New User "+i));
			}
		}
	}

	/** Sets the search strategy to the specified strategy
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy){
		this.strat = strategy;
	}

	/**
	 * @return Current strategy in use
	 */
	public Strategy getStrategy(){
		return strat;
	}


	/** Sets number of simulations
	 * @param nSimulations
	 */
	public void setNumSimulations(int nSimulations){
		this.nSimulations = nSimulations;
	}

	/**
	 * @return number of simulations
	 */
	public int getNumSimulations(){
		return nSimulations;
	}

	/**
	 * Takes in user input for simulation, sets the parameters accordingly, and runs the simulation.
	 */
	public void simulate(int simulations,boolean undo){
		Random random = new Random();
		chosenUsersForIterations.clear();
		// Runs the simulation nSimulations times.
		for (int i = 0; i < simulations; i++){
			if (!undo){
			User rUser = users.get(random.nextInt(users.size()));
			tempUsers.add(rUser);
			chosenUsersForIterations.add(rUser);
			rUser.act(this, strat, topK, stratProducers,undo);
			

			}
			else{
			decrement++;
			currentUser=tempUsers.get(tempUsers.size()-decrement);
			currentUser.act(this,strat,topK,stratProducers,undo);
			}
			actCount++;
			for (int z = 0; z< users.size();z++ ){
				users.get(z).updatePayOffHistory(undo);
			}
		}
	}
	
	public void UndoSimulate(){
		simulate(1,true);
	}
	
	public void stepThroughSimulate(){
		simulate(1,false);
	}

	/**
	 * @return users chosen for iterations in the simulation
	 */
	public ArrayList<User> getChosenUsers(){
		return chosenUsersForIterations;
	}

	/**
	 * Generates an Array List of random taste.
	 * @return
	 */
	public ArrayList<Tag> generateRandomTaste() {

		ArrayList<Tag> taste = new ArrayList<Tag>();
		Random randomTag = new Random();
		Random randomTaste = new Random();
		int tasteSize = randomTaste.nextInt(nTags)+1;
		int rTag = 0;
		for (int j = 0; j < tasteSize; j++){
			// We don't want the same tag to be added multiple times.
			do{
				rTag = randomTag.nextInt(tags.size());
			}while (taste.contains(tags.get(rTag)));
			taste.add(tags.get(rTag));
		}
		return taste;
	}

	/**
	 * @return list of all users 
	 */
	public ArrayList<User> getUsers(){
		return this.users;
	}

	/**
	 * @return Number of times user.act() is executed
	 */
	public int getActCount(){
		return actCount;
	}



}
