/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.orders.OrderDAO;
import sample.orders.OrderDTO;
import sample.user.UserDTO;

/**
 *
 * @author mrhie
 */
public class SearchOrderServlet extends HttpServlet {

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
        String url = "";
        try {

            HttpSession session = request.getSession(false);
            String date = request.getParameter("date");
            if (date.equals("")) {
                url = "ShowHistoryOrderServlet";
            } else {
                url = "historyorderpage.jsp";

                UserDTO customer = (UserDTO) session.getAttribute("CUSTOMER");
                String cusId = customer.getUserID();
                OrderDAO daoOrder = new OrderDAO();
                Vector<OrderDTO> listOrder = daoOrder.searchOrderByDate(cusId);
                Vector<OrderDTO> listOrderReal = new Vector<>();
                for (int i = 0; i < listOrder.size(); i++) {
                    LocalDate d = listOrder.get(i).getCreateDate().toLocalDate();
                    LocalDate d2 = LocalDate.parse(date);
                    if (d.isEqual(d2)) {
                        listOrderReal.add(listOrder.get(i));
                    }
                }
                if (listOrderReal.isEmpty() == false) {
                    request.setAttribute("LISTHISTORYORDER", listOrderReal);
                }
            }

        } catch (Exception e) {

            log("Error at SearchOrderServlet: " + e.getMessage());
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
