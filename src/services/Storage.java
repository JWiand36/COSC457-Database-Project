package services;

import structures.*;

import java.util.ArrayList;
import java.util.Date;

import static services.Display.*;

public class Storage {

    private DataInterface item;
    private String employee_name = "John";
    private String store_location = "Towson";

    boolean isManager(){
        return true;
    }

    boolean isEmployee(String user, String pass) {

        System.out.println(user+"\n"+pass);

        if(user.equals("John") && pass.equals("12345")){
            employee_name = user;
            return true;
        }else
            return false;
    }

    String getEmployee(){ return employee_name;}

    String getStore(){ return store_location; }


    DataInterface getOne(String data, int location){

        if(location == CUSTOMERS){
            return item = getCustomer(data);
        }else if(location == SALES){
            return item = getReceipt(data);
        }else if(location == INVENTORY){
            return item = getProduct(data);
        }else if(location == EMPLOYEE){
            return item = getEmployee(data);
        }else if(location == ADD_SALE){
            return item = getProduct(data);
        }else
            return null;
    }

    void addData(DataInterface data, int current_pane){

        if(current_pane == CUSTOMERS){
            addCustomer((Customer) data);
        }else if(current_pane == SALES){
            addSale((Receipt) data);
        }else if(current_pane == INVENTORY){
            addProduct((Product) data);
        }else if(current_pane == EMPLOYEE){
            addEmployee((Employee) data);
        }
    }

    void editData(DataInterface data, int current_pane){

        if(current_pane == CUSTOMERS){
            editCustomer((Customer) data);
        }else if(current_pane == INVENTORY){
            editProduct((Product) data);
        }else if(current_pane == EMPLOYEE){
            editEmployee((Employee) data);
        }
    }


    //All these methods are going to be reworked. All data contained in these methods are going to be removed
    //when an actual database is used. These place holders are in place just to prove the front end works.
    ArrayList<String> getData(int n){

        ArrayList<String> result = new ArrayList<>();

        if(n == CUSTOMERS){
            result.add("Ryan");
            result.add("Lindsy");
        }else if(n == SALES){

            result.add("1");
            result.add("2");

        }else if(n == INVENTORY){
            result.add("Construction Paper");
            result.add("Kill a Mocking Bird");
        }else if(n == EMPLOYEE){
            result.add("John");
            result.add("Stacy");
        }else if(n == ADD_SALE){
            result.add("Construction Paper");
            result.add("Kill a Mocking Bird");
        }

        return result;
    }

    private DataInterface getCustomer(String data){

        ArrayList<Customer> list = new ArrayList<>();

        list.add(new Customer("Ryan", "Corn", "Towson", "Male"));
        list.add(new Customer("Lindsy", "Davis", "Washington DC", "Female"));

        for(Customer customer: list){
            if(data.equals(customer.getFirst_name()))
                return customer;
        }

        return new Customer();
    }

    private DataInterface getReceipt(String data){

        ArrayList<Receipt> list = new ArrayList<>();

        Product p1 = new Product("Product 1", "", 0, 5.0);
        Product p2 = new Product("Product 2", "", 0, 4.0);
        Product p3 = new Product("Product 3", "", 0, 3.0);
        Product p4 = new Product("Product 4", "", 0, 2.0);
        Product p5 = new Product("Product 5", "", 0, 1.0);

        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(p1);
        products1.add(p3);
        products1.add(p5);

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(p1);
        products2.add(p2);
        products2.add(p4);

        Date date = new Date();

        list.add(new Receipt("Nick", "Cindy", "Frederick", products1, date.toString()));
        list.add(new Receipt("Jessica", "Brad", "Towson", products2, date.toString()));

        list.get(0).setId(1);
        list.get(1).setId(2);

        for(Receipt receipt: list){
            if(data.equals(receipt.getId()+""))
                return receipt;
        }

        return new Receipt();
    }

    private DataInterface getProduct(String data){

        ArrayList<Product> list = new ArrayList<>();

        list.add(new Product("Construction Paper", "Paper", 30, 3.50));
        list.add(new Product("Kill a Mocking Bird", "Book", 10, 5.00));

        for(Product product: list){
            if(data.equals(product.getName()))
                return product;
        }

        return new Product();
    }

    private DataInterface getEmployee(String data){

        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee(0, "Stacy Smith", "John", "Doe", "Frederick", "Male", 35, "Frederick"));
        list.add(new Employee(1, "", "Stacy", "Smith", "Walkersville", "Female", 40, "Frederick"));

        for(Employee employee: list){
            if(data.equals(employee.getFirst_name()))
                return employee;
        }

        return new Employee();
    }

    private void addCustomer(Customer customer){
        System.out.println("Added Customer: " + customer.getFirst_name());

    }

    private void addSale(Receipt receipt){
        System.out.println("Added Sale: " + receipt.getCustomer_name() + ":" + receipt.getEmployee_name() + ":" + receipt.getTotal_balance());

    }

    private void addProduct(Product product){
        System.out.println("Added Product: " + product.getName() + ":" + product.getAmt_left());

    }

    private void addEmployee(Employee employee){
        System.out.println("Added Employee: " + employee.getFirst_name());

    }

    private void editCustomer(Customer customer){
        System.out.println("Edited Customer: " + customer.getFirst_name());

    }

    private void editProduct(Product product){
        System.out.println("Edited Product: " + product.getName() + ":" + product.getAmt_left());
    }

    private void editEmployee(Employee employee){
        System.out.println("Edited Employee: " + employee.getFirst_name());

    }

    void addAmt(int amount){
        Product temp = (Product) item;
        temp.setAmt_left(temp.getAmt_left() + amount);
        editProduct(temp);
    }

    void subAmt(int amount){
        Product temp = (Product) item;
        temp.setAmt_left(temp.getAmt_left() - amount);
        editProduct(temp);
    }
}
