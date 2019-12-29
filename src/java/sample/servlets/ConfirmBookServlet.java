/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.books.BookDAO;
import sample.books.BookDTO;
import sample.cart.CartItem;
import sample.cart.Item;
import sample.discounts.DiscountDAO;
import sample.discounts.DiscountDTO;
import sample.orderdetails.OrderDetailDAO;
import sample.orderdetails.OrderDetailDTO;
import sample.orders.OrderDAO;
import sample.orders.OrderDTO;
import sample.user.UserDTO;

/**
 *
 * @author mrhie
 */
public class ConfirmBookServlet extends HttpServlet {

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
            BookDAO daoBook = new BookDAO();
            Vector<String> listErrorQuantity = new Vector<>();
            boolean findError = false;
            for (int i = 0; i < cart.getCart().size(); i++) {
                if (request.getParameter("quantity_" + (i + 1)) != null) {
                    int quantity = Integer.parseInt(request.getParameter("quantity_" + (i + 1)));

                    Item item = cart.getCart().get(i);
                    item.setQuantity(quantity);

                }
            }
            for (int i = 0; i < cart.getCart().size(); i++) {
                BookDTO b = cart.getCart().get(i).getBook();
                int quantity = cart.getCart().get(i).getQuantity();
                int maxQuantity = daoBook.getMaxQuantity(b.getBookID());
                if (quantity > maxQuantity) {
                    listErrorQuantity.add("Out of Stock at row " + (i + 1) + " (Max quantity: " + maxQuantity + ")");

                    findError = true;
                }

            }

            if (findError == true) {
                request.setAttribute("LISTERRORQUANTITY", listErrorQuantity);
            } else {

                OrderDTO order = new OrderDTO();
                UserDTO customer = (UserDTO) session.getAttribute("CUSTOMER");
                order.setCustomerID(customer.getUserID());
                DiscountDTO discount = (DiscountDTO) session.getAttribute("DISCOUNT");
                float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
                if (discount != null) {
                    totalPrice = totalPrice * (1 - discount.getValue());
                }
                order.setTotalPrice(totalPrice);
                LocalDateTime currentDate = LocalDateTime.now();
                order.setCreateDate(currentDate);
                OrderDAO daoOrder = new OrderDAO();
                daoOrder.createNewOrder(order);
                int orderId = daoOrder.getNewestOrderID();
                OrderDetailDAO daoOrderDetail = new OrderDetailDAO();

                for (int i = 0; i < cart.getCart().size(); i++) {
                    BookDTO b = cart.getCart().get(i).getBook();
                    OrderDetailDTO orderDetail = new OrderDetailDTO();
                    orderDetail.setOrderID(orderId);
                    orderDetail.setBookID(b.getBookID());
                    orderDetail.setQuantity(cart.getCart().get(i).getQuantity());
                    orderDetail.setUnitPrice(b.getPrice());
                    orderDetail.setBookName(b.getBookName());
                    if (daoOrderDetail.createNewOrderDetail(orderDetail) == false) {
                        findError = true;
                    } else {
                        int quantity = cart.getCart().get(i).getQuantity();
                        daoBook.updateQuantityAfterShopping(b.getBookID(), quantity);
                    }
                }
                if (findError == false) {
                    UserDTO cus = (UserDTO) session.getAttribute("CUSTOMER");
                    String disCode = (String) session.getAttribute("DISCOUNTCODE");
                    DiscountDAO daoDiscount = new DiscountDAO();
                    daoDiscount.useDiscount(cus.getUserID(), disCode);
                    request.setAttribute("Mes", "Confirm success.");
                    session.removeAttribute("CART");
                }
            }
        } catch (Exception e) {

            log("Error at ConfirmBookServlet: " + e.getMessage());
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
