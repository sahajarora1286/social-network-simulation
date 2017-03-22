package com.sahajarora.socialnetwork;
import java.io.Serializable;
import java.util.ArrayList;


/** 
 * A document will have one tag and a producer.
 *  @author Sahajnoor Arora
 */
public class Document implements Serializable {
	private static final long serialVersionUID = 8L;
	private Tag tag; //Tag of this document
	private Producer producer; //The producer of this document
	private ArrayList<User> likingUsers; //Users who have liked this document
	private int docId;

	public Document(Producer producer, Tag tag, int docId) {
		this.docId = docId;
		this.producer = producer;
		this.tag = tag;
		likingUsers = new ArrayList<User>();
	}
	
	/**
	 * Increases the number of likes and adds the user to the list of users who have liked this document.
	 * @param user User who 'liked' this document.
	 */
	public void like(User user){
		likingUsers.add(user);
		if(!getProducer().equals(user))  // Producer himself liking his document does not increment his payoff
		getProducer().incrementPayoff();
	}
	
	/**
	 * @return document ID
	 */
	public int getDocumentId(){
		return docId;
	}
	
	/**
	 * @return document ID in string form
	 */
	public String getName(){
		return "Document "+getDocumentId();
	}
	
	/**
	 * Returns the number of likes this document has.
	 * @return Number of likes the document has.
	 */
	public int getLikes(){
		return likingUsers.size();
	}
	
	/**
	 * Returns the producer of this document.
	 * @return Producer of this document.
	 */
	public Producer getProducer(){
		return producer;
	}
	
	/**
	 * Returns a list of users who have liked this document.
	 * @return List of users who have liked this document.
	 */
	public ArrayList<User> getLikingUsers(){
		return likingUsers;
	}
	
	/**
	 * Returns the tag of this document.
	 * @return The tag of this document.
	 */
	public Tag getTag(){
		return tag;
	}
	
	

}
