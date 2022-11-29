package com.generation.gamestore.controller;


import com.generation.gamestore.model.Categoria;
import com.generation.gamestore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @GetMapping
    public ResponseEntity<List<Categoria>> ListaCategoria() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    //Crud - Read busca pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> ListaPorId(@PathVariable Long id) {
        Optional<Categoria> buscaID = categoriaRepository.findById(id);
        return buscaID
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }


    ///Buscar categoria por nome GET
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Categoria>> BuscaPortitulo(@PathVariable String titulo){
        return ResponseEntity.ok(categoriaRepository.findAllByTituloContainingIgnoreCase(titulo));


    }

    //Metodo Post
    @PostMapping
    public ResponseEntity<Categoria> CriarCategoria(@Valid @RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));

    }

    //Método PUT
    @PutMapping
    public ResponseEntity<Categoria> AtualizarCategoria(@Valid @RequestBody Categoria categoria) {
        if(categoria.getId() == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(categoriaRepository.save(categoria));
        }
    }

    //Método Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletaCategoria(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.notFound().build();
        } else {
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }


    }

}
