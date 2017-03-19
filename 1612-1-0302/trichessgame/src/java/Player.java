/**
 * Created by
 */

public class Player {

    private String name;
    private int xh; //先下还是后下 1先下，2后下

    public Player(String name, int xh) {
        this.name = name;
        this.xh = xh;
    }

    public Player(int xh) {
        if(xh==1) {
            this.name = "A";
            this.xh = 1;
        }
        else {
            this.name = "B";
            this.xh = 2;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }
}
