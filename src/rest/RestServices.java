package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.MainModel;



@Path("Rest")
public class RestServices {

	@GET
	@Path("getProductInfo")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("productId") String productId) throws Exception {
		
		// return product information as json file
		
		return MainModel.getInstance().getProductInfo(productId);
	}
	@GET
	@Path("getOrdersByPartNumber")
	@Produces("text/plain")
	public String getOrdersByPartNumber(@QueryParam("productId") String productId) throws Exception {
		
		
		// return json strings of all orders that have bid 
		
		
		return null;
	}

}
