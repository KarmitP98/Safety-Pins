package rest;

import model.MainModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("restServices")
public class RestServices {

    @GET
    @Path("/getProductInfo/")
    @Produces("text/plain")
    public String getProductInfo(@QueryParam("productId") String productId) throws Exception {
        return MainModel.getInstance().getProductInfo(productId);
    }

    @GET
    @Path("/getOrdersByPartNumber/")
    @Produces("text/plain")
    public String getOrdersByPartNumber(@QueryParam("productId") String productId) throws Exception {
        return MainModel.getInstance().getOrdersByPartNumber(productId);
    }


}