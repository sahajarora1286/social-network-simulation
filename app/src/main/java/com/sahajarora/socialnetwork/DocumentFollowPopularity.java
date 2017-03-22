package com.sahajarora.socialnetwork;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//author:adhiraj
public class DocumentFollowPopularity implements Strategy{
	private ArrayList<Document>sortedDocs;
	private ArrayList<User>returnFollowerse1;
	private ArrayList<User>returnFollowerse2;
	private User user;
public DocumentFollowPopularity(){
	sortedDocs = new ArrayList<Document>();
}

@Override
public ArrayList<Document> getSearchResults(SocialNetwork sn, int topK, User u) {
	// TODO Auto-generated method stub
	sortedDocs.addAll(sn.getDocuments()); // Add all the documents in the social network to sortedDocs
	this.user=u;
	if (sortedDocs.size()>0){
		System.out.println("strategy 5 selected");
		Collections.sort(sortedDocs,new MyFollowDocComp()); //Sorts the sortedDocs list based on the number of likes 
		if (sortedDocs.size()>topK){
			sortedDocs = new ArrayList<>(sortedDocs.subList(0, topK)); // We only want the user to see the top k documents
		}

	}

	return sortedDocs;
}
class MyFollowDocComp implements Comparator<Document>{
	@Override
public int compare(Document e1, Document e2) {
returnFollowerse1=e1.getProducer().getFollowers();
returnFollowerse2=e2.getProducer().getFollowers();
returnFollowerse1.retainAll(user.getFollowers());
returnFollowerse2.retainAll(user.getFollowers());
	if(returnFollowerse1.size() > returnFollowerse2.size()){
		return 1;
	} else if (returnFollowerse1.size() < returnFollowerse2.size()) {
		return -1;
	}
	return returnFollowerse1.size() - returnFollowerse2.size();
}

}}
