package br.com.jeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.jeb.dao.factory.ProdutoQuantidadeFactory;
import br.com.jeb.dao.factory.VendaFactory;
import br.com.jeb.dao.generic.GenericDAO;
import br.com.jeb.domain.Estoque;
import br.com.jeb.domain.ProdutoQuantidade;
import br.com.jeb.domain.Venda;
import br.com.jeb.domain.Venda.Status;
import br.com.jeb.exceptions.DAOException;
import br.com.jeb.exceptions.MaisDeUmRegistroException;
import br.com.jeb.exceptions.TableException;
import br.com.jeb.exceptions.TipoChaveNaoEncontradaException;

public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO {
	public VendaDAO() {
		super();
	}

	@Override
	public Class<Venda> getTipoClasse() {
		return Venda.class;
	}

	@Override
	public void atualiarDados(Venda entity, Venda entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setStatus(entity.getStatus());
	}

	@Override
	public void excluir(String valor) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			String sql = "UPDATE TB_VENDA SET STATUS = ? WHERE ID = ?";
			connection = getConnection();
			stm = connection.prepareStatement(sql);
			stm.setString(1, Status.CONCLUIDA.name());
			stm.setLong(2, venda.getId());
			stm.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("ERRO ATUALIZANDO OBJETO ", e);
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			String sql = "UPDATE TB_VENDA SET STATUS = ? WHERE ID = ?";
			connection = getConnection();
			stm = connection.prepareStatement(sql);
			stm.setString(1, Status.CANCELADA.name());
			stm.setLong(2, venda.getId());
			stm.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("ERRO ATUALIZANDO OBJETO ", e);
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_VENDA ");
		sb.append("(ID, CODIGO, ID_CLIENTE, VALOR_TOTAL, DATA_VENDA, STATUS)");
		sb.append("VALUES (nextval('sq_venda'),?,?,?,?,?)");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Venda entity) throws SQLException {
		stmInsert.setString(1, entity.getCodigo());
		stmInsert.setLong(2, entity.getCliente().getId());
		stmInsert.setBigDecimal(3, entity.getValorTotal());
		stmInsert.setTimestamp(4, Timestamp.from(entity.getDataVenda()));
		stmInsert.setString(5, entity.getStatus().name());
	}

	@Override
	protected String getQueryExclusao() {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmInsert, String valor) throws SQLException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	protected String getQueryAtualizacao() {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Venda entity) throws SQLException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stm, String valor) throws SQLException {
		stm.setString(1, valor);
	}

	@Override
	public Venda consultar(String valor) throws MaisDeUmRegistroException, TableException, DAOException {
		// TODO pode ser feito desta forma
		// Venda venda = super.consultar(valor);
		// Cliente cliente = clienteDAO.consultar(venda.getCliente().getId());
		// venda.setCliente(cliente);
		// return venda;

		// TODO Ou pode ser feito com join
		StringBuilder sb = sqlBaseSelect();
		sb.append("WHERE V.CODIGO = ? ");
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			stm = connection.prepareStatement(sb.toString());
			setParametrosQuerySelect(stm, valor);
			rs = stm.executeQuery();
			if (rs.next()) {
				Venda venda = VendaFactory.convert(rs);
				buscarAssociacaoVendaProdutos(connection, venda);
				return venda;
			}
		} catch (SQLException e) {
			throw new DAOException("ERRO CONSULTANDO OBJETO ", e);
		} finally {
			closeConnection(connection, stm, rs);
		}
		return null;
	}

	private void buscarAssociacaoVendaProdutos(Connection connection, Venda venda)
			throws DAOException {
		PreparedStatement stmProd = null;
		ResultSet rsProd = null;
		try {
			StringBuilder sbProd = new StringBuilder();
			sbProd.append("SELECT PQ.ID, PQ.QUANTIDADE, PQ.VALOR_TOTAL, ");
			sbProd.append("P.ID AS ID_PRODUTO, P.CODIGO, P.NOME, P.DESCRICAO, P.VALOR ");
			sbProd.append("FROM TB_PRODUTO_QUANTIDADE PQ ");
			sbProd.append("INNER JOIN TB_PRODUTO P ON P.ID = PQ.ID_PRODUTO ");
			sbProd.append("WHERE PQ.ID_VENDA = ?");
			stmProd = connection.prepareStatement(sbProd.toString());
			stmProd.setLong(1, venda.getId());
			rsProd = stmProd.executeQuery();
			Set<ProdutoQuantidade> produtos = new HashSet<>();
			while (rsProd.next()) {
				ProdutoQuantidade prodQ = ProdutoQuantidadeFactory.convert(rsProd);
				produtos.add(prodQ);
			}
			venda.setProdutos(produtos);
			venda.recalcularValorTotalVenda();
		} catch (SQLException e) {
			throw new DAOException("ERRO CONSULTANDO OBJETO ", e);
		} finally {
			closeConnection(connection, stmProd, rsProd);
		}
	}

	@Override
	public Collection<Venda> buscarTodos() throws DAOException {
		List<Venda> lista = new ArrayList<>();
		StringBuilder sb = sqlBaseSelect();
		try {
			Connection connection = getConnection();
			PreparedStatement stm = connection.prepareStatement(sb.toString());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Venda venda = VendaFactory.convert(rs);
				buscarAssociacaoVendaProdutos(connection, venda);
				lista.add(venda);
			}
		} catch (SQLException e) {
			throw new DAOException("ERRO CONSULTANDO OBJETO ", e);
		}
		return lista;
	}

	private StringBuilder sqlBaseSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT V.ID AS ID_VENDA, V.CODIGO, V.VALOR_TOTAL, V.DATA_VENDA, V.STATUS, ");
		sb.append("C.ID AS ID_CLIENTE, C.NOME, C.CPF, C.TEL, C.ENDERECO, C.NUMERO, C.CIDADE, C.ESTADO, C.CEP ");
		sb.append("FROM TB_VENDA V ");
		sb.append("INNER JOIN TB_CLIENTE C ON V.ID_CLIENTE = C.ID ");
		return sb;
	}

	@Override
	public Boolean cadastrar(Venda entity) throws TipoChaveNaoEncontradaException, DAOException {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false); // Inicio da transação

			stm = connection.prepareStatement(getQueryInsercao(), Statement.RETURN_GENERATED_KEYS);
			setParametrosQueryInsercao(stm, entity);
			int rowsAffected = stm.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet rs = stm.getGeneratedKeys()) {
					if (rs.next()) {
						entity.setId(rs.getLong(1));
					}
				}

				for (ProdutoQuantidade prod : entity.getProdutos()) {
					// Verifica e baixa o estoque usando a mesma conexão
					String queryEstoque = "SELECT * FROM TB_ESTOQUE WHERE ID_PRODUTO = ?";
					try (PreparedStatement stmEstoque = connection.prepareStatement(queryEstoque)) {
						stmEstoque.setLong(1, prod.getProduto().getId());
						try (ResultSet rs = stmEstoque.executeQuery()) {
							if (!rs.next()) {
								throw new DAOException(
										"Estoque não encontrado para o produto: " + prod.getProduto().getNome());
							}
							Estoque estoque = new Estoque();
							estoque.setId(rs.getLong("ID"));
							estoque.setIdProduto(rs.getLong("ID_PRODUTO"));
							estoque.setQuantidade(rs.getInt("QUANTIDADE"));
							estoque.setQuantidadeMinima(rs.getInt("QUANTIDADE_MINIMA"));
							estoque.setUltimaAtualizacao(rs.getTimestamp("ULTIMA_ATUALIZACAO").toInstant());

							if (!estoque.temEstoqueSuficiente(prod.getQuantidade())) {
								throw new DAOException(
										"Estoque insuficiente para o produto: " + prod.getProduto().getNome());
							}

							estoque.baixarEstoque(prod.getQuantidade());

							// Atualiza o estoque na mesma transação
							String updateEstoque = "UPDATE TB_ESTOQUE SET QUANTIDADE = ?, ULTIMA_ATUALIZACAO = ? WHERE ID = ?";
							try (PreparedStatement stmUpdateEstoque = connection.prepareStatement(updateEstoque)) {
								stmUpdateEstoque.setInt(1, estoque.getQuantidade());
								stmUpdateEstoque.setTimestamp(2, Timestamp.from(Instant.now()));
								stmUpdateEstoque.setLong(3, estoque.getId());
								stmUpdateEstoque.executeUpdate();
							}
						}
					}

					// Insere o produto na venda
					stm = connection.prepareStatement(getQueryInsercaoProdQuant());
					setParametrosQueryInsercaoProdQuant(stm, entity, prod);
					stm.executeUpdate();
				}

				connection.commit(); // Commit da transação
				return true;
			}

			connection.rollback();
			return false;
		} catch (SQLException e) {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.rollback();
				}
			} catch (SQLException ex) {
				throw new DAOException("ERRO NO ROLLBACK DA TRANSAÇÃO", ex);
			}
			throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	private String getQueryInsercaoProdQuant() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_PRODUTO_QUANTIDADE ");
		sb.append("(ID, ID_PRODUTO, ID_VENDA, QUANTIDADE, VALOR_TOTAL)");
		sb.append("VALUES (nextval('sq_produto_quantidade'),?,?,?,?)");
		return sb.toString();
	}

	private void setParametrosQueryInsercaoProdQuant(PreparedStatement stm, Venda venda, ProdutoQuantidade prod)
			throws SQLException {
		stm.setLong(1, prod.getProduto().getId());
		stm.setLong(2, venda.getId());
		stm.setInt(3, prod.getQuantidade());
		stm.setBigDecimal(4, prod.getValorTotal());
	}
}
