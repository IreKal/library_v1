package controllerClasses;

import modelClasses.User;
import otherClass.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MybooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loginUser = Db.getLoginUser(request);
        request.setAttribute("viewFile", "mybooks.jsp");
        request.setAttribute("bookTitle", "My books");
        request.setAttribute("bookList", Db.bookRepository()
                .getAllByUserId(loginUser.getUser_id(), true));
        Db.view(request, response);
    }
}
