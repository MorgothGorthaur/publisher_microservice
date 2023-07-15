package executor.service.publisher.model;

import java.util.Objects;

public class StepDto {
    private String action;
    private String value;

    public StepDto() {
    }

    public StepDto(String action, String value) {
        this.action = action;
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepDto stepDto = (StepDto) o;

        if (!Objects.equals(action, stepDto.action)) return false;
        return Objects.equals(value, stepDto.value);
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StepDto{" +
                "action='" + action + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}