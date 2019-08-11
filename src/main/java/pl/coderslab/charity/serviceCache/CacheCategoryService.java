package pl.coderslab.charity.serviceCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;

import java.util.List;

@Service
public class CacheCategoryService implements CategoryService {

	@Autowired
	@Qualifier("dbCategoryService")
	private CategoryService categoryService;

	@Cacheable("categories")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}

}
