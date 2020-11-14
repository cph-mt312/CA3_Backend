package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fetchers.GameOfThronesFetcher;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("got")
public class GameOfThronesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService es = Executors.newCachedThreadPool();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"user", "admin"})
    public String getGameOfThronesQuote() throws InterruptedException, ExecutionException, TimeoutException, IOException {
        String gameOfThronesQuote = GameOfThronesFetcher.fetchQuotes(es, GSON);
        return gameOfThronesQuote;
    }
}
