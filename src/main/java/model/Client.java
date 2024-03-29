package model;

public class Client {

    private String id;
    private String fullName;
    private String greeting;

    public Client() {
    }

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Client{");
        sb.append("id='").append(id).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", greeting='").append(greeting).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
