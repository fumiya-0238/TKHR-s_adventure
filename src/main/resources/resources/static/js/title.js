const audioContext = new AudioContext();
let source = null;

// オーディオファイルをフェッチしてバッファにロード
async function loadAudio(url) {
  const response = await fetch(url);
  const arrayBuffer = await response.arrayBuffer();
  const audioBuffer = await audioContext.decodeAudioData(arrayBuffer);
  return audioBuffer;
}

// ループ再生
function playSeamlessLoop(url) {
  loadAudio(url).then(audioBuffer => {
    source = audioContext.createBufferSource();
    source.buffer = audioBuffer;
    source.loop = true; // ループを有効化
    source.connect(audioContext.destination);
    source.start(0); // 再生開始
  }).catch(error => console.error('エラー:', error));
}

// 停止
function stopAudio() {
  if (source) {
    source.stop();
    source = null;
  }
}

const choiceDifficult = (event) => {
	const element = event.target;
	titleToBattle(element.id);
};
async function titleToBattle(difficulty) {
	await fetchDifficult(difficulty);
	await getMonster();
	visibleBattle();
	hideTitle();
	if(difficulty == 'expert'){
	playSeamlessLoop("/bgms/戦闘2.mp3");
	}else{
	playSeamlessLoop("/bgms/戦闘.mp3");
	}
}

async function fetchDifficult(difficulty) {
	await fetch('/difficulty', {
			method: 'POST',
			headers: {
				'Content-Type': 'text/plain'
			},
			body: difficulty
		}).then(response => response.json())
		.then(data => {
			data.forEach(item => changeScreen(item));
		})
		.catch(error => console.error('Error:', error));
}

function hideTitle() {
	titleElement.style.visibility = 'hidden';
	for (let i = 0, l = buttonsElement.length; i < l; i++) {
		buttonsElement[i].style.visibility = 'hidden';
	}
}

function visibleBattle() {
	const battleElements = document.getElementsByClassName("battleElement");
	for (let i = 0, l = battleElements.length; i < l; i++) {
		battleElements[i].style.visibility = 'visible';
	}
}
document.getElementById("titlebutton").addEventListener("click",choiceDifficult);
