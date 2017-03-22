package com.sahajarora.socialnetwork;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User can either be a producer or a consumer. Every user has a taste. Every user acts differently.
 *  @author Sahajnoor Arora
 */
public abstract class User implements Serializable{
	private static final long serialVersionUID = 2L;
	private String name; 
	private ArrayList<Tag> taste; //taste is a set of tags. It can have any number of tags but not duplicate tags.
	private ArrayList<User> followers; //List of users following this user
	private ArrayList<User> following; //List of users whom this user is following
	private ArrayList<Document> likedDocuments; //List of documents that this user has liked
	private int payoff;
	private ArrayList<SearchData> record;
	private int distance;
	private ArrayList<Integer> payOffHistory;
	/** Constructor 
	 * @param taste
	 * @param name
	 */
	public User(ArrayList<Tag> taste, String name){
		this.name = name;
		this.taste = taste;
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		likedDocuments = new ArrayList<Document>();
		payoff = 0;
		record = new ArrayList<SearchData>();payOffHistory = new ArrayList<Integer>();
	}

	/**
	 * Iterates through the search results and liked the document if it matches this user's taste
	 * and follows the users who like that document.
	 * @param searchResults List of documents sorted according to the strategy 
	 */
	public void actOnResults(List<Document> searchResults){

		for (Document doc : searchResults){
			if ( matchesMyTaste(doc) &&  !(doc.getLikingUsers().contains(this))  ) {
				like(doc);
				for(User user : doc.getLikingUsers()){
					if (!user.equals(this)){	// We don't want the user to follow itself
						follow(user);
					}
				}

			}
		}

	}

	/**
	 * Returns a list of documents created by this user. This method is overriden by Producer.
	 * @return
	 */
	public abstract ArrayList<Document> getCreations();

	/**
	 * Method overridden by Producer and Consumer
	 * This method implements the actions of producer and consumer users respectively
	 * @param sn The Social Network
	 * @param topK 
	 * @param s The strategy to implement
	 */
	public abstract void act(SocialNetwork sn, Strategy s, int topK, String stratProducers,boolean undo);


	/**
	 * Returns the type of the user. (Producer or Consumer)
	 * @return The type of the user.
	 */


	/**
	 *Method overridden by Producer and Consumer
	 *This simply returns the type of user - either consumer or producer
	 */
	public abstract String getType();

	/**
	 *Method overridden by Producer and Consumer
	 *This simply returns the user name and user type (consumer or producer)
	 */
	@Override
	public String toString(){
		return name;
	}


	/**
	 * Adds a tag to this user's taste.
	 * @param tag The tag to be added to this user's taste.
	 */
	public void addToTaste(Tag tag){
		taste.add(tag);

	}

	/**
	 * Likes the document.
	 * @param document The document to 'like'
	 */
	public void like(Document document){
		document.like(this);
		likedDocuments.add(document);
	}





	/* 
	 * compares two objects to see if they are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;

		if (getName().equals(other.getName())) return true;  //we only need to check the names because every user will have a unique name

		return false;
	}

	/**
	 * Adds the user to the list of users whom this user is following. Basically this user 'follows' the user passed in as parameter.
	 * @param user The user who this user is following
	 */
	public void addFollowing(User user){
		if(!user.equals(this)){
			if (!following.contains(user))
				following.add(user);
			}
	}

	/**
	 * Adds the user to this user's followers list.
	 * @param user The user who is following this user.
	 */
	public void addFollower(User user){
		if (!user.equals(this)){
			if (!followers.contains(user))
				followers.add(user);
		}
	}

	/**
	 * Follows the user.
	 * @param user The user to follow.
	 */
	public void follow(User user){


			addFollowing(user);
			user.addFollower(this);
		
	}

	/**
	 * Returns the taste of this user.
	 * @return Taste of this user.
	 */
	public ArrayList<Tag> getTaste() {
		return taste;
	}

	/**
	 * Returns a list of people following this user.
	 * @return
	 */
	public ArrayList<User> getFollowers(){
		return followers;
	}

	/**
	 * Returns a list of users whom this user is following.
	 * @return
	 */
	public ArrayList<User> getFollowing() {
		return following;
	}

	/**
	 * Returns a list of documents that this user has liked.
	 * @return
	 */
	public ArrayList<Document> getLikedDocuments() {
		return likedDocuments;
	}


	/**
	 * Returns true if this user has liked the document passed in as a parameter.
	 * @param doc The document to check.
	 * @return true if this user has 'liked' the document.
	 */
	public boolean hasLiked(Document doc){
		if (likedDocuments.contains(doc))
			return true;

		else
			return false;
	}


	/**
	 * Returns true if doc matches this user's taste.
	 * @param doc The document to check.
	 * @return true if the document matches this user's taste.
	 */
	public boolean matchesMyTaste(Document doc){



		for(Tag t: taste)
			if(doc.getTag().getId() == t.getId())
				return true;
		return false;


	}

	/**
	 * Returns the name of this user.
	 * @return Name of this user.
	 */
	public String getName(){
		return name + " (" + getType() + ")";
	}

	public void addRecord(SearchData newRecord){
		record.add(newRecord);
	}

	/**
	 * @return the payoff
	 */
	public int getCurrentPayoff(){
		return payoff;
	}

	public void setDistance(int i){
		this.distance=i;
	}
	public int getDistance(){
		return distance;
	}


	/**
	 * Updates the payoff
	 * @param pay_off new payoff value, used for consumer
	 */
	public void updatePayoff(int pay_off){
		this.payoff = pay_off;
	}

	/**
	 * Increments the Payoff by 1, used for producer
	 */
	public void incrementPayoff(){
		payoff++;
	}


	/**
	 * @return the record of the search/payoff values of this user
	 */
	public ArrayList<SearchData> getRecord() {
		return record;
	}
	public void updatePayOffHistory(boolean undo){
		if (!undo){
		payOffHistory.add(payoff);}else{payOffHistory.remove(payOffHistory.size()-1);}
	}

	public ArrayList<Integer> getpayOffHistory(){
		return payOffHistory;
	}





}
