package test;

import static org.junit.Assert.*;

import movie.MovieManager;
import movie.Movies;
import movie.Renter;
import org.junit.Before;
import org.junit.Test;

/**
 * Name:
 * ID:
 * Description: A class for Junit4 normal test case
 */
public class MovieManageNormalTest {
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

    @Test
    public void testAddMovie() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        movie = new Movies("code2","name1");
        manager.addMovie(movie);
        movie = new Movies("code3","name1");
        manager.addMovie(movie);
        assertEquals(3, manager.curMovieNumber);
    }

    @Test
    public void testRemoveMovie() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        movie = new Movies("code2","name1");
        manager.addMovie(movie);
        movie = new Movies("code3","name1");
        manager.addMovie(movie);
        manager.discontinueMovie("code2");
        manager.discontinueMovie("code3");
        assertEquals(1, manager.curMovieNumber);
    }

    @Test
    public void testAddRenter() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter renter;
        renter = new Renter(1,"fName1","lName1");
        manager.rentMovie("code1",renter);
        renter = new Renter(2,"fName2","lName2");
        manager.rentMovie("code1",renter);
        renter = new Renter(3,"fName3","lName3");
        manager.rentMovie("code1",renter);
        assertEquals(7, manager.movies[0].getCount());
    }

    @Test
    public void testRemoveRenter() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter renter;
        renter = new Renter(1,"fName1","lName1");
        manager.rentMovie("code1",renter);
        renter = new Renter(2,"fName2","lName2");
        manager.rentMovie("code1",renter);
        renter = new Renter(3,"fName3","lName3");
        manager.rentMovie("code1",renter);
        manager.returnRental(1,"code1");
        manager.returnRental(2,"code1");
        assertEquals(9, manager.movies[0].getCount());
    }

}
