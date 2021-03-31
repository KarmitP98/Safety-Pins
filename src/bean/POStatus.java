package bean;

public enum POStatus {
	
	ORDERED, PROCESSED, DENIED;
	
	public static POStatus getPOStatus(String status) throws Exception {
		
		if (status == null)
			return null;
		
		switch(status.toUpperCase()) {
		
		case "ORDERED":
			return ORDERED;
		case "PROCESSED":
			return PROCESSED;
		case "DENIED":
			return DENIED;
			
		default:
			throw new Exception("Invalid PO Status");
		}
		
	}

}
