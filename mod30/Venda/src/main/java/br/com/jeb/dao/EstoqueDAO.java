package br.com.jeb.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import br.com.jeb.dao.generic.GenericDAO;
import br.com.jeb.domain.Estoque;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class EstoqueDAO extends GenericDAO<Estoque, Long> implements IEstoqueDAO {

    @Override
    public Class<Estoque> getTipoClasse() {
        return Estoque.class;
    }

    @Override
    public void atualiarDados(Estoque entity, Estoque entityCadastrado) {
        entityCadastrado.setQuantidade(entity.getQuantidade());
        entityCadastrado.setQuantidadeMinima(entity.getQuantidadeMinima());
        entityCadastrado.setUltimaAtualizacao(Instant.now());
    }

    @Override
    protected String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_ESTOQUE ");
        sb.append("(ID, ID_PRODUTO, QUANTIDADE, QUANTIDADE_MINIMA, ULTIMA_ATUALIZACAO)");
        sb.append("VALUES (nextval('sq_estoque'),?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Estoque entity) throws SQLException {
        stmInsert.setLong(1, entity.getIdProduto());
        stmInsert.setInt(2, entity.getQuantidade());
        stmInsert.setInt(3, entity.getQuantidadeMinima());
        stmInsert.setTimestamp(4, Timestamp.from(Instant.now()));
    }

    @Override
    protected String getQueryExclusao() {
        return "DELETE FROM TB_ESTOQUE WHERE ID = ?";
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) throws SQLException {
        stmExclusao.setLong(1, valor);
    }

    @Override
    protected String getQueryAtualizacao() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_ESTOQUE ");
        sb.append("SET QUANTIDADE = ?, ");
        sb.append("QUANTIDADE_MINIMA = ?, ");
        sb.append("ULTIMA_ATUALIZACAO = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Estoque entity) throws SQLException {
        stmUpdate.setInt(1, entity.getQuantidade());
        stmUpdate.setInt(2, entity.getQuantidadeMinima());
        stmUpdate.setTimestamp(3, Timestamp.from(Instant.now()));
        stmUpdate.setLong(4, entity.getId());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stm, Long valor) throws SQLException {
        stm.setLong(1, valor);
    }

    @Override
    public void atualizarEstoque(Estoque estoque) throws TipoChaveNaoEncontradaException, DAOException {
        Estoque estoqueBD = buscarPorProduto(estoque.getIdProduto());
        estoque.setId(estoqueBD.getId());
        super.alterar(estoque);
    }

    @Override
    public Estoque buscarPorProduto(Long idProduto) throws DAOException {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM TB_ESTOQUE ");
            sb.append("WHERE ID_PRODUTO = ?");

            PreparedStatement stm = getConnection().prepareStatement(sb.toString());
            stm.setLong(1, idProduto);
            var rs = stm.executeQuery();

            if (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setId(rs.getLong("ID"));
                estoque.setIdProduto(rs.getLong("ID_PRODUTO"));
                estoque.setQuantidade(rs.getInt("QUANTIDADE"));
                estoque.setQuantidadeMinima(rs.getInt("QUANTIDADE_MINIMA"));
                estoque.setUltimaAtualizacao(rs.getTimestamp("ULTIMA_ATUALIZACAO").toInstant());
                return estoque;
            }

            return null;
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar estoque por produto", e);
        }
    }
}
