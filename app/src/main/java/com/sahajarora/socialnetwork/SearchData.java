package com.sahajarora.socialnetwork;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * As a way to keep track of which strategies produced the most pays offs,
 *we will have a class that will hold  the search data.
 *This will help in understanding that which strategies are most helpful 
 *for improving the quality of the search, and also hold the search history of the user
 * @author Haris
 */
public class SearchData implements Serializable{
	private static final long serialVersionUID = 4L;
	private int payOff;
	private Strategy strategyUsed;
	private ArrayList<Document> searchResults;

	// more info about the act of the user to be added in near future

	public SearchData(int payOff, Strategy strategyUsed, ArrayList<Document> searchResults){
		this.payOff = payOff;
		this.strategyUsed = strategyUsed;
		this.searchResults = searchResults;
	}

	/**
	 * Returns the payoff
	 * @return The calculated payoff.
	 */
	public int getPayOff() {
		return payOff;
	}

	/**
	 * Returns the strategy that was used.
	 * @return Strategy used.
	 */
	public Strategy getStrategyUsed() {
		return strategyUsed;
	}

	/**
	 * Returns the search results that were shown to the user in it's history.
	 * @return List of search results shown to the user in the past.
	 */
	//Added by Sahajnoor
	public ArrayList<Document> getSearchResults() {
		return searchResults;
	}



}