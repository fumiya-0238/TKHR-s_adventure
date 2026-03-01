const weaponName = document.getElementById("weaponName");
const closeHover = (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
};
const closeOut = (event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
};


weaponName.addEventListener('mouseover', (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
});

weaponName.addEventListener('mouseout', async(event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
});


weaponName.addEventListener('mouseover', (event) => {
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
});

weaponName.addEventListener('mouseout', async(event) => {
	const element = event.target;
	element.style.outlineStyle = "none";
});

function makeMyWeaponInfo(data) {
	const nameFontSize = 30;
	const textFontSize = 20
	const gap = 150;
	const kind = ["装備武器", "融合武器"];
	let location = {
		left: 0,
		top: 0
	}
	let superDiv;
	const weaponLine = document.createElement('img');
	weaponLine.style.position = 'absolute';
	weaponLine.style.width = '427px';
	weaponLine.style.height = '8px';
	weaponLine.style.top = '150px';
	weaponLine.className = 'image';
	weaponLine.src = '/images/weaponLine.png';
	for (let i = 0, l = data.length; i < l; i++) {
		if (data[i].includes("weapon")) {
			location.left = 0;
			location.top = 0;
			if (superDiv) {
				infoBody.appendChild(superDiv);
			}
			const index = data[i++].substring(6);
			superDiv = document.createElement('div');
			superDiv.className = 'infoText';
			superDiv.style.left = '5px';
			superDiv.style.maxWidth = "417px";
			superDiv.style.top = index * gap + 60 + 'px';
			superDiv.style.fontSize = '20px';
			const name = data[i++];
			const nameWidth = name.length * nameFontSize
			const weaponName = document.createElement('div');
			weaponName.className = 'myWeaponName';
			weaponName.textContent = name;
			weaponName.style.fontSize = nameFontSize + 'px';
			weaponName.style.position = 'absolute';
			weaponName.style.left = (427 - nameWidth) / 2 + 'px';
			weaponName.style.top = index * gap + 5 + 'px';
			weaponName.style.width = nameWidth + 'px';
			weaponName.style.color = "#ed6c00";
			infoBody.appendChild(weaponName);
			const mainWidth = 4 * textFontSize;
			const weaponMain = document.createElement('div');
			weaponMain.className = 'myWeaponMain';
			weaponMain.textContent = kind[index];
			weaponMain.style.fontSize = textFontSize + 'px';
			weaponMain.style.position = 'absolute';
			weaponMain.style.left = '5px';
			weaponMain.style.top = index * gap + 5 + 'px';
			weaponMain.style.width = mainWidth + 'px';
			weaponMain.style.color = "#00ff00";
			infoBody.appendChild(weaponMain);
			if (index == 0) {
				const attack = data[i];
				const weaponAttack = document.createElement('div');
				weaponAttack.id = 'weaponAttack';
				weaponAttack.textContent = "攻撃力:" + attack;
				weaponAttack.style.fontSize = textFontSize + 'px';
				weaponAttack.style.position = 'absolute';
				weaponAttack.style.left = '300px';
				weaponAttack.style.top = parseInt(weaponName.style.top) + nameFontSize +
					'px';
				weaponAttack.style.whiteSpace = 'nowrap';
				infoBody.appendChild(weaponAttack);
			}
			i++;

		}
		location = sliceDiv(superDiv, data[i], textFontSize, location);
	}
	infoBody.appendChild(weaponLine);
	infoBody.appendChild(superDiv);
}

infoBody.addEventListener('mouseover', (event) => {
	// クリックされた要素が "target" クラスを持つか確認
	const element = event.target;
	if (element.classList.contains('infoLink')||element.classList.contains('playerConditions')||element.classList.contains('monsterConditions')) {
		element.style.outlineWidth = '3px';
		element.style.outlineStyle = 'solid';
		element.style.outlineColor = 'red';
	}
});
infoBody.addEventListener('mouseout', async(event) => {
	// クリックされた要素が "target" クラスを持つか確認
	const element = event.target;
	if (element.classList.contains('infoLink')||element.classList.contains('playerConditions')||element.classList.contains('monsterConditions')) {
		element.style.outlineStyle = "none";
	}
});

