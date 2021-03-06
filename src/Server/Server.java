package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool
    static Properties prop;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) throws IOException {
        Configurations conf = Configurations.getInstance();
        this.prop = conf.getProperties();
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(prop.getProperty("threadPoolSize")));
    }

    public void start(){
        new Thread(() -> {
            runServer();
        }).start();
    }

    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    //Insert the new task into the thread pool:
                    threadPool.submit(() -> {
                        ServerStrategy(clientSocket);
                    });


                } catch (SocketTimeoutException e){
                    e.printStackTrace();
                }
            }
            serverSocket.close();
            threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ServerStrategy(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }
}
