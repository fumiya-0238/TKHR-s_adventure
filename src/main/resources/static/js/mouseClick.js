monsterName.addEventListener('click', async() => {
	betweenInfo = true;
	commandHoverFlag = false;
	monsterName.style.outlineStyle = 'none';
	clickSound.currentTime = 0;
	clickSound.play();
	fetch('/monsterInfo')
		.then(response => response.json())
		.then(data => {
			infoBody.innerHTML = '';
			makeMonsterInfo(data.slice(1));
			infoElement.style.visibility = 'visible';
		})
		.catch(error => console.error('Error:', error));
});

conditionButton.addEventListener('click', async() => {
	betweenInfo = true;
	commandHoverFlag = false;
	conditionButton.style.outlineStyle = 'none';
	clickSound.currentTime = 0;
	clickSound.play();
	try {
		const response = await fetch('/conditionconfirm');
		const data = await response.json();
		clickSound.currentTime = 0;
		clickSound.play();
		await makeConditionList(data);
		infoElement.style.visibility = 'visible';
	} catch (error) {
		console.error('エラー:', error);
	}
});

battleLog.addEventListener('click', async() => {
	betweenInfo = true;
	commandHoverFlag = false;
	battleLog.style.outlineStyle = 'none';
	clickSound.currentTime = 0;
	clickSound.play();
	await fetch('/battleLog')
		.then(response => response.json())
		.then(data => {
			showBattleLog(data);
			logElement.style.visibility = 'visible';
		})
		.catch(error => console.error('Error:', error));
});

weaponName.addEventListener('click', async() => {
	betweenInfo = true;
	commandHoverFlag = false;
	weaponName.style.outlineStyle = 'none';
	clickSound.currentTime = 0;
	clickSound.play();
	await fetch('/myWeaponInfo')
		.then(response => response.json())
		.then(data => {
			infoBody.innerHTML = '';
			makeMyWeaponInfo(data);
			infoElement.style.visibility = 'visible';
		})
		.catch(error => console.error('Error:', error));
});

goTitle.addEventListener('click', async() => {
	if (window.confirm("タイトルに戻りますか？")) {
		battleToTitle();
		titleFlag = true;
	}
});

infoBody.addEventListener('click', async(event) => {
	event.target.style.outlineStyle = 'none';
	// クリックされた要素が "target" クラスを持つか確認
	if (event.target.classList.contains('infoLink')) {
		infoClickSound.currentTime = 0;
		infoClickSound.play();
		// すべての "target" 要素を取得
		const elements = document.querySelectorAll('.infoLink');
		// クリックされた要素のインデックスを取得
		try {
			const index = Array.from(elements).indexOf(event.target);
			const response = await fetch('/subPageInfo', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
					'index': index,
					toggle: conditionToggle
				})
			});
			const data = await response.json();
			pages.push(infoBody.innerHTML);
			infoBody.innerHTML = '';
			const kind = data[0].split("/");
			switch (kind[0]) {
				case "item":
					makeItemInfo(data.slice(1));
					break;
				case "weapon":
					makeWeaponInfo(data.slice(1));
					break;
				case "action":
					makeActionInfo(data.slice(1));
					break;
				case "condition":
					makeConditionInfo(data.slice(1));
					break;
			}
			infoElement.style.visibility = 'visible';
			backInfo.style.visibility = 'visible';
		} catch (error) {
			console.error('エラー:', error);
		}
	} else if (event.target.classList.contains('playerConditions')) {
		clickSound.currentTime = 0;
		clickSound.play();
		conditionToggle = true;
		infoBody.innerHTML = playerConditionPage.innerHTML;
	} else if (event.target.classList.contains('monsterConditions')) {
		clickSound.currentTime = 0;
		clickSound.play();
		conditionToggle = false;
		infoBody.innerHTML = monsterConditionPage.innerHTML;
	}
});

