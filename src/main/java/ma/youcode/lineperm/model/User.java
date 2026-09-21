package ma.youcode.lineperm.model;

public class User {
    private final Long id;
    private final String login;
    private final String passwordHash;
    
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
