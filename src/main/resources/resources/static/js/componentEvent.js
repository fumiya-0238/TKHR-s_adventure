const list = document.getElementById('itemList');
const commandButton = document.getElementById("commandButton");
function allMouseEventAdd() {
	commandClickFlag = true;
	itemHoverFlag  = true;
	keyListenerFlag  = true;
	console.log("マウスadd");
}

function allMouseEventRemove() {
	commandClickFlag  = false;
	itemHoverFlag  = false;
	keyListenerFlag  = false;
	
	console.log("マウスremove");
}
nextFloor.addEventListener("click", (event) => {
	goNextFloor();
    });
	
const dropDown = document.getElementById("dropDown");
dropDown.addEventListener('change', async (event) => {
	const activeButton = document.querySelector('.tab-menu button.active');
	const sort = event.target.options[event.target.selectedIndex].text;// 選択されたオプションのテキスト
	switch (activeButton.id) {
		case "shopItemButton":
			await fetch('/itemSort',{
								method: 'POST',
								headers: {
									'Content-Type': 'text/plain'
								},
								body: sort
							})
				.then(response => response.json())
				.then(data => showShopItem(data))
				.catch(error => console.error('Error:', error));
				break;
		case "shopWeaponButton":
			break;
		default:
	}
	//  const selectedValue = event.target.value; // 選択されたオプションのvalue
	});
	commandButton.addEventListener("click", commandClick);
		commandButton.addEventListener("mouseover", commandHover);
		commandButton.addEventListener("mouseout", commandOut);

		list.addEventListener("click", itemClick);
		list.addEventListener("mouseover", itemHover);
		list.addEventListener("mouseout", itemOut);
		list.addEventListener("keydown", itemKey);
		list.addEventListener("contextmenu", itemKey);