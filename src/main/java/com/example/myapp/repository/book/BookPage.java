package com.example.myapp.repository.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookPage {
	private String pageName;
	private int id;
	private String name;
	private List<BookPage> subPages;
	private String pageN;
	private List<String> texts;

	public BookPage(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public BookPage(BookPage page) {
		id = page.getId();
		name = page.getName();
		subPages = page.getSubPages();
		pageN = page.getPageN();
		texts = page.getTexts();
	}

	public List<BookPage> getSubPages() {
		return subPages;
	}

	public void addSubPage(BookPage page) {
		if (Objects.isNull(subPages)) {
			subPages = new ArrayList<>();
		}
		subPages.add(page);
	}

	public void setPageN(String pageN) {
		this.pageN = pageN;
	}

	public String getNText() {
		if (Objects.isNull(pageN)) {
			return "-1/-1";
		}

		if (!pageN.contains("<n>")) {
			return pageN;
		}
		return "-1/-1";
	}

	public void replacePageN(String replace) {
		if (Objects.isNull(pageN)) {
			pageN = "-1/" + replace;
			return;
		}
		pageN = pageN.replace("<n>", replace);
	}

	public int getId() {
		return id;
	}

	public String getPageName() {
		return pageName;
	}

	public void defaultPageName(String type) {
		pageName = type + "/" + id;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getName() {
		return name;
	}

	public String getPageN() {
		return pageN;
	}

	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}
}
