package demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoHttp2Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoHttp2ApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void testHttp2Connect() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://127.0.0.1:" + port + "/").build();
        Response response = client.newCall(request).execute();
        assertThat(response.protocol()).isEqualTo(Protocol.HTTP_2);
    }

}
