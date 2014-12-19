package automation.rest;

import automation.rest.utils.AlmUtils;
import automation.rest.utils.CookieStorage;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author sergey.belonozhko@hp.com
 */
public class AlmListItemsTest {
    private CookieStorage cookieStorage;

    @Before
    public void setUp() throws Exception {
        cookieStorage = AlmUtils.loginWithDefaults();
    }

    @Test
    public void testGetListItems() throws Exception {
        RestClient client = new RestClient();
        Resource listItemsResource = client.resource(AlmUtils.getDefaultProjectUri() + "list-items");
        cookieStorage.applyCookies(listItemsResource);
        ClientResponse listItemsResponse = listItemsResource
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        String content = listItemsResponse.getEntity(String.class);
        System.out.println(content);
        ListItemsResponse lists = new ObjectMapper().readValue(content, ListItemsResponse.class);
        System.out.println(lists.totalCount);
    }

    public static class ListItemsResponse {
        public List<ListItem> data;
        @JsonProperty("total-count")
        public int totalCount;
    }

    public static class ListItem {
        public String type;
        public Integer id;
        @JsonProperty("logical-identifier")
        public String logicalIdentifier;
        public String name;
        public Object parent;
    }
}
