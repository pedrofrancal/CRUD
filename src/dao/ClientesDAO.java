package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Cliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe controladora de clientes no banco de dados
 */
public class ClientesDAO {
	/**
	 * Função que adiciona os dados de um cliente no banco de dados
	 */
	public void adicionar(Cliente c) throws DAOException {
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "INSERT INTO logon VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getEmail());
			st.setString(2, c.getSenha());
			st.executeUpdate();

			sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setString(1, c.getCpf());
			st.setString(2, c.getEmail());
			st.setString(3, c.getNome());
			st.setString(4, c.getTelefone());
			st.setDate(5, java.sql.Date.valueOf(c.getNascimento()));
			st.setString(6, c.getEndereco());
			st.setString(7, c.getCep());
			st.executeUpdate();
			con.close();
			
			Alert a = new Alert(AlertType.CONFIRMATION, "CLIENTE CADASTRADO COM SUCESSO");
			a.show();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
