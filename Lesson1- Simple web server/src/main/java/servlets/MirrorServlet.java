package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by @maxkrivich on 08.05.2017.
 */

public class MirrorServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(req);


        resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageVariables));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    HashMap<String, Object> createPageVariablesMap(HttpServletRequest req){
        HashMap<String, Object> res = new HashMap<>();
        res.put("key", req.getParameter("key"));
        return res;
    }
}
