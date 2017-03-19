package movie;

/**
 * Name:
 * ID:
 * Description: Class representing a renter
 */
public class Renter {
    //An int representing the renter ID.
    private int ID;
    //A String representing the renter’s first name.
    private String fName;
    //A String representing the renter’s last name.
    private String lName;

    public Renter(int ID, String fName, String lName) {
        this.ID = ID;
        this.fName = fName;
        this.lName = lName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "ID=" + ID +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}
