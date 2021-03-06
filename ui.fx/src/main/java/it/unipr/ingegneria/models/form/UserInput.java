package it.unipr.ingegneria.models.form;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The {@code UserInput} is a simple class to contains the information of User form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public  class UserInput {
    /**
     * The {@code Field} is a static class that contains the field of user form
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
    public static class Field {
        private String title = "User registration";
        public final Label NAME_LABEL = new Label("Name");
        public  final Label SURNAME_LABEL = new Label("Surname");
        public  final Label EMAIL_LABEL = new Label("Email");
        public  final Label PSW_LABEL = new Label("Password");
        public  final  TextField NAME_INPUT= new TextField();
        public  final   TextField SURNAME_INPUT= new TextField();
        public  final   TextField EMAIL_INPUT=new TextField();
        public  final PasswordField PSW_INPUT=new PasswordField();

        public Field(){};

        public Field setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getTitle() {
            return title;
        }
    }

        private UserInput(){};
    }