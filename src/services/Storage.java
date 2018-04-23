package services;

import structures.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import static services.Display.*;

public class Storage {

    private DataInterface item;
    private int store_id = 1;
    private String store_location = "Towson";
    private String employeeName;
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public Storage(){
        setUpDatabase();
    }

    boolean isManager(){
        try {
            resultSet = statement.executeQuery("select concat_ws(' ', s.fname, s.lname) " +
                    "from employee as e, employee as s " +
                    "where s.ssn = e.super_ssn " +
                    "and concat_ws(' ', s.fname, s.lname) = \""+employeeName+"\"");

            return resultSet.next();

        }catch (SQLException ex){ex.printStackTrace();}

        return false;
    }

    boolean isEmployee(String user, String pass) {

        try {
            resultSet = statement.executeQuery("select username, password, ssn from account");

            while(resultSet.next()) {
                if((user.equals(resultSet.getString(1)) && pass.equals(resultSet.getString(2)))) {

                    resultSet = statement.executeQuery("select concat_ws(' ', fname, lname) " +
                            "from employee " +
                            "where employee.ssn = \"" +resultSet.getInt(3)+"\";");

                    resultSet.next();
                    employeeName = resultSet.getString(1);

                    return true;
                }
            }
        }catch (SQLException ex){ex.printStackTrace();}

        return false;
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

    void setStore(String store){
        this.store_location = store;

        try{
            resultSet = statement.executeQuery("select store_id from store where location = '"+store_location+"';");
            resultSet.next();
            store_id = resultSet.getInt(1);
        }catch (SQLException ex){ ex.printStackTrace(); }
    }

    private void setUpDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/project?autoReconnect=true&useSSL=false",
                    "project", "mypassword");

            statement = connection.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void closeDatabase(){
        try{
            connection.close();
        }catch (SQLException ex){ex.printStackTrace();}
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

            resultSet = statement.executeQuery("select c_id, fname, lname, sex, address, balance from customer " +
                    "where concat_ws(' ',fname, lname) = \"" + data +"\" ");

            resultSet.next();
            int id = resultSet.getInt(1);
            String fname = resultSet.getString(2);
            String lname = resultSet.getString(3);
            String sex = resultSet.getString(4);
            String address = resultSet.getString(5);
            String balance = resultSet.getString(6);

            customer = new Customer(id, fname, lname, address, sex, balance);
        }catch (SQLException ex){
            ex.printStackTrace();
            customer = new Customer();
        }

