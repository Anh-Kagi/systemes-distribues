package fr.polytech.projet.naturalthescattering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class NaturalTheScatteringApplicationTests {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void fullTest() throws Exception {
		MockHttpSession session = new MockHttpSession();
		
		mvc.perform(MockMvcRequestBuilders.post("/api/auth/register").session(session).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", TestConfig.username).param("password", TestConfig.password))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(""))
			.andDo(MockMvcResultHandlers.log());
		
		mvc.perform(MockMvcRequestBuilders.post("/api/auth/logout").session(session))
			.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"))
			.andDo(MockMvcResultHandlers.log());
		
		session = new MockHttpSession();
		
		mvc.perform(MockMvcRequestBuilders.post("/api/auth/login").session(session).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", TestConfig.username).param("password", TestConfig.password))
			.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/"))
			.andDo(MockMvcResultHandlers.log());
		
		mvc.perform(MockMvcRequestBuilders.post("/api/booster/open").session(session))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(""))
			.andExpect(MockMvcResultMatchers.jsonPath("$.booster").isArray())
			.andDo(MockMvcResultHandlers.log());
		
		mvc.perform(MockMvcRequestBuilders.post("/api/forum/thread").session(session))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$/.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(""))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
			.andDo(MockMvcResultHandlers.log());
		
		String passwd = "thisisanotherpassword";
		
		mvc.perform(MockMvcRequestBuilders.put("/api/self/mdp").session(session).param("old", TestConfig.password).param("new", passwd))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(""))
			.andDo(MockMvcResultHandlers.log());
		
		mvc.perform(MockMvcRequestBuilders.put("/api/self/mdp").session(session).param("old", passwd).param("new", TestConfig.password))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(""))
			.andDo(MockMvcResultHandlers.log());
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/self").session(session).param("mdp", TestConfig.password))
			.andExpect(MockMvcResultMatchers.status().isResetContent())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value(""))
			.andDo(MockMvcResultHandlers.log());
	}
}