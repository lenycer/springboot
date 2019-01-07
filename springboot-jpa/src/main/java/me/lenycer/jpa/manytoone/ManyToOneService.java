package me.lenycer.jpa.manytoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Service
public class ManyToOneService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Transactional
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Transactional
    public Team updateTeam(Team team) {
        Team findTeam = this.getTeam(team.getId());
        findTeam.setName("update team name");

        return this.getTeam(team.getId());
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member updateMember(Member member) {
        Member findMember = this.getMember(member.getId());
        findMember.setName("update member name");

        return this.getMember(member.getId());
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
