package me.lenycer.jpa;

import me.lenycer.jpa.simple.Company;
import me.lenycer.jpa.simple.CompanyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

	@Autowired
	CompanyService companyService;

	@Test
	@Transactional
	public void create() {
		Company company = new Company();
		company.setName("Test company");
		company.setLocation("Test location");

		Company result = companyService.createCompany(company);
		System.out.println(result);

		Assert.assertEquals(company, result);
	}

	@Test
	@Transactional
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

		Assert.assertEquals(list.size(), arr.length);
	}

}

