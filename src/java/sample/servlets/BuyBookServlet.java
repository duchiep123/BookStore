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
import sample.books.BookDAO;
import sample.books.BookDTO;
import sample.cart.CartItem;
import sample.cart.Item;

/**
 *
 * @author mrhie
 */
public class BuyBookServlet extends HttpServlet {

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

            String bookID = request.getParameter("bookID");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (session.getAttribute("CUSTOMER") != null) {
                    BookDAO daoBook = new BookDAO();
                    Item item = new Item();
                    item.setBook(daoBook.getBookByID(bookID));
                    item.setQuantity(quantity);
                    if (session.getAttribute("CART") == null) { // add book lan dau tien khi chua co cart
                        CartItem cart = new CartItem();

                        cart.addToCart(item);
                        session.setAttribute("CART", cart);
                    } else { // add book co san tron cart
                        int check = 0;
                        CartItem cart = (CartItem) session.getAttribute("CART");

                        for (int i = 0; i < cart.getCart().size(); i++) {
                            BookDTO b = cart.getCart().get(i).getBook();
                            if (b.getBookID().equals(bookID)) {
                                cart.updateQuantity(bookID, quantity);
                                check = 1;
                                session.setAttribute("CART", cart);
                                break;
                            }
                        }
                        if (check == 0) { // add new book

                            cart.addToCart(item);
                            session.setAttribute("CART", cart);
                        }

                    }
                    request.setAttribute("Mes", "Buy Success");

                }

            }

        } catch (Exception e) {
            log("Error at BuyBookServlet: " + e.getMessage());
        } finally {

            request.getRequestDispatcher("ShowDetailBookServlet").forward(request, response);
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
