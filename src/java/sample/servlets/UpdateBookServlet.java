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
import sample.books.BookDAO;
import sample.books.BookDTO;

/**
 *
 * @author mrhie
 */
public class UpdateBookServlet extends HttpServlet {

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
            String bookName = request.getParameter("txtBookName");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            String author = request.getParameter("txtAuthor");
            int category = Integer.parseInt(request.getParameter("txtCategory"));
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

            BookDTO dto = new BookDTO();
            dto.setBookName(bookName);
            dto.setPrice(price);
            dto.setAuthor(author);
            dto.setCategory(category);
            dto.setQuantity(quantity);
            dto.setBookID(bookID);

            BookDAO dao = new BookDAO();
            if(dao.updateBook(dto)){
                request.setAttribute("Mes", "Update book success.");
            }
        } catch (Exception e) {
            log("Error at UpdateBookServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("EditBookServlet").forward(request, response);
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
