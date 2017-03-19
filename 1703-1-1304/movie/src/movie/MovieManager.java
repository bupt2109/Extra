package movie;

import exception.*;

/**
 * Name:
 * ID:
 * Description: A class representing the highest layer of managing the class roster containing
 */
public class MovieManager {
    //An array of Movies
    public Movies[] movies;
    //Total number of current Movies.
    public int curMovieNumber;

    /**
     * Runs the main loop of the program. This displays the menu,
     * accepts user input, and handles each of the user commands.
     * Handles the custom Exceptions and displays the messages to the user.
     */
    public void run() {

        while(true){
            MovieManagerUI.state = 0;
            MovieManagerUI.printMenu();
            String command = MovieManagerUI.getCommand();
            if(MovieManagerUI.state == -1){
                break;
            }
            if(MovieManagerUI.state == 1){
                Movies movie = new Movies(command.split(";",-1)[0],command.split(";",-1)[1]);
                try{
                    addMovie(movie);
                } catch (MovieLimitException e) {
                    System.err.println("Movie Limit Exception!");
                } catch (EmptyMovieInfoException e) {
                    System.err.println("Empty Movie Info Exception!");
                } catch (DuplicateMovieException e) {
                    System.err.println("Duplicate Movie Exception");
                }
            }else if(MovieManagerUI.state == 2){
                try{
                    discontinueMovie(command);
                } catch (EmptyMovieListException e) {
                    System.err.println("Empty Movie List Exception");
                } catch (MovieNotFoundException e) {
                    System.err.println("Movie Not Found Exception");
                } catch (RentedMovieException e) {
                    System.err.println("Rented Movie Exception");
                }
            }else if(MovieManagerUI.state == 3){
                String movieCode = command.split(";",-1)[0];
                int renterID = Integer.parseInt(command.split(";",-1)[1]);
                String firstName = command.split(";",-1)[2];
                String lastName = command.split(";",-1)[3];
                Renter renter = new Renter(renterID,firstName,lastName);
                try{
                    rentMovie(movieCode,renter);
                } catch (EmptyRenterNameException e) {
                    System.err.println("Empty Renter Name Exception");
                } catch (RenterLimitException e) {
                    System.err.println("Renter Limit Exception");
                } catch (DuplicateRenterException e) {
                    System.err.println("Duplicate Renter Exception");
                } catch (MovieNotFoundException e) {
                    System.err.println("Movie Not Found Exception");
                } catch (InvalidRenterIDException e) {
                    System.out.println("Invalid Renter ID Exception");
                }
            }else if(MovieManagerUI.state == 4){
                String movieCode = command.split(";",-1)[0];
                int renterID = Integer.parseInt(command.split(";",-1)[1]);
                try{
                    returnRental(renterID,movieCode);
                } catch (MovieNotFoundException e) {
                    System.err.println("Movie Not Found Exception");
                } catch (RenterNotFoundException e) {
                    System.err.println("Renter Not Found Exception");
                } catch (EmptyRenterListException e) {
                    System.err.println("Empty Renter List Exception");
                } catch (InvalidRenterIDException e) {
                    System.out.println("Invalid Renter ID Exception");
                }
            }else if(MovieManagerUI.state == 5){
                printInventory();
            }else if(MovieManagerUI.state == 6){
                break;
            }
        }

    }

    /**
     * Adds a movie to the array of movies
     * @param m movie to be added
     * @throws MovieLimitException ex
     * @throws EmptyMovieInfoException ex
     * @throws DuplicateMovieException ex
     */
    public void addMovie(Movies m) throws
            MovieLimitException, EmptyMovieInfoException, DuplicateMovieException{

        if(curMovieNumber>=20){
            throw new MovieLimitException();
        }
        if(m.getCode()==null || m.getCode().length()==0 ||
           m.getName()==null || m.getName().length()==0){
            throw new EmptyMovieInfoException();
        }
        for (int i = 0; i < curMovieNumber; i++) {
            if(m.getCode().equals(movies[i].getCode())){
                throw new DuplicateMovieException();
            }
        }
        movies[curMovieNumber] = m;
        curMovieNumber++;

    }

    /**
     * Removes a movie from the array of Movies
     * @param movieCode movie to be deleted
     * @throws MovieNotFoundException ex
     * @throws EmptyMovieListException ex
     * @throws RentedMovieException ex
     */
    public void discontinueMovie(String movieCode) throws
            MovieNotFoundException, EmptyMovieListException, RentedMovieException{

        if(curMovieNumber==0){
            throw new EmptyMovieListException();
        }
        int index = findMovie(movieCode);
        if(index == -1){
            throw new MovieNotFoundException();
        }
        if(movies[index].getCount()!=10){
            throw new RentedMovieException();
        }
        movies[index] = movies[curMovieNumber-1];
        movies[curMovieNumber-1] = null;
        curMovieNumber--;

    }

    /**
     * Adds a renter to an already existing movieCode.
     * @param movieCode movie information
     * @param s renter information
     * @throws RenterLimitException ex
     * @throws DuplicateRenterException ex
     * @throws MovieNotFoundException ex
     * @throws EmptyRenterNameException ex
     * @throws InvalidRenterIDException ex
     */
    public void rentMovie(String movieCode, Renter s) throws
            RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException,InvalidRenterIDException{
        if(s.getfName()==null || s.getfName().length()==0 ||
                s.getlName()==null || s.getlName().length()==0){
            throw new EmptyRenterNameException();
        }
        int index = findMovie(movieCode);
        if(index == -1){
            throw new MovieNotFoundException();
        }
        Movies movie = movies[index];
        movie.rentMovie(s);

    }

    /**
     * Removes a renter from an already existing movieCode.
     * @param renterId renter information
     * @param movieCode movie information
     * @throws RenterNotFoundException ex
     * @throws EmptyRenterListException ex
     * @throws MovieNotFoundException ex
     * @throws InvalidRenterIDException ex
     */
    public void returnRental(int renterId, String movieCode) throws
            RenterNotFoundException,EmptyRenterListException, MovieNotFoundException, InvalidRenterIDException{

        int index = findMovie(movieCode);
        if(index == -1){
            throw new MovieNotFoundException();
        }
        Movies movie = movies[index];
        movie.returnRental(renterId);

    }

    /**
     * Prints the information for all movies and their renters.
     */
    public void printInventory(){
        for (int i = 0; i < curMovieNumber; i++) {
            System.out.println(movies[i]);
        }
        if(curMovieNumber==0)
            System.out.println("null");
    }

    public MovieManager() {
        movies = new Movies[20];
        curMovieNumber = 0;
    }

    /**
     * find movie index in movies
     * @param movieCode movie code
     * @return -1: no found, or i: index
     */
    private int findMovie(String movieCode){
        int i;
        for (i = 0; i < curMovieNumber; i++) {
            if(movies[i].getCode().equals(movieCode)){
                break;
            }
        }
        return (i == curMovieNumber)? -1: i;
    }
}
