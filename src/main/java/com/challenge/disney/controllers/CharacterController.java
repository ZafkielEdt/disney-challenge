package com.challenge.disney.controllers;

import com.challenge.disney.dtos.CharacterDTO;
import com.challenge.disney.dtos.CharacterDTOBasic;
import com.challenge.disney.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTOBasic>> getAllCharacters() {

        List<CharacterDTOBasic> characters = characterService.getAllCharacters();

        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable Long id) {

        CharacterDTO dtoResult = characterService.getCharacter(id);

        return ResponseEntity.ok().body(dtoResult);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CharacterDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> idMovie) {

        List<CharacterDTO> dtos = characterService.getByFilters(name, age, idMovie);

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterDTO> createChar(@Valid @RequestBody CharacterDTO charDTO) {

        CharacterDTO dtoResult = characterService.createCharacter(charDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResult);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CharacterDTO> updateChar(@PathVariable Long id,@RequestBody CharacterDTO characterDTO) {

        CharacterDTO dto = characterService.updateCharacter(id,characterDTO);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChar(@PathVariable Long id) {

        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
