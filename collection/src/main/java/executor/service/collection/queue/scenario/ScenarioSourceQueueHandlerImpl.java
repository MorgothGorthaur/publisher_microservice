package executor.service.collection.queue.scenario;

import executor.service.collection.queue.QueueHandler;
import executor.service.logger.annotation.Logged;
import executor.service.model.Scenario;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Logged
@RequiredArgsConstructor
public class ScenarioSourceQueueHandlerImpl implements ScenarioSourceQueueHandler {
    private final QueueHandler<Scenario> handler;
    @Override
    public void add(Scenario scenario) {
        setUUID(scenario);
        handler.add(scenario);
    }
    @Override
    public void addAll(List<Scenario> scenarios) {
        scenarios.forEach(this::setUUID);
        handler.addAll(scenarios);
    }

    @Override
    public Optional<Scenario> poll() {
        return handler.poll();
    }

    @Override
    public List<Scenario> removeAll() {
        return handler.removeAll();
    }

    @Override
    public List<Scenario> removeByCount(int size) {
        return handler.removeByCount(size);
    }

    private void setUUID(Scenario scenario) {
        scenario.setUUID(UUID.fromString(scenario.toString()));
    }
}

