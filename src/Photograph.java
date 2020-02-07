import java.io.File;
/*
 * Homework 5 Min Woo Kim, mk4ed 
 * Sources: Big Java Book, Java Website
 */
public class Photograph implements Comparable<Photograph> {
    
    /*
     * Holds the caption of photograph.
     */
    private String caption;

    /*
     * Holds the filename of photograph.
     */
    private String filename;

    /*
     * Holds the date of photo taken of photograph.
     */
    private String dateTaken;

    /*
     * Holds the rating of the photograph.
     */
    private int rating;

    private File imageFile;


    /*
     * Constructor to define filename, caption, dateTaken, and rating.
     */
    
    public Photograph(String filename, String caption, String dateTaken, int rating) {
        this.filename = filename;
        this.caption = caption;
        this.dateTaken = dateTaken;
        this.rating = rating;
        this.imageFile = new File(filename);
    }

    /*
     * Constructor to define caption and filename of photograph.
     */
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        this.imageFile = new File(filename);
    }

    /*
     * Gets the rating of the photo.
     */
    public int getRating() {
        return rating;
    }

    /*
     * Sets the rating of photograph if and only if the rating is from 0 to 5.
     * @param rating The set rating of photograph
     */
    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    /*
     * Gets the date taken of photo.
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /*
     * Gets caption of photograph.
     */
    public String getCaption() {
        return caption;
    }

    /*
     * Sets Caption of photograph.
     * @param caption The set caption of photograph.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /*
     * Gets filename of photograph.
     */
    public String getFilename() {
        return filename;
    }

    /*
     * Sets filename of photograph.
     * @param filename The set filename of photograph.
     */
    public void setFilename(String filename) {
        this.filename = filename;
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
        Photograph otherPhoto = (Photograph) o;
        return this.caption.equals(otherPhoto.caption) && this.filename.equals(otherPhoto.filename);
    }

    /*
     * Returns string representation of the object.
     */
    public String toString() {
        return "Caption: " + this.caption + "     Filename: " + this.filename;
    }

    @Override
    public int hashCode() {
        return (this.caption + "---" + this.filename).hashCode();
    }

    /*
     * Compares the object's dateTaken and p's dateTaken. 
     * Returns -1 if the object's dateTaken is before p's dateTaken.
     * Returns 1 if the object's dateTaken is after p's dateTaken.
     * If they are equal, it compares object's caption with p's caption.
     *     returns -1 when the object's caption is first alphabetically.
     *     returns 1 when the Photograph p's caption is first alphabetically.
     * Returns 0 when the dateTaken and the caption names are the same.
     */
    public int compareTo(Photograph p) {
        if (this.dateTaken.compareTo(p.dateTaken) == 0) {
            if (this.getCaption().compareTo(p.getCaption()) < 0) {
                return -1;
            } else if (this.getCaption().compareTo(p.getCaption()) > 0) {
                return 1;
            }
        } else if (this.dateTaken.compareTo(p.dateTaken) > 0) {
            return 1;
        } else {
            return -1;
        }
        return 0;
    }

    public File getFile() {
        return imageFile;
    }

    public void setFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
