package com.example.myapp.model.conditions;


public enum CreateCondition {
    COUNTER{
        public Condition getCondition() {
            return new カウンター();
        }
    },
    GOLDCHANCE{
        public Condition getCondition() {
            return new ゴールドチャンス状態();
        }
    },
    SLIME{
        public Condition getCondition() {
            return new スライム状態();
        } 
    },
    DEATH_MATCH{
        public Condition getCondition() {
            return new デスマッチ();
        } 
    },
    THORN{
        public Condition getCondition() {
            return new トゲトゲ();
        } 
    },
    BERSERK{
        public Condition getCondition() {
            return new バーサーク状態();
        } 
    },
    EYE_CHARGE{
        public Condition getCondition() {
            return new 眼チャージ();
        } 
    },
    TENTION_SUB{
        public Condition getCondition() {
            return new 気合サブスク状態();
        } 
    },
    PENETRATE{
        public Condition getCondition() {
            return new 研ぎ石状態();
        } 
    },
    GUTS{
        public Condition getCondition() {
            return new 根性状態();
        } 
    },
    CURSE{
        public Condition getCondition() {
            return new 死神の呪い();
        } 
    },
    TENACITY{
        public Condition getCondition() {
            return new 執念();
        } 
    },
    TENACITY_PLUS{
        public Condition getCondition() {
            return new 強執念();
        } 
    },
    WEEK_INVALID{
        public Condition getCondition() {
            return new 手加減無効();
        } 
    },
    PEARL_ATTACK{
        public Condition getCondition() {
            return new 真珠飛ばし();
        } 
    },
    TAUNT{
        public Condition getCondition() {
            return new 挑発状態();
        } 
    },
    SHARE_DAMAGE{
        public Condition getCondition() {
            return new 痛覚共有状態();
        } 
    },
    CONTROL_SWITCH{
        public Condition getCondition() {
            return new 暴走スイッチ();
        }  
    },
    DEFENCE{
        public Condition getCondition() {
            return new 防御状態();
        } 
    },
    DEFENCE_BUFF{
        public Condition getCondition() {
            return new 防御強化状態();
        } 
    },
    HP_UP{
        public Condition getCondition() {
            return new 毎ターンHP回復();
        } 
    },
    HP_DOWN{
        public Condition getCondition() {
            return new 毎ターンHP減少();
        } 
    },
    STEALTH{
        public Condition getCondition() {
            return new 霧隠れ();
        } 
    };
    public abstract Condition getCondition();
}
