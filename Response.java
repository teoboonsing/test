package EzyTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Response {
	BigDecimal amount;
	char subscriptionType;
	List<String> invoiceDates;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public char getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(char subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public List<String> getInvoiceDates() {
		return invoiceDates;
	}

	public void setInvoiceDates(List<String> invoiceDates) {
		this.invoiceDates = invoiceDates;
	}

}
