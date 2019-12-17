package truncatedmodels;

import com.groupfour.chatapp.chatapp.models.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TruncatedChat {
    @Id
    @GeneratedValue
    private Long chatId;
    private String chatName;
    private Date timeStamp = new Date();

    @ManyToOne User admin;
    @ManyToMany Set<User> users = new HashSet<>();

    public TruncatedChat() {
    }

    public TruncatedChat(String chatName) {
        this.chatName = chatName;
    }

    public void addUserToChat(User user) {
        users.add(user);
    }

    public void addUsersToChat(Set<User> listOfUsers) {
        users.addAll(listOfUsers);
    }

    public Long getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<User> getUsers() {
        return users;
    }
}
