package services;

import structures.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import static services.Display.*;

public class Storage {

    private DataInterface item;
    private String store_location = "Towson";
    private Statement statement;
    private ResultSet resultSet;

    public Storage(){
        setUpDatabase();
    }

    boolean isManager(){
        return true;
    }

    boolean isEmployee(String user, String pass) {

        System.out.println(user+"\n"+pass);

        return (user.equals("John") && pass.equals("12345"));
    }

    ArrayList<String> getStore(){ return getStores(); }

    String getOneStore(){ return this.store_location; }

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

    void setStore(String store){ this.store_location = store; }

    private void setUpDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/project?autoReconnect=true&useSSL=false",
                    "project", "mypassword");

            statement = connection.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    ArrayList<String> getData(int n){

        ArrayList<String> result = new ArrayList<>();

        try {
            if (n == CUSTOMERS) {
                resultSet = statement.executeQuery("select concat_ws(' ',customer.fname, customer.lname) as name "
                        +"from customer " +
                        "where customer.c_id in " +
                            "(select c_id " +
                            "from shopat, store " +
                            "where shopat.store_id = store.store_id " +
                            "and store.location = \""+ store_location +"\");");
            } else if (n == SALES) {
                resultSet = statement.executeQuery("select S_id from sale " +
                        "where sale.s_id in " +
                            "(select s_id " +
                            "from sold_at, store " +
                            "where sold_at.store_id = store.store_id " +
                            "and store.location = \""+ store_location +"\");");
            } else if (n == INVENTORY) {
                resultSet = statement.executeQuery("select Name from products " +
                        "where products.p_id in " +
                            "(select p_id " +
                            "from has, store " +
                            "where has.store_id = store.store_id " +
                            "and store.location = \""+ store_location +"\");");
            } else if (n == EMPLOYEE) {
                resultSet = statement.executeQuery("select concat_ws(' ',employee.fname, employee.lname) as name " +
                        "from employee, store " +
                        "where employee.works_at = store.store_id " +
                        "and store.location = \""+ store_location +"\";");
            } else if (n == ADD_SALE) {
                resultSet = statement.executeQuery("select Name from products " +
                        "where products.p_id in " +
                        "(select p_id " +
                        "from has, store " +
                        "where has.store_id = store.store_id " +
                        "and store.location = \""+ store_location +"\");");
            }

            int i = 1;

            while(resultSet.next()){
                result.add(resultSet.getString(i));
            }
        }catch (SQLException ex ){
            ex.printStackTrace();
        }

        return result;
    }

    ArrayList<String> getData(String data, int n) {

        ArrayList<String> result = new ArrayList<>();

        try{
         if (n == VENDOR) {
            resultSet = statement.executeQuery("select vendor.Name "+
                    "from vendor, supplies "+
                    "where vendor.v_id = supplies.v_id "+
                    "AND supplies.p_id in "+
                    "(select p_id "+
                    "from products "+
                    "where products.name = \""+data+"\");");
        } else if (n == WARHOUSE) {
            resultSet = statement.executeQuery("select warehouse.location "+
                    "from warehouse, contains "+
                    "where warehouse.w_id = contains.w_id "+
                    "AND contains.p_id in "+
                    "(select p_id "+
                    "from products "+
                    "where products.name = \""+data+"\");");
        }

        int i = 1;

        while(resultSet.next()){
            result.add(resultSet.getString(i));
        }

        }catch (SQLException ex ){
            ex.printStackTrace();
        }

        return result;
    }

        private ArrayList<String> getStores(){
        ArrayList<String> result = new ArrayList<>();

        try{
            resultSet = statement.executeQuery("select location from store");

            int i = 1;
            while(resultSet.next()){
                result.add(resultSet.getString(i));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    private DataInterface getCustomer(String data){

        Customer customer;

        try{

            resultSet = statement.executeQuery("select fname, lname, sex, address, balance from customer where " +
                    "concat_ws(' ',fname, lname) = \"" + data +"\" ");

            resultSet.next();
            String fname = resultSet.getString(1);
            String lname = resultSet.getString(2);
            String sex = resultSet.getString(3);
            String address = resultSet.getString(4);
            String balance = resultSet.getString(5);

            customer = new Customer(fname, lname, address, sex, balance);
        }catch (SQLException ex){
            ex.printStackTrace();
            customer = new Customer();
        }

        return customer;
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

        list.add(new Receipt("Nick", "Frederick", products1, date.toString()));
        list.add(new Receipt("Jessica","Towson", products2, date.toString()));

        list.get(0).setId(1);
        list.get(1).setId(2);

        for(Receipt receipt: list){
            if(data.equals(receipt.getId()+""))
                return receipt;
        }

        return new Receipt();
    }

    private DataInterface getProduct(String data){

        Product product;

        try {
            resultSet = statement.executeQuery("select name, description, amt_left, price from products where name = \"" + data + "\"");

            resultSet.next();
            String name = resultSet.getString(1);
            String description = resultSet.getString(2);
            int amt = Integer.parseInt(resultSet.getString(3));
            double price = Double.parseDouble(resultSet.getString(4));

            product = new Product(name, description, amt, price);

        }catch (SQLException e){
            e.printStackTrace();
            product = new Product();
        }

        return product;
    }

    private DataInterface getEmployee(String data){


        Employee employee;

        try{

            resultSet = statement.executeQuery("select e.fname, e.lname, e.sex, e.address, e.age, t.location, " +
                        "(select concat_ws(' ',fname, lname) " +
                        "from employee " +
                        "where e.super_ssn = ssn) " +
                    "from employee as e, store as t " +
                    "where concat_ws(' ',e.fname, e.lname) = \"" + data +"\" " +
                    "and e.works_at = t.store_id");

            resultSet.next();
            String fname = resultSet.getString(1);
            String lname = resultSet.getString(2);
            String sex = resultSet.getString(3);
            String address = resultSet.getString(4);
            int age = Integer.parseInt(resultSet.getString(5));
            String superName = resultSet.getString(7);
            String store = resultSet.getString(6);

            employee = new Employee(superName, fname, lname, address, sex, age, store);
        }catch (SQLException ex){
            ex.printStackTrace();
            employee = new Employee();
        }


        return employee;
    }

    private void addCustomer(Customer customer){
        System.out.println("Added Customer: " + customer.getFirst_name());

    }

    private void addSale(Receipt receipt){
        System.out.println("Added Sale: " + receipt.getCustomer_name() + ":" + ":" + receipt.getTotal_balance());

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
