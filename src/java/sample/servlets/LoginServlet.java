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
import sample.category.CategoryDAO;
import sample.category.CategoryDTO;

import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author mrhie
 */
public class LoginServlet extends HttpServlet {

    private final String NONADMIN = "shoppingpage.jsp";
    private final String ADMIN = "adminpage.jsp";
    private final String LOGIN = "loginpage.jsp";
    private final String DETAILBOOK = "ShowDetailBookServlet";

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
        String url = LOGIN;
        try {
            CategoryDAO daoCategory = new CategoryDAO();
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");

            UserDAO daoUser = new UserDAO();
            UserDTO user = daoUser.login(userID, password);

            HttpSession session = request.getSession();

            switch (user.getRole()) {
                case 1:
                    {
                        // admin
                        session.removeAttribute("kind");
                        session.removeAttribute("searchValue");
                        session.removeAttribute("MinPrice");
                        session.removeAttribute("MaxPrice");
                        session.removeAttribute("searchCategory");
                        session.setAttribute("ADMIN", user.getFullname());
                        Vector<CategoryDTO> listCategory = daoCategory.getAllCategory();
                        request.setAttribute("LISTCATEGORY", listCategory);
                        url = ADMIN;
                        break;
                    }
                case 2:
                    {
                        // customer
                        String bookID = "";
                        bookID = request.getParameter("bookID");
                        if (bookID.equals("")==false) {
                            url = DETAILBOOK;
                        } else {
                            url = NONADMIN;
                        }       Vector<CategoryDTO> listCategory = daoCategory.getAllCategory();
                        request.setAttribute("LISTCATEGORY", listCategory);
                        session.setAttribute("CUSTOMER", user);
                        break;
                    }
                default:
                    request.setAttribute("MesError", "UserID or password maybe incorrect.");
                    break;
            }

        } catch (Exception e) {
        
            log("Error at LoginServlet: " + e.getMessage());
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