function getTextWidth(text, fontSize, fontFamily) {
    const canvas = document.createElement('canvas');
    const context = canvas.getContext('2d');
    context.font = `${fontSize}px ${fontFamily}`;
    const metrics = context.measureText(text);
    return metrics.width;
	
}
function sliceDiv(superDiv, data, textFontSize, location) {
	const arrays = data.split("/");
	const textWidth = getTextWidth(arrays[0],textFontSize,window.getComputedStyle(infoBody).fontFamily) + 6;
	const newLeft = textWidth + location.left;
	console.log(arrays[0]);
	console.log(window.getComputedStyle(infoBody).fontFamily);
	
	
	console.log(textWidth);
	console.log(newLeft);
	tempDiv = document.createElement('div');
	tempDiv.style.position = 'absolute';
	if (427 < newLeft) {
		switch (arrays[1]) {
			case "infoLink":
				location.top += textFontSize;
				tempDiv.className = arrays[1];
				tempDiv.textContent = arrays[0];
				tempDiv.style.left = 0 + 'px';
				tempDiv.style.top = location.top + 'px';
				tempDiv.style.width = textWidth + 'px';
				superDiv.appendChild(tempDiv);
				location.left = textWidth;
				break;
			default:
				const n = (427 - location.left) / textFontSize;
				const divSlice = arrays[0].slice(0, n);
				tempDiv.textContent = divSlice;
				tempDiv.style.left = location.left + 'px';
				tempDiv.style.top = location.top + 'px';
				tempDiv.style.width = divSlice.length * textFontSize + 'px';
				superDiv.appendChild(tempDiv);
				location.left = 0;
				location.top += textFontSize;
				location = sliceDiv(superDiv, arrays[0].slice(n), textFontSize, location);
		}
	} else {
		tempDiv.style.width = textWidth + 'px';
		tempDiv.style.top = location.top + 'px';
		tempDiv.style.left = location.left + 'px';
		tempDiv.textContent = arrays[0];
		location.left = newLeft;
		superDiv.appendChild(tempDiv);
	}
	if (arrays[1] == "infoLink") {
		tempDiv.className = arrays[1];
	}
	if (arrays[1] == "br") {
		location.left = 0;
		location.top += textFontSize;
	}
	return location;
}

function makeItemInfo(data) {
	const nameFontSize = 30;
	const textFontSize = 20;
	let location = {
		left: 0,
		top: 0
	}
	const nameWidth = data[0].length * nameFontSize
	const itemName = document.createElement('div');
	itemName.id = 'itemName';
	itemName.textContent = data[0];
	itemName.style.fontSize = nameFontSize + 'px';
	itemName.style.position = 'absolute';
	itemName.style.left = (427 - nameWidth) / 2 + 'px';
	itemName.style.top = '5px';
	itemName.style.width = nameWidth + 'px';
	itemName.style.color = "#00ff00";

	const itemPrice = document.createElement('div');
	itemPrice.id = 'itemPrice';
	itemPrice.textContent = "買値:" + data[1];
	itemPrice.style.fontSize = '20px';
	itemPrice.style.position = 'absolute';
	itemPrice.style.left = '300px';
	itemPrice.style.top = parseInt(itemName.style.top) + nameFontSize + 'px';
	itemPrice.style.whiteSpace = 'nowrap';
	infoBody.appendChild(itemName);
	infoBody.appendChild(itemPrice);
	const superDiv = document.createElement('div');
	superDiv.className = 'infoText';
	superDiv.style.left = '5px';
	superDiv.style.maxWidth = "417px";
	superDiv.style.top = '70px';
	superDiv.style.fontSize = '20px';
	for (let i = 2, l = data.length; i < l; i++) {
		location = sliceDiv(superDiv, data[i], textFontSize, location);
	}
	infoBody.appendChild(superDiv);
}

function makeWeaponInfo(data) {
	const nameFontSize = 30;
	const textFontSize = 20;
	let location = {
		left: 0,
		top: 0
	}
	const nameWidth = data[0].length * nameFontSize
	const weaponName = document.createElement('div');
	weaponName.id = 'weaponName';
	weaponName.textContent = data[0];
	weaponName.style.fontSize = nameFontSize + 'px';
	weaponName.style.position = 'absolute';
	weaponName.style.left = (427 - nameWidth) / 2 + 'px';
	weaponName.style.top = '5px';
	weaponName.style.width = nameWidth + 'px';
	weaponName.style.color = "#ed6c00";
	
	const weaponPrice = document.createElement('div');
	weaponPrice.id = 'weaponPrice';
	weaponPrice.textContent = "買値:" + data[2];
	weaponPrice.style.fontSize = textFontSize + 'px';
	weaponPrice.style.position = 'absolute';
	weaponPrice.style.left = '300px';
	weaponPrice.style.top = parseInt(weaponName.style.top) + nameFontSize + 'px';
	weaponPrice.style.whiteSpace = 'nowrap';
	
	const weaponAttack = document.createElement('div');
	weaponAttack.id = 'weaponAttack';
	weaponAttack.textContent = "攻撃力:" + data[1];
	weaponAttack.style.fontSize = textFontSize + 'px';
	weaponAttack.style.position = 'absolute';
	weaponAttack.style.left = '170px';
	weaponAttack.style.top = parseInt(weaponName.style.top) + nameFontSize + 'px';
	weaponAttack.style.whiteSpace = 'nowrap';


	infoBody.appendChild(weaponName);
	infoBody.appendChild(weaponAttack);
	infoBody.appendChild(weaponPrice);
	const superDiv = document.createElement('div');
	superDiv.className = 'infoText';
	superDiv.style.left = '5px';
	superDiv.style.maxWidth = "417px";
	superDiv.style.top = '70px';
	superDiv.style.fontSize = '20px';
	for (let i = 3, l = data.length; i < l; i++) {
		location = sliceDiv(superDiv, data[i], textFontSize, location);
	}
	infoBody.appendChild(superDiv);
}

