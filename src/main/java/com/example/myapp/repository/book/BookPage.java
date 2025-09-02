package com.example.myapp.repository.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookPage {
	private int id;
	private String name;
	private List<BookPage> subPages;
	private List<String> texts;

	public BookPage(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public List<BookPage> getSubPage() {
		return subPages;
	}

	public void setSubPage(BookPage page) {
		if (Objects.isNull(subPages)) {
			subPages = new ArrayList<>();
		}
		subPages.add(page);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}
}
