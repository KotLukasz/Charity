package pl.coderslab.charity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.coderslab.charity.repository.*;


@RunWith(Suite.class)
@Suite.SuiteClasses({
		CharityApplicationTests.class,
		CategoryRepositoryTest.class,
		InstitutionRepositoryTest.class,
		DonationRepositoryTest.class,
		RoleRepositoryTest.class,
		UserRepositoryTest.class
})
public class AllTests {
}
