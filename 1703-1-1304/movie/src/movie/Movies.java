package movie;
import exception.*;

import java.util.Arrays;

/**
 * Name:
 * ID:
 * Description: Class that represents a movie
 */
public class Movies {
    //A String representing the movie code.
    private String code;
    //A String representing the movie name.
    private String name;
    //An int representing the count of rented copies of the movie.
    private int count;
    //An array of Renters renting the movie.
    private Renter[] renters;

    /**
     * Adds a renter to the movie. The renters array order will need to be maintained.
     * @param renter renter information
     * @throws RenterLimitException ex
     * @throws DuplicateRenterException ex
     * @throws InvalidRenterIDException ex
     */
    public void rentMovie(Renter renter) throws RenterLimitException, DuplicateRenterException, InvalidRenterIDException{

        if(count == 0){
            throw new RenterLimitException();
        }
        if(renter.getID() < 0){
            throw new InvalidRenterIDException();
        }
        for(int i=0;i<10-count;i++){
            if(renter.getID()==renters[i].getID()){
                throw new DuplicateRenterException();
            }
        }
        //add and sort
        String lName = renter.getlName();
        int index=0;
        for(int i=0;i<10-count;i++,index++){
            int ct = lName.compareTo(renters[i].getlName());
            if(ct<0){
                break;
            }else if(ct==0){
                if(renter.getID()<renters[i].getID()){
                    break;
                }else{
                    index++;
                    break;
                }
            }
        }
        for(int i=10-count;i>index;i--){
            renters[i] = renters[i-1];
        }
        renters[index] = renter;
        count--;
    }

    /**
     * Removes a renter from the movie based on their renters ID.
     * @param renterId renter information
     * @throws EmptyRenterListException ex
     * @throws RenterNotFoundException ex
     * @throws InvalidRenterIDException ex
     */
    public void returnRental(int renterId) throws EmptyRenterListException, RenterNotFoundException, InvalidRenterIDException{
        if(count == 10){
            throw new EmptyRenterListException();
        }
        if(renterId < 0){
            throw new InvalidRenterIDException();
        }
        int index=0;
        for(int i=0;i<10-count;i++,index++){
            if(renterId==renters[i].getID()){
                break;
            }
        }
        if(index==10-count){
            throw new RenterNotFoundException();
        }
        //remove from renter list
        for (int i = index; i < 9-count; i++) {
            renters[i] = renters[i+1];
        }
        renters[9-count] = null;
        count++;
    }

    public Movies(String code, String name) {
        this.code = code;
        this.name = name;
        this.count = 10;
        this.renters = new Renter[10];
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Renter[] getRenters() {
        return renters;
    }

    public void setRenters(Renter[] renters) {
        this.renters = renters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movies{" +"code='" + code + '\'' +", name='" + name + '\'' +", copies=" + count + "}\n");
        for (int i = 0; i < 10-count; i++) {
            sb.append('\t'+renters[i].toString()+'\n');
        }
        if(count==10){
            sb.append("\tno renters\n");
        }
        return sb.toString();
    }
}
