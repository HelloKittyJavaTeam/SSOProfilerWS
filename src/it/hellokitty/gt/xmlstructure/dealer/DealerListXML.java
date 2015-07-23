package it.hellokitty.gt.xmlstructure.dealer;

import java.util.ArrayList;
import java.util.List;

public class DealerListXML {
	private List<DealerXML> dealerList = new ArrayList<DealerXML>();

 	public List<DealerXML> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<DealerXML> dealerList) {
		this.dealerList = dealerList;
	}
}
