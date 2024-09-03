package com.techls.ofd.services.model;

import com.techls.ofd.entities.Model;
import com.techls.ofd.repositories.cashregister.CashRegisterRepository;
import com.techls.ofd.repositories.model.ModelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final CashRegisterRepository cashRegisterRepository;

    @Override
    @Transactional
    public Long create(Model model) {
        return modelRepository.save(model).getId();
    }

    @Override
    @Transactional
    public void update(Model model) {
        modelRepository.save(model);
    }

    @Override
    @Transactional
    public void archive(Long id) {
        cashRegisterRepository.findByModel_Id(id).forEach(
                cashRegister -> {
                    cashRegister.setArchive(true);
                    cashRegisterRepository.save(cashRegister);
                }
        );
        modelRepository.findById(id).ifPresent(
                model -> {
                    model.setArchive(true);
                    modelRepository.save(model);
                }
        );
    }

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> getCombo() {
        return modelRepository.findAll().stream()
                .filter(el -> !el.getArchive())
                .filter(el -> el.getTitle() != null).toList();
    }
}
