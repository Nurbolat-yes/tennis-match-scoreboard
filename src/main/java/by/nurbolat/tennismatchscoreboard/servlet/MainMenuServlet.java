package by.nurbolat.tennismatchscoreboard.servlet;

import by.nurbolat.tennismatchscoreboard.util.JspWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/main-menu")
public class MainMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspWrapper.toPath("mainMenu")).forward(req,resp);
    }
}
