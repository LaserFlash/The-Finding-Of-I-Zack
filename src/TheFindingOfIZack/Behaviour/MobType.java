package TheFindingOfIZack.Behaviour;

public enum MobType {
    Fast {
        @Override
        public Mob constructMob() {
            return new MobFast();
        }
    },
    Shooter {
        @Override
        public Mob constructMob() {
            return new MobShooter();
        }
    },
    Slow {
        @Override
        public Mob constructMob() {
            return new MobSlow();
        }
    },
    Standard {
        @Override
        public Mob constructMob() {
            return new MobStandard();
        }
    },
    Boss {
        @Override
        public Mob constructMob() {
            return new MobBoss();
        }
    };


    public abstract Mob constructMob();

    public static MobType generateRandomMob(){
            int type = (int) (Math.random()*5);
            if (type>2)         return Standard;
            else if (type==2)   return Fast;
            else if (type==1)   return Shooter;
            else                return Slow;
    }
}

