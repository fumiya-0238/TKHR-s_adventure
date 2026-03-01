package com.example.myapp.creater;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.myapp.repository.book.BookPage;
import com.example.myapp.repository.book.PictureBook;

public enum CreateText {
	INSTANCE;

	private PictureBook pictureBook;
	
	public void setPictureBook(PictureBook pictureBook) {
		this.pictureBook = pictureBook;
	}

	public void macherFind(BookPage bookPage, String text) {
		String regex = "<[^>]+/\\d+(?:(?:/\\d+)*|(?:/<n>))>";//"<[^>]*\\/+[^>]*>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		List<String> texts = new ArrayList<>();
		BookPage subPage;
		int lastEnd = 0;
		for (int i = 0; matcher.find(); i++) {
			String matched = matcher.group(); // 一致した文字列
			int startPosition = matcher.start(); // 開始位置（0-based）
			int endPosition = matcher.end();

			// 一致部分の前の文字列を追加
			if (startPosition > lastEnd) {
				texts.add(text.substring(lastEnd, startPosition));
			}
			lastEnd = endPosition;
			String matchedResult = matched.substring(1, matched.length() - 1);
			String[] arrays = matchedResult.split("/");
			switch (arrays[0]) {
			case "item":
				subPage = pictureBook.getItemPages().get(Integer.parseInt(arrays[1]) - 1);
				break;
			case "weapon":
				subPage = pictureBook.getWeaponPages().get(Integer.parseInt(arrays[1]) - 1);
				break;
			case "action":
				subPage = pictureBook.getActionPages().get(Integer.parseInt(arrays[1]) - 1);
				break;
			case "condition":
				subPage = pictureBook.getConditionPages().get(Integer.parseInt(arrays[1]) - 1);
				break;
			case "monster":
				subPage = pictureBook.getMonsterPages().get(Integer.parseInt(arrays[1]) - 1);
				break;
			default:
				subPage = pictureBook.getItemPages().get(Integer.parseInt(arrays[1]) - 1);
			}
			if (3 == arrays.length) {
				bookPage.setPageN(i + "/" +arrays[2]);
			}
			texts.add("<" + subPage.getName() + ">");
			bookPage.addSubPage(subPage);
		}
		if (lastEnd == 0) {
			// 一致部分がなかった場合、文字列全体を追加
			texts.add(text);
		} else if (lastEnd < text.length()) {
			// 最後の残りの部分を追加
			texts.add(text.substring(lastEnd));
		}
		bookPage.setTexts(texts);
	}
}