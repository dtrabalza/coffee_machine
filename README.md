#Coffee Machine

This is a coffee machine able to:

- List existing drinks
- Prepare a drink
- Increase / decrease drink strength
- Add sugar to a drink
- Add milk to certain drinks
- Save a favorite drink
- View saved drinks
- Prepare favorite drinks

Input is accepted using the command line.
The coffee machine contains a certain number of base ingredients:
- Water
- Coffee
- Milk
- Chocolate
- Sugar

Drinks are configurable without recompiling the software and stored in a file.

#Run the program

In the repository's root folder, type

gradle clean installApp

go to the folder: build/install/coffee_machine/bin/

run coffee_machine (or coffee_machine.bat for windows)

The log file will be in build/install/coffee_machine/bin/coffeeMachine.log
