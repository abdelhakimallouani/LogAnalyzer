package ma.youcode.lineperm.model;

public class User {
    private Long id;
    private String login;
    private String passwordHash;
    
    public User(String login, String passwordHash){
        this.login=login;
        this.passwordHash=passwordHash;
    }
    public User(Long id, String login,String passwordHash){
        this.id=id;
        this.login=login;
        this.passwordHash=passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
