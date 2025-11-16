async function battleToTitle() {
	stopAudio();
	visibleTitle();
	hideBattle();
	titleFlag = true;
	monsterElement.style.visibility = 'hidden';
	messageWindow.style.visibility = 'hidden';
	messageText.style.visibility = 'hidden';
	monsterBeat.textContent = "";
	bonusResult.textContent = "";
	expResult.textContent = "";
	levelUp.textContent = "";
	cockatrice.innerHTML = '';
}
async function changeScreen(item) {
	if (titleFlag) {
		return;
	}
	const change = item.split("/");
	console.log(change);
	switch (change[0]) {
		case "DungeonStart":
		await getMonster();
		visibleBattle();
		hideTitle();
		break;
		case "SetMonsterName":
			monsterName.textContent = `${change[1]}`;
			break;
		case "SetMonsterHP":
			monsterHP.textContent = `HP${change[1]}`;
			break;
		case "SetMonsterOverHP":
			const overHP = document.getElementById("overHP");
			overHP.textContent = `(${change[1]})`;
			overHP.style.left = parseInt(monsterHP.style.left) + monsterHP.clientWidth +
				'px';
			overHP.style.top = parseInt(monsterHP.style.top) + 'px';
			break;
		case "SetMonsterAttack":
			monsterATK.textContent = `攻撃力${change[1]}`;
			break;
		case "SetMonsterEXP":
			monsterEXP.textContent = `経験値${change[1]}`;
			break;
		case "SetMonsterGold":
			monsterGold.textContent = `お金${change[1]}`;
			break;
		case "SetMonsterTurn":
			monsterTurn.style.visibility = 'visible';
			monsterTurn.textContent = `ボーナス残り${change[1]}ターン`;
			break;
		case "DeleteMonsterTurn":
			monsterTurn.style.visibility = 'hidden';
			break;
		case "SetMonsterDamage":
			monsterDamage.style.color = "#ff0000";
			monsterDamage.textContent = `${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					monsterDamage.textContent = '';
					resolve();
				}, 300);
			});
			monsterDamage.style.left = parseInt(monsterElement.style.left) +
				monsterElement.clientWidth / 2 - monsterDamage.textContent.length *
				parseInt(window.getComputedStyle(monsterDamage).fontSize) / 2.8 + 'px';
			monsterDamage.style.top = parseInt(titleLocation.top) + 'px';
			monsterElement.style.visibility = 'hidden';
			damageSound.currentTime = 0;
			damageSound.play();
			await new Promise(resolve => setTimeout(resolve, 80));
			monsterElement.style.visibility = 'visible';
			break;
		case "SetMonsterHeal":
			monsterDamage.style.color = "#00ff00";
			monsterDamage.textContent = `${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					monsterDamage.textContent = '';
					resolve();
				}, 300);
			});
			monsterDamage.style.left = parseInt(monsterElement.style.left) +
				monsterElement.clientWidth / 2 - monsterDamage.textContent.length *
				parseInt(window.getComputedStyle(monsterDamage).fontSize) / 2.8 + 'px';
			monsterDamage.style.top = parseInt(titleLocation.top) + 'px';
			healSound.currentTime = 0;
			healSound.play();
			break;
		case "ResetMonsterDamage":
			monsterDamage.textContent = "";
			break;
		case "ShowMonster":
			await getMonster();
			monsterElement.style.visibility = 'visible';
			break;
		case "DeleteMonster":
			await new Promise(resolve => setTimeout(resolve, 60));
			monsterElement.style.visibility = 'hidden';
			break;
		case "MonsterHidden":
			monsterElement.style.visibility = 'hidden';
			break;
		case "MonsterDeath":
			monsterElement.style.visibility = 'hidden';
			monsterBeat.textContent = `${change[1]}を倒した`;
			break;
		case "CockatriceOn":
			const img = document.createElement('img');
			img.style.left = commandElements[0].style.left;
			img.style.top = parseInt(commandElements[0].style.top) + 23 * change[1] +
				'px';
			commandElements[change[1]].style.visibility = 'hidden';
			//
			img.className = 'image';
			img.classList.add('cockatriceSwitch');
			img.style.position = 'absolute';
			img.src = '/images/コカトリスイッチ.png';
			cockatrice.appendChild(img);
			break;
		case "CockatriceOff":
			cockatrice.innerHTML = '';
			for (let i = 0; i < 5; i++) {
				commandElements[i].style.visibility = 'visible';
			}
			break;
		case "SetPlayerLV":
			levelElement.textContent = `LV${change[1]}`;
			break;
		case "SetPlayerHP":
			document.getElementById("HP").textContent = `HP${change[1]}`;
			break;
		case "SetPlayerMaxHP":
			maxHP.textContent = `/${change[1]}`;
			maxHP.style.left = parseInt(HP.style.left) + HP.clientWidth + 'px';
			maxHP.style.top = parseInt(HP.style.top) + 'px';
			break;
		case "SetPlayerAttack":
			document.getElementById("ATK").textContent = `攻撃力${change[1]}`;
			break;
		case "SetPlayerWeaponAttack":
			weaponAttack.style.left = parseInt(playerAttack.style.left) + playerAttack.clientWidth +
				'px';
			weaponAttack.style.top = parseInt(playerAttack.style.top) + 'px';
			weaponAttack.textContent = `(${change[1]})`;
			break;
		case "SetPlayerCritical":
			document.getElementById("criticalAttack").textContent =
				`強攻撃(残り${change[1]}回)`;
			break;
		case "SetPlayerTension":
			document.getElementById("tension").textContent = `気合${change[1]}%`;
			break;
		case "UpPlayerTension":
			upTensionSound.currentTime = 0;
			upTensionSound.play();
			playerTensionUp.style.left = parseInt(tension.style.left) + tension.clientWidth +
				5 +
				'px';
			playerTensionUp.style.top = parseInt(tension.style.top) + 'px';
			playerTensionUp.style.color = "#00ff00";
			playerTensionUp.textContent = `+${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					playerTensionUp.textContent = ''; // 0.1秒後に文字を消す
					resolve(); // Promiseを解決
				}, 300); // 100ms = 0.1秒
			});
			break;
		case "CounterAttack":
			await new Promise(resolve => setTimeout(resolve, 300));
			monsterActionSound.currentTime = 0;
			monsterActionSound.play();
			break;
		case "DownPlayerTension":
			playerTensionUp.style.left = parseInt(tension.style.left) + tension.clientWidth +
				5 +
				'px';
			playerTensionUp.style.top = parseInt(tension.style.top) + 'px';
			playerTensionUp.style.color = "#ff0000";
			playerTensionUp.textContent = `-${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					playerTensionUp.textContent = ''; // 0.1秒後に文字を消す
					resolve(); // Promiseを解決
				}, 300); // 100ms = 0.1秒
			});
			break;
		case "SetPlayerBoost":
			const boost = change[1];
			var ctx = boostElement.getContext('2d');
			ctx.fillStyle = '#00ff00';
			ctx.fillRect(0, 0, boost, boostElement.clientHeight);
			ctx.fillRect(0, 0, boost, boostElement.clientHeight);
			ctx.fillStyle = 'black';
			ctx.fillRect(boost, 0, boostElement.clientWidth, boostElement.clientHeight);
			percentElement.textContent = `${boost}%`;
			break;
		case "BoostOn":
			for (let i = 0, l = commandElements.length; i < l; i++) {
				commandElements[i].style.color = 'red';
			}
			boostClickSound.currentTime = 0;
			boostClickSound.play();
			break;
		case "BoostOff":
			for (let i = 0, l = commandElements.length; i < l; i++) {
				commandElements[i].style.color = 'white';
			}
			clickSound.currentTime = 0;
			clickSound.play();
			break;
		case "DefenceSuccess":
			defenceSound.currentTime = 0;
			defenceSound.play();
			break;
		case "SetPlayerWeapon":
			weaponNameElement.textContent = `${change[1]}`;
			break;
		case "SetPlayerSubWeapon":
			subWeaponNameElement.textContent = `${change[1]}`;
			break;
		case "SetPlayerEXP":
			document.getElementById("EXP").textContent = `残り経験値${change[1]}`;
			break;
		case "SetPlayerSumEXP":
			sumEXP.textContent = `獲得経験値:${change[1]}`;
			break;
		case "SetPlayerGold":
			document.getElementById("Gold").textContent = `所持金${change[1]}`;
			break;
		case "PlayerDamage":
			damageSound.currentTime = 0;
			damageSound.play();
			playerDamage.style.left = parseInt(maxHP.style.left) + maxHP.clientWidth + 5 +
				'px';
			playerDamage.style.top = parseInt(maxHP.style.top) + 'px';
			playerDamage.style.color = "#ff0000";
			playerDamage.textContent = `-${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					playerDamage.textContent = ''; // 0.1秒後に文字を消す
					resolve(); // Promiseを解決
				}, 300); // 100ms = 0.1秒
			});
			break;
		case "DeletePlayerDamage":
			playerDamage.textContent = "";
			break;
		case "SetPlayerHeal":
			healSound.currentTime = 0;
			healSound.play()
			playerDamage.style.left = parseInt(maxHP.style.left) + maxHP.clientWidth + 5 +
				'px';
			playerDamage.style.top = parseInt(maxHP.style.top) + 'px';
			playerDamage.style.color = "#00ff00";
			playerDamage.textContent = `+${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					playerDamage.textContent = ''; // 0.1秒後に文字を消す
					resolve(); // Promiseを解決
				}, 300); // 100ms = 0.1秒
			});
			//new Promise(resolve => setTimeout(resolve, 300));
			/*
			setTimeOut(() =>{
				playerDamage.textContent = "";
			},300);*/
			break;
		case "HPNormal":
			maxHP.style.color = "#ffffff";
			HP.style.color = "#ffffff";
			break;
		case "HPHalf":
			maxHP.style.color = 'yellow';
			HP.style.color = 'yellow';
			break;
		case "HPPinch":
			maxHP.style.color = '#ff4500';
			HP.style.color = '#ff4500';
			break;
		case "Poisoned":
			playerAttack.style.color = '#da00d3';
			weaponAttack.style.color = '#da00d3';
			break;
		case "PoisonHeal":
			playerAttack.style.color = 'white';
			weaponAttack.style.color = 'white';
			break;

		case "DefenceWait":
			await new Promise(resolve => setTimeout(resolve, 300));
			break;
		case "AddItem":
			const newItem = document.createElement('li');
			newItem.textContent = change[1];
			//newItem.setAttribute('tabindex', '0');
			newItem.className = 'infoButton';
			itemList.appendChild(newItem);
			break;
		case "RemoveItem":
			const remove = itemList.getElementsByTagName('li');
			list.removeChild(remove[parseInt(change[1])]);
			break;
		case "ActiveItem":
			const active = itemList.getElementsByTagName('li');
			active[parseInt(change[1])].style.color = 'red';
			//document.getElementById("weaponATK").textContent = `HP${change[1]}`;
			break;
		case "SetSumTurn":
			sumTurn.textContent = `計${change[1]}ターン`;
			break;
		case "SetDifficulty":
			difficulty.textContent = `${change[1]}`;
			break;
		case "SetSumFloor":
			const floor = document.getElementById("floor");
			floor.style.left = parseInt(difficulty.style.left) + parseInt(difficulty.clientWidth) +
				'px';
			floor.style.top = parseInt(difficulty.style.top) + 'px';
			floor.textContent = `(${change[1]})`;
			document.getElementById("floor").textContent = `${change[1]}階`;
			break;
		case "Stop":
			await waitForClick();
			break;
		case "NextFloor":
			goNextFloor();
			break;
		case "Result":
			const arrays = change[1].split("#");
			expResult.textContent = `経験値を${arrays[0]}、お金を${arrays[1]}G獲得`;
			switch (arrays[2]) {
				case "0":
					bonusResult.textContent = "";
					expResult.style.top = parseInt(monsterBeat.style.top) + resultMesSize + 'px';
					levelUp.style.top = parseInt(expResult.style.top) + expResult.clientHeight + 'px';
					break;
				case "1":
					bonusResult.textContent = "ボーナスを1つゲット　経験値とお金が1.3倍";
					expResult.style.top = parseInt(bonusResult.style.top) + resultMesSize + 'px';
					levelUp.style.top = parseInt(expResult.style.top) + expResult.clientHeight + 'px';
					break;
				case "2":
					bonusResult.textContent = "ボーナスを2つゲット　経験値とお金が1.5倍";
					expResult.style.top = parseInt(bonusResult.style.top) + resultMesSize * 2 + 'px';
					levelUp.style.top = parseInt(expResult.style.top) + expResult.clientHeight + 'px';
					break;
			}
			break;
		case "LevelUp":
			levelUp.textContent = `レベルが${change[1]}に上がった`;
			break;
		case "WindowMessage":
			messageText.textContent = `${change[1]}`;
			messageWindow.style.visibility = 'visible';
			messageText.style.visibility = 'visible';
			break;
		case "Shop":
			createShop();
			break;
		case "FusionMode":
			openTab(shopWeaponButton, 'tab2');
			fusionMode = true;
			cancelFusion.style.visibility = 'visible';
			nextFloor.style.visibility = 'hidden';
			break;
		case "FusionCancel":
			openTab(shopServiceButton, 'tab3');
			fusionMode = false;
			cancelFusion.style.visibility = 'hidden';
			nextFloor.style.visibility = 'visible';
			break;
		case "CommandText":
			commandMessages.length = 0;
			change[1].split("#").forEach(item => {
				commandMessages.push(item);
			});
			break;
		case "AttackEffect":
			effectElement.style.visibility = 'visible';
			const src = effectElement.src;
			effectElement.src = '';
			effectElement.src = src;
			await new Promise(resolve => setTimeout(resolve, 200));
			effectElement.style.visibility = 'hidden';
			break;
		case "MonsterActionEffect":
			monsterActionSound.currentTime = 0;
			monsterActionSound.play();
			const canvas = document.createElement('canvas');
			const ctxMonster = canvas.getContext('2d');

			// Canvasを画像と同じサイズに設定
			canvas.width = monsterElement.width;
			canvas.height = monsterElement.height;

			// 画像をCanvasに描画
			ctxMonster.drawImage(monsterElement, 0, 0);

			// 画像のピクセルデータを取得
			const imageData = ctxMonster.getImageData(0, 0, canvas.width, canvas.height);
			const data = imageData.data;

			// 不透明なピクセルのみを白くする
			for (let i = 0; i < data.length; i += 4) {
				if (data[i + 3] > 0) { // アルファ値が0より大きい（不透明）場合
					data[i] = 255; // R
					data[i + 1] = 255; // G
					data[i + 2] = 255; // B
				}
			}

			// 変更したデータをソースキャンバスに適用
			ctxMonster.putImageData(imageData, 0, 0);

			// ターゲットキャンバスを取得
			//const monsterEffect = document.getElementById('monsterEffect');
			//const targetCtx = monsterEffect.getContext('2d');
			canvas.style.position = 'absolute';
			canvas.style.top = monsterElement.offsetTop + 'px';
			canvas.style.left = monsterElement.offsetLeft + 'px';
			//	canvas.style.zIndex = '1';
			monsterElement.parentNode.appendChild(canvas);
			await new Promise(resolve => setTimeout(resolve, 300));
			canvas.remove();
			break;
		case "PlayerConditionMessage":
			const playerNewCondition = document.createElement('li');
			playerNewCondition.className = 'condition-item';
			playerNewCondition.textContent = `${change[1]}`;
			playerNewCondition.style.color = '#00ff00';
			animateAndRemoveItem(playerNewCondition);
			break;
		case "MonsterConditionMessage":
			const monsterNewCondition = document.createElement('li');
			monsterNewCondition.className = 'condition-item';
			monsterNewCondition.textContent = `${change[1]}`;
			monsterNewCondition.style.color = '#ff0000';
			animateAndRemoveItem(monsterNewCondition);
			break;
		case "Buy":
			buySound.currentTime = 0;
			buySound.play();
			playerGoldGet.style.left = parseInt(gold.style.left) + gold.clientWidth + 5 +
				'px';
			playerGoldGet.style.top = parseInt(gold.style.top) + 'px';
			playerGoldGet.style.color = "#ff0000";
			playerGoldGet.textContent = `-${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					playerGoldGet.textContent = ''; // 0.1秒後に文字を消す
					resolve(); // Promiseを解決
				}, 300); // 100ms = 0.1秒
			});
			break;
		case "GetMoney":
			///buySound.currentTime = 0;
			//buySound.play();
			playerGoldGet.style.left = parseInt(gold.style.left) + gold.clientWidth + 5 +
				'px';
			playerGoldGet.style.top = parseInt(gold.style.top) + 'px';
			playerGoldGet.style.color = "#00ff00";
			playerGoldGet.textContent = `+${change[1]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					playerGoldGet.textContent = '';
					resolve(); // Promiseを解決
				}, 300); // 100ms = 0.1秒
			});
			break;
		case "NoEnough":
			noEnoughMoneySound.currentTime = 0;
			noEnoughMoneySound.play();
			break;
		case "CommandClick":
			commandClickSound.currentTime = 0;
			commandClickSound.play();
			break;
		case "ClickSound":
			clickSound.currentTime = 0;
			clickSound.play();
			break;
		case "CommandOK":
			commandClickFlag = false;
			commandHoverFlag = false;
			nowHoverCommand.style.outlineStyle = "none";
			break;
		case "Wait":
			await new Promise(resolve => setTimeout(resolve, parseInt(change[1])));
			break;
		case "TurnStart":
			await fetch('/turnStart');
			commandHoverFlag = true;
			commandClickFlag = true;
			break;
		case "GameOver":
			alert('ゲームオーバー');
			battleToTitle();
			break;
		case "GameClear":
			alert('クリア');
			battleToTitle();
			break;
		case "Return":
			break;
		case "DebugRoom":
			const dungeons = change[1].split("%");
			for(let i=0,l=dungeons.length;i<l;i++){
				showFloors(dungeons[i],i);
			}
		debug.style.visibility = 'visible';
		break;
	}
}

