package utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class AddMessage {
	
	public static void message(Severity severityInfo, String msg) {
		FacesMessage message = new FacesMessage(severityInfo, msg, null);
		FacesContext.getCurrentInstance().addMessage(null,message);
	}

}
