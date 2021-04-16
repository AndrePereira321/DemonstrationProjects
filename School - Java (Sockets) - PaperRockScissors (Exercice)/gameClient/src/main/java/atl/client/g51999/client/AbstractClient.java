// code issu de sources disponibles depuis ici :
// https://www.site.uottawa.ca/school/research/lloseng/supportMaterial/ocsf/ocsf.html
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com
package atl.client.g51999.client;

import atl.client.g51999.model.Observable;
import atl.g51999.gameserverutils.messages.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * The <code> AbstractClient </code> contains all the methods necessary to set
 * up the client side of a client-server architecture. When a client is thus
 * connected to the server, the two programs can then exchange <code> Object
 * </code> instances.<p>
 *
 * Method <code> handleMessageFromServer </code> must be defined by a concrete
 * subclass. Several other hook methods may also be overriden.<p>
 *
 * Several public service methods are provided to application that use this
 * framework.<p>
 *
 * Project Name: OCSF (Object Client-Server Framework)
 * <p>
 *
 * @author Dr. Robert Lagani&egrave;re
 * @author Dr. Timothy C. Lethbridge
 * @author Fran&ccedil;ois B&eacutel;langer
 * @author Paul Holden
 * @version December 2003 (2.31)
 */
public abstract class AbstractClient extends Observable implements Runnable {

    /**
     * The server's host name.
     */
    private String host;

    /**
     * The port number.
     */
    private int port;

    /**
     * The thread created to read data from the server.
     */
    private Thread connectionReader;

    /**
     * Sockets are used in the operating system as channels of communication
     * between two processes.
     *
     * @see java.net.Socket
     */
    private Socket clientSocket;
    /**
     * The stream to handle data going to the server.
     */
    private ObjectOutputStream output;
    /**
     * The stream to handle data from the server.
     */
    private ObjectInputStream input;

    /**
     * Indicates if the thread is ready to stop. Needed so that the loop in the
     * run method knows when to stop waiting for incoming messages.
     */
    private boolean readyToStop = false;

    /**
     * Constructs the client.
     *
     * @param host the server's host name.
     * @param port the port number.
     */
    public AbstractClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @return the host name.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the server host for the next connection. The change in host only
     * takes effect at the time of the next call to openConnection().
     *
     * @param host the host name.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port number.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the server port number for the next connection. The change in port
     * only takes effect at the time of the next call to openConnection().
     *
     * @param port the port number.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Closes all aspects of the connection to the server.
     *
     * @exception IOException if an I/O error occurs when closing.
     */
    private void closeAll() throws IOException {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
        } finally {
            output = null;
            input = null;
            clientSocket = null;
        }
    }

    /**
     * Opens the connection with the server. If the connection is already
     * opened, this call has no effect.
     *
     * @exception IOException if an I/O error occurs when opening.
     */
    final public void openConnection() throws IOException {
        if (isConnected()) {
            return;
        }
        try {
            this.clientSocket = new Socket(host, port);
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            try {
                closeAll();
            } catch (Exception exc) {
            }
            throw ex;
        }

        connectionReader = new Thread(this);
        readyToStop = false;
        connectionReader.start();
    }

    /**
     * returns the client's description.
     *
     * @return the client's Inet address.
     */
    final public InetAddress getInetAddress() {
        return clientSocket.getInetAddress();
    }

    /**
     * Closes the connection to the server.
     *
     * @exception IOException if an I/O error occurs when closing.
     */
    final public void closeConnection() throws IOException {
        readyToStop = true;
        closeAll();
    }

    /**
     * Sends an object to the server. This is the only way that methods should
     * communicate with the server. This method can be overriden, but if so it
     * should still perform the general function of sending to server, by
     * calling the <code>super.sendToServer()</code> method perhaps after some
     * kind of filtering is done.
     *
     * @param msg The message to be sent.
     * @exception IOException if an I/O error occurs when sending
     */
    public void sendToServer(Message msg) throws IOException {
        if (clientSocket == null || output == null) {
            throw new SocketException("socket does not exist");
        }
        output.reset();
        output.writeObject(msg);
    }

    /**
     * Hook method called after a connection has been established. The default
     * implementation does nothing. It may be overridden by subclasses to do
     * anything they wish.
     */
    protected abstract void connectionEstablished();

    /**
     * Hook method called each time an exception is thrown by the client's
     * thread that is reading messages from the server. The method may be
     * overridden by subclasses. Most exceptions will cause the end of the
     * reading thread except for <code>ClassNotFoundException</code>s received
     * when an object of unknown class is received and for the
     * <code>RuntimeException</code>s that can be thrown by the message handling
     * method implemented by the user.
     *
     * @param exception the exception raised.
     */
    protected abstract void connectionException(Exception exception);

    /**
     * Hook method called after the connection has been closed. The default
     * implementation does nothing. The method may be overriden by subclasses to
     * perform special processing such as cleaning up and terminating, or
     * attempting to reconnect.
     */
    protected abstract void connectionClosed();

    /**
     * Handles a message sent from the server to this client. This MUST be
     * implemented by subclasses, who should respond to messages.
     *
     * @param msg the message sent.
     */
    protected abstract void handleMessageFromServer(Object msg);

    /**
     * Waits for messages from the server. When each arrives, a call is made to
     * <code>handleMessageFromServer()</code>. Not to be explicitly called.
     */
    @Override
    final public void run() {
        connectionEstablished();

        Object msg;
        try {
            while (!readyToStop) {
                try {
                    msg = input.readObject();
                    if (!readyToStop) {
                        handleMessageFromServer(msg);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    connectionException(ex);
                }
            }
        } catch (Exception ex) {
            if (!readyToStop) {
                try {
                    closeAll();
                } catch (IOException ex1) {
                }
                connectionReader = null;
                connectionException(ex);
            }
        } finally {
            connectionReader = null;
            connectionClosed();
        }
    }

    /**
     * @return true if the client is connnected.
     */
    public boolean isConnected() {
        return connectionReader != null && connectionReader.isAlive();
    }

}
