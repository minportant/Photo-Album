/*
 * Homework 5 Min Woo Kim, mk4ed 
 * Sources: Big Java Book, Java Website
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class PhotoLibrary extends PhotographContainer {

    /*
     * Holds the id of PhotoLibrary.
     */
    private int id;

    /*
     * Holds the HashSet of albums of PhotoLibrary.
     */
    private HashSet<Album> albums = new HashSet<Album>();

    /*
     * Gets the Album of PhotoLibrary.
     */
    public HashSet<Album> getAlbums() {
        return albums;
    }

    /*
     * Creates a new Album with the given album name in the parameter only if the album with the same name does not exist.
     * Returns true if the adding was successful, false if not.
     * @param albumName The name of the Album to be created if it does not exist already.
     */
    public boolean createAlbum(String albumName) {
        if (getAlbumByName(albumName) == null) {
            this.albums.add(new Album(albumName));
            return true;
        } else {
            return false;
        }
    }

    /*
     * Removes the album with the same name as album name given in the parameter.
     * @param albumName The name of the Album to be removed from HashSet albums
     */
    public boolean removeAlbum(String albumName) {
        return albums.remove(new Album(albumName));

    }

    /*
     * Adds the Photograph to the specified album if and only if that photo does not already exist in the album. Returns
     * true if the adding was successful, false otherwise.
     * @param p The photograph being added.
     * @param albumName The specified name of album for the photo to be added to.
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        if (this.photos.contains(p)) {
            for (Album a : this.albums) {
                if (a.getName().equals(albumName)) {
                    if (a.hasPhoto(p) == false) {
                        a.addPhoto(p);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
     * Removes the Photograph from the specified album. Returns true if successfully removed, false otherwise.
     * @param p The photograph being removed
     * @param albumName The specified name of album for the photo to be removed.
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                if (a.hasPhoto(p) == true) {
                    a.removePhoto(p);
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Returns the album when the name of the album is given.
     * @param albumName The album name that is being searched.
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                return a;
            }
        }
        return null;
    }

    /*
     * If the current objects ArrayList has the photograph p, it erases from the list and returns true. It returns false
     * otherwise. Also erases the same photo in the album if exists.
     * @param p The Photograph that is being erased.
     */
    public boolean removePhoto(Photograph p) {
        for (Album a : this.albums) {
            a.removePhoto(p);
        }
        if (this.photos.contains(p)) {
            this.photos.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Returns string representation of the object.
     */
    public String toString() {
        ArrayList<String> albumNames = new ArrayList<String>();
        for (Album a : albums) {
            albumNames.add(a.getName());
        }
        return "Name: " + this.name + "    ID: " + this.id + "\n Photos: " + this.photos + "\n Albums: " + albumNames;
    }

    /*
     * Constructor that defines name and id to person.
     */
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
        // TODO Auto-generated constructor stub
    }

    /*
     * Sets the name of Person.
     * @param name The name that's set to Person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Gets the id of Person.
     */
    public int getId() {
        return id;
    }

    /*
     * Sets the id of Person.
     * @param id The id that's set to the Person.
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * Sets the photos of Person.
     * @param photos The ArrayList that's set to Person.
     */
    public void setPhotos(ArrayList<Photograph> photos) {
        this.photos = photos;
    }

    /*
     * Compares the class and the elements in the class to see if they are equal.
     * @param o The object that is being compared to.
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        PhotoLibrary otherPerson = (PhotoLibrary) o;
        return this.id == otherPerson.id;
    }

    /*
     * Returns an ArrayList of photos that are common between the photos of person a and person b.
     * @param a One of the person being compared with.
     * @param b The other person being compared with.
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> returnPhoto = new ArrayList<Photograph>();
        for (int i = 0; i < a.photos.size(); i++) {
            for (int j = 0; j < b.photos.size(); j++) {
                if (a.photos.get(i).equals(b.photos.get(j))) {
                    if (!returnPhoto.contains(a.photos.get(i))) {
                        returnPhoto.add(a.photos.get(i));
                    }
                }
            }
        }
        return returnPhoto;
    }

    /*
     * Returns the measure of similarity between photos from person a and photos from person b in terms of numerical value
     * between 0 and 1.
     * @param a One of the person being compared with.
     * @param b The other person being compared with.
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        // creating first, second, and commonNum to simplify the rest of the code.
        // Since it is initialized as double, it does not have to be casted later.
        double first = a.numPhotographs();
        double second = b.numPhotographs();
        double commonNum = commonPhotos(a, b).size();
        if (first == 0 || second == 0) {
            return 0.0;
        } else if (first > second) {
            return commonNum / second;
        } else {
            return commonNum / first;
        }
    }

}
