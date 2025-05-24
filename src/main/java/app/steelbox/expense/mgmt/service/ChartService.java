package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.shared.ChartData;
import app.steelbox.expense.mgmt.model.shared.DateRange;
import app.steelbox.expense.mgmt.model.shared.MonthlyChart;
import app.steelbox.expense.mgmt.model.view.TransactionDto;
import app.steelbox.expense.mgmt.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChartService {

    private final TransactionService transactionService;

    @Autowired
    public ChartService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public MonthlyChart getMonthlyChart(String month, int year) {
        DateRange dateRange = DateUtil.getDateRange(month, year);
        List<TransactionDto> transactionDtoList = transactionService.getTransactionsForAMonth(dateRange);
        MonthlyChart monthlyChart = new MonthlyChart();
        monthlyChart.setMonth(month);
        monthlyChart.setYear(year);

        Map<String, ChartData> chartDataMap = transactionDtoList.stream()
                .collect(Collectors.toMap(
                        TransactionDto::getCategory,
                        transactionDto -> {
                            ChartData chartData = new ChartData();
                            chartData.setCategory(transactionDto.getCategory());
                            chartData.setTotalAmount(transactionDto.getAmount());
                            chartData.setTransactionsCount(1);
                            return chartData;
                        },
                        (oldData, newData) -> {
                            oldData.setTotalAmount(oldData.getTotalAmount() + newData.getTotalAmount());
                            oldData.setTransactionsCount(oldData.getTransactionsCount() + 1);
                            return oldData;
                        }
                ));

        double totalAmount = transactionDtoList.stream()
                .mapToDouble(TransactionDto::getAmount).sum();

        monthlyChart.setTotal(totalAmount);
        monthlyChart.setCategoryWiseData(chartDataMap.values().stream().toList());
        return monthlyChart;
    }

}
