package it.hellokitty.gt.xmlstructure.country;

import java.util.ArrayList;
import java.util.List;

public class CountryListXML {
	List<CountryXML> country = new ArrayList<CountryXML>();

	public List<CountryXML> getCountry() {
		return country;
	}

	public void setCountry(List<CountryXML> country) {
		this.country = country;
	}
	
}
