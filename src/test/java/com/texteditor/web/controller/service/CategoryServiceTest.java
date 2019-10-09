package com.texteditor.web.controller.service;


import com.texteditor.lib.dao.CategoryDao;
import com.texteditor.lib.dao.CategoryDaoImpl;
import com.texteditor.lib.model.Category;
import com.texteditor.lib.service.CategoryService;
import com.texteditor.lib.service.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryDao dao = new CategoryDaoImpl();

    @InjectMocks
    private CategoryService service = new CategoryServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo() throws Exception{
        List<Category> list = Arrays.asList(
                new Category(),
                new Category()
        );
        when(dao.findAll()).thenReturn(list);
        assertEquals("findAll should return two categories", 2,service.findAll().size());
        verify(dao).findAll();

    }

    @Test
    public void findById_ShouldReturnOne(){
        when(dao.findById(1L)).thenReturn(new Category());
        assertThat(service.findById(1L),instanceOf(Category.class));
        verify(dao).findById(1L);
    }


}
