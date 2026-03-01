
nextFloor.addEventListener("click", () => {
	document.getElementById('narabikae').style.visibility = 'hidden';
	shopSort.style.visibility = 'hidden';
	//shopItemList.innerHTML = '';
	//shopWeaponList.innerHTML = '';
	//shopServiceList.innerHTML = '';
	nextFloor.style.visibility = 'hidden';
	goNextFloor();
});

const shopSort = document.getElementById("shopSort");
shopSort.addEventListener('change', async(event) => {
	const activeButton = document.querySelector('.tab-menu button.active');
	const sort = event.target.options[event.target.selectedIndex].text; // 選択されたオプションのテキスト
	switch (activeButton.id) {
		case "shopItemButton":
			await fetch('/itemSort', {
					method: 'POST',
					headers: {
						'Content-Type': 'text/plain'
					},
					body: sort
				})
				.then(response => response.json())
				.then(data => createShopItem(data))
				.catch(error => console.error('Error:', error));
			break;
		case "shopWeaponButton":
			await fetch('/weaponSort', {
					method: 'POST',
					headers: {
						'Content-Type': 'text/plain'
					},
					body: sort
				})
				.then(response => response.json())
				.then(data => createShopWeapon(data))
				.catch(error => console.error('Error:', error));
			break;
		default:
	}
});


itemList.addEventListener("click", itemClick);
itemList.addEventListener("mouseover", itemHover);
itemList.addEventListener("mouseout", itemOut);
