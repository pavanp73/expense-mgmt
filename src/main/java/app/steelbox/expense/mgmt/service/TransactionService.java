package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.db.PaymentMethod;
import app.steelbox.expense.mgmt.model.db.Transaction;
import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.model.enums.PayMethod;
import app.steelbox.expense.mgmt.model.enums.Type;
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
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TypeLookupService typeLookupService,
                              CategoryService categoryService,
                              PaymentMethodService paymentMethodService) {
        this.transactionRepository = transactionRepository;
        this.typeLookupService = typeLookupService;
        this.categoryService = categoryService;
        this.paymentMethodService = paymentMethodService;
    }

    public Transaction addTransaction(TransactionDto transactionDto) {

        // for now, it's always EXPENSE type
        TypeLookup typeLookup = typeLookupService.findByType(Type.EXPENSE.getType());
        PaymentMethod paymentMethod = paymentMethodService.findByPaymentMethod(transactionDto.getPaymentMethod());
        Category category = categoryService.findCategoryByName(transactionDto.getCategory());

        Transaction transaction = new Transaction();
        transaction.setCategory(category);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setPaymentMethodId(paymentMethod);
        transaction.setTypeId(typeLookup);
        transaction.setTimestamp(transactionDto.getTransactionTime());
        transaction.setDescription(transactionDto.getDescription());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
