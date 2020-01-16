package si.rso.products.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Log
@Path("/simulate")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalcEndpoint {

    @GET
    public Response simulate(@QueryParam("times") @DefaultValue("5") int times) {
        ArrayList<Integer> primes = this.getPrimes(times);
        System.err.println(primes.size());
        return Response.noContent().build();
    }
    
    public ArrayList<Integer> getPrimes(int maxCheck) {
        ArrayList<Integer> primeNumbersFound = new ArrayList<Integer>();
        
        for (int i = 1; i <= maxCheck; i++) {
            if (checkIfPrime(i)) {
                primeNumbersFound.add(i);
            }
        }
        return primeNumbersFound;
    }
    
    private boolean checkIfPrime(int numberToCheck) {
        int remainder;
        for (int i = 2; i <= numberToCheck / 2; i++) {
            remainder = numberToCheck % i;
            if (remainder == 0) {
                return false;
            }
        }
        return true;
    }
}
