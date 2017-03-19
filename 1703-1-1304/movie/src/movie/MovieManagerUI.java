package movie;

import java.util.Scanner;

/**
 * Name:
 * ID:
 * Description: Class representing the I/O between the program and user.
 * o There really isn't a "state" to keep when using this object, so all of its methods can be static.
 */
public class MovieManagerUI {

    //0:choose 1:addMovie 2:discontinueMovie 3:returnRental 4:printInventory 5:quit
    public static int state;

    private static Scanner sc = new Scanner(System.in);

    private static String menu =
                    "Welcome to Class Roster Manager!\n" +
                    "Select an action based on the following menu:\n" +
                    "----------\n" +
                    "am: Add Movie\n" +
                    "dm: Discontinue Movie\n" +
                    "rm: Rent Movie\n" +
                    "rr: Return Rental\n" +
                    "p: Print Movie Inventory\n" +
                    "q: Quit Program\n" +
                    "----------\n" +
                    "Enter Command:";

    /**
     * Prints the menu options to the console.
     */
    public static void printMenu() {
        System.out.println(menu);
    }

    /**
     * Gets the command from the user. Loops until the user enters a valid
     * command and returns the valid command.
     * @return valid command
     */
    public static String getCommand() {

        String command = sc.nextLine();
        while(true) {
            if (command == null || command.length() == 0) {
                System.out.print("Enter Command:");
                command = sc.nextLine();
                continue;
            }
            command = command.trim();
            if(command.equals("am") ||
                    command.equals("dm") ||
                    command.equals("rm") ||
                    command.equals("rr") ||
                    command.equals("p")  ||
                    command.equals("q")){
                break;
            }
            System.out.print("Enter Command:");
            command = sc.nextLine();
        }
        if(command.equals("am")){
            state = 1;
            command = movieInfo();
        }else if(command.equals("dm")){
            state = 2;
            command = movieCode();
        }else if(command.equals("rm")){
            state = 3;
            command = movieCoderenterInfo();
        }else if(command.equals("rr")){
            state = 4;
            command = movieCoderenterID();
        }else if(command.equals("p")){
            state = 5;
            command = null;
        }else{//if(command.equals("q"))
            state = -1;
            command = null;
        }
        return command;
    }



    /**
     * Getting the movie information when adding a new movie.
     * code name
     * @return movie code + movie name
     */
    private static String movieInfo(){
        String str;
        while(true){
            System.out.print("Add movie, please input movie code and movie name, split by \";\"");
            str = sc.nextLine();
            if (str == null || str.length() == 0) {
                continue;
            }
            if(!str.contains(";")){
                continue;
            }
            str = str.trim();
            if(str.split(";",-1).length == 2) {
                str = (str.split(";",-1)[0].trim()+";"+str.split(";",-1)[1].trim());
                break;
            }
        }
        return str;
    }

    /**
     * Getting the movie code when adding a renter to a movie or trying to remove a movie.
     * code
     * @return movie code
     */
    private static String movieCode(){
        System.out.print("Remove movie, please input movie code");
        String str = sc.nextLine();
        return str.trim();
    }


    /**
     * Getting the movie code when adding a renter to a movie or trying to remove a movie.
     * code + renter info
     * @return movie code + renter info
     */
    private static String movieCoderenterInfo() {
        String str;
        while(true){
            System.out.print("Add a renter, please input movie code and renter ID, renter first name, renter last name, split by \";\"");
            str = sc.nextLine();
            if (str == null || str.length() == 0) {
                continue;
            }
            if(!str.contains(";")){
                continue;
            }
            str = str.trim();
            if(str.split(";",-1).length == 4) {
                String[] strings = str.split(";",-1);
                String ID = renterID(strings[1]);
                if(ID==null)
                    continue;
                str = (strings[0].trim()+";"+strings[1].trim()+";"+strings[2].trim()+";"+strings[3].trim() );
                break;
            }
        }
        return str;
    }

    /**
     * Getting the movie code and renter ID when return a renter.
     * code + ID
     * @return movie code + renter ID
     */
    private static String movieCoderenterID() {
        String str;
        while(true){
            System.out.print("Return a renter, please input movie code and renter ID, split by \";\"");
            str = sc.nextLine();
            if (str == null || str.length() == 0) {
                continue;
            }
            if(!str.contains(";")){
                continue;
            }
            str = str.trim();
            if(str.split(";",-1).length == 2) {
                String[] strings = str.split(";",-1);
                String ID = renterID(strings[1]);
                if(ID==null)
                    continue;
                str = (strings[0].trim()+";"+strings[1].trim());
                break;
            }
        }
        return str;
    }


    /**
     * Getting the renter ID when trying to remove a renter from a movie.
     * @return ID
     */
    private static String renterID(String str){
        if(str==null || str.length()==0)
            return null;
        str = str.trim();
        for(int i=1;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch>'9' || ch<'0')
                return null;
        }
        return str;
    }
}
