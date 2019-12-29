/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.books.BookDAO;
import sample.books.BookDTO;
import sample.category.CategoryDAO;
import sample.category.CategoryDTO;
import sample.status.StatusDAO;
import sample.status.StatusDTO;

/**
 *
 * @author mrhie
 */
public class SearchServlet extends HttpServlet {

    private final String ADMIN = "adminpage.jsp";
    private final String CUSTOMER = "shoppingpage.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = CUSTOMER;
        try {
            HttpSession session = request.getSession(false);
            String kind = request.getParameter("kind");
            if (kind == null) {
                kind = (String) session.getAttribute("kind");

            } else {

                session.setAttribute("kind", kind);
            }

            StatusDAO daoStatus = new StatusDAO();
            Vector<StatusDTO> listStatus = daoStatus.getAllStatus();

            session.setAttribute("LISTSTATUS", listStatus);
            //
            CategoryDAO daoCategory = new CategoryDAO();
            Vector<CategoryDTO> listCategory = daoCategory.getAllCategory();
            request.setAttribute("LISTCATEGORY", listCategory);
            BookDAO daoBook = new BookDAO();
            if (kind != null) {
                if (kind.equalsIgnoreCase("ByName")) {

                    String searchValue = request.getParameter("searchValue");
                    if (searchValue == null) {
                        searchValue = (String) session.getAttribute("searchValue");
                    }
                    Vector<BookDTO> listResult = daoBook.searchBookByLikeName(searchValue);
                    session.setAttribute("searchValue", searchValue);
                    request.setAttribute("LISTBOOK", listResult);
                } else if (kind.equalsIgnoreCase("ByCategory")) {
                    String category = request.getParameter("txtCategory");
                    if (category == null) {
                        category = (String) session.getAttribute("searchCategory");
                    }

                    for (int i = 0; i < listCategory.size(); i++) {
                        if (category.equalsIgnoreCase(listCategory.get(i).getDescription())) {
                            Vector<BookDTO> listResult = daoBook.searchBookByCategory(listCategory.get(i).getCategory());
                            request.setAttribute("LISTBOOK", listResult);

                            session.setAttribute("searchCategory", category);
                            break;
                        }
                    }

                } else if (kind.equalsIgnoreCase("ByPrice")) {
                    String txtMinPrice = request.getParameter("txtMinPrice");
                    String txtMaxPrice = request.getParameter("txtMaxPrice");
                    float minPrice = 0;
                    float maxPrice = 0;
                    if (txtMaxPrice == null || txtMinPrice == null) {
                        txtMinPrice = (String) session.getAttribute("MinPrice");
                        txtMaxPrice = (String) session.getAttribute("MaxPrice");
                        minPrice = Float.parseFloat(txtMinPrice);
                        maxPrice = Float.parseFloat(txtMaxPrice);

                    } else {
                        minPrice = Float.parseFloat(txtMinPrice);
                        maxPrice = Float.parseFloat(txtMaxPrice);
                    }

                    Vector<BookDTO> listResult = daoBook.searchBookByPrice(minPrice, maxPrice);
                    request.setAttribute("LISTBOOK", listResult);
                    session.setAttribute("MinPrice", minPrice);
                    session.setAttribute("MaxPrice", maxPrice);

                }
            }
            if (session.getAttribute("ADMIN") != null) {
                url = ADMIN;
            } else if (session.getAttribute("CUSTOMER") != null) {
                url = CUSTOMER;
            }

        } catch (Exception e) {
            log("Error at SearchServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
