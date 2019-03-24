package me.lenycer.jpa.manytoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lenycer on 24/03/2019.
 */
@RestController
public class ManyToOneController {

    @Autowired
    ManyToOneService manyToOneService;

    @RequestMapping("/test/mto")
    public void createMemberTest() {
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
    }

    @RequestMapping("/member/{id}")
    public Member getMember(@PathVariable Long id) {
        return manyToOneService.getMember(id);
    }

    @RequestMapping("/member")
    public List<Member> getMember() {
        return manyToOneService.getMembers();
    }
}