const commandClick = async(event) => {
	const element = event.target;
	if (element.id == 'commandButton') {
		return;
	}
	if (!commandClickFlag) {
		return;
	}
	switch (element) {
		case boost:
			try {
				const response = await fetch('/boost');
				const data = await response.json();
				if (data[0] == "Return/") {
					return;
				}
			//	commandClickFlag = false;
			//	element.style.outlineStyle = "none";
				for (const item of data) {
					await changeScreen(item);
				}
			} catch (error) {
				console.error('エラー:', error);
			}
			break;
		default:
			try {
				const response = await fetch('/command', {
					method: 'POST',
					headers: {
						'Content-Type': 'text/plain'
					},
					body: element.id
				});
				const data = await response.json();
				if (data[0] == "Return/") {
					return;
				}
				for (const item of data) {
					await changeScreen(item);
				}
			} catch (error) {
				console.error('エラー:', error);
			}
	}
	if (nowHoverCommand!= "" && commandHoverFlag) {
		nowHoverCommand.style.outlineWidth = '3px';
		nowHoverCommand.style.outlineStyle = 'solid';
		nowHoverCommand.style.outlineColor = 'red';
	}
	
	
};
const backClick = async() => {
	weaponName.style.outlineStyle = 'none';
	clickSound.currentTime = 0;
	clickSound.play();
	fetch('/backPage');
	infoBody.innerHTML = pages.at(-1);
	pages.pop();
	if (pages.length == 0) {
		backInfo.style.visibility = 'hidden';
	}
};

const closeClick = async() => {
	betweenInfo = false;
	clickSound.currentTime = 0;
	clickSound.play();
	closeInfo.style.outlineStyle = 'none';
	const response = await fetch('/closeInfo');
	commandHoverFlag = await response.json();
	infoElement.style.visibility = 'hidden';
	backInfo.style.visibility = 'hidden';
	pages.length = 0;
};

const logCloseClick = async() => {
	betweenInfo = false;
	const response = await fetch('/closeInfo');
	commandHoverFlag = await response.json();
	clickSound.currentTime = 0;
	clickSound.play();
	closeLog.style.outlineStyle = 'none';
	logElement.style.visibility = 'hidden';
};


const commandHover = (event) => {
	const element = event.target;
	nowHoverCommand = element;
	if (!commandHoverFlag) {
		return;
	}
	if (element.classList.contains('commands')) {
		// 子要素のリストを取得
		const elements = Array.from(document.querySelectorAll(
			'#commandButton .commands'));
		// クリックされた要素のインデックスを取得
		const index = elements.indexOf(element);
		messageText.textContent = commandMessages[index];
	}

	messageWindow.style.visibility = 'visible';
	messageText.style.visibility = 'visible';
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
};

const commandOut = (event) => {
	nowHoverCommand = "";
	const element = event.target;
	messageWindow.style.visibility = 'hidden';
	messageText.style.visibility = 'hidden';
	element.style.outlineStyle = "none";
};

const weaponNameHover = (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
};

const weaponNameOut = (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
};

const infoHover = (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
};

const infoOut = (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
};

const logHover = (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
};

const logOut = (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
};

closeInfo.addEventListener('mouseover', (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
});

closeInfo.addEventListener('mouseout', (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
});

backInfo.addEventListener('mouseover', (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
});

backInfo.addEventListener('mouseout', (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
});

goTitle.addEventListener('mouseover', (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
});
goTitle.addEventListener('mouseout', (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
});

commandButton.addEventListener("click", commandClick);
commandButton.addEventListener("mouseover", commandHover);
commandButton.addEventListener("mouseout", commandOut);

monsterName.addEventListener("mouseover", infoHover);
monsterName.addEventListener("mouseout", infoOut);

conditionButton.addEventListener("mouseover", infoHover);
conditionButton.addEventListener("mouseout", infoOut);

battleLog.addEventListener("mouseover", infoHover);
battleLog.addEventListener("mouseout", infoOut);

