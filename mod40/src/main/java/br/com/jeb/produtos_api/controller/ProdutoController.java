package br.com.jeb.produtos_api.controller;

import br.com.jeb.produtos_api.model.Produto;
import br.com.jeb.produtos_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoRepository repository;

  @GetMapping
  public List<Produto> listar() {
    return repository.findAll();
  }

  @PostMapping
  public Produto criar(@RequestBody Produto produto) {
    return repository.save(produto);
  }

  @PutMapping("/{id}")
  public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
    produto.setId(id);
    return repository.save(produto);
  }

  @DeleteMapping("/{id}")
  public void excluir(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
