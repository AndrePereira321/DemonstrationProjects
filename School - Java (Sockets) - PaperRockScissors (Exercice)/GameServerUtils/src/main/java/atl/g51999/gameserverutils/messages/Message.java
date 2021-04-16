package atl.g51999.gameserverutils.messages;

import atl.g51999.gameserverutils.users.User;
import java.io.Serializable;

/**
 *
 * @author andre
 */
public abstract class Message implements Serializable {

    private final Type type;
    private final User user;
    private final Object content;

    public Message(Type type, User user, Object content) {
        this.type = type;
        this.user = user;
        this.content = content;
    }

    /**
     * Return the message type.
     *
     * @return the message type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Return the message author.
     *
     * @return the message author.
     */
    public User getUser() {
        return user;
    }

    /**
     * Return the message content.
     *
     * @return the message content.
     */
    public Object getContent() {
        return content;
    }

}
