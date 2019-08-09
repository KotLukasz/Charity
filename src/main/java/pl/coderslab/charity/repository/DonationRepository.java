package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {


	@Query("SELECT COALESCE(SUM (d.quantity), 0) FROM Donation d")
	int sumQuantity();

	@Query("SELECT COALESCE(COUNT (d.institution), 0) FROM Donation d")
	int countInstitution();

	List<Donation> findAllByUser(User user);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Donation d set d.pickedUp = :pickedUp, d.pickedUpDate = :pickedUpDate where d.id = :id")
	void updateSetPickedUpAndPickedUpDate(@Param("id") Long id, @Param("pickedUp") boolean pickedUp, @Param("pickedUpDate") LocalDate pickedUpDate);

	@Query("SELECT d FROM Donation d WHERE d.user = :user ORDER BY d.pickedUp, d.pickedUpDate ASC")
	List<Donation>findAllByUserOrderByPickedUpPickedUpDateAsc (User user);

}