function showFloors(dungeon,n){
	const newList = document.createElement('ul');
	newList.className = 'dungeonList';
	newList.classList.add('text');
	newList.style.top = '5px';
	newList.style.left =n * 150 + 10 +'px';
	const dungeons = dungeon.split("#");
	for(let i=0,l=dungeons.length;i<l;i++){
		const newItem = document.createElement('li');
		newItem.className = 'floorButton';
		newItem.textContent = dungeons[i];
		newItem.style.width = '150px';
		newList.appendChild(newItem);		
	}
	debugDungeons.appendChild(newList);
}

async function animateAndRemoveItem(item) {
	conditionMessage.appendChild(item);
	await delay(0);
	item.classList.add('moved-up');

	await delay(1000);
	item.classList.add('slide-out');

	item.style.opacity = '0';
	item.style.margin = '0';
	item.style.padding = '0';
	item.style.height = '0';

	await delay(500);
	item.remove();
}

const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));
// マウスクリックを待つ関数
async function waitForClick() {
	const wait = new Promise((resolve) => {
		const handler = (event) => {
			if (!event.target.closest(".infoButton") && !betweenInfo) {
				document.removeEventListener("click", handler); // 手動でリスナー解除
				resolve(); // info以外をクリックしたら解決
			}
		};
		document.addEventListener("click", handler);
	});
	await wait;
}

async function getMonster() {
	const response = await fetch('/monster');
	const data = await response.json();
	document.getElementById("monster").src = data.imageURL;
}

function hideBattle() {
	const battleElements = document.getElementsByClassName("battleElement");
	for (let i = 0, l = battleElements.length; i < l; i++) {
		battleElements[i].style.visibility = 'hidden';
	}
}

function visibleTitle() {
	document.getElementById("title").style.visibility = 'visible';
	const difficultyButtons = document.getElementsByClassName("difficultyButton");
	for (let i = 0, l = difficultyButtons.length; i < l; i++) {
		difficultyButtons[i].style.visibility = 'visible';
	}
}

async function goNextFloor() {
	monsterBeat.textContent = "";
	bonusResult.textContent = "";
	expResult.textContent = "";
	levelUp.textContent = "";
	commandHoverFlag = true;
	try {
		const response = await fetch('/nextfloor');
		const data = await response.json();
		if (data[0] == "Return/") {
			return;
		}
		await getMonster();
		for (const item of data) {
			await changeScreen(item);
		}
	} catch (error) {
		console.error('エラー:', error);
	}
	shopElement.style.visibility = 'hidden';
}

hideBattle();
