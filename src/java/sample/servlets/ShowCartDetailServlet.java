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
import sample.cart.CartItem;

/**
 *
 * @author mrhie
 */
public class ShowCartDetailServlet extends HttpServlet {

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
            float totalPrice = 0;
            if (cart != null) {
                for (int i = 0; i < cart.getCart().size(); i++) {
                    BookDTO b = cart.getCart().get(i).getBook();
                    int quantity = cart.getCart().get(i).getQuantity();
                    totalPrice += b.getPrice() * quantity;
                }

                BookDAO daoBook = new BookDAO();
                Vector<BookDTO> listBookMaxQuantity = new Vector<>();
                for (int i = 0; i < cart.getCart().size(); i++) {
                    BookDTO tmp = new BookDTO();
                    BookDTO b = cart.getCart().get(i).getBook();
                    tmp.setBookID(b.getBookID());
                    tmp.setQuantity(daoBook.getMaxQuantity(b.getBookID()));
                    listBookMaxQuantity.add(tmp);

                }

                session.setAttribute("TOTALPRICE", totalPrice);
                session.setAttribute("LISTMAXQUANTITY", listBookMaxQuantity);
            }
        } catch (Exception e) {
       
            log("Error at ShowCartDetailServlet: " + e.getMessage());
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
