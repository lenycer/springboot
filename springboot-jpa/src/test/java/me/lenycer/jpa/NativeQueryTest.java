package me.lenycer.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.lenycer.jpa.manytoone.ManyToOneService;
import me.lenycer.jpa.manytoone.Member;
import me.lenycer.jpa.manytoone.Team;
import me.lenycer.jpa.nquery.NativeQueryDTO;
import me.lenycer.jpa.nquery.NativeQueryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueryTest {

	@Autowired
	NativeQueryService nativeQueryService;

	@Autowired
	ManyToOneService manyToOneService;

	@Test
	public void memberTest() throws JsonProcessingException {
		Team team1 = new Team();
		team1.setName("Member's Team 1");
		Team createTeam1 = manyToOneService.createTeam(team1);

		Member member1 = new Member();
		member1.setName("lenycer");
		member1.setAge(19);
		member1.setTeam(createTeam1);
		manyToOneService.createMember(member1);

		Team team2 = new Team();
		team2.setName("Member's Team 2");
		Team createTeam2 = manyToOneService.createTeam(team2);

		Member member2 = new Member();
		member2.setName("cvic");
		member2.setAge(22);
		member2.setTeam(createTeam2);
		manyToOneService.createMember(member2);

		Member member3 = new Member();
		member3.setName("trygan");
		member3.setAge(22);
		member3.setTeam(createTeam2);
		manyToOneService.createMember(member3);

		List<NativeQueryDTO> list = nativeQueryService.getNativeQuery(createTeam2.getId());
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
	}
}

