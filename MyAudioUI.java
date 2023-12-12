
//Yashvi Sheth
//501161982
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI {
	// create an arraylist of audiocontents where you store the range of songs you
	// want to download
	static ArrayList<AudioContent> range = new ArrayList<AudioContent>();

	// this method will return range
	public static ArrayList<AudioContent> getMyList() {
		return range;
	}

	public static void main(String[] args) {
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your
		// mylibrary
		AudioContentStore store = new AudioContentStore();
		// Create my music mylibrary
		Library mylibrary = new Library();
		// create a new scanner
		try (Scanner scanner = new Scanner(System.in)) {
			// everytime something is types, print>
			System.out.print(">");

			// Process keyboard actions
			while (scanner.hasNextLine()) {
				try {
					String action = scanner.nextLine();

					if (action == null || action.equals("")) {
						System.out.print("\n>");
						continue;
					} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
						return;

					else if (action.equalsIgnoreCase("STORE")) // List all songs in the store
					{
						store.listAll();
					} else if (action.equalsIgnoreCase("SONGS")) // List all songs in the songs playlist
					{
						mylibrary.listAllSongs();
					} else if (action.equalsIgnoreCase("BOOKS")) // List all books in the audiobooks playlist
					{
						mylibrary.listAllAudioBooks();
					} else if (action.equalsIgnoreCase("PODCASTS")) // List all songs
					{
						mylibrary.listAllPodcasts();
					} else if (action.equalsIgnoreCase("ARTISTS")) // List all artists in the artists playlist
					{
						mylibrary.listAllArtists();
					} else if (action.equalsIgnoreCase("PLAYLISTS")) // List all playlists
					{
						mylibrary.listAllPlaylists();
					}
					// Download audiocontent (song/audiobook/podcast) from the store
					// Specify the index of the content
					else if (action.equalsIgnoreCase("DOWNLOAD")) {
						// get the range from the store from the user
						System.out.print("From Store Content #: ");
						int start = scanner.nextInt();
						System.out.print("To Store Content #: ");
						int end = scanner.nextInt();
						// go through each audiocontent from start to end and add that audiocontent to
						// range
						for (int i = start; i < end + 1; i++) {
							AudioContent a = store.getContent(i);
							range.add(a);
						}
						// call download with argumanet range
						mylibrary.download(range);
						// clear range
						range.clear();

					} else if (action.equalsIgnoreCase("DOWNLOADA")) {
						// get the artist name from the user
						System.out.print("Artist: ");
						String name = scanner.nextLine();
						// call downloadArtist with argument name
						mylibrary.downloadArtist(name);

					} else if (action.equalsIgnoreCase("DOWNLOADG")) {
						// get the genre from the user
						System.out.print("Genre: ");
						String genre = scanner.nextLine();
						// call downloadGenre with argument genre
						mylibrary.downloadGenre(genre);
					}
					// Get the *library* index (index of a song based on the songs list)
					// of a song from the keyboard and play the song
					else if (action.equalsIgnoreCase("PLAYSONG")) {
						int index = 0;
						// ask the user which song they want to play
						System.out.print("Song Number: ");
						// if there is an integer, save that int in the index variable
						if (scanner.hasNextInt()) {
							index = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and
												// nextInt())
						}
						// call the playsong method with the paramter as index
						mylibrary.playSong(index);
					}
					// Print the table of contents (TOC) of an audiobook that
					// has been downloaded to the library. Get the desired book index
					// from the keyboard - the index is based on the list of books in the library
					else if (action.equalsIgnoreCase("BOOKTOC")) {
						// ask the user which book they want to play
						System.out.print("Audio Book Number: ");
						// if there is an integer, save that int in the index variable
						int index = scanner.nextInt();
						// call the printAudioBookTOC method with the argument as index
						mylibrary.printAudioBookTOC(index);
						// Print error message if the book doesn't exist in the library
					}
					// Similar to playsong above except for audio book
					// In addition to the book index, read the chapter
					// number from the keyboard - see class Library
					else if (action.equalsIgnoreCase("PLAYBOOK")) {
						// ask the user which book they want to play and save the number in BookNumber
						System.out.print("Audio Book Number: ");
						int bookNumber = scanner.nextInt();
						// ask the user which chapter they want to play and save the number in
						// chapterNumber
						System.out.print("Chapter Number: ");
						int chapterNumber = scanner.nextInt();
						// call the playAudiobook method with the two ints as the arguments
						mylibrary.playAudioBook(bookNumber, chapterNumber);
					}
					// Print the episode titles for the given season of the given podcast
					// In addition to the podcast index from the list of podcasts,
					// read the season number from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PODTOC")) {

					}
					// Similar to playsong above except for podcast
					// In addition to the podcast index from the list of podcasts,
					// read the season number and the episode number from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PLAYPOD")) {

					}
					// Specify a playlist title (string)
					// Play all the audio content (songs, audiobooks, podcasts) of the playlist
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PLAYALLPL")) {
						// ask the user which playlist they want to play and store the title as the
						// title
						System.out.print("Playlist Title: ");
						String playlistTitle = scanner.nextLine();
						// call the playPlaylist method with the title they specified as the argument
						mylibrary.playPlaylist(playlistTitle);
					}
					// Specify a playlist title (string)
					// Read the index of a song/audiobook/podcast in the playist from the keyboard
					// Play all the audio content
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PLAYPL")) {
						// ask the user which playlist they want to play and store the title as the
						// title
						System.out.print("Playlist Title: ");
						String playlistTitle = scanner.nextLine();
						// ask the user which content they want to play and store that number in content
						// number
						System.out.print("Content Number: ");
						int contentNumber = scanner.nextInt();
						// call playPlaylist with the 2 above variables as the arguments
						mylibrary.playPlaylist(playlistTitle, contentNumber);
					}
					// Delete a song from the list of songs in mylibrary and any play lists it
					// belongs to
					// Read a song index from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("DELSONG")) {
						// ask the user which song they want to delete
						System.out.print("Library Song #: ");
						// save that number in index
						int index = scanner.nextInt();
						// call the delete song method with index as the argument
						mylibrary.deleteSong(index);
					}
					// Read a title string from the keyboard and make a playlist
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("MAKEPL")) {
						// ask the user what they want to name the playlist
						System.out.print("Playlist Title: ");
						// save that name in a String
						String playlistname = scanner.nextLine();
						// call the makeplaylist method with their name as the argument
						mylibrary.makePlaylist(playlistname);
					}
					// Print the content information (songs, audiobooks, podcasts) in the playlist
					// Read a playlist title string from the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
					{
						// ask the user which playlist they want to print
						System.out.print("Playlist Title: ");
						// store that in a String
						String playlistname = scanner.nextLine();
						// call the printPlaylist method with the string as the argument
						mylibrary.printPlaylist(playlistname);
					}
					// Add content (song, audiobook, podcast) from mylibrary (via index) to a
					// playlist
					// Read the playlist title, the type of content ("song" "audiobook" "podcast")
					// and the index of the content (based on song list, audiobook list etc) from
					// the keyboard
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("ADDTOPL")) {
						// ask the user which playlist they want to add the content to
						System.out.print("Playlist Title: ");
						// store that value into a String
						String playlistname = scanner.nextLine();
						// let them specify the type of content and store in String
						System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
						String typeName = scanner.nextLine();
						// let them specify the library content so they can search through the content
						// arraylist
						System.out.print("Library content #: ");
						if (scanner.hasNextInt()) {
							// save that number in an int
							int contentnumber = scanner.nextInt();
							// call the addcontenttoplaylist method with the 3 above variables as the
							// arguments
							mylibrary.addContentToPlaylist(typeName, contentnumber, playlistname);
						}
					}
					// Delete content from play list based on index from the playlist
					// Read the playlist title string and the playlist index
					// see class Library for the method to call
					else if (action.equalsIgnoreCase("DELFROMPL")) {
						// ask the user which playlist they want to delete from
						System.out.print("Playlist Title: ");
						// store it in a String
						String playlistname = scanner.nextLine();
						// ask them which library content it is
						System.out.print("Library content #: ");
						// store that in an int
						int contentnumber = scanner.nextInt();
						// call the delContent From Playlist and use the 2 above variables as arguments
						mylibrary.delContentFromPlaylist(contentnumber, playlistname);
					}

					else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
					{
						// call the sortSongsByYear method to sort the songs by year
						mylibrary.sortSongsByYear();
					} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
					{
						// call the sortSongsByName method to sort the songs by name alphabetically
						mylibrary.sortSongsByName();
					} else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
					{
						// call the sortSongsByLength method to sort the songs by length
						mylibrary.sortSongsByLength();
						// if the user types in "Search"
					} else if (action.equalsIgnoreCase("SEARCH")) {
						// get thenm to print the title of the audio content
						System.out.print("Title: ");
						// store it in a String
						String title = scanner.nextLine();
						// call the title search method in store
						store.titleSearch(title);
						// if the user types in searcha
					} else if (action.equalsIgnoreCase("SEARCHA")) {
						// print out artist and ask the user to write an artist
						System.out.print("Artist: ");
						// store it in a String
						String artist = scanner.nextLine();
						// call the correct search method in store
						store.artistSearch(artist);
						// if the user types in searchg
					} else if (action.equalsIgnoreCase("SEARCHG")) {
						// print out the different genres and ask the user to type in a genre
						System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
						// store it in a String
						String genre = scanner.nextLine();
						// call the designated search method in store
						store.genreSearch(genre);
						// if the user types in searchp
					} else if (action.equalsIgnoreCase("SEARCHP")) {
						// tell them to enter a phrase and store it in a string
						System.out.print("Enter phrase: ");
						String phrase = scanner.nextLine();
						// call the searchall method in store
						store.searchAll(phrase);
					}
					System.out.print("\n>");
					// since everything was in a try block, catch any exceptions and print the error
					// message
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					System.out.print(">");
				}
			}
		}
	}
}
