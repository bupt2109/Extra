package test;
import  org.junit.runner.RunWith;
import  org.junit.runners.Suite;


/**
 * Name:
 * ID:
 * Description: A class for Junit4 test suite
 */
@RunWith(Suite. class )
@Suite.SuiteClasses( {
        MovieManageNormalTest.class,
        MovieManageErrorTest.class,
        MovieManageBoundaryTest.class
} )
public class MovieManagerAllTests {

}
