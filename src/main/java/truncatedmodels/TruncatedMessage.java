package truncatedmodels;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class TruncatedMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long messageId;
    private Date timeStamp = new Date();
    private String messageBody;

    public TruncatedMessage() {

    }
    public TruncatedMessage(String messageBody) {
        this.messageBody = messageBody;
    }


    public Long getMessageId() {
        return messageId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
