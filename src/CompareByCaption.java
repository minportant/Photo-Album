/*
 * Homework 5 Min Woo Kim, mk4ed 
 * Sources: Big Java Book, Java Website
 */
import java.util.Comparator;

/*
 * Compares two photographs, photograph a and photograph b by Caption.
 * When the captions are equal, 
 *      Returns -1 when the rating of photograph a is greater.
 *      Returns 1 when the rating of photograph b is greater
 * When the captions are not equal, 
 *      Returns -1 when the photograph a's caption is first alphabetically 
 *      Returns 1 when the photograph b's caption is first alphabetically 
 * When the captions and the ratings equal,
 *      Returns 0.
 */
public class CompareByCaption implements Comparator<Photograph> {
    public int compare(Photograph a, Photograph b) {
        if (a.getCaption().compareTo(b.getCaption()) == 0) {
            if (a.getRating() > b.getRating()) {
                return -1;
            } else if (a.getRating() < b.getRating()) {
                return 1;
            }
        } else if (a.getCaption().compareTo(b.getCaption()) < 0) {
            return -1;
        } else {
            return 1;
        }
        return 0;
    }
}
