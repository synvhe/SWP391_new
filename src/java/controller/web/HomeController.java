package controller.web;

import DAL.CategoryDAO;
import DAL.ImagesDAO;
import DAL.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Images;
import model.Product;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ProductDAO productDAO = new ProductDAO();
        ImagesDAO imagesDAO = new ImagesDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        try {

            List<Category> listCate = categoryDAO.getAllCategories();
            request.setAttribute("listCate", listCate);

            String txtSearch = request.getParameter("txt");
            String categoryIdParam = request.getParameter("categoryId");

            List<Product> products;

            if (txtSearch != null && !txtSearch.trim().isEmpty()) {

                products = productDAO.searchProductsByName(txtSearch.trim());
            } else if (categoryIdParam != null && !categoryIdParam.trim().isEmpty()) {

                try {
                    int categoryId = Integer.parseInt(categoryIdParam.trim());
                    products = productDAO.getProductsByCategory(categoryId);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid category ID: " + categoryIdParam);
                }
            } else {

                products = productDAO.getAllProducts();
            }

            request.setAttribute("products", products);

            Map<Integer, List<Images>> productImages = new HashMap<>();
            for (Product product : products) {
                List<Images> images = imagesDAO.getImagesByProductId(product.getProductId());
                productImages.put(product.getProductId(), images);
            }
            request.setAttribute("productImages", productImages);

            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
