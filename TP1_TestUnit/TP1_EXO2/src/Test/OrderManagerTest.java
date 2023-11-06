package Test;

import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import com.example.service.IPayementManager;
import com.example.service.IStockManager;
import com.example.service.OrderManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OrderManagerTest {
    static DatabaseConnection db;
    static Connection connect;
    //Initialiser la base de donnees ( se connecter a une base en memoire)
    @BeforeAll
    static void setup(){
        db = new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        connect = db.connect();
        db.createDb(connect);
    }
    //Tester le cas ou la quantite demande depasse la quantite disponible dans le stock
    @Test
    void createOrder1() {
        //Definir les MOCKs

        IStockManager stock = Mockito.mock(IStockManager.class);
        IPayementManager payement = Mockito.mock(IPayementManager.class);

        //Definir ordermanager et set les MOCKs.

        OrderManager gest_command = new OrderManager();
        gest_command.setiPayementManager(payement);
        gest_command.setiStockManager(stock);

        //Definir la commande

        Customer client = new Customer();
        client.setCustomerID("client1");

        Order order = new Order();
        order.setCustomer(client);
        order.setOrderDate("jeudi");
        order.setOrderNum(1);

        Product produit = new Product();
        produit.setProductID("produit1");

        Orderline ligne = new Orderline();
        ligne.setOrderedQte(7); //On veut demander une quantite = 7
        ligne.setProduct(produit);

        List<Orderline> listproduit = new ArrayList<>();
        listproduit.add(ligne);

        order.setOrderlines(listproduit); //On ajoute la liste des produits qui contient 7 exemplaires du produit1

        when(stock.getProductQte(ligne.getProduct())).thenReturn(3); //On suppose qu'on a 3 exemplaires du produit dans stock

        //On verifie si la methode retourne False

        assertFalse(gest_command.createOrder(order));
    }
    @Test
    void createOrder2() {
        //Definir les MOCKs

        IStockManager stock = Mockito.mock(IStockManager.class);
        IPayementManager payement = Mockito.mock(IPayementManager.class);
        OrderDao command = Mockito.mock(OrderDao.class);

        //Definir Ordermanager et set les MOCKs
        OrderManager gest_command = new OrderManager();
        gest_command.setiPayementManager(payement);
        gest_command.setiStockManager(stock);
        gest_command.setOrderDao(command);

        //Definir la commande

        Customer client = new Customer();
        client.setCustomerID("client1");

        Product produit = new Product();
        produit.setProductID("produit1");

        Orderline ligne = new Orderline();
        ligne.setOrderedQte(1); //On veut commander un seul produit
        ligne.setProduct(produit);

        when(stock.getProductQte(produit)).thenReturn(2); //Supposer qu'on a 2 exemplaires du produit1

        List<Orderline> listproduit = new ArrayList<>();
        listproduit.add(ligne);

        Order order = new Order();
        order.setCustomer(client);
        order.setOrderDate("jeudi");
        order.setOrderNum(1);
        order.setOrderlines(listproduit);

        //Definir le resultat
        boolean result = gest_command.createOrder(order);

        //Verifier si la commande est inseree (supposition) et si le produit est retire du stock.
        verify(command,times(1)).insertOrder(order);
        verify(stock,times(1)).removeProductStock(produit,1);

        //Test de la fonction: Retourne VRAI
        assertTrue(result);

    }
    @Test
    void cancelOrder1() {
        //Definir les MOCKs
        IPayementManager payment = Mockito.mock(IPayementManager.class);

        //Quand on verifier si la commande est paye, on suppose que OUI, commande est payee.
        when(payment.isPaid(2)).thenReturn(Boolean.TRUE);

        //Definir OrderManager sans oublier le set.
        OrderManager gest_command = new OrderManager();
        gest_command.setiPayementManager(payment);

        //Verifier que la methode retourne FALSE.
        assertFalse(gest_command.cancelOrder(2));
    }
    @Test
    void cancelOrder2() {
        //Definir les MOCKs
        IPayementManager payment = Mockito.mock(IPayementManager.class);
        IStockManager stock = Mockito.mock(IStockManager.class);
        OrderDao command = Mockito.mock(OrderDao.class);

        //Definir OrderManager (ne pas oublier les sets)
        OrderManager gest_command = new OrderManager();
        gest_command.setiStockManager(stock);
        gest_command.setiPayementManager(payment);
        gest_command.setOrderDao(command);

        //On suppose ici que la commande n'est pas payee.
        when(payment.isPaid(2)).thenReturn(Boolean.FALSE);

        //On definit le produit a annuler sa commande.


        Product produit = new Product();
        produit.setProductID("produit1");

        Orderline ligne = new Orderline();
        ligne.setOrderedQte(2);
        ligne.setProduct(produit);

        List<Orderline> listproduit = new ArrayList<>();
        listproduit.add(ligne);

        //On suppose que quand getOrderDetails est appelee, on retourne liste contenant "produit1"
        when(command.getOrderDetails(2)).thenReturn(listproduit);

        //Definir resultat.
        boolean result = gest_command.cancelOrder(2);

        //Verifier que le porduit a ete recupere dans le stock.
        verify(stock,times(1)).addProductStock(produit,2);

        //Verifier que la methode retourne TRUE.
        assertTrue(result);

    }
}