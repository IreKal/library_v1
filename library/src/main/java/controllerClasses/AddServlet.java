package controllerClasses;

import modelClasses.Book;
import modelClasses.User;
import otherClass.Db;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("viewFile", "add.jsp");
        DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        request.setAttribute("bookTitle", "Add new book / "
                + sdf.format(new Date()));
        Db.view(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = Db.getLoginUser(request);
        String bookAutor = request.getParameter("bookAutor");
        boolean visible = request.getParameter("visible") != null;

        Book newBook = new Book();
        newBook.setUser_id(loginUser.getUser_id());
        newBook.setPublication_date(new Date());
        newBook.setAutor(bookAutor);
        newBook.setVisibility(visible);

        boolean addResult = Db.bookRepository().add(newBook);
        if (!addResult) {
            request.setAttribute("viewFile", "add.jsp");
            DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            request.setAttribute("bookTitle", "Add new book / "
                    + sdf.format(new Date()));
            request.setAttribute("message", "Something went wrong");
            Db.view(request, response);
        } else {
            response.sendRedirect("mybooks");
        }

    }
}

