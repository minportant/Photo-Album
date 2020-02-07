/*
 * Homework 5 Min Woo Kim, mk4ed 
 * Sources: Big Java Book, Java Website
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class PhotographContainer {

    /*
     * Holds the name of Album.
     */
    protected String name;

    /*
     * Holds the ArrayList of Photograph of Album
     */
    protected ArrayList<Photograph> photos = new ArrayList<Photograph>();

    /*
     * Gets the name of Album.
     */
    public String getName() {
        return name;
    }

    /*
     * Sets the name of the album.
     * @param name The name that the Album is set to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Constructor for Album defines the name of Album.
     * @param name The name it is defined to
     */
    public PhotographContainer(String name) {
        this.name = name;
    }

    /*
     * Gets the photos of the Album.
     */
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    /*
     * Adds the photograph to the current list of photos if and only if it does not exist already. Returns true if
     * successfully added, false otherwise.
     * @param p The photograph being added.
     */
    public boolean addPhoto(Photograph p) {
        if (p == null) {
            return false;
        } else if (!this.photos.contains(p)) {
            this.photos.add(p);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Returns true if the current object has the Photograph.
     * @param p The photograph being searched to see if it exists.
     */
    public boolean hasPhoto(Photograph p) {
        return this.photos.contains(p);
    }

    /*
     * Removes photograph p from the album. Returns true if successful, false otherwise.
     * @param p The photograph being searched to be removed.
     */
    public boolean removePhoto(Photograph p) {
        if (this.photos.contains(p)) {
            this.photos.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Returns the size or the number of photos in the Album.
     */
    public int numPhotographs() {
        return this.photos.size();
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
        Album otherAlbum = (Album) o;
        return this.name == otherAlbum.name;
    }

    /*
     * Returns string representation of the object.
     */
    public String toString() {
        return "Name: " + this.name + "\n Photos: " + this.photos;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /*
     * This method returns an ArrayList of photos from PhotoLibrary of photos that has a greater rating than the given
     * parameter.
     * @param rating The rating needed to be added to the returnPhotos.
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> returnPhotos = new ArrayList<Photograph>();
        for (Photograph p : this.photos) {
            if (p.getRating() >= rating) {
                returnPhotos.add(p);
            }
        }
        return returnPhotos;
    }

    /*
     * Returns an ArrayList of photos from PhotoLibrary of photos that were taken in the year of given parameter.
     * @param year The year specified to match to be added into the return ArrayList.
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> returnPhotos = new ArrayList<Photograph>();
        if (year < 0) {
            return null;
        }
        for (Photograph p : this.photos) {
            if (p.getDateTaken().substring(4, 5).equals("-") && p.getDateTaken().substring(7, 8).equals("-")
                    && Integer.parseInt(p.getDateTaken().substring(5, 7)) <= 12
                    && Integer.parseInt(p.getDateTaken().substring(5, 7)) > 0
                    && Integer.parseInt(p.getDateTaken().substring(8)) <= 31) {
                if (Integer.parseInt(p.getDateTaken().substring(0, 4)) == year) {
                    returnPhotos.add(p);
                }
            }
        }
        return returnPhotos;
    }

    /*
     * Returns an ArrayList of photos from PhotoLibrary of photos that were taken in the year and the month of given
     * parameter.
     * @param month The month specified to match to be added into the return ArrayList
     * @param year The year specified to match to be added into the return ArrayList
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> returnPhotos = new ArrayList<Photograph>();
        if (month > 12 || month <= 0 || year < 0) {
            return null;
        }
        for (Photograph p : this.photos) {
            if (p.getDateTaken().substring(4, 5).equals("-") && p.getDateTaken().substring(7, 8).equals("-")
                    && Integer.parseInt(p.getDateTaken().substring(5, 7)) <= 12
                    && Integer.parseInt(p.getDateTaken().substring(5, 7)) > 0
                    && Integer.parseInt(p.getDateTaken().substring(8)) <= 31) {
                if (Integer.parseInt(p.getDateTaken().substring(0, 4)) == year
                        && Integer.parseInt(p.getDateTaken().substring(5, 7)) == month) {
                    returnPhotos.add(p);
                }
            }
        }
        return returnPhotos;
    }

    /*
     * Returns an ArrayList of photos from PhotoLibrary of photos that were taken from the given parameters of begin date to
     * end date. If the parameters are formatted incorrectly or begin happens before end, it returns null.
     * @param beginDate The given date as the starting date to filter out pictures
     * @param endDate The other end of the date to filter out pictures.
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photograph> returnPhotos = new ArrayList<Photograph>();
        // creates Calendar of begin and end
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        // creates the format of the calendar created
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // gives begin and end values of the format of calendar.
            begin.setTime(format.parse(beginDate));
            end.setTime(format.parse(endDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (begin.after(end)) {
            return null;
        }

        // If any of the numbers rounded up due to the nature of calendar and the value of Year, Month, or Date is not the
        // same as what it was initially, returns null.
        if (Integer.parseInt(beginDate.substring(0, 4)) != begin.get(Calendar.YEAR)
                || Integer.parseInt(endDate.substring(0, 4)) != end.get(Calendar.YEAR)
                || Integer.parseInt(beginDate.substring(5, 7)) != begin.get(Calendar.MONTH) + 1
                || Integer.parseInt(endDate.substring(5, 7)) != end.get(Calendar.MONTH) + 1
                || Integer.parseInt(beginDate.substring(8)) != begin.get(Calendar.DAY_OF_MONTH)
                || Integer.parseInt(endDate.substring(8)) != end.get(Calendar.DAY_OF_MONTH)) {
            return null;
        }

        // loops through photograph p then sets them into the Calendar format of yyyy-MM-dd
        for (Photograph p : this.photos) {
            Calendar between = Calendar.getInstance();
            try {
                between.setTime(format.parse(p.getDateTaken()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Comparison between begin, between, and after.
            if (begin.before(between) && end.after(between)) {
                returnPhotos.add(p);
            } else if (begin.equals(between) && end.after(between)) {
                returnPhotos.add(p);
            } else if (begin.before(between) && end.equals(between)) {
                returnPhotos.add(p);
            } else if (begin.equals(between) && end.equals(between)) {
                returnPhotos.add(p);
            }
        }
        return returnPhotos;

    }
}
