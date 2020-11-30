package controller;

import boundary.AdministradorBoundary;
import boundary.ClienteBoundary;
import boundary.SuporteBoundary;
import dao.DAOException;
import dao.LoginDAO;
import entity.Administrador;
import entity.Login;
import entity.Suporte;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class LoginController {
	private StringProperty email = new SimpleStringProperty();
	private StringProperty senha = new SimpleStringProperty();

	public Login getLogin() {
		Login login = new Login();
		login.setEmail(this.email.get());
		login.setSenha(this.senha.get());
		return login;
	}

	public Pane logar() {
		Pane paneHierarquico = null;
		Login user = getLogin();
		LoginDAO ld = new LoginDAO();
		boolean valida = false;
		try {
			valida = ld.ValidaLogin(user);
		} catch (DAOException e) {
			Alert a = new Alert(AlertType.ERROR, e.getMessage());
			a.show();
		}

		if (valida) {
			try {
				user = ld.retornaTipo(user);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			if(user instanceof Administrador) {
				paneHierarquico = new AdministradorBoundary().gerarTela();
			}else if(user instanceof Suporte) {
				paneHierarquico = new SuporteBoundary().gerarTela();
			}else {
				ClienteBoundary cB = new ClienteBoundary();
				cB.setEmail(this.email.get());
				paneHierarquico = cB.gerarTela();
			}
		} else {
			Alert a = new Alert(AlertType.ERROR, "SENHA ERRADA");
			a.show();
		}
		return paneHierarquico;
	}

	public StringProperty getEmailProperty() {
		return email;
	}

	public StringProperty getSenhaProperty() {
		return senha;
	}
}
