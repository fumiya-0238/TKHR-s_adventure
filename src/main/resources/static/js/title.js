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
		bgm = audioContext.createBufferSource();
		bgm.buffer = audioBuffer;
		bgm.loop = true; // ループを有効化
		bgm.connect(gainNode);
		gainNode.connect(audioContext.destination);
		bgm.start(0); // 再生開始
	}).catch(error => console.error('エラー:', error));
}

// 停止
function stopAudio() {
	if (bgm) {
		bgm.stop();
		bgm = null;
	}
}

const choiceDifficult = (event) => {
	const element = event.target;
	titleToBattle(element.id);
};

async function titleToBattle(difficulty) {
	titleFlag = false;
	await fetchDifficult(difficulty);
	if (difficulty == 'expert') {
		playSeamlessLoop("/bgms/戦闘2.mp3");
	} else {
		playSeamlessLoop("/bgms/戦闘.mp3");
	}
}

async function fetchDifficult(difficulty) {
	try {
		const response = await fetch('/difficulty', {
			method: 'POST',
			headers: {
				'Content-Type': 'text/plain'
			},
			body: difficulty
		});
		const data = await response.json();
		for (const item of data) {
			await changeScreen(item);
		}
	} catch (error) {
		console.error('エラー:', error);
	}
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
document.getElementById("titlebutton").addEventListener("click",
	choiceDifficult);
