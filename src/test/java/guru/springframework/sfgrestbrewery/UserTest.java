package guru.springframework.sfgrestbrewery;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import guru.springframework.sfgrestbrewery.payload.request.LoginRequest;

@SpringBootTest
@AutoConfigureMockMvc
@EnableEncryptableProperties
class UserTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
		mapper = new ObjectMapper()
				.enable(SerializationFeature.INDENT_OUTPUT)
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	@Test
	String adminAuthenticateTest() throws Exception{

		String token = "";
		
		LoginRequest login = new LoginRequest();

		login.setUsername("gaetano");
		login.setPassword("admin1234");

		String jsonRequest = mapper.writeValueAsString(login);

		MvcResult result = mockMvc.perform(post("/api/auth/signin")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String actualJson = result.getResponse().getContentAsString();

		JSONArray array = new JSONArray("[" + actualJson + "]");
		for(int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			token = object.getString("token");
		}
		return token;
	}
	
	@Test
	String userAuthenticateTest() throws Exception{

		String token = "";
		
		LoginRequest login = new LoginRequest();

		login.setUsername("domenico");
		login.setPassword("user1234");

		String jsonRequest = mapper.writeValueAsString(login);

		MvcResult result = mockMvc.perform(post("/api/auth/signin")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String actualJson = result.getResponse().getContentAsString();

		JSONArray array = new JSONArray("[" + actualJson + "]");
		for(int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			token = object.getString("token");
		}
		return token;
	}
	
	@Test
	void testAdminGetUsers() throws Exception {
		String token = adminAuthenticateTest();
		mockMvc.perform(get("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	void testUserGetUsers() throws Exception {
		String token = userAuthenticateTest();
		mockMvc.perform(get("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	void testAdminGetUserByValidId() throws Exception{
		String token = adminAuthenticateTest();
		mockMvc.perform(get("/api/v1/users/user/{id}", 1)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	void testUserGetUserByValidId() throws Exception{
		String token = userAuthenticateTest();
		mockMvc.perform(get("/api/v1/users/user/{id}", 1)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isForbidden())
		.andReturn();
	}
	
	@Test
	void testAdminNotGetBeerByNotValidId() throws Exception{
		String token = adminAuthenticateTest();
		mockMvc.perform(get("/api/v1/users/user/{id}", 3)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andReturn();
	}
	
	@Test
	void testUserNotGetBeerByNotValidId() throws Exception{
		String token = userAuthenticateTest();
		mockMvc.perform(get("/api/v1/users/user/{id}", 3)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isForbidden())
		.andReturn();
	}
	
	@Test
	void testAdminGetUserFromRole() throws Exception{
		String token = adminAuthenticateTest();
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("role", "USER");
		mockMvc.perform(get("/api/v1/users/user-from-role")
				.params(paramsMap)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	void testUserGetUserFromRole() throws Exception{
		String token = userAuthenticateTest();
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("role", "USER");
		mockMvc.perform(get("/api/v1/users/user-from-role")
				.params(paramsMap)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isForbidden())
			.andReturn();
	}
	
	@Test
	void testAdminDeleteBeerByValidId() throws Exception {
		String token = adminAuthenticateTest();
		mockMvc.perform(delete("/api/v1/users/user/{id}", 3)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	void testUserDeleteBeerByValidId() throws Exception {
		String token = userAuthenticateTest();
		mockMvc.perform(delete("/api/v1/users/user/{id}", 3)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isForbidden())
		.andReturn();
	}
	
	@Test
	void testUnauthorizedGetUsers() throws Exception {
		mockMvc.perform(get("/api/v1/beers")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized())
		.andReturn();
	}
	
	@Test
	void testUnauthorizedGetUserByValidId() throws Exception{
		mockMvc.perform(get("/api/v1/users/user/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized())
		.andReturn();
	}
	
	@Test
	void testUnauthorizedNotGetBeerByNotValidId() throws Exception{
		mockMvc.perform(get("/api/v1/users/user/{id}", 3)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized())
		.andReturn();
	}
	
	@Test
	void testUnauthorizedGetUserFromRole() throws Exception{
		MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		paramsMap.add("role", "USER");
		mockMvc.perform(get("/api/v1/users/user-from-role")
				.params(paramsMap)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized())
			.andReturn();
	}
	
	@Test
	void testUnauthorizedDeleteBeerByValidId() throws Exception {
		mockMvc.perform(delete("/api/v1/users/user/{id}", 3)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized())
		.andReturn();
	}

}
