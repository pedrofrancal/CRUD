package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ChamadoDAO {
	
	public void resolver(String email, String descricao) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "UPDATE chamado SET descricao_atendimento = ? WHERE email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, descricao);
			st.setString(2, email);
			st.executeUpdate();
			sql = "UPDATE chamado SET situacao_atendimento = ATENDIDO where email = ?";
			st.setString(1, email);
			st.executeUpdate();
			con.close();
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
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
}
