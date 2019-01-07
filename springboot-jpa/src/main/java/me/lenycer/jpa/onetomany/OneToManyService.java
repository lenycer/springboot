package me.lenycer.jpa.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Service
public class OneToManyService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    ParentRepository parentRepository;

    @Transactional
    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

    public Parent getParent(Long id) {
        return parentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Parent> getParents() {
        return parentRepository.findAll();
    }

    @Transactional
    public Parent updateParent(Parent parent) {
        Parent findParent = this.getParent(parent.getId());
        findParent.setName("update parent name");

        return this.getParent(parent.getId());
    }

    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }

    @Transactional
    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    public Child getChild(Long id) {
        return childRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Child> getChilds() {
        return childRepository.findAll();
    }

    @Transactional
    public Child updateChild(Child child) {
        Child findChild = this.getChild(child.getId());
        findChild.setName("update child name");

        return this.getChild(child.getId());
    }

    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }
}
