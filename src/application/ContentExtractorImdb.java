package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorImdb implements ContentExtractor {

	@Override
	public List<Content> contentExtractor(String json) {
		JsonParser jsonParser = new JsonParser();
		List<Map<String, String>> attributesList = jsonParser.parse(json);

		List<Content> contentList = new ArrayList<>();

		// popular a lista de atributos
		for (Map<String, String> attribute : attributesList) { // regex
			var content = new Content(attribute.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg"),
					attribute.get("title"));

			contentList.add(content);
		}

		return contentList;
	}
}
