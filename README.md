# Quicktipp-Generator
Der Quicktipp-Generator ermöglicht die Generierung von Quicktipps für die Spiele Lotto (6 aus 49) und Eurojackpot, unter Berücksichtigung von Unglückszahlen.
Die gewählten Unglückszahlen kommen nicht im Quicktipp vor.

Der Quicktipp-Generator wird über die Kommandozeile ausgeführt.

Es besteht die Möglichkeit in Quicktipp-Generator/src mit javac Quicktipp.java das Programm zu kompilieren.
Und mit java Quicktipp *Unglückszahlen* auszuführen. *Unglückszahlen* kann durch bis zu 6 Ganzzahlen ersetzt werden, die Werte von 0-50 annehmen können.

Alternativ kann man in den Ordner der Quicktipp-Generator.jar zu navigieren.
Und die durch java -jar Quicktipp-Generator.jar *Unglückszahlen* auszuführen. Auch hier kann *Unglückszahlen* durch bis zu 6 Ganzzahlen ersetzt werden, die Werte von 0-50 annehmen können.

Unglückszahlen werden nach erfolgreicher Quicktippgenerierung gespeichert und überschreiben ältere gespeicherte Zahlen. Dazu wird eine Unglueckszahlen.txt Datei erstellt, in der sich die gespeicherten Zahlen befinden.
Wenn gespeicherte Zahlen vorhanden sind kann man die Apllikation ohne *Unglückszahlen* aufrufen und es werden die zuletzt gespeicherten Zahlen verwendet.
Die Steurung erfolgt über die Eingabe von Zahlen in der Kommandozeile und der anschließenden Bestätigung durch die Eingabetaste. 

Die Logdatei QuicktippLogger.log wird ebenfalls im aktuellen Verzeichnis erstellt.
