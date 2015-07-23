package it.hellokitty.gt.xmlstructure.application;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsXML {
	private List<ApplicationXML> application = new ArrayList<ApplicationXML>();

	public List<ApplicationXML> getApplication() {
		return application;
	}

	public void setApplication(List<ApplicationXML> application) {
		this.application = application;
	}
}
