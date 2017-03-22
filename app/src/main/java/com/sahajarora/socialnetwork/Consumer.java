package com.sahajarora.socialnetwork;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * A Consumer is a type of a User. A consumer will act based on the strategy being implemented.
 * This class will also hold a reference to the search history of the user.
 * @author Sahajnoor Arora
 */
public class Consumer extends User implements Serializable{

	private static final long serialVersionUID = 9L;
	public Consumer(ArrayList<Tag> taste, String name) {
		super(taste, name);
	}

	/**
	 * Returns true if this user has seen this doc in the past.
	 * @param doc Document to check
	 * @return true if seen
	 */
	//Added by Haris
	private boolean hasSeen(Document doc){
		for (SearchData sd : super.getRecord()){	// if this user's search record contains this doc, it means that this user has seen this doc.
			if (sd.getSearchResults().contains(doc)) return true;
		}
		return false;
	}

	/**
	 * Calculates the payoff.
	 * @param searchResults List of the search results
	 * @return The calculated payoff
	 */
	//Added by Haris
	private int calculatePayoff(List<Document> searchResults ){

		//Apparently the accumulated payoff  the consumer should add to the payoff in the previous iteration
		int pay_off = super.getCurrentPayoff();

		for (Document doc : searchResults){
			if (!hasSeen(doc) && matchesMyTaste(doc))
				pay_off++;
		}
		return pay_off;
	}



	/**
	 * Searches for top k documents (for now) based on the strategy. Calculates the payoff based on the search results. 
	 * Acts on the results.
	 */
	//Added by Haris
	@Override
	public void act(SocialNetwork sn, Strategy strat, int topK, String stratProducers,boolean undo){
		ArrayList<Document> searchResults = strat.getSearchResults(sn, topK,this);
		updatePayoff(calculatePayoff(searchResults));
		SearchData newRecord = new SearchData(super.getCurrentPayoff(), strat, searchResults);
		super.addRecord(newRecord);
		super.actOnResults(searchResults);
	}
	
	/* 
		Gets the types of user - Consumer
	 */
	public String getType(){
		return "Consumer";
	}
	
	
	/* 
	 * Returns the user name and user type (consumer)
	 */
	/*
	public String toString(){
		String t = "";
		t += super.getName() + " (Consumer)";
		return t;
	}
	*/
	@Override
	public ArrayList<Document> getCreations() {
		// TODO Auto-generated method stub
		return null;
	}






}
