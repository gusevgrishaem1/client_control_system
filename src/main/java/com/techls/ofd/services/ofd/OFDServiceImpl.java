package com.techls.ofd.services.ofd;

import com.techls.ofd.entities.OFD;
import com.techls.ofd.repositories.cashregister.CashRegisterRepository;
import com.techls.ofd.repositories.ofd.OFDRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OFDServiceImpl implements OFDService {
    private final OFDRepository ofdRepository;
    private final CashRegisterRepository cashRegisterRepository;

    @Override
    @Transactional
    public Long create(OFD ofd) {
        ofd.setArchive(false);
        return ofdRepository.save(ofd).getId();
    }

    @Override
    @Transactional
    public void update(OFD ofd) {
        ofdRepository.save(ofd);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cashRegisterRepository.findByOfd_Id(id).forEach(
                e -> {
                    e.setArchive(true);
                    cashRegisterRepository.save(e);
                }
        );
        ofdRepository.findById(id).ifPresent(
                ofd -> {
                    ofd.setArchive(true);
                    ofdRepository.save(ofd);
                }
        );
    }

    @Override
    @Transactional
    public void recover(Long id) {
        cashRegisterRepository.findByOfd_Id(id).forEach(
                e -> {
                    e.setArchive(false);
                    cashRegisterRepository.save(e);
                }
        );
        ofdRepository.findById(id).ifPresent(
                ofd -> {
                    ofd.setArchive(false);
                    ofdRepository.save(ofd);
                }
        );
    }

    @Override
    public List<OFD> getAll() {
        return ofdRepository.findAll();
    }

    @Override
    public List<OFD> getCombo() {
        return ofdRepository.findAll().stream()
                .filter(el -> !el.getArchive())
                .filter(el -> el.getTitle() != null)
                .toList();
    }
}
