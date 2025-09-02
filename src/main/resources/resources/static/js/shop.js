function openTab(event, tabId) {
    // すべてのタブコンテンツを非表示
    document.querySelectorAll('.tab-content').forEach(content => {
      content.classList.remove('active');
    });

    // すべてのタブボタンのアクティブ状態を解除
    document.querySelectorAll('.tab-button').forEach(button => {
      button.classList.remove('active');
    });

    // 選択されたタブコンテンツを表示
    document.getElementById(tabId).classList.add('active');

    // 選択されたタブボタンをアクティブに
    event.currentTarget.classList.add('active');
  }
 

  async function createShop(){
  await fetch('/shop')
  	.then(response => response.json())
  	.then(data => {
		const name = document.createElement('div');
		const price = document.createElement('div');
		const stock = document.createElement('div');
		name.style.position = 'absolute';
		name.style.width = 40+'px';
		name.textContent = "名前";
		name.style.color ='#00ff00';
		price.style.position = 'absolute';
		price.style.width = 40+'px';
		price.style.left = 230+'px'
		price.textContent = "値段";
		price.style.color ='#00ff00';
		stock.style.position = 'absolute';
		stock.style.width = 40+'px';
		stock.style.left = 330+'px'
		stock.style.left = name.style.right+'px'
		stock.textContent = "在庫";
		stock.style.color ='#00ff00';
		shopInfo.appendChild(name);
		shopInfo.appendChild(price);
		shopInfo.appendChild(stock);
		showShopItem(data);
  	}).catch(error => console.error("エラー:", error));

	}
	
	function showShopItem(data){
		shopItemList.innerHTML = '';
		for (let i = 0, l = parseInt(data.ShopItemSize); i < l; i++) {
			const li = document.createElement('li');
			li.className = 'shopItem';
			const itemName = document.createElement('div');
			const itemPrice = document.createElement('div');
			const itemStock = document.createElement('div');
			 itemName.textContent = data[`ShopItemName${i}`];
			 itemName.style.width = 200 + 'px';
			 itemName.style.position = 'absolute';
			 itemName.style.left = 0 +'px';
			 //itemName.style.top = i*parseInt(window.getComputedStyle(shopItemList).fontSize)+'px';
			 itemPrice.textContent = data[`ItemPrice${i}`]+"G";
			 itemPrice.style.width = 90 + 'px';
			 itemPrice.style.position = 'absolute';
			 itemPrice.style.textAlign = 'right';
			 itemPrice.style.left = parseInt(itemName.style.width)+'px';
			// itemPrice.style.top = parseInt(itemName.style.top)+'px';
			 itemStock.textContent = data[`ItemStock${i}`]+"個";
			 itemStock.style.width = 80 + 'px';
			 itemStock.style.position = 'absolute';
			 itemStock.style.textAlign = 'right';
			 itemStock.style.left = parseInt(itemPrice.style.left) + parseInt(itemPrice.style.width)+'px';
			// itemStock.style.top = parseInt(itemName.style.top)+'px';
			li.appendChild(itemName);
			li.appendChild(itemPrice);
			li.appendChild(itemStock);
			if(data[`ItemStock${i}`] !='∞'){
				li.style.color = 'yellow';
			}else{
				li.style.color = 'white';
			}
			shopItemList.appendChild(li);
			const shopItem = document.getElementsByClassName("shopItem")[i];
			shopItem.style.position = 'absolute';
			shopItem.style.width =  parseInt(itemName.style.width)+parseInt(itemPrice.style.width)+parseInt(itemStock.style.width)+'px';
			shopItem.style.height = window.getComputedStyle(shopItemList).fontSize;
			shopItem.style.top = 5+i*parseInt(shopItem.style.height) +'px';
			shopItem.style.left = 5 +'px';
			}
		}
		
		shopItemList.addEventListener('click', (event) => {
			const shopItem = event.target.closest('.shopItem');
		  	if (shopItem) {
		  	  // リスト内のインデックスを取得
		  	  const index = Array.from(shopItemList.children).indexOf(shopItem);
			  fetch('/itemBuy',{
			  	  method: 'POST',
			  	  headers: { 'Content-Type': 'text/plain' },
			  	  body: index
			  	}).then(response => response.json())
			  	.then(data => {
					showShopItem(data);
			  		showPlayerStatus(data);
			  	}).catch(error => console.error("エラー:", error));
				
		  	}
		  });

		shopItemList.addEventListener('mouseover',(event) => {
		  	const element = event.target;
			if (event.target.tagName === 'UL') {
				return;
			  	}
			const shopItem = element.closest('.shopItem');
			//console.log(element.tagName);
		  	shopItem.style.outlineWidth = '3px';
		  	shopItem.style.outlineStyle = 'solid';
		  	shopItem.style.outlineColor = 'red';
		  });

		shopItemList.addEventListener('mouseout',(event) => {
		  	const element = event.target;
			if (event.target.tagName === 'UL') {
				return;
			  	}
			  const shopItem = element.closest('.shopItem');
		  	 shopItem.style.outlineStyle = "none";
		  });