package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ChamadoDAO {
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
}
