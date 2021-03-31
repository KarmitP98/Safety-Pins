package bean;

public enum VisitEventType {
	
	VIEW, CART, PURCHASE;
	
	public static VisitEventType getVisitEventType(String type) throws Exception {
		
		if (type == null) 
			return null;
		
		switch(type) {
		case "VIEW":
			return VIEW;
		case "CART":
			return CART;
		case "PURCHASE":
			return PURCHASE;
			
		default:
			throw new Exception("Invalid VisitEventType");
		}
	}

}
