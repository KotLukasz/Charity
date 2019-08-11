package pl.coderslab.charity.serviceCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class DbInstitutionService implements InstitutionService {

	@Autowired
	private InstitutionRepository institutionRepository;

	public List<Institution> getAllInstitutions() {
		System.out.println("------------> pobieram Instytucje");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return institutionRepository.findAll();
	}


}
