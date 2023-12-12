
//Yashvi Sheth
//501161982
import javax.naming.event.ObjectChangeListener;

/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
//make song a subclass of audiocontent and let it implement the comparable interface
public class Song extends AudioContent implements Comparable<Song>// implement the Comparable interface
{
	// make the typename a song
	public static final String TYPENAME = "SONG";

	// create an enum to spciefy the genre
	public static enum Genre {
		POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL
	};

	// declare the variables associated with the song class
	private String artist; // Can be multiple names separated by commas
	private String composer; // Can be multiple names separated by commas
	private Genre genre;
	private String lyrics;

	// create constructors for all the variables and initalize them
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics) {
		super(title, year, id, type, audioFile, length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
		// Make use of the constructor in the super class AudioContent.
		// Initialize additional Song instance variables.
	}

	// the gettype method should return the type of audio content it is
	public String getType() {
		return TYPENAME;
	}

	// Print information about the song. First print the basic information of the
	// AudioContent
	// by making use of the printInfo() method in superclass AudioContent and then
	// print artist, composer, genre
	public void printInfo() {
		// print out the information from the super class and then add the artist,
		// composer and genre
		super.printInfo();
		System.out.println("Artist: " + artist + " Composer: " + composer + " Genre: " + genre);
	}

	// Play the song by setting the audioFile to the lyrics string and then calling
	// the play() method of the superclass
	public void play() {
		// set the audiofile as the lyrics
		this.setAudioFile(lyrics);
		// call the super method to play
		super.play();
	}

	// create the accessor methods that either get or set the variables
	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	// Two songs are equal if their AudioContent information is equal and both the
	// composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other) {
		// see if the variables from the super content is equal to those of other
		if (super.equals(other)) {
			// if so, cast other as a song
			Song s = (Song) other;
			// if both song's composers and artists are equal return true
			if (s.composer.equals(this.composer) && s.artist.equals(this.artist)) {
				return true;
			}
		}
		// or else return false
		return false;
	}

	// Implement the Comparable interface
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other) {
		// return the int that is associated with whether or not the current song's
		// title is equal to the title of other's title
		return this.getTitle().compareTo(other.getTitle());
	}

}
