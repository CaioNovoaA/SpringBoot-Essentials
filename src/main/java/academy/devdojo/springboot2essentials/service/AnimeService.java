package academy.devdojo.springboot2essentials.service;

import academy.devdojo.springboot2essentials.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    public List<Anime> ListAll(){
    return List.of(new Anime(1L ,"Co "), new Anime(2L,"Beker"), new Anime(3L,"Josias do Baile"));
    }
}
