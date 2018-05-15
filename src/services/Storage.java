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
            return getCustomer(data);
        }else if(location == SALES){
            return getReceipt(data);
        }else if(location == INVENTORY){
            return getProduct(data);
        }else if(location == EMPLOYEE){
            return getEmployee(data);
        }else if(location == ADD_SALE){
            return getProduct(data);
        }else if(location == EVENT) {
            return getEvent(data);
        }else if(location == PROMOTION){
            return getPromotion(data);
        }else if(location == SHIPMENT){
            return getOrder(data);
        }else if(location == ORDER) {
            return getOrder(data);
        }else if(location == ADDORDER){
            return getProduct(data);
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
        }else if(current_pane == EVENT){
            addEvent((Event) data);
        }else if(current_pane == PROMOTION){
            addPromotion((Promotion) data);
        }else if(current_pane == ADDORDER){
            addOrder((Order) data);
        }else if(current_pane == SHIPMENT){
            editOrder((Order) data);
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
                            "from shop_at, store " +
                            "where shop_at.store_id = store.store_id " +
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
                        "from employee, store, works_at " +
                        "where works_at.store_id = store.store_id " +
                        "and works_at.ssn = employee.ssn " +
                        "and store.location = \""+ store_location +"\";");
            } else if (n == ADD_SALE) {
                resultSet = statement.executeQuery("select Name from products " +
                        "where products.p_id in " +
                        "(select p_id " +
                        "from has, store " +
                        "where has.store_id = store.store_id " +
                        "and store.location = \""+ store_location +"\");");
            } else if (n == EVENT){
                resultSet = statement.executeQuery("select concat_ws(' ',events.event_id, events.name) as name from events " +
                        "where events.event_id in " +
                        "(select event_id " +
                        "from take_place, store " +
                        "where take_place.store_id = store.store_id " +
                        "and store.location = \""+ store_location +"\");");
            } else if (n == PROMOTION){
                resultSet = statement.executeQuery("select concat_ws(' ',promotions.promo_id, products.name) as name " +
                        "from promotions, products, promoted " +
                        "where products.P_id = promoted.P_id " +
                        "and promotions.promo_id = promoted.promo_id;");
            } else if (n == SHIPMENT){
                resultSet = statement.executeQuery("select concat_ws(' ',orders.o_id, shipment.shipping_address) as name " +
                        "from orders, shipment, ship " +
                        "where orders.o_id = ship.o_id " +
                        "and shipment.shipment_id = ship.shipment_id;");
            }else if (n == ORDER){
                resultSet = statement.executeQuery("select concat_ws(' ',orders.o_id, shipment.shipping_address) as name " +
                        "from orders, shipment, ship " +
                        "where orders.o_id = ship.o_id " +
                        "and shipment.shipment_id = ship.shipment_id;");
            } else if (n == ADDORDER) {
                resultSet = statement.executeQuery("select Name from products " +
                        "where products.p_id in " +
                        "(select p_id " +
                        "from has, store " +
                        "where has.store_id = store.store_id " +
                        "and store.location = \""+ store_location +"\");");
            }else if (n == 13){ //Gets the shipping Companies
                resultSet = statement.executeQuery("select Name from shipping_company;");
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

    private Product getProduct(String data){

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
                    "from employee as e, store as t, works_at as w " +
                    "where concat_ws(' ',e.fname, e.lname) = \"" + data +"\" " +
                    "and w.ssn = e.ssn " +
                    "and w.store_id = t.store_id");

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

    private Event getEvent(String data){ //Event_Id Name


        Event event;

        try{

            resultSet = statement.executeQuery("select events.event_id, events.name, events.host_name, events.date " +
                    "from events " +
                    "where concat_ws(' ', events.event_id, events.name) = \"" + data + "\";");

            resultSet.next();
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String host = resultSet.getString(3);
            String date = resultSet.getString(4);

            event = new Event(id, name, date, host);
        }catch (SQLException ex){
            ex.printStackTrace();
            event = new Event();
        }


        return event;

    }

    private Promotion getPromotion(String data){
        Promotion promotion;

        try{

            resultSet = statement.executeQuery("select promotions.promo_id, products.name, promotions.discount, promotions.date " +
                    "from promotions, products, promoted " +
                    "where concat_ws(' ', promotions.promo_id, products.name) = \"" + data + "\" " +
                    "and promotions.promo_id = promoted.promo_id " +
                    "and products.P_id = promoted.P_id;");

            resultSet.next();
            int id = resultSet.getInt(1);
            String item = resultSet.getString(2);
            double discount = resultSet.getDouble(3);
            String date = resultSet.getString(4);

            promotion = new Promotion(id, item, discount,date);
        }catch (SQLException ex){
            ex.printStackTrace();
            promotion = new Promotion();
        }


        return promotion;
    }

    private Order getOrder(String data){

        Order order;

        try {
            Company company = new Company(0,"",0);
            ArrayList<Product> products = new ArrayList<>();
            int id = 0;
            String customer = "";
            int confirmNum = 0;
            int companyId = 0;
            String company_name;
            String address = "";
            double cost = 0;
            double shippingCost = 0;

            resultSet = statement.executeQuery("select orders.o_id, shipment.shipping_address, invoice.cost, concat_ws(' ', customer.fname, customer.lname) " +
                    "from orders, invoice, shipment, processed, ship, customer, ordered " +
                    "where concat_ws(' ', orders.o_id, shipment.shipping_address) = \"" + data + "\" " +
                    "and orders.o_id = ship.o_id " +
                    "and shipment.shipment_id = ship.shipment_id " +
                    "and orders.o_id = processed.O_id " +
                    "and invoice.invoice_id = processed.invoice_id " +
                    "and orders.o_id = ordered.o_id " +
                    "and customer.c_id = ordered.c_id;");

            resultSet.next();

            id = resultSet.getInt(1);
            address = resultSet.getString(2);
            cost = resultSet.getDouble(3);
            customer = resultSet.getString(4);

            resultSet = statement.executeQuery("select shipment.confirm_Num, shipping_Company.name, shipping_company.price, " +
                    "shipping_Company.company_id " +
                    "from orders, shipping_company, shipment, shipping, ship " +
                    "where orders.o_id = " + id + " " +
                    "and orders.o_id = ship.o_id " +
                    "and shipment.shipment_id = ship.shipment_id " +
                    "and shipment.shipment_id = shipping.shipment_id " +
                    "and shipping_company.company_id = shipping.company_id;");

            if(resultSet.next()) {
                confirmNum = resultSet.getInt(1);
                company_name = resultSet.getString(2);
                shippingCost = resultSet.getDouble(3);
                companyId = resultSet.getInt(4);
                company = new Company(companyId, company_name, shippingCost);
            }

            resultSet = statement.executeQuery("select products.p_id, products.name, products.description, products.amt_left, products.price " +
                    "from orders, processed, invoice, ordered_items, products " +
                    "where orders.o_id = " + id + " " +
                    "and orders.o_id = processed.o_id " +
                    "and invoice.invoice_id = processed.invoice_id " +
                    "and ordered_items.invoice_id = invoice.invoice_id " +
                    "and ordered_items.p_id = products.p_id;");

            while (resultSet.next()) {
                int pId = resultSet.getInt(1);
                String pName = resultSet.getString(2);
                String pDescription = resultSet.getString(3);
                int pAmt = resultSet.getInt(4);
                double pPrice = resultSet.getDouble(5);

                products.add(new Product(pId, pName, pDescription, pAmt, pPrice));
            }

            order = new Order(id, customer, confirmNum, cost, products, address, company);

        }catch (SQLException ex){
            ex.printStackTrace();
            order = new Order();
        }

        return order;
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

                resultSet = statement.executeQuery("select c_id from shop_At where c_id = " + id);
                if(resultSet.next()){

                    preparedStatement.executeUpdate("update shop_at set store_id = "+store_id+" where c_id = "+id);

                }else {
                    preparedStatement = connection.prepareStatement("insert into shop_at(c_id, store_id) " +
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

                preparedStatement = connection.prepareStatement("insert into shop_at(c_id, store_id) " +
                        "values (?,"+store_id+")");
                preparedStatement.setString(1,resultSet.getString(1));
                preparedStatement.executeUpdate();
            }

        }catch (SQLException ex){ ex.printStackTrace(); }
    }

    private void addSale(Receipt receipt){

//    public Receipt(String customer_name, String store_location, ArrayList<Product> products, String date) {

        try{

            preparedStatement = connection.prepareStatement("insert into sale(total_cost, date) " +
                    "values(?,?)");
            preparedStatement.setDouble(1,receipt.getTotal_balance());
            preparedStatement.setString(2,receipt.getDate());
            preparedStatement.executeUpdate();


            resultSet = statement.executeQuery("select s_id from sale where date = '"+receipt.getDate()+"';");
            resultSet.next();
            int id = resultSet.getInt(1);

            preparedStatement = connection.prepareStatement("insert into sold_at (s_id, store_id) " +
                    "values (?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,store_id);
            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery("select c_id " +
                    "from customer " +
                    "where concat_ws(' ', fname, lname) = '" +receipt.getCustomer_name()+"'");
            if(resultSet.next()){
                int cid = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("insert into makes_sale(c_id, s_id) " +
                        "values (?,?)");
                preparedStatement.setInt(1,cid);
                preparedStatement.setInt(2,id);
                preparedStatement.executeUpdate();
            }

            for(Product product: receipt.getProducts()){

                preparedStatement = connection.prepareStatement("insert into items_sold(p_id, s_id) " +
                        "values(?,?);");
                preparedStatement.setInt(1,product.getId());
                preparedStatement.setInt(2,id);
                preparedStatement.executeUpdate();

                subAmt(1,product);
            }

        }catch (SQLException ex){ex.printStackTrace();}

    }

    private void addProduct(Product product){

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
                        "set fname = ?, lname = ?, sex = ?, address = ?, age = ?, super_ssn = ? " +
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

                preparedStatement.executeUpdate();


                resultSet = statement.executeQuery("select ssn from employee where concat_ws(' ', fname, lname) = " +
                        "'"+employee.getFirst_name()+" "+employee.getLast_name()+"'");
                resultSet.next();
                preparedStatement = connection.prepareStatement("update works_at set store_id = ? where ssn = ?");
                preparedStatement.setInt(1,store_id);
                preparedStatement.setInt(2, resultSet.getInt(1));
                preparedStatement.executeUpdate();

            }else{
                resultSet = statement.executeQuery("select ssn from employee where concat_ws(' ', fname, lname) = " +
                        "'"+employee.getSuper_Name()+"'");

                preparedStatement = connection.prepareStatement("insert into employee(fname, " +
                        "lname, sex, address, age, super_ssn, ssn) values (?,?,?,?,?,?,?)");
                preparedStatement.setString(1,employee.getFirst_name());
                preparedStatement.setString(2,employee.getLast_name());
                preparedStatement.setString(3,employee.getSex().charAt(0)+"");
                preparedStatement.setString(4,employee.getAddress());
                preparedStatement.setInt(5,employee.getAge());

                if(resultSet.next())
                    preparedStatement.setInt(6,resultSet.getInt(1));
                else
                    preparedStatement.setString(6,null);

                preparedStatement.setInt(7,employee.getSsn());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("insert into works_at (store_id, ssn) values(?,?)");
                preparedStatement.setInt(1,store_id);
                preparedStatement.setInt(2, employee.getSsn());
                preparedStatement.executeUpdate();
            }

        }catch (SQLException ex){ ex.printStackTrace(); }

    }

    void addAmt(int amount, Product product){

        try {
            preparedStatement = connection.prepareStatement("update products " +
                    "set amt_left = amt_left + "+amount+" " +
                    "where name = '"+product.getName()+"';" );
            preparedStatement.executeUpdate();
        }catch (SQLException ex){ex.printStackTrace();}
    }

    void subAmt(int amount, Product product){

        try {
            preparedStatement = connection.prepareStatement("update products " +
                    "set amt_left = amt_left - "+amount+" " +
                    "where name = '"+product.getName()+"';" );
            preparedStatement.executeUpdate();
        }catch (SQLException ex){ex.printStackTrace();}
    }

    private void addEvent(Event event){
        try{
            resultSet = statement.executeQuery("select event_id from events " +
                    "where events.name = '" + event.getName()+"' "+
                    "and events.date = '" +event.getDate()+"';");

            if(resultSet.next()){
                String id = resultSet.getString(1);

                preparedStatement = connection.prepareStatement("update events " +
                        "set name = ?, host_name = ?, date = ? " +
                        "where event_id = '" + id +"'");

                preparedStatement.setString(1,event.getName());
                preparedStatement.setString(2,event.getHost());
                preparedStatement.setString(3,event.getDate());

                preparedStatement.executeUpdate();

            }else{
                preparedStatement = connection.prepareStatement("insert into events(name, " +
                        "host_name, date) values (?,?,?)");
                preparedStatement.setString(1,event.getName());
                preparedStatement.setString(2,event.getHost());
                preparedStatement.setString(3,event.getDate());
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select event_id from events where name = '"+event.getName()+"';");
                resultSet.next();


                preparedStatement = connection.prepareStatement("insert into take_place (store_id, event_id) values(?,?)");
                preparedStatement.setInt(1,store_id);
                preparedStatement.setInt(2, resultSet.getInt(1));
                preparedStatement.executeUpdate();
            }

        }catch (SQLException ex){ ex.printStackTrace(); }


    }

    private void addPromotion(Promotion promotion){
        try{
            resultSet = statement.executeQuery("select promo_id from promotions " +
                    "where promotions.promo_id = '" + promotion.getId()+"';");

            if(resultSet.next()){
                int id = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("update promotions " +
                        "set discount = ?, date = ? " +
                        "where promo_id = '" + id +"'");

                preparedStatement.setDouble(1,promotion.getDiscount());
                preparedStatement.setString(2,promotion.getDate());

                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select products.P_id " +
                        "from products " +
                        "where products.name = \"" + promotion.getItem() +"\";");

                resultSet.next();

                int pId = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("update promoted " +
                        "set p_id = ? " +
                        "where promo_id = '" + id +"'");

                preparedStatement.setInt(1,pId);

                preparedStatement.executeUpdate();

            }else{
                preparedStatement = connection.prepareStatement("insert into promotions(discount, " +
                        " date) values (?,?)");
                preparedStatement.setDouble(1,promotion.getDiscount());
                preparedStatement.setString(2,promotion.getDate());
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select promo_id from promotions where discount = '"+promotion.getDiscount()+"' " +
                        "and date = '"+ promotion.getDate()+"';");
                resultSet.next();

                int id = resultSet.getInt(1);

                resultSet = statement.executeQuery("select p_id from products where name = '"+promotion.getItem()+"';");
                resultSet.next();

                int pid = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("insert into promoted (p_id, promo_id) values(?,?)");
                preparedStatement.setInt(1,pid);
                preparedStatement.setInt(2,id);
                preparedStatement.executeUpdate();
            }

        }catch (SQLException ex){ ex.printStackTrace(); }



    }

    private void addOrder(Order order){
        int id = 0;
        int pId = 0;
        int invoiceId = 0;
        int customerId = 0;
        int shipmentId = 0;

        try {

            preparedStatement = connection.prepareStatement("insert into orders() values ()");
            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery("select Max(o_id) from orders;");
            resultSet.next();

            id = resultSet.getInt(1);

            preparedStatement = connection.prepareStatement("insert into invoice(cost) values (?)");
            preparedStatement.setDouble(1, order.getCost());
            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery("select Max(invoice_id) from invoice;");
            resultSet.next();

            invoiceId = resultSet.getInt(1);

            preparedStatement = connection.prepareStatement("insert into processed(invoice_id, o_id) values (?,?)");
            preparedStatement.setInt(1, invoiceId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            for(Product product: order.getProducts()) {
                resultSet = statement.executeQuery("select products.p_id " +
                        "from products " +
                        "where name = \"" + product.getName() + "\";");
                resultSet.next();

                pId = resultSet.getInt(1);

                preparedStatement = connection.prepareStatement("insert into ordered_items(p_id, invoice_id) " +
                        "value(?,?);");
                preparedStatement.setInt(1,pId);
                preparedStatement.setInt(2,invoiceId);
                preparedStatement.executeUpdate();
            }

            resultSet = statement.executeQuery("select c_id " +
                    "from customer " +
                    "where concat_ws(' ', fname, lname) = \""+order.getCustomer()+"\";");
            resultSet.next();
            customerId = resultSet.getInt(1);

            preparedStatement = connection.prepareStatement("insert into ordered(c_id, o_id) values (?,?)");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("insert into shipment(shipping_address, confirm_Num) values (?,?)");
            preparedStatement.setString(1, order.getShipping_Address());
            preparedStatement.setInt(2,0);
            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery("select Max(shipment_id) from shipment;");
            resultSet.next();

            shipmentId = resultSet.getInt(1);

            preparedStatement = connection.prepareStatement("insert into ship(shipment_id, o_id) values (?,?)");
            preparedStatement.setInt(1, shipmentId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }catch (SQLException ex){ ex.printStackTrace(); }

    }

    private void editOrder(Order order){

        System.out.println();

        try {
            int shipmentId;
            int confirm = 0;

            resultSet = statement.executeQuery("select shipment_id from ship where o_id = " + order.getId() + ";");
            resultSet.next();

            shipmentId = resultSet.getInt(1);

            do {
                confirm = (int) (Math.random() * 10000);

                resultSet = statement.executeQuery("select shipment_id from shipment where confirm_num = " + confirm + ";");
            }while(resultSet.next());


            preparedStatement = connection.prepareStatement("update shipment " +
                    "set confirm_num = ? " +
                    "where shipment_id = '" + shipmentId +"'");
            preparedStatement.setInt(1, confirm);
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("insert into shipping(shipment_id, company_id) values (?,?)");
            preparedStatement.setInt(1, shipmentId);
            preparedStatement.setInt(2, order.getCompany().getId());
            preparedStatement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    Company getOneCompany(String data){
        Company company = new Company(0,"",0);

        int companyId = 0;
        String company_name = "";
        double shippingCost = 0;
        try {

            resultSet = statement.executeQuery("select shipping_Company.name, shipping_company.price, " +
                    "shipping_Company.company_id " +
                    "from shipping_company " +
                    "where shipping_company.name = \"" + data + "\";");

            if (resultSet.next()) {

                company_name = resultSet.getString(1);
                shippingCost = resultSet.getDouble(2);
                companyId = resultSet.getInt(3);

                company = new Company(companyId, company_name, shippingCost);
            }
        }catch(SQLException ex){ex.printStackTrace();}

        return company;
    }
}
