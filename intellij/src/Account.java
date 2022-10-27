public class Account {
    
    //fields
    private String name = "";
    private String username = "";
    private String email = "";
    private String password = "";
    
    //constructor
    public Account(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + "'s Account " +
                "\n Username: " + username +
                "\n Email: " + email +
                "\n Password: " + password;
    }
}
