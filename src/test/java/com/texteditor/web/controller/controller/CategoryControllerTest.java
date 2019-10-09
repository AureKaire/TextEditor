package com.texteditor.web.controller.controller;
import com.texteditor.lib.model.Category;
import com.texteditor.lib.service.CategoryService;
import com.texteditor.lib.service.CategoryServiceImpl;
import com.texteditor.lib.web.controller.CategoryController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)

public class CategoryControllerTest {
    private MockMvc mockMvc;


    @InjectMocks
    private CategoryService service = new CategoryServiceImpl();

    @InjectMocks
    private CategoryController controller;




    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void listCategory_ShouldGetListAndRedirect() throws Exception {

        List<Category> list = Arrays.asList(
                new Category(),
                new Category()
        );
        Mockito.when(service.findAll()).thenReturn(list);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("category/index"))
                .andExpect(model().attribute("categories", list));


    }

    @Test
    public void formNewCategory_ShouldAddModelAttributesForNewForm() throws Exception {

        mockMvc.perform(get("categories/add"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("category", new Category()))
                .andExpect(model().attribute("heading", "New Text"))
                .andExpect(model().attribute("submit", "Add"))
                .andExpect(redirectedUrl("category/form"));


    }
}







