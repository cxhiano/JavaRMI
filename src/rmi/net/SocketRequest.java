package rmi.net;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rmi.message.Request;
import rmi.message.Response;

/**
 * Contains shorthand for sending request to geiven port on given host
 *
 * @author Chao Xin, Chao Zhang
 */
public class SocketRequest {
    /**
     * Send request to port:host
     *
     * @param host
     * @param port
     * @param request
     * @return response object
     */
    public static Response request(String host, int port, Request request) {
        Socket sock = null;

        try {
            sock = new Socket(host, port);

            //Setup input/output stream
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

            out.writeObject(request);
            out.flush();

            //get response object
            Response response = (Response) in.readObject();

            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sock != null)   //close request on exit
                    sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
