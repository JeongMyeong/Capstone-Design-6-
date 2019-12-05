package e.evolc.chatapp;

/**
 * Created by evolc on 2019-11-26.
 */

public class Chat {

    public int user;
    public String text;

    public Chat(int user, String text){
        this.user = user;
        this.text = text;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
