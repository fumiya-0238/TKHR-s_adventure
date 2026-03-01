function makeConditionList(fullData) {
	conditionTables = [];
	infoBody.innerHTML = '';
	conditionSelect = document.createElement('select');
	conditionSelect.id = 'conditionSelect';
	
	const newOption = document.createElement('option');
	newOption.textContent = 'プレイヤー';
	//newOption.value = 'playerCondition';
	conditionSelect.append(newOption);
	makeCondition(fullData['playerConditions']);
	let i = 0;
	while(true){
		const searchData = 'monsterConditions' + i;
	if (searchData in fullData) {
		const monsterConditionPage = document.createElement('div');
		const monsterName = fullData[searchData][0];
		const newOption = document.createElement('option');
		newOption.textContent = monsterName;
		conditionSelect.append(newOption);
		//newOption.value = 'Condition';
		makeCondition(fullData[searchData].slice(1));
		i++;
	} else {
		break;
	}
	}
	infoBody.append(conditionSelect);
	infoBody.append(conditionTables[0]);
}

function makeCondition(data) {
	const tableContainerDiv = document.createElement('div');
	tableContainerDiv.className = 'table-container';
	const table = document.createElement('table');
	table.className = 'conditionTable';
	table.style.left = '10px';
	table.style.width = '410px';
	table.style.fontSize = '19px';
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');
	let th = document.createElement('th');
	th.textContent = '状態名';
	th.style.width = parseInt(table.style.fontSize) * 15 + 'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '重複';
	th.style.width = parseInt(table.style.fontSize) * 2.5 + 1 + 'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '残りターン数';
	th.style.width = parseInt(table.style.fontSize) * 12 + 'px';;
	tr.appendChild(th);
	thead.appendChild(tr);
	const tbody = document.createElement('tbody');
	tbody.className = 'conditionTableBody';
	table.appendChild(thead);
	table.appendChild(tbody);
	for (let i = 0, l = parseInt(data.length) / 3; i < l; i++) {
		const x = i * 3;
		const newRow = document.createElement('tr');
		const conditionName = document.createElement('td');
		const conditionAmount = document.createElement('td');
		const conditionTurn = document.createElement('td');
		const cn = document.createElement('div');
		cn.textContent = data[x];
		cn.className = 'infoLink';
		conditionName.appendChild(cn);
		const arrays = data[x + 1].split("/");
		if (arrays[1] == 'false') {
			conditionAmount.textContent = '-';
		} else {
			conditionAmount.textContent = arrays[0];
		}
		conditionTurn.textContent = data[x + 2];
		if (conditionTurn.textContent == '1') {
			conditionTurn.style.color = 'yellow';
		}
		newRow.appendChild(conditionName);
		newRow.appendChild(conditionAmount);
		newRow.appendChild(conditionTurn);
		tbody.appendChild(newRow);
	}
	tableContainerDiv.appendChild(table);
	conditionTables.push(tableContainerDiv);
}
/*
function makeMonsterConditionList(monsterConditionPage,data) {
	const monName = data[0];
	const tableContainerDiv = document.createElement('div');
	tableContainerDiv.className = 'table-container';

	const table = document.createElement('table');
	table.className = 'monsterConditionTable';
	table.style.left = '10px';
	table.style.width = '410px';
	table.style.fontSize = '19px';
	const thead = document.createElement('thead');
	const tr = document.createElement('tr');

	let th = document.createElement('th');
	th.textContent = '状態名';
	th.style.width = parseInt(table.style.fontSize) * 15 + 'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '重複';
	th.style.width = parseInt(table.style.fontSize) * 2.5 + 'px';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = '残りターン数';
	th.style.width = parseInt(table.style.fontSize) * 12 + 'px';;
	tr.appendChild(th);
	thead.appendChild(tr);

	const tbody = document.createElement('tbody');
	tbody.className = 'monsterConditionTableBody';

	table.appendChild(thead);
	table.appendChild(tbody);
	const lastData = data.slice(1);
	for (let i = 0, l = parseInt(lastData.length) / 3; i < l; i++) {
		const newRow = document.createElement('tr');
		const conditionName = document.createElement('td');
		const conditionAmount = document.createElement('td');
		const conditionTurn = document.createElement('td');
		const cn = document.createElement('div');
		cn.textContent = lastData[i * 3];
		cn.className = 'infoLink';
		conditionName.appendChild(cn);
		const arrays = lastData[i * 3 + 1].split("/");
		if (arrays[1] == 'false') {
			conditionAmount.textContent = '-';
		} else {
			conditionAmount.textContent = arrays[0];
		}
		conditionTurn.textContent = lastData[i * 3 + 2];
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
}*/
