/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.books.BookDTO;
import sample.cart.CartItem;
import sample.cart.Item;

/**
 *
 * @author mrhie
 */
public class UpdateQuantityServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession(false);
            CartItem cart = (CartItem) session.getAttribute("CART");
            for (int i = 0; i < cart.getCart().size(); i++) {
                if (request.getParameter("quantity_" + (i + 1)) != null) {
                    int quantity = Integer.parseInt(request.getParameter("quantity_" + (i + 1)));

                    Item item = cart.getCart().get(i);
                    item.setQuantity(quantity);

                    request.setAttribute("Mes", "Update success.");

                    break;
                }
            }
            float totalPrice = 0;
            for (int i = 0; i < cart.getCart().size(); i++) {
                BookDTO b = cart.getCart().get(i).getBook();
                int quantity = cart.getCart().get(i).getQuantity();
                totalPrice += b.getPrice() * quantity;
            }
            session.setAttribute("TOTALPRICE", totalPrice);

        } catch (Exception e) {
            log("Error at UpdateQuantityServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("showcartpage.jsp").forward(request, response);
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
