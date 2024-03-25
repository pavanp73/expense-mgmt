package app.steelbox.expense.mgmt.resource;

import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.service.TypeLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type-lookup")
public class TypeLookupResource {

    private final TypeLookupService typeLookupService;

    @Autowired
    public TypeLookupResource(TypeLookupService typeLookupService) {
        this.typeLookupService = typeLookupService;
    }

    @GetMapping
    public List<TypeLookup> fetchAll() {
        return typeLookupService.fetchAll();
    }

    @PostMapping
    public List<TypeLookup> createTypeLookup(@RequestBody List<String> typeLookupList) {
        return typeLookupService.createTypeLookup(typeLookupList);
    }
}
