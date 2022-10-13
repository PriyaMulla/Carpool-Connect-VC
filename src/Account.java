public class Account {
    
    //fields
    public String name = "";
    public String username = "";
    public String email = "";
    private String password = "";
    
    //constructor
    private Account (String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
