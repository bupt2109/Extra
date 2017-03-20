import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this class aims to do logic jobs.
 * read movie file information.
 * store movies into list.
 * find each director with their highest score movie.
 * store highest score movies into map, key is director, value is movie.
 * write highest score movies to file.
 */
public class MovieParse {
    private String fileIn;
    private String fileOut;
    private Map<String, Movie> movieMap;
    private List<Movie> movieList;

    /**
     *  read movie file information.
     *  might throw IO exception and invalid movie exception
     *  store movies into list.
     */
    public void movieRead() {
        try {
            File file = new File(fileIn);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                try{
                    if (validMovie(line)) {
                        movieList.add(new Movie(line));
                    }
                }catch(InvalidMovieException ex){
                    System.out.println("Invalid Movie: "+line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * find each director with their highest score movie.
     * store highest score movies into map, key is director, value is movie.
     */
    public void findBestMovie(){
        for (Movie myMovie: movieList) {
            String director = myMovie.getDirector();
            if(!movieMap.containsKey(director)){
                movieMap.put(director,myMovie);
            }else{
                if(myMovie.getScore()>movieMap.get(director).getScore()){
                    movieMap.put(director,myMovie);
                }
            }
        }
    }

    /**
     * write highest score movies to file.
     * might throw IO exception
     */
    public void movieWrite() {
        try{
            File file = new File(fileOut);
            BufferedWriter writer= new BufferedWriter(new FileWriter(file));

            for (Movie myMovie : movieMap.values()) {
                System.out.println(myMovie);
                writer.write(myMovie.toString()+'\n');
            }
            writer.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * constructor with default in and out path.
     */
    public MovieParse() {
        this("text.txt","out.txt");
    }

    /**
     * constructor with specific in and out path.
     * @param fileIn in path
     * @param fileOut out path
     */
    public MovieParse(String fileIn, String fileOut) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
        movieList = new ArrayList<Movie>();
        movieMap = new HashMap<String, Movie>();
    }

    /**
     *  change in and out path
     * @param in in path
     * @param out out path
     */
    public void setAttribute(String in, String out){
        this.fileIn = in;
        this.fileOut = out;
    }

    /**
     * whether the input line can be convert to a movie class
     * might throw the exception
     * @param movie input line
     * @return true if input line is a valid movie format
     * @throws InvalidMovieException
     */
    private boolean validMovie(String movie) throws InvalidMovieException {
        if (movie == null || movie.length() > 100) {
            throw new InvalidMovieException();
        }
        String[] strings = movie.split(";",-1);
        if(strings.length!=3 || !isNumeric(strings[2])){
            throw new InvalidMovieException();
        }
        return true;
    }

    /**
     * whether the string can be convert to a positive integer
     * @param str string
     * @return true if the string is a positive integer
     */
    private boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
