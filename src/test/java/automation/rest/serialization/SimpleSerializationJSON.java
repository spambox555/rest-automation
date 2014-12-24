package automation.rest.serialization;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author sergey.belonozhko@hp.com
 */
public class SimpleSerializationJSON {
    @Test
    public void testSimpleObjectSerialization() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("simple-person.json");
        Person person = new ObjectMapper().readValue(inputStream, Person.class);
        System.out.println(person);
        new ObjectMapper().writeValue(new FileOutputStream(""), person);
        new ObjectMapper().writeValueAsString(person);

    }

    public static class Person {
        public String name;
        public Integer age;
        public List<String> socialNetworkProfiles;


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", socialNetworkProfiles=" + socialNetworkProfiles +
                    '}';
        }
    }
}
