
//Yashvi Sheth
//501161982
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist {
	// declare the variables associated with the Playlist class
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture

	// create constructors for the PLaylist variables
	public Playlist(String title) {
		this.title = title;
		this.contents = new ArrayList<AudioContent>();
	}

	// create the accessor methods which can get or set a certain variable part of
	// the playlist class
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addContent(AudioContent content) {
		contents.add(content);
	}

	public ArrayList<AudioContent> getContent() {
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents) {
		this.contents = contents;
	}

	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio
	 * content object
	 * Make sure the index starts at 1
	 */
	public void printContents() {
		// go through the contents arraylist and print the information for the current
		// content
		for (int i = 0; i < contents.size(); i++) {
			System.out.print((i + 1) + ". ");
			contents.get(i).printInfo();
			System.out.println("");
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll() {
		// go through all the content in the contents arraylist
		for (int i = 0; i < contents.size(); i++) {
			// print the information for the current content
			// call the play method for the current content
			contents.get(i).play();
			System.out.println("");
		}
	}

	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range.
	public void play(int index) {
		// check if the index is valid or else return an error message
		if (index < 0 || index > contents.size()) {
			System.out.println("Invalid Index");
		} else {
			// call the play method for the current content
			contents.get(index).play();
		}
	}

	public boolean contains(int index) {
		// return true if the index is valid
		if (index >= 0 && index <= contents.size()) {
			return true;
		}
		return false;
	}

	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other) {
		// cast other as a playlist
		Playlist p = (Playlist) other;
		// if the titles are equal then return true;
		if (this.getTitle().equals(p.getTitle())) {
			return true;
		}
		return false;
	}

	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index) {
		// call the contains method and keep index as the parameter
		// if it is true
		if (contains(index) == true) {
			// remove the value at the index from contents
			contents.remove(index);
		}
	}

}
