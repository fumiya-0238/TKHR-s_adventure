function makeConditionList(fullData) {
	infoBody.innerHTML = '';
	playerConditionPage = document.createElement('div');
	monsterConditionPage = document.createElement('div');
	const playerDiv = document.createElement('div');
			playerDiv.className = 'text';
			playerDiv.classList.add('playerConditions');
			playerDiv.textContent = 'プレイヤー';
			playerDiv.style.whiteSpace = 'nowrap';
			
			const monsterDiv = document.createElement('div');
						monsterDiv.className = 'text';
						monsterDiv.classList.add('monsterConditions');
						monsterDiv.textContent = 'モンスター';
						monsterDiv.style.whiteSpace = 'nowrap';
						playerConditionPage.appendChild(playerDiv);
						playerConditionPage.appendChild(monsterDiv);
						monsterConditionPage.appendChild(playerDiv.cloneNode(true));
						monsterConditionPage.appendChild(monsterDiv.cloneNode(true));
						makePlayerConditionList(fullData['playerConditions']);
						makeMonsterConditionList(fullData['monsterConditions']);
						infoBody.innerHTML = playerConditionPage.innerHTML;
}

function makePlayerConditionList(data) {
	const tableContainerDiv = document.createElement('div');
	tableContainerDiv.className = 'table-container';
	const table = document.createElement('table');
	table.id = 'playerConditionTable';
	table.style.left = '10px';
	table.style.width = '410px';
	table.style.fontSize = '19px';
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');
	let th = document.createElement('th');
	th.textContent = '状態名';
	th.style.width = parseInt(table.style.fontSize) * 15 +'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '量';
	th.style.width = parseInt(table.style.fontSize) * 2.5 +'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '残りターン数';
	th.style.width = parseInt(table.style.fontSize) * 12 +'px';;
	tr.appendChild(th);

	thead.appendChild(tr);

	const tbody = document.createElement('tbody');
	tbody.id = 'playerConditionTableBody';

	table.appendChild(thead);
	table.appendChild(tbody);
	for (let i = 0, l = parseInt(data.length) / 3; i < l; i++) {
		const newRow = document.createElement('tr');
		const conditionName = document.createElement('td');
		const conditionAmount = document.createElement('td');
		const conditionTurn = document.createElement('td');
		const cn = document.createElement('div');
		cn.textContent = data[i * 3];
		cn.className = 'infoLink';
		conditionName.appendChild(cn);
		const arrays = data[i * 3 + 1].split("/");
		if (arrays[1] == 'false') {
			conditionAmount.textContent = '-';
		} else {
			conditionAmount.textContent = arrays[0];
		}
		conditionTurn.textContent = data[i * 3 + 2];
		if (conditionTurn.textContent == '1') {
			conditionTurn.style.color = 'yellow';
		}
		newRow.appendChild(conditionName);
		newRow.appendChild(conditionAmount);
		newRow.appendChild(conditionTurn);
		tbody.appendChild(newRow);
	}
	tableContainerDiv.appendChild(table);
	playerConditionPage.appendChild(tableContainerDiv);
}

function makeMonsterConditionList(data) {
	
	const tableContainerDiv = document.createElement('div');
	tableContainerDiv.className = 'table-container';

	const table = document.createElement('table');
	table.id = 'monsterConditionTable';
	table.style.left = '10px';
	table.style.width = '410px';
	table.style.fontSize = '19px';
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');

	let th = document.createElement('th');
	th.textContent = '状態名';
	th.style.width = parseInt(table.style.fontSize) * 15 +'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '量';
	th.style.width = parseInt(table.style.fontSize) * 2.5 +'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '残りターン数';
	th.style.width = parseInt(table.style.fontSize) * 12 +'px';;
	tr.appendChild(th);
	thead.appendChild(tr);

	const tbody = document.createElement('tbody');
	tbody.id = 'monsterConditionTableBody';

	table.appendChild(thead);
	table.appendChild(tbody);
	for (let i = 0, l = parseInt(data.length) / 3; i < l; i++) {
		const newRow = document.createElement('tr');
		const conditionName = document.createElement('td');
		const conditionAmount = document.createElement('td');
		const conditionTurn = document.createElement('td');
		const cn = document.createElement('div');
		cn.textContent = data[i * 3];
		cn.className = 'infoLink';
		conditionName.appendChild(cn);
		const arrays = data[i * 3 + 1].split("/");
		if (arrays[1] == 'false') {
			conditionAmount.textContent = '-';
		} else {
			conditionAmount.textContent = arrays[0];
		}
		conditionTurn.textContent = data[i * 3 + 2];
		if (conditionTurn.textContent == '1') {
			conditionTurn.style.color = 'yellow';
		}
		newRow.appendChild(conditionName);
		newRow.appendChild(conditionAmount);
		newRow.appendChild(conditionTurn);
		tbody.appendChild(newRow);
	}
	tableContainerDiv.appendChild(table);
	monsterConditionPage.appendChild(tableContainerDiv);
}