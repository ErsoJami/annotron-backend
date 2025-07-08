package ru.progzona.annotron.model.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.progzona.annotron.utils.validator.SizeOfElements;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RadioOutputConfig.class, name = "radio"),
        @JsonSubTypes.Type(value = CheckboxOutputConfig.class, name = "checkbox"),
        @JsonSubTypes.Type(value = TextOutputConfig.class, name = "text")
})
public abstract class OutputConfig extends Config {}

abstract class OptionsOutputConfig extends OutputConfig {
    @NotEmpty(message = "The list of options cannot be empty for the radio/checkbox.")
    @Size(min = 2, max = 20, message = "The parameters should be from 2 to 20.")
    @SizeOfElements(min = 1, max = 100, message = "The length of the parameters should be from 1 to 100 characters.")
    private List<String> options;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}

class RadioOutputConfig extends OptionsOutputConfig  {}

class CheckboxOutputConfig extends OptionsOutputConfig  {}

class TextOutputConfig extends OutputConfig {}



