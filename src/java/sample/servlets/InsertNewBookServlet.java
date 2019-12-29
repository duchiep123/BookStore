/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
public class InsertNewBookServlet extends HttpServlet {

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
            BookDAO dao = new BookDAO();
            if (dao.getBookByID(bookID) == null) {
                String bookName = request.getParameter("bookName");
                String description = request.getParameter("description");
                int catgory = Integer.parseInt(request.getParameter("category"));
                float price = Float.parseFloat(request.getParameter("price"));
                String author = request.getParameter("author");
                String image = request.getParameter("image");
                LocalDate importDate = LocalDate.now();
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int status = 1;
                BookDTO newBook = new BookDTO(bookID, status, bookName, image, description, price, author, catgory, quantity, importDate);
                if (dao.insertNewBook(newBook)) {
                    request.setAttribute("Mes", "Insert new Book success.");
                } else {
                    request.setAttribute("Mes", "Insert new Book fail.");
                }
            } else {
                request.setAttribute("Mes", "BookID is exsited.");
            }

        } catch (Exception e) {
            log("Errot at InsertNewBookServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("insertbookpage.jsp").forward(request, response);
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
