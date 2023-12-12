
//Yashvi Sheth
//501161982
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	// create an arraylist of AudioContent called contents
	private ArrayList<AudioContent> contents;
	// create a hashmap which maps a string to an integer
	private Map<String, Integer> titleToIndexMap = new HashMap<String, Integer>();
	// create a hasmap which maps a string to an arrylist of integers
	private Map<String, ArrayList<Integer>> artistToInt = new HashMap<String, ArrayList<Integer>>();
	// create a hashmap which maps a genre to an arraylist of integers
	private Map<Song.Genre, ArrayList<Integer>> genreToInt = new HashMap<Song.Genre, ArrayList<Integer>>();

	public AudioContentStore() {

		// initialize the contents arraylist
		contents = new ArrayList<AudioContent>();

		try {
			// create a new file from "store.txt"
			File file = new File("store.txt");
			// create a new scanner which goes through the file
			Scanner scan = new Scanner(file);
			// while the scanner has a next line
			while (scan.hasNextLine()) {
				// stote the next line in a string
				String line = scan.nextLine();
				// if the string is equal to song
				if (line.equalsIgnoreCase("song")) {
					// make the type the line
					String type = line;
					// make the id the next line
					String id = scan.nextLine();
					// make the title the next line
					String title = scan.nextLine();
					// make the year by parsing the next line as an int
					int year = Integer.parseInt(scan.nextLine());
					// make the length by parsing the next line as an int
					int length = Integer.parseInt(scan.nextLine());
					// make the next line the artist
					String artist = scan.nextLine();
					// make the next line the composer
					String composer = scan.nextLine();
					// make the next line a genre
					Song.Genre genre = Song.Genre.valueOf(scan.nextLine());
					// make the number of lyrics by parsing the next line as an int
					int numberOfLyrics = Integer.parseInt(scan.nextLine());
					// create a lyrics string
					String lyrics = "";
					// create a for loop that goes through the scanner int numberOfLyrics times
					for (int i = 0; i < numberOfLyrics; i++) {
						// add the nezt line to the lyrics string
						lyrics += scan.nextLine() + "\n";
					}
					// create a new song and use the bove string and ints as parameters
					Song s = new Song(title, year, id, type, artist, length, artist, composer, genre, lyrics);
					// add that song to contents
					contents.add(s);
					// if the line ia audiobook
				} else if (line.equalsIgnoreCase("audiobook")) {
					// make the type the line
					String type = line;
					// make the id the next line
					String id = scan.nextLine();
					// make the title the next line
					String title = scan.nextLine();
					// make the year by parsing the next line as an int
					int year = Integer.parseInt(scan.nextLine());
					// make the length by parsing the next line as an int
					int length = Integer.parseInt(scan.nextLine());
					// make the author the next line
					String author = scan.nextLine();
					// make the narrator the next line
					String narrator = scan.nextLine();
					// parse the next line as an int and store that as the number of chapter titles
					int numofchaptertitles = Integer.parseInt(scan.nextLine());
					// create an arraylist that holds the chapter titles
					ArrayList<String> chapterTitles = new ArrayList<String>();
					// create a for loop that will go through the file numofchaptertitles times
					for (int i = 0; i < numofchaptertitles; i++) {
						// add the next line to the chapter titles arraylist
						chapterTitles.add(scan.nextLine());
					}
					// make the number of lines by parsing the next line as an int
					int numoflines = Integer.parseInt(scan.nextLine());
					// create a new string for the lines
					String lines = "";
					// create a new chapters arraylist
					ArrayList<String> chapters = new ArrayList<String>();
					// go through the file numofchaptertitles times
					for (int i = 0; i < numofchaptertitles; i++) {
						// go through the file numoflines time and then add the next line to the lines
						// string
						for (int j = 0; j < numoflines; j++) {
							lines += scan.nextLine() + "\n";
						}
						// add the lines to the chapters arraylist
						chapters.add(lines);
						// rest the lines string
						lines = "";
					}
					// create a new audiobook and add the above string and ints as parameters
					AudioBook a = new AudioBook(title, year, id, type, null, length, author,
							narrator, chapterTitles, chapters);
					// add that audiobook to the contents arraylist
					contents.add(a);

				}
			}
			// close the scanner
			scan.close();
			// go through the contents arraylist
			for (int i = 0; i < contents.size(); i++) {
				// put the title of the current content and map that to i+1
				titleToIndexMap.put(contents.get(i).getTitle(), i + 1);
			}

			// go through the contents arraylist
			for (int i = 0; i < contents.size(); i++) {
				// get the type of the current audio content and if it is a song
				if (contents.get(i).getType().equalsIgnoreCase("song")) {
					// then cast the current audiocontent as a song
					Song s = (Song) contents.get(i);
					// if the artist hashmap already contains the key which is the artist
					if (artistToInt.containsKey(s.getArtist())) {
						// create an integer arraylist which is equal to the value of the key on the
						// hashmap which is the song's artist name
						ArrayList<Integer> intList = artistToInt.get(s.getArtist());
						// add i+1 to intList
						intList.add(i + 1);
					} else {
						// or else create a new arraylist
						ArrayList<Integer> intList = new ArrayList<Integer>();
						// add i+1 to the intList
						intList.add(i + 1);
						// put the artist in the artist hashmap as the key and intList as the value
						artistToInt.put(s.getArtist(), intList);
					}
				}
			}

			// go through the contents arraylist
			for (int i = 0; i < contents.size(); i++) {
				// if the type for the current content is song
				if (contents.get(i).getType().equalsIgnoreCase("song")) {
					// then cast the current audiocontent as a song
					Song s = (Song) contents.get(i);
					// if the genre hashmap contains the genre as a key
					if (genreToInt.containsKey(s.getGenre())) {
						// create an integer arraylist which is equal to the value at the genre key on
						// the genre hashmap
						ArrayList<Integer> intList = genreToInt.get(s.getGenre());
						// add i+1 to the arraylist
						intList.add(i + 1);
					} else {
						// create a new integer arraylist
						ArrayList<Integer> intList = new ArrayList<Integer>();
						// add i+1 to the arraylist
						intList.add(i + 1);
						// put the genre of the song as the key in the genre hashmap and make the value
						// the integer arraylist
						genreToInt.put(s.getGenre(), intList);
					}
				}

			}
			// get the errormessage if an error is caught
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchAll(String phrase) {
		// go through the contents arraylist
		for (AudioContent a : contents) {
			// if the type of the audiocontent is song
			if (a.getType().equalsIgnoreCase("SONG")) {
				// then cast the current audiocontent as a song
				Song s = (Song) a;
				// if any paramter has the given phrase within it then print the information for
				// the song
				if (s.getTitle().contains(phrase) || s.getArtist().contains(phrase) || s.getComposer().contains(phrase)
						|| s.getId().contains(phrase) || s.getLyrics().contains(phrase)
						|| s.getType().contains(phrase)) {
					s.printInfo();
				}
			}
			// if the type is an audiobook
			if (a.getType().equalsIgnoreCase("AUDIOBOOK")) {
				// cast the content as an audiobook
				AudioBook b = (AudioBook) a;
				// if any paramter has the given phrase within it then print the information for
				// the audiobook
				if (b.getTitle().contains(phrase) || b.getAuthor().contains(phrase) || b.getNarrator().contains(phrase)
						|| b.getId().contains(phrase) || b.getChapterTitles().contains(phrase)
						|| b.getType().contains(phrase) || b.getChapters().contains(phrase)) {
					b.printInfo();
				}
			}
		}
	}

	// create a getContent method that takes an int as an argument
	public AudioContent getContent(int index) {
		// creturn null if the index is not valid
		if (index < 1 || index > contents.size()) {
			return null;
		}
		// or else return the value at contents at index-1
		return contents.get(index - 1);
	}

	// search for the title
	public void titleSearch(String title) {
		// if the title hashmap contains the title as a key
		if (titleToIndexMap.containsKey(title)) {
			// the index is equal to the value of the key
			int index = titleToIndexMap.get(title);
			// get the content at index
			AudioContent a = getContent(index);
			// print the index and then print the info
			System.out.print(index + ". ");
			a.printInfo();
		} else {
			// or else print that there is no match
			System.out.print("No matches for " + title);
		}
	}

	// search for the artist
	public void artistSearch(String artist) {
		// if the artist hashmap contains the artist as a key
		if (artistToInt.containsKey(artist)) {
			// the intList is equal to the value at the key
			ArrayList<Integer> intList = artistToInt.get(artist);
			// go through the intList arraylist
			for (int i = 0; i < intList.size(); i++) {
				// index is equal to the arrayList content at i
				int index = intList.get(i);
				// get the content at the index
				AudioContent a = getContent(index);
				// print out the information
				System.out.print(index + ". ");
				a.printInfo();
				System.out.print("\n");
			}

			// or else print that there are no matches
		} else {
			System.out.print("No matches for " + artist);
		}
	}

	// search for the genre
	public void genreSearch(String genre) {
		// turn the genre string into an actual genre
		Song.Genre g = Song.Genre.valueOf(genre);
		// if the genrehashmap contains the key g
		if (genreToInt.containsKey(g)) {
			// the arraylist is equal to the value from the genre hashmap at key g
			ArrayList<Integer> intList = genreToInt.get(g);
			// go through the intList arraylist
			for (int i = 0; i < intList.size(); i++) {
				// the index is equal to the current index at the intList
				int index = intList.get(i);
				// the audiocontent is equal to getContent
				AudioContent a = getContent(index);
				// print the information
				System.out.print(index + ". ");
				a.printInfo();
				System.out.print("\n");
			}

		} else {
			// or else print the there are no matches
			System.out.print("No matches for " + genre);
		}
	}

	// this method returns the integer arraylist which is the value for the key
	// artist in the artist hashmap
	public ArrayList<Integer> aDownload(String artist) {
		if (artistToInt.containsKey(artist)) {
			return artistToInt.get(artist);
		}
		return null;
	}

	// this method returns the integer arraylist which is the value for the key
	// genre in the genre hashmap
	public ArrayList<Integer> gDownload(String genre) {
		Song.Genre g = Song.Genre.valueOf(genre);
		if (genreToInt.containsKey(g)) {
			return genreToInt.get(g);
		}
		return null;
	}

	public void listAll() {
		// go through the contents arraylist
		for (int i = 0; i < contents.size(); i++) {
			// index is i +!
			int index = i + 1;
			// print that out
			System.out.print("" + index + ". ");
			// call the printInfo method on the current content
			contents.get(i).printInfo();
			// add a new line
			System.out.println();
		}
	}

}
