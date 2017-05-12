package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @maxkrivich on 12.05.2017.
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        UserProfile profile = accountService.getUserByLogin(login);
        if(profile == null){
            profile = new UserProfile(login, pass, "");
            accountService.addNewUser(profile);
            accountService.addSession(req.getSession().getId(), profile);

            Gson gson = new Gson();
            String json = gson.toJson(profile);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Login is already used");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
