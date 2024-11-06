import java.util.Scanner;

class App {
    public static void main(String[] args) throws Exception {
        // Initialize MoviePosterWriter instance
        MoviePosterWriter mpw = new MoviePosterWriter();
        
        // Define an array of movies
        String[] movies = { "The Matrix", "Inception", "Interstellar", "Gladiator", "Toy Story" };
        
        // Loop through each movie, set it in the writer, and download its poster
        for (String movieName : movies) {
            Movie movie = new Movie(movieName);
            mpw.setMovieString(movie.getMovieNameForURL());
            mpw.write(movie.getMovieFilename());
            System.out.println("Downloaded poster for: " + movieName + " (" + mpw.movieYear + ")");
        }
        
        // Extra Credit: Ask the user for a custom movie and download its poster
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a movie title to download its poster:");
        String userMovieName = scanner.nextLine();
        Movie userMovie = new Movie(userMovieName);
        mpw.setMovieString(userMovie.getMovieNameForURL());
        mpw.write(userMovie.getMovieFilename());
        System.out.println("Downloaded poster for your movie: " + userMovieName + " (" + mpw.movieYear + ")");
        scanner.close();
    }
}
