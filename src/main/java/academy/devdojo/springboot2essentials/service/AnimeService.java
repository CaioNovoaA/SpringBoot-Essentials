package academy.devdojo.springboot2essentials.service;

import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private static List<Anime> animes;
    private final AnimeRepository animeRepository;

    static {
           animes = new ArrayList<>(List.of(new Anime(1L, "Co "), new Anime(2L, "Beker")));
    }
    public List<Anime> ListAll(){
    return animes;
    }
    public Anime findById(Long id){
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(Anime anime) {
        animeRepository.save(anime);
         return anime;
    }
    public void delete(Long id){
        animes.remove(findById(id));
    }

    public void replace(Anime anime) {
        delete(anime.getId());
    }
}
