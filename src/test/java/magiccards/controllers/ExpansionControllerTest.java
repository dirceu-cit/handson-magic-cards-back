package magiccards.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import magiccards.entities.Expansion;
import magiccards.factories.ExpansionFactory;
import magiccards.repositories.ExpansionRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(value = SpringRunner.class)
@WebMvcTest(controllers = {ExpansionController.class})
public class ExpansionControllerTest {

    private static final Integer EXPANSION_ID_1 = 1;

    @Value("${expansions.endpoint.url}")
    private String expansionsEndpoint;

    @Autowired
    private ExpansionController expansionController;

    @MockBean
    private ExpansionRepository expansionRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenIPostANewExpansion_ThenItShouldReturn200() throws Exception {
        Expansion expansion = ExpansionFactory.createExpansion();
        mockMvc.perform(post(expansionsEndpoint)
                .content(new ObjectMapper().writeValueAsBytes(expansion))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void whenIPutAnExpansion_ThenItShouldReturn200() throws Exception {
        Expansion expansion = ExpansionFactory.createExpansion();
        mockMvc.perform(put("/expansions")
                .content(new ObjectMapper().writeValueAsBytes(expansion))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void whenIDeleteAnExpansion_ThenItShouldReturn200() throws Exception {
        Expansion expansion = ExpansionFactory.createExpansion();
        expansionController.create(expansion);
        mockMvc.perform(delete("/expansions/" + EXPANSION_ID_1)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().is(200));
    }


    @Test
    public void WhenIGetAnExpansionById_ThenItShouldReturnTheExpansion() throws Exception {
        Expansion expansion = ExpansionFactory.createExpansion();
        expansion.setExpansionId(EXPANSION_ID_1);
        when(expansionRepository.findOne(EXPANSION_ID_1)).thenReturn(expansion);

        mockMvc.perform(get("/expansions/" + EXPANSION_ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expansionId", is(EXPANSION_ID_1)))
                .andExpect(jsonPath("$.name", is("Expansion 1")))
                .andExpect(jsonPath("$.ptBrName", is("Expansão 1")))
                .andExpect(jsonPath("$.linkName", is("Expansion Link Name 1")))
                .andExpect(jsonPath("$.expansionCategoryId", is(1)))
                .andExpect(jsonPath("$.isLegal", is(true)))
                .andExpect(jsonPath("$.isPromo", is(true)))
                .andReturn();
    }

    @Test
    public void whenIGetPagedExpansions_ThenThePaginationContentSizeShouldMatch() throws Exception {
        Expansion expansion1 = ExpansionFactory.createExpansion();
        Expansion expansion2 = ExpansionFactory.createExpansion();
        Expansion expansion3 = ExpansionFactory.createExpansion();

        List<Expansion> expansions = Lists.newArrayList(expansion1, expansion2, expansion3);

        Page<Expansion> page = new PageImpl<>(expansions, new PageRequest(0, 5), expansions.size());

        when(expansionRepository.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/expansions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andReturn();
    }

}
