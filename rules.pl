
sameColor(C, C).
sameSimbol(S, S).

%if Card1 and Card2 are Common CardS, they Can be played if they have the Same Color or the Same Simbol
canPlayCC(Color1, Simbol1, Color2, Simbol2) :-
    sameColor(Color1, Color2);
    sameSimbol(Simbol1, Simbol2).

%if Card1 iS a wild Card and Card2 iS a wild Card, Card2 Can be played anyway.
canPlayWW() :-
    true.

%if Card1 iS a wild Card and Card2 iS a Common Card, Card2 Can be played if it haS the Same Color aS requeSted by the player.
canPlayWC(ChosenColor, Color2) :-
    sameColor(ChosenColor, Color2).

%if Card1 iS a Common Card and Card2 iS a wild Card, Card2 Can be played if Card1 iSn't skip or drawTwo.
canPlayCW(Simbol1) :-
    Simbol1 \= skip,
    Simbol1 \= drawTwo.

drawTwo(drawTwo).
skip(skip).
drawFour(drawFour).
reverse(reverse).


