package controllerClasses;

import otherClass.Db;
import modelClasses.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("user_id"));
        User foundUser = Db.userRepository().get(userId);

        if (foundUser != null) {
            request.setAttribute("viewFile", "profile.jsp");
            request.setAttribute("bookTitle", foundUser.toString());
            request.setAttribute("bookList", Db.bookRepository()
                    .getAllByUserId(userId, false));
        }

        Db.view(request, response);
    }
}
