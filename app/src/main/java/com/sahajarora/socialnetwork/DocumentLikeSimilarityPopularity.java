package com.sahajarora.socialnetwork;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//author:adhiraj

public class DocumentLikeSimilarityPopularity implements Strategy{
	private ArrayList<Document> sortedDocs;
	private User user;
	private ArrayList<Document>returnDocs1;
	private ArrayList<Document>returnDocs2;
	public DocumentLikeSimilarityPopularity(){
		sortedDocs = new ArrayList<Document>();
		returnDocs1=new ArrayList<Document>();
		returnDocs2=new ArrayList<Document>();
	}

	@Override
	public ArrayList<Document> getSearchResults(SocialNetwork sn, int topK, User u) {
		sortedDocs.addAll(sn.getDocuments()); // Add all the documents in the social network to sortedDocs
		this.user=u;
		if (sortedDocs.size()>0){
			System.out.println("strategy 4 selected");
			Collections.sort(sortedDocs,new MySimilarDocComp()); //Sorts the sortedDocs list based on the number of likes 
			if (sortedDocs.size()>topK){
				sortedDocs = new ArrayList<>(sortedDocs.subList(0, topK)); // We only want the user to see the top k documents
			}

		}

		return sortedDocs;
	}

	class MySimilarDocComp implements Comparator<Document>{
		@Override
	public int compare(Document e1, Document e2) {
		returnDocs1=e1.getProducer().getLikedDocuments();
		returnDocs1.retainAll(user.getLikedDocuments());
		returnDocs2=e2.getProducer().getLikedDocuments();
		returnDocs2.retainAll(user.getLikedDocuments());
		if(returnDocs1.size() > returnDocs2.size()){
			return 1;
		} else if (returnDocs1.size() < returnDocs2.size()){
			return -1;
		}
			return returnDocs1.size()-returnDocs2.size();
	}
}
}

