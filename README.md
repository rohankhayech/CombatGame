# Textual Turn-Based Combat Game
## Description
This project is a textual, turn-based combat game written for a university assignment.

The game involves fighting a selection of enemies using a variety of attack options, earning gold and trading weapons, armour and potions with the shop. The game is designed to be modular and expandable, allowing for new items and enemies to be easily added to the game. 

The source code demonstrates use of common software design patterns such as the observer pattern used in event-driven programming and the decorator pattern used to describe in-game item attributes.

It also features a text-based heirarchal menu system, utilising the composite pattern allowing for many levels of sub-menus. This system allows for population of menu options at runtime and supports rich return of selected objects. 

## How to Run
Run the following command in a bash or Powershell terminal:
```
'./gradlew run'
```