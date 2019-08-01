package pl.coderslab.charity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.service.CategoryService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharityApplicationTests {

    @Autowired
    @Qualifier("cacheCategoryService")
    private CategoryService categoryService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void oi(){
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        categoryService.getAllCategories().size();
        assertEquals(3, categoryService.getAllCategories().size());
    }

}
