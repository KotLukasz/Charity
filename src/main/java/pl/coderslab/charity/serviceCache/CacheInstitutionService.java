package pl.coderslab.charity.serviceCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;

import java.util.List;

@Service
public class CacheInstitutionService implements InstitutionService {

	@Autowired
	@Qualifier("dbInstitutionService")
	private InstitutionService institutionService;

	@Cacheable("institutions")
	public List<Institution> getAllInstitutions() {
		return institutionService.getAllInstitutions();
	}

}
