
namespace Evercraft.Combat
{
    public class Character
    {
        private int _hitPoints = 5;

        public string Name { get; set; }
        public Alignment Alignment { get; set; }
        public int ArmorClass
        {
            get { return 10; }
        }
        public int HitPoints
        {
            get { return this._hitPoints; }
            private set { this._hitPoints = value; }
        }


        public Character(string name)
        {
            Name = name;
        }

        public bool Attack(Character defender, int roll)
        {
            var attackSuccessful = roll >= defender.ArmorClass;

            if (attackSuccessful)
            {
                defender.Damage();
                if (roll == 20)
                {
                    defender.Damage();
                }
            }

            return attackSuccessful;
        }

        public void Damage()
        {
            HitPoints = HitPoints - 1;
        }

        public bool IsAlive()
        {
            return HitPoints > 0;
        }

    }
}
