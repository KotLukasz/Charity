package pl.coderslab.charity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonationRepositoryTest {

	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Test
	public void givenDonations_whenCountDonationsByQuantity_thenCountQuantityProvided() {
		//given

		//when

		//then

	}

	@Test
	public void givenDonations_whenCountDonationsByInstitutions_thenCountInstitutionsProvided() {
		//given

		//when

		//then
	}

	@Test
	public void givenUser_whenFindDonationsByUser_thenUserDonationsProvided() {
		//given

		//when

		//then
	}

	@Test
	public void givenPickedUpAndPickedUpdate_whenUpdateDonations_thenDonationIsUpdated() {
		//given

		//when

		//then
	}

	@Test
	public void givenUser_whenFindDonationsByUser_thenDonationsOrderByPickedUpAndPickedUpDateAsc() {
		//given

		//when

		//then
	}
}