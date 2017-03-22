package com.sahajarora.socialnetwork;
import java.util.ArrayList;
import java.util.List;


/*
 * @author Sahajnoor Arora
 */
public interface Strategy {
	public ArrayList<Document> getSearchResults(SocialNetwork sn, int topK, User u);
}
