const dataBase = document.getElementById("dataBase");
dataBase.style.top = parseInt(titleLocation.top) - 60 + 'px';
dataBase.style.left = parseInt(titleLocation.left) + 680 + 'px';
let activeTable = 0;
let highlightedRow = null;
const deleteButton = document.getElementById("deleteButton");
const upButton = document.getElementById("upButton");
const downButton = document.getElementById("downButton");
const downloadSQL = document.getElementById("downloadSQL");
fetch('/getDataBase').then(response => response.json())
	.then(data => {
		createMonsterTable(data['monster']);
		createConditionTable(data['condition']);
		createActionTable(data['action']);
		createItemTable(data['item']);
		createWeaponTable(data['weapon']);
		createServiceTable(data['service']);
		for(let i = 0,l=dungeonPhysicalName.length;i<l;i++){
			const dn = dungeonPhysicalName[i];
			createDungeonMonsterTable(data[dn+'Monster'], dn+'MonsterDataBase');
			createShopFloorTable(data[dn+'ShopFloor'], dn+'ShopFloorDataBase');
			createShopItemTable(data[dn+'Item'], dn+'ItemDataBase');
			createShopWeaponTable(data[dn+'Weapon'], dn+'WeaponDataBase');
			createShopServiceTable(data[dn+'Service'], dn+'ServiceDataBase');
		}
	})
	.catch(error => console.error('Error:', error));

