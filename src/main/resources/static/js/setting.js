const titleTop = 0;
const titleLeft = 100;
const titleElement = document.getElementById("title");
titleElement.style.top = titleTop + 'px';
titleElement.style.left = titleLeft + 'px';
const titleLocation = titleElement.getBoundingClientRect();

//titleとbattleは同じ位置
const battleElement = document.getElementById("battle");
battleElement.style.top = parseInt(titleLocation.top) + 'px';
battleElement.style.left = parseInt(titleLocation.left) + 'px';

//初級ボタンのtop位置
const buttonsElement = document.getElementsByClassName("difficulty_button");
for (let i = 0, l = buttonsElement.length; i < l; i++) {
    buttonsElement[i].style.top = parseInt(titleLocation.top) + 320 + 'px';
}

//ボタンのleft位置
const beginnerElement = document.getElementById("beginner");
beginnerElement.style.left = parseInt(titleLocation.left) + 80 + 'px';

for (let i = 1, l = buttonsElement.length; i < l; i++) {
    buttonsElement[i].style.left = parseInt(buttonsElement[i - 1].style.left) + 200 + 'px';
}

//ステータスのleft位置
const playerElements = document.getElementsByClassName("player_status");
for (let i = 0, l = playerElements.length; i < l; i++) {
    playerElements[i].style.left = parseInt(titleLocation.left) + 'px';
}

//ステータスのtop位置
const levelElement = document.getElementById("LV");
levelElement.style.top = parseInt(titleLocation.top) + 308 + 'px';
for (let i = 1, l = playerElements.length; i < l; i++) {
    playerElements[i].style.top = parseInt(playerElements[i - 1].style.top) + parseInt(window.getComputedStyle(playerElements[0]).fontSize) + 'px';
}

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

const monsterElement = document.getElementById("monster");
monsterElement.style.left = parseInt(titleLocation.left) + 212 + 'px';
monsterElement.style.top = parseInt(titleLocation.top) + 'px';

const monsterHP = document.getElementById("monsterHP");
monsterHP.style.left = parseInt(titleLocation.left) + 200 + 'px';
monsterHP.style.top = parseInt(titleLocation.top) + 205 + 'px';

const overHP = document.getElementById("overHP");
overHP.style.left = parseInt(titleLocation.left) + 235 + 'px';
overHP.style.top = parseInt(titleLocation.top) + 205 + 'px';

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