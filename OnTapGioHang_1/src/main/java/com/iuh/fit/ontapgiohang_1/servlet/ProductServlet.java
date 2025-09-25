package com.iuh.fit.ontapgiohang_1.servlet;

import com.iuh.fit.ontapgiohang_1.dao.ProductDAO;
import com.iuh.fit.ontapgiohang_1.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSTR = req.getParameter("id");
        if (idSTR != null) {
            int id = Integer.parseInt(idSTR);
            Product product = productDAO.getProductById(id);
            if(product != null){
                req.setAttribute("product", product);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/product-detail.jsp");
                requestDispatcher.forward(req, resp);
            }else {
                resp.sendError(404);
            }
        }

        List<Product> products = productDAO.getAllProducts();
        req.setAttribute("products", products);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/product-list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
