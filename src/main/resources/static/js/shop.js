shopItemButton.addEventListener('click', (event) => {
	openTab(event.currentTarget, 'tab1');
});
shopWeaponButton.addEventListener('click', (event) => {
	openTab(event.currentTarget, 'tab2');
});

shopServiceButton.addEventListener('click', (event) => {
	openTab(event.currentTarget, 'tab3');
});

cancelFusion.addEventListener('click', async() => {
	try {
		const response = await fetch('/cancelFusion');
		const data = await response.json();
		if (data[0] == "Return/") {
			return;
		}
		createShopWeapon(data["weaponList"]);
		createShopService(data["serviceList"]);
		data["playerStatus"].forEach(item => changeScreen(item));
	} catch (error) {
		console.error('エラー:', error);
	}
});

function openTab(target, tabId) {
	if (fusionMode) {
		return;
	}
	// すべてのタブコンテンツを非表示
	document.querySelectorAll('.tab-content').forEach(content => {
		content.classList.remove('active');
	});

	// すべてのタブボタンのアクティブ状態を解除
	document.querySelectorAll('.tab-button').forEach(button => {
		button.classList.remove('active');
	});

	// 選択されたタブコンテンツを表示
	document.getElementById(tabId).classList.add('active');

	// 選択されたタブボタンをアクティブに
	target.classList.add('active');

	const parent = document.getElementById('shopInfo');
	const thirdDiv = parent.getElementsByTagName('div')[2];
	let options;

	switch (tabId) {
		case "tab1":
			thirdDiv.textContent = '在庫';
			document.getElementById('narabikae').style.visibility = 'visible';
			dropDown.style.visibility = 'visible';
			options = [{
				value: 'option1',
				text: '種類順'
			}, {
				value: 'option2',
				text: '値段順'
			}, {
				value: 'option3',
				text: '名前順'
			}];
			break;
		case "tab2":
			thirdDiv.textContent = '攻撃力';
			document.getElementById('narabikae').style.visibility = 'visible';
			dropDown.style.visibility = 'visible';
			options = [{
				value: 'option1',
				text: '種類順'
			}, {
				value: 'option2',
				text: '値段順'
			}, {
				value: 'option3',
				text: '名前順'
			}, {
				value: 'option4',
				text: '攻撃力順'
			}];
			break;
		case "tab3":
			thirdDiv.textContent = '在庫';
			options = [];
			document.getElementById('narabikae').style.visibility = 'hidden';
			dropDown.style.visibility = 'hidden';
			break;
	}

	// 既存オプションをクリア
	dropDown.innerHTML = '';

	// 配列からオプションを追加
	options.forEach(opt => {
		const option = document.createElement('option');
		option.value = opt.value;
		option.text = opt.text;
		dropDown.appendChild(option);
	});
}


async function createShop() {
	commandHoverFlag = false;
	await fetch('/shop')
		.then(response => response.json())
		.then(data => {
			document.getElementById('shopInfo').innerHTML = '';
			const name = document.createElement('div');
			const price = document.createElement('div');
			const stock = document.createElement('div');
			name.style.position = 'absolute';
			name.style.width = 40 + 'px';
			name.textContent = "名前";
			name.style.color = '#00ff00';
			price.style.position = 'absolute';
			price.style.width = 40 + 'px';
			price.style.left = 230 + 'px'
			price.textContent = "値段";
			price.style.color = '#00ff00';
			stock.style.position = 'absolute';
			stock.style.width = 60 + 'px';
			stock.style.left = 330 + 'px'
			stock.style.left = name.style.right + 'px'
			stock.textContent = "";
			stock.style.color = '#00ff00';
			shopInfo.appendChild(name);
			shopInfo.appendChild(price);
			shopInfo.appendChild(stock);
			createShopItem(data["shopItem"]);
			createShopWeapon(data["shopWeapon"]);
			createShopService(data["shopService"]);
			nextFloor.style.visibility = 'visible';
		}).catch(error => console.error("エラー:", error));
	openTab(shopItemButton, 'tab1');
	shopElement.style.visibility = 'visible';
}

