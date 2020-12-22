package EzyTest;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EzyTest {

	public static void main(String[] args) {
		// subscribe();
	}

	public static Response subscribe(BigDecimal amt, char type, String dayOfWeekMonth, Date startDate, Date endDate) throws Exception {
		validateInput(amt, type, dayOfWeekMonth, startDate, endDate);
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(endDate);
	    cal.add(Calendar.MONTH, 3);
	    
		if(endDate.after(cal.getTime())) {
			endDate = cal.getTime();
		}
		
		List<String> invoiceDate = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		switch(type) {
		case 'd':
			Calendar cal2 = Calendar.getInstance();
		    cal2.setTime(startDate);
		    while(cal2.getTime().before(endDate)) {
		    	invoiceDate.add(sdf.format(cal2.getTime()));
		    	cal2.add(Calendar.DATE, 1);
		    }
			break;
		case 'w':
			Calendar cal3 = Calendar.getInstance();
		    cal3.setTime(startDate);
		    while(cal3.get(Calendar.DAY_OF_WEEK) != getDayOfWeekAsInt(dayOfWeekMonth)) {
		    	cal3.add(Calendar.DATE, 1);
		    }
		    while(cal3.getTime().before(endDate)) {
		    	invoiceDate.add(sdf.format(cal3.getTime()));
		    	cal3.add(Calendar.DATE, 7);
		    }
			break;
		case 'm':
			Calendar cal4 = Calendar.getInstance();
		    cal4.setTime(startDate);
		    while(cal4.get(Calendar.DAY_OF_MONTH) != Integer.parseInt(dayOfWeekMonth)) {
		    	cal4.add(Calendar.DATE, 1);
		    }
		    while(cal4.getTime().before(endDate)) {
		    	invoiceDate.add(sdf.format(cal4.getTime()));
		    	cal4.add(Calendar.MONTH, 1);
		    }
			break;
		}
		Response res = new Response();
		res.setAmount(amt);
		res.setSubscriptionType(type);
		res.setInvoiceDates(invoiceDate);
		return res;
	}

	private static void validateInput(BigDecimal amt, char type, String dayOfWeekMonth, Date startDate, Date endDate)
			throws Exception {
		if (amt.doubleValue() < 0) {
			throw new Exception("Amount cannot be negative");
		}
		if (type != 'w' && type != 'd' && type != 'm') {
			throw new Exception("Invalid subscription type");
		}
		if (startDate == null) {
			throw new Exception("Invalid start date");
		}
		if (startDate.after(endDate)) {
			throw new Exception("Start Date cannot after End Date");
		}
		if (type == 'm') {
			try {
				Integer.parseInt(dayOfWeekMonth);
			} catch (NumberFormatException e) {
				throw new Exception("Invalid Day of Month");
			}
		}
		if (type == 'w') {
			if (!Arrays.asList(DateFormatSymbols.getInstance().getShortWeekdays()).contains(dayOfWeekMonth)) {
				throw new Exception("Invalid Day of Week");
			}
		}
	}

	public static int getDayOfWeekAsInt(String day) {
		if (day == null) {
			return -1;
		}
		switch (day.toLowerCase()) {
		case "mon":
			return Calendar.MONDAY;
		case "tue":
			return Calendar.TUESDAY;
		case "wed":
			return Calendar.WEDNESDAY;
		case "thu":
			return Calendar.THURSDAY;
		case "fri":
			return Calendar.FRIDAY;
		case "sat":
			return Calendar.SATURDAY;
		case "sun":
			return Calendar.SUNDAY;
		default:
			return -1;
		}
	}

}
