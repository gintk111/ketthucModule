package Controller;

import Model.Category;
import Model.Product;
import service.ProductSevice;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductSevice productSevice;

    public void init() {
        productSevice = new ProductSevice();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "insert" :
                    insertProduct(request, response);
                    break;
                case "update" :
                    updateProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "insert" :
                    showInsertProduct(request, response);
                    break;
                case "update" :
                    showUpdateProduct(request, response);
                    break;
                case "delete" :
                    deleteProduct(request, response);
                    break;
                default:
                    showProduct(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productSevice.deleteProduct(id);

        String key = request.getParameter("key");
        String cat = request.getParameter("cat");
        if (cat == "") {
            cat = null;
        }
        List<Product> products = productSevice.sellectAllProduct(key,cat);
        request.setAttribute("message", "\n" + "Deleted successfully");
        request.setAttribute("customer", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categories = productSevice.selectAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        Product product = productSevice.selectProduct(id);
        request.setAttribute("product", product);
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int cat = Integer.parseInt(request.getParameter("cat"));
        String color = request.getParameter("color");
        float price = Float.parseFloat(request.getParameter("price"));
        String des = request.getParameter("des");

        Product product = new Product(id,name,amount,cat,des,color,price);
        productSevice.updateProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        request.setAttribute("message", "Update successfully");
        dispatcher.forward(request, response);

    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        List<Category> categories = productSevice.selectAllCategory();
        request.setAttribute("categories", categories);
        String key = request.getParameter("key");
        String cat = request.getParameter("cat");
        if (cat == "") {
            cat = null;
        }
        List<Product> products = productSevice.sellectAllProduct(key,cat);
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showInsertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Category> categories = productSevice.selectAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int cat_id = Integer.parseInt(request.getParameter("cat"));
        String color = request.getParameter("color");
        float price = Float.parseFloat(request.getParameter("price"));
        String des = request.getParameter("des");

        Product product = new Product(name,amount,cat_id,des,color,price);
        productSevice.insertProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
        request.setAttribute("message", "Insert successfully");
        dispatcher.forward(request, response);
    }



}
