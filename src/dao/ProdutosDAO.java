package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Produto;

public class ProdutosDAO {

	public void adicionar(Produto p) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO produto(preco, nome, descricao) " + "VALUES(?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setDouble(1, p.getPreco());
			st.setString(2, p.getNome());
			st.setString(3, p.getDescricao());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public List<Produto> pesquisarPorNome(String nome) throws DAOException{
		List<Produto> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM produto WHERE nome LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Produto p = new Produto();
				p.setDescricao(rs.getString("descricao"));
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				lista.add(p);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
	
	public List<Produto> pesquisarTodosProdutos() throws DAOException{
		List<Produto> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM produto";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Produto p = new Produto();
				p.setDescricao(rs.getString("descricao"));
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				lista.add(p);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
	
	public void adicionarCompra(String email, int pedidoID) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO carrinho VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, pedidoID);
			st.setString(2, email);
			st.executeUpdate();
			con.close();
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}

}
