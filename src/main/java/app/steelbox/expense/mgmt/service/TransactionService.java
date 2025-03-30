package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.db.Transaction;
import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.model.enums.TransactionType;
import app.steelbox.expense.mgmt.model.view.TransactionDto;
import app.steelbox.expense.mgmt.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TypeLookupService typeLookupService;
    private final CategoryService categoryService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TypeLookupService typeLookupService,
                              CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.typeLookupService = typeLookupService;
        this.categoryService = categoryService;
    }

    public Transaction addTransaction(TransactionDto transactionDto) {

        // for now, it's always EXPENSE type
        TypeLookup typeLookup = typeLookupService.findByType(TransactionType.EXPENSE.getType());
        Category category = categoryService.findCategoryByName(transactionDto.getCategory());

        Transaction transaction = new Transaction();
        transaction.setCategory(category);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTypeId(typeLookup);
        transaction.setTimestamp(transactionDto.getTransactionTime());
        transaction.setDescription(transactionDto.getDescription());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
