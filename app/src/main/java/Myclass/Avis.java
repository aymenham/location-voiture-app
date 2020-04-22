package Myclass;

/**
 * Created by LENOVO on 22/03/2018.
 */

public class Avis {

    private String username ;

    private String comment ;

    private String date ;

    public Avis(String username, String comment, String date) {
        this.username = username;
        this.comment = comment;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
