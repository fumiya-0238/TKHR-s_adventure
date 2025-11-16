const itemClick = async(event) => {
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
	if(data[0] == "Return/"){
		return;
	}
	    for (const item of data) {
	        await changeScreen(item);
	    }
	}catch (error) {
		        console.error('エラー:', error);
		    }
			}
};

const itemHover = (event) => {
	const element = event.target;
	// クリックされた要素が<li>か確認
	if (element.tagName === 'LI') {
		element.style.outlineWidth = '3px';
		element.style.outlineStyle = 'solid';
		element.style.outlineColor = 'red';
		nowHoverElement = element;
	}
};

const itemOut = (event) => {
	const element = event.target;
	if (element.tagName === 'LI') {
	  nowHoverElement = "";
	 element.style.outlineStyle = "none";
	 }
};



