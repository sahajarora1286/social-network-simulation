package com.sahajarora.socialnetwork;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//author:adhiraj
public class DocumentUserPopularity implements Strategy{
	
	private ArrayList<Document> sortedDocs;
	
	public DocumentUserPopularity(){
		
		sortedDocs = new ArrayList<Document>();
	
	}

	@Override
	public ArrayList<Document> getSearchResults(SocialNetwork sn, int topK,User u) {
		// TODO Auto-generated method stub
		sortedDocs.addAll(sn.getDocuments()); // Add all the documents in the social network to sortedDocs

		if (sortedDocs.size()>0){
			System.out.println("strategy 2 selected");
			Collections.sort(sortedDocs,new MyUserDocComp()); //Sorts the sortedDocs list based on the number of likes 
			if (sortedDocs.size()>topK){
				sortedDocs = new ArrayList<>(sortedDocs.subList(0, topK)); // We only want the user to see the top k documents
			}
		}
		return sortedDocs;
	}
}
class MyUserDocComp implements Comparator<Document>{
	@Override
	public int compare(Document e1, Document e2) {
		if(e1.getProducer().getFollowers().size() < e2.getProducer().getFollowers().size()){
			return 1;
		} else if (e1.getProducer().getFollowers().size() > e2.getProducer().getFollowers().size()){
			return -1;
		}
		return e1.getProducer().getFollowers().size() - e2.getProducer().getFollowers().size();
	}
}
