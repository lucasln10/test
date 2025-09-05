package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /*
    * METODOS DE SERVIÇO
    * */
    public void create(Produto produto){
        if(produto == null){
            throw new RuntimeException("O produto não pode ser nulo");
        }
        produtoRepository.saveAndFlush(produto);
    }

    public List<Produto> allProdutos(){
        return produtoRepository.findAll();
    }

    public void update(Long id, Produto produto){
        Produto produtoId = findById(id);

        if (produto.getNomeProduto() != null){
            produtoId.setNomeProduto(produto.getNomeProduto());
        }

        if(produto.getDescriçãoProduto() != null) {
            produtoId.setDescriçãoProduto(produto.getDescriçãoProduto());
        }

        produtoRepository.saveAndFlush(produtoId);
       /* Produto produtoAtualizado = Produto.builder()
                .nomeProduto(produto.getNomeProduto() == null ? produtoId.getNomeProduto() : produto.getNomeProduto())
                .descriçãoProduto(produto.getDescriçãoProduto() == null ? produtoId.getDescriçãoProduto() : produto.getDescriçãoProduto())
                .id(produtoId.getId())
                .build();*/
    }

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("O id não pode ser nulo"));
    }

    public void deleteProduto(Long id){
        Produto produtoId = findById(id);
        produtoRepository.deleteById(produtoId.getId());
    }



    /*
    * METODOS DO CONTROLLER
    * */

    /*@GetMapping
    public ResponseEntity<>*/

    @PostMapping("/criar")
    public ResponseEntity<Void> createProduto(@RequestBody Produto produto){
         create(produto);
         return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produto>> listAllProduto(){
        List<Produto> produtos = allProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<Produto> findByIdProduct(Long id){
        Produto produtoId = findById(id);
        return ResponseEntity.ok(produtoId);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> updateProduto(Long id, @RequestBody Produto produto){
        update(id, produto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(Long id){
        deleteProduto(id);
        return ResponseEntity.ok().build();
    }

}