function createShopItem(data) {
	shopItemList.innerHTML = '';
	for (let i = 0, l = data.length / 3; i < l; i++) {
		const li = document.createElement('li');
		li.className = 'shopItem';
		const itemName = document.createElement('div');
		const itemPrice = document.createElement('div');
		const itemStock = document.createElement('div');
		itemName.textContent = data[i * 3];
		itemName.style.width = 200 + 'px';
		itemName.style.position = 'absolute';
		itemName.style.left = 0 + 'px';
		//itemName.style.top = i*parseInt(window.getComputedStyle(shopItemList).fontSize)+'px';
		itemPrice.textContent = data[i * 3 + 1] + "G";
		itemPrice.style.width = 90 + 'px';
		itemPrice.style.position = 'absolute';
		itemPrice.style.textAlign = 'right';
		itemPrice.style.left = parseInt(itemName.style.width) + 'px';
		// itemPrice.style.top = parseInt(itemName.style.top)+'px';
		itemStock.textContent = data[i * 3 + 2] + "個";
		itemStock.style.width = 80 + 'px';
		itemStock.style.position = 'absolute';
		itemStock.style.textAlign = 'right';
		itemStock.style.left = parseInt(itemPrice.style.left) + parseInt(itemPrice.style
			.width) + 'px';
		// itemStock.style.top = parseInt(itemName.style.top)+'px';
		li.appendChild(itemName);
		li.appendChild(itemPrice);
		li.appendChild(itemStock);
		if (data[i * 3 + 2] != '∞') {
			li.style.color = 'yellow';
		} else {
			li.style.color = 'white';
		}
		li.style.position = 'absolute';
		li.style.width = parseInt(itemName.style.width) + parseInt(itemPrice.style.width) +
			parseInt(itemStock.style.width) + 'px';
		li.style.height = window.getComputedStyle(shopItemList).fontSize;
		li.style.top = 5 + i * parseInt(li.style.height) + 'px';
		li.style.left = 5 + 'px';
		shopItemList.appendChild(li);
		//const shopItem = document.getElementsByClassName("shopItem")[i];
	}
}

function createShopWeapon(data) {
	shopWeaponList.innerHTML = '';
	for (let i = 0, l = data.length / 4; i < l; i++) {
		const li = document.createElement('li');
		li.className = 'shopWeapon';
		const weaponName = document.createElement('div');
		const weaponPrice = document.createElement('div');
		const weaponStock = document.createElement('div');
		weaponName.textContent = data[i * 4];
		weaponName.style.width = 200 + 'px';
		weaponName.style.position = 'absolute';
		weaponName.style.left = 0 + 'px';
		//weaponName.style.top = i*parseInt(window.getComputedStyle(shopweaponList).fontSize)+'px';
		weaponPrice.textContent = data[i * 4 + 1] + "G";
		weaponPrice.style.width = 90 + 'px';
		weaponPrice.style.position = 'absolute';
		weaponPrice.style.textAlign = 'right';
		weaponPrice.style.left = parseInt(weaponName.style.width) + 'px';
		// weaponPrice.style.top = parseInt(weaponName.style.top)+'px';
		weaponStock.textContent = data[i * 4 + 2];
		weaponStock.style.width = 80 + 'px';
		weaponStock.style.position = 'absolute';
		weaponStock.style.textAlign = 'right';
		weaponStock.style.left = parseInt(weaponPrice.style.left) + parseInt(
			weaponPrice.style.width) + 'px';
		// weaponStock.style.top = parseInt(weaponName.style.top)+'px';
		li.appendChild(weaponName);
		li.appendChild(weaponPrice);
		li.appendChild(weaponStock);
		li.style.position = 'absolute';
		li.style.width = parseInt(weaponName.style.width) + parseInt(weaponPrice.style
			.width) + parseInt(weaponStock.style.width) + 'px';
		li.style.height = window.getComputedStyle(shopWeaponList).fontSize;
		li.style.top = 5 + i * parseInt(li.style.height) + 'px';
		li.style.left = 5 + 'px';
		shopWeaponList.appendChild(li);
		//const shopItem = document.getElementsByClassName("shopItem")[i];
	}
}

function createShopService(data) {
	shopServiceList.innerHTML = '';
	for (let i = 0, l = data.length / 3; i < l; i++) {
		const li = document.createElement('li');
		li.className = 'shopService';
		const serviceName = document.createElement('div');
		const servicePrice = document.createElement('div');
		const serviceStock = document.createElement('div');
		serviceName.textContent = data[i * 3];
		serviceName.style.width = 200 + 'px';
		serviceName.style.position = 'absolute';
		serviceName.style.left = 0 + 'px';
		//serviceName.style.top = i*parseInt(window.getComputedStyle(shopserviceList).fontSize)+'px';
		servicePrice.textContent = data[i * 3 + 1] + "G";
		servicePrice.style.width = 90 + 'px';
		servicePrice.style.position = 'absolute';
		servicePrice.style.textAlign = 'right';
		servicePrice.style.left = parseInt(serviceName.style.width) + 'px';
		// servicePrice.style.top = parseInt(serviceName.style.top)+'px';
		serviceStock.textContent = data[i * 3 + 2] + "回";
		serviceStock.style.width = 80 + 'px';
		serviceStock.style.position = 'absolute';
		serviceStock.style.textAlign = 'right';
		serviceStock.style.left = parseInt(servicePrice.style.left) + parseInt(
			servicePrice.style.width) + 'px';
		// serviceStock.style.top = parseInt(serviceName.style.top)+'px';
		li.appendChild(serviceName);
		li.appendChild(servicePrice);
		li.appendChild(serviceStock);
		if (data[i * 3 + 2] != '∞') {
			li.style.color = 'yellow';
		} else {
			li.style.color = 'white';
		}
		li.style.position = 'absolute';
		li.style.width = parseInt(serviceName.style.width) + parseInt(servicePrice.style
			.width) + parseInt(serviceStock.style.width) + 'px';
		li.style.height = window.getComputedStyle(shopServiceList).fontSize;
		li.style.top = 5 + i * parseInt(li.style.height) + 'px';
		li.style.left = 5 + 'px';
		shopServiceList.appendChild(li);
		//const shopItem = document.getElementsByClassName("shopItem")[i];
	}
}

