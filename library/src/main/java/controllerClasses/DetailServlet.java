package controllerClasses;

import modelClasses.Book;
import otherClass.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("book_id"));
        Book foundBook = Db.bookRepository().get(bookId);

        if (foundBook != null) {
            request.setAttribute("viewFile", "detail.jsp");
            request.setAttribute("bookTitle", foundBook.getPublication_date());
            request.setAttribute("book", foundBook);
        }
        Db.view(request, response);
    }
}

