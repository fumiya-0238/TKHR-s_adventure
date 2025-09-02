function makeConditionList(data){
	// 親コンテナのdiv要素を作成
	const playerConditionsDiv = document.createElement('div');
	playerConditionsDiv.className = 'text';
	playerConditionsDiv.id = 'playerConditions';
	playerConditionsDiv.textContent = 'プレイヤー';

	// テーブルコンテナのdiv要素を作成
	const tableContainerDiv = document.createElement('div');
	tableContainerDiv.className = 'table-container';

	// テーブル要素を作成
	const table = document.createElement('table');
	table.id = 'playerConditionTable'; // IDのスペルミスをそのまま反映

	// thead要素を作成
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');

	// テーブルのヘッダー（th）を作成
	const headers = ['状態名', '量', '残りターン数'];
	headers.forEach(headerText => {
	    const th = document.createElement('th');
	    th.textContent = headerText;
	    tr.appendChild(th);
	});
	
	// theadにtrを追加
	thead.appendChild(tr);

	// tbody要素を作成
	const tbody = document.createElement('tbody');
	tbody.id = 'playerConditionTableBody';

	// テーブルにtheadとtbodyを追加
	table.appendChild(thead);
	table.appendChild(tbody);
	
		for (let i = 0, l = parseInt(data.PlayerConditionSize); i < l; i++) {	
			const newRow = document.createElement('tr');
			const playerConditionName = document.createElement('td');
			const playerConditionAmount = document.createElement('td');
			const playerConditionTurn = document.createElement('td');
			playerConditionAmount.textContent = data[`PlayerConditionAmount${i}`];
			if(0<data[`PlayerConditionAmount${i}`]){
			playerConditionName.textContent = data[`PlayerConditionName${i}`];
            playerConditionTurn.textContent = data[`PlayerConditionTurn${i}`];
			newRow.appendChild(playerConditionName);
			newRow.appendChild(playerConditionAmount);
			newRow.appendChild(playerConditionTurn);
			tbody.appendChild(newRow);
			}
			}
			
			// table-containerにテーブルを追加
			tableContainerDiv.appendChild(table);
			infoBody.innerHTML = '';
			infoBody.appendChild(playerConditionsDiv);
			infoBody.appendChild(tableContainerDiv);
			infoElement.style.visibility = 'visible';
			pages.push('conditionConfirm');
		}
		