function createMonsterTable(data) {
	const table = document.querySelector('#monsterDataBase table');
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const name = document.createElement('td');
		name.className = 'dataBaseEditable';
		name.textContent = data[i]['name'];
		name.style.whiteSpace = 'nowrap';
		const hp = document.createElement('td');
		hp.className = 'dataBaseEditable';
		hp.textContent = data[i]['hp'];
		const overHp = document.createElement('td');
		overHp.className = 'dataBaseEditable';
		overHp.textContent = data[i]['overHp'];
		const attack = document.createElement('td');
		attack.className = 'dataBaseEditable';
		attack.textContent = data[i]['attack'];
		const exp = document.createElement('td');
		exp.className = 'dataBaseEditable';
		exp.textContent = data[i]['exp'];
		const gold = document.createElement('td');
		gold.className = 'dataBaseEditable';
		gold.textContent = data[i]['gold'];
		const turn = document.createElement('td');
		turn.className = 'dataBaseEditable';
		turn.textContent = data[i]['turn'];
		const text = document.createElement('td');
		text.className = 'dataBaseEditable';
		text.textContent = data[i]['text'];
		tr.appendChild(id);
		tr.appendChild(name);
		tr.appendChild(hp);
		tr.appendChild(overHp);
		tr.appendChild(attack);
		tr.appendChild(exp);
		tr.appendChild(gold);
		tr.appendChild(turn);
		tr.appendChild(text);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createConditionTable(data) {
	const table = document.querySelector('#conditionDataBase table');
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const name = document.createElement('td');
		name.style.whiteSpace = 'nowrap';
		name.className = 'dataBaseEditable';
		name.textContent = data[i]['name'];
		const duplication = document.createElement('td');
		duplication.style.whiteSpace = 'nowrap';
		duplication.className = 'dataBaseEditable';
		duplication.textContent = data[i]['duplication'];
		const text = document.createElement('td');
		text.className = 'dataBaseEditable';
		text.textContent = data[i]['text'];
		tr.appendChild(id);
		tr.appendChild(name);
		tr.appendChild(duplication);
		tr.appendChild(text);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createActionTable(data) {
	const table = document.querySelector('#actionDataBase table');
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const name = document.createElement('td');
		name.style.whiteSpace = 'nowrap';
		name.className = 'dataBaseEditable';
		name.textContent = data[i]['name'];
		const attackIs = document.createElement('td');
		attackIs.style.whiteSpace = 'nowrap';
		attackIs.className = 'dataBaseEditable';
		attackIs.textContent = data[i]['attackIs'];
		const text = document.createElement('td');
		text.className = 'dataBaseEditable';
		text.textContent = data[i]['text'];
		tr.appendChild(id);
		tr.appendChild(name);
		tr.appendChild(attackIs);
		tr.appendChild(text);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createItemTable(data) {
	const table = document.querySelector('#itemDataBase table');
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const name = document.createElement('td');
		name.style.whiteSpace = 'nowrap';
		name.className = 'dataBaseEditable';
		name.textContent = data[i]['name'];
		const furigana = document.createElement('td');
		furigana.style.whiteSpace = 'nowrap';
		furigana.className = 'dataBaseEditable';
		furigana.textContent = data[i]['furigana'];
		const price = document.createElement('td');
		price.className = 'dataBaseEditable';
		price.textContent = data[i]['price'];
		const text = document.createElement('td');
		text.className = 'dataBaseEditable';
		text.textContent = data[i]['text'];
		tr.appendChild(id);
		tr.appendChild(name);
		tr.appendChild(furigana);
		tr.appendChild(price);
		tr.appendChild(text);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createWeaponTable(data) {
	const table = document.querySelector('#weaponDataBase table');
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const name = document.createElement('td');
		name.style.whiteSpace = 'nowrap';
		name.className = 'dataBaseEditable';
		name.textContent = data[i]['name'];
		const furigana = document.createElement('td');
		furigana.style.whiteSpace = 'nowrap';
		furigana.className = 'dataBaseEditable';
		furigana.textContent = data[i]['furigana'];
		const price = document.createElement('td');
		price.className = 'dataBaseEditable';
		price.textContent = data[i]['price'];
		const attack = document.createElement('td');
		attack.className = 'dataBaseEditable';
		attack.textContent = data[i]['attack'];
		const text = document.createElement('td');
		text.className = 'dataBaseEditable';
		text.textContent = data[i]['text'];
		tr.appendChild(id);
		tr.appendChild(name);
		tr.appendChild(furigana);
		tr.appendChild(price);
		tr.appendChild(attack);
		tr.appendChild(text);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createServiceTable(data) {
	const table = document.querySelector('#serviceDataBase table');
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const name = document.createElement('td');
		name.style.whiteSpace = 'nowrap';
		name.className = 'dataBaseEditable';
		name.textContent = data[i]['name'];
		const price = document.createElement('td');
		price.className = 'dataBaseEditable';
		price.textContent = data[i]['price'];
		const text = document.createElement('td');
		text.className = 'dataBaseEditable';
		text.textContent = data[i]['text'];
		tr.appendChild(id);
		tr.appendChild(name);
		tr.appendChild(price);
		tr.appendChild(text);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
}

function createDungeonMonsterTable(data, tableId) {
	const container = document.createElement('div');
	container.id=tableId;
	container.className='dataBase-content';
	const table = document.createElement('table');
	table.className = 'dataBaseTable';
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');
	const headers = ['階数', 'モンスターID', 'レベル'];
	headers.forEach(text => {
	  const th = document.createElement('th');
	  th.textContent = text;
	  tr.appendChild(th);
	});
	thead.appendChild(tr);
	table.appendChild(thead);
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const monsterId = document.createElement('td');
		monsterId.style.whiteSpace = 'nowrap';
		monsterId.className = 'dataBaseEditable';
		monsterId.textContent = data[i]['monsterId'];
		const monsterLv = document.createElement('td');
		monsterLv.style.whiteSpace = 'nowrap';
		monsterLv.className = 'dataBaseEditable';
		monsterLv.textContent = data[i]['lv'];
		tr.appendChild(id);
		tr.appendChild(monsterId);
		tr.appendChild(monsterLv);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	container.appendChild(table);
	databaseTab.appendChild(container);
}

function createShopFloorTable(data, tableId) {
	const container = document.createElement('div');
	container.id=tableId;
		container.className='dataBase-content';
		const table = document.createElement('table');
		table.className = 'dataBaseTable';
		const thead = document.createElement('thead');
		const tr = document.createElement('tr');
		const headers = ['ID', '階数'];
		headers.forEach(text => {
		  const th = document.createElement('th');
		  th.textContent = text;
		  tr.appendChild(th);
		});
		thead.appendChild(tr);
		table.appendChild(thead);
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const shopFloor = document.createElement('td');
		shopFloor.style.whiteSpace = 'nowrap';
		shopFloor.className = 'dataBaseEditable';
		shopFloor.textContent = data[i]['floor'];
		tr.appendChild(id);
		tr.appendChild(shopFloor);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	container.appendChild(table);
	databaseTab.appendChild(container);
}

function createShopItemTable(data, tableId) {
	const container = document.createElement('div');
	container.id=tableId;
		container.className='dataBase-content';
		const table = document.createElement('table');
		table.className = 'dataBaseTable';
		const thead = document.createElement('thead');
		const tr = document.createElement('tr');
		const headers = ['ID', 'アイテムID', '在庫'];
		headers.forEach(text => {
		  const th = document.createElement('th');
		  th.textContent = text;
		  tr.appendChild(th);
		});
		thead.appendChild(tr);
		table.appendChild(thead);
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const itemId = document.createElement('td');
		itemId.style.whiteSpace = 'nowrap';
		itemId.className = 'dataBaseEditable';
		itemId.textContent = data[i]['itemId'];
		const stock = document.createElement('td');
		stock.style.whiteSpace = 'nowrap';
		stock.className = 'dataBaseEditable';
		stock.textContent = data[i]['stock'];
		tr.appendChild(id);
		tr.appendChild(itemId);
		tr.appendChild(stock);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	container.appendChild(table);
	databaseTab.appendChild(container);
}

function createShopWeaponTable(data, tableId) {
	const container = document.createElement('div');
	container.id=tableId;
		container.className='dataBase-content';
		const table = document.createElement('table');
		table.className = 'dataBaseTable';
		const thead = document.createElement('thead');
		const tr = document.createElement('tr');
		const headers = ['ID', '武器ID', '在庫'];
		headers.forEach(text => {
		  const th = document.createElement('th');
		  th.textContent = text;
		  tr.appendChild(th);
		});
		thead.appendChild(tr);
		table.appendChild(thead);
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const weaponId = document.createElement('td');
		weaponId.style.whiteSpace = 'nowrap';
		weaponId.className = 'dataBaseEditable';
		weaponId.textContent = data[i]['weaponId'];
		const stock = document.createElement('td');
		stock.style.whiteSpace = 'nowrap';
		stock.className = 'dataBaseEditable';
		stock.textContent = data[i]['stock'];
		tr.appendChild(id);
		tr.appendChild(weaponId);
		tr.appendChild(stock);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	container.appendChild(table);
	databaseTab.appendChild(container);
}

function createShopServiceTable(data, tableId) {
	const container = document.createElement('div');
	container.id=tableId;
	container.className='dataBase-content';
	const table = document.createElement('table');
	table.className = 'dataBaseTable';
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');
	const headers = ['ID', 'サービスID', '在庫'];
	headers.forEach(text => {
	  const th = document.createElement('th');
	  th.textContent = text;
	  tr.appendChild(th);
	});
	thead.appendChild(tr);
	table.appendChild(thead);
	const tbody = document.createElement('tbody');
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const tr = document.createElement('tr');
		tr.className = 'dataBaseRow';
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		id.className = 'idColumn';
		const serviceId = document.createElement('td');
		serviceId.style.whiteSpace = 'nowrap';
		serviceId.className = 'dataBaseEditable';
		serviceId.textContent = data[i]['serviceId'];
		const stock = document.createElement('td');
		stock.style.whiteSpace = 'nowrap';
		stock.className = 'dataBaseEditable';
		stock.textContent = data[i]['stock'];
		tr.appendChild(id);
		tr.appendChild(serviceId);
		tr.appendChild(stock);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	container.appendChild(table);
	databaseTab.appendChild(container);
}

function dataBaseOpenTab(tabName) {
	// すべてのコンテンツを非表示に
	document.querySelectorAll('.dataBase-content').forEach(content => {
		content.classList.remove('active');
	});
	// すべてのボタンのアクティブ状態を解除
	const buttons = document.querySelectorAll('.dataBase-tab-button');
	buttons.forEach(button => {
			button.classList.remove('active');
		})
		// 選択したタブのコンテンツを表示
	document.getElementById(tabName).classList.add('active');
	// クリックしたボタンをアクティブに
	const activeButton = document.querySelector(
		`button[onclick="dataBaseOpenTab('${tabName}')"]`);
	activeButton.classList.add('active');
	// 選択されたタブのインデックスを取得
	activeTable = Array.from(buttons).indexOf(activeButton);
	clearHighlight();
}

// ハイライトを解除する関数
function clearHighlight() {
	if (highlightedRow) {
		highlightedRow.style.backgroundColor = ''; // ハイライト解除
		highlightedRow = null;
	}
}

// 表の外をクリックしたときの処理
document.addEventListener('click', (event) => {
	// クリックがdataBaseの外の場合、ハイライトを解除
	if (!dataBase.contains(event.target)) {
		clearHighlight();
	}
});

dataBase.addEventListener('click', (event) => {
	const td = event.target;
	const tr = td.parentElement;
	// ID列（最初の列）をクリックした場合

	if (td.classList.contains('idColumn')) {
		if (tr === highlightedRow) {
			clearHighlight();
		} else {
			clearHighlight(); // 前のハイライトを解除
			tr.style.backgroundColor = 'red'; // 行を赤くする
			highlightedRow = tr; // ハイライトされた行を追跡
		}
		return;
	}

	if (event.target.classList.contains('dataBaseEditable')) {
		const td = event.target;

		// すでにinputがある場合は処理を終了
		if (td.querySelector('input')) {
			return;
		}
		dataBaseMode = true;
		// 現在のtdのテキストを取得
		const currentText = td.textContent;

		// input要素を作成
		const input = document.createElement('input');
		input.type = 'text';
		input.value = currentText;

		// tdの寸法を取得
		const tdStyle = window.getComputedStyle(td);
		const width = td.clientWidth - parseFloat(tdStyle.paddingLeft) - parseFloat(
			tdStyle.paddingRight);
		const height = td.clientHeight - parseFloat(tdStyle.paddingTop) -
			parseFloat(tdStyle.paddingBottom);

		// inputのサイズをtdに合わせる
		input.style.width = `${width}px`;
		input.style.height = `${height}px`;
		input.style.boxSizing = 'border-box'; // パディングとボーダーを含める
		input.style.font = tdStyle.font; // フォントを一致
		input.style.padding = tdStyle.padding; // パディングを一致

		// tdの内容をinputに置き換え
		td.textContent = '';
		td.appendChild(input);

		// inputにフォーカスを当てる
		input.focus();
		let isEnterPressed = false;
		// inputからフォーカスが外れたときの処理
		input.addEventListener('blur', () => {
			if (isEnterPressed) {
				return;
			}
			// inputの値をtdのテキストとして設定
			const newValue = input.value;
			td.textContent = newValue;
			handleEditComplete(td, newValue); // 編集終了時に情報を取得・送信
		});

		// Enterキーで編集を終了
		input.addEventListener('keypress', (e) => {
			if (e.key === 'Enter') {
				isEnterPressed = true;
				const newValue = input.value;
				td.textContent = newValue;
				handleEditComplete(td, newValue); // 編集終了時に情報を取得・送信
				input.blur(); // フォーカスを外す
			}
		});
	}
});

// 編集終了時の情報を取得する関数
async function handleEditComplete(td, newValue) {
	const row = td.parentElement;
	const table = row.parentElement;
	const rowIndex = Array.from(table.children).indexOf(row);
	const columnIndex = Array.from(row.children).indexOf(td);
	dataBaseMode = false;
	await fetch('/updateDataBase', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			activeTable: activeTable,
			row: rowIndex,
			column: columnIndex,
			value: newValue
		})
	}).then(response =>response.json())
	.then(data => {
	appendChangeData(data);
	}).catch(error => console.error('Error:', error));
}

deleteButton.addEventListener('click', () => {
	if (!highlightedRow) {
		return;
	}

	// 確認ダイアログを表示
	if (window.confirm('この行を削除しますか？')) {
		const table = highlightedRow.parentElement;
		const rowIndex = Array.from(table.children).indexOf(highlightedRow);

		// サーバーに削除リクエストを送信（オプション）
		fetch('/deleteRow', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				activeTable: activeTable,
				row: rowIndex
			})
		}).then(response =>response.json())
			.then(data => {
			appendChangeData(data);
			}).catch(error => console.error('削除エラー:', error));

		// 行を削除
		highlightedRow.remove();

		// 削除された行より下の行のIDを-1
		const rows = Array.from(table.children);
		for (let i = rowIndex; i < rows.length; i++) {
			const idCell = rows[i].querySelector('.idColumn');
			if (idCell) {
				const currentId = parseInt(idCell.textContent.replace('ID', '')); // 例: "ID1" → 1
				if (!isNaN(currentId)) {
					idCell.textContent = currentId - 1;
				};
			};
		};
		// ハイライトを解除
		highlightedRow = null;
	};
});

