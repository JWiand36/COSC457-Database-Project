package services;

import structures.*;

import java.util.ArrayList;

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
            result.add("Paper");
            result.add("Books");
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

        list.add(new Customer());
        list.add(new Customer());

        list.get(0).setFirst_name("Ryan");
        list.get(0).setLast_name("Corn");
        list.get(1).setFirst_name("Lindsy");
        list.get(1).setLast_name("Davis");

        for(Customer customer: list){
            if(data.equals(customer.getFirst_name()))
                return customer;
        }

        return new Customer();
    }

    private DataInterface getReceipt(String data){

        ArrayList<Receipt> list = new ArrayList<>();

        list.add(new Receipt());
        list.add(new Receipt());

        list.get(0).setId(1);
        list.get(0).setCustomer_name("Nick");
        list.get(0).setEmployee_name("Cindy");

        list.get(1).setId(2);
        list.get(1).setCustomer_name("Jessica");
        list.get(1).setEmployee_name("Brad");

        for(Receipt receipt: list){
            if(data.equals(receipt.getId()+""))
                return receipt;
        }

        return new Receipt();
    }

    private DataInterface getProduct(String data){

        ArrayList<Product> list = new ArrayList<>();

        list.add(new Product());
        list.add(new Product());

        list.get(0).setName("Paper");
        list.get(1).setName("Books");

        for(Product product: list){
            if(data.equals(product.getName()))
                return product;
        }

        return new Product();
    }

    private DataInterface getEmployee(String data){

        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee());
        list.add(new Employee());

        list.get(0).setFirst_name("John");
        list.get(0).setLast_name("Doe");

        list.get(1).setFirst_name("Stacy");
        list.get(1).setLast_name("Smith");

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
