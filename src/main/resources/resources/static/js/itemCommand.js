const itemClick = async(event) => {
	if(!commandClickFlag){
			return ;
		}
	if (event.target.tagName === 'LI') {
	  // リスト内のインデックスを取得	
	  try{
	  const index = Array.from(list.children).indexOf(event.target);
	const response = await fetch('/item', {
	  method: 'POST',
	  headers: { 'Content-Type': 'text/plain' },
	  body: index
	});
	const data = await response.json();
	    for (const item of data) {
	        await changeScreen(item);
			console.log(item);
	    }
	}catch (error) {
		        console.error('エラー:', error);
		    }
			}
};

const itemHover = (event) => {
	if(!itemHoverFlag){
		return;
	}
	const element = event.target;
	// クリックされた要素が<li>か確認
	if (element.tagName === 'LI') {
		event.target.focus(); // ホバーしたリスト項目にフォーカス
	}
};

const itemOut = (event) => {
	const element = event.target;
	// クリックされた要素が<li>か確認
	if (element.tagName === 'LI') {
	  // リスト内のインデックスを取得
	  event.target.blur();
	 //element.style.outlineStyle = "none";
	 }
};

const itemKey = async(event) => {
	commandClickFlag = false;
	const element = event.target;
	try{
	  const index = Array.from(list.children).indexOf(element);
	const response = await fetch('/itemInfo', {
	  method: 'POST',
	  headers: { 'Content-Type': 'text/plain' },
	  body: index
	});
	const data = await response.json();
	pages.push(data[0]);
	infoBody.innerHTML='';
	makeItemInfo(data.slice(1));	
	infoElement.style.visibility = 'visible';
	}catch (error) {
		        console.error('エラー:', error);
		    }
};