addButton.addEventListener('click', () => {
	const elements = document.getElementsByClassName('dataBaseTable');
	const table = elements[activeTable];
	const thead = table.querySelector('thead');
	const tbodyRows = Array.from(table.querySelectorAll('tbody tr'));
	const newId = tbodyRows.length + 1;

	// 新しい行を作成
	const newRow = document.createElement('tr');
	newRow.className = 'dataBaseRow';
	// 列数をtheadの最初の行から取得（theadがない場合は1列）
	let numColumns = 1;
	if (thead) {
		const headerRow = thead.querySelector('tr');
		if (headerRow) {
			numColumns = headerRow.children.length; // <th>の数
		}
	}

	// ID列（idColumn）
	const idCell = document.createElement('td');
	idCell.classList.add('idColumn');
	idCell.textContent = newId;
	newRow.appendChild(idCell);

	// 他の列（theadに基づいてdataBaseEditableまたは空白）
	for (let i = 1; i < numColumns; i++) {
		const cell = document.createElement('td');
		cell.className = 'dataBaseEditable';
		cell.textContent = ''; // 空白
		newRow.appendChild(cell);
	}

	// テーブルに新しい行を追加（tbodyがある場合はtbodyに、なければtableに）
	const tbody = table.querySelector('tbody');
	tbody.appendChild(newRow);

	// サーバーに追加リクエストを送信（オプション）
	fetch('/addRow', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			id: newId,
			activeTable: activeTable
		})
	}).then(response =>response.json())
				.then(data => {
				appendChangeData(data);
			}).catch(error => {
		console.error('追加エラー:', error);
	});
});

