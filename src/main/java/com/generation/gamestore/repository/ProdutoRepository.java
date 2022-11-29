package com.generation.gamestore.repository;

import com.generation.gamestore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{

    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);  //Método nao nativo necessário para rodar

    public List<Produto> findAllByPrecoGreaterThanOrderByPreco(double preco); //Método bysca por maior preço

    public List<Produto> findAllByPrecoLessThanOrderByPreco(double preco);//Metodo busca menor preço



}
