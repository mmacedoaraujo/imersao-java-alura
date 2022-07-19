package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {

		// fazer uma conexão HTTP e buscar os top 250 filmes
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

		// aqui transformamos a string url em um uri para ser usado no HttpRequest
		// abaixo.
		URI adress = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(adress).GET().build();

		// aqui o BodyHandler define a forma como receberemos os dados requisitados (em
		// String no exemplo)
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		// System.out.println(body);

		// extrair só os dados que interessam (titulo, poster e a classificação)
		// "parsear os dados"
		JsonParser jsonParser = new JsonParser();
		List<Map<String, String>> movieList = jsonParser.parse(body);

		String y = Character.toString(128_512);
		System.out.println(y);

		// exibir e manipular os dados
		for (Map<String, String> filme : movieList) {
			System.out.println("Título: " + filme.get("title"));
			System.out.println("Poster: " + filme.get("image"));
			System.out.println("Classificação: " + filme.get("imDbRating"));
			System.out.println("\uD83D\uDE00");
			System.out.println();
		}
	}

}