upButton.addEventListener('click', () => {
	if (!highlightedRow) {
		return;
	}

	const table = highlightedRow.parentElement;
	const rows = Array.from(table.children);
	const rowIndex = rows.indexOf(highlightedRow);
	// 最初の行の場合、移動不可
	if (rowIndex === 0) {
		return;
	}

	const nextRow = rows[rowIndex - 1];

	// ID列以外のセル内容を入れ替え
	const currentCells = Array.from(highlightedRow.children);
	const nextCells = Array.from(nextRow.children);
	for (let i = 1, l = currentCells.length; i < l; i++) { // i=0はID列なのでスキップ
		const temp = currentCells[i].textContent;
		currentCells[i].textContent = nextCells[i].textContent;
		nextCells[i].textContent = temp;
	}

	// ハイライトを下の行に移動
	highlightedRow.style.backgroundColor = ''; // 現在のハイライト解除
	nextRow.style.backgroundColor = 'red'; // 下の行をハイライト
	highlightedRow = nextRow;
	// サーバーに移動リクエストを送信（オプション）
	fetch('/moveUpRow', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			rowIndex: rowIndex + 1,
			activeTable: activeTable,
		})
	}).then(response =>response.json())
					.then(data => {
					appendChangeData(data);
				}).catch(error => {
		console.error('移動エラー:', error);
	});
});

