package dhbw.online.bookly;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dhbw.online.bookly.dto.Page;
import org.junit.Assert;
import org.keycloak.OAuth2Constants;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class Stepdefs {

    private String getUser = "{\"username\":\"test-user\",\"mail\":\"max.mustermann@test.bookly.online\",\"first_name\":\"Max\",\"last_name\":\"Mustermann\"}";
    private String getFriendshipbook = "{\"uuid\":1,\"title\":\"Unser super tolles Buch\",\"user\":{\"username\":\"test-user\",\"mail\":\"max.mustermann@test.bookly.online\",\"first_name\":\"Max\",\"last_name\":\"Mustermann\"},\"pages\":[{\"uuid\":2,\"name\":\"Max Mustermann\",\"address\":\"TeststraÃ\u009Fe 12\",\"telephone\":\"555555\",\"mobile\":\"123456\",\"school_class\":\"1A\",\"school\":\"Driving School\",\"size\":\"1.8m\",\"hair_color\":\"blond\",\"eye_color\":\"green\",\"birthday\":null,\"star_sign\":null,\"favorite_subject\":null,\"favorite_pet\":null,\"how_to_please_me\":null,\"what_i_dont_like\":\"learning\",\"favorite_job\":null,\"my_hobbies\":\"Jogging, Dancing, Programming\",\"fan_of\":null,\"favorite_movie\":\"Star Trek, Lilo and Stitch\",\"favorite_sport\":null,\"favorite_book\":null,\"favorite_food\":\"Spaghetti\",\"nice_comment\":null,\"date\":null,\"leftOver\":\"My last words are CHOCOLATE\"},{\"uuid\":3,\"name\":\"Maximila Musterfrau\",\"address\":\"TeststraÃ\u009Fe 12\",\"telephone\":\"012345\",\"mobile\":\"23412313\",\"school_class\":\"1A\",\"school\":\"MIT\",\"size\":\"1.8m\",\"hair_color\":\"blond\",\"eye_color\":\"green\",\"birthday\":null,\"star_sign\":null,\"favorite_subject\":null,\"favorite_pet\":null,\"how_to_please_me\":null,\"what_i_dont_like\":\"learning\",\"favorite_job\":\"Chillen\",\"my_hobbies\":\"Jogging, Dancing, Programming\",\"fan_of\":\"Doing nothing\",\"favorite_movie\":null,\"favorite_sport\":null,\"favorite_book\":\"1984\",\"favorite_food\":null,\"nice_comment\":\"Hi Neeeko :) Du kannst mich lesen\",\"date\":null,\"leftOver\":\"My last words are CHOCOLATE\"}]}";
    private String getPage = "[{\"uuid\":2,\"name\":\"Max Mustermann\",\"address\":\"TeststraÃ\u009Fe 12\",\"telephone\":\"555555\",\"mobile\":\"123456\",\"school_class\":\"1A\",\"school\":\"Driving School\",\"size\":\"1.8m\",\"hair_color\":\"blond\",\"eye_color\":\"green\",\"birthday\":null,\"star_sign\":null,\"favorite_subject\":null,\"favorite_pet\":null,\"how_to_please_me\":null,\"what_i_dont_like\":\"learning\",\"favorite_job\":null,\"my_hobbies\":\"Jogging, Dancing, Programming\",\"fan_of\":null,\"favorite_movie\":\"Star Trek, Lilo and Stitch\",\"favorite_sport\":null,\"favorite_book\":null,\"favorite_food\":\"Spaghetti\",\"nice_comment\":null,\"date\":null,\"leftOver\":\"My last words are CHOCOLATE\"},{\"uuid\":3,\"name\":\"Maximila Musterfrau\",\"address\":\"TeststraÃ\u009Fe 12\",\"telephone\":\"012345\",\"mobile\":\"23412313\",\"school_class\":\"1A\",\"school\":\"MIT\",\"size\":\"1.8m\",\"hair_color\":\"blond\",\"eye_color\":\"green\",\"birthday\":null,\"star_sign\":null,\"favorite_subject\":null,\"favorite_pet\":null,\"how_to_please_me\":null,\"what_i_dont_like\":\"learning\",\"favorite_job\":\"Chillen\",\"my_hobbies\":\"Jogging, Dancing, Programming\",\"fan_of\":\"Doing nothing\",\"favorite_movie\":null,\"favorite_sport\":null,\"favorite_book\":\"1984\",\"favorite_food\":null,\"nice_comment\":\"Hi Neeeko :) Du kannst mich lesen\",\"date\":null,\"leftOver\":\"My last words are CHOCOLATE\"}]";
    private String newPage = "{\"uuid\":3,\"name\":\"Maximila Musterfrau\",\"address\":\"TeststraÃ\u009Fe 12\",\"telephone\":\"012345\",\"mobile\":\"23412313\",\"school_class\":\"1A\",\"school\":\"MIT\",\"size\":\"1.8m\",\"hair_color\":\"blond\",\"eye_color\":\"green\",\"birthday\":null,\"star_sign\":null,\"favorite_subject\":null,\"favorite_pet\":null,\"how_to_please_me\":null,\"what_i_dont_like\":\"learning\",\"favorite_job\":\"Chillen\",\"my_hobbies\":\"Jogging, Dancing, Programming\",\"fan_of\":\"Doing nothing\",\"favorite_movie\":null,\"favorite_sport\":null,\"favorite_book\":\"1984\",\"favorite_food\":null,\"nice_comment\":\"Hi Neeeko :) Du kannst mich lesen\",\"date\":null,\"leftOver\":\"My last words are CHOCOLATE\"}";
    private OAuth2RestTemplate oAuth2RestTemplate;

    private RestTemplate login(String username, String password) {
        String url = "https://keycloak.bookly.online/auth/realms/bookly/protocol/openid-connect/token";

        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setGrantType(OAuth2Constants.PASSWORD);
        resourceDetails.setAccessTokenUri(url);
        resourceDetails.setClientId("bookly-app");
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);

        this.oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
        return oAuth2RestTemplate;
    }

    @Given("I login with {string}{string}")
    public void i_am_logged_in(String username, String password) throws Exception {
        RestTemplate restTemplate = login(username, password);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:8080/api/user", HttpMethod.GET, request, String.class, new LinkedMultiValueMap<>());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode()); //a restricted request should be accessible
    }

    @Given("^I navigate to friendship book$")
    public void i_navigate_to_friendship_book_cover() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity<String> response = oAuth2RestTemplate
                .exchange("http://localhost:8080/api/friendshipbook", HttpMethod.GET, request, String.class, new LinkedMultiValueMap<>());
        Assert.assertEquals(getFriendshipbook, response.getBody());
    }

    @When("^User friendship book cover is loaded$")
    public void user_friendship_book_cover_is_loaded() throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        i_am_able_to_create_a_new_cover();//to ensure that there is a picture loaded
        ResponseEntity response = oAuth2RestTemplate.exchange("http://localhost:8080/api/friendshipbook/image", HttpMethod.GET, request, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Then("^I delete friendship book cover$")
    public void i_select_delete_friendship_book_cover() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity response = oAuth2RestTemplate.exchange("http://localhost:8080/api/friendshipbook/image", HttpMethod.DELETE, request, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Then("^The page should be refreshed$")
    public void the_page_should_be_refreshed() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity response = oAuth2RestTemplate.exchange("http://localhost:8080/api/page", HttpMethod.GET, request, String.class);
        Assert.assertEquals(getPage, response.getBody());
    }

    @Then("^I can create a friendship book cover$")
    public void i_am_able_to_create_a_new_cover() throws IOException {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        final String filename = "test_image.jpg";
        ByteArrayResource contentsAsResource = new ByteArrayResource(extractBytes(filename)) {
            @Override
            public String getFilename() {
                return filename;
            }
        };
        map.add("file", contentsAsResource);

        ResponseEntity<String> response = oAuth2RestTemplate.postForEntity("http://localhost:8080/api/friendshipbook/image", map, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    public byte[] extractBytes(String imageName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(imageName);
        // open image
        assert resource != null;
        File imgPath = new File(resource.getFile());
        return Files.readAllBytes(imgPath.toPath());
    }

    @Given("^I navigate to profile$")
    public void i_navigate_to_profile() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity<String> response = oAuth2RestTemplate
                .exchange("http://localhost:8080/api/user", HttpMethod.GET, request, String.class, new LinkedMultiValueMap<>());
        Assert.assertEquals(getUser, response.getBody());
    }

    @Given("^Friendship book remaining pages not null$")
    public void friendship_book_remaining_pages_not_null() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity response = oAuth2RestTemplate.exchange("http://localhost:8080/api/page", HttpMethod.GET, request, String.class);
        Assert.assertEquals(getPage, response.getBody());
    }

    @When("User pages are loaded")
    public void user_pages_are_loaded() {
        the_page_should_be_refreshed();//the same
    }

    @When("I select delete page")
    public void i_select_delete_page() {
        int uuid = createPage();
        deletePage(uuid);//delete this specific page
    }

    @Then("I am not able to see the deleted page")
    public void i_am_not_able_to_see_the_deleted_page() {
        int uuid = createPage();
        deletePage(uuid);

        ResponseEntity<Page[]> response = oAuth2RestTemplate.getForEntity("http://localhost:8080/api/page", Page[].class);
        Assert.assertNotNull(response.getBody());
        boolean status = true;
        for (Page page : response.getBody()) {
            if (page.getUuid() == uuid) {
                status = false; //there should be no page with this uuid
                break;
            }
        }
        Assert.assertTrue(status);
    }

    @When("I select add entry")
    public void i_select_add_entry() {
        int uuid = createPage();
        deletePage(uuid);
    }

    public void deletePage(int uuid) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);
        ResponseEntity response = oAuth2RestTemplate.exchange("http://localhost:8080/api/page?uuid=" + uuid, HttpMethod.DELETE, request, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    public int createPage() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity<Page> response = oAuth2RestTemplate.postForEntity("http://localhost:8080/api/page", request, Page.class);
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().getUuid() > 0); //there should be a uuid
        return response.getBody().getUuid();
    }

    @Then("I can edit the public page")
    public void i_can_edit_the_page() {
        /*Edit new page*/
        Page page = new Page();
        page.setUuid(2);
        page.setName("Ein Edit Test");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Page> entity = new HttpEntity<>(page, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity newResponse = restTemplate.exchange("http://localhost:8080/api/public/page", HttpMethod.POST, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, newResponse.getStatusCode());
    }

    @Then("The response is the test user")
    public void theResponseIsTheTestUser() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);

        ResponseEntity<String> response = oAuth2RestTemplate
                .exchange("http://localhost:8080/api/user", HttpMethod.GET, request, String.class, new LinkedMultiValueMap<>());
        Assert.assertEquals(getUser, response.getBody());
    }

    @Then("I can copy the uuid for sharing the page")
    public void iCanCopyTheUuidForSharingThePage() {
        int uuid = createPage();//it is possible to create a page and later on sharing
        deletePage(uuid);//delete page for clean testing
    }

    @When("I go to the public page")
    public void iGoToThePublicPage() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange("http://localhost:8080/api/public/page?uuid=3", HttpMethod.GET, request, String.class);
        Assert.assertEquals(newPage, response.getBody());
    }

}