        return customer;
    }

    private DataInterface getReceipt(String data){

        Receipt receipt;
        ArrayList<Product> products = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("select s.s_id, s.total_cost, s.date, " +
                    "store.location " +
                    "from sale as s, " +
                    "store inner join sold_at on sold_at.store_id = store.store_id " +
                    "where sold_at.s_id = s.s_id " +
                    "and s.s_id = \"" + data + "\";");

            resultSet.next();
            int id = resultSet.getInt(1);
            double cost = resultSet.getDouble(2);
            String date = resultSet.getString(3);
            String store = resultSet.getString(4);


            String customerName = "";


            resultSet = statement.executeQuery("select concat_ws(' ', customer.fname,customer.lname) " +
                    "from sale, customer inner join makes_sale on makes_sale.c_id = customer.c_id " +
                    "where makes_sale.s_id = sale.s_id " +
                    "and sale.s_id = \""+data+"\";");

            if(resultSet.next())
                customerName = resultSet.getString(1);


            resultSet = statement.executeQuery("select products.p_id, products.name, products.description, products.amt_left, products.price " +
                    "from items_sold as p inner join products on p.P_id = products.P_id, sale " +
                    "where sale.s_id = p.s_id " +
                    "and sale.s_id = \""+data+"\";");

            while(resultSet.next()){
                int p_id = resultSet.getInt(1);
                String p_name = resultSet.getString(2);
                String p_descrip = resultSet.getString(3);
                int amt_left = resultSet.getInt(4);
                double price = resultSet.getDouble(5);
                products.add(new Product(p_id, p_name, p_descrip, amt_left, price));
            }

            receipt = new Receipt(id, customerName, store, cost, products, date);

        }catch (SQLException ex ){
            ex.printStackTrace();
            receipt = new Receipt();
        }

        return receipt;
    }

    private DataInterface getProduct(String data){

        Product product;

        try {
            resultSet = statement.executeQuery("select p_id, name, description, amt_left, price from products " +
                    "where name = \"" + data + "\"");

            resultSet.next();
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            int amt = resultSet.getInt(4);
            double price = resultSet.getDouble(5);

            product = new Product(id, name, description, amt, price);

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
            int age = resultSet.getInt(5);
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

        try{
            resultSet = statement.executeQuery("select c_id from customer where concat_ws(' ', fname, lname) = " +
                    "'"+customer.getFirst_name()+" "+customer.getLast_name()+"'");

            if(resultSet.next()){
                int id = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("update customer " +
                        "set fname = ?, lname = ?, sex = ?, address = ?, balance = ? " +
                        "where c_id = " + id);

                preparedStatement.setString(1,customer.getFirst_name());
                preparedStatement.setString(2,customer.getLast_name());
                preparedStatement.setString(3,customer.getSex().charAt(0)+"");
                preparedStatement.setString(4,customer.getAddress());
                preparedStatement.setString(5,customer.getBalance()+"");
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select c_id from shopAt where c_id = " + id);
                if(resultSet.next()){

                    preparedStatement.executeUpdate("update shopat set store_id = "+store_id+" where c_id = "+id);

                }else {
                    preparedStatement = connection.prepareStatement("insert into shopat(c_id, store_id) " +
                            "values (?," + store_id + ")");
                    preparedStatement.setString(1, id+"");
                    preparedStatement.executeUpdate();
                }
            }else{

                preparedStatement = connection.prepareStatement("insert into customer(fname, " +
                        "lname, sex, address, balance) values (?,?,?,?, 0.0)");
                preparedStatement.setString(1,customer.getFirst_name());
                preparedStatement.setString(2,customer.getLast_name());
                preparedStatement.setString(3,customer.getSex().charAt(0)+"");
                preparedStatement.setString(4,customer.getAddress());
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select c_id from customer where concat_ws(' ', fname, lname) = " +
                        "'"+customer.getFirst_name()+" "+customer.getLast_name()+"'");
                resultSet.next();

                preparedStatement = connection.prepareStatement("insert into shopat(c_id, store_id) " +
                        "values (?,"+store_id+")");
                preparedStatement.setString(1,resultSet.getString(1));
                preparedStatement.executeUpdate();
            }

        }catch (SQLException ex){ ex.printStackTrace(); }
    }

    private void addSale(Receipt receipt){

//    public Receipt(String customer_name, String store_location, ArrayList<Product> products, String date) {

    }

    private void addProduct(Product product){

//    public Product(String name, String description, int amt_left, double price){

        try {
            resultSet = statement.executeQuery("select p_id from products where name = '"+product.getName()+"';");

            if(resultSet.next()){
                int id = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("update products " +
                        "set name = ?, description = ?, amt_left = ?, price = ? " +
                        "where name = '" + product.getName() +"'");

                preparedStatement.setString(1,product.getName());
                preparedStatement.setString(2,product.getDescription());
                preparedStatement.setInt(3,product.getAmt_left());
                preparedStatement.setDouble(4,product.getPrice());
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select p_id from has where p_id = " + id+" and store_id = "+store_id);
                if(!resultSet.next()){
                    preparedStatement = connection.prepareStatement("insert into has (store_id, p_id) values("+store_id+","+
                            id+");");
                    preparedStatement.executeUpdate();
                }
            }else{
                preparedStatement = connection.prepareStatement("insert into products(name, " +
                        "description, amt_left, price) values (?,?,?,?)");
                preparedStatement.setString(1,product.getName());
                preparedStatement.setString(2,product.getDescription());
                preparedStatement.setInt(3,product.getAmt_left());
                preparedStatement.setDouble(4,product.getPrice());
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select p_id from products where name = '"+product.getName()+"';");
                resultSet.next();

                preparedStatement = connection.prepareStatement("insert into has (store_id, p_id) values("+store_id+","+
                        resultSet.getInt(1)+");");
                preparedStatement.executeUpdate();
            }
        }catch (SQLException ex){ex.printStackTrace();}
    }

    private void addEmployee(Employee employee){

        try{
            resultSet = statement.executeQuery("select concat_ws(' ', fname, lname) from employee where concat_ws(' ', fname, lname) = " +
                    "'"+employee.getFirst_name()+" "+employee.getLast_name()+"'");

            if(resultSet.next()){
                String name = resultSet.getString(1);

                resultSet = statement.executeQuery("select ssn from employee where concat_ws(' ', fname, lname) = " +
                        "'"+employee.getSuper_Name()+"'");

                preparedStatement = connection.prepareStatement("update employee " +
                        "set fname = ?, lname = ?, sex = ?, address = ?, age = ?, super_ssn = ?, works_at = ? " +
                        "where concat_ws(' ', fname, lname) = '" + name+"'");

                preparedStatement.setString(1,employee.getFirst_name());
                preparedStatement.setString(2,employee.getLast_name());
                preparedStatement.setString(3,employee.getSex().charAt(0)+"");
                preparedStatement.setString(4,employee.getAddress());
                preparedStatement.setString(5,employee.getAge()+"");

                if(resultSet.next())
                    preparedStatement.setInt(6,resultSet.getInt(1));
                else
                    preparedStatement.setString(6,null);

                preparedStatement.setInt(7,store_id);
                preparedStatement.executeUpdate();

            }else{
                resultSet = statement.executeQuery("select ssn from employee where concat_ws(' ', fname, lname) = " +
                        "'"+employee.getSuper_Name()+"'");

                preparedStatement = connection.prepareStatement("insert into employee(fname, " +
                        "lname, sex, address, age, super_ssn, works_at, ssn) values (?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1,employee.getFirst_name());
                preparedStatement.setString(2,employee.getLast_name());
                preparedStatement.setString(3,employee.getSex().charAt(0)+"");
                preparedStatement.setString(4,employee.getAddress());
                preparedStatement.setInt(5,employee.getAge());

                if(resultSet.next())
                    preparedStatement.setInt(6,resultSet.getInt(1));
                else
                    preparedStatement.setString(6,null);

                preparedStatement.setInt(7,store_id);
                preparedStatement.setInt(8,employee.getSsn());
                preparedStatement.executeUpdate();
            }

        }catch (SQLException ex){ ex.printStackTrace(); }

    }

    void addAmt(int amount){
        Product temp = (Product) item;
        temp.setAmt_left(temp.getAmt_left() + amount);
    }

    void subAmt(int amount){
        Product temp = (Product) item;
        temp.setAmt_left(temp.getAmt_left() - amount);
    }
}
