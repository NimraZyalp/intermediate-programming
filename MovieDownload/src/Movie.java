class Movie {

    // Fields
    String movieName;
    String movieNameForUrl;
    String movieScreenshotFilename;

    Movie(String name) {
        movieName = name;
        movieNameForUrl = getMovieNameForURL();
        movieScreenshotFilename = getMovieFilename();
    }

    /* Makes the movie name URL-safe by replacing spaces with plus signs. */
    String getMovieNameForURL() {
        return movieName.replace(" ", "+");
    } 

    /* Adds a ".jpg" extension to the movie name and replaces spaces with underscores. */
    String getMovieFilename() {
        return movieName.replace(" ", "_") + ".jpg";
    } 
}
