package com.example.myapp.model.items;

import com.example.myapp.service.GameService;

public interface ItemObserver {
	public void turnStart(GameService battle);

	public void turnEnd(GameService battle);
}
