package it.hellokitty.gt.xmlstructure.application;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ApplicationXML {
	private String name;
	
    private String id;
    
    private RoleListXML roleList = new RoleListXML();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 @XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "role-list")
	public RoleListXML getRoleList() {
		return roleList;
	}

	public void setRoleList(RoleListXML roleList) {
		this.roleList = roleList;
	}
}