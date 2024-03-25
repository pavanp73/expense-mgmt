package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.repository.TypeLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeLookupService {

    private final TypeLookupRepository typeLookupRepository;

    @Autowired
    public TypeLookupService(TypeLookupRepository typeLookupRepository) {
        this.typeLookupRepository = typeLookupRepository;
    }

    public List<TypeLookup> fetchAll() {
        return typeLookupRepository.findAll();
    }

    public List<TypeLookup> createTypeLookup(List<String> typeLookupList) {
        return typeLookupRepository.saveAll(
                typeLookupList.stream().map(lookup -> {
                    TypeLookup typeLookup = new TypeLookup();
                    typeLookup.setType(lookup);
                    return typeLookup;})
                .collect(Collectors.toList())
        );
    }

    public TypeLookup findByType(String type) {
        return typeLookupRepository.findByType(type);
    }
}
