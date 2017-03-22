package com.sahajarora.socialnetwork;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Producer is a type of User. A producer created a document and acts based on the strategy being implemented.
 *  @author Sahajnoor Arora
 */
public class Producer extends User implements Serializable {
	private static final long serialVersionUID = 5L;
	private Random r;
	private ArrayList<Document> myDocs;
	private Tag tag;
	private int docId;
	private Document d;
	private ArrayList<Tag> tags;
	private ArrayList<Tag> pastpickedTag;
	private Tag pickedTag;
	private int decrement;


	/**Constructor with fields for producer 
	 * @param taste
	 * @param name
	 */
	public Producer(ArrayList<Tag> taste, String name) {
		super(taste, name);
		r = new Random();
		myDocs = new ArrayList<Document>();
		pastpickedTag = new ArrayList<>();

	}

	/**
	 * Searches for top 10 documents (for now) based on the strategy. Creates a new document that matches this producer's taste.
	 * Likes his own documents and then acts on the results.
	 */
	@Override
	public void act(SocialNetwork sn, Strategy stratUsers, int topK, String stratProducers, boolean undo){
		ArrayList<Document> searchResults = new ArrayList<Document>();
		searchResults = stratUsers.getSearchResults(sn, topK,this);
		ArrayList<Tag> taste = getTaste();
		docId = generateDocId(sn);
		if (!undo){
		tag = taste.get(r.nextInt(taste.size()));
		//tags.add(tag);
		
		d = createDocument(tag, docId);
		
		sn.addDocument(d);
		
		if (stratProducers.equals("A")){
			like(d);
			}
		else if(stratProducers.equals("B")){
			
			do{
				pickedTag=new Tag(r.nextInt(sn.getTagsCount()));
				pastpickedTag.add(pickedTag);
			}while(taste.contains(pickedTag));
			for (Document a:sn.getDocuments()){
				if (a.getTag().equals(pickedTag)){
					like(a);
					for (User u:a.getLikingUsers()){
						follow(u);
					}
				}
			}
		}
		super.actOnResults(searchResults);
		SearchData newRecord = new SearchData(super.getCurrentPayoff(), stratUsers, searchResults); // Saving his payoff at this point of time
		super.addRecord(newRecord);
		}else{decrement++;
			d = createDocument(tags.get(tags.size()-decrement), docId);
			
			sn.addDocument(d);
			
			
			
			if (stratProducers.equals("A")){
				like(d);
				}
			else if(stratProducers.equals("B")){
				
				do{
					pickedTag=new Tag(r.nextInt(sn.getTagsCount()));
					pastpickedTag.add(pickedTag);
				}while(taste.contains(pickedTag));
				for (Document a:sn.getDocuments()){
					if (a.getTag().equals(pickedTag)){
						like(a);
						for (User u:a.getLikingUsers()){
							follow(u);
						}
					}
				}
			}
		}
	}


	/**
	 * @return the list of producer's creations
	 */
	@Override
	public ArrayList<Document> getCreations(){
		return myDocs;
	}

	/**Generates a document ID for the newly created document
	 * @param sn
	 * @return new document ID
	 */
	public int generateDocId(SocialNetwork sn){
		int id = sn.getDocuments().size() + 1;
		return id;
	}

	/**Creates a new document and adds it to the list of documents
	 * @param  tag
	 * @param  id
	 * @return
	 */
	public Document createDocument(Tag tag, int id){
		Document newDoc = new Document(this, tag, id);
		myDocs.add(newDoc);
		return newDoc;
	}

	/* 
	 * @param User user
	 * Adds the user as a foller and increments payoff
	 */
	public void addFollower(User user){
		super.addFollower(user);
		super.incrementPayoff();
	}

	/* 
	Gets the types of user - Producer
	 */
	public String getType(){
		return "Producer";
	}

	
	/* 
	 * Returns the user name and user type (producer)
	 */
	/*
	public String toString(){
		String t = "";
		t += super.getName() + " (Producer) ";
		return t;
	}
	*/






}
