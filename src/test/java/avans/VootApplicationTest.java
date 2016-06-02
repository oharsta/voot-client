package avans;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class VootApplicationTest {

  private OAuth2RestTemplate vootService;

  @Before
  public void before() throws IOException {
    Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("voot.properties"));

    ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
    details.setId("avans");
    details.setClientId(properties.getProperty("client_id"));
    details.setClientSecret(properties.getProperty("secret"));
    details.setAccessTokenUri(properties.getProperty("access_token_url"));
    details.setScope(singletonList("members"));

    this.vootService = new OAuth2RestTemplate(details);
  }

  @Test
  public void members() {
    String url = "https://voot.test.surfconext.nl/members/{group_urn}";
    List<Map<String, String>> rawMembers = vootService.getForObject(url, List.class, "urn:collab:group:test.surfteams.nl:nl:surfnet:diensten:bar");
    List<Member> members = rawMembers.stream().map(map -> new Member(map.get("id"), map.get("email"), map.get("name"))).collect(toList());
    assertEquals(7, members.size());
  }


}
