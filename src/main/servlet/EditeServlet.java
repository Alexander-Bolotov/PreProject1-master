package src.main.servlet;

import src.main.model.User;
import src.main.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        User user = new User(id, name);

        if (name == null || id == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Не введен пароль или логин или ID");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            if (userService.editeUser(user)) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().println("Пользователь с таким именем уже существует");
            }
        }
    }
}
