# uno_player
This is an AI project for college, where AI will play uno with the user.

common cards (76):
colors: red, green, blue, yellow
each color: 0, two of each from 1 to 9
names: red0, red1a, red1b, red2a...

action cards (24):
skip (2 of each color)
reverse (2 of each color)
draw two (+2) (2 of each color)
names: skipRed0, skipRed2... reverseRed1, reverseRed2..., drawTwoRed1, drawTwoRed2...

wild cards (8):
wild (4 cards)
wild draw four (+4) cards
names: wild1, wild2... wildDrawFour1, wildDrawFourTwo...

master class: card(name)

commoncard(name, color, simbol)

wildcard(name, simbol)

