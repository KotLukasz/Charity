package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {


	@Query("SELECT COALESCE(SUM (d.quantity), 0) FROM Donation d")
	int sumQuantity();

	@Query("SELECT COALESCE(COUNT (d.institution), 0) FROM Donation d")
	int countInstitution();

	List<Donation> findAllByUser(User user);

}
