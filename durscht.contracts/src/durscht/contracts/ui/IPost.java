package durscht.contracts.ui;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a posting of a user
 */
public interface IPost {

    /**
     * Gets the id of the post
     * @return the id of the post
     */
    int getId();

    /**
     * Gets the name of the user
     * @return the name of the user
     */
    String getUsername();

    /**
     * Gets the creation date of the post
     * @return the creation date of the post
     */
    Date getDate();

    /**
     * Gets the name (brand - type) of the beer
     * @return the name of the beer
     */
    String getBeer();

    /**
     * Gets the rating of the beer from the post
     * @return the rating of the beer
     */
    int getRating();

    /**
     * Gets the price of the beer from the post
     * @return the price of the beer
     */
    double getPrice();

    /**
     * Gets the description of the post
     * @return the description of the post
     */
	String getDescription();
}
