package controllerClasses;

import modelClasses.Book;
import otherClass.Db;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("book_id"));
        Book foundBook = Db.bookRepository().get(bookId);

        if (foundBook != null) {
            request.setAttribute("viewFile", "edit.jsp");
            DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            request.setAttribute("bookTitle", "Edit book / "
                    + sdf.format(foundBook.getPublication_date()));
            request.setAttribute("book", foundBook);
            Db.view(request, response);
        } else {
            response.sendRedirect("mybooks");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("book_id"));
        Book foundBook = Db.bookRepository().get(bookId);

        if (foundBook != null) {
            String bookAutor = request.getParameter("bookAutor");
            boolean visible = request.getParameter("visible") != null;
            foundBook.setAutor(bookAutor);
            foundBook.setVisibility(visible);

            Book editResult = Db.bookRepository()
                    .update(foundBook.getBook_id(), foundBook);

            if (editResult != null) {
                response.sendRedirect("mybooks");
            } else {
                request.setAttribute("viewFile", "edit.jsp");
                DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                request.setAttribute("bookTitle", "Edit book / "
                        + sdf.format(foundBook.getPublication_date()));
                request.setAttribute("message", "Something went wrong");
                Db.view(request, response);
            }
        } else {
            response.sendRedirect("mybooks");
        }

    }
}
