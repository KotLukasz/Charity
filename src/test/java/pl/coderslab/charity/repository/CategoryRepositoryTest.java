package pl.coderslab.charity.repository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.entity.Category;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	private static Category category;
	private final static String BOOK_CATEGORY_NAME = "Book";

	@BeforeClass
	public static void categorySample () {
		category = new Category();
		category.setName(BOOK_CATEGORY_NAME);
	}

	@Test
	public void whenAddingCategory_thenCategoryIsAdded() {
		//given
		categoryRepository.save(category);
		//when
		Category categoryFromRepository = categoryRepository.findByName(BOOK_CATEGORY_NAME);
		//then
		assertEquals(category, categoryFromRepository);
	}


	@Test
	public void whenDeletingCategory_thenCategoryIsDeleted() {
		//given
		//when
		categoryRepository.save(category);
		categoryRepository.delete(category);
		Category categoryFromRepository = categoryRepository.findByName(BOOK_CATEGORY_NAME);
		//then
		assertNull(categoryFromRepository);
	}

}