package ru.progzona.annotron.model.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextInputConfig.class, name = "text"),
        @JsonSubTypes.Type(value = ImageInputConfig.class, name = "image")
})
public abstract class InputConfig extends Config {
    @NotBlank(message = "The component context cannot be empty.")
    @Size(min = 1, max = 1000, message = "The component context must be between 1 and 1000 characters long.")
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

class TextInputConfig extends InputConfig {}

class ImageInputConfig extends InputConfig {}