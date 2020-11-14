package fetchers;

import com.google.gson.Gson;
import dto.GameOfThronesDTO;
import java.io.IOException;
import utils.HttpUtils;

import java.util.concurrent.*;

public class GameOfThronesFetcher {

    private static String GOT_URL = "https://got-quotes.herokuapp.com/quotes";

    public static String fetchQuotes(ExecutorService threadPool, Gson gson) throws InterruptedException, ExecutionException, TimeoutException, IOException {

        Callable<GameOfThronesDTO> gameOfThronesTask = new Callable<GameOfThronesDTO>() {
            @Override
            public GameOfThronesDTO call() throws Exception {
                String gameOfThrones = HttpUtils.fetchData(GOT_URL);
                GameOfThronesDTO gameOfThronesDTO = gson.fromJson(gameOfThrones, GameOfThronesDTO.class);
                return gameOfThronesDTO;
            }
        };

        Future<GameOfThronesDTO> futureGameOfThrones = threadPool.submit(gameOfThronesTask);
        GameOfThronesDTO gameOfThronesDTO = futureGameOfThrones.get(5, TimeUnit.SECONDS);
        String gameOfThronesJson = gson.toJson(gameOfThronesDTO);

        return gameOfThronesJson;
    }
}
