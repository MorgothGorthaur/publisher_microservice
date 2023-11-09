package executor.service.processing.controller.report;

import executor.service.model.ScenarioReport;
import executor.service.processing.service.report.ReportProcessingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReportSourceControllerImpl implements ReportSourceController {
    private final ReportProcessingService service;

    @Override
    public void add(ScenarioReport element) {
        service.add(element);
    }

    @Override
    public void addAll(List<ScenarioReport> elements) {
        service.addAll(elements);
    }

}
