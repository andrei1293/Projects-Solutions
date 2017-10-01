package service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/")
public class TaxCalculatorService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String COST = "cost";
	private static final String TAX = "tax";
	private static final String TOTAL = "total";

	@GET
	@Path("/calculator")
	@Produces(MediaType.APPLICATION_JSON)
	public String test(@QueryParam(COST) String cost, @QueryParam(TAX) String tax) throws JSONException {
		BigDecimal costValue = new BigDecimal(cost);
		BigDecimal taxValue = new BigDecimal(tax);

		BigDecimal totalValue = costValue.add(costValue.multiply(taxValue));

		JSONObject result = new JSONObject();
		result.put(TOTAL, totalValue);

		return result.toString();
	}
}
