package rs.raf.turisticki_vodic_be.entities;

public class Activity {

    private int id;
    private String name;

    public Activity(){


    }

    public Activity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Activity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
