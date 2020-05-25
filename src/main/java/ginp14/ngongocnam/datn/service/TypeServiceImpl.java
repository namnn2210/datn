package ginp14.ngongocnam.datn.service;

import ginp14.ngongocnam.datn.dao.TypeRepository;
import ginp14.ngongocnam.datn.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }


    @Override
    public Type findById(int id) {
        return typeRepository.findById(id);
    }

    @Override
    public void save(Type team) {
        typeRepository.save(team);
    }

    @Override
    public List<Type> findAllByCategoryId(int id) {
        return typeRepository.findAllByCategoryId(id);
    }

    @Override
    public Page<Type> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
}
