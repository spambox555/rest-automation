package automation.rest;

import automation.rest.utils.AlmUtils;
import automation.rest.utils.ConnectionProperties;
import automation.rest.utils.CookieStorage;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author sergey.belonozhko@hp.com
 */
public class AlmDomainsTest {

    ConnectionProperties props = AlmUtils.getConnectionProperties();
    private CookieStorage cookieStorage;

    @Before
    public void setUp() throws Exception {
        cookieStorage = AlmUtils.loginWithDefaults();
    }

    @Test
    public void testGetDomains() throws Exception {
        RestClient client = new RestClient();
        Resource domainsResource = client.resource(props.getBaseDirUri() + "api/domains");
        cookieStorage.applyCookies(domainsResource);
        ClientResponse domainsResponse = domainsResource
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        Assert.assertEquals(200, domainsResponse.getStatusCode());
        String content = domainsResponse.getEntity(String.class);
        System.out.println(content);
        DomainsResult domainsResult = new ObjectMapper().readValue(content, DomainsResult.class);
        System.out.println(domainsResult.totalCount);
    }

    public static class Domain {
        public String name;
        @JsonIgnore
        public Object projects;
    }

    public static class DomainsResult {
        public List<Domain> results;
        @JsonProperty("total-count")
        public int totalCount;
    }
}
