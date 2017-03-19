package movie;

/**
 * Name:
 * ID:
 * Description: Contains the main method. All it does is construct a MovieManager and calls .run().
 */
public class Lab3 {

    /**
     * 输入的时候用;隔开各个参数：
     * 如：rent a movie需要movie code，renter ID, first name, last name
     * 可以输入 HPDH2;5;Tom;Riddle
     * main function of Lab3
     * @param args none
     */
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();
        manager.run();
    }
}
