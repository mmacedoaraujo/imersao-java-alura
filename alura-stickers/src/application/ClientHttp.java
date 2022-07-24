package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {

	public String dataRetriever(String url) {

		try {
			// aqui transformamos a string url em um uri para ser usado no HttpRequest
			// abaixo.
			URI adress = URI.create(url);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
			// aqui o BodyHandler define a forma como receberemos os dados requisitados (em
			// String no exemplo)
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			return response.body();

		} catch (IOException | InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
}
