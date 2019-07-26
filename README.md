GAME RULES
- Winning condition: one of the target searchers (scouts and finders) found the target (the donut for team ants and the door for team beetles)
- Left side pane: ants: main moving method: walking (meaning moving might be restricted if there's an insect on its way)
	- Scout: can capture the donut
	- Ranger: has air attack
	- Heavy
- Right side pane: beetles: main moving method: flying (can fly over other insects)
	- Finder: can detect the door; has kick back attack
	- Bogus: can paralyse the insect being attacked for 1 turn
	- Greedy
- Heal button: once clicked, a certain amount of HP will be added to the insect, but it'll be paralysed for 1 turn.
- Undo button: can be pressed atmost 3 consecutive times; this functionality can only be used once by each team.
- The insect can attack its own team


NOTES ON IMPLEMENTATION 
	- Controller-View coupling: The controllers are also in charge of initialising and changing the views' look as FXML imposes a highly coupled relationship between the controllers and the views. The only way of accessing view components of a FXML file is through the @FXML annotation from the controller specified in the FXML file, and we tried to create a separated class to change the look of these components but it seems impossible to do that from outside of the controller class. 
	- HP display: we didn't have enough time to implement this functionality so it won't be obvious whether the attack (especially HPAttack) has any effect on the attackee or not. So to test out the HPAttack, we would recommend using either a Heavy or Greedy as they are the strongest and would knock the opponent down quickly.