package me.lenycer.jpa;

import me.lenycer.jpa.manytoone.ManyToOneService;
import me.lenycer.jpa.manytoone.Member;
import me.lenycer.jpa.manytoone.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneTest {

	@Autowired
	ManyToOneService manyToOneService;

	@Test
	public void create() {
		Team team = new Team();
		team.setName("My Team");

		manyToOneService.createTeam(team);
	}

	@Test
	public void getTeam() {
		Team team1 = new Team();
		team1.setName("My Team1");
		Team createTeam1 = manyToOneService.createTeam(team1);

		Team team2 = new Team();
		team2.setName("My Team2");

		Team createTeam2 = manyToOneService.createTeam(team2);

		Assert.assertEquals(team1.getName(), createTeam1.getName());
		Assert.assertEquals(team2.getName(), createTeam2.getName());

		List<Team> list = manyToOneService.getTeams();
		System.out.println(list);
	}

	@Test
	public void updateTeam() {
		Team team = new Team();
		team.setName("My Team");

		Team createTeam = manyToOneService.createTeam(team);

		Team updateTeam = manyToOneService.updateTeam(createTeam);

		Assert.assertEquals(updateTeam.getName(), "update team name");
	}

	@Test
	public void memberTest() {
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

		System.out.println(manyToOneService.getMember(member2.getId()));
		System.out.println(manyToOneService.getMembers());
	}
}

