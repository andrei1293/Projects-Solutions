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
	private static final String ERROR = "error";

	private static final String POSITIVE_MESSAGE = "Cost and tax values should be positive!";
	private static final String VALUES_MESSAGE = "Enter cost and tax values!";

	@GET
	@Path("/calculator")
	@Produces(MediaType.APPLICATION_JSON)
	public String calculateTotalCost(@QueryParam(COST) String cost, @QueryParam(TAX) String tax) throws JSONException {
		JSONObject result = new JSONObject();

		if (cost != null && tax != null && !cost.isEmpty() && !tax.isEmpty()) {
			BigDecimal costValue = new BigDecimal(cost);
			BigDecimal taxValue = new BigDecimal(tax).divide(new BigDecimal(100));

			if (costValue.signum() == 1 && taxValue.signum() == 1) {
				BigDecimal totalValue = costValue.add(costValue.multiply(taxValue));

				result.put(TOTAL, totalValue);
			} else {
				result.put(ERROR, POSITIVE_MESSAGE);
			}
		} else {
			result.put(ERROR, VALUES_MESSAGE);
		}

		return result.toString();
	}
}
