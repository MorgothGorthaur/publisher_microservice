package executor.service.processing.service.scenario;

import executor.service.model.Scenario;
import executor.service.queue.producer.scenario.ScenarioProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
class ScenarioProcessingServiceImpl implements ScenarioProcessingService {
    private final ScenarioProducer producer;

    @Override
    public void add(Scenario scenario) {
        producer.add(scenario);
    }

    @Override
    public void addAll(List<Scenario> scenarios) {
        scenarios.forEach(producer::add);
    }

}
