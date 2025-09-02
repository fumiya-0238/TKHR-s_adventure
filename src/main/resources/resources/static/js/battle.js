async function battleToTitle() {
	visibleTitle();
	hideBattle();
	stopAudio();
}
async function changeScreen(item) {
	const change = item.split("/");
	switch (change[0]) {
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
			monsterTurn.textContent = `ボーナス残り${change[1]}ターン`;
			break;
			case "SetMonsterDamage":
				const monsterDamage = document.getElementById("monsterDamage");
				monsterDamage.textContent = `${change[1]}`;
				monsterDamage.style.left = parseInt(monsterElement.style.left)+ monsterElement.clientWidth/2 - monsterDamage.textContent.length*
				parseInt(window.getComputedStyle(monsterDamage).fontSize)/2.8 + 'px';
				monsterDamage.style.top = parseInt(titleLocation.top) + 'px';
						console.log(parseInt(monsterElement.style.left)+ monsterElement.clientWidth/2);
						console.log(monsterDamage.textContent.length*parseInt(window.getComputedStyle(monsterDamage).fontSize)/2.8);
						break;
		case "SetPlayerLV":
			levelElement.textContent = `LV${change[1]}`;
			break;
		case "SetPlayerHP":
			document.getElementById("HP").textContent = `HP${change[1]}`;
			break;
		case "SetPlayerMaxHP":
			const maxHP = document.getElementById("maxHP");
			const HP = document.getElementById("HP");
			maxHP.textContent = `/${change[1]}`;
			maxHP.style.left = parseInt(HP.style.left) + HP.clientWidth + 'px';
			maxHP.style.top = parseInt(HP.style.top) + 'px';
			break;
		case "SetPlayerAttack":
			document.getElementById("ATK").textContent = `攻撃力${change[1]}`;
			break;
		case "SetPlayerWeaponAttack":
			const playerAttack = document.getElementById("ATK");
			const weaponAttack = document.getElementById("weaponATK");
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
		case "AddItem":
			const newItem = document.createElement('li');
			newItem.textContent = change[1];
			newItem.setAttribute('tabindex', '0');
			itemList.appendChild(newItem);
			break;
		case "RemoveItem":
			const remove = itemList.getElementsByTagName('li');
			list.removeChild(remove[parseInt(change[1])]);
			break;
		case "ActiveItem":
			const active = itemList.getElementsByTagName('li');
			active[parseInt(change[1])].style.color = 'red';
			document.getElementById("weaponATK").textContent = `HP${change[1]}`;
			break;
		case "SetSumTurn":
			sumTurn.textContent = `計${change[1]}ターン`;
			break;
		case "SetDifficulty":
			difficulty.textContent = `${change[1]}`;
			break;
		case "SetSumFloor":
			const floor = document.getElementById("floor");
			floor.style.left = parseInt(difficulty.style.left) + parseInt(window.getComputedStyle(
				difficulty).fontSize) * (difficulty.textContent.length) / 1.1 + 2 + 'px';
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
		case "Shop":
			createShop();
			break;
		case "GameOver":
			alert('ゲームオーバー');
			break;
		case "GameClear":
			alert('クリア');
						break;
	}
}

// マウスクリックを待つ関数
async function waitForClick() {
  // クリックを待つPromiseを作成
  const wait = new Promise((resolve) => {
    document.addEventListener('click', () => {
      resolve(); // クリックされたらPromiseを解決
    }, { once: true }); // 一度だけリスナーを実行
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

const commandHover = (event) => {
	if(!commandClickFlag){
		return ;	
		}
	const element = event.target;
	element.style.outlineWidth = '3px';
	element.style.outlineStyle = 'solid';
	element.style.outlineColor = 'red';
};
const commandOut = (event) => {
	if(!commandClickFlag){
		return ;	
		}
	const element = event.target;
	element.style.outlineStyle = "none";
};
const commandClick = async(event) => {
	if(!commandClickFlag){
	return ;	
	}
	allMouseEventRemove();
	const element = event.target;
	element.style.outlineStyle = "none";
	switch (element) {
		case conditionButton:
			try {
			        // fetchリクエストを送信し、レスポンスを待機
			        const response = await fetch('/conditionconfirm');
			        const data = await response.json();
			            await makeConditionList(data);
			    } catch (error) {
			        console.error('エラー:', error);
			    }
			break;
		case boost:
			await fetch('/boost')
				.then(response => response.json())
				.then(data => {
					if (data) {
						for (let i = 0, l = commandElements.length; i < l; i++) {
							commandElements[i].style.color = 'red';
						}
					} else {
						for (let i = 0, l = commandElements.length; i < l; i++) {
							commandElements[i].style.color = 'white';
						}
					}
				})
				.catch(error => console.error('Error:', error));
			break;
		case goTitle:
			break;
		default:
			try {
			        // fetchリクエストを送信し、レスポンスを待機
			        const response = await fetch('/command', {
			            method: 'POST',
			            headers: {
			                'Content-Type': 'text/plain'
			            },
			            body: element.id
			        });
			        const data = await response.json();
			        for (const item of data) {
			            await changeScreen(item);
						console.log(item);
			        }
			    } catch (error) {
			        // エラーハンドリング
			        console.error('エラー:', error);
			    }
	}
	/*
	if (element == boost){
		window.confirm("諦めてタイトルに戻りますか？");
		}
	/*try {
		if (element == boost) {

		} else {
			
		}
	} catch (error) {
		console.error("エラー:", error);
	};*/
	allMouseEventAdd();
};

function goNextFloor() {
	shopElement.style.visibility = 'hidden';
	//allMouseEventAdd();
	fetch('/nextfloor')
		.then(response => response.json())
		.then(data => {
			getMonster();
			data.forEach(item => changeScreen(item));
		}).catch(error => console.error("エラー:", error));
}
hideBattle();
