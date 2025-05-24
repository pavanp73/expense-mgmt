package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.db.Transaction;
import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.model.shared.TransactionType;
import app.steelbox.expense.mgmt.model.shared.DateRange;
import app.steelbox.expense.mgmt.model.view.TransactionDto;
import app.steelbox.expense.mgmt.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TypeLookupService typeLookupService;
    private final CategoryService categoryService;

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TypeLookupService typeLookupService,
                              CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.typeLookupService = typeLookupService;
        this.categoryService = categoryService;
    }

    public TransactionDto addTransaction(TransactionDto transactionDto) throws ParseException {

        // for now, it's always EXPENSE type
        TypeLookup typeLookup = typeLookupService.findByType(TransactionType.EXPENSE.getType());
        Category category = categoryService.findCategoryByName(transactionDto.getCategory());

        Transaction transaction = new Transaction();
        transaction.setCategory(category);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTypeId(typeLookup);
        transaction.setTimestamp(DATE_FORMAT.parse(transactionDto.getTransactionDate()).getTime());
        transaction.setDescription(transactionDto.getDescription());

        transaction = transactionRepository.save(transaction);
        return mapToDto(transaction);
    }

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapToDto).toList();
    }

    List<TransactionDto> getTransactionsForAMonth(DateRange dateRange) {
        List<Transaction> transactionsForMonth = transactionRepository
                .findByTimestampBetween(dateRange.getStartDate(), dateRange.getEndDate());
        return transactionsForMonth.stream().map(this::mapToDto).toList();
    }

    private TransactionDto mapToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setTransactionDate(DATE_FORMAT.format(transaction.getTimestamp()));
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setCategory(transaction.getCategory().getName());
        transactionDto.setTransactionType(transaction.getCategory().getTypeLookup().getType());
        transactionDto.setPaymentMethod(transactionDto.getPaymentMethod());
        return transactionDto;
    }
}
