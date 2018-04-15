package services;

import structures.*;

import java.util.ArrayList;
import java.util.Date;

import static services.Display.*;

public class Storage {

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
        }

        return result;
    }

    DataInterface getOne(String data, int location){

        if(location == CUSTOMERS){
            return getCustomer(data);
        }else if(location == SALES){
            return getReceipt(data);
        }else if(location == INVENTORY){
            return getProduct(data);
        }else if(location == EMPLOYEE){
            return getEmployee(data);
        }else
            return null;
    }

    boolean isManager(){
        return true;
    }

    boolean isEmployee() { return true; }

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

        Product[] products1 = {p1,p3,p5};
        Product[] products2 = {p2,p4,p1};

        list.add(new Receipt("Nick", "Cindy", "Frederick", products1, new Date()));
        list.add(new Receipt("Jessica", "Brad", "Towson", products2, new Date()));

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

    }

    private void addSale(Receipt sale){

    }

    private void addProduct(Product product){

    }

    private void addEmployee(Employee employee){

    }
}
