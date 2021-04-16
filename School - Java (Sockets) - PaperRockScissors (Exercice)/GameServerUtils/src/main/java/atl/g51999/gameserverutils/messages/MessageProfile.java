package atl.g51999.gameserverutils.messages;

import atl.g51999.gameserverutils.users.User;

/**
 * The <code> Message </code> represents a message with the profile of a
 * specific user.
 */
public class MessageProfile extends Message {

    /**
     * Constructs message with the profile of a specific user. The author of the
     * message give his profile to be send.
     *
     * @param id userID of the author.
     * @param name user name of the author.
     */
    public MessageProfile(int id, String name) {
        this(new User(id, name));
    }

    /**
     * Constructs message with the profile of a specific user. The author of the
     * message give his profile to be send.
     *
     * @param user The user's profile.
     */
    public MessageProfile(User user) {
        super(Type.PROFILE, user, user);
    }

}
