package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Funcionario;

public class CadastrosDAO {
	public void adicionar(Funcionario f) throws DAOException {
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO logon VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, f.getEmail());
			st.setString(2, f.getSenha());
			st.executeUpdate();

			sql = "INSERT INTO funcionario VALUES (?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setString(1, f.getCpf());
			st.setString(2, f.getNome());
			st.setString(3, f.getEmail());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<Funcionario> pesquisarPorEmail(String email) throws DAOException {
		List<Funcionario> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT * FROM funcionario WHERE logon_email LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + email + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setCpf(rs.getString("cpf"));
				f.setEmail(rs.getString("logon_email"));
				f.setNome(rs.getString("nome"));
				lista.add(f);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}

	public void atualizar(Funcionario f) throws DAOException {
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			
			String sql = "UPDATE funcionario SET nome = ? WHERE email = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, f.getNome());
			st.setString(2, f.getEmail());
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void deletar(Funcionario f) throws DAOException{
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "DELETE funcionario WHERE logon_email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, f.getEmail());
			st.executeUpdate();
			
			sql = "DELETE logon WHERE email = ?";
			st = con.prepareStatement(sql);
			st.setString(1, f.getEmail());
			st.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
