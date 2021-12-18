package com.challenge.disney.controllers;

import com.challenge.disney.dtos.GenreDTO;
import com.challenge.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {

        List<GenreDTO> dtoResult = genreService.getAllGenres();

        return ResponseEntity.ok().body(dtoResult);
    }

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) {

        GenreDTO dtoResult = genreService.createGenre(genreDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResult);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @Valid @RequestBody GenreDTO dto) {

        GenreDTO dtoResult = genreService.updateGenre(id, dto);

        return ResponseEntity.ok().body(dtoResult);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {

        genreService.deleteGenre(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
