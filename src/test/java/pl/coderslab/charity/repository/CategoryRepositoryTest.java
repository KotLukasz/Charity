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

	private static Category book;

	@BeforeClass
	public static void categorySample () {
		book = new Category();
		book.setName("Book");
	}

	@Test
	public void whenAddingCategory_thenCategoryIsAdded() {
		//given
		//when
		categoryRepository.save(book);
		//then
		assertEquals(categoryRepository.findByName("Book").getName(), book.getName());
		assertEquals(categoryRepository.findByName("zabawki").getName(),"zabawki");
	}


	@Test(expected = NullPointerException.class)
	public void whenDeletingCategory_thenCategoryIsDeleted() {
		//given
		//when
		categoryRepository.delete(book);
		categoryRepository.delete(categoryRepository.findByName("zabawki"));
		//then
		assertEquals(categoryRepository.findByName("Book").getName(), book.getName());
		assertEquals(categoryRepository.findByName("zabawki").getName(),"zabawki");
	}

}