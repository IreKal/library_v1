package controllerClasses;

import otherClass.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("viewFile", "index.jsp");
        request.setAttribute("bookTitle", "Book App!");
        request.setAttribute("bookList", Db.bookRepository().getAll());
        request.setAttribute("userList", Db.userRepository().getAll());
        Db.view(request, response);
    }

}
