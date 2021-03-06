package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Chamado;
import entity.Cliente;

/**
 * Classe que manipula o banco de dados de chamado
 */
public class ChamadoDAO {
	
	/**
	 * Fun��o que responde um chamado no banco de dados
	 */
	public void resolver(String email, String descricao) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE chamado SET descricao_atendimento = ? WHERE cliente_logon_email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, descricao);
			st.setString(2, email);
			st.executeUpdate();
			con.close();
			mudarSituacao(email);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Fun��o utilizada para mudar o status de um chamado para ATENDIDO
	 */
	private void mudarSituacao(String email) throws DAOException {
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE chamado SET situacao_atendimento = 'ATENDIDO' where cliente_logon_email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			st.executeUpdate();
			con.close();
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Fun��o que abre um chamado no banco de dados
	 */
	public void abrirChamado(String email, String descricao) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO chamado(descricao, data_chamado, cliente_logon_email) VALUES (?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, descricao);
			st.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			st.setString(3, email);
			st.executeUpdate();
			con.close();
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Fun��o que, diferente de seu nome, apaga um chamado do banco de dados
	 */
	public void arquivar(String email) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE chamado WHERE cliente_logon_email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			st.executeUpdate();
			con.close();
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Fun��o que retorna uma lista com todos os chamados
	 */
	public List<Chamado> pesquisarTodosChamados() throws DAOException {
		List<Chamado> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM chamado";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Chamado c = new Chamado();
				c.setDataChamado(rs.getDate("data_chamado").toLocalDate());
				Cliente cc = new Cliente();
				cc.setEmail(rs.getString("cliente_logon_email"));
				c.setCliente(cc);
				c.setDescricao(rs.getString("descricao"));
				c.setId(rs.getInt("id"));
				lista.add(c);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
}