function makeActionInfo(data) {
	const nameFontSize = 30;
	const textFontSize = 20;
	let location = {
		left: 0,
		top: 0
	}
	const nameWidth = data[0].length * nameFontSize
	const actionName = document.createElement('div');
	actionName.id = 'actionName';
	actionName.textContent = data[0];
	actionName.style.fontSize = nameFontSize + 'px';
	actionName.style.position = 'absolute';
	actionName.style.left = (427 - nameWidth) / 2 + 'px';
	actionName.style.top = '5px';
	actionName.style.width = nameWidth + 'px';
	actionName.style.color = "#ff0000";

	const actionAttackIs = document.createElement('div');
	actionAttackIs.id = 'actionAttackIs';
	actionAttackIs.textContent = "分類:" + data[1];
	actionAttackIs.style.fontSize = textFontSize + 'px';
	actionAttackIs.style.position = 'absolute';
	actionAttackIs.style.left = '300px';
	actionAttackIs.style.top = parseInt(actionName.style.top) + textFontSize +
		'px';
	actionAttackIs.style.whiteSpace = 'nowrap';
	infoBody.appendChild(actionName);
	infoBody.appendChild(actionAttackIs);
	const superDiv = document.createElement('div');
	superDiv.className = 'infoText';
	superDiv.style.left = '5px';
	superDiv.style.maxWidth = "417px";
	superDiv.style.top = '70px';
	superDiv.style.fontSize = '20px';
	for (let i = 2, l = data.length; i < l; i++) {
		location = sliceDiv(superDiv, data[i], textFontSize, location);
	}
	infoBody.appendChild(superDiv);
}

function makeConditionInfo(data) {
	const nameFontSize = 30;
	const textFontSize = 20;
	let location = {
		left: 0,
		top: 0
	}
	const nameWidth = data[0].length * nameFontSize
	const conditionName = document.createElement('div');
	conditionName.id = 'conditionName';
	conditionName.textContent = data[0];
	conditionName.style.fontSize = nameFontSize + 'px';
	conditionName.style.position = 'absolute';
	conditionName.style.left = (427 - nameWidth) / 2 + 'px';
	conditionName.style.top = '5px';
	conditionName.style.width = nameWidth + 'px';
	conditionName.style.color = "#00ffff";

	const conditionDuplication = document.createElement('div');
	conditionDuplication.id = 'conditionDuplication';
	conditionDuplication.textContent = "重ねがけ:" + data[1];
	conditionDuplication.style.fontSize = textFontSize + 'px';
	conditionDuplication.style.position = 'absolute';
	conditionDuplication.style.left = '300px';
	conditionDuplication.style.top = parseInt(conditionName.style.top) +
		textFontSize + 10 +
		'px';
	conditionDuplication.style.whiteSpace = 'nowrap';
	infoBody.appendChild(conditionName);
	infoBody.appendChild(conditionDuplication);
	const superDiv = document.createElement('div');
	superDiv.className = 'infoText';
	superDiv.style.left = '5px';
	superDiv.style.maxWidth = "417px";
	superDiv.style.top = '70px';
	superDiv.style.fontSize = '20px';
	for (let i = 2, l = data.length; i < l; i++) {
		location = sliceDiv(superDiv, data[i], textFontSize, location);
	}
	infoBody.appendChild(superDiv);
}

