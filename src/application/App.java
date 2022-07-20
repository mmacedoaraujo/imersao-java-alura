package application;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws Exception {

		// fazer uma conexão HTTP e buscar os top 250 filmes
		String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";

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

		// exibir e manipular os dados
		var gerador = new GeradoraDeFigurinhas();
		for (int i = 0; i < 10; i++) {

			Map<String, String> movie = movieList.get(i);
			
			String imageUrl = movie.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
			String titulo = movie.get("title");

			InputStream inputStream = new URL(imageUrl).openStream();
			String nomeArquivo = titulo + ".png";

			gerador.createSticker(inputStream, nomeArquivo);

			System.out.println(titulo);
			System.out.println();
		}

	}
}
