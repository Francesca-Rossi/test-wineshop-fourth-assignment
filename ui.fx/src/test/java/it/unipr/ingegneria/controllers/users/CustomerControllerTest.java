package it.unipr.ingegneria.controllers.users;

import it.unipr.ingegneria.ClientSocket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import  org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {
    private static final  String NAME="pippo";
    private static final  String SURNAME="pippo";
    private static final  String EMAIL="pippo@gmail.com";
    private static final  String PASSWORD="oppip";
    private CustomerController employee;

    ClientSocket clientTest=mock(ClientSocket.class);
    @BeforeEach
    void setUp() {
        this.employee=new CustomerController(clientTest);
    }


    @Test
    void shouldRegister() {
        employee.register(NAME, SURNAME, EMAIL, PASSWORD);
    }
}