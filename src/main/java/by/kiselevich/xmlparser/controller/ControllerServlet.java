package by.kiselevich.xmlparser.controller;

import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.CommandProvider;
import by.kiselevich.xmlparser.command.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {""})
@MultipartConfig(maxFileSize=1024*1024*5)
public class ControllerServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(ControllerServlet.class);
    private static final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void init() throws ServletException {
        LOG.trace("ControllerServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("ControllerServlet doGet");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("ControllerServlet doPost");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParameter = req.getParameter(Parameter.COMMAND.getValue());
        Command command = commandProvider.getCommand(commandParameter);
        req.getRequestDispatcher(command.execute(req, resp).getPath()).forward(req, resp);
    }
}
