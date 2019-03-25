package ua.com.sliusar.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * Class ParserHttpRequest
 *
 * @author create by ivanslusar
 * 3/22/19
 * @project MyLuxoftProject
 */
public class ParserHttpRequest {
    private static final Logger logger = Logger.getLogger(ParserHttpRequest.class);
    public static String toJson(HttpServletRequest req) {
        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception ex) {
            logger.error("We have a problem with parse request to JSON", ex);
        }
        return jb.toString();
    }
}
