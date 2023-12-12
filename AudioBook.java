
//Yashvi Sheth
//501161982
import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
//make audiobook a subclass of AudioContent
public class AudioBook extends AudioContent {
	// make sure the type is audiobook
	public static final String TYPENAME = "AUDIOBOOK";

	// initialize all the variables associated with the audibook class
	private String author;
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	// create the constructors for the audiobook class
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
			String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters) {
		super(title, year, id, type, audioFile, length);
		this.author = author;
		this.narrator = narrator;
		this.currentChapter = 0;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
		// Make use of the constructor in the super class AudioContent.
		// Initialize additional AudioBook instance variables.
	}

	// return the type name whenever getType is called
	public String getType() {
		return TYPENAME;
	}

	// Print information about the audiobook. First print the basic information of
	// the AudioContent
	// by making use of the printInfo() method in superclass AudioContent and then
	// print author and narrator
	// see the video
	public void printInfo() {
		super.printInfo();
		System.out.println("Author: " + author + " Narrated by: " + narrator);
	}

	// Play the audiobook by setting the audioFile to the current chapter title
	// (from chapterTitles array list)
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play() {
		// creating a variable for the chapter which includes the current chapter title
		// and the actual chapter
		// you call the setAudioFile
		var chapter = getChapterTitles().get(currentChapter) + '\n' + getChapters().get(currentChapter);
		setAudioFile(chapter);
		super.play();

	}

	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC() {
		// go through the chapterTitles array list and print the names in order
		for (int i = 0; i < chapterTitles.size(); i++) {
			System.out.println("Chapter " + (i + 1) + ". " + chapterTitles.get(i) + '\n');
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter) {
		// set current chapter to the chapter number-1 if it is a valid index
		if (chapter >= 1 && chapter <= chapters.size()) {
			currentChapter = chapter - 1;
		}
	}

	// Two AudioBooks are equal if their AudioContent information is equal and both
	// the author and narrators are equal
	public boolean equals(Object other) {
		if (super.equals(other)) {
			// cast object as an audiobook and check if everything including the author and
			// narrator are equal
			AudioBook a = (AudioBook) other;
			if (a.author.equals(this.author) && a.narrator.equals(this.narrator)) {
				return true;
			}
		}
		return false;
	}

	// create the accessor methods that either gets or sets variables part of the
	// AudioBook class
	public int getNumberOfChapters() {
		return chapters.size();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNarrator() {
		return narrator;
	}

	public void setNarrator(String narrator) {
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles() {
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles) {
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters() {
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters) {
		this.chapters = chapters;
	}

}
