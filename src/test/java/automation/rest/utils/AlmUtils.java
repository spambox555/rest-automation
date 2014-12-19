package automation.rest.utils;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author sergey.belonozhko@hp.com
 */
public class AlmUtils {

    public static CookieStorage login(String almBaseUri, String user, String pass) {
        CookieStorage cookieStorage = new CookieStorage();
        RestClient client = new RestClient();
        ClientResponse authResponse = client.resource(almBaseUri + "authentication-point/alm-authenticate")
                .accept(MediaType.APPLICATION_XML_TYPE)
                .contentType(MediaType.APPLICATION_XML_TYPE)
                .header("PtAL", "c12e01f2a13ff5587e1e9e4aedb8242d")
                .post(getAuthData(user, pass));
        cookieStorage.rememberCookies(authResponse);
        String authCookies = authResponse.getHeaders().getFirst("Set-Cookie");

        Resource resource = client.resource(almBaseUri + "rest/site-session");
        cookieStorage.applyCookies(resource);
        ClientResponse sessionResponse = resource
                .cookie(authCookies)
                .post("");
        cookieStorage.rememberCookies(sessionResponse);
        return cookieStorage;
    }

    private static String getAuthData(String user, String pass) {
        String bodyPattern = "<alm-authentication>\n" +
                "    <user>%s</user>\n" +
                "    <password>%s</password>\n" +
                "</alm-authentication>";
        return String.format(bodyPattern, user, pass);
    }

    public static ConnectionProperties getConnectionProperties() {
        try (InputStream is = AlmUtils.class.getClassLoader().getResourceAsStream("tests.properties")) {
            Properties props = new Properties();
            props.load(is);
            return new ConnectionProperties(props);
        } catch (IOException e) {
            throw new RuntimeException("test.properties missed");
        }
    }

    public static CookieStorage loginWithDefaults() {
        ConnectionProperties props = getConnectionProperties();
        return login(props.getBaseDirUri(), props.getUserName(), props.getPassword());
    }
}
