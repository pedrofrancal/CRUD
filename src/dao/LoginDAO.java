package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Administrador;
import entity.Cliente;
import entity.Login;
import entity.Suporte;

public class LoginDAO {
//	private static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
//	private static final String HOST = "192.168.99.100";
//	private static final String DB = "lojaOnline";
//	private static final String USER = "SA";
//	private static final String PASS = "P4ssw0rd";

//	public LoginDAO() {
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public boolean ValidaLogin(Login user) throws DAOException {
		try {
			Connection con = ConnectionSingleton.getInstance().getConnection();
//			Connection con = DriverManager.getConnection(String.format(
//					"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", HOST, DB, USER, PASS));
			String sql = "SELECT senha FROM logon WHERE email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getEmail());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				if (rs.getString("senha").equals(user.getSenha())) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return false;

	}

	public Login retornaTipo(Login user) throws DAOException {
		Login userHierarchy = null;
		try {
//			Connection con = DriverManager.getConnection(String.format(
//					"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", HOST, DB, USER, PASS));
			Connection con = ConnectionSingleton.getInstance().getConnection();
			String sql = "SELECT logon.email, \r\n"
					+ "	CASE WHEN (logon.email = administrador.funcionario_logon_email)\r\n" + "	THEN\r\n"
					+ "		'ADMINISTRADOR'\r\n" + "	ELSE\r\n"
					+ "		CASE WHEN (logon.email = suporte.funcionario_logon_email)\r\n" + "		THEN\r\n"
					+ "			'SUPORTE'\r\n" + "		ELSE\r\n" + "			'CLIENTE'\r\n" + "		END\r\n"
					+ "	END AS hierarquia\r\n"
					+ "FROM logon LEFT JOIN administrador ON administrador.funcionario_logon_email = logon.email\r\n"
					+ "LEFT JOIN suporte ON suporte.funcionario_logon_email = logon.email\r\n"
					+ "LEFT JOIN cliente ON cliente.logon_email = logon.email\r\n" + "WHERE logon.email = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getEmail());
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				if (rs.getString("hierarquia").equalsIgnoreCase("administrador")) {
					Administrador adm = new Administrador();
					adm.setEmail(user.getEmail());
					adm.setSenha(user.getSenha());
					sql = "SELECT funcionario.nome, funcionario.cpf, suporte.email_para_suporte FROM\r\n"
							+ "funcionario LEFT JOIN administrador ON funcionario.cpf = administrador.funcionario_cpf\r\n"
							+ "LEFT JOIN suporte ON suporte.funcionario_cpf = funcionario.cpf\r\n"
							+ "WHERE funcionario.logon_email = ?";
					st = con.prepareStatement(sql);
					st.setString(1, user.getEmail());
					ResultSet rs2 = st.executeQuery();
					if (rs2.next()) {
						adm.setCpf(rs2.getString("cpf"));
						adm.setNome(rs2.getString("nome"));
					}
					st.close();
					userHierarchy = adm;
				} else if (rs.getString("hierarquia").equalsIgnoreCase("suporte")) {
					Suporte s = new Suporte();
					s.setEmail(user.getEmail());
					s.setSenha(user.getSenha());
					sql = "SELECT funcionario.nome, funcionario.cpf, suporte.email_para_suporte FROM\r\n"
							+ "funcionario LEFT JOIN administrador ON funcionario.cpf = administrador.funcionario_cpf\r\n"
							+ "LEFT JOIN suporte ON suporte.funcionario_cpf = funcionario.cpf\r\n"
							+ "WHERE funcionario.logon_email = ?";
					st.close();
					st = con.prepareStatement(sql);
					st.setString(1, user.getEmail());
					ResultSet rs2 = st.executeQuery();
					if (rs2.next()) {
						s.setCpf(rs2.getString("cpf"));
						s.setEmailInstitucional(rs2.getString("email_para_suporte"));
						s.setNome(rs2.getString("nome"));
					}
					userHierarchy = s;
				} else {
					Cliente c = new Cliente();
					c.setEmail(user.getEmail());
					c.setSenha(user.getSenha());
					sql = "SELECT c.cpf, c.logon_email, c.nome, c.telefone, FORMAT(c.dataNascimento, 'yyyy-MM-dd') AS nascimento, c.endereco, \r\n"
							+ "CONCAT(SUBSTRING(c.cep, 1, 6),'-', SUBSTRING(c.cep, 7, 9)) AS cep \r\n"
							+ "FROM cliente c\r\n" + "WHERE c.logon_email = ?";
					st.close();
					st = con.prepareStatement(sql);
					st.setString(1, user.getEmail());
					ResultSet rs2 = st.executeQuery();
					if (rs2.next()) {
						c.setCpf(rs2.getString("cpf"));
						c.setCep(rs2.getString("cep"));
						c.setEndereco(rs2.getString("endereco"));
						c.setNascimento(rs2.getDate("nascimento").toLocalDate());
						c.setTelefone(rs2.getString("telefone"));
					}
					userHierarchy = c;
				}
			}
			st.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return userHierarchy;
	}
}
