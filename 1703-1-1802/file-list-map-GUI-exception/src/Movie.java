/**
 * this class aims to declare a movie with movie name. movie director, movie score.
 */
public class Movie {
    private String line;
    private String movieName;
    private String director;
    private int score;

    public Movie(String line) {
        this.line = line;
        String[] args = line.split(";",-1);
        movieName = args[0];
        director = args[1];
        score = Integer.valueOf(args[2]);
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return  line;
    }
}
