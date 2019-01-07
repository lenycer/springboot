package me.lenycer.jpa;

import me.lenycer.jpa.simple.Company;
import me.lenycer.jpa.simple.CompanyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

	@Autowired
	CompanyService companyService;

	@Test
	public void create() {
		Company company = new Company();
		company.setName("Test company");
		company.setLocation("Test location");

		Company result = companyService.createCompany(company);
		System.out.println(result);

		Assert.assertEquals(company, result);
	}

	@Test
	public void findAll() {
		String[] arr = {"A", "B", "C"};
		Arrays.stream(arr).forEach(s -> {
			Company company = new Company();
			company.setName(s);
			company.setLocation("location");
			companyService.createCompany(company);
		});

		List<Company> list = companyService.getCompanies();
		System.out.println(list);
	}


	@Test
	public void update() {
		Company company = new Company();
		company.setName("Test company");
		company.setLocation("Test location");

		Company result = companyService.createCompany(company);
		System.out.println(result);

		companyService.update(result);

		Company findCompany = companyService.getCompany(result.getId());

		System.out.println(findCompany);
		Assert.assertEquals("updateLocation", findCompany.getLocation());
	}

	@Test(expected = RuntimeException.class)
	public void delete() {
		Company company = new Company();
		company.setName("Test company");
		company.setLocation("Test location");

		Company result = companyService.createCompany(company);

		companyService.delete(result.getId());

		companyService.getCompany(result.getId());
	}
}

