const titleTop = 0;
const titleLeft = 100;
const titleElement = document.getElementById("title");
let pages =[];
let commandClickFlag = true;
let itemHoverFlag = true;
let keyListenerFlag = true;
titleElement.style.top = titleTop + 'px';
titleElement.style.left = titleLeft + 'px';
const titleLocation = titleElement.getBoundingClientRect();

//titleとbattleは同じ位置
const battleElement = document.getElementById("battle");
battleElement.style.top = parseInt(titleLocation.top) + 'px';
battleElement.style.left = parseInt(titleLocation.left) + 'px';

const shopElement = document.getElementById("shop");
shopElement.style.top = parseInt(titleLocation.top) + 'px';
shopElement.style.left = parseInt(titleLocation.left) + 'px';

const infoElement = document.getElementById("info");
infoElement.style.top = parseInt(titleLocation.top) + 'px';
infoElement.style.left = parseInt(titleLocation.left) + 'px';

const closeInfo = document.getElementById("closeInfo");
closeInfo.style.top = parseInt(infoElement.style.top) + 180 + 'px';
closeInfo.style.left = 5 + 'px';

//難易度ボタンのtop位置
const buttonsElement = document.getElementsByClassName("difficultyButton");
for (let i = 0, l = buttonsElement.length; i < l; i++) {
    buttonsElement[i].style.top = parseInt(titleLocation.top) + 320 + 'px';
}

//初級ボタンのleft位置
const beginnerElement = document.getElementById("beginner");
beginnerElement.style.left = parseInt(titleLocation.left) + 80 + 'px';

for (let i = 1, l = buttonsElement.length; i < l; i++) {
    buttonsElement[i].style.left = parseInt(buttonsElement[i - 1].style.left) + 200 + 'px';
}

//ステータスのleft位置
const playerElements = document.getElementsByClassName("playerStatus");
for (let i = 0, l = playerElements.length; i < l; i++) {
    playerElements[i].style.left = parseInt(titleLocation.left) + 'px';
}

//ステータスのtop位置
const levelElement = document.getElementById("LV");
levelElement.style.top = parseInt(titleLocation.top) + 308 + 'px';
for (let i = 1, l = playerElements.length; i < l; i++) {
    playerElements[i].style.top = parseInt(playerElements[i - 1].style.top) + parseInt(window.getComputedStyle(playerElements[i - 1]).fontSize) + 'px';
}

const weaponNameElement = document.getElementById("weaponName");
weaponNameElement.style.top = parseInt(document.getElementById("weapon").style.top) + 'px';
weaponNameElement.style.left = parseInt(titleLocation.left) + parseInt(window.getComputedStyle(document.getElementById("weapon")).fontSize) * 2 + 6 + 'px';

const subWeaponNameElement = document.getElementById("subWeaponName");
subWeaponNameElement.style.top = parseInt(document.getElementById("subWeapon").style.top) + 'px';
subWeaponNameElement.style.left = parseInt(titleLocation.left) + parseInt(window.getComputedStyle(document.getElementById("subWeapon")).fontSize) * 2 + 5 + 'px';

//コマンドのleft位置
const commandElements = document.getElementsByClassName("commands");
for (let i = 0, l = commandElements.length; i < l; i++) {
    commandElements[i].style.left = parseInt(titleLocation.left) + 240 + 'px';
}

//コマンドのtop位置
commandElements[0].style.top = parseInt(titleLocation.top) + 308 + 'px';
for (let i = 1, l = commandElements.length; i < l; i++) {
    commandElements[i].style.top = parseInt(commandElements[i - 1].style.top) + parseInt(window.getComputedStyle(commandElements[0]).fontSize) + 'px';
}
//ブーストゲージ
const boostElement = document.getElementById("boostGauge");
boostElement.style.left = parseInt(titleLocation.left) + 240 + 'px';
boostElement.style.top = parseInt(commandElements[5].style.top) + 28 + 'px';

const percentElement = document.getElementById("boostPercent");
percentElement.style.left = parseInt(boostElement.style.left) + boostElement.offsetWidth + 3 +'px';
percentElement.style.top = parseInt(boostElement.style.top) + 'px';

const monsterElement = document.getElementById("monster");
monsterElement.style.left = parseInt(titleLocation.left) + 192 + 'px';
monsterElement.style.top = parseInt(titleLocation.top) + 'px';

