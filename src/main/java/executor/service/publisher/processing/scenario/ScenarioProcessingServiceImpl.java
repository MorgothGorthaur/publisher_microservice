package executor.service.publisher.processing.scenario;

import executor.service.publisher.model.Scenario;
import executor.service.publisher.queue.scenario.ScenarioSourceQueueHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ScenarioProcessingServiceImpl implements ScenarioProcessingService {
    private final ScenarioSourceQueueHandler queueHandler;

    public ScenarioProcessingServiceImpl(ScenarioSourceQueueHandler queueHandler) {
        this.queueHandler = queueHandler;
    }

    @Override
    public void add(Scenario scenario) {
        queueHandler.add(scenario);
    }

    @Override
    public void addAll(List<Scenario> scenarios) {
        queueHandler.addAll(scenarios);
    }

    @Override
    public List<Scenario> removeByCount(int count) {
        return queueHandler.removeByCount(count);
    }

    @Override
    public Optional<Scenario> poll() {
        return queueHandler.poll();
    }

    @Override
    public List<Scenario> removeAll() {
        return queueHandler.removeAll();
    }
}
