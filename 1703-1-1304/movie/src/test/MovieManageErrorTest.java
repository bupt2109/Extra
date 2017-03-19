package test;


import movie.MovieManager;
import movie.Movies;
import movie.Renter;
import exception.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Name:
 * ID:
 * Description: A class for Junit4 error test case
 */
public class MovieManageErrorTest {

    private static MovieManager manager = new MovieManager();

    @Before
    public void setUp(){
        for(int i=0;i<manager.curMovieNumber;i++){
            Movies m = manager.movies[i];
            Renter[] renters = m.getRenters();
            for(int j=0;j< 10-m.getCount();j++){
                renters[j] = null;
            }
            manager.movies[i] = null;
        }
        manager.curMovieNumber = 0;
    }

    @Test(expected  =  DuplicateMovieException. class )
    public void testDuplicateMovie() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
    }

    @Test(expected  =  DuplicateRenterException.class )
    public void testDuplicateRenter() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter r = new Renter(1,"fName"+1,"lName"+1);
        manager.rentMovie("code1",r);
        manager.rentMovie("code1",r);

    }

    @Test(expected  =  EmptyMovieInfoException.class )
    public void testEmptyMovieInfo() throws Exception{
        Movies movie;
        movie = new Movies("code1","");
        manager.addMovie(movie);

    }

    @Test(expected  =  EmptyMovieListException.class )
    public void testEmptyMovieList() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        manager.discontinueMovie("code1");
        manager.discontinueMovie("code2");
    }

    @Test(expected  =  EmptyRenterListException.class )
    public void testEmptyRenterList() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        manager.returnRental(1,"code1");
    }

    @Test(expected  =  EmptyRenterNameException.class )
    public void testEmptyRenterName() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter r = new Renter(1,"fName"+1,"");
        manager.rentMovie("code1",r);
    }

    @Test(expected  =  InvalidRenterIDException.class )
    public void testInvalidRenterID() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter r = new Renter(-1,"fName","lName");
        manager.rentMovie("code1",r);
    }

    @Test(expected  =  MovieLimitException.class )
    public void testMovieLimit() throws Exception{
        Movies movie;
        for(int i=0;i<21;i++) {
            movie = new Movies("code"+i, "name"+i);
            manager.addMovie(movie);
        }
    }

    @Test(expected  =  MovieNotFoundException.class )
    public void testMovieNotFound() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        manager.discontinueMovie("code2");
    }

    @Test(expected  =  RentedMovieException.class )
    public void testRentedMovie() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter r = new Renter(1,"fName1","lName1");
        manager.rentMovie("code1",r);
        manager.discontinueMovie("code1");
    }

    @Test(expected  =  RenterLimitException.class )
    public void testRenterLimit() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter r ;
        for(int i=0;i<11;i++){
            r = new Renter(i,"fName"+i,"lName"+1);
            manager.rentMovie("code1",r);
        }
    }

    @Test(expected  =  RenterNotFoundException.class )
    public void testRenterNotFound() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter r ;
        for(int i=0;i<5;i++){
            r = new Renter(i,"fName"+i,"lName"+1);
            manager.rentMovie("code1",r);
        }
        manager.returnRental(8,"code1");
    }

}
