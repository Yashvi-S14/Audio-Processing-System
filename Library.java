
//Yashvi Sheth
//501161982
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.ErrorManager;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library {
	// declare the array lists that will be used in the library class
	private ArrayList<Song> songs;
	private ArrayList<AudioBook> audiobooks;
	private ArrayList<Playlist> playlists;

	// private ArrayList<Podcast> podcasts;

	// Public methods in this class set errorMesg string
	// Error Messages can be retrieved from main in class MyAudioUI by calling
	// getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	AudioContentStore store = new AudioContentStore();

	public Library() {
		// initialize all the variables
		this.songs = new ArrayList<Song>();
		this.audiobooks = new ArrayList<AudioBook>();
		this.playlists = new ArrayList<Playlist>();
		// podcasts = new ArrayList<Podcast>(); ;
	}

	/*
	 * Download audio content from the store. Since we have decided (design
	 * decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list)
	 * then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or
	 * AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already
	 * there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false.
	 * Otherwise add it to the list and return true
	 * See the video
	 */
	// create a download method which takes in the range from myAudioUI
	public void download(ArrayList<AudioContent> rangeContents) throws ContentNotFoundException {
		// if the list is empty then throw an exception
		if (rangeContents.isEmpty()) {
			throw new ContentNotFoundException("No Match for content");
		} else {
			// or else go through the rangecontents arraylist
			for (int i = 0; i < rangeContents.size(); i++) {
				// if songs contains the current audiocontent at rangecontents
				if (songs.contains(rangeContents.get(i))) {
					// print that it is already downloaded
					System.out.println("Song " + rangeContents.get(i).getTitle() + " is already downloaded");
					// if audiobooks contains the the current audiocontent at rangecontents
				} else if (audiobooks.contains(rangeContents.get(i))) {
					// print that it is downloaded
					System.out.println("Audiobook " + rangeContents.get(i).getTitle() + " is already downloaded");
					// if the type is a song
				} else if (rangeContents.get(i).getType().equalsIgnoreCase("SONG")) {
					// cast the current auidocontent as a song and add it to the songs arraylist
					songs.add((Song) rangeContents.get(i));
					// print that it has been added to the library
					System.out.println(
							rangeContents.get(i).getType() + " " + rangeContents.get(i).getTitle()
									+ " Added to Library");
					// if the type of the current audiocontent is audiobook
				} else if (rangeContents.get(i).getType().equalsIgnoreCase("AUDIOBOOK")) {
					// cast the current auidocontent as a audiobook and add it to the audiobooks
					// arraylist
					audiobooks.add((AudioBook) rangeContents.get(i));
					// print that is has been added to library
					System.out.println(
							rangeContents.get(i).getType() + " " + rangeContents.get(i).getTitle()
									+ " Added to Library");
				} else {
					throw new ContentNotFoundException("Invalid input");

				}
			}
		}
	}

	// create a download for artists that takes in a string artist
	public void downloadArtist(String artist) throws ContentExistsException {
		// if it is empty then throw an exception
		if (artist.isEmpty()) {
			throw new ContentNotFoundException("No Match for content");
		} else {
			// save the value at the artist key from the artist hashmap into indices by
			// calling the method which returns the value
			ArrayList<Integer> indices = store.aDownload(artist);
			// go through the indices arraylist
			for (int i = 0; i < indices.size(); i++) {
				// save the content from indeces at i into an audiocontent
				AudioContent a = store.getContent(indices.get(i));
				// if the type is a song
				if (a.getType().equalsIgnoreCase("SONG")) {
					// if the song contains the song version of a
					if (songs.contains((Song) a)) {
						// print that it is already downloaded
						System.out.println(a.getType() + " " + a.getTitle() + " Already downloaded");
					} else {
						// cast a as a song and add it to songs
						songs.add((Song) a);
						// print that it has been added to the library
						System.out.println(a.getType() + " " + a.getTitle() + " Added to Library");
					}
					// if the type is an audiobook
				} else if (a.getType().equalsIgnoreCase("AUDIOBOOK")) {
					// if the audiobook contains an audiobook version of a
					if (audiobooks.contains((AudioBook) a)) {
						// print out that it is already downloaded
						System.out.println(a.getType() + " " + a.getTitle() + " Already downloaded");
					} else {
						// or else add the audiobook version of a into audiobooks and print that it has
						// been added to the library
						audiobooks.add((AudioBook) a);
						System.out.println(a.getType() + " " + a.getTitle() + " Added to Library");
					}
				}
			}
			// clear indices
			indices.clear();
		}
	}

	// download genre
	public void downloadGenre(String genre) throws ContentExistsException {
		// if it is empty
		if (genre.isEmpty()) {
			throw new ContentExistsException("No matches for content");
		} else {
			// save the value at the genre key from the genre hashmap into indices by
			// calling the method which returns the value
			ArrayList<Integer> indices = store.gDownload(genre);
			// go through the indices
			for (int i = 0; i < indices.size(); i++) {
				// save the content from indeces at i into an audiocontent
				AudioContent a = store.getContent(indices.get(i));
				// if the type is song
				if (a.getType().equalsIgnoreCase("SONG")) {
					// and if songs contains the song version of a
					if (songs.contains((Song) a)) {
						// print that it is already downloaded
						System.out.println(a.getType() + " " + a.getTitle() + " Already downloaded");
					} else {
						// or else cast a as a song and add it to songs
						songs.add((Song) a);
						// print that it it has been added to library
						System.out.println(a.getType() + " " + a.getTitle() + " Added to Library");
					}
				}
			}
			// clear indices
			indices.clear();
		}
	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() {
		// go through the songs array list
		for (int i = 0; i < songs.size(); i++) {
			// the printing index is 1 more than i
			int index = i + 1;
			System.out.print("" + index + ". ");
			// print out the information at the current song
			songs.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() {
		// go through the audiobooks arraylist
		for (int i = 0; i < audiobooks.size(); i++) {
			// printing index is 1 more than i
			int index = i + 1;
			System.out.print("" + index + ". ");
			// print out the information for the current audiobook
			audiobooks.get(i).printInfo();
			System.out.println();
		}

	}

	// Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts() {

	}

	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists() {
		// go through the playlists array list
		for (int i = 0; i < playlists.size(); i++) {
			int index = i + 1;
			// get the title of the current playlist and print it
			System.out.print("" + index + ". " + playlists.get(i).getTitle());
			System.out.println();
		}
	}

	// Print the name of all artists.
	public void listAllArtists() {
		// create an arraylist that will hold all the artists
		ArrayList<String> listOfArtists = new ArrayList<String>();
		// go through the songs arraylist
		for (int i = 0; i < songs.size(); i++) {
			// get the artist of the current song, and if listOfArtists does not contain
			// that artist
			if (!listOfArtists.contains(songs.get(i).getArtist())) {
				// add that artist to the listOfArtists arraylist
				listOfArtists.add(songs.get(i).getArtist());
			}
		}
		// then go through the listOfArtists arraylist and print the names in order
		for (int i = 0; i < listOfArtists.size(); i++) {
			System.out.println(i + 1 + ". " + listOfArtists.get(i));
		}
		// First create a new (empty) array list of string
		// Go through the songs array list and add the artist name to the new arraylist
		// only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists
		// names

	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well if it
	// is part of the playlist
	public void deleteSong(int index) throws IndexOutOfBoundsException {
		// if the index is out of bounds, throw an exception
		if (index < 1 || index > songs.size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		} else {
			// go through playlists
			for (int i = 0; i < playlists.size(); i++) {
				// if the content of the current playlist is equal to that of the song in the
				// songs
				// playlist at index-1, call the deleteContent method on that playlist
				if (playlists.get(i).getContent().contains(songs.get(index - 1))) {
					// if the index is not a valid index, print out the error message
					// or else, go to the deleteContent method of the current playlist
					// and delete the content at index-1
					playlists.get(i).deleteContent(index - 1);
				}
			}
		}
		// as well, remove the song from the library at index-1
		songs.remove(index - 1);
	}

	// Sort songs in library by year
	public void sortSongsByYear() {
		// Use Collections.sort()
		// sort the
		// sort the songs by year by calling Collections.sort and creating a new
		// instance of the songYearCOmparator class
		Collections.sort(songs, new SongYearComparator());
	}

	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> {
		// create a compare method that will compare 2 songs
		public int compare(Song a, Song b) {
			// check the years and return 1 if the year is greater than the year
			// in b and return -1 if it is less than that
			if (a.getYear() > b.getYear()) {
				return 1;
			} else if (a.getYear() < b.getYear()) {
				return -1;
			}
			return 0;
		}
	}

	// Sort songs by length
	public void sortSongsByLength() {
		// Use Collections.sort()
		// sort the song by creatsing a new instance of the songlengthcomparator class
		Collections.sort(songs, new SongLengthComparator());

	}

	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song> {
		public int compare(Song a, Song b) {
			// create a method that returns an int and compares 2 song
			// if the length of song a in greater than the length of song b, return 1
			if (a.getLength() > b.getLength()) {
				return 1;
			}
			// or else return -1 if it is less than the length of b
			else if (a.getLength() < b.getLength()) {
				return -1;
			}
			return 0;
		}
	}

	// Sort songs by title
	public void sortSongsByName() {
		// Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		// sort the songs using collections.sort
		// it gets sorted because of of the compareTo method in Song which implements
		// comparable
		Collections.sort(songs);
	}

	/*
	 * Play Content
	 */

	// Play song from songs list
	public void playSong(int index) throws IndexOutOfBoundsException {
		// if the index is less than 1 and is greater then the songs list, print the
		// error message
		if (index < 1 || index > songs.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		// or else call the play method for the song at index-1
		songs.get(index - 1).play();
	}

	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode) {
		return false;
	}

	// Print the episode titles of a specified season
	// Bonus
	public boolean printPodcastEpisodes(int index, int season) {
		return false;
	}

	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws ContentNotFoundException {
		// if the index is less than 1 and is greater then the audiobooks list, throw an
		// exception
		if (index < 1 || index > audiobooks.size()) {
			throw new ContentNotFoundException("Audiobook not found");
		}
		// if the chapter number is out of bounds throw an error message
		if (chapter < 1 || chapter > audiobooks.get(index - 1).getNumberOfChapters()) {
			throw new ContentNotFoundException("Chapter not found");
		} else {
			// call the select chapter method for the current audiobook and make the
			// parameter the chapter
			audiobooks.get(index - 1).selectChapter(chapter);
			audiobooks.get(index - 1).play();
		}
	}

	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) throws ContentNotFoundException {
		// if the index is invalid, throw an excpetion
		if (index < 0 || index > audiobooks.size()) {
			throw new ContentNotFoundException("Book chapter not found");
		}
		// or else call the printTOC method for the current audiobook
		audiobooks.get(index - 1).printTOC();
	}

	/*
	 * Playlist Related Methods
	 */

	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) throws ContentNotFoundException {
		// go through the playlists arraylist
		for (int i = 0; i < playlists.size(); i++) {
			// if the title of the current playlist and the paramter title are equal
			if (playlists.get(i).getTitle().equals(title)) {
				// throw an excpetion
				throw new ContentNotFoundException("Playlist Already Exists");
			}
		}
		// or else create a new playlist with the paramter as the title
		Playlist p = new Playlist(title);
		// add that playlist to playlists and return true
		playlists.add(p);
	}

	// Print list of content information (songs, audiobooks etc) in playlist named
	// title from list of playlists
	public void printPlaylist(String title) throws ContentNotFoundException {
		// go through all the playlists
		for (int i = 0; i < playlists.size(); i++) {
			// if the current playlist title is equal to the argument
			if (playlists.get(i).getTitle().equals(title)) {
				// call the printContents method for the current playlist
				playlists.get(i).printContents();
			} else {
				// or else throw an error message
				throw new ContentNotFoundException("Playlist does not exist");
			}
		}
	}

	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) throws ContentNotFoundException {
		// go through all the playlists
		for (int i = 0; i < playlists.size(); i++) {
			// if the current playlist's title is equal to the argument
			if (playlists.get(i).getTitle().equals(playlistTitle)) {
				// call the playAll method for the current playlist
				playlists.get(i).playAll();
				// or else throw an error message
			} else {
				throw new ContentNotFoundException(playlistTitle + " does not exist");
			}
		}
	}

	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) throws ContentNotFoundException {
		// go through the playlists arraylist
		for (int i = 0; i < playlists.size(); i++) {
			// if the title of the current playlist is equal to the argument playlisttitle
			if (playlists.get(i).getTitle().equals(playlistTitle)) {
				// call the play method for the current playlist and make the argument for that
				// method the index paramter -1
				playlists.get(i).play(indexInPL - 1);
			} else {
				throw new ContentNotFoundException(playlistTitle + " does not exist");
			}
		}
	}

	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) throws IndexOutOfBoundsException {

		// make a boolean variable to see if the playlist exists
		boolean playlistExists = false;
		// create a variable that keeps track of the index in the playlist
		int indexInPL = 0;
		// go through the playlists
		for (int i = 0; i < playlists.size(); i++) {
			// if the current playlist equals the playlist title
			if (playlists.get(i).getTitle().equals(playlistTitle)) {
				// set playlistexists as true
				playlistExists = true;
				// make indexInPl i
				indexInPL = i;
			}
		}
		// if the playlist exists
		if (playlistExists == true) {
			// if the type is audiobook
			if (type.equalsIgnoreCase(AudioBook.TYPENAME)) {
				// if the index is out of bounds
				if (index < 1 || index > audiobooks.size()) {
					// throw an index out of bounds exception
					throw new IndexOutOfBoundsException("Index out of bounds");
				} else {
					// or else call the addContent method on the audio content at indexInPl
					playlists.get(indexInPL).addContent(audiobooks.get(index - 1));
				}
				// if the type is a song
			} else if (type.equalsIgnoreCase(Song.TYPENAME)) {
				// if the index is out of bounds
				if (index < 1 || index > songs.size()) {
					// throw an index out of bounds exception
					throw new IndexOutOfBoundsException("Index out of bounds");
				} else {
					// or else call the addContent method on the audio content at indexInPl
					playlists.get(indexInPL).addContent(songs.get(index - 1));
				}
			}
		}
	}

	// Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is
	// valid
	public void delContentFromPlaylist(int index, String title) throws ContentNotFoundException {
		// go through the playlists
		for (int i = 0; i < playlists.size(); i++) {
			// if the playlist titles are equal
			if (playlists.get(i).getTitle().equals(title)) {
				// check if the index is valid and if it is not, throw an exception
				if (index < 1 || index > playlists.get(i).getContent().size()) {
					throw new ContentNotFoundException("Playlist does not exist");
				} else {
					// or else call the deleteContent method on the current playlist and make the
					// argument index - 1
					playlists.get(i).deleteContent(index - 1);
				}
			}
		}

	}

	// create 2 custom classes which extend runtimeException
	class ContentExistsException extends RuntimeException {
		// create a method which takes in a content
		public ContentExistsException(String content) {
			// this constructs a new runtime Exception
			super(content);
		}
	}

	class ContentNotFoundException extends RuntimeException {
		// create a method which takes in a content
		public ContentNotFoundException(String content) {
			// this constructs a new runtimeException
			super(content);
		}
	}

}
