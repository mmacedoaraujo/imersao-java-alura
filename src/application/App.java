package application;

import java.io.IOException;
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

		// fazer uma conex�o HTTP e buscar os top 250 filmes
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

		// extrair s� os dados que interessam (titulo, poster e a classifica��o)
		// "parsear os dados"
		JsonParser jsonParser = new JsonParser();
		List<Map<String, String>> movieList = jsonParser.parse(body);

		// exibir e manipular os dados
		var gerador = new GeradoraDeFigurinhas();
		for (Map<String, String> filme : movieList) {

			String urlImagem = filme.get("image");
			//urlImagem.split("._V1_UX128_CR0,3,128,176_AL_.jpg");
			//urlImagem.split("._V1_UX128_CR0,1,128,176_AL_.jpg");

			String titulo = filme.get("title");

			InputStream inputStream = new URL(urlImagem).openStream();
			String nomeArquivo = titulo + ".png";

			gerador.createSticker(inputStream, nomeArquivo);

			System.out.println(titulo);
			System.out.println();
		}

	}
}
