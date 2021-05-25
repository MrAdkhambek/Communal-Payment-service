package r2.llc.communal.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        return resolveToken(bearerToken);
    }
}
