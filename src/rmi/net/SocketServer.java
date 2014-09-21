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

public class SocketServer extends Thread {
    private static final Logger LOGGER = Logger.getGlobal();
    private static Map<Integer, SocketServer> serverSockets = new ConcurrentHashMap<Integer, SocketServer>();

    public int port;
    private ServerSocket listener;
    private Map<String, SocketRequestHandler> handlers;

    public synchronized static SocketServer getServer(int port) throws IOException {
        SocketServer server = serverSockets.get(port);

        if (server == null) {
            server = new SocketServer(port);
            serverSockets.put(port, server);
        }

        return server;
    }

    private SocketServer(int port) throws IOException {
        this.port = port;
        this.listener = new ServerSocket(port);
        this.handlers = new ConcurrentHashMap<String, SocketRequestHandler>();
    }

    private class Dispatcher implements Runnable {

        private Socket socket;
        private Map<String, SocketRequestHandler> handlers;

        public Dispatcher(Socket socket,
                Map<String, SocketRequestHandler> handlers) {
            this.socket = socket;
            this.handlers = handlers;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream out = new ObjectOutputStream(
                        this.socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(
                        this.socket.getInputStream());
                Request req = (Request) in.readObject();
                Response resp = null;
                SocketRequestHandler handler = this.handlers
                        .get(req.getToken());
                if (handler != null) {
                    resp = handler.handle(req);
                }
                if (resp == null) {
                    resp = new Response();
                    resp.e = new MissingHandlerException();
                }
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

    public void run() {
        Socket socket = null;
        Executor executor = Executors.newCachedThreadPool();
        while (true) {
            try {
                socket = listener.accept();
                LOGGER.info(socket.toString());
                executor.execute(new Dispatcher(socket, handlers));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void bindHandler(String token, SocketRequestHandler handler) {
        this.handlers.put(token, handler);
    }

    public void removeHandler(String token) {
        this.handlers.remove(token);
    }
}
