package Main;

import DAO.DiscountCodeDAO;
import DAO.ProductCodeDAO;
import Models.DiscountCode;
import Models.ProductCode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class TrabalhoTecnicasDB {

    private static Scanner in;
    private static final DiscountCodeDAO discountDAO = new DiscountCodeDAO();
    private static final ProductCodeDAO productDAO = new ProductCodeDAO();

    public static void main(String[] args) {
        showMenu();
        readOption();

        /*System.out.println("DISCOUNT 'M': " + discountDAO.buscarPorID("M").getRate());

        List<ProductCode> listaProducts = productDAO.buscarTodos();
        System.out.println("PRODUCTS");
        listaProducts.stream().forEach((item) -> {
            System.out.println("Description: " + item.getDescription() + " Item: " + item.getProdCode() + " Discount: " + item.getDiscountCode());
        });

        System.out.println("PRODUCT 'BK': " + productDAO.buscarPorID("BK").getDescription());

        ProductCode novoCode = new ProductCode("FF", 'F', "Fabio");
        if (productDAO.insert(novoCode)) {
            System.out.println("Inserido: Code - " + novoCode.getDiscountCode() + " DiscountCode - "
                    + novoCode.getDiscountCode() + " Description: " + novoCode.getDescription());
        }*/
    }

    private static void showMenu() {
        System.out.println("==============================================");
        System.out.println("|   MENU                                     |");
        System.out.println("==============================================");
        System.out.println("| Opções :                                   |");
        System.out.println("|        1. Listar descontos                 |");
        System.out.println("|        2. Inserir desconto                 |");
        System.out.println("|        3. Consultar produdos por desconto  |");
        System.out.println("|        4. Exit                             |");
        System.out.println("==============================================");
    }

    private static void readOption() {        
        requestEntry("Escolha uma opção.");
        
        in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1:
                listDiscounts();
                showMenu();
                readOption();
                break;
            case 2:
                insertDiscount();
                showMenu();
                readOption();
                break;
            case 3:
                listByDiscount();
                showMenu();
                readOption();
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    private static void listDiscounts() {
        List<DiscountCode> listaDescontos = discountDAO.buscarTodos();
        System.out.println("DISCOUNTS");
        listaDescontos.stream().forEach((item) -> {
            System.out.println("**** Item: " + item.getDiscountCode() + " Rate: " + item.getRate());
        });
        System.out.println("");
    }

    private static void insertDiscount() {
        String code = null;
        BigDecimal rate = null;
        
        requestEntry("Digite o código:");        
        Scanner inCode = new Scanner(System.in);
        if (inCode.hasNext()) {
            code = inCode.next();
        }
        
        requestEntry("Digite a taxa:");        
        Scanner inRate = new Scanner(System.in);
        if (inCode.hasNextBigDecimal()) {
            rate = inRate.nextBigDecimal();
        }
        
        DiscountCode novo = new DiscountCode(code, rate);
        if (discountDAO.insert(novo)) {
            System.out.println("**** Inserido: Code - " + novo.getDiscountCode() + " Rate - " + novo.getRate());
        }
        System.out.println("");
    }

    private static void listByDiscount() {
        String code = null;
        requestEntry("Digite o código:");
        
        Scanner inCode = new Scanner(System.in);
        if (inCode.hasNext()) {
            code = inCode.next();
        }
        
        List<ProductCode> listaProductsWithDiscount = productDAO.buscarPorDesconto(code.toUpperCase());
        listaProductsWithDiscount.stream().forEach((item) -> {
            System.out.println("**** PRODUCT POR DISCOUNT CODE: " + item.getDescription());
        });
    }
    
    private static void requestEntry(String msg){
        System.out.println("");
        System.out.println(msg);
        System.out.println("");
    }
}
