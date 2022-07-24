package application;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

	public static void main(String[] args) throws Exception {

		/*String urlNasa = "http://localhost:8080/languages";
		ClientHttp httpNasa = new ClientHttp();
		String jsonNasa = httpNasa.dataRetriever(urlNasa);
		ContentExtractorNasa contentExtractorNasa = new ContentExtractorNasa();
		List<Content> contentListNasa = contentExtractorNasa.contentExtractor(jsonNasa);
		var stickerGenerator = new StickerGenerator();

		System.out.println("======== Nasa API ========");
		for (int i = 0; i < 2; i++) {

			Content content = contentListNasa.get(i);
			InputStream inputStream = new URL(content.getUrl()).openStream();
			String fileName = "saida/" + content.getTitle() + ".png";
			stickerGenerator.createSticker(inputStream, fileName);
			System.out.println(content.getTitle());
			System.out.println();

		}
		*/
		String url = "http://localhost:8080/languages";
		ContentExtractor extractor = new ContentExtractorImdb();
		var http = new ClientHttp();
		String json = http.dataRetriever(url);
		List<Content> contentList = extractor.contentExtractor(json);
		System.out.println("======== Top Languages Api ========");
		var stickerGenerator = new StickerGenerator();
		
		for (int i = 0; i < 3; i++) {

			Content content = contentList.get(i);
			InputStream inputStream = new URL(content.getImageUrl()).openStream();
			String fileName = "saida/" + content.getTitle() + ".png";
			stickerGenerator.createSticker(inputStream, fileName);
			System.out.println(content.getTitle());
		}

	}
}
