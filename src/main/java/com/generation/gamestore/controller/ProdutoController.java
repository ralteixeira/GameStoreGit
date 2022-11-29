package com.generation.gamestore.controller;

import com.generation.gamestore.model.Produto;
import com.generation.gamestore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> ListaProd() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    //Método busca por maior preço
    @GetMapping("/preco/{preco}")
    public ResponseEntity<List<Produto>>listaMaiorPreco(@PathVariable double preco){

        return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThanOrderByPreco(preco));
    }

    //Método busca por menor preço
    @GetMapping("precos/{preco}")
    public ResponseEntity<List<Produto>>ListaMenorPreco(@PathVariable double preco){

        return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThanOrderByPreco(preco));
    }



    //Crud - Read busca pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> ListaPorId(@PathVariable Long id) {
        Optional<Produto> buscaID = produtoRepository.findById(id);
        return buscaID
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }



    ///Buscar Produtos por nome GET
    @GetMapping("produto/{nome}")
    public ResponseEntity<List<Produto>>BuscaPorNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));


    }



    //Metodo Post
    @PostMapping
    public ResponseEntity<Produto> CriarProduto(@Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));

    }


    //Método PUT
    @PutMapping                //putPostagem
    public ResponseEntity<Produto> AtualizarProduto(@Valid @RequestBody Produto produto) {
        if(produto.getId() == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(produtoRepository.save(produto));
        }
    }

    //Método Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletaProdutos(@PathVariable Long id) {

        if(id == null){
            return ResponseEntity.notFound().build();
        }else {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }




}