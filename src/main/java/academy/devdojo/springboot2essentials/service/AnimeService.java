package academy.devdojo.springboot2essentials.service;

import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.exception.BadRequestException;
import academy.devdojo.springboot2essentials.mapper.AnimeMapper;
import academy.devdojo.springboot2essentials.repository.AnimeRepository;
import academy.devdojo.springboot2essentials.requests.AnimePostRequestBody;
import academy.devdojo.springboot2essentials.requests.AnimePostRequestBodyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private static List<Anime> animes;
    private final AnimeRepository animeRepository;

    public  List<Anime> listAllNonpageable() {
        return animeRepository.findAll();
    }

    public Page<Anime> ListAll(Pageable pageable) {return animeRepository.findAll(pageable);}
    public List<Anime> findByName(String name) {return animeRepository.findByName(name);}

    public Anime findbyidOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException( "Anime not found"));

    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = animePostRequestBody.toModel();
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.delete(findbyidOrThrowBadRequestException(id));
    }

    public void replace(AnimePostRequestBodyResponse animePostRequestBodyResponse) {
        Anime savedAnime = findbyidOrThrowBadRequestException(animePostRequestBodyResponse.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBodyResponse);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }


}
