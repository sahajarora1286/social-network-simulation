package com.sahajarora.socialnetwork;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Each tag has a unique ID. A user's taste can have many tags, whereas a document can have only one tag.
 *  @author Sahajnoor Arora
 */
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;

	public Tag(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of this tag.
	 * @return Unique ID of this tag.
	 */
	public int getId(){
		return id;
	}

	/* 
	 * @return tag ID in strign form
	 */
	@Override
	public String toString(){
		return ("Tag " + id);
	}

}
