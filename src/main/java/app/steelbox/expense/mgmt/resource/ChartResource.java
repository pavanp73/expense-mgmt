package app.steelbox.expense.mgmt.resource;

import app.steelbox.expense.mgmt.model.shared.MonthlyChart;
import app.steelbox.expense.mgmt.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chart")
public class ChartResource {

    private final ChartService chartService;

    @Autowired
    public ChartResource(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping
    public MonthlyChart getMonthlyChart(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month) {
        return chartService.getMonthlyChart(month, year);
    }
}
