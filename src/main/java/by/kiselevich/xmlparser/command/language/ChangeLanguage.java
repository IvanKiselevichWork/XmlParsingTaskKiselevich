package by.kiselevich.xmlparser.command.language;

import by.kiselevich.xmlparser.command.Attribute;
import by.kiselevich.xmlparser.command.Command;
import by.kiselevich.xmlparser.command.Page;
import by.kiselevich.xmlparser.command.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChangeLanguage implements Command {

    private static final Set<String> LANGUAGES = new HashSet<>(Arrays.asList("ru_RU", "en_US"));

    @Override
    public Page execute(HttpServletRequest req, HttpServletResponse resp) {
        String targetLanguage = req.getParameter(Parameter.TARGET_LANGUAGE.getValue());
        if (targetLanguage != null && LANGUAGES.contains(targetLanguage)) {
            req.getSession().setAttribute(Attribute.LANGUAGE.getValue(), targetLanguage);
            return Page.HOME;
        }
        return Page.WRONG_REQUEST;
    }
}
