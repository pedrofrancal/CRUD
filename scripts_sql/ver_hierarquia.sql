USE lojaOnline

SELECT logon.email, 
	CASE WHEN (logon.email = administrador.funcionario_logon_email)
	THEN
		'ADMINISTRADOR'
	ELSE
		CASE WHEN (logon.email = suporte.funcionario_logon_email)
		THEN
			'SUPORTE'
		ELSE
			'CLIENTE'
		END
	END AS hierarquia
FROM logon LEFT JOIN administrador ON administrador.funcionario_logon_email = logon.email
LEFT JOIN suporte ON suporte.funcionario_logon_email = logon.email
LEFT JOIN cliente ON cliente.logon_email = logon.email
WHERE logon.email = 'root@email.com'