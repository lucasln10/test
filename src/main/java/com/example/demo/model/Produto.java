package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "produto")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome_produto", nullable = false, unique = true)
    private String nomeProduto;

    private String descriçãoProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescriçãoProduto() {
        return descriçãoProduto;
    }

    public void setDescriçãoProduto(String descriçãoProduto) {
        this.descriçãoProduto = descriçãoProduto;
    }
}
