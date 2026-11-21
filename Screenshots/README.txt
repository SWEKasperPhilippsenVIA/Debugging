Debugging – Battleship (debug-demo)
===================================

Teknikker jeg brugte, og hvordan:

1. Line breakpoint + Step Into (screenshot 01-03): Breakpoint i Board.calculateEndPoint() og Step Into
   getRandomInteger(). Variables viste maxValue = 1, og Evaluate Expression gav nextInt(1) = 0 hver gang.
   Switchen rammer derfor altid case 0, så skibet bliver altid vandret.
   Rettelse: getRandomInteger(max) returnerer nu 0..max (nextInt(max + 1)).

2. Breakpoint på hitCount++ + Evaluate Expression (screenshot 04-06): Breakpointet stoppede ved hver
   træffer og viste, at hitCount når 3. Et breakpoint i isSunk() viste, at "3 > 3" er false, mens
   "3 >= 3" er true. Derfor kom "Ship sunk!" aldrig. Rettelse: isSunk() bruger nu >=.

3. Exception breakpoint på ArrayIndexOutOfBoundsException (screenshot 07-08): Debuggeren stoppede,
   da exceptionen blev kastet. Variables viste startPoint x=8, endPoint x=10 og x=10, mens
   grid.length = 10. Skibet kunne altså starte så tæt på kanten, at det gik ud over griddet.
   Rettelse: Retningen vælges først, og startpunktet begrænses, så hele skibet altid er inden for.

Ekstra fund: Player kunne aldrig skyde på række/kolonne 9 (nextInt(9) giver 0-8). Det er rettet på
samme måde, så alle 100 felter kan rammes.

Test: Det rettede program er kørt 20.000 gange med 0 exceptions, ca. 50/50 vandrette/lodrette skibe
og "Ship sunk!" præcis når der er 3 træffere (screenshot 09 viser rettelserne, 10 en kørsel efter).
