package by.kiselevich.xmlparser.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtil {

    private static final Logger LOG = LogManager.getLogger(HttpUtil.class);

    private static final String TEXT_CONTENT_TYPE = "text/plain";
    private static final String UTF_8_ENCODING = "UTF-8";

    private HttpUtil() {

    }

    public static void writeMessageToResponse(HttpServletResponse resp, String message) {
        try {
            resp.setContentType(TEXT_CONTENT_TYPE);
            resp.setCharacterEncoding(UTF_8_ENCODING);
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(message);
        } catch (IOException e) {
            LOG.warn(e);
        }
    }
}
