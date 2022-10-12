public class Account {
    
    //fields
    public String name = "";
    public String username = "";
    public String email = "";
    private final String password = "";
    
    //constructor
    private Account (String username, String password, String name, String email){
        username = this.username;
        password = this.password;
        name = this.name;
        email = this.email;
    }
}
