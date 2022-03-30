package academy.devdojo.springboot2essentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExeceptionDetails{
    private final String fields;
    private final String fieldsMessage;
}
