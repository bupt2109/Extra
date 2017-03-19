package test;

import movie.MovieManager;
import movie.Movies;
import movie.Renter;
import exception.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Name:
 * ID:
 * Description: A class for Junit4 boundary test case
 */
public class MovieManageBoundaryTest {
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
    public void testSortRenter() throws Exception{
        Movies movie;
        movie = new Movies("code1","name1");
        manager.addMovie(movie);
        Renter renter;
        renter = new Renter(4,"fName1","bb");
        manager.rentMovie("code1",renter);
        renter = new Renter(2,"fName2","bb");
        manager.rentMovie("code1",renter);
        renter = new Renter(3,"fName3","aa");
        manager.rentMovie("code1",renter);

        Renter[] renters = manager.movies[0].getRenters();
        assertEquals(3, renters[0].getID());
        assertEquals(2, renters[1].getID());
        assertEquals(4, renters[2].getID());
    }

}
