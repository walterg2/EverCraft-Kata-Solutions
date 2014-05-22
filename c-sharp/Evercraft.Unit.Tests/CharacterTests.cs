using NUnit.Framework;
using Evercraft.Combat;

namespace Evercraft.Unit.Tests
{
    class CharacterTests
    {
        private Character _character;

        [SetUp]
        public void Setup()
        {
            _character = new Character("Bob");
        }

        [Test]
        public void ItHasAName()
        {
            Assert.AreEqual("Bob", _character.Name);
        }

        [TestCase(Alignment.Good)]
        [TestCase(Alignment.Evil)]
        [TestCase(Alignment.Neutral)]
        public void ItHasAValidAlignmentOf(Alignment alignment)
        {
            _character.Alignment = alignment;

            Assert.AreEqual(alignment, _character.Alignment);
        }

        [Test]
        public void ItHasADefaultArmorClassOfTen()
        {
            Assert.AreEqual(10, _character.ArmorClass);
        }

        [Test]
        public void ItHasDefaultHitPointsOfFive()
        {
            Assert.AreEqual(5, _character.HitPoints);
        }

        [TestFixture]
        public class Combat
        {
            private Character _attacker;
            private Character _defender;

            [SetUp]
            public void Setup()
            {
                _attacker = new Character("Bob");
                _defender = new Character("Jane");
            }

            [Test]
            public void AttackerSuccessfullyAttacksWhenRollEqualsDefendersArmorClass()
            {
                Assert.IsTrue(_attacker.Attack(_defender, _defender.ArmorClass));
            }

            [Test]
            public void AttackerSuccessfullyAttacksWhenRollIsGreaterThanDefendersArmorClass()
            {
                Assert.IsTrue(_attacker.Attack(_defender, _defender.ArmorClass + 1));
            }

            [Test]
            public void AttackerUnsuccessfullyAttacksWhenRollEqualsDefendersArmorClass()
            {
                Assert.IsFalse(_attacker.Attack(_defender, _defender.ArmorClass - 1));
            }

            [Test]
            public void DefenderIsDamagedWhenAttackIsSuccessful()
            {
                var defenderHitPoints = _defender.HitPoints;

                _attacker.Attack(_defender, _defender.ArmorClass);

                Assert.AreEqual(defenderHitPoints - 1, _defender.HitPoints);
            }

            [Test]
            public void DefenderDamageIsDoubledWhenAttackRollIsNaturalTwenty()
            {
                var defenderHitPoints = _defender.HitPoints;

                _attacker.Attack(_defender, 20);

                Assert.AreEqual(defenderHitPoints - 2, _defender.HitPoints);
            }

            [Test]
            public void DefenderHasPerishedWhenHitPointsAreReducedToZero()
            {
                _attacker.Attack(_defender, 20);
                _attacker.Attack(_defender, 20);
                _attacker.Attack(_defender, 20);

                Assert.IsFalse(_defender.IsAlive());
            }

            [Test]
            public void DefenderIsAliveWhenHitPointsAreGreaterThanZero()
            {
                Assert.IsTrue(_defender.IsAlive());
            }

        }
    }
}
