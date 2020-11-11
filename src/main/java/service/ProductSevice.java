package service;

import Model.Category;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSevice {
    private final static String jdbcURL = "jdbc:mysql://localhost:3306/casestudy?useSSL=false";
    private final static String jdbcUsername = "root";
    private final static String jdbcPassword = "02031997";

    private static final String SELECT_CATEGORY = "select * from category ";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products" + " (name,amount,cat_id,description,color,price) VALUES" + " (?,?,?,?,?,?);";
    private static  final  String SELECT_ALL_PRODUCT = "select products.id,products.name,products.amount,products.color,products.description,products.price,category.cat_name from products join category on products.cat_id = category.cat_id";
    private static  final  String SELECT_PRODUCT = "select products.id,products.name,products.cat_id,products.amount,products.color,products.description,products.price,category.cat_name from products join category on products.cat_id = category.cat_id where id = ?";
    private static  final  String UPDATE_PRODUCT = "update products set name = ?,cat_id= ?, amount =? ,color=?,description=?,price=? where id = ?;";
    private static  final  String DELETE_PRODUCT_SQL = "delete from products where id = ?;";


    protected static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean deleteProduct(int id) {
        boolean rowDeleted = false;
        try{
            Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    public Product selectProduct(int id) {
        Product product = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int pr_id = rs.getInt("products.id");
                String name = rs.getString("products.name");
                int amount = rs.getInt("products.amount");
                String cat_name = rs.getString("category.cat_name");
                String des = rs.getString("products.description");
                String color = rs.getString("products.color");
                float price = rs.getFloat("products.price");
                int cat_id = rs.getInt("products.cat_id");
                product = new Product(pr_id,name,amount,cat_id,cat_name,des,color,price);
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public boolean updateProduct (Product product) throws SQLException{
        boolean rowUpdated = false;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getCat_id());
            preparedStatement.setInt(3,product.getAmount());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setFloat(6,product.getPrice());
            preparedStatement.setInt(7,product.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    public List<Product> sellectAllProduct (String key,String cat) {
        List<Product> products = new ArrayList<>();
        String where = "";
        if (key == ""){
            key = null;
        }
        if (cat != null) {
            where += " products.cat_id = " + cat;
        }
        if (key != null) {
            if (where != "") {
                where += " AND products.name like " + " %"+key+"%";
            } else {
                where += " products.name like " + " '" +"%"+key+"%'";
            }
        }
        try {
            Connection connection = getConnection();
            if (where != ""){
                where = " WHERE" + where;
            }else {
                where = "";
            }
            String strWhere = SELECT_ALL_PRODUCT + where;
            PreparedStatement preparedStatement = connection.prepareStatement(strWhere);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(strWhere);
            while (rs.next()) {
                int id = rs.getInt("products.id");
                String name = rs.getString("products.name");
                int amount = rs.getInt("products.amount");
                String cat_name = rs.getString("category.cat_name");
                String des = rs.getString("products.description");
                String color = rs.getString("products.color");
                float price = rs.getFloat("products.price");

                products.add(new Product(id,name,amount,cat_name,des,color,price));
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public void insertProduct(Product product) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getAmount());
            preparedStatement.setInt(3, product.getCat_id());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setFloat(6, product.getPrice());
            preparedStatement.executeUpdate();
            System.out.println(INSERT_PRODUCT_SQL);
        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<Category> selectAllCategory () {
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cat_id = rs.getInt("cat_id");
                String cat_name = rs.getString("cat_name");
                categories.add(new Category(cat_id,cat_name));
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return categories;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
