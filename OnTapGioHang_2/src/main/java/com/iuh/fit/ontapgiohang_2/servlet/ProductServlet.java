package com.iuh.fit.ontapgiohang_2.servlet;

import com.iuh.fit.ontapgiohang_2.dao.ProductDAO;
import com.iuh.fit.ontapgiohang_2.model.Product;
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
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        this.productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        if (productId != null) {
            int productIdInt = Integer.parseInt(productId);
            Product product = productDAO.getProductById(productIdInt);
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }else{
                req.setAttribute("product", product);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/product-detail.jsp");
                requestDispatcher.forward(req, resp);
            }
        }

        List<Product> products = productDAO.getAllProduct();
        req.setAttribute("products", products);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/product-list.jsp");
        requestDispatcher.forward(req, resp);

    }
}