downButton.addEventListener('click', () => {
	if (!highlightedRow) {
		return;
	}

	const table = highlightedRow.parentElement;
	const rows = Array.from(table.children);
	const rowIndex = rows.indexOf(highlightedRow);
	// 最後の行の場合、移動不可
	if (rowIndex === rows.length - 1) {
		return;
	}

	const nextRow = rows[rowIndex + 1];

	// ID列以外のセル内容を入れ替え
	const currentCells = Array.from(highlightedRow.children);
	const nextCells = Array.from(nextRow.children);
	for (let i = 1, l = currentCells.length; i < l; i++) { // i=0はID列なのでスキップ
		const temp = currentCells[i].textContent;
		currentCells[i].textContent = nextCells[i].textContent;
		nextCells[i].textContent = temp;
	}

	// ハイライトを下の行に移動
	highlightedRow.style.backgroundColor = ''; // 現在のハイライト解除
	nextRow.style.backgroundColor = 'red'; // 下の行をハイライト
	highlightedRow = nextRow;

	// サーバーに移動リクエストを送信（オプション）
	fetch('/moveDownRow', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			rowIndex: rowIndex + 1,
			activeTable: activeTable,
		})
	}).then(response =>response.json())
					.then(data => {
					appendChangeData(data);
				}).catch(error => {
		console.error('移動エラー:', error);
	});
});

downloadSQL.addEventListener('click', async() => {
	// サーバーにSQLデータをリクエスト
	const response = await fetch('/downloadSQL', {
		method: 'POST',
		headers: {
			'Content-Type': 'text/plain'
		},
		body: String(activeTable)
	});
	// サーバーから返されたSQL文字列を取得
	const sqlContent = await response.json();

	// ファイル名（例: my_table_20251015.sql）
	const date = new Date().toISOString().slice(0, 10).replace(/-/g, '');
	const tableName = sqlContent[0];

	const fileName = `${tableName}_${date}.sql`;
	const textArray = sqlContent.slice(1).join('\n');
	const blob = new Blob([textArray], {
		type: 'text/sql'
	});
	const url = URL.createObjectURL(blob);
	const a = document.createElement('a');
	a.href = url;
	a.download = fileName;
	document.body.appendChild(a);
	a.click();
	document.body.removeChild(a);
	URL.revokeObjectURL(url);

});
