package truncatedmodels;

import javax.persistence.*;

@MappedSuperclass
public class TruncatedUser {

    @Id
    @GeneratedValue
    private Long userId;

    private String userName;
    private Integer activeStatus = 0;

    public TruncatedUser() {
    }

    public TruncatedUser(String userName) {
        this.userName = userName;
    }


    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }
}
