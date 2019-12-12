package org.wlopera.project.service.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import lombok.AllArgsConstructor;

/**
 * Clase 'CallBackHandler' de seguridad que permite autenticar el usuario que realiza la peticion. 
 * @author Willian Lopera
 *
 */

@AllArgsConstructor
public class CrimsonLogicPasswordCallBack implements CallbackHandler {
	private String user;
	private String password;

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (Callback callback : callbacks) {
			final WSPasswordCallback wpc = (WSPasswordCallback) callback;

			if (wpc.getIdentifier().equals(user)) {
				wpc.setPassword(password);
				return;
			}
		}
	}
}