shopItemList.addEventListener('click', (event) => {
	const shopItem = event.target.closest('.shopItem');
	if (shopItem) {
		// リスト内のインデックスを取得
		const index = Array.from(shopItemList.children).indexOf(shopItem);
		fetch('/itemBuy', {
				method: 'POST',
				headers: {
					'Content-Type': 'text/plain'
				},
				body: index
			}).then(response => response.json())
			.then(data => {
				if ("itemList" in data) {
					createShopItem(data["itemList"]);
				}
				if ("playerStatus" in data) {
					data["playerStatus"].forEach(item => changeScreen(item));
				}

			}).catch(error => console.error("エラー:", error));

	}
});

shopItemList.addEventListener('mouseover', (event) => {
	const element = event.target;
	if (event.target.tagName === 'UL') {
		return;
	}
	const shopItem = element.closest('.shopItem');
	nowHoverElement = shopItem;
	shopItem.style.outlineWidth = '3px';
	shopItem.style.outlineStyle = 'solid';
	shopItem.style.outlineColor = 'red';
});

shopItemList.addEventListener('mouseout', (event) => {
	const element = event.target;
	if (event.target.tagName === 'UL') {
		return;
	}
	const shopItem = element.closest('.shopItem');
	shopItem.style.outlineStyle = "none";
	nowHoverElement = "";
});

shopWeaponList.addEventListener('click', (event) => {
	const shopWeapon = event.target.closest('.shopWeapon');
	if (shopWeapon) {
		// リスト内のインデックスを取得
		const index = Array.from(shopWeaponList.children).indexOf(shopWeapon);
		fetch('/weaponBuy', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
					'weaponIndex': index
				})
			}).then(response => response.json())
			.then(data => {
				if ("weaponList" in data) {
					createShopWeapon(data["weaponList"]);
				}
				if ("itemList" in data) {
					createShopItem(data["itemList"]);
				}
				if ("serviceList" in data) {
					createShopService(data["serviceList"]);
				}
				if ("playerStatus" in data) {
					data["playerStatus"].forEach(item => changeScreen(item));
				}
			}).catch(error => console.error("エラー:", error));
	}
});

shopWeaponList.addEventListener('mouseover', (event) => {
	const element = event.target;
	if (event.target.tagName === 'UL') {
		return;
	}
	const shopWeapon = element.closest('.shopWeapon');
	shopWeapon.style.outlineWidth = '3px';
	shopWeapon.style.outlineStyle = 'solid';
	shopWeapon.style.outlineColor = 'red';
	nowHoverElement = shopWeapon;
});

shopWeaponList.addEventListener('mouseout', (event) => {
	const element = event.target;
	if (event.target.tagName === 'UL') {
		return;
	}
	const shopWeapon = element.closest('.shopWeapon');
	shopWeapon.style.outlineStyle = "none";
	nowHoverElement = "";
});

shopServiceList.addEventListener('click', (event) => {
	const shopService = event.target.closest('.shopService');
	if (shopService) {
		// リスト内のインデックスを取得
		const index = Array.from(shopServiceList.children).indexOf(shopService);
		fetch('/serviceBuy', {
				method: 'POST',
				headers: {
					'Content-Type': 'text/plain'
				},
				body: index
			}).then(response => response.json())
			.then(data => {
				if ("serviceList" in data) {
					createShopService(data["serviceList"]);
				}
				if ("weaponList" in data) {
					createShopWeapon(data["weaponList"]);
				}
				if ("playerStatus" in data) {
					data["playerStatus"].forEach(item => changeScreen(item));
				}
			}).catch(error => console.error("エラー:", error));
	}
});

shopServiceList.addEventListener('mouseover', (event) => {
	const element = event.target;
	if (event.target.tagName === 'UL') {
		return;
	}
	const shopService = element.closest('.shopService');
	shopService.style.outlineWidth = '3px';
	shopService.style.outlineStyle = 'solid';
	shopService.style.outlineColor = 'red';
	nowHoverElement = shopService;
});

shopServiceList.addEventListener('mouseout', (event) => {
	const element = event.target;
	if (event.target.tagName === 'UL') {
		return;
	}
	const shopService = element.closest('.shopService');
	shopService.style.outlineStyle = "none";
	nowHoverElement = "";
});