function makeMonsterInfo(data) {
	const nameFontSize = 30;
	const textFontSize = 20;
	let location = {
		left: 0,
		top: 0
	}
	const nameWidth = data[0].length * nameFontSize
	const infoMonsterName = document.createElement('div');
	//infoinfoMonsterName.id = 'infoinfoMonsterName';
	infoMonsterName.textContent = data[0];
	infoMonsterName.style.fontSize = nameFontSize + 'px';
	infoMonsterName.style.position = 'absolute';
	infoMonsterName.style.left = (427 - nameWidth) / 2 + 'px';
	infoMonsterName.style.top = '5px';
	infoMonsterName.style.width = nameWidth + 'px';
	infoMonsterName.style.color = "#ffffff";

	const infoMonsterHP = document.createElement('div');
	infoMonsterHP.textContent = "HP:"+data[1];
	infoMonsterHP.style.fontSize = textFontSize + 'px';
	infoMonsterHP.style.position = 'absolute';
	infoMonsterHP.style.left = '5px';
	infoMonsterHP.style.top = '35px';
	infoMonsterHP.style.color = "#ffffff";
	infoMonsterHP.style.whiteSpace = 'nowrap';
	
	const infoMonsterOverHP = document.createElement('div');
	infoMonsterOverHP.textContent = "オーバーHP:"+data[2];
	infoMonsterOverHP.style.fontSize = textFontSize + 'px';
	infoMonsterOverHP.style.position = 'absolute';
	infoMonsterOverHP.style.left = '100px';
	infoMonsterOverHP.style.top = '35px';
	infoMonsterOverHP.style.color = "#ff7f27";
	infoMonsterOverHP.style.whiteSpace = 'nowrap';

	const infoMonsterAttack = document.createElement('div');
	infoMonsterAttack.textContent = "攻撃力:"+data[3];
	infoMonsterAttack.style.fontSize = textFontSize + 'px';
	infoMonsterAttack.style.position = 'absolute';
	infoMonsterAttack.style.left = '300px';
	infoMonsterAttack.style.top = '35px';
	infoMonsterAttack.style.color = "#ffffff";
	infoMonsterAttack.style.whiteSpace = 'nowrap';
	
	const infoMonsterEXP = document.createElement('div');
	//infoMonsterEXP.id = 'infoMonsterEXP';
	infoMonsterEXP.textContent = "経験値:" + data[4];
	infoMonsterEXP.style.fontSize = textFontSize + 'px';
	infoMonsterEXP.style.position = 'absolute';
	infoMonsterEXP.style.left = '5px';
	infoMonsterEXP.style.top = parseInt(infoMonsterHP.style.top) +
		textFontSize +
		'px';
		infoMonsterEXP.style.color = "#ffffff";
	infoMonsterEXP.style.whiteSpace = 'nowrap';

	const infoMonsterGold = document.createElement('div');
	infoMonsterGold.textContent = "お金:" + data[5];
	infoMonsterGold.style.fontSize = textFontSize + 'px';
	infoMonsterGold.style.position = 'absolute';
	infoMonsterGold.style.left = '130px';
	infoMonsterGold.style.top = parseInt(infoMonsterHP.style.top) +
		textFontSize +
		'px';
		infoMonsterGold.style.color = "#ffffff";
	infoMonsterGold.style.whiteSpace = 'nowrap';

	const infoMonsterTurn = document.createElement('div');
	infoMonsterTurn.textContent = "ボーナスターン:" + data[6];
	infoMonsterTurn.style.fontSize = textFontSize + 'px';
	infoMonsterTurn.style.position = 'absolute';
	infoMonsterTurn.style.left = '240px';
	infoMonsterTurn.style.color = "#ff7f27";
	infoMonsterTurn.style.top = parseInt(infoMonsterHP.style.top) +
		textFontSize +
		'px';
	infoMonsterTurn.style.whiteSpace = 'nowrap';

	infoBody.appendChild(infoMonsterName);
	infoBody.appendChild(infoMonsterHP);
	infoBody.appendChild(infoMonsterOverHP);
	infoBody.appendChild(infoMonsterAttack);
	infoBody.appendChild(infoMonsterEXP);
	infoBody.appendChild(infoMonsterGold);
	infoBody.appendChild(infoMonsterTurn);
	const superDiv = document.createElement('div');
	superDiv.className = 'infoText';
	superDiv.style.left = '5px';
	superDiv.style.maxWidth = "417px";
	superDiv.style.top = '90px';
	superDiv.style.fontSize = '20px';
	for (let i = 7, l = data.length; i < l; i++) {
		location = sliceDiv(superDiv, data[i], textFontSize, location);
	}
	infoBody.appendChild(superDiv);
}
