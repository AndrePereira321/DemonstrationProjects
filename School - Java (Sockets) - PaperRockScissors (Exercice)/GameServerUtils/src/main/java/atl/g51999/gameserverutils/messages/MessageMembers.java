package atl.g51999.gameserverutils.messages;

import atl.g51999.gameserverutils.users.User;
import atl.g51999.gameserverutils.users.Members;


/**
 * The <code> Message </code> represents a message with the list of all
 * connected users.
 */
public class MessageMembers extends Message {

    /**
     * Constructs message with the list of all connected users.
     *
     * @param members list of all connected users.
     */
    public MessageMembers(Members members) {
        super(Type.MEMBERS, User.EVERYBODY, members);
    }

    /**
     * Retrieves the Members structure of the message.
     *
     * @return The members structure of the message.
     */
    public Members getMembers() {
        return (Members) this.getContent();
    }

}
