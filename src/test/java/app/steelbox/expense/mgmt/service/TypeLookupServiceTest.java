package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.repository.TypeLookupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;

public class TypeLookupServiceTest {

    @Mock
    private TypeLookupRepository typeLookupRepository;

    @InjectMocks
    private TypeLookupService typeLookupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAll() {
        List<TypeLookup> typeList = List.of(createTypeLookup("Expense"), createTypeLookup("Income"));
        Mockito.when(typeLookupRepository.findAll()).thenReturn(typeList);

        List<TypeLookup> result = typeLookupService.fetchAll();

        Assert.notNull(result, "result is null");
        Assert.isTrue(result.size() == 2, "result size is incorrect");
    }

    @Test
    public void testFindById() {
        TypeLookup expenseType = createTypeLookup("Expense");
        Mockito.when(typeLookupRepository.findByType("Expense")).thenReturn(expenseType);

        TypeLookup result = typeLookupService.findByType("Expense");
        Assert.notNull(result, "result is null");
        Assert.isTrue(result.getType().equals(expenseType.getType()), "result type is incorrect");
    }

    private TypeLookup createTypeLookup(String type) {
        TypeLookup typeLookup = new TypeLookup();
        typeLookup.setId(new Random().nextInt());
        typeLookup.setType(type);
        typeLookup.setCreatedTS(System.currentTimeMillis());
        typeLookup.setUpdatedTS(System.currentTimeMillis());
        return typeLookup;
    }
}
