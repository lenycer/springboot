package me.lenycer.jpa.nquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a1100440 on 07/01/2019.
 */
@Service
public class NativeQueryService {

    @Autowired
    NativeRepository nativeRepository;

    public List<NativeQueryDTO> getNativeQuery(Long teamId) {
        return nativeRepository.nativeQuery(teamId);
    }
}
