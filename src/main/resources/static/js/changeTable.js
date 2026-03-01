let changeFlag = false;
const changeDataBase = document.getElementById("changeDataBase");
//document.getElementById("rollBackButton").style.left = '100px';
changeDataBase.style.top = parseInt(dataBase.style.top) + 'px';
changeDataBase.style.left = parseInt(dataBase.style.left) + 'px';
const changeTable = document.getElementById("changeTable");
changeTable.style.top = '27px';
const changeTbody = document.getElementById("changeTbody");
const changeTh = changeTable.querySelectorAll('th');
changeTh[0].style.width = '41px';
changeTh[1].style.width = '150px';
changeTh[2].style.width = '17px';
changeTh[3].style.width = '70px';
changeTh[4].style.width = '200px';
changeTh[5].style.width = '200px';
changeTh[6].style.width = '102px';
const historyNumber =  document.getElementById("historyNumber");

changeButton.addEventListener('click', () => {
	if(changeFlag){
		document.getElementById("upButton").style.visibility = 'visible';
		document.getElementById("downButton").style.visibility = 'visible';
		document.getElementById("deleteButton").style.visibility = 'visible';
		document.getElementById("addButton").style.visibility = 'visible';
		document.getElementById("downloadSQL").style.visibility = 'visible';
		document.getElementById("dataBase-tab-container").style.visibility = 'visible';
		changeTable.style.visibility = 'hidden';
		historyNumber.style.visibility = 'hidden';
		document.getElementById("rollBackButton").style.visibility = 'hidden';
		changeButton.textContent = '変更履歴';
	}else{
		document.getElementById("upButton").style.visibility = 'hidden';
		document.getElementById("downButton").style.visibility = 'hidden';
		document.getElementById("deleteButton").style.visibility = 'hidden';
		document.getElementById("addButton").style.visibility = 'hidden';
		document.getElementById("downloadSQL").style.visibility = 'hidden';
		document.getElementById("dataBase-tab-container").style.visibility = 'hidden';
		changeTable.style.visibility = 'visible';
		historyNumber.style.visibility = 'visible';
		document.getElementById("rollBackButton").style.visibility = 'visible';
		changeButton.textContent = 'テーブル';
	}
	changeFlag = !changeFlag;
});

fetch('/getChangeDataBase').then(response => response.json())
	.then(data => {
		createChangeTable(data);
	})
	.catch(error => console.error('Error:', error));

function createChangeTable(data) {
	const l = parseInt(data.length);
	for (let i = 0 ; i < l; i++) {
		const tr = document.createElement('tr');
		const changeType = document.createElement('td');
		changeType.textContent = data[i]['changeType'];
		changeType.style.whiteSpace = 'nowrap';
		const tableName = document.createElement('td');
		tableName.textContent = data[i]['tableName'];
		const id = document.createElement('td');
		id.textContent = data[i]['id'];
		const columnName = document.createElement('td');
		columnName.textContent = data[i]['columnName'];
		const beforeValue = document.createElement('td');
		beforeValue.textContent = data[i]['beforeValue'];
		const afterValue = document.createElement('td');
		afterValue.textContent = data[i]['afterValue'];
		const createdAt = document.createElement('td');
		createdAt.style.whiteSpace = 'nowrap';
		createdAt.textContent = data[i]['createdAt'];
		tr.appendChild(changeType);
		tr.appendChild(tableName);
		tr.appendChild(id);
		tr.appendChild(columnName);
		tr.appendChild(beforeValue);
		tr.appendChild(afterValue);
		tr.appendChild(createdAt);
		changeTbody.appendChild(tr);
	}
	changeTable.appendChild(changeTbody);
	historyNumber.textContent = '履歴の数:' + l;
}

function appendChangeData(data){
	if(data === undefined){
		return;
	}
	
	const tbody = changeTable.querySelectorAll('tbody');
	const tr = document.createElement('tr');
	const changeType = document.createElement('td');
	changeType.textContent = data['changeType'];
	changeType.style.whiteSpace = 'nowrap';
	if(changeType.textContent == '更新不可'){
		return;
	}
	const tableName = document.createElement('td');
	tableName.textContent = data['tableName'];
	const id = document.createElement('td');
	id.textContent = data['id'];
	const columnName = document.createElement('td');
	columnName.textContent = data['columnName'];
	const beforeValue = document.createElement('td');
	beforeValue.textContent = data['beforeValue'];
	const afterValue = document.createElement('td');
	afterValue.textContent = data['afterValue'];
	const createdAt = document.createElement('td');
	createdAt.style.whiteSpace = 'nowrap';
	createdAt.textContent = data['createdAt'];
	tr.appendChild(changeType);
	tr.appendChild(tableName);
	tr.appendChild(id);
	tr.appendChild(columnName);
	tr.appendChild(beforeValue);
	tr.appendChild(afterValue);
	tr.appendChild(createdAt);
	changeTbody.appendChild(tr);
	changeTable.appendChild(changeTbody);	
	const l = changeTbody.querySelectorAll('tr').length;
	historyNumber.textContent = '履歴の数:' + l;
}
/*
// ハイライトを解除する関数
function clearHighlight() {
	if (highlightedRow) {
		highlightedRow.style.backgroundColor = ''; // ハイライト解除
		highlightedRow = null;
	}
}

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
				index: activeIndex,
				row: rowIndex
			})
		}).catch(error => {
			console.error('削除エラー:', error);
		});

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
				}
			}
		}
		// ハイライトを解除
		highlightedRow = null;
	}
});
*/
