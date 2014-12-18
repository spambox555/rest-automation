package automation.rest;

import org.apache.wink.client.RestClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author sergey.belonozhko@hp.com
 */
public class YodaRestTest {
    @Test
    public void testYoda() throws Exception {
        RestClient client = new RestClient();
        String request = "Train yourself to let go of everything you fear to lose.";
        System.out.println();
        System.out.println("Request: " + request);
        String response = client.resource("https://yoda.p.mashape.com/yoda")
                .queryParam("sentence", request)
                .header("X-Mashape-Key", "4ygwK9Xr83msh7ZkyWUCam3lbfuPp1LajlUjsnFBBtZuwGyAZs")
                .get(String.class);
        System.out.println("Response: " + response);
        Assert.assertNotEquals(request, response);
    }
}
