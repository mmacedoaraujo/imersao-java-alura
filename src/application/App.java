package application;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

	public static void main(String[] args) throws Exception {

		String urlNasa = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
		ClientHttp httpNasa = new ClientHttp();
		String jsonNasa = httpNasa.dataRetriever(urlNasa);
		ContentExtractorNasa contentExtractorNasa = new ContentExtractorNasa();
		List<Content> contentListNasa = contentExtractorNasa.contentExtractor(jsonNasa);
		var stickerGenerator = new stickerGenerator();
		
		System.out.println("======== Nasa API ========");
		for (int i = 0; i < 3; i++) {
			
			Content content = contentListNasa.get(i);
			InputStream inputStream = new URL(content.getUrl()).openStream();
			String fileName = "saida/" + content.getTitle() + ".png";
			stickerGenerator.createSticker(inputStream, fileName);
			System.out.println(content.getTitle());
			System.out.println();

		}

		String urlIMDB = "https://api.mocki.io/v2/549a5d8b";
		ClientHttp httpIMDB = new ClientHttp();
		String jsonIMDB = httpIMDB.dataRetriever(urlIMDB);
		ContentExtractorImdb contractExtractorIMDB = new ContentExtractorImdb();
		List<Content> contentListIMDB = contractExtractorIMDB.contentExtractor(jsonIMDB);
		
		System.out.println("======== IMDB top 250 API ========");
		for (int i = 0; i < 10;i++) {
			
			Content content = contentListIMDB.get(i);
			InputStream inputStream = new URL(content.getUrl()).openStream();
			String fileName = "saida/" + content.getTitle() + ".png";
			stickerGenerator.createSticker(inputStream, fileName);
			System.out.println(content.getTitle());
		}
	}
}
