package me.lenycer.jpa;

import me.lenycer.jpa.onetomany.Child;
import me.lenycer.jpa.onetomany.OneToManyService;
import me.lenycer.jpa.onetomany.Parent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTest {

	@Autowired
	OneToManyService oneToManyService;

	@Test
	@Transactional
	public void childTest() {
		Parent parent1 = new Parent();
		parent1.setName("Child's Parent 1");
		Parent createParent1 = oneToManyService.createParent(parent1);

		String[] arr1 = {"lenycer", "sic", "seo"};
		Arrays.stream(arr1).forEach(s -> {
			Child child1 = new Child();
			child1.setName(s);
			child1.setAge(19);
			child1.setParentId(createParent1.getId());
			oneToManyService.createChild(child1);
		});

		Parent parent2 = new Parent();
		parent2.setName("Child's Parent 2");
		Parent createParent2 = oneToManyService.createParent(parent2);

		String[] arr2 = {"cvic", "jin", "park"};
		Arrays.stream(arr2).forEach(s -> {
			Child child2 = new Child();
			child2.setName(s);
			child2.setAge(22);
			child2.setParentId(createParent2.getId());
			oneToManyService.createChild(child2);
		});

		Parent parent = oneToManyService.getParent(parent1.getId());
		System.out.println(parent);
//		System.out.println(oneToManyService.getParents());
	}
}

