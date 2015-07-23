package it.hellokitty.gt.xmlstructure.arealist;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "area-list")
public class AreaListXML {
	private List<AreaXML> area;

	public List<AreaXML> getArea() {
		return area;
	}

	public void setArea(List<AreaXML> area) {
		this.area = area;
	}
}
