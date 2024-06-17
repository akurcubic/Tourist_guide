package rs.raf.turisticki_vodic_be.entities;


public class User {

    private int id;
    private String email;
    private String name;
    private String surname;
    private String type;
    private String status;
    private String password;

    public User(int id, String email, String name, String surname, String type,String status,String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.password = password;
        this.status = status;

    }

    public User(){


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
