function showBattleLog(data){
	logList.innerHTML = '';
	
	for (let i = 0, l = parseInt(data.length); i < l; i++) {
		const li = document.createElement('li');
		li.innerHTML=data[i];
		logList.appendChild(li);
		}
	}