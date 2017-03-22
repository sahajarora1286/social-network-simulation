package com.sahajarora.socialnetwork;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//author:adhiraj
//show following's uploaded docs first, then followers docs, then non 
public class DocumentFriendPopularity implements Strategy{
private ArrayList<Document> sortedDocs;
private ArrayList<Document> returnDocs;
private User user;
	public DocumentFriendPopularity(){
		
		sortedDocs = new ArrayList<Document>();
		
	}

	@Override
	public ArrayList<Document> getSearchResults(SocialNetwork sn, int topK, User u) {
		// TODO Auto-generated method stub
		sortedDocs.addAll(sn.getDocuments()); // Add all the documents in the social network to sortedDocs
		this.user=u;
		if (sortedDocs.size()>0){
			System.out.println("strategy 3 selected");
			for (int i=0;i<sortedDocs.size()-1;i++){
			if (sortedDocs.get(i).getProducer().getFollowing().contains(user)){
				sortedDocs.get(i).getProducer().setDistance(1);
		}else if (sortedDocs.get(i).getProducer().getFollowers().contains(user)){
			sortedDocs.get(i).getProducer().setDistance(2);
	}else{sortedDocs.get(i).getProducer().setDistance(0);
}
	}
			Collections.sort(sortedDocs,new MyFriendDocComp());
			if (sortedDocs.size()>topK){
				sortedDocs = new ArrayList<>(sortedDocs.subList(0, topK)); // We only want the user to see the top k documents
			}
		}
		return sortedDocs;
	}

	}
class MyFriendDocComp implements Comparator<Document>{
	@Override
	public int compare(Document e1, Document e2) {
		if(e1.getProducer().getDistance() > e2.getProducer().getDistance()){
			return 1;
		} else if (e1.getProducer().getDistance() < e2.getProducer().getDistance()){
			return -1;
		}
		return e1.getProducer().getDistance() - e2.getProducer().getDistance();
	}
}