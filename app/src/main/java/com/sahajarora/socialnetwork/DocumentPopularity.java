package com.sahajarora.socialnetwork;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * @author Adhiraj
 */
public class DocumentPopularity implements Strategy, Serializable{
	private static final long serialVersionUID = 7L;
	private ArrayList<Document> sortedDocs;//sorted arraylist of documents based on the strategy

	public DocumentPopularity(){
		sortedDocs = new ArrayList<Document>();
	}

	@Override
	public ArrayList<Document> getSearchResults(SocialNetwork sn, int topK, User u) {
		sortedDocs.addAll(sn.getDocuments()); // Add all the documents in the social network to sortedDocs

		if (sortedDocs.size()>0){
			System.out.println("strategy 1 selected");
			Collections.sort(sortedDocs,new MyDocComp()); //Sorts the sortedDocs list based on the number of likes 
			if (sortedDocs.size()>topK){
				sortedDocs = new ArrayList<>(sortedDocs.subList(0, topK)); // We only want the user to see the top k documents
			}

		}

		return sortedDocs;
	}

}

/**
 * Comparator to sort the search results based on the number of likes of the documents (lowest to highest)
 */
class MyDocComp implements Comparator<Document>{
	@Override
	public int compare(Document e1, Document e2) {
		if(e1.getLikes() < e2.getLikes()){
			return 1;
		} else if (e1.getLikes() > e1.getLikes()){
			return -1;
		}
		return e1.getLikes() - e1.getLikes();
	}
}