const effectElement = document.getElementById("effect");
effectElement.style.left = parseInt(monsterElement.style.left) + 'px';
effectElement.style.top = parseInt(titleLocation.top) + 'px';

const monsterHP = document.getElementById("monsterHP");
monsterHP.style.left = parseInt(titleLocation.left) + 200 + 'px';
monsterHP.style.top = parseInt(titleLocation.top) + 205 + 'px';

/*const overHP = document.getElementById("overHP");
overHP.style.left = parseInt(titleLocation.left) + 255 + 'px';
overHP.style.top = parseInt(titleLocation.top) + 205 + 'px';*/

const monsterATK = document.getElementById("monsterATK");
monsterATK.style.left = parseInt(titleLocation.left) + 320 + 'px';
monsterATK.style.top = parseInt(titleLocation.top) + 205 + 'px';

const monsterEXP = document.getElementById("monsterEXP");
monsterEXP.style.left = parseInt(titleLocation.left) + 200 + 'px';
monsterEXP.style.top = parseInt(titleLocation.top) + 205 + parseInt(window.getComputedStyle(monsterHP).fontSize) + 'px';

const monsterGold = document.getElementById("monsterGold");
monsterGold.style.left = parseInt(titleLocation.left) + 320 + 'px';
monsterGold.style.top = parseInt(titleLocation.top) + 205 + parseInt(window.getComputedStyle(monsterHP).fontSize) + 'px';

const monsterTurn = document.getElementById("monsterTurn");
monsterTurn.style.left = parseInt(titleLocation.left) + 190 + 'px';
monsterTurn.style.top = parseInt(titleLocation.top) + 205 + parseInt(window.getComputedStyle(monsterHP).fontSize) * 2 + 'px';

const difficulty = document.getElementById("difficulty");
difficulty.style.left = parseInt(titleLocation.left) + 5 + 'px';
difficulty.style.top = parseInt(titleLocation.top) + 5 + 'px';

const sumTurn = document.getElementById("sumTurn");
sumTurn.style.left = parseInt(titleLocation.left) + 4 + 'px';
sumTurn.style.top = parseInt(difficulty.style.top) + 30 + 'px';

const sumEXP = document.getElementById("sumEXP");
sumEXP.style.left = parseInt(titleLocation.left) + 4 + 'px';
sumEXP.style.top = parseInt(sumTurn.style.top) + 30 + 'px';

const monsterName = document.getElementById("monsterName");
monsterName.style.left = parseInt(titleLocation.left) + 4 + 'px';
monsterName.style.top = parseInt(sumEXP.style.top) + 30 + 'px';

const condition = document.getElementById("conditionButton");
condition.style.left = parseInt(titleLocation.left) + 4 + 'px';
condition.style.top = parseInt(monsterName.style.top) + 30 + 'px';

const messageLog = document.getElementById("messageLog");
messageLog.style.left = parseInt(titleLocation.left) + 4 + 'px';
messageLog.style.top = parseInt(condition.style.top) + 30 + 'px';

const setting = document.getElementById("setting");
setting.style.left = parseInt(titleLocation.left) + 4 + 'px';
setting.style.top = parseInt(messageLog.style.top) + 70 + 'px';

const goTitle = document.getElementById("goTitle");
goTitle.style.left = parseInt(titleLocation.left) + 4 + 'px';
goTitle.style.top = parseInt(setting.style.top) + 30 + 'px';

const itemList = document.getElementById("itemList");
itemList.style.left = parseInt(titleLocation.left) + 435 + 'px';
itemList.style.top = parseInt(titleLocation.top) + 'px';

const shopItemList =  document.getElementById("shopItemList");
/*
const nextFloor = document.getElementById("nextFloor");
nextFloor.style.left = parseInt(titleLocation.left) + 195 + 'px';
nextFloor.style.top = parseInt(titleLocation.top) + 177 + 'px';

const narabikae = document.getElementById("narabikae");
narabikae.style.left = parseInt(titleLocation.left) + 'px';
narabikae.style.top = parseInt(nextFloor.style.top) + 'px';

const dropDown = document.getElementById("dropDown");
dropDown.style.left = parseInt(narabikae.style.left) + 80 + 'px';
dropDown.style.top = parseInt(narabikae.style.top) + 'px';

const shopInfo = document.getElementById("shopInfo");*/


//shopItemList