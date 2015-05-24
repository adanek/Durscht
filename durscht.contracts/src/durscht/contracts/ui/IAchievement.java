package durscht.contracts.ui;

public interface IAchievement {

    /**
     * Gets the name of the achievement
     * @return the name of the achievement
     */
    String getName();

    /**
     * Gets the progress of the achievement
     * The progress is a positive integer between 0 and 100
     * and represents the current progress in percent
     * 100:= Achievement complete
     * @return the current progress of the achievement
     */
    int getProgress();

    /**
     * Gets the URL of the associated image
     * @return the URL of the image
     */
    String getImageUrl();
}
