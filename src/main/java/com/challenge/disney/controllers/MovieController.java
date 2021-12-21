package com.challenge.disney.controllers;

import com.challenge.disney.dtos.MovieDTO;
import com.challenge.disney.dtos.MovieDTOBasic;
import com.challenge.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<MovieDTOBasic>> getAllMovies() {

        List<MovieDTOBasic> dtos = movieService.getAllMovies();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {

        MovieDTO dtoResult = movieService.getMovie(id);

        return ResponseEntity.ok().body(dtoResult);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<MovieDTO>> getByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<MovieDTO> dtos = movieService.getByFilters(title, genreId, order);

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) {

        MovieDTO dtoResult = movieService.createMovie(movieDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResult);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id,@Valid @RequestBody MovieDTO movieDTO) {

        MovieDTO dtoResult = movieService.updateMovie(id,movieDTO);

        return ResponseEntity.ok().body(dtoResult);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/link-char")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MovieDTO> linkCharacter(@RequestParam Set<Long> idChar, @RequestParam Long idMovie) {

        MovieDTO dto = movieService.linkCharacter(idChar, idMovie);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/link-genre")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MovieDTO> linkGenre(@RequestParam Set<Long> idGenre, @RequestParam Long idMovie) {

        MovieDTO dto = movieService.linkGenre(idGenre,idMovie);

        return ResponseEntity.ok().body(dto);
    }
}
