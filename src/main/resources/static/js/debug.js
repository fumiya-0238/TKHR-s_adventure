const debug = document.getElementById("debug");
debug.style.top = parseInt(titleLocation.top) + 'px';
debug.style.left = parseInt(titleLocation.left) + 'px';
const debugDungeons = document.getElementById("debugDungeons");
const debugOption = document.createElement('option');
debugOption.textContent = 'デバッグ';
	debugOption.value = 'debug';
	dungeonSelect.append(debugOption);
//dungeonSelect.appendChild

debugDungeons.addEventListener('click', async(event) => {
	const element = event.target;
	if (element.tagName === 'LI') {
	  const index = Array.from(element.parentNode.children).indexOf(element);
	  const listElements = debugDungeons.querySelectorAll('ul, ol');
	  const listIndex = Array.from(listElements).indexOf(element.parentNode);
	  const response = await fetch('/goDebugDungeon', {
	    method: 'POST',
	    headers: { 'Content-Type': 'application/json'},
	    body: JSON.stringify({
	    	'index': index,
	    	'listIndex': listIndex
	    })
	  });
	  const data = await response.json();
	  for (const item of data) {
	  	await changeScreen(item);
	  }
	  } else if(element.id == 'playerSetting'){
		debug.style.visibility = 'hidden';
		debugDungeons.innerHTML = '';
		document.getElementById('narabikae').style.visibility = 'visible';
		document.getElementById('shopSort').style.visibility = 'visible';
		nextFloor.style.visibility = 'visible';
		shopElement.style.visibility = 'visible';
	  }
});

debugDungeons.addEventListener('mouseover', async(event) => {
	const element = event.target;
	if (element.tagName === 'LI') {
		element.style.outlineWidth = '3px';
		element.style.outlineStyle = 'solid';
		element.style.outlineColor = 'red';
	  const index = Array.from(element.parentNode.children).indexOf(element);
	  const listElements = debugDungeons.querySelectorAll('ul, ol');
	  const listIndex = Array.from(listElements).indexOf(element.parentNode);
	  const response = await fetch('/debugMonster', {
	    method: 'POST',
	    headers: { 'Content-Type': 'application/json'},
	    body: JSON.stringify({
	    	'index': index,
	    	'listIndex': listIndex
	    })
	  });
	  const data = await response.json();
	  document.getElementById("sampleMonster").src = data.imageURL;
	  } else if(element.id == 'playerSetting'){
		element.style.outlineWidth = '3px';
		element.style.outlineStyle = 'solid';
		element.style.outlineColor = 'red';
  	  }
});


debugDungeons.addEventListener('mouseout', (event) => {
	const element = event.target;
	if (element.tagName === 'LI' || element.id == 'playerSetting') {
		element.style.outlineStyle = 'none';
	  }
});