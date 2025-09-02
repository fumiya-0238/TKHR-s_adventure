package com.example.myapp.creater;

import com.example.myapp.model.actions.Action;
import com.example.myapp.model.actions.強攻撃;
import com.example.myapp.model.actions.強貫通攻撃;
import com.example.myapp.model.actions.攻撃;
import com.example.myapp.model.actions.草で回復;
import com.example.myapp.model.actions.貫通攻撃;
import com.example.myapp.repository.book.ActionPage;
import com.example.myapp.repository.book.PictureBook;

public enum CreateAction {
	INSTANCE;

	private PictureBook pb;

	public void setPictureBook(PictureBook pb) {
		this.pb = pb;
	}

	public Action create(int id) {
		Action action;
		ActionPage ap = pb.getActionPages().get(id - 1);
		switch (id) {
		case 1:
			action = new 攻撃();
			break;
		case 2:
			action = new 強攻撃();
			break;
		case 3:
			action = new 貫通攻撃();
			break;
		case 4:
			action = new 強貫通攻撃();
			break;
		case 10:
			action = new 草で回復();
			break;
		default:
			action = new 攻撃();
		}
		action.setStatus(ap.getName(), ap.isAttackIs());
		return action;
	}
}