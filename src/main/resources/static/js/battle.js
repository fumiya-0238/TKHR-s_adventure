async function battleToTitle() {
	stopAudio();
	visibleTitle();
	hideBattle();
	titleFlag = true;
	effectOn = false;
	conditionTables = [];
	//monsterElement.style.visibility = 'hidden';
	messageWindow.style.visibility = 'hidden';
	messageText.style.visibility = 'hidden';
	monsterBeat.textContent = "";
	bonusResult.textContent = "";
	expResult.textContent = "";
	levelUp.textContent = "";
	cockatrice.innerHTML = '';
	monsterList.innerHTML = '';
	monsterStatusList.innerHTML = '';
	monsterNameList.innerHTML = '';
	monsterSize = 0;
}
async function changeScreen(item) {
	if (titleFlag) {
		return;
	}
	console.log(item);
	const change = item.split("$");
	const change1Split = change[1].split("#");
	switch (change[0]) {
		case "DungeonStart":
			//await getMonster();
			visibleBattle();
			hideTitle();
			break;
		case "SetMonsterName":
			const monsterName = document.getElementsByClassName('monsterName')[
				change1Split[1]];
			monsterName.textContent = `${change1Split[0]}`;
			break;
		case "SetMonsterHP":
			const monsterHP = monsterStatusList.getElementsByClassName('monsterHP')[
				change1Split[1]];
			monsterHP.textContent = `HP${change1Split[0]}`;
			break;
		case "SetMonsterOverHP":
			const monsterOverHP = monsterStatusList.getElementsByClassName(
				'monsterOverHP')[change1Split[1]];
			const monsterHP2 = monsterStatusList.getElementsByClassName('monsterHP')[
				change1Split[1]];
			monsterOverHP.textContent = `(${change1Split[0]})`;
			monsterOverHP.style.left = parseInt(monsterHP2.style.left) + monsterHP2.clientWidth +
				'px';
			monsterOverHP.style.top = parseInt(monsterHP2.style.top) + 'px';
			break;
		case "SetMonsterAttack":
			const monsterATK = monsterStatusList.getElementsByClassName('monsterATK')[
				change1Split[1]];
			monsterATK.textContent = `攻撃力${change1Split[0]}`;
			break;
		case "SetMonsterEXP":
			const monsterEXP = monsterStatusList.getElementsByClassName('monsterEXP')[
				change1Split[1]];
			monsterEXP.textContent = `経験値${change1Split[0]}`;
			break;
		case "SetMonsterGold":
			const monsterGold = monsterStatusList.getElementsByClassName('monsterGold')[
				change1Split[1]];
			monsterGold.textContent = `お金${change1Split[0]}`;
			break;
		case "SetMonsterTurn":
			const monsterTurn = monsterStatusList.getElementsByClassName('monsterTurn')[
				change1Split[1]];
			monsterTurn.textContent = `ボーナス残り${change1Split[0]}ターン`;
			monsterTurn.style.visibility = 'visible';
			break;
		case "DeleteMonsterTurn":
			const monsterDTurn = monsterStatusList.getElementsByClassName('monsterTurn')[
				change[1]];
			monsterDTurn.style.visibility = 'hidden';
			break;
		case "SetMonsterDamage":
			const monsterDamage = document.getElementsByClassName('monsterDamage')[change1Split[1]];
				let monsterElement;
				if(effectOn){
					 monsterElement = document.getElementsByClassName('monsterEffect')[change1Split[1]];
				} else {
					 monsterElement = document.getElementsByClassName('monsterImage')[change1Split[1]];
				}
			monsterDamage.style.color = "#ff0000";
			monsterDamage.textContent = `${change1Split[0]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					monsterDamage.textContent = '';
					resolve();
				}, 300);
			});
			console.log(monsterElement);
			monsterDamage.style.left =
				monsterElement.clientWidth / 2 - monsterDamage.textContent.length *
				parseInt(window.getComputedStyle(monsterDamage).fontSize) / 2.8 + 'px';
			//monsterDamage.style.top = parseInt(titleLocation.top) + 'px';
			monsterElement.style.visibility = 'hidden';
			damageSound.currentTime = 0;
			damageSound.play();
			await new Promise(resolve => setTimeout(resolve, 80));
			monsterElement.style.visibility = 'visible';
			break;
		case "SetMonsterHeal":
			const monHeLi = document.querySelectorAll('#monsterImageList li');
			const monsterHeal = monHeLi[change1Split[1]].querySelectorAll(
				'.monsterDamage')[0];
			const monsterElementHe = monHeLi[change1Split[1]].querySelectorAll(
				'.monsterImage')[0];
			monsterHeal.style.color = "#00ff00";
			monsterHeal.textContent = `${change1Split[0]}`;
			new Promise((resolve) => {
				setTimeout(() => {
					monsterHeal.textContent = '';
					resolve();
				}, 300);
			});
			monsterHeal.style.left =
				monsterElementHe.clientWidth / 2 - monsterHeal.textContent.length *
				parseInt(window.getComputedStyle(monsterHeal).fontSize) / 2.8 + 'px';
			healSound.currentTime = 0;
			healSound.play();
			break;
		case "ResetMonsterDamage":
			const monsterDamageRe = document.getElementsByClassName('monsterDamage')[
				change1Split[0]];
			monsterDamageRe.textContent = '';
			break;
		case "ShowMonster":
			let arrayI = [];
			let imageURLs = [];
			change[1].split("&").forEach(item => {
				const mo = item.split("#");
				arrayI.push(mo[0]);
				imageURLs.push(mo[1])
			});
			// monsI[0].split("#");
			await getMonster(arrayI, imageURLs);
			//monsterElement.style.visibility = 'visible';
			break;
		case "ReShowMonster":
			if(effectOn){
				document.getElementsByClassName('monsterEffect')[change1Split[0]].style.visibility = 'visible';
			}else {
				document.getElementsByClassName('monsterImage')[change1Split[0]].style.visibility = 'visible';
			}
			break;
		case "DeleteMonster":
			await new Promise(resolve => setTimeout(resolve, 60));
			if(effectOn){
				document.getElementsByClassName('monsterEffect')[change1Split[0]].style.visibility = 'hidden';
			} else {
				document.getElementsByClassName('monsterImage')[change1Split[0]].style.visibility = 'hidden';
			}
		break;
		case "MonsterHidden":
			document.getElementsByClassName('monsterImage')[change1Split[0]].style.visibility = 'hidden';
			break;
		case "MonsterDeath":
			monsterList.innerHTML = '';
			monsterSize = 0;
			monsterBeat.textContent = `${change1Split[0]}を倒した`;
			break;
		case "MonsterEffect":
			const monsterEffect = document.getElementsByClassName('monsterEffect')[
				change1Split[1]];
			monsterEffect.src = change1Split[0];
			monsterEffect.style.visibility = 'visible';
			effectOn = true;
			break;
		case "DeleteMonsterEffect":
			const deleteMonsterEffect = document.getElementsByClassName('monsterEffect')[
				change1Split[0]];
			deleteMonsterEffect.style.visibility = 'hidden';
			effectOn = false;
			break;
		case "TargetMonster":
			const monTaLi = document.querySelectorAll('#monsterImageList li');
			for (let i = 0, l = monTaLi.length; i < l; i++) {
				if (i == change[1]) {
					monTaLi[i].style.outlineWidth = '3px';
					monTaLi[i].style.outlineStyle = 'solid';
					monTaLi[i].style.outlineColor = 'red';
				} else {
					monTaLi[i].style.outlineStyle = 'none';
				}
			}
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
		case "BreakWeapon":
			breakWeaponSound.currentTime = 0;
			breakWeaponSound.play();
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
		case "ShowItem":
			itemList.innerHTML = "";
			for(let i = 1,l=change1Split.length;i<l;i++){
				const newItem = document.createElement('li');
				newItem.textContent = change1Split[i];
				newItem.className = 'infoButton';
				itemList.appendChild(newItem);
			}
			if(change1Split[0]<20){
				itemListBlack.style.visibility = 'visible';
				itemListBlack.style.top = parseInt(itemList.style.top) + change1Split[0] * 24  + 'px';
				itemListBlack.style.height =  parseInt(titleLocation.top) + 480 - parseInt(itemListBlack.style.top) + 'px';
				//const canvas = document.getElementById('myCanvas');
				    const ctx = itemListBlack.getContext('2d');
				    // 黒で四角形を塗りつぶす（canvas全体をカバー）
				    ctx.fillStyle = 'black';           // 塗りの色を黒に設定
				    ctx.fillRect(0, 0, itemListBlack.width, itemListBlack.height); // (x, y, width, height)
			}else {
				itemListBlack.style.visibility = 'hidden';
			}
			
			
			break;
		case "AddItem":
			if(20 == itemList.getElementsByTagName('li').length){
				return;
			}
			const newItem = document.createElement('li');
			newItem.textContent = change[1];
			//newItem.setAttribute('tabindex', '0');
			newItem.className = 'infoButton';
			itemList.appendChild(newItem);
			break;
		case "RemoveItem":
			const remove = itemList.getElementsByTagName('li');
			itemList.removeChild(remove[parseInt(change[1])]);
			break;
		case "ActiveItem":
			const active = itemList.getElementsByTagName('li');
			active[parseInt(change[1])].style.color = 'red';
			break;
			case "ShowItemSwitch":
				itemSwtchFlag = true;
				itemSwitch.style.visibility = 'visible';
				//const active = itemList.getElementsByTagName('li');
				//active[parseInt(change[1])].style.color = 'red';
				break;
			case "DeleteItemSwitch":
				itemSwtchFlag = false;
				itemSwitch.style.visibility = 'hidden';
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
					expResult.style.top = parseInt(monsterBeat.style.top) + resultMesSize +
						'px';
					levelUp.style.top = parseInt(expResult.style.top) + expResult.clientHeight +
						'px';
					break;
				case "1":
					bonusResult.textContent = "ボーナスを1つゲット　経験値とお金が1.3倍";
					expResult.style.top = parseInt(bonusResult.style.top) + resultMesSize +
						'px';
					levelUp.style.top = parseInt(expResult.style.top) + expResult.clientHeight +
						'px';
					break;
				case "2":
					bonusResult.textContent = "ボーナスを2つゲット　経験値とお金が1.5倍";
					expResult.style.top = parseInt(bonusResult.style.top) + resultMesSize * 2 +
						'px';
					levelUp.style.top = parseInt(expResult.style.top) + expResult.clientHeight +
						'px';
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
			const color = '#a3a3a3';
			shopItemButton.style.color = color;
			shopServiceButton.style.color = color;
			cancelFusion.style.visibility = 'visible';
			nextFloor.style.visibility = 'hidden';
			break;
		case "FusionCancel":
			fusionMode = false;
			openTab(shopServiceButton, 'tab3');
			shopItemButton.style.color = '';
			shopServiceButton.style.color = '';
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
			//const monLi = document.querySelectorAll('#monsterImageList li');
			const effectElement = document.getElementsByClassName('effect')[change1Split[
				0]];
			//[change[1]]
			//const effectElement = monsterLi.getElementById("effect");
			//const monsterHPSplit = change[1].split("#");
			//const monsterHP = document.getElementsByClassName('monsterHP')[monsterHPSplit[1]];
			//monsterHP.textContent = `HP${monsterHPSplit[0]}`;
			//effectElement.style.left = parseInt(monsterList.style.left) + 'px';
			//effectElement.style.top = parseInt(titleLocation.top) + 'px';
			effectElement.style.visibility = 'visible';
			const src = effectElement.src;
			effectElement.src = '';
			effectElement.src = src;
			await new Promise(resolve => setTimeout(resolve, 200));
			effectElement.style.visibility = 'hidden';
			break;
		case "MonsterActionEffect":
			//const monALi = document.querySelectorAll('#monsterImageList li');
			let actionEffect;
			if(effectOn){
				actionEffect = document.getElementsByClassName('monsterEffect')[change1Split[0]];
			}else {
				actionEffect = document.getElementsByClassName('monsterImage')[change1Split[0]];
			}
			
			monsterActionSound.currentTime = 0;
			monsterActionSound.play();
			const canvas = document.createElement('canvas');
			const ctxMonster = canvas.getContext('2d');

			// Canvasを画像と同じサイズに設定
			canvas.width = actionEffect.width;
			canvas.height = actionEffect.height;

			// 画像をCanvasに描画
			ctxMonster.drawImage(actionEffect, 0, 0);

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
			canvas.style.top = actionEffect.offsetTop + 'px';
			canvas.style.left = actionEffect.offsetLeft + 'px';
			//	canvas.style.zIndex = '1';
			actionEffect.parentNode.appendChild(canvas);
			await new Promise(resolve => setTimeout(resolve, 300));
			canvas.remove();
			break;
		case "PlayerConditionMessage":
			const playerNewCondition = document.createElement('li');
			playerNewCondition.className = 'condition-item';
			playerNewCondition.textContent = `${change[1]}`;
			playerNewCondition.style.color = '#00ff00';
			animateAndRemoveCondition(playerNewCondition);
			break;
		case "MonsterConditionMessage":
			const monsterNewCondition = document.createElement('li');
			monsterNewCondition.className = 'condition-item';
			monsterNewCondition.textContent = `${change[1]}`;
			monsterNewCondition.style.color = '#ff0000';
			animateAndRemoveCondition(monsterNewCondition);
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
		case "BeginnerButton":
			beginnerButton.src = change[1];
			break;
		case "NoviceButton":
			noviceButton.src = change[1];
			break;
		case "ExpertButton":
			expertButton.src = change[1];
			break;
		case "DungeonList":
			change[1].split("&").forEach(item => {
				const dungeonName = item.split("#");
				newOption = document.createElement('option');
				newOption.textContent = dungeonName[0];
				newOption.value = dungeonName[1];
				dungeonPhysicalName.push(dungeonName[1]);
				dungeonSelect.append(newOption);
			});
			break;
		case "DebugDungeonList":
			const tabMenu = document.getElementById('dataBase-tab-menu');
			const dif = change[1].split("&");
			for (let i = 0, l = dif.length; i < l; i++) {
				const diff = dif[i].split("#");
				const monsterButton = document.createElement('button');
				monsterButton.className = 'dataBase-tab-button';
				monsterButton.textContent = diff[0] + 'モンスター';
				monsterButton.setAttribute('onclick', "dataBaseOpenTab('" + diff[1] +
					"MonsterDataBase')");
				const shopButton = document.createElement('button');
				shopButton.className = 'dataBase-tab-button';
				shopButton.textContent = diff[0] + '店階';
				shopButton.setAttribute('onclick', "dataBaseOpenTab('" + diff[1] +
					"ShopFloorDataBase')");
				const itemButton = document.createElement('button');
				itemButton.className = 'dataBase-tab-button';
				itemButton.textContent = diff[0] + 'アイテム';
				itemButton.setAttribute('onclick', "dataBaseOpenTab('" + diff[1] +
					"ItemDataBase')");
				const weaponButton = document.createElement('button');
				weaponButton.className = 'dataBase-tab-button';
				weaponButton.textContent = diff[0] + '武器';
				weaponButton.setAttribute('onclick', "dataBaseOpenTab('" + diff[1] +
					"WeaponDataBase')");
				const serviceButton = document.createElement('button');
				serviceButton.className = 'dataBase-tab-button';
				serviceButton.textContent = diff[0] + 'サービス';
				serviceButton.setAttribute('onclick', "dataBaseOpenTab('" + diff[1] +
					"ServiceDataBase')");
				tabMenu.appendChild(monsterButton);
				tabMenu.appendChild(shopButton);
				tabMenu.appendChild(itemButton);
				tabMenu.appendChild(weaponButton);
				tabMenu.appendChild(serviceButton);
			}
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
		case "DebugPlayer":
			visibleBattle();
			hideTitle();
			debug.style.visibility = 'hidden';
			nextFloor.textContent = '階層セレクト';
			break;
		case "DebugDungeon":
			const dungeons = change[1].split("%");
			let left = 105;
			const playerSetting = document.createElement('div');
			playerSetting.className = 'text';
			playerSetting.id = 'playerSetting';
			playerSetting.textContent = 'プレイヤーセッティング';
			
			for (let i = 0, l = dungeons.length; i < l; i++) {
				const dungeonSplits = dungeons[i].split("#");
				const mozi = dungeonSplits[0].length - 2;
				const w = mozi * 15 + 45;
				const newList = document.createElement('ul');
				newList.className = 'dungeonList';
				newList.classList.add('text');
				newList.style.top = '5px';
				newList.style.left = left + 'px';
				left += w;
				for (let j = 0, l = dungeonSplits.length; j < l; j++) {
					const newItem = document.createElement('li');
					newItem.className = 'floorButton';
					newItem.textContent = dungeonSplits[j];
					newItem.style.width = w + 'px';
					newList.appendChild(newItem);
				}
				debugDungeons.appendChild(newList);
			}
			debugDungeons.appendChild(playerSetting);
			debug.style.visibility = 'visible';
			break;
		case "DebugEnd":
			debug.style.visibility = 'hidden';
			debugDungeons.innerHTML = '';
			nextFloor.textContent = '次の階へ行く';
			break;
	}
}

async function animateAndRemoveCondition(item) {
	conditionMessage.style.visibility = 'visible';
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
	if (conditionMessage.children.length == 0) {
		conditionMessage.style.visibility = 'hidden';
	}
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

async function getMonster(arrays, imageURLs) {

	//const moSize = arrays.length
	const monsterLi = document.querySelectorAll('#monsterImageList li');
	for (let i = 0, l = arrays.length; i < l; i++) {
		if (monsterSize <= i) {
			const newMonster = document.createElement('li');
			const newImage = document.createElement('img');
			newImage.src = imageURLs[i];
			newImage.className = 'monsterImage';
			const newEffect = document.createElement('img');
			newEffect.style.visibility = 'hidden';
			newEffect.src = '/images/攻撃.gif';
			newEffect.classList.add('effect');
			newEffect.style.left = '0px';
			const monsterDamage = document.createElement("div");
			monsterDamage.classList.add('text');
			monsterDamage.classList.add('monsterDamage');

			const newMonsterEffect = document.createElement('img');
			newMonsterEffect.style.left = '0px';
			newMonsterEffect.className = 'monsterEffect';

			newMonster.appendChild(newImage);
			newMonster.appendChild(newMonsterEffect);
			newMonster.appendChild(newEffect);
			newMonster.appendChild(monsterDamage);
			monsterList.appendChild(newMonster);
			monsterSize++;
			const monsterStatus = document.createElement('li');
			monsterStatus.className = 'monsterStatus';
			const monsterHP = document.createElement("div");
			monsterHP.classList.add('text');
			monsterHP.classList.add('monsterHP');
			const monsterOverHP = document.createElement("div");
			monsterOverHP.classList.add('text');
			monsterOverHP.classList.add('monsterOverHP');
			const monsterATK = document.createElement("div");
			monsterATK.classList.add('text');
			monsterATK.classList.add('monsterATK');
			const monsterEXP = document.createElement("div");
			monsterEXP.classList.add('text');
			monsterEXP.classList.add('monsterEXP');
			const monsterGold = document.createElement("div");
			monsterGold.classList.add('text');
			monsterGold.classList.add('monsterGold');
			const monsterTurn = document.createElement("div");
			monsterTurn.classList.add('text');
			monsterTurn.classList.add('monsterTurn');

			monsterStatus.appendChild(monsterHP);
			monsterStatus.appendChild(monsterOverHP);
			monsterStatus.appendChild(monsterATK);
			monsterStatus.appendChild(monsterEXP);
			monsterStatus.appendChild(monsterGold);
			monsterStatus.appendChild(monsterTurn);
			monsterStatusList.appendChild(monsterStatus);
			const monsterName = document.createElement('li');
			monsterName.className = 'monsterName';
			monsterNameList.append(monsterName);
		} else {
			monsterLi[i].src = imageURLs[i];
		}
	}
	const newMonsterLi = document.querySelectorAll('#monsterImageList li');
	if (newMonsterLi.length == 1) {
		newMonsterLi[0].style.left = '35px';
		newMonsterLi[0].style.top = '0px';
		newMonsterLi[0].style.width = '205px';
		newMonsterLi[0].style.height = '205px';
		const newMonsterEf = newMonsterLi[0].getElementsByClassName('monsterEffect')[0];
		newMonsterEf.style.width = '205px'
		newMonsterEf.style.height = '205px';
		//console.log(newMonsterLi[0]);

		//const newMonsterEf = newMonsterLi[0].querySelectorAll('effect');
		//console.log(newMonsterEf[0]);
		//newMonsterEf[0].style.left = newMonsterLi[0].style.left;
		//newMonsterEf[0].style.top = newMonsterLi[0].style.top;
		//newEffect.style.left = parseInt(monsterList.style.left) + 'px';
		//effectElement.style.top = parseInt(titleLocation.top) + 'px';
		const monsterStatus = document.getElementsByClassName('monsterStatus')[0];
		monsterStatus.style.fontSize = '19px'
			//108 90
		const monsterHP = document.getElementsByClassName('monsterHP')[0];
		monsterHP.style.left = 43 + 'px';
		monsterHP.style.top = 130 + 'px';

		const monsterATK = document.getElementsByClassName('monsterATK')[0];
		monsterATK.style.left = parseInt(monsterHP.style.left) + 125 + 'px';
		monsterATK.style.top = parseInt(monsterHP.style.top) + 'px';

		const monsterEXP = document.getElementsByClassName('monsterEXP')[0];
		monsterEXP.style.left = parseInt(monsterHP.style.left) + 'px';
		monsterEXP.style.top = parseInt(monsterHP.style.top) + parseInt(window.getComputedStyle(
			monsterHP).fontSize) + 'px';

		const monsterGold = document.getElementsByClassName('monsterGold')[0];
		monsterGold.style.left = monsterATK.style.left;
		monsterGold.style.top = monsterEXP.style.top;

		const monsterTurn = document.getElementsByClassName('monsterTurn')[0];
		monsterTurn.style.left = 32 + 'px';
		monsterTurn.style.top = parseInt(monsterHP.style.top) + parseInt(window.getComputedStyle(
			monsterHP).fontSize) * 2 + 'px';
		//monsterLi[0].
	} else {
		let monsterLeft = 0;
		for (let j = 0; j < monsterSize; j++) {
			const newEf = newMonsterLi[j].getElementsByClassName('effect')[0];
			const newMonsterEf = newMonsterLi[j].getElementsByClassName('monsterEffect')[
				0];
			newMonsterLi[j].style.left = monsterLeft + 'px';

			newMonsterLi[j].style.top = '50px';
			//newMonsterEf.style.left = '0px';
			//newMonsterEf[j].style.top = newMonsterLi[j].style.top;

			newMonsterLi[j].style.width = '135px';
			newMonsterLi[j].style.height = '135px';
			newEf.style.width = '135px'
			newEf.style.height = '135px';
			newMonsterEf.style.width = '135px'
			newMonsterEf.style.height = '135px';

			const monsterStatus = document.getElementsByClassName('monsterStatus')[j];
			monsterStatus.style.fontSize = '11px'
			const monsterHP = document.getElementsByClassName('monsterHP')[j];
			monsterHP.style.left = monsterLeft + 'px';
			monsterHP.style.top = 130 + 'px';

			const monsterATK = document.getElementsByClassName('monsterATK')[j];
			monsterATK.style.left = parseInt(monsterHP.style.left) + 75 + 'px';
			monsterATK.style.top = parseInt(monsterHP.style.top) + 'px';

			const monsterEXP = document.getElementsByClassName('monsterEXP')[j];
			monsterEXP.style.left = parseInt(monsterHP.style.left) + 'px';
			monsterEXP.style.top = parseInt(monsterHP.style.top) + parseInt(window.getComputedStyle(
				monsterHP).fontSize) + 'px';

			const monsterGold = document.getElementsByClassName('monsterGold')[j];
			monsterGold.style.left = monsterATK.style.left;
			monsterGold.style.top = monsterEXP.style.top;

			const monsterTurn = document.getElementsByClassName('monsterTurn')[j];
			monsterTurn.style.left = monsterLeft + 'px';
			monsterTurn.style.top = parseInt(monsterHP.style.top) + parseInt(window.getComputedStyle(
				monsterHP).fontSize) * 2 + 'px';
			monsterLeft += 135;
		}
	}
	//document.getElementById("monster").src = data.imageURL;
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
		for (const item of data) {
			await changeScreen(item);
		}
	} catch (error) {
		console.error('エラー:', error);
	}
	shopElement.style.visibility = 'hidden';
}

hideBattle();
fetch('/init').then(response => response.json())
	.then(data => {
		for (const item of data) {
			changeScreen(item);
		}
	})
	.catch(error => console.error('Error:', error));
