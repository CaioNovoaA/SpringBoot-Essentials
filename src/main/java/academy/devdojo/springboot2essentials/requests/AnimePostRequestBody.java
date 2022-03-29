package academy.devdojo.springboot2essentials.requests;

import academy.devdojo.springboot2essentials.domain.Anime;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class AnimePostRequestBody {
    private String name;

    @Deprecated
    public AnimePostRequestBody() {
    }
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)// Gera Json da propriedade quando tem apenas um campo
    public AnimePostRequestBody(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Anime toModel(){
        return new Anime(name);
    }
}