package pl.coderslab.charity.serviceCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;

@Service
public class DbCategoryService  implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategories(){
		System.out.println("------------> pobieram Kategorie");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return categoryRepository.findAll();
	}

}
