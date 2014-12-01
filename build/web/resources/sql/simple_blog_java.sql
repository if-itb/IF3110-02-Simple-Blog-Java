-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2014 at 08:37 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simple_blog_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `komentar` varchar(200) NOT NULL,
  `tanggal` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_post` (`id_post`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(200) NOT NULL,
  `author` varchar(200) NOT NULL,
  `tanggal` date NOT NULL,
  `konten` text NOT NULL,
  `status` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `judul`, `author`, `tanggal`, `konten`, `status`) VALUES
(1, 'Davion, the Dragon Knight', 'dota2', '2014-11-22', 'After years on the trail of a legendary Eldwurm, the skilled dragon-slayer found himself facing a disappointing foe: the dreaded Slyrak had grown ancient and frail, its wings tattered, its few remaining scales stricken with scale-rot, its fangs ground to nubs, and its fire-gouts no more threatening than a pack of wet matchsticks. Seeing no honor to be gained in dragon-murder, the young knight prepared to turn away and leave his old foe to die in peace. But a voice crept into his thoughts, and Slyrak gave a whispered plea that the knight might honor him with death in combat. The knight agreed, and found himself rewarded beyond expectation for his act of mercy: As he sank his blade in Slyrak''s breast, the dragon sank a claw into his throat. As their blood mingled, Slyrak sent his power out along the Blood Route, offering all its strength and centuries of wisdom to the knight. The dragon''s death sealed their bond, and Dragon Knight was born. The ancient power slumbers in the knight, waking when he calls it; or perhaps it is the Dragon that calls the Knight...\n', 'published'),
(2, 'Karroch, the Beastmaster', 'dota2', '2014-11-23', 'Karroch was born a child of the stocks. His mother died in childbirth; his father, a farrier for the Last King of Slom, was trampled to death when Karroch was five. Afterward Karroch was indentured to the king’s menagerie, where he grew up among all the beasts of the royal court: lions, apes, fell-deer, and things less known, things barely believed in. When the lad was seven, an explorer brought in a beast like none before seen. Dragged before the King in chains, the beast spoke, though its mouth moved not. Its words: a plea for freedom. The King only laughed and ordered the beast perform for his amusement; and when it refused, struck it with the Mad Scepter and ordered it dragged to the stocks.\n\nOver the coming months, the boy Karroch sneaked food and medicinal draughts to the wounded creature, but only managed to slow its deterioration. Wordlessly, the beast spoke to the boy, and over time their bond strengthened until the boy found he could hold up his end of a conversation—could in fact speak now to all the creatures of the King''s menagerie. On the night the beast died, a rage came over the boy. He incited the animals of the court to rebel and threw open their cages to set them amok on the palace grounds. The Last King was mauled in the mayhem. In the chaos, one regal stag bowed to the boy who had freed him; and with Beastmaster astride him, leapt the high walls of the estate, and escaped. Now a man, Karroch the Beastmaster has not lost his ability to converse with wild creatures. He has grown into a warrior at one with nature’s savagery.', 'unpublished'),
(3, 'Leviathan, the Tidehunter', 'dota2', '2014-11-24', 'The Tidehunter known as Leviathan was once the champion of the Sunken Isles, but his motives are as mysterious as those of his people. We all know the importance of the Drylanders'' shipping lanes, how empires may rise and fall according to who controls the open water. Far less is known of the submarine lanes, and how the warring tribes of the Meranthic Diaspora have carved out habitations through endless undersea skirmishes. In the fragile treaties between the Mer and Men, we can glimpse the extent of the drowned empires, but their politics appear complex and opaque. It would seem that Leviathan tired of such petty strife, and set off on his own, loyal only to his abyssal god, Maelrawn the Tentacular. He stalks the shallows now in search of men or meranths who stray into his path, and with a particular loathing for Admiral Kunkka, who has long been his nemesis for reasons lost in the deepest trenches of the sea.\n', 'published'),
(4, 'Ostarion, the Wraith King', 'dota2', '2014-11-25', 'For untold years, King Ostarion built a kingdom from the remains of his enemies. It was an obsessive''s errand, done to pass the long eternities of a monarchy that seemed fated never to end. He believed that as long as he built up the towers of his palace, he could not die. But eventually he learned that he had been deluded...that bone itself could perish. Deeply mistrustful of flesh, he sought a more permanent way of extending his reign, and at last settled on pursuit of wraith energy, a form of pure spirit given off by certain dark souls at death. Should he infuse himself with Wraith Essence, he thought he might create a body as luminous and eternal as his ego. On the millennial solstice known as Wraith-Night, he submitted to a rite of transformation, compelling his subjects to harvest enough souls to fuel his ambition for immortality. No one knows how many of his champions died, for the only survivor who mattered was the Wraith King who rose with the sun on the following morn. Now he rarely spends a moment on his glowing throne--but strides out with sword drawn, demanding a fealty that extends far beyond death.\n', 'unpublished'),
(5, 'Naix, the Lifestealer', 'dota2', '2014-11-26', 'In the dungeons of Devarque, a vengeful wizard lay in shackles, plotting his escape. He shared his cell with a gibbering creature known as N''aix, a thief cursed by the Vile Council with longevity, so that its life-sentence for theft and cozening might be as punishing as possible. Over the years, its chains had corroded, along with its sanity; N''aix retained no memory of its former life and no longer dreamt of escape. Seeing a perfect vessel for his plans, the wizard wove a spell of Infestation and cast his life-force into N''aix''s body, intending to compel N''aix to sacrifice itself in a frenzy of violence while the mage returned to his body and crept away unnoticed. Instead, the wizard found his mind caught in a vortex of madness so powerful that it swept away his plans and shattered his will. Jarred to consciousness by the sudden infusion of fresh life, N''aix woke from its nightmare of madness and obeyed the disembodied voice that filled its skull, which had only the one thought: To escape. In that moment Lifestealer was born. The creature cast its mind into dungeon guards and soldiers, compelling them to open locks and cut down their companions, opening an unobstructed path to freedom while feeding on their lives. Lifestealer still wears the broken shackles as a warning that none may hold him, but on the inside remains a prisoner. Two minds inhabit the single form--a nameless creature of malevolent cunning, and the Master whose voice he pretends to obey.\n', 'published'),
(6, 'Rattletrap, the Clockwerk', 'dota2', '2014-11-27', 'Rattletrap descends from the same far-flung kindred as Sniper and Tinker, and like many of the Keen Folk, has offset his diminutive stature through the application of gadgetry and wit. The son of the son of a clockmaker, Rattletrap was many years apprenticed to that trade before war rode down from the mountains and swept the plains villages free of such innocent vocations. “Your new trade is battle,” his dying father told him as the village of their ancestors lay in charred and smoking ruins.\n\nIt is a poor tradesman who blames his tools, and Rattletrap was never one to make excuses. After burying his father among the ruins of their village, he set about to transform himself into the greatest tool of warfare that any world had ever seen. He vowed to never again be caught unprepared, instead using his talents to assemble a suit of powered Clockwerk armor to make the knights of other lands look like tin cans by comparison. Now Rattletrap is alive with devices—a small but deadly warrior whose skills at ambush and destruction have risen to near-automated levels of efficiency. An artisan of death, his mechanizations make short work of the unwary, heralding a new dawn in this age of warfare. What time is it? It''s Clockwerk time!', 'unpublished'),
(7, 'Morphling', 'dota2', '2014-11-28', 'For dark eons the comet circled. Held in thrall to a distant sun, bound by gravity’s inexorable pull, the massive ball of ice careened through the blackness between worlds, made strange by its dark journey. On the eve of the ancient war of the Vloy, it punched down through the sky and lit a glowing trail across the night, a sign both armies took for an omen. The frozen ball melted in a flash of boiling heat, as below two forces enjoined in battle across the border of a narrow river. Thus freed from its icy stasis, the Morphling was born into conflict, an elemental power at one with the tides of the ocean, capricious and unconstrained. He entered the fight, instinctively taking the form of the first general who dared set foot across the water, and then struck him dead. As the motley warriors clashed, he shifted from form to form throughout the battle, instantly absorbing the ways of these strange creatures--now a footsoldier, now an archer, now the cavalryman--until, by the time the last soldier fell, Morphling had played every part. The battle''s end was his beginning.\n', 'published'),
(8, 'Azwraith, the Phantom Lancer', 'dota2', '2014-11-29', 'The remote village of Pole had no knowledge of the wars raging in the heart of the kingdom. For them, the quiet of spear fishing and a family meal were all that a full life required. Yet war came for them nonetheless. Joining the able-bodied conscripts as they filed past their homes, the humble lancer Azwraith vowed to bring peace to his kingdom, and in so doing, his people. Placed with his kin in the vanguard of the final assault against the Dread Magus Vorn, the cost to his fellows was absolute. As the charging force battled toward the fortress, Azwraith alone among his kind remained standing, and he alone was able to infiltrate the keep. Focused and infuriated by the slaughter of his brothers, Azwraith bested each of the wizard''s deadly traps and conjured guardians. Soon the simple fisherman arrived at Vorn''s tower sanctum. The pair dueled through the night, pike to staff, as chaos raged below, and with a deafening cry Azwraith pierced his enemy. But the wizard did not simply expire; he exploded into uncountable shards of light, penetrating his killer with power. As the dust settled and the smoke of combat began to clear, Azwraith found himself standing among a throng of his kin. Each seemed to be dressed as he was, each seemed armed as he was, and he could sense that each thought as he did. Aware that his allies were approaching, he willed these phantoms to hide themselves, and one by one they began to vanish into nothingness. As the soldiers came upon the sanctum, they found the warrior that had bested the wizard. When they approached their champion, the lancer vanished. The pikeman who had stood before them was no more than another phantom.\n', 'unpublished'),
(9, 'Mortred, the Phantom Assassin', 'dota2', '2014-11-30', 'Through a process of divination, children are selected for upbringing by the Sisters of the Veil, an order that considers assassination a sacred part of the natural order. The Veiled Sisters identify targets through meditation and oracular utterances. They accept no contracts, and never seem to pursue targets for political or mercenary reasons. Their killings bear no relation to any recognizable agenda, and can seem to be completely random: A figure of great power is no more likely to be eliminated than a peasant or a well digger. Whatever pattern the killings may contain, it is known only to them. They treat their victims as sacrifices, and death at their hand is considered an honor. Raised with no identity except that of their order, any Phantom Assassin can take the place of any other; their number is not known. Perhaps there are many, perhaps there are few. Nothing is known of what lies under the Phantom Veil. Except that this one, from time to time, when none are near enough to hear, is known to stir her veils with the forbidden whisper of her own name: Mortred.\n', 'published');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `role` varchar(200) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `email`, `role`) VALUES
('root', 'root', 'root@gmail.com', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
