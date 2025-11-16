const audioContext = new AudioContext();
const gainNode = audioContext.createGain();
gainNode.gain.setValueAtTime(0.05, audioContext.currentTime);
let bgm = null;
const commandClickSound = new Audio('/bgms/コマンドクリック.mp3');
const clickSound = new Audio('/bgms/マウスクリック.mp3');
const infoClickSound = new Audio('/bgms/関連ページクリック.mp3');
const boostClickSound = new Audio('/bgms/ブーストオン.mp3');
const damageSound = new Audio('/bgms/ダメージ.mp3');
const healSound = new Audio('/bgms/HP回復.mp3');
const upTensionSound = new Audio('/bgms/気合上昇.mp3');
const buySound = new Audio('/bgms/店で買う.mp3');
const missAttackSound = new Audio('/bgms/攻撃失敗.mp3');
const defenceSound = new Audio('/bgms/防御.mp3');
const noEnoughMoneySound = new Audio('/bgms/お金が足りない.mp3');
const weaponFusionSound = new Audio('/bgms/武器融合.mp3');
const monsterActionSound = new Audio('/bgms/モンスター行動.mp3');
let seVolume = 0.01;
commandClickSound.volume = seVolume;
clickSound.volume = seVolume;
infoClickSound.volume = seVolume;
boostClickSound.volume = seVolume;
damageSound.volume = seVolume;
healSound.volume = seVolume;
upTensionSound.volume = seVolume;
buySound.volume = seVolume;
missAttackSound.volume = seVolume;
defenceSound.volume = seVolume;
noEnoughMoneySound.volume = seVolume;
weaponFusionSound.volume = seVolume;
monsterActionSound.volume = seVolume;

const titleTop = 0;
const titleLeft = 100;
const titleElement = document.getElementById("title");
let pages =[];
let monsterConditionPage;
let playerConditionPage;
let conditionToggle = true;
let commandHoverFlag = true;
let commandClickFlag = true;
let betweenInfo = false;//info中
let titleFlag = false;
let fusionMode = false;
let commandMessages = [];
let itemMessages = [];
let nowHoverElement = "";
let nowHoverCommand = null;
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

const backInfo = document.getElementById("backInfo");
backInfo.style.top = parseInt(closeInfo.style.top) + 'px';
backInfo.style.left = parseInt(closeInfo.style.left) + 70 + 'px';
backInfo.style.visibility = 'hidden';

const logElement = document.getElementById("log");
logElement.style.top = parseInt(titleLocation.top) + 'px';
logElement.style.left = parseInt(titleLocation.left) + 'px';

const closeLog = document.getElementById("closeLog");
closeLog.style.top = parseInt(infoElement.style.top) + 360 + 'px';
closeLog.style.left = 5 + 'px';

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
monsterATK.style.left = parseInt(titleLocation.left) + 325 + 'px';
monsterATK.style.top = parseInt(titleLocation.top) + 205 + 'px';

const monsterEXP = document.getElementById("monsterEXP");
monsterEXP.style.left = parseInt(titleLocation.left) + 200 + 'px';
monsterEXP.style.top = parseInt(titleLocation.top) + 205 + parseInt(window.getComputedStyle(monsterHP).fontSize) + 'px';

const monsterGold = document.getElementById("monsterGold");
monsterGold.style.left = monsterATK.style.left;
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
sumEXP.style.top = parseInt(sumTurn.style.top) + 25 + 'px';

const monsterName = document.getElementById("monsterName");
monsterName.style.left = parseInt(titleLocation.left) + 4 + 'px';
monsterName.style.top = parseInt(sumEXP.style.top) + 25 + 'px';

const condition = document.getElementById("conditionButton");
condition.style.left = parseInt(titleLocation.left) + 4 + 'px';
condition.style.top = parseInt(monsterName.style.top) + 25 + 'px';

const battleLog = document.getElementById("battleLog");
battleLog.style.left = parseInt(titleLocation.left) + 4 + 'px';
battleLog.style.top = parseInt(condition.style.top) + 25 + 'px';

/*
const setting = document.getElementById("setting");
setting.style.left = parseInt(titleLocation.left) + 4 + 'px';
setting.style.top = parseInt(battleLog.style.top) + 70 + 'px';*/

const goTitle = document.getElementById("goTitle");
goTitle.style.left = parseInt(titleLocation.left) + 4 + 'px';
goTitle.style.top = parseInt(battleLog.style.top) + 70 + 'px';

const itemList = document.getElementById("itemList");
itemList.style.left = parseInt(titleLocation.left) + 435 + 'px';
itemList.style.top = parseInt(titleLocation.top) + 'px';

const shopItemList =  document.getElementById("shopItemList");
const shopWeaponList =  document.getElementById("shopWeaponList");
const shopServiceList =  document.getElementById("shopServiceList");
const logList =  document.getElementById("logList");

const maxHP = document.getElementById("maxHP");
const HP = document.getElementById("HP");
const playerAttack = document.getElementById("ATK");
const weaponAttack = document.getElementById("weaponATK");
const tension = document.getElementById("tension");
const gold = document.getElementById("Gold");
const monsterDamage = document.getElementById("monsterDamage");
const playerDamage = document.getElementById("playerDamage");
const playerTensionUp = document.getElementById("playerTensionUp");
const playerGoldGet = document.getElementById("playerGoldGet");
const monsterBeat = document.getElementById("monsterBeat");
const resultMesSize = 14;
monsterBeat.style.left = parseInt(titleLocation.left) + 192 + 'px';
monsterBeat.style.top = parseInt(titleLocation.top) + 'px';
const bonusResult = document.getElementById("bonusResult");
bonusResult.style.left = parseInt(monsterBeat.style.left) + 'px';
bonusResult.style.top = parseInt(monsterBeat.style.top) + resultMesSize + 'px';
const expResult = document.getElementById("expResult");
const levelUp = document.getElementById("levelUp");
expResult.style.left = parseInt(monsterBeat.style.left) + 'px';
levelUp.style.left = parseInt(monsterBeat.style.left) + 'px';

for (let i = 0, l = buttonsElement.length; i < l; i++) {
    buttonsElement[i].style.visibility = 'visible';
}
 titleElement.style.visibility = 'visible';
 
 const messageWindow = document.getElementById("messageWindow");
messageWindow.style.left = parseInt(titleLocation.left) + 'px';
messageWindow.style.top = parseInt(titleLocation.top) + 260 + 'px';

const messageText = document.getElementById("messageText");
messageText.style.left = parseInt(titleLocation.left) + 2 +'px';
messageText.style.top = parseInt(titleLocation.top) + 262 + 'px';

const commandButton = document.getElementById("commandButton");
const cockatrice = document.getElementById("cockatrice");
const dungeonSelect = document.getElementById("dungeonSelect");
dungeonSelect.style.left = parseInt(titleLocation.left)+ 10+'px';
dungeonSelect.style.top = parseInt(titleLocation.top) + 440 +'px';

const goSpecialDungeonButton = document.getElementById("goSpecialDungeonButton");
goSpecialDungeonButton.style.left = parseInt(titleLocation.left)+ 200+'px';
goSpecialDungeonButton.style.top = parseInt(titleLocation.top) + 435 +'px';

const conditionMessage = document.getElementById("conditionMessage");
conditionMessage.style.left = parseInt(titleLocation.left)+ 267 +'px';
conditionMessage.style.top = parseInt(titleLocation.top) + 10 +'px';

const cancelFusion = document.getElementById("cancelFusion");


