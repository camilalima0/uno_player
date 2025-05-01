% Colors
color(red).
color(green).
color(blue).
color(yellow).

% Symbols
symbol(_0).
symbol(_1).
symbol(_2).
symbol(_3).
symbol(_4).
symbol(_5).
symbol(_6).
symbol(_7).
symbol(_8).
symbol(_9).
symbol(skip).
symbol(reverse).
symbol(draw_two).
symbol(wild).
symbol(draw_four).

% Common common
canPlayCC(Color1, Symbol1, Color2, Symbol2) :-
    (Color1 = Color2 ; Symbol1 = Symbol2).

%common wild
canPlayCW(Symbol1) :-
    Symbol1 \= skip,
    Symbol1 \= draw_two.

%wild common
canPlayWC(ChosenColor, Color2) :-
    ChosenColor == Color2.

%wild wild
canPlayWW() :-
    true.

draw_two(Symbol) :- Symbol = draw_two.
draw_four(Symbol) :- Symbol = draw_four.
skip(Symbol) :- Symbol = skip.

% % Wilds can be played unless the previous card was skip or draw_two
% can_play(wild, _, _, _, skip).
% can_play(wild, _, _, _, draw_two) :- !, fail.
% can_play(wild, _, _, _, _) :- !.

% % Draw four cards can be played unless the previous card was skip or draw_two
% can_play(draw_four, _, _, _, skip).
% can_play(draw_four, _, _, _, draw_two) :- !, fail.
% can_play(draw_four, _, _, _, _) :- !.








