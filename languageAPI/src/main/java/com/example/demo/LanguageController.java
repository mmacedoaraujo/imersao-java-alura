package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {

	@Autowired
	private LanguageRepository langRepo;

	@GetMapping("/languages")
	public List<Language> getLanguage() {
		List<Language> languages = langRepo.findAll();
		return languages;
	}

	@PostMapping("/languages")
	public Language createLanguage(@RequestBody Language language) {
		Language createdLanguage = langRepo.save(language);
		return createdLanguage;
	}

}
