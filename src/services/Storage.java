package services;

import structures.*;

import java.util.ArrayList;

import static services.Display.*;

public class Storage {


    ArrayList<String> getData(int n){

        ArrayList<String> result = new ArrayList<>();

        if(n == CUSTOMERS){
            result.add("customer 1");
            result.add("customer 2");
        }else if(n == SALES){

            result.add("1");
            result.add("2");

        }else if(n == INVENTORY){
            result.add("Product 1");
            result.add("Product 2");
        }else if(n == EMPLOYEE){
            result.add("Employee 1");
            result.add("Employee 2");
        }

        return result;
    }

    boolean isManager(){
        return true;
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

    private DataInterface getCustomer(String data){

        ArrayList<Customer> list = new ArrayList<>();

        list.add(new Customer());
        list.add(new Customer());

        list.get(0).setFirst_name("Customer 1");
        list.get(1).setFirst_name("Customer 2");

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
        list.get(0).setCustomer_name("Customer 1");
        list.get(0).setEmployee_name("Employee 1");

        list.get(1).setId(2);
        list.get(1).setCustomer_name("Customer 2");
        list.get(1).setEmployee_name("Employee 2");

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

        list.get(0).setName("Product 1");
        list.get(1).setName("Product 2");

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

        list.get(0).setFirst_name("Customer 1");
        list.get(1).setFirst_name("Customer 2");

        for(Employee employee: list){
            if(data.equals(employee.getFirst_name()))
                return employee;
        }

        return new Employee();
    }
}
