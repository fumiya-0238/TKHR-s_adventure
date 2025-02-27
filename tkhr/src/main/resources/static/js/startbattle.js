//ボタンのイベントリスナー
const choiceDifficult = (event) => {
    const element = event.target;
    const validIds = ["beginner", "novice", "expert"];
    if (validIds.includes(element.id)) {
        titleToBattle(element.id);
    }
};
async function titleToBattle(element) {
    hideTitle();
    await fetchDifficult(`/${element}`);
    visible();
    await getMonster();
}
async function fetchDifficult(difficult) {
    try {
        const response = await fetch(difficult);
        const data = await response.json();
        document.getElementById("LV").textContent = `LV${data.Player.LV}`;
        document.getElementById("HP").textContent = `HP${data.Player.HP}/${data.Player.MAXHP}`;
        document.getElementById("ATK").textContent = `攻撃力${data.Player.ATK}(${data.Player.Weapon.ATK})`;
        document.getElementById("weapon").textContent = `武器:${data.Player.Weapon.Name}`;
        document.getElementById("tension").textContent = `気合${data.Player.Tension}%`;
        document.getElementById("EXP").textContent = `残り経験値${data.Player.EXP}`;
        document.getElementById("Gold").textContent = `所持金${data.Player.Gold}`;
        document.getElementById("criticalAttack").textContent = `強攻撃(残り${data.Player.Critical}回)`;
        document.getElementById("monsterHP").textContent = `HP${data.Monster.HP}`;
        document.getElementById("overHP").textContent = `(${data.Monster.OverHP})`;
        document.getElementById("monsterATK").textContent = `攻撃力${data.Monster.ATK}`;
        document.getElementById("monsterEXP").textContent = `経験値${data.Monster.EXP}`;
        document.getElementById("monsterGold").textContent = `お金${data.Monster.Gold}G`;
        document.getElementById("monsterTurn").textContent = `ボーナス残り${data.Monster.Turn}ターン`;
    } catch (error) {
        console.error("エラー:", error);
    };
}
async function getMonster() {
    try {
        const response = await fetch('/monster');
        const data = await response.json();
        document.getElementById("monster").src = data.imageURL;
    } catch (error) {
        console.error('エラー:', error);
    }
}
function hideTitle() {
    const battleImage = document.getElementById("battle");
    battleImage.style.visibility = 'visible';

    const titleImage = document.getElementById("title");
    titleImage.style.visibility = 'hidden';

    const beginnerButton = document.getElementById("beginner");
    beginnerButton.style.visibility = 'hidden';

    const noviceButton = document.getElementById("novice");
    noviceButton.style.visibility = 'hidden';

    const expertButton = document.getElementById("expert");
    expertButton.style.visibility = 'hidden';
}

function visible() {
    const playerElements = document.getElementsByClassName("player_status");
    for (let i = 0, l = playerElements.length; i < l; i++) {
        playerElements[i].style.visibility = 'visible';
    }
    const commandElements = document.getElementsByClassName("commands");
    for (let i = 0, l = commandElements.length; i < l; i++) {
        commandElements[i].style.visibility = 'visible';
    }
    const monsterElement = document.getElementById("monster");
    monsterElement.style.visibility = 'visible';

    const monsterElements = document.getElementsByClassName("monster_status");
    for (let i = 0, l = monsterElements.length; i < l; i++) {
        monsterElements[i].style.visibility = 'visible';
    }
}
const commandHover = (event) => {
    const element = event.target;
    const validIds = ["attack", "weekAttack", "criticalAttack", "defence", "tameru"];
    if (validIds.includes(element.id)) {
        element.style.outlineWidth = '3px';
        element.style.outlineStyle = 'solid';
        element.style.outlineColor = 'red';
    }
};
const commandOut = (event) => {
    const element = event.target;
    const validIds = ["attack", "weekAttack", "criticalAttack", "defence", "tameru"];
    if (validIds.includes(element.id)) {
        element.style.outlineStyle = "none";
    }
}
document.getElementById("titlebutton").addEventListener("click", choiceDifficult);
document.getElementById("commandbutton").addEventListener("mouseover", commandHover);
document.getElementById("commandbutton").addEventListener("mouseout", commandOut);