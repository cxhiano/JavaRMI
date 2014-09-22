package rmi.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import rmi.message.Request;
import rmi.message.Response;

/**
 * SocketServer
 *
 * A concurrent server. The server maintains a thread pool to run each request
 * by using <code> Executor executor = Executors.newCachedThreadPool(); </code>
 * You can register handler to the server to handle request. Each handler is
 * associated with a token for identification.
 *
 * The server receive Request object and response with Response object. Each
 * Request object has a <code> getToken() </code> method to return a token which
 * identifies its handler.
 *
 *
 * @author Chao Xin, Chao Zhang
 */
public class SocketServer extends Thread {
    private static final Logger LOGGER = Logger.getGlobal();
    private static Map<Integer, SocketServer> serverSockets = new ConcurrentHashMap<Integer, SocketServer>();

    public int port;
    private ServerSocket listener;
    private Map<String, SocketRequestHandler> handlers;

    /**
     * Return a running server on given port.
     *
     * @param port
     * @return A SocketServer running on given port
     */
    public synchronized static SocketServer getServer(int port)
            throws IOException {
        SocketServer server = serverSockets.get(port);

        // The server does not exist yet. Create one.
        if (server == null) {
            server = new SocketServer(port);
            server.start();
            serverSockets.put(port, server);
        }

        return server;
    }

    private SocketServer(int port) throws IOException {
        this.port = port;
        this.listener = new ServerSocket(port);
        this.handlers = new ConcurrentHashMap<String, SocketRequestHandler>();
    }

    /**
     * The server assigns a thread for each request that runs the dispatcher.
     * The dispatcher dispatches requests to corresponding handlers.
     */
    private class Dispatcher implements Runnable {

        private Socket socket;
        private Map<String, SocketRequestHandler> handlers;

        public Dispatcher(Socket socket,
                Map<String, SocketRequestHandler> handlers) {
            this.socket = socket;
            this.handlers = handlers;
        }

        /**
         * Receive request, dispatch to corresponding handler.
         */
        @Override
        public void run() {
            try {
                ObjectOutputStream out = new ObjectOutputStream(
                        this.socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(
                        this.socket.getInputStream());

                Request req = (Request) in.readObject();
                Response resp = null;

                // Fetch handler by the token in Request object.
                SocketRequestHandler handler = this.handlers
                        .get(req.getToken());

                if (handler != null) { // Fetch success
                    resp = handler.handle(req);
                } else { // Cannot find corresponding handler
                    resp.e = new MissingHandlerException();
                }

                // Response the request
                out.writeObject(resp);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (this.socket != null)
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
    }

    /**
     * Receive requests and assign them to a dispatcher on a new thread
     */
    public void run() {
        Socket socket = null;
        Executor executor = Executors.newCachedThreadPool();
        while (true) {
            try {
                socket = listener.accept();
                LOGGER.info(socket.toString());
                // Assign to a dispatcher on a new thread
                executor.execute(new Dispatcher(socket, handlers));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Add a request handler associated with specific token to server
     *
     * @param token
     * @param handler
     */
    public synchronized void bindHandler(String token,
            SocketRequestHandler handler) {
        this.handlers.put(token, handler);
    }

    /**
     * Remove the handler associated with given token
     * 
     * @param token
     */
    public synchronized void removeHandler(String token) {
        this.handlers.remove(token);
    }
}
