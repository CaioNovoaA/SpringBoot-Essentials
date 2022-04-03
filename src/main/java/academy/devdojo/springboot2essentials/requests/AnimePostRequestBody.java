package academy.devdojo.springboot2essentials.requests;

import academy.devdojo.springboot2essentials.domain.Anime;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostRequestBody {
   @NotEmpty(message = "The anime name cannot be empty") @NotBlank(message = "the anime cannot be blank")
   private String name;
   private String url;

    @Deprecated
    public AnimePostRequestBody() {
    }
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)// Gera Json da propriedade quando tem apenas um campo
    public AnimePostRequestBody(String name, String url) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Anime toModel(){
        return new Anime(name);
    }
}