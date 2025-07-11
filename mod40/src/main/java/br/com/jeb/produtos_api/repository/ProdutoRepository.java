package br.com.jeb.produtos_api.repository;

import br.com.jeb.produtos_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
