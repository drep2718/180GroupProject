import java.util.*;

public class ThreadSafe extends Thread {
    private static final Object MAIN_LOCK = new Object();
    private static List<Boolean> locked;
    private Server[] servers;
    private Client[][] clients;

    static {
        for (int i = 0; i < 10; i++ ) {
            locked.add(false);
        }
    }

    public ThreadSafe() {

        servers = new Server[10];
        clients = new Client[10][10];

        for (int i = 0; i < servers.length; i++) {
            servers[i] = new Server();
            for (int j = 0; j < clients[i].length; j++) {
                clients[i][j] = new Client(servers[i]);
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < servers.length; i++) {
            synchronized (MAIN_LOCK) {
                if (locked.get(i)) {
                    continue;
                } else {
                    locked.set(i, true);
                }
            }
            for (Client client : clients[i]) {
                client.start();
            }

            for (Client client : clients[i]) {
                try {
                    client.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (MAIN_LOCK) {
                locked.set(i, false);
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafe threadSafe = new ThreadSafe();
        threadSafe.start();
    }
}

