package com.techls.ofd.services.ofd;

import com.techls.ofd.entities.OFD;

import java.util.List;

public interface OFDService {
    Long create(OFD ofd);

    void update(OFD ofd);

    void delete(Long id);

    List<OFD> getAll();

    List<OFD> getCombo();
}