weaponName.addEventListener("mouseover", weaponNameHover);
weaponName.addEventListener("mouseout", weaponNameOut);

closeInfo.addEventListener("click", closeClick);
closeLog.addEventListener("click", logCloseClick);
closeLog.addEventListener("mouseover", logHover);
closeLog.addEventListener("mouseout", logOut);
backInfo.addEventListener("click", backClick);
const closeLogKey = (event) => {
	if (event.key != 'z') {
		return;
	}
	logCloseClick();
}

const documentKeyDown = (event) => {
	if (event.key == 'a' && nowHoverElement.className == "infoButton") {
		itemKey(nowHoverElement);
	}
	if (event.key == 'a' && nowHoverElement.className == "shopItem") {
		shopItemKey(nowHoverElement);
	}
	if (event.key == 'a' && nowHoverElement.className == "shopWeapon") {
		shopWeaponKey(nowHoverElement);
	}
	if (event.key == 'z' && betweenInfo) {
		closeClick();
	}
}

document.addEventListener("keydown", documentKeyDown);

async function itemKey(element) {
	clickSound.currentTime = 0;
	clickSound.play();
	betweenInfo = true;
	commandHoverFlag = false;
	try {
		const index = Array.from(list.children).indexOf(element);
		const response = await fetch('/itemInfo', {
			method: 'POST',
			headers: {
				'Content-Type': 'text/plain'
			},
			body: index
		});
		const data = await response.json();
		pages.push(data[0]);
		infoBody.innerHTML = '';
		makeItemInfo(data.slice(1));
		infoElement.style.visibility = 'visible';
		nowHoverElement = "";
	} catch (error) {
		console.error('エラー:', error);
	}
};

async function shopItemKey(shopItem) {
	clickSound.currentTime = 0;
	clickSound.play();
	betweenInfo = true;
	commandHoverFlag = false;
	// リスト内のインデックスを取得
	const index = Array.from(shopItemList.children).indexOf(shopItem);
	await fetch('/shopItemInfo', {
			method: 'POST',
			headers: {
				'Content-Type': 'text/plain'
			},
			body: index
		}).then(response => response.json())
		.then(data => {
			pages.push(data[0]);
			infoBody.innerHTML = '';
			makeItemInfo(data.slice(1));
			infoElement.style.visibility = 'visible';
			nowHoverElement = "";
		}).catch(error => console.error("エラー:", error));
};
async function shopWeaponKey(shopWeapon) {
	clickSound.currentTime = 0;
	clickSound.play();
	betweenInfo = true;
	commandHoverFlag = false;
	// リスト内のインデックスを取得
	const index = Array.from(shopWeaponList.children).indexOf(shopWeapon);
	await fetch('/shopWeaponInfo', {
			method: 'POST',
			headers: {
				'Content-Type': 'text/plain'
			},
			body: index
		}).then(response => response.json())
		.then(data => {
			pages.push(data[0]);
			infoBody.innerHTML = '';
			makeWeaponInfo(data.slice(1));
			infoElement.style.visibility = 'visible';
			nowHoverElement = "";
		}).catch(error => console.error("エラー:", error));
};
/*
const itemKey = async(event) => {
	if(event.key != 'a'){
		return;
	}
	clickSound.currentTime = 0;
	clickSound.play();
	betweenInfo = true;
	commandHoverFlag = false;
	const element = event.target;
	try{
	  const index = Array.from(list.children).indexOf(element);
	const response = await fetch('/itemInfo', {
	  method: 'POST',
	  headers: { 'Content-Type': 'text/plain' },
	  body: index
	});
	const data = await response.json();
	pages.push(data[0]);
	infoBody.innerHTML='';
	makeItemInfo(data.slice(1));	
	infoElement.style.visibility = 'visible';
	}catch (error) {
		        console.error('エラー:', error);
		    }
};*/

function goSpecialDungeon() {
	titleToBattle(dungeonSelect.value);
}
