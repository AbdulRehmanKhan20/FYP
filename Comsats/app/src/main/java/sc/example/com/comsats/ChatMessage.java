package sc.example.com.comsats;

public class ChatMessage {
    public boolean left;
    public String userName;
    public String message;

    public ChatMessage(boolean left, String message,String userName) {
        super();
        this.left = left;
        this.message = message;
        this.userName = userName;
    }
}