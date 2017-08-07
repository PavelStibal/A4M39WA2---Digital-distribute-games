INSERT INTO category (id, name, description, parentcategory_id) VALUES
(1, 'Adventura', 'Adventura je druh počítačové hry, ve které hlavní hrdina ' ||
 'řeší rozličné úkoly. Hráč postupuje ''klikatým'' příběhem k vzdálenému cíli, ' ||
  'přičemž je kladen důraz na mnohost řešení zápletek a hádanek.', NULL),
(2, 'FPS', 'Podžánr akčních počítačových her charakteristický simulací vlastního ' ||
 'pohledu herní postavy, neboli postavy za kterou hráč jedná v samotné hře. Zkratka ' ||
  'znamená v češtině střílečka z pohledu první osoby.', NULL),
(3, 'MOBA', 'Žánr hry, ve kterém se více hráčů utká online v aréně.', NULL),
(4, 'MMORPG', 'Jedná se počítačovou online hru na hrdiny o více hráčích, která umožňuje ' ||
 'připojení i tisíců hráčů najednou; zpravidla skrze Internet. Hra se obvykle, podobně ' ||
  'jako jiné hry na hrdiny, odehrává ve fiktivním světě, často ve fantasy či sci-fi ' ||
   'prostředí.', NULL);

INSERT INTO manufacturer (id, name, description) VALUES
(1, 'Zima Software', 'Česká firma zabývající se vývojem počítačových her.'),
(2, 'Valve Corporation', 'Americká společnost zabývající se vývojem videoher a online ' ||
 'distribucí se sídlem v Bellevue v americkém státě Washington.'),
(3, 'Riot Games', 'Americká video herní společnost, která byla založena v roce 2006. Jejich ' ||
 'sídlem je Santa Monica v Kalifornii v USA.');

INSERT INTO game (id, name, description, category_id, parameters, manufac_id) VALUES
(1, 'Polda 6', 'Hra byla vydána v prosinci roku 2014', 1, 'Bohuzel nejsou', 1),
(2, 'Counter-Strike', 'Hráči na konci každého kola získávají podle osobního a týmového ' ||
 'výkonu peníze (za zabití nepřítele, splnění úkolu, ale i za prohru), které využívají k ' ||
  'nákupu zbraní a vybavení.', 2, 'Bohuzel nejsou', 2),
(3, 'Half-Life 2', 'Half-Life 2 je, stejně jako jeho předchůdce Half-Life, first-person ' ||
 'shooter, rozdělený do kapitol, v nichž se hráč ujímá Gordona Freemana.', 2, 'Bohuzel nejsou', 2),
(4, 'League of Legends', 'je online hra pro více hráčů. Jedná se o hru typu "free to play".' ||
 'Proti sobě se postaví dva týmy po 5 (5 vs 5) nebo 3 (3 vs 3) hráčích. Každý si zvolí svého ' ||
  'hrdinu', 3, 'Bohuzel nejsou', 3),
(5, 'DOTA 2', 'Je strategická počítačová hra žánru MOBA (Multiplayer online battle arena), hra ' ||
 'je v mnoha zemích uznaná jako plnohodnotný sport', 2, 'Bohuzel nejsou', 3);

INSERT INTO player (id, nick, pass, name, email) VALUES
(1, 'user_1', 'user_1', NULL, NULL),
(2, 'user_2', 'user_2', NULL, NULL);