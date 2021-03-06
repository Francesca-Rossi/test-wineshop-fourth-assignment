package it.unipr.ingegneria;

import it.unipr.ingegneria.controllers.LoginController;
import it.unipr.ingegneria.controllers.users.AdminController;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.controllers.NotifyWineController;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.utils.Type;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.List;
import java.util.Map;
/**
 * This is the main class of the JavaFx client
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @version 1.0
 * @since 1.0
 */
public class HelloFX extends Application {
    private static final String ADDRESS = "230.0.0.1";
    private static final int DPORT = 4446;
    private static final int SIZE = 1024;
    private ClientSocket clientSocket;

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        InetAddress inetA = InetAddress.getByName(ADDRESS);
                        InetSocketAddress group = new InetSocketAddress(inetA, DPORT);
                        NetworkInterface netI = NetworkInterface.getByInetAddress(inetA);
                        MulticastSocket s = new MulticastSocket(DPORT);

                        s.joinGroup(group, netI);

                        byte[] buf = new byte[SIZE];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);

                        s.receive(packet);

                        Object o = toObject(packet.getData());
                        if ((o != null) && (o instanceof Map)) {
                            // Nome Vino,  Quantità Disponibile nel magazzino
                            Map<String, Long> results = (Map<String, Long>) o;
                            NotifyWineController wineAvailable=new NotifyWineController();
                            wineAvailable.setWineAvaible(results);

                        }
                    } catch (IOException | ClassNotFoundException e) {
                        // e.printStackTrace();
                    }
                }
            });
            t.setDaemon(true);
            t.start();

            this.clientSocket = new ClientSocket();
            UserSearchCriteria userSearchCriteriaByType = new UserSearchCriteria().setUserType(Type.ADMIN);
            List<User> listAdmin = clientSocket.searchUsers(userSearchCriteriaByType);
            if (listAdmin.isEmpty()) {
                AdminController admin = new AdminController(clientSocket);
                admin.getForm();
            } else {
                LoginController login = new LoginController(clientSocket);
                login.getForm();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private Object toObject(final byte[] b)
            throws IOException, ClassNotFoundException {
        ObjectInputStream s = new ObjectInputStream(new ByteArrayInputStream(b));

        Object o = s.readObject();
        s.close();

        return o;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void stop()
    {
        this.clientSocket.closeSocket();
    }
}

