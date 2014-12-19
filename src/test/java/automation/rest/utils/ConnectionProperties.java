package automation.rest.utils;

import java.util.Properties;

/**
 * @author sergey.belonozhko@hp.com
 */
public class ConnectionProperties {
    private final Properties props;

    public ConnectionProperties(Properties props) {
        this.props = props;
    }

    public String getBaseDirUri() {
        return props.getProperty("test.server.baseDir");
    }

    public String getUserName() {
        return props.getProperty("test.user1.name");
    }

    public String getPassword() {
        return props.getProperty("test.user1.password");
    }

    public String getDomain() {
        return props.getProperty("test.project.domain");
    }

    public String getProject() {
        return props.getProperty("test.project.name");
    }
}
