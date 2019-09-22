package pl.coderslab.charity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Institution;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstitutionRepositoryTest {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Test
	public void givenName_whenFindByName_thenInstitutionNotNull() {
		//given
		String name = "Fundacja \\“Bez domu\\”";
		//when
		Institution institution = institutionRepository.findByName(name);
		//then
		assertNotNull(institution);
	}

	@Test
	@Transactional
	public void givenNameAndDescriptionAndId_thenUpdateInstitutionById_thenInstitutionUpdated() {
		//given
		Long id = institutionRepository.findByDescription("Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania").getId();
		String name = "Instytucja";
		String description = "Dla ubogich";
		//when
		institutionRepository.updateSetNameAndAndDescription(id, name, description);
		Institution institutionFromRepository = institutionRepository.getOne(id);
		//then
		assertThat(name, is(institutionFromRepository.getName()));
		assertThat(description, is(institutionFromRepository.getDescription()));
	}
}