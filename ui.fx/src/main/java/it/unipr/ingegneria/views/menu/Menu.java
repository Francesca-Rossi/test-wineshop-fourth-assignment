package it.unipr.ingegneria.views.menu;


import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.models.menu.MenuItems;
import it.unipr.ingegneria.request.UserLogoutRequest;
import it.unipr.ingegneria.controllers.users.UserController;
import it.unipr.ingegneria.utils.ModelRequestType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The {@code Menu} is the father class of the all menu classes
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Menu {
    private ClientSocket clientSocket;
    private Stage menuStage;

    public Menu(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setMenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    public VBox getMenu() {
        return null;
    }

    public HBox item(MenuItems items){
        return null;
    }

    public void logout() {
        this.clientSocket.closeSocket();
    }
    public void goToProfile(UserController user, User userAuthenticate) {
        user.getProfile(userAuthenticate);
        this.setMenuStage(user.getStage());
    }

    public void closeStage(UserController controller){
        if ((this.menuStage==null)&&(controller!=null)) {
            controller.getStage().close();
        }
        else {
            this.menuStage.close();
        }
    }
}

