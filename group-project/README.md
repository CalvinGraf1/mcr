# Projet_MCR

# Explication du projet 

- Jeu de simulation de bataille entre 2 camps

On utilise le builder pour la création de différents personnages personnalisé à rajouter dans notre équipe et l'équipe adverse

On a 3 types de personnages : Orc (corps à corps), Elf (à distance) ou Humain (les deux)

On peut les personnaliser de deux manières différentes : les armures qui ajoutent des points de vie max et les armes qui définissent la puissance d'attaque

Les 3 types de personnages auront des statistiques différentes en terme de vie / attaque, mais les deux cumulés seront les mêmes pour tous et qui sont inchangeable mis à part avec l'équipement et armure ajoutée.

Nous avons 3 niveaux d'armures : gris (faible), bleu (moyen), violet (fort)
Nous avons 2 types d'armes : corps à corps ou à distance
Il y a 3 armes différents : lance et épée (corps à corps), arc (distance)
Il y a 2 types d'épée différents : normal ou magique
L'épée normal peut avoir les effets essence et feu (elle doit d'abord mettre de l'essence pour la mettre en feu)
L'épée magique peut avoir les effets essence, feu, poudre de perlimpinpin (pas besoin d'essence pour la mettre en feu. Si l'épée a déjà du feu, elle ne peut pas avoir de poudre de perlimpinpin)


Chaque race a une restriction d'arme en fonction de son type
1. Humain : Corps à corps et à distance
2. Orc : Corps à corps 
3. Archer : A distance
Chacune des armes à 3 niveaux également (gris, bleu, violet)

Nous avons 3 types de casques également : gris, bleu, violet.
Chaque personnage peut avoir ou non une côte de maille sauf les Elf qui ne peuvent pas en avoir.
Impossible de porter une armure sans côte de maille.
Il faut obligatoirement respecter l'ordre suivant : côte de maille -> armure -> casque -> arme
Il est cependant possible de porter un casque sans armure.

Chaque arme ou armure coûte un certains nombre de points pour les ajouter. Plus l'équipement est fort, plus ça coûte cher. Chaque personnage doit obligatoirement avoir une arme et une armure

1. Le nombre et l'équipement de l'équipe ennemi est défini aléatoirement en fonction de son nombre de point

2. Nous devons définir notre équipe en ajoutant des personnages (nous pouvons choisir leur race, armure et arme) afin d'avoir une meilleur équipe que l'adversaire (nous avons le même nombre de point).

3. Le combat est lancé et les attaques se font au tour par tour en commençant par les méchants. Les attaques se font de manière aléatoire, on ne choisi pas qui on attaque.

4. Si on perd, on doit recommencer le même combat en refaisant une nouvelle équipe. Si on gagne, on passe au combat suivant et nous avons 5 points de plus pour faire notre équipe.