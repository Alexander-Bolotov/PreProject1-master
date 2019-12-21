package src.main.servlet;

import src.main.model.User;
import src.main.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        String name = req.getParameter("name");
        if (name == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пользователь");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            if (userService.deleteUserByName(name)) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().println("Client not delete");
            }
        }
    }
